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

## Running via Terminal
1. [Running the project](#running-the-project)
1. You might need to copy the `local.properties` from Huckleberry into the `android` folder


# Reference

### Updating the JavaScript

`react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res`


### Running the project ##

`sudo npm install`
`npm run android`


### Permission Error
Some device might not have the Activity to handle Intent android.settings.action.MANAGE_OVERLAY_PERMISSION, and crashes. We can grant that permission via adb
`adb shell appops set com.tinkerbell SYSTEM_ALERT_WINDOW allow`


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
