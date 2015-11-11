DEPENDS += "luna-service2 pmloglib"

# Don't use qtmultimedia which is set by default PACKAGECONFIG
# Disabled for now, seems it also doesn't work with gstreamer currently.
# PACKAGECONFIG = "gstreamer qtlocation qtsensors"
PACKAGECONFIG = "qtlocation qtsensors"

inherit webos_ports_fork_repo

SRCREV_qtwebengine = "a7aa3ccd707720a7ad53c99a35922be84ff6b378"
SRCREV_chromium = "55d8f2d6e3761e0dc6fb5efa13ac606322360f29"

SRC_URI = " \
    ${WEBOS_PORTS_GIT_REPO_COMPLETE};name=qtwebengine \
    ${WEBOS_PORTS_GIT_REPO}/qtwebengine-chromium${WEBOS_GIT_BRANCH};name=chromium;destsuffix=git/src/3rdparty \
"

do_fetch[vardeps] += "SRCREV_chromium"

