# Copyright (c) 2012-2014 LG Electronics, Inc.

PKGV .= "-0webos1"
EXTENDPRAUTO_append = "webos3"

EXTRA_OECONF =+ " --disable-static"

do_configure_append() {
    sed -i '/^SUBDIRS/s/ doc tests//' Makefile
}
