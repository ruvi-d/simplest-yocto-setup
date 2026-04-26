# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repo is

`simplest-yocto-setup` is a reference Yocto/OpenEmbedded build environment managed with [kas](https://kas.readthedocs.io/). It demonstrates a clean, minimal layer structure for a fictitious company ("KISS") building for three embedded boards.

The repo owns only `meta-kiss/` and the kas config (`.config.yaml`). Everything else (`bitbake/`, `openembedded-core/`, `meta-arm/`) is fetched by kas and is git-ignored. Inside the devcontainer, `kas checkout` has already been run.

## Build commands

Each machine has a dedicated pre-configured build directory. Source `oe-init-build-env` with the correct build folder, then run `bitbake`.

| Machine | Real board | Build folder |
|---|---|---|
| `dogbonedark` | BeagleBone Black | `build/` |
| `stompduck` | STM32MP157A-DK1 | `build-st/` |
| QEMU ARM | — | `build-qemuarm/` |

```bash
# Initialize environment for a machine (run from repo root)
source openembedded-core/oe-init-build-env build        # dogbonedark
source openembedded-core/oe-init-build-env build-st     # stompduck
source openembedded-core/oe-init-build-env build-qemuarm

# Build the image
bitbake kiss-image

# Build a specific recipe
bitbake <recipe-name>

# Run in QEMU
bitbake kiss-image && runqemu qemuarm nographic
```

## Architecture and layer structure

`meta-kiss/` is the only custom layer. Its structure follows standard OE conventions:

- **`conf/distro/kiss.conf`** — distro policy (features, name, version). All KISS devices share these distro features.
- **`conf/machine/`** — one `.conf` per board, each `require`-ing `include/common.inc` plus tune/SoC includes from OE-Core. Machine configs wire up `virtual/kernel` and `virtual/bootloader` to the kiss-specific recipes.
- **`recipes-kernel/linux/linux-kiss_*.bb`** — kernel recipe(s). Board-specific `defconfig` files and patches live in subdirectories named after the machine.
- **`recipes-bsp/u-boot/u-boot-kiss_git.bb`** — U-Boot recipe. Board-specific configs/patches in subdirectories.
- **`recipes-bsp/trusted-firmware-a/`** — `.bbappend` to configure TF-A (used by `stompduck` and `freiheit93`).
- **`recipes-bsp/firmware-imx/`** — NXP proprietary firmware for `freiheit93`; EULA files live alongside the recipe.
- **`recipes-core/images/kiss-image.bb`** — the only image recipe, installs `packagegroup-core-boot`, `dropbear`, and `sl`.
- **`recipes-security/optee/`** — OP-TEE bbappend for `freiheit93`.
- **`wic/`** — one `.wks.in` per machine defining the SD card partition layout.

## Key design principles (from the project's intent)

- Single layer (`meta-kiss`) for all machines — no unnecessary layer splits.
- Avoid vendor BSP layers when mainline kernel/U-Boot support is sufficient; copy only the minimal necessary snippets.
- kas manages all layer fetching; no manual cloning.
