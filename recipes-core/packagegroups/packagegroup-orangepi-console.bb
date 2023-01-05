SUMMARY = "Organize packages to avoid duplication across all images"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

# contains basic dependencies, that can work without graphics/display
RDEPENDS_packagegroup-orangepi-console = "\
    alsa-utils-aplay \
    coreutils \
    cpufrequtils \
    gnupg \
    hostapd \
    htop \
    iptables \
    iproute2 \
    kernel-modules \
    networkmanager \
    networkmanager-nmtui \
    openssh-sftp-server \
    bluez5 \
    dialog \
    i2c-tools \
    sudo \
    net-tools \
    findutils \
    "
