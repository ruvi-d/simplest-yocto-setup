SUMMARY = "Trivial example C library used by the chello example app"
DESCRIPTION = "A minimal C library exposing a single hello() function. \
               Used together with the chello recipe to demonstrate building \
               a custom library and an application that links against it. \
               PACKAGECONFIG selects whether libhello is built as a shared \
               library (default) or as a static archive."
HOMEPAGE = "https://github.com/ruvi-d/simplest-yocto-setup"
LICENSE = "MIT"
# nooelint: oelint.var.licenseremotefile
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://Makefile \
           file://hello.c \
           file://hello.h \
           "

S = "${WORKDIR}"

# Set PACKAGECONFIG to either 'shared' (default) or 'static' to pick how
# libhello is built. If both are set, 'static' wins; if neither is set,
# the fallback is 'shared'.
PACKAGECONFIG ??= "shared"
PACKAGECONFIG[shared] = ""
PACKAGECONFIG[static] = ""

LIBTYPE = "${@bb.utils.contains('PACKAGECONFIG', 'static', 'static', 'shared', d)}"

EXTRA_OEMAKE = "CC='${CC}' AR='${AR}' \
                CFLAGS='${CFLAGS}' \
                LDFLAGS='${LDFLAGS}' \
                LIBTYPE='${LIBTYPE}'"

do_install() {
    oe_runmake install DESTDIR=${D} prefix=${prefix} libdir=${libdir} includedir=${includedir}
}

# When built as a static archive, the runtime package is empty (the .a
# lands in ${PN}-staticdev). Allow that without a packaging warning.
ALLOW_EMPTY:${PN} = "1"
