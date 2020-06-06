SUMMARY = "A console development image customized for the rpi"
HOMEPAGE = "http://www.jumpnowtek.com"

require images/basic-image.bb

DEPENDS += "bcm2835-bootfiles"

DEV_EXTRAS = " \
    libstdc++ \
    spiloop \
    pigpio \
"

RPI_STUFF = " \
    raspi2fb \
    userland \
"

IMAGE_INSTALL += " \
    avahi-daemon \
    ${DEV_EXTRAS} \
    ${RPI_STUFF} \
"

export IMAGE_BASENAME = "brs-image"
