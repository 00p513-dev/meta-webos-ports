# Copyright (c) 2013-2023 LG Electronics, Inc.

SUMMARY = "webOS QML LS2 bridge"
AUTHOR = "Elvis Lee <kwangwoong.lee@lge.com>"
SECTION = "webos/libs"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=89aea4e17d99a7cacdbeed46a0096b10 \
    file://oss-pkg-info.yaml;md5=5d5cc0ac1cf0f514284f99a9777cfe5c \
"

DEPENDS = "qtdeclarative luna-service2 glib-2.0"
RDEPENDS:${PN} += "qml-webos-components"

WEBOS_VERSION = "1.0.0-132_8ba88a9b0dae1ce530d903f76ff9a6a1b8b533c1"
PR = "r18"

PV = "1.0.0-132+git"
SRCREV = "8ba88a9b0dae1ce530d903f76ff9a6a1b8b533c1"

inherit webos_qmake6
inherit pkgconfig
inherit webos_public_repo

SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"


OE_QMAKE_PATH_HEADERS = "${OE_QMAKE_PATH_QT_HEADERS}"

# Perform extra QML validation
# WEBOS_QMLLINT_EXTRA_VALIDATION = "1"

FILES:${PN} += "${OE_QMAKE_PATH_QML}/WebOSServices/*"

do_install:append() {
    # until /usr/lib/qt5/qml paths in .qml files are updated to respect OE_QMAKE_PATH_QML
    ln -snf . ${D}/${libdir}/qt5
}
FILES:${PN} += "${libdir}/qt5"
 
