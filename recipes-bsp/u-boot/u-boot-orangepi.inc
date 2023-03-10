require u-boot.inc

HOMEPAGE = "https://github.com/orangepi-xunlong/OrangePiRK3399_uboot"
LICENSE = "GPLv2+"
COMPATIBLE_MACHINE = "(rk3399)"
SECTION = "bootloaders"
DEPENDS = "dtc-native bc-native swig-native bison-native coreutils-native"

S = "${WORKDIR}/git"

# u-boot will build native python module
inherit python3native

do_configure () {
    if [ -z "${UBOOT_CONFIG}" ]; then
        if [ -n "${UBOOT_MACHINE}" ]; then
            oe_runmake -C ${S} O=${B} ${UBOOT_MACHINE}
        else
            oe_runmake -C ${S} O=${B} oldconfig
        fi

        ${S}/scripts/kconfig/merge_config.sh -m .config ${@" ".join(find_cfgs(d))}
        cml1_do_configure
    fi
}

do_compile_prepend () {
	export STAGING_INCDIR=${STAGING_INCDIR_NATIVE};
	export STAGING_LIBDIR=${STAGING_LIBDIR_NATIVE};
}

do_compile_append () {
	# copy to default search path
	if [ ${SPL_BINARY} ]; then
		cp ${B}/spl/${SPL_BINARY} ${B}/
	fi
	if [ -f "${WORKDIR}/${MACHINE}/boot.cmd" ]; then
		cp ${WORKDIR}/${MACHINE}/boot.cmd ${WORKDIR}/boot.cmd
		${B}/tools/mkimage -C none -A arm -T script -d ${WORKDIR}/boot.cmd ${WORKDIR}/boot.scr
	fi
}

do_install_append() {
	if [ -f "${WORKDIR}/boot.scr" ]; then
		install -m 644 ${WORKDIR}/boot.scr ${D}/boot/
	fi
	if [ -f "${WORKDIR}/${MACHINE}/uEnv.txt" ]; then
		install -m 644 ${WORKDIR}/${MACHINE}/uEnv.txt ${D}/boot/
	fi
}

do_deploy_append() {
	cp -a ${B}/tools/mkimage ${DEPLOY_DIR_IMAGE}
	if [ -f "${WORKDIR}/${MACHINE}/uEnv.txt" ]; then
		install -D -m 644 ${WORKDIR}/${MACHINE}/uEnv.txt ${DEPLOYDIR}/
	fi
	if [ -f "${WORKDIR}/boot.scr" ]; then
		install -D -m 644 ${WORKDIR}/boot.scr ${DEPLOYDIR}/
	fi
	if [ -f "${WORKDIR}/boot.cmd" ]; then
		install -D -m 644 ${WORKDIR}/boot.cmd ${DEPLOYDIR}/
	fi
}

PACKAGE_BEFORE_PN = "${PN}-scripts"
FILES_${PN}-scripts = " \
	/boot/boot.scr \
	/boot/uEnv.txt \
"
