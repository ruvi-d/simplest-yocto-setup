# Build a simple, minimal root filesystem.
#
# This recipe is a simplified form of core-image-minimal.

SUMMARY = "A simple, minimal image"

IMAGE_INSTALL = "packagegroup-core-boot dropbear khello"

IMAGE_LINGUAS = ""

IMAGE_FEATURES += "debug-tweaks"

inherit core-image
