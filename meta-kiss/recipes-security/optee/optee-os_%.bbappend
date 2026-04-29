# The OP-TEE recipe lives in meta-arm; tell bitbake to also look for
# file:// SRC_URI entries (e.g. our out-of-source DTS) under this
# bbappend's directory. Only stompgoose ships local files today, so
# scope the FILESEXTRAPATHS prepend to that machine.
FILESEXTRAPATHS:prepend:stompgoose := "${THISDIR}/${BPN}:"

COMPATIBLE_MACHINE:stompduck = "^stompduck$"
COMPATIBLE_MACHINE:stompgoose = "^stompgoose$"

# stompgoose ships its OP-TEE device tree out-of-source from this
# layer. The file is dropped directly into core/arch/arm/dts/ at unpack
# time; the machine conf points OP-TEE at it via CFG_EMBED_DTB_SOURCE_FILE.
SRC_URI:append:stompgoose = " \
    file://stm32mp157a-stompgoose.dts;subdir=git/core/arch/arm/dts \
"
