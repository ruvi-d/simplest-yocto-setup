# Build a simple, minimal root filesystem.
#
# This recipe is a simplified form of core-image-minimal.

SUMMARY ?= "A simple, minimal image"
DESCRIPTION ?= "Minimal KISS root filesystem with busybox, dropbear and a small set of utilities."
LICENSE ?= "MIT"

IMAGE_INSTALL = "packagegroup-core-boot dropbear sl kernel-module-khello chello"

IMAGE_LINGUAS = " "

inherit core-image
