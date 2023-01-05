require orangepi-minimal-image.bb

SUMMARY = "Basic console image for OrangePi boards"

IMAGE_FEATURES += "package-management ssh-server-openssh hwcodecs"

CORE_IMAGE_BASE_INSTALL += " \
    packagegroup-orangepi-console \
"
