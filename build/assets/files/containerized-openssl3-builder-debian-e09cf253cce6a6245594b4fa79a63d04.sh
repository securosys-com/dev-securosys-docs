#!/usr/bin/env bash

set -euo pipefail

DISTRO_IMAGE_NAME="ubuntu:20.04"
OPENSSL_VERSION=3.2.1

export OPENSSL_VERSION;
mkdir -p "$(pwd)/openssl-${OPENSSL_VERSION}-bin"

docker run --rm \
       --env IUSER="$(id -u)" \
       --env IGRP="$(id -g)" \
       --env OPENSSL_VERSION \
       --volume "$(pwd)/openssl-${OPENSSL_VERSION}-bin:/tmp/out" \
       "${DISTRO_IMAGE_NAME}" bash -ce \
'
echo "-- Installing build dependencies";
export DEBIAN_FRONTEND=noninteractive;
apt-get -q update;
apt-get -yq install perl autoconf-archive automake libtool make gcc curl tar gzip;

TMPDIR=$(realpath "${TMPDIR:-/tmp}");

echo "-- Downloading openssl-${OPENSSL_VERSION}";
curl "https://www.openssl.org/source/openssl-${OPENSSL_VERSION}.tar.gz" -o "${TMPDIR}/openssl-${OPENSSL_VERSION}.tar.gz";
cd "${TMPDIR}";

echo "-- Extracting openssl-${OPENSSL_VERSION}";
tar xf "openssl-${OPENSSL_VERSION}.tar.gz";
mkdir -p "${TMPDIR}/build" "${TMPDIR}/inst";
cd "${TMPDIR}/build";

echo "-- Configuring openssl-${OPENSSL_VERSION}";
"../openssl-${OPENSSL_VERSION}/Configure" --libdir=lib --prefix="/opt/openssl-${OPENSSL_VERSION}" --openssldir="/opt/openssl-${OPENSSL_VERSION}/ssl";

echo "-- Compiling  openssl-${OPENSSL_VERSION}  (this may take a while)";
p="$(getconf _NPROCESSORS_ONLN)";
make --silent -j "${p}" -l "${p}" build_sw;
make --silent -j "${p}" -l "${p}" test;
make --silent -j "${p}" -l "${p}" install_sw install_ssldirs DESTDIR="${TMPDIR}/inst";

echo "-- Zipping binary openssl-${OPENSSL_VERSION}";
cd "${TMPDIR}/inst/opt/";
tar czf "${TMPDIR}/out/openssl-${OPENSSL_VERSION}.bin.tgz" .;
chown "${IUSER}:${IGRP}" "${TMPDIR}/out/openssl-${OPENSSL_VERSION}.bin.tgz";

echo "-- Build complete";
echo "-- To install OpenSSL on this system execute:";
echo "$ sudo tar -C /opt/ -xf \"openssl-${OPENSSL_VERSION}-bin/openssl-${OPENSSL_VERSION}.bin.tgz\"";
'
