# Copyright (c) 2020-2024 LG Electronics, Inc.

SUMMARY = "Bluetooth HFP(Hands Free Profile) support service"
AUTHOR = "Muralidhar N <muralidhar.n@lge.com>"
SECTION = "webos/services"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=2763f3ed850f8412903ea776e0526bea \
    file://oss-pkg-info.yaml;md5=63319cd2c369569050a7c0cc246fc8ba \
"

DEPENDS = "glib-2.0 glib-2.0-native luna-service2 pmloglib libpbnjson"

WEBOS_VERSION = "1.0.0-33_536d71dbd9969c56038da29ce451dca8a7767e09"
PR = "r8"

inherit webos_cmake
inherit webos_system_bus
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit pkgconfig

# Set WEBOS_HFP_ENABLED_ROLE to a space-separted list of
# HFP (Hands Free Profile) role to be supported by the
# webOS HFP support service at runtime.
# Possible candidate of HFP role is AG(Audio Gateway),
# HF (Hands Free).
WEBOS_HFP_ENABLED_ROLE = "HF"

EXTRA_OECMAKE += "-DWEBOS_HFP_ENABLED_ROLE:STRING='${WEBOS_HFP_ENABLED_ROLE}'"

SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

inherit webos_systemd
WEBOS_SYSTEMD_SERVICE = "webos-hfp-service.service"
