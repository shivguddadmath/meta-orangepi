DESCRIPTION = "Rockchip-OrangePi binary loader"

LICENSE = "BINARY"
LIC_FILES_CHKSUM = "file://LICENSE.TXT;md5=564e729dd65db6f65f911ce0cd340cf9"
NO_GENERIC_LICENSE[BINARY] = "LICENSE.TXT"

DEPENDS = "orangepi-binary-native"

SRC_URI = "git://github.com/orangepi-xunlong/OrangePiRK3399_external.git;branch=master;"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git/rkbin"


LOADER_rk3399 ?= "rk33/rk3399_loader_v1.08.106.bin"

MINILOADER_rk3399 ?= "rk33/rk3399_miniloader_v1.15.bin"

DDR_rk3399 ?= "rk33/rk3399_ddr_800MHz_v1.15.bin"

BL31_rk3399 ?= "rk33/rk3399_bl31_v1.18.elf"

inherit deploy

DDR_BIN = "ddr.bin"
LOADER_BIN = "loader.bin"
MINILOADER_BIN = "miniloader.bin"
ATF_BIN = "atf.bin"
UBOOT_IMG = "uboot.img"
UBOOT_ITB = "u-boot.itb"
BL31_ELF = "bl31.elf"

RKBINARY_DEPLOY_DIR = "${DEPLOYDIR}/orangepi-binary"

do_deploy () {
	install -d ${RKBINARY_DEPLOY_DIR}
	[ ${DDR} ] && cp ${S}/${DDR} ${RKBINARY_DEPLOY_DIR}/${DDR_BIN}
	[ ${MINILOADER} ] && cp ${S}/${MINILOADER} ${RKBINARY_DEPLOY_DIR}/${MINILOADER_BIN}	
	[ ${LOADER} ] && cp ${S}/${LOADER} ${RKBINARY_DEPLOY_DIR}/${LOADER_BIN}
	[ ${ATF} ] && cp ${S}/${ATF} ${RKBINARY_DEPLOY_DIR}/${ATF_BIN}
	[ ${BL31} ] && cp ${S}/${BL31} ${RKBINARY_DEPLOY_DIR}/${BL31_ELF}
}

addtask deploy before do_build after do_compile

do_package[noexec] = "1"
do_packagedata[noexec] = "1"
do_package_write[noexec] = "1"
do_package_write_ipk[noexec] = "1"
do_package_write_rpm[noexec] = "1"
do_package_write_deb[noexec] = "1"
do_package_write_tar[noexec] = "1"
