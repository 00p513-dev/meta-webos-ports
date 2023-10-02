# Copyright (c) 2014-2023 LG Electronics, Inc.

SUMMARY = "webOS Configuration Service"
AUTHOR  = "Sangwoo Kang <sangwoo82.kang@lge.com>"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10 \
                    file://oss-pkg-info.yaml;md5=2bdfe040dcf81b4038370ae96036c519 \
"

DEPENDS = "luna-service2 pmloglib glib-2.0 libpbnjson gtest"
RDEPENDS:${PN} += "configd-data"

inherit pkgconfig
inherit webos_cmake
inherit webos_system_bus
inherit webos_systemd
inherit webos_public_repo

WEBOS_VERSION = "1.2.0-18_0d07514460a6c35e3a7aa0d360468f626210eaae"

PV = "1.2.0-18+git"
SRCREV = "0d07514460a6c35e3a7aa0d360468f626210eaae"

SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE}"

S = "${WORKDIR}/git"

inherit webos_systemd
WEBOS_SYSTEMD_SERVICE = "configd.service"

PACKAGES =+ "${PN}-tests"
FILES:${PN}-tests = "${libexecdir}/tests/*"
FILES:${PN} += "${webos_sysbus_datadir}"
