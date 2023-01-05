SOC_FAMILY = "rk3399"

require conf/machine/include/arm/arch-armv8a.inc
require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "mali-gpu:mali-midgard:"

PREFERRED_PROVIDER_virtual/kernel = "linux-orangepi-4-lts"
PREFERRED_VERSION_linux-orangechip = "4.4"

SERIAL_CONSOLES = "1500000;ttyFIQ0"
KERNEL_IMAGETYPE = "Image"
KBUILD_DEFCONFIG = "rk3399_linux_defconfig"

PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-orangepi-4-lts"

MACHINE_ESSENTIAL_EXTRA_RRECOMMENDS += "\
   kernel-modules \
   resize-helper \
"

IMAGE_FSTYPES += "rockchip-orangepi-gpt-img"
IMAGE_CLASSES += "rockchip-orangepi-gpt-img"
