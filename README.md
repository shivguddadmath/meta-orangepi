# Meta-orangepi

# Table of contents

1. [Introduction](#introduction)
2. [Dependencies](#dependencies)
3. [List of Orange Pi Boards supported](#list_of_boards_supported)
4. [Using the meta-orangepi layer](#meta_orangepi_usage)
    1. [Step 1:Fetching the Source](#source_fetch)
    2. [Step 2:Setting up the Environment](#setup)
    3. [Step 3:Bblayers.conf Setup](#bblayers.conf_setup)
    4. [Step 4:Local.conf Setup](#local.conf_setup)
    5. [Step 5:Building the Image](#build_image)
5. [Serial Console](#serial_console)
6. [Login Details](#login)
7. [Networking](#networking)
    1. [Wifi Connectivity](#wifi)
    2. [Bluetooth Connectivity](#bluetooth)    
8. [Release Info](#release_info)
9. [Change Log](#change_log)
10. [Contributing](#contributing)
11. [Reporting Bugs](#bugs)
12. [Maintainers](#maintainers)


## Introduction <a name="introduction"></a>

The OpenEmbedded/Yocto Project BSP layer for the Orange Pi machines

## Dependencies <a name="dependencies"></a>

The meta-orangepi layer depends on:

	URI: git://git.yoctoproject.org/poky
	branch: dunfell
    
	URI: git://git.openembedded.org/meta-openembedded
	layers: meta-oe meta-networking meta-python meta-multimedia meta-gnome meta-xfce
	branch: dunfell

## List of Orange Pi Boards supported <a name="list_of_boards_supported"></a>

1) OrangePi 4 LTS

## Using the meta-radxa layer <a name="meta_orangepi_usage"></a>

### Step 1: Fetching the source <a name="source_fetch"></a>

Fetch the source using the commands given below:

<pre><code>~ $ mkdir yocto
~ $ cd yocto
~/yocto $ git clone --depth 1 git://git.yoctoproject.org/poky -b dunfell
~/yocto $ cd poky
~/yocto/poky $ git clone --depth 1 git://git.openembedded.org/meta-openembedded.git -b dunfell
~/yocto/poky $ git clone --depth 1 https://github.com/shivguddadmath/meta-orangepi.git -b dunfell
~/yocto.poky $ git clone --depth 1 https://github.com/YoeDistro/meta-python2.git -b dunfell
</code></pre>

### Step 2: Setting up the Environment <a name="setup"></a>

<pre><code>~/yocto/poky $ source oe-init-build-env
</code></pre>

#### Step 3: bblayers.conf Setup <a name="bblayers.conf_setup"></a>

* You can simply copy the bblayers.conf.sample present in meta-orangepi/conf folder to the build/conf folder and rename it to bblayers.conf

<pre><code>~/yocto/poky/build $ cp ../meta-orangepi/conf/bblayers.conf.sample conf/bblayers.conf
</code></pre>

<div align="center"><b>OR</b></div>

* Add the layers manually as given below to the bblayers.conf in the build/conf folder

<pre><code>  ${TOPDIR}/../meta \
  ${TOPDIR}/../meta-poky \
  ${TOPDIR}/../meta-yocto-bsp \
  ${TOPDIR}/../meta-openembedded/meta-oe \
  ${TOPDIR}/../meta-openembedded/meta-networking \
  ${TOPDIR}/../meta-openembedded/meta-python \
  ${TOPDIR}/../meta-openembedded/meta-multimedia \ 
  ${TOPDIR}/../meta-openembedded/meta-gnome \
  ${TOPDIR}/../meta-openembedded/meta-xfce \
  ${TOPDIR}/../meta-python2 \
  ${TOPDIR}/../meta-orangepi \
</code></pre>

### Step 4: local.conf Setup <a name="local.conf_setup"></a>

* You can simply copy the local.conf.sample present in meta-radxa/conf folder to the build/conf folder and rename it to local.conf and uncomment the machine for which you want to build an image

<pre><code>~/yocto/poky/build $ cp ../meta-radxa/conf/local.conf.sample conf/local.conf
~/yocto/poky/build $ nano conf/local.conf
</code></pre>

<div align="center"><b>OR</b></div>

* Add the following lines in the build/conf/local.conf

```
MACHINE ?= "xxxx"
DISTRO_FEATURES_append = " pam systemd x11"
VIRTUAL-RUNTIME_init_manager = "systemd"
PACKAGECONFIG_append_pn-systemd = " resolved networkd"
DISTRO_FEATURES_remove = "wayland"
```

**Replace xxxx with the machine you want to build the image for. All supported machines can be found in meta-orangepi/conf/machine.**

### Step 5: Building the Image <a name="build_image"></a>

* If you wish to build a minimal image use the command given below:
```
~/yocto/poky/build $ bitbake -k orangepi-minimal-image
```
<div align="center"><b>OR</b></div>

* If you wish to build a console image use the command given below:

```
~/yocto/poky/build $ bitbake -k orangepi-console-image
```
<div align="center"><b>OR</b></div>

* If you wish to build a desktop image use the command given below:

```
~/yocto/poky/build $ bitbake -k orangepi-desktop-image
```

**At the end of a successful build, you should have a gpt.img image in build/tmp/deploy/images/MACHINE/ folder. The gpt.img can be directly flashed on the sd-card**

## Serial Console <a name="serial_console"></a>

The Serial Console for OrangePi 4 LTS is enabled on UART-2

**Helpful Links:**


## Login Details <a name="login"></a>

```
Username: 
Password: 
```

## Networking <a name="networking"></a>

**Network Devices available:**

+ Wifi
+ Ethernet
+ Bluetooth

### Wifi Connectivity <a name="wifi"></a>

+ Using Commandline Based GUI(nmtui) [Available on console and desktop images]

nmtui is a curses based GUI. You can start it by running the following command:

```
nmtui
```

+ Using Commandline Utility(nmcli) [Available on console and desktop images]

nmcli is a command-line tool for controlling NetworkManager and reporting network status.

**List available devices**

```
nmcli dev
```

**Turn on Wifi**

```
nmcli r wifi on
```

**Scanning different devices**

```
nmcli dev wifi
```

**Connect to WiFi Hotspot**

```
nmcli dev wifi connect "SSID" password "PASSWORD"
```

***Note:You need to replace “SSID” and “Password” with your actual WiFi’s SSID and password.***

### Bluetooth Connectivity <a name="bluetooth"></a>

+ Bluetooth on OrangePi 4 LTS

**Activating bluetooth:**

```
hciconfig hci0 up
```

**Check Bluetooth device:**

```
 $ hciconfig
 hci0:   Type: Primary  Bus: UART
         BD Address: 43:45:C5:00:1F:AC  ACL MTU: 1021:8  SCO MTU: 64:1
         UP RUNNING 
         RX bytes:876 acl:0 sco:0 events:62 errors:0
         TX bytes:4755 acl:0 sco:0 commands:62 errors:0
```

## Release Info <a name="release_info"></a>

1. OrangePi 4 LTS

+ Kernel version: 
+ U-Boot version: 

## Change Log <a name="change_log"></a>

+ Added board support for OrangePi 4 LTS

## Contributing <a name="contributing"></a>

Please use github for pull requests: https://github.com/shivguddadmath/meta-orangepi/pulls

## Reporting bugs <a name="bugs"></a>

The github issue tracker (https://github.com/shivguddadmath/meta-orangepi/issues) is being used to keep track of bugs.

## Maintainers <a name="maintainers"></a>

* Shiv Guddadmath <shiv.guddadmath@gmail.com>
