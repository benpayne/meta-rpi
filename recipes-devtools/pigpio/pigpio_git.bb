DESCRIPTION = "Library and tools for GPIO on the Pi"
HOMEPAGE = "https://github.com/joan2937/pigpio"
SECTION = "devel/libs"
LICENSE = "PublicDomain"
LIC_FILES_CHKSUM = "file://UNLICENCE;md5=61287f92700ec1bdf13bc86d8228cd13"

COMPATIBLE_MACHINE = "^rpi$"

SRCREV = "30e6b3557486d323809d3783a6886afbbf701d95"
SRC_URI = " \
	git://github.com/joan2937/pigpio.git;protocol=https;branch=master \
	file://makefile.diff \
	file://makefilev2.diff \
"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CC=${CC}' 'AR=${AR}' 'RANLIB=${RANLIB}' 'STRIP=${STRIP}'"

do_install () {
  oe_runmake install DESTDIR=${D} SBINDIR=${sbindir} MANDIR=${mandir} \
    INCLUDEDIR=${includedir} prefix=/usr
  rm -rf ${D}/usr/man
  rm -rf ${D}/opt
  rm -rf ${D}/usr/local
}

PARALLEL_MAKE = ""

BBCLASSEXTEND = "native"

#FILES_${PN} += "${bindir} ${libdir}/* ${includedir}"
#FILES_${PN}-dev += "${libdir}/* ${includedir}"
