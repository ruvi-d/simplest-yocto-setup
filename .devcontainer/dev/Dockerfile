FROM crops/yocto:ubuntu-22.04-base

# Switch to root to install packages
USER root

# Install pipx required for kas install
#  also instlled zsh to aid local developement
RUN apt-get update && \
    DEBIAN_FRONTEND=noninteractive apt-get install -y \
    pipx \
    zsh && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

# Switch back to yoctouser
USER yoctouser

# Install kas and oelint(Bitbake linter)
RUN pipx ensurepath && \
    pipx install kas && \
    pipx install oelint-adv
# Add pipx install dirto PATH
ENV PATH=/home/yoctouser/.local/bin:$PATH
