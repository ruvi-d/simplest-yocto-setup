FROM crops/yocto:ubuntu-22.04-base

# pipx for kas/oelint, plus curl/jq/tar/ca-certificates for the actions/runner
# install. Mirrors the install logic in .devcontainer/dev/Dockerfile.
USER root
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y --no-install-recommends \
        pipx \
        curl \
        jq \
        tar \
        ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

USER yoctouser
RUN pipx ensurepath && \
    pipx install kas && \
    pipx install oelint-adv
ENV PATH=/home/yoctouser/.local/bin:$PATH

ARG RUNNER_VERSION=2.319.1
RUN mkdir -p /home/yoctouser/runner && \
    cd /home/yoctouser/runner && \
    curl -fsSL -o runner.tgz \
        "https://github.com/actions/runner/releases/download/v${RUNNER_VERSION}/actions-runner-linux-x64-${RUNNER_VERSION}.tar.gz" && \
    tar xzf runner.tgz && \
    rm runner.tgz

COPY --chown=yoctouser:yoctouser entrypoint.sh /home/yoctouser/entrypoint.sh
RUN chmod +x /home/yoctouser/entrypoint.sh

WORKDIR /home/yoctouser/runner
ENTRYPOINT ["/home/yoctouser/entrypoint.sh"]
