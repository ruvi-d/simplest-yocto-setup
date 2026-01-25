SUMMARY = "Yet another hello world kernel module"
DESCRIPTION = "A simple example about how to write a recipe for a kernel space C module."
MAINTAINER = "Ruvinda Dhambarage <contact@ruvi.tech>"
SECTION = "kernel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://helloworld.c file://Makefile file://helloworld-params.c"

S = "${WORKDIR}"

inherit module
