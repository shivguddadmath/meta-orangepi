SUMMARY = "Minimal image for OrangePi boards"

IMAGE_FEATURES += "splash"

LICENSE = "MIT"

inherit core-image features_check extrausers

# let's make sure we have a good image..
REQUIRED_DISTRO_FEATURES = "pam systemd"

CORE_IMAGE_BASE_INSTALL += " \
    kernel-modules \
"

EXTRA_USERS_PARAMS = "usermod -P orangepi root;"
EXTRA_USERS_PARAMS += "useradd -P orangepi orangepi;"