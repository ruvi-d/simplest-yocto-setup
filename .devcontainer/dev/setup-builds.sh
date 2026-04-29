#!/bin/bash
set -e

# Create build folders sequentially
KAS_BUILD_DIR=build KAS_MACHINE=dogbonedark kas checkout .config.yaml:.config.devcontainer.yaml

KAS_BUILD_DIR=build-st KAS_MACHINE=stompduck kas checkout .config.yaml:.config.devcontainer.yaml

KAS_BUILD_DIR=build-st-custom KAS_MACHINE=stompgoose kas checkout .config.yaml:.config.devcontainer.yaml

KAS_BUILD_DIR=build-qemuarm KAS_MACHINE=qemuarm kas checkout .config.yaml:.config.devcontainer.yaml

KAS_BUILD_DIR=build-nxp KAS_MACHINE=freiheit93 kas checkout .config.yaml:.config.devcontainer.yaml:.config.nxp.eula.yaml
