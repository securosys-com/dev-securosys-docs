
/*
 * Copyright (C) 2022, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Sample of how to performantly encode large data (files)
 * with AES/GCM.
 *
 * @author Eric Laroche
 */
public class ChunkingGcmSample {

	// optional reference files
	final static String referencebase = "chunking-gcm-test-data";
	final static String referenceinput = referencebase + ".input";
	final static String referenceencrypted = referencebase + ".encrypted";
	final static String referencedecrypted = referencebase + ".decrypted";

	/** Report rate of operation for a size (bytes) and interval (nanos). */
	static void report(final long start, final long stop, final long size, final String operation) {
		final long nanos = Math.max(stop - start, 1);
		final double seconds = nanos / (double)1e9;
		final double megas = size / (double)(1 << 20);
		final double rate = megas / seconds;
		System.out.println(
			operation + ": "
			// + seconds + " s, "
			+ rate + " Mbytes/s"
		);
	}

	public static void main(final String... args) throws Exception {
		(new ChunkingGcmSample()).runtest();
	}

	// input/output files
	final String base = "out." + UUID.randomUUID().toString();
	final String input = base + ".input";
	final String encrypted = base + ".encrypted";
	final String decrypted = base + ".decrypted";

	// sizes
	final int mega = (1 << 20); // MB
	long size = ((long)1 << 30); // 1 GB, file size
	// long size = ((long)1 << 24); // 16 MB (smaller test size)
	int chunk = mega; // 1 MB, chunk size, fits well in memory
	final int spare = 64; // spare buffer size, helps minimizing memcopies

	/** Run the test. */
	void runtest() throws Exception {

		final String usedInput;

		if ((new File(referenceinput)).exists()) {
			usedInput = referenceinput;
		} else {
			long start, stop; // timings
			System.out.println("writing " + ((double)size / mega) + " Mbytes random data...");
			start = System.nanoTime();
			makeRandom(input, size, chunk);
			stop = System.nanoTime();
			report(start, stop, size, "random data");
			usedInput = input;
		}

		runtest(usedInput);

		// [cleanup]
		if (usedInput == input) {
			(new File(input)).delete();
		}

	}

	/** Run the test. */
	void runtest(final String input) throws Exception {

		// get the real input size; if reference test data is used, this may differ
		size = Files.size(Paths.get(input));

		System.out.println("testing AES/GCM performance with " + ((double)size / mega) + " Mbytes input data");

		long start, stop; // timings

		final SecretKey key;

		// local AES key, session key (don't reuse when IV below is all zeros)
		// final KeyGenerator kg = KeyGenerator.getInstance("AES");
		// kg.init(256);
		// key = kg.generateKey();

		// make the key out of the head of the input file, so encrypted to be comparable to reference
		try (final InputStream is = new FileInputStream(input)) {
			final byte[] bytes = new byte[32];
			is.read(bytes);
			key = new SecretKeySpec(bytes, "AES");
		}

		// encryption
		start = System.nanoTime();
		encrypt(input, encrypted, chunk, key);
		stop = System.nanoTime();
		report(start, stop, size, "encrypt");

		// decryption
		start = System.nanoTime();
		decrypt(encrypted, decrypted, chunk, key);
		stop = System.nanoTime();
		report(start, stop, size, "decrypt");

		// [checks]
		assert equals(decrypted, input, chunk);
		if ((new File(referenceencrypted)).exists()) {
			assert equals(encrypted, referenceencrypted, chunk);
		}

		// [cleanup]
		(new File(decrypted)).delete();
		(new File(encrypted)).delete();

	}

	/** Make a random file of specified size. */
	void makeRandom(final String output, final long size, final int chunkSize) throws IOException {
		assert output != null;

		try (final OutputStream os = new FileOutputStream(output)) {
			makeRandom(os, size, chunkSize);
		}
	}

	/** Write random data of specified size. */
	void makeRandom(final OutputStream output, final long size, final int chunkSize) throws IOException {
		assert output != null;
		assert size >= 0;
		assert chunkSize > 0;

		final SecureRandom secureRandom = new SecureRandom();
		final byte[] bytes = new byte[chunkSize];
		int i;
		for (long n = 0; n < size; n += i) {
			secureRandom.nextBytes(bytes);
			i = chunkSize;
			if (n + i > size) {
				i = (int)(size - n);
				assert i > 0;
			}
			output.write(bytes, 0, i);
		}
	}

	/** Encrypt a file. */
	void encrypt(final String input, final String output, final int chunkSize, final SecretKey key) throws Exception {
		assert input != null;
		assert output != null;

		try (final InputStream is = new FileInputStream(input)) {
			try (final OutputStream os = new FileOutputStream(output)) {
				encrypt(is, os, chunkSize, key);
			}
		}
	}

	/** Encrypt a stream. */
	void encrypt(final InputStream input, final OutputStream output, final int chunkSize, final SecretKey key) throws Exception {
		assert input != null;
		assert output != null;
		assert chunkSize > 0;
		assert key != null;
		assert spare >= 0;

		final byte[] bytes = new byte[spare + chunkSize]; // provide spare bytes in the buffer, to enable less memcopy
		final byte[] helper = new byte[spare + chunkSize + spare]; // provide helper buffer for aes operation, with some more spare bytes, to enable less memcopy
		int i;
		for (long n = 0;; n += i) {

			// read part
			i = input.read(bytes, spare, bytes.length - spare);
			if (i < 0) { // we typically get more than 0, and then directly -1, i.e. never 0
				i = 0;
			}
			// in a perfect world we would check if i is actually chunkSize, but for test code we don't

			// encrypt part
			final Object[] vl = encrypt(bytes, spare, i, n, key, helper);
			assert vl != null;
			assert vl.length == 3;
			assert vl[0] instanceof byte[];
			final byte[] v = (byte[])vl[0];
			assert vl[1] instanceof Integer;
			final int o = ((Integer)vl[1]).intValue();
			assert o >= 0 && o <= v.length;
			assert vl[2] instanceof Integer;
			final int l = ((Integer)vl[2]).intValue();
			assert l >= 0 && Math.addExact(o, l) <= v.length;

			// write part
			output.write(v, o, l);

			if (i == 0) { // break after writing the last empty chunk
				break;
			}
		}
	}

	/**
	 * Encrypt a chunk.
	 *
	 * @param input input chunk; may be empty, but not null
	 * @param offs offset into input chunk, helps minimizing memcopies
	 * @param length payload length of input chunk
	 * @param offset offset into the original input stream, needed for later decrypt authentication, and unique encryption IV
	 * @param key AES encryption key
	 * @param output helper buffer; may be null; if it's not: better be big enough
	 * @return output/offset/length; will neither be null, nor empty
	 */
	Object[] encrypt(final byte[] input, final int offs, final int length, final long offset, final SecretKey key, final byte[] output) throws Exception {
		assert input != null;
		assert offs >= 0;
		assert length >= 0 && Math.addExact(offs, length) <= input.length;
		assert offset >= 0;
		assert key != null;

		final byte[] bytes;
		final int o;
		if (offs < 16) {
			// spare bytes offered by the caller are not enough; need to allocate/memcopy
			bytes = new byte[16 + length]; // offset (8), chunk length (8), input chunk (length)
			System.arraycopy(input, offs, bytes, 16, length);
			o = 0;
		} else {
			bytes = input;
			o = offs - 16;
		}

		// header: offset (8 bytes), chunk length (8 bytes)
		ByteBuffer.wrap(bytes, o, 8).order(ByteOrder.BIG_ENDIAN).putLong(offset);
		ByteBuffer.wrap(bytes, o + 8, 8).order(ByteOrder.BIG_ENDIAN).putLong(length);

		final byte[] iv = new byte[12];
		// offset determines IV; me may not reuse any IV, for cryptalalysis reasons
		ByteBuffer.wrap(iv, 0, 8).order(ByteOrder.BIG_ENDIAN).putLong(offset);
		// System.arraycopy(bytes, o, iv, 0, 8); // offset determines IV
		final GCMParameterSpec gcmp = new GCMParameterSpec(128, iv);
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding");
		gcm.init(Cipher.ENCRYPT_MODE, key, gcmp);

		final byte[] u;
		final byte[] v;
		final int l;
		if (output == null) {
			// no output buffer offered by the caller; need to allocate/memcopy
			u = gcm.doFinal(bytes, o, 16 + length);
			v = new byte[16 + u.length]; // encryped chunk length (8), chunk length (8), encryped chunk
			l = u.length;
			System.arraycopy(u, 0, v, 16, l);
		} else {
			// assume output buffer offered by the caller is big enough
			// this is done by the caller by allocating somewhat more than the input size
			// if it'd be too small, following would be thrown: javax.crypto.ShortBufferException: Output buffer must be (at least) 1048608 bytes long
			l = gcm.doFinal(bytes, o, 16 + length, output, 16);
			v = output;
		}

		// outer header (after encryption): encrypted length (8 bytes), chunk length (8 bytes)
		// [unencryped chunk length is handy when decrypting asynchronously (in parallel), as the reader needs]
		ByteBuffer.wrap(v, 0, 8).order(ByteOrder.BIG_ENDIAN).putLong(l);
		ByteBuffer.wrap(v, 8, 8).order(ByteOrder.BIG_ENDIAN).putLong(length);

		return new Object[] {v, Integer.valueOf(0), Integer.valueOf(16 + l)};
	}

	/** Decrypt a file. */
	void decrypt(final String input, final String output, final int chunkSize, final SecretKey key) throws Exception {
		assert input != null;
		assert output != null;

		try (final InputStream is = new FileInputStream(input)) {
			try (final OutputStream os = new FileOutputStream(output)) {
				decrypt(is, os, chunkSize, key);
			}
		}
	}

	/** Decrypt a stream. */
	void decrypt(final InputStream input, final OutputStream output, final int chunkSize, final SecretKey key) throws Exception {
		assert input != null;
		assert output != null;
		assert chunkSize > 0;
		assert key != null;

		byte[] u = null;
		byte[] helper = null;
		int i;
		for (long n = 0;; n += i) {

			// read part
			// outer header: encrypted length, chunk length
			final byte[] ub = new byte[16];
			i = input.read(ub);
			if (i <= 0) { // not expecting a non-empty terminating chunk
				throw new BadPaddingException("no terminating chunk");
			} else if (i != 16) { // incomplete header
				throw new BadPaddingException("cut chunk header");
			}
			final long ul = ByteBuffer.wrap(ub, 0, 8).order(ByteOrder.BIG_ENDIAN).getLong();
			final long l = ByteBuffer.wrap(ub, 8, 8).order(ByteOrder.BIG_ENDIAN).getLong();
			if (ul < 0 || ul > 2 * chunkSize) { // just a coarse sanity check. typically expected chunkSize+32
				throw new BadPaddingException("chunk length out of range");
			} else if (l < 0 || l > 2 * ul) { // just a coarse sanity check. typically expected chunkSize aka ul-32
				throw new BadPaddingException("inner chunk length out of range");
			}
			final int iul = (int)ul;
			if (u == null || u.length < iul) { // as all the chunks will be the same, there's only one allocation expected
				u = new byte[iul];
				helper = new byte[iul + spare]; // provide helper buffer for aes operation, to enable less memcopy
			}
			i = input.read(u, 0, iul);
			if (i < iul) {
				throw new BadPaddingException("cut chunk");
			}

			// decrypt part
			final Object[] vl = decrypt(u, i, (int)l, n, key, helper);
			assert vl != null;
			assert vl.length == 3;
			assert vl[0] instanceof byte[];
			final byte[] v = (byte[])vl[0];
			assert vl[1] instanceof Integer;
			final int o = ((Integer)vl[1]).intValue();
			assert o >= 0 && o <= v.length;
			assert vl[2] instanceof Integer;
			final int ll = ((Integer)vl[2]).intValue();
			assert ll >= 0 && Math.addExact(o, ll) <= v.length;

			i = ll;
			if (i == 0) {
				if (input.read() != -1) { // stream must be at end after terminating chunk
					throw new BadPaddingException("excess bytes after last chunk");
				}
				break;
			}

			// write part
			output.write(v, o, ll);
		}
	}

	/**
	 * Decrypt a chunk.
	 *
	 * @param input input chunk
	 * @param length payload length of input chunk
	 * @param offset expected offset into the original input stream, needed for later decrypt authentication, and unique encryption IV
	 * @param key AES decryption key
	 * @return output/offset/length; may be empty
	 */
	Object[] decrypt(final byte[] input, final int length, final int ilength, final long offset, final SecretKey key, final byte[] output) throws Exception {
		assert input != null;
		assert length >= 0 && length <= input.length;
		assert offset >= 0;
		assert key != null;

		final byte[] iv = new byte[12];
		ByteBuffer.wrap(iv, 0, 8).order(ByteOrder.BIG_ENDIAN).putLong(offset); // offset determines IV
		final GCMParameterSpec gcmp = new GCMParameterSpec(128, iv);
		final Cipher gcm = Cipher.getInstance("AES/GCM/NoPadding");
		gcm.init(Cipher.DECRYPT_MODE, key, gcmp);

		final byte[] bytes;
		final int l;
		if (output == null) {
			// no output buffer offered by the caller; need to allocate/memcopy
			bytes = gcm.doFinal(input, 0, length);
			l = bytes.length;
		} else {
			// assume output buffer offered by the caller is big enough
			// this is done by the caller by allocating somewhat around input size
			// if it'd be too small, following would be thrown: javax.crypto.ShortBufferException: Output buffer must be (at least) 1048576 bytes long
			l = gcm.doFinal(input, 0, length, output, 0);
			bytes = output;
		}

		if (l < 16) {
			throw new BadPaddingException("cut header after decryption");
		}
		final long nn = ByteBuffer.wrap(bytes, 0, 8).order(ByteOrder.BIG_ENDIAN).getLong();
		final long ii = ByteBuffer.wrap(bytes, 8, 8).order(ByteOrder.BIG_ENDIAN).getLong();
		final int il = l - 16;
		if (nn != offset) {
			throw new BadPaddingException("unexpected chunk offset");
		} else if (ii != il) {
			throw new BadPaddingException("unexpected chunk length");
		} else if (il != ilength) {
			throw new BadPaddingException("unexpected inner length");
		}
		return new Object[] {bytes, Integer.valueOf(16), Integer.valueOf(il)};
	}

	/** Check file equality. */
	boolean equals(final String input1, final String input2, final int chunkSize) throws IOException {
		assert input1 != null;
		assert input2 != null;

		try (final InputStream is1 = new FileInputStream(input1)) {
			try (final InputStream is2 = new FileInputStream(input2)) {
				return equals(is1, is2, chunkSize);
			}
		}
	}

	/** Check stream equality. */
	boolean equals(final InputStream input1, final InputStream input2, final int chunkSize) throws IOException {
		assert input1 != null;
		assert input2 != null;
		assert chunkSize > 0;

		final byte[] bytes1 = new byte[chunkSize];
		final byte[] bytes2 = new byte[chunkSize];
		for (;;) {
			final int i1 = input1.read(bytes1);
			final int i2 = input2.read(bytes2);
			if (i1 != i2) {
				return false;
			} else if (i1 < 0) {
				return true;
			} else if (!Arrays.equals(bytes1, bytes2)) {
				return false;
			}
		}
	}

}

/*

headers and tag generate 48 additional bytes per chunk:

+---------------------------------+
| length of encrypted chunk       |
| [8 bytes, bigendian]            |
| ('1 M + 32')                    |
+---------------------------------+
| length of unencrypted chunk     |
| [8 bytes, bigendian]            |
| ('1 M')                         |
+---------------------------------+---------------------------------+
| encryption of data to the right | stream offset                   |
| [typically 1 MiB + 16 bytes]    | [8 bytes, bigendian]            |
|                                 | ('0', '1 M', '2 M', ...)        |
|                                 +---------------------------------+
|                                 | chunk length                    |
|                                 | [8 bytes, bigendian]            |
|                                 | ('1 M')                         |
|                                 +---------------------------------+
|                                 | chunk plain data                |
|                                 | [typically 1 MiB]               |
|                                 | ...                             |
+---------------------------------+---------------------------------+
| implicit GCM tag                |
| [16 bytes]                      |
+---------------------------------+

notes:

- the length of the unencrypted chunk is needed outside the encrypted data (too) to enable asynchronous decryption
(reader must know next stream offset before last data chunk is decrypted)

- the stream offset is needed to:
-- authenticate chunk order
-- authenticate no chunk is missing
-- unique GCM IV per chunk (security requirement)

- stream offset needs more than 32 bits as it must support sizes bigger than 4 GiB

- having internal headers aligned to 16 bytes avoids additional AES pad overhead

- bigendian is the usual standard (when not mainly dealing with Marvell or Intel)

- a finishing empty input chunk (48 bytes output) signals the end

decrypt check notes:

- untimely end-of-streams must be signalled

- sanity checks on encrypted chunk length must be done (e.g. not negative, not much larger than expected chunk size)

- offset must be checked against expected per chunk, so the lengths

- if the finishing empty input chunk is missing, this must be signalled

implementation notes:

- input buffers have some spare leading bytes to enable in-place header creation
(avoiding need for copying 1 MiB)

- input and output buffers are pre-allocated, so no allocation overhead per chunk is expected

- input and output (after encryption/decryption) buffers are per async queue entry, so no race conditions happen

*/

