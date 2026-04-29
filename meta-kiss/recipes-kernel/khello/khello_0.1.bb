SUMMARY = "KISS hello world out-of-tree kernel module"
DESCRIPTION = "A trivial Linux kernel module that prints a greeting on \
               load and a farewell on unload, used to demonstrate building and shipping \
               an out-of-tree module from meta-kiss."
HOMEPAGE = "https://github.com/ruvi-d/simplest-yocto-setup"
LICENSE = "GPL-2.0-only"
# nooelint: oelint.var.licenseremotefile
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRC_URI = "file://Makefile \
           file://khello.c \
           "

S = "${WORKDIR}"

RPROVIDES:${PN} += "kernel-module-khello"
