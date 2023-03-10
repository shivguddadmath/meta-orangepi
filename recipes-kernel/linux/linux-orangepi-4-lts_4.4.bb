DESCRIPTION = "Linux kernel for OrangePi 4 LTS"

inherit kernel
inherit python3native
require recipes-kernel/linux/linux-yocto.inc

DEPENDS += "openssl-native"

SRC_URI = " \
	git://github.com/orangepi-xunlong/OrangePiRK3399_kernel.git;branch=master; \
	file://brcmfmac.scc \
"

SRCREV = "${AUTOREV}"
LINUX_VERSION = "4.4.179"
KCONFIG_MODE = "alldefconfig"

# Override local version in order to use the one generated by linux build system
# And not "yocto-standard"
LINUX_VERSION_EXTENSION = ""
PR = "r1"
PV = "${LINUX_VERSION}"

# Include only supported boards for now
COMPATIBLE_MACHINE = "(rk3399)"

do_compile_append() {
	oe_runmake dtbs
}

# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
do_patch_append() {
	for s in `grep -rIl python ${S}/scripts`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done
}

do_deploy_append() {
	install -d ${DEPLOYDIR}/overlays
	install -m 644 ${WORKDIR}/linux-orangepi-4-lts_4*/arch/arm64/boot/dts/rockchip/* ${DEPLOYDIR}/overlays
	# install -m 644 ${S}/arch/arm64/boot/dts/rockchip/overlays-orangepi4/hw_intfc.conf ${DEPLOYDIR}/
}
