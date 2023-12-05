# Copyright (c) 2023 Herman van Hazendonk <github.com@herrie.org>

DESCRIPTION = "Binder based ofono plugin (Needed for Android 8.0+)."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "webos/support"

DEPENDS = "ofono libgbinder libgbinder-radio glib-2.0 libglibutil libofonobinderpluginext"

inherit pkgconfig

SRC_URI = "git://github.com/mer-hybris/ofono-binder-plugin.git;branch=master;protocol=https"
S = "${WORKDIR}/git"

PV = "1.1.12"
SRCREV = "0bd4932f0c30187cd90ac91fe40c9e7131ed6110"

CFLAGS += "--sysroot=${RECIPE_SYSROOT} "
LDFLAGS += "--sysroot=${RECIPE_SYSROOT} "

EXTRA_OEMAKE = "KEEP_SYMBOLS=1 CROSS_COMPILE=${TARGET_PREFIX} CC='${CC}'"
PARALLEL_MAKE = ""

do_install() {
    make install DESTDIR=${D}
}

FILES:${PN} += "/usr/lib/ofono/plugins/binderplugin.so"