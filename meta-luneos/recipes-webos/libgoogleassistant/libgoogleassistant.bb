# Copyright (c) 2018-2023 LG Electronics, Inc.

SUMMARY = "Google assistant engine library"
AUTHOR  = "Kyungjik Min <dp.min@lge.com>"
SECTION = "webos/library"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = " \
    file://LICENSE;md5=3b83ef96387f14655fc854ddc3c6bd57 \
    file://oss-pkg-info.yaml;md5=9e4744182d366ff5258e3268c575afe2 \
"

# The same restriction as in
# meta-luneos/recipes-upstreamable/snowboy/snowboy_%.bbappend
# libgoogleassistant depends on snowboy
COMPATIBLE_MACHINE = "rpi|aarch64|x86-64|qemux86-64|^halium$|pinetab2|pinephonepro|pinephone"

DEPENDS = "snowboy glib-2.0 googleapis grpc json-c pmloglib pulseaudio"
RDEPENDS:${PN}:class-target = "snowboy-models"

VIRTUAL-RUNTIME_bash ?= "bash"
RDEPENDS:${PN}:append:class-target = " ${VIRTUAL-RUNTIME_bash}"
RDEPENDS:${PN}:remove:class-target = "${@oe.utils.conditional('WEBOS_PREFERRED_PROVIDER_FOR_BASH', 'busybox', 'bash', '', d)}"

WEBOS_VERSION = "1.0.1-10_b8610f05673d48b498e38cb774d6f1056c3b5522"
PR = "r8"

PV = "1.0.1-10+git"
SRCREV = "b8610f05673d48b498e38cb774d6f1056c3b5522"

inherit webos_cmake
inherit webos_public_repo
inherit pkgconfig

SRC_URI = "${WEBOSOSE_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "-DGOOGLEAPIS_PATH=${STAGING_INCDIR}/google"

INSANE_SKIP:${PN} = "textrel"

