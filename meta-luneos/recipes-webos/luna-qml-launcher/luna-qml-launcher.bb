SUMMARY = "The qml application launcher for the Luna Next webOS UI platform."
LICENSE = "GPL-3.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ddd17b0c9241d7b24a4960caefe8e40"

DEPENDS = "qtbase qtdeclarative luna-sysmgr-common libwebos-application"
RDEPENDS_${PN} += " qtdeclarative-qmlplugins"

PV = "0.1.0-5+git${SRCPV}"
SRCREV = "e8b7962d1f72dbbace29b7f6c5c1537b1d9019e6"

inherit webos_ports_repo
inherit webos_system_bus
inherit webos_cmake_qt5

SRC_URI = "${WEBOS_PORTS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
