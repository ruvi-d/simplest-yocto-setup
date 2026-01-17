#!/bin/bash
set -e

# Create build folders sequentially
KAS_BUILD_DIR=build KAS_MACHINE=dogbonedark kas checkout .config.yaml:.devcontainer.config.yml

KAS_BUILD_DIR=build-st KAS_MACHINE=stompduck kas checkout .config.yaml:.devcontainer.config.yml 

KAS_BUILD_DIR=build-qemuarm KAS_MACHINE=qemuarm kas checkout .config.yaml:.devcontainer.config.yml

KAS_BUILD_DIR=build-ti KAS_MACHINE=beaglebone kas checkout .config.ti-sdk.yaml:.devcontainer.config.yml