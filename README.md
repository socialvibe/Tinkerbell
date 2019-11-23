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

## Running via Terminal
1. [Running the project](#running-the-project)
1. You might need to copy the `local.properties` from Huckleberry into the `android` folder


# Reference

### Updating the JavaScript

`react-native bundle --platform android --dev false --entry-file index.js --bundle-output android/app/src/main/assets/index.android.bundle --assets-dest android/app/src/main/res`


### Running the project ##

`sudo npm install`
`npm run android`
