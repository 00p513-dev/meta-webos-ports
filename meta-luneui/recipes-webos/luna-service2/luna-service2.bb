# Copyright (c) 2012-2018 LG Electronics, Inc.

SUMMARY = "webOS Luna System Bus library, daemon, and utilities"
AUTHOR = "Anatolii Sakhnik <anatolii.sakhnik@lge.com>"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "libpbnjson pmloglib glib-2.0 gtest"
VIRTUAL-RUNTIME_cpushareholder ?= "cpushareholder-stub"
VIRTUAL-RUNTIME_rdx-utils ?= "rdxd"
VIRTUAL-RUNTIME_bash ?= "bash"
RDEPENDS_${PN} = "luna-service2-security-conf ${VIRTUAL-RUNTIME_cpushareholder} ${VIRTUAL-RUNTIME_rdx-utils} ${VIRTUAL-RUNTIME_bash}"

PR = "r23"

WEBOS_DISTRO_PRERELEASE ??= ""
EXTRA_OECMAKE += "${@ '-DWEBOS_DISTRO_PRERELEASE:STRING="devel"' \
                  if d.getVar('WEBOS_DISTRO_PRERELEASE',True) != '' else ''}"

inherit webos_ports_repo
inherit webos_cmake
inherit webos_system_bus
inherit webos_core_os_dep
inherit webos_machine_impl_dep
inherit webos_lttng
inherit webos_test_provider

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE};branch=webosose"
S = "${WORKDIR}/git"

SRCREV = "ac9819984a96bad5f3d2d655d7661943f4d7fd64"

# This fix-up will be removed shortly. luna-service2 headers must be included
# using '#include <luna-service2/*.h>'
do_install_append() {
    # XXX Temporarily, create links from the old locations until all users of
    # luna-service2 convert to using pkg-config
    ln -svnf luna-service2/lunaservice.h ${D}${includedir}/lunaservice.h
    ln -svnf luna-service2/lunaservice-errors.h ${D}${includedir}/lunaservice-errors.h
    ln -svnf lib${BPN}.so ${D}${libdir}/liblunaservice.so
}

# Disable LTTng tracepoints explicitly.
# LTTng tracepoints in LS2 can cause out of memory, because LS2 is used by many components.
# To enable tracepoints back use WEBOS_LTTNG_ENABLED_pn-luna-service2 = "1"
WEBOS_LTTNG_ENABLED = "0"
EXTRA_OECMAKE += " ${@bb.utils.contains('WEBOS_LTTNG_ENABLED', '1', '-DWEBOS_LTTNG_ENABLED:BOOLEAN=True', '', d)}"

WEBOS_DISABLE_LS2_SECURITY ?= "0"
EXTRA_OECMAKE += '${@oe.utils.conditional("WEBOS_DISABLE_LS2_SECURITY", "1", "-DWEBOS_LS2_SECURE:BOOLEAN=False", "" ,d)}'

PACKAGES += "${PN}-perf"

FILES_${PN}-perf += "${webos_testsdir}/${BPN}-perf"
