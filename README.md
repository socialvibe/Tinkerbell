## Table of Contents

* [Updating the JavaScript](#updating-the-javascript)
* [Running the project](#running-the-project)

# Running the project
One can either run this project from the Terminal, or Android Studio.

## Running via Android Studio
1. `npm install`
1. Open the android folder as prject with Android Studio
1. [Updating the JavaScript](#updating-the-javascript)
1. Run and build as normal

* note: if your project is crashing, you might encountered the [Permission Error](#permission-error)

## Running via Terminal
1. [Running the project](#running-the-project)
1. You might need to create, or copy the `local.properties` file from another Android project into the `android` folder. Read more about the [properties-files](https://developer.android.com/studio/build#properties-files) here

* note: if you are not seeing your change, you might had run into the [ADB reverse tcp Failure](#adb-reverse-tcp-failed)

# Reference

### Updating the JavaScript

`react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res`


### Running the project ##

`sudo npm install`
`npm run android`


### Permission Error
Some device might not have the Activity to handle Intent android.settings.action.MANAGE_OVERLAY_PERMISSION, and crashes. We can grant that permission via adb
`adb shell appops set com.tinkerbell SYSTEM_ALERT_WINDOW allow`


### ADB reverse tcp Failed
When ADB is connecting while tcp/ip, there seems to be a ADB bug that prevent the reverse tcp command to work. The erorr looks a bit like this:
> adb: error: more than one device/emulator
1. Connect via a wire/usb
1. People claim there are [work arounds](https://stackoverflow.com/questions/51592477/adb-s-192-168-1-65555-error-more-than-one-device-emulator#answer-60304950)
1. Or, alway [Updating the JavaScript](#updating-the-javascript) before install
