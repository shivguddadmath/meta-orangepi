HOMEPAGE = "https://github.com/orangepi-xunlong/OrangePiRK3399_uboot"
SECTION = "bootloaders"
DEPENDS += "flex-native bison-native"

LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://Licenses/README;md5=30503fd321432fc713238f582193b78e"
PE = "1"

SRC_URI = " \
	git://github.com/orangepi-xunlong/OrangePiRK3399_uboot.git;branch=master; \
"
SRCREV = "${AUTOREV}"

S = "${WORKDIR}/git"
