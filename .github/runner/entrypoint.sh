#!/usr/bin/env bash
set -euo pipefail

cd /home/yoctouser/runner

if [ ! -f .runner ]; then
    ./config.sh --unattended --replace \
        --url    "${RUNNER_URL}" \
        --token  "${RUNNER_TOKEN}" \
        --name   "${RUNNER_NAME:-yocto-runner-1}" \
        --labels "${RUNNER_LABELS:-self-hosted,Linux,X64,yocto-build}" \
        --work   _work
fi

exec ./run.sh
