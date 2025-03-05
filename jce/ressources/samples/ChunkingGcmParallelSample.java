
/*
 * Copyright (C) 2022, Securosys SA
 * Author Eric Laroche <eric@securosys.ch>
 */

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;

/**
 * Sample of how to performantly, parallelized,
 * encode large data (files) with AES/GCM.
 *
 * @author Eric Laroche
 */
public class ChunkingGcmParallelSample extends ChunkingGcmSample {

	public static void main(final String... args) throws Exception {
		(new ChunkingGcmParallelSample()).runtest();
	}

	final int cores = Runtime.getRuntime().availableProcessors(); // cpus / cpu cores

	final int defaultconcurrency = cores - 1; // leave one core for the reader/dispatcher/writer
	final int configuredconcurrency = Integer.parseInt(System.getProperty("concurrency", String.valueOf(defaultconcurrency)));
	final int concurrency = Math.max(configuredconcurrency, 1); // don't have concurrency zero (etc)

	final int defaultqueuefactor = 2;
	final int configuredqueuefactor = Integer.parseInt(System.getProperty("queuefactor", String.valueOf(defaultqueuefactor)));
	final int queuefactor = Math.max(configuredqueuefactor, 1); // don't have queue factor zero (etc)
	final int queuesize = Math.multiplyExact(queuefactor, concurrency);

	final ExecutorService workers = Executors.newFixedThreadPool(concurrency, new DaemonizingThreadFactory("worker"));

	final long defaultsize = size;
	final long configuredsize = Long.parseLong(System.getProperty("size", String.valueOf(defaultsize)));
	{size = Math.max(configuredsize, 0);}

	final int defaultchunk = chunk;
	final int configuredchunk = Integer.parseInt(System.getProperty("chunk", String.valueOf(defaultchunk)));
	{chunk = Math.max(configuredchunk, 1);} // don't have chunk size zero (etc)

	@Override void runtest() throws Exception {
		System.out.println("running with concurrency " + concurrency + " (+ 1)");
		super.runtest();
	}

	@Override void encrypt(final InputStream input, final OutputStream output, final int chunkSize, final SecretKey key) throws Exception {
		assert input != null;
		assert output != null;
		assert chunkSize > 0;
		assert key != null;

		@SuppressWarnings("unchecked")
		final Future<Object[]>[] f = new Future[queuesize];
		int w = 0;
		int r = 0;
		final byte[][] inputhelper = new byte[queuesize][];
		final byte[][] outputhelper = new byte[queuesize][];
		for (int q = 0; q < queuesize; q++) {
			inputhelper[q] = new byte[spare + chunkSize];
			outputhelper[q] = new byte[spare + chunkSize + spare];
		}

		int i;
		for (long n = 0;; n += i) {

			for (;;) { // flush/wait

				// write part: write what's ready
				for (;;) {
					final Future<Object[]> ff = f[r];
					if (ff == null) { // nothing ready nor running
						break;
					} else if (!ff.isDone()) { // output is not yet ready
						break;
					}

					// flush what's ready
					final Object[] vl = ff.get();
					output.write((byte[])vl[0], ((Integer)vl[1]).intValue(), ((Integer)vl[2]).intValue());
					f[r] = null;
					r++;
					r %= f.length;
				}

				// wait for flush part
				final Future<Object[]> ff = f[w];
				if (ff != null) { // if there is no room to read: wait, and let flush
					ff.get();
				} else { // ok for reading
					break;
				}
			}

			// read part
			// final byte[] bytes = new byte[chunkSize];
			// i = input.read(bytes);
			final byte[] bytes = inputhelper[w];
			final byte[] obytes = outputhelper[w];
			i = input.read(bytes, spare, bytes.length - spare);
			if (i < 0) { // we typically get more than 0, and then directly -1, i.e. never 0
				i = 0;
			}

			// encrypt part
			// final byte[] in = Arrays.copyOf(bytes, i); // need a copy in async encryption
			final int length = i;
			final long offset = n;
			f[w] = workers.submit(new Callable<Object[]>() {
				@Override public Object[] call() throws Exception {
					// return encrypt(in, 0, length, offset, key, null);
					return encrypt(bytes, spare, length, offset, key, obytes);
				}
			});
			w++;
			w %= f.length;

			if (i == 0) { // break after writing the last empty chunk
				break;
			}
		}

		// flush part: wait/write
		for (;;) {
			final Future<Object[]> ff = f[r];
			if (ff == null) { // all done
				break;
			}

			final Object[] vl = ff.get();
			output.write((byte[])vl[0], ((Integer)vl[1]).intValue(), ((Integer)vl[2]).intValue());
			f[r] = null;
			r++;
			r %= f.length;
		}
		for (final Future<Object[]> ff : f) {
			assert ff == null;
		}
	}

	@Override void decrypt(final InputStream input, final OutputStream output, final int chunkSize, final SecretKey key) throws Exception {
		assert input != null;
		assert output != null;
		assert chunkSize > 0;
		assert key != null;

		@SuppressWarnings("unchecked")
		final Future<Object[]>[] f = new Future[queuesize];
		int w = 0;
		int r = 0;
		final byte[][] inputhelper = new byte[queuesize][];
		final byte[][] outputhelper = new byte[queuesize][];
		for (int q = 0; q < queuesize; q++) {
			inputhelper[q] = new byte[spare + chunkSize];
			outputhelper[q] = new byte[spare + chunkSize + spare];
		}

		int i;
		for (long n = 0;; n += i) {

			for (;;) { // flush/wait

				// write part: write what's ready
				for (;;) {
					final Future<Object[]> ff = f[r];
					if (ff == null) { // nothing ready nor running
						break;
					} else if (!ff.isDone()) { // output is not yet ready
						break;
					}

					// flush what's ready
					final Object[] vl = ff.get();
					output.write((byte[])vl[0], ((Integer)vl[1]).intValue(), ((Integer)vl[2]).intValue());
					f[r] = null;
					r++;
					r %= f.length;
				}

				// wait for flush part
				final Future<Object[]> ff = f[w];
				if (ff != null) { // if there is no room to read: wait, and let flush
					ff.get();
				} else { // ok for reading
					break;
				}
			}

			// read part
			final byte[] ub = new byte[16];
			i = input.read(ub);
			if (i <= 0) { // not expecting a non-empty terminating chunk
				throw new BadPaddingException("no terminating chunk");
			} else if (i != 16) { // incomplete header
				throw new BadPaddingException("cut chunk header");
			}
			final long ul = ByteBuffer.wrap(ub, 0, 8).order(ByteOrder.BIG_ENDIAN).getLong();
			final long l = ByteBuffer.wrap(ub, 8, 8).order(ByteOrder.BIG_ENDIAN).getLong();
			if (ul < 0 || ul > 2 * chunkSize) {
				throw new BadPaddingException("chunk length out of range");
			} else if (l < 0 || l > 2 * ul) {
				throw new BadPaddingException("inner chunk length out of range");
			}
			final int iul = (int)ul;
			// final byte[] u = new byte[iul];
			final byte[] u = inputhelper[w];
			final byte[] obytes = outputhelper[w];
			if (iul > u.length) {
				throw new BadPaddingException("internal buffer too small");
			}
			i = input.read(u, 0, iul);
			if (i < iul) {
				throw new BadPaddingException("cut chunk");
			}

			// decrypt part
			// final byte[] in = Arrays.copyOf(u, i); // need a copy in async decryption
			final int length = i;
			final int ilength = (int)l;
			final long offset = n;
			f[w] = workers.submit(new Callable<Object[]>() {
				@Override public Object[] call() throws Exception {
					// return decrypt(in, length, ilength, offset, key, null);
					return decrypt(u, length, ilength, offset, key, obytes);
				}
			});
			w++;
			w %= f.length;

			i = ilength;
			if (i == 0) {
				if (input.read() != -1) {
					throw new BadPaddingException("excess bytes after last chunk");
				}
				break;
			}
		}

		// flush part: wait/write
		for (;;) {
			final Future<Object[]> ff = f[r];
			if (ff == null) { // all done
				break;
			}

			final Object[] vl = ff.get();
			output.write((byte[])vl[0], ((Integer)vl[1]).intValue(), ((Integer)vl[2]).intValue());
			f[r] = null;
			r++;
			r %= f.length;
		}
		for (final Future<Object[]> ff : f) {
			assert ff == null;
		}
	}

	static class DaemonizingThreadFactory implements ThreadFactory {

		private final static long initialthreadnumber = 1;
		private final static AtomicLong instance = new AtomicLong(initialthreadnumber);
		private final ThreadFactory threadFactory = Executors.defaultThreadFactory();
		private final String name;

		public DaemonizingThreadFactory(final String name) {
			this.name = name;
		}

		@Override public Thread newThread(final Runnable r) {
			final Thread thread = threadFactory.newThread(r);
			final long i = instance.getAndIncrement();
			final String name = (i == initialthreadnumber ? this.name : this.name + "-" + i);
			thread.setName(name);
			thread.setDaemon(true);
			return thread;
		}

	}

}

