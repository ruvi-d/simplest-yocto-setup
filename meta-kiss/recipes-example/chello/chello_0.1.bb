SUMMARY = "Trivial example C app that prints a greeting via libhello"
DESCRIPTION = "A minimal C application that calls into libhello to print a \
               message. Used as a companion to the libhello recipe to \
               demonstrate building an application against a custom library \
               from meta-kiss."
HOMEPAGE = "https://github.com/ruvi-d/simplest-yocto-setup"
LICENSE = "MIT"
# nooelint: oelint.var.licenseremotefile
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

DEPENDS = "libhello"

SRC_URI = "file://Makefile \
           file://chello.c \
           "

S = "${WORKDIR}"

EXTRA_OEMAKE = "CC='${CC}' \
                CFLAGS='${CFLAGS}' \
                LDFLAGS='${LDFLAGS}'"

do_install() {
    oe_runmake install DESTDIR=${D} prefix=${prefix} bindir=${bindir}
}
