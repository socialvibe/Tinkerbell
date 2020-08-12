## Table of Contents

* [Updating the JavaScript](#updating-the-javascript)
* [Running the project](#running-the-project)

# Running the project
One can either run this project from the Terminal, or Android Studio.

## Running via Android Studio
1. `npm install`
1. Open the android folder as prject with Android Studio
1. [Updating the JavaScript](#updating-the-javascript), which would be the version that the app tries to use when it can't reach the server
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

# Note

### ADB version
React native uses the `adb reverse` comment, and for that to work, your adb version must be `1.0.40` or above.
Check your adb version by:
`# adb --version`
`# 1.0.40`
You can download the up-to-date platform tools here: https://developer.android.com/studio/releases/platform-tools

### Connect without `adb reverse`
Some of the Android Rom (devices) seems to have problem with `adb reverse` (Fire TV for example). We can connect those devices by:
1. Launch the React Native debug menu by:
    1. Clicking the "menu/option key" (≡), or
    1. Long press on fast-forward button ▷▷
    1. `adb shell input keyevent 82`
1. Click Dev Settings
1. Under `Debugging`, click "Debug server host & port for device"
1. Enter your dev machine's ipaddress, follow by the port. Ex: `192.168.12.34:8081`
1. Click "OK" to save
1. Go back, launch the debug menu again, and click Reload

### ADB reverse tcp Failed
When ADB is connecting while tcp/ip, there seems to be a ADB bug that prevent the reverse tcp command to work. The erorr looks a bit like this:
> adb: error: more than one device/emulator
1. Connect via a wire/usb
1. People claim there are [work arounds](https://stackoverflow.com/questions/51592477/adb-s-192-168-1-65555-error-more-than-one-device-emulator#answer-60304950)
1. Or [Connect without adb reverse](#connect-without-adb-reverse)

### Permission Error
Some device might not have the Activity to handle Intent android.settings.action.MANAGE_OVERLAY_PERMISSION, and crashes. We can grant that permission via adb
`adb shell appops set com.tinkerbell SYSTEM_ALERT_WINDOW allow`