# Simple recipe for using a mainline Linux kernel

LICENSE = "GPL-2.0-only"

inherit kernel

# As we are using a fully mainline kernel there is no need to clone an
# entire git repository, which would take time and disk space. Downloading
# a tarball is enough. In real cases you might have an internal kernel
# repository with your branches, so it can make sense to fetch from
# there. Or you could be using a mainline kernel with only a few patches on
# top, and in that case downloading a tarball and applying your patches can
# be a good solution.
SRC_URI = "${KERNELORG_MIRROR}/linux/kernel/v6.x/linux-${PV}.tar.xz"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"
S = "${WORKDIR}/linux-${PV}"

# For most realistic setups, a customized defconfig is preferred to the default
# architecture defconfig, to enable all the needed features and drivers while
# trimming the kernel and modules size disabling unneeded ones.
SRC_URI += "file://defconfig"

# dogbonedarker and stompgoose demonstrate out-of-source device trees:
# the .dts file lives in this layer and is dropped directly into the
# kernel source tree at unpack time via SRC_URI's subdir= parameter.
# The kbuild pattern rule for %.dtb compiles it from there, so no
# Makefile patching is needed.
SRC_URI:append:dogbonedarker = " file://am335x-dogbonedarker.dts;subdir=linux-${PV}/arch/arm/boot/dts/ti/omap"
SRC_URI:append:stompgoose = " file://stm32mp157a-stompgoose.dts;subdir=linux-${PV}/arch/arm/boot/dts/st"
