# Build kiss-image with the OE runtime test framework enabled.
#
# This is a thin wrapper around kiss-image.bb. It pulls in debug-tweaks
# (so the test harness can SSH in as root with an empty password) and
# inherits the testimage class, which boots the image under QEMU and
# runs the listed runtime test cases.
#
# Run the tests with:
#   bitbake kiss-image-testing -c testimage
# Or set TESTIMAGE_AUTO = "1" to run them as part of the image build.

require kiss-image.bb

SUMMARY = "kiss-image with runtime sanity tests enabled"

IMAGE_FEATURES += "debug-tweaks"

IMAGE_CLASSES += "testimage"

# Use QEMU user-mode (slirp) networking. The default (TAP/TUN) requires
# /dev/net/tun, which isn't available inside the devcontainer.
TEST_RUNQEMUPARAMS = "slirp"

# Minimal set of runtime checks suitable for the kiss-image package set
# (busybox + dropbear). The test names map to modules under
# openembedded-core/meta/lib/oeqa/runtime/cases.
TEST_SUITES = "ping ssh date df parselogs"
