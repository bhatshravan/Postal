# FirePOST

The postal web app and android app made for postal services in Smart India Hackathon 

## Getting Started & Prerequisites

The folder consists of 2 parts-

  Android part-
  One is the postal app,be sure to configure theapp level build.gradle files and adding the 2 libraries for gradle sync.

  The libraries are-
  ```
  compile 'com.android.support:appcompat-v7:25.1.0'
  compile 'com.journeyapps:zxing-android-embedded:3.4.0'
  ```

  The manifest should use the permissions-
  ```
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
  ```

  The web app uses php for the printing part so be sure to enable an xampp server or anything similar.
  The libraries used in web app are jquery,bootstrap and pdf417js by bkuzmic with bootstrap validator.

## New features
* Added offline generators but they don't post to a sql server.
* Added a folder for java sorting programs to have all data in 1 place.
* Added a bag tracking delivery under Web App/Del/index.html .    Any changes or beta versions to be changed and made ready by monday.


## Built With

* [PDF417-JS](https://github.com/bkuzmic/pdf417-js) - The library generation code/
* [JQuery](https://jquery.com/) - JQuery library.
* [Bootstrap](getbootstrap.com/) - Used to display GUI HTML rendering.
* [1000hz-Validator](1000hz.github.io/bootstrap-validator/) - Used for form validation in bootstrap.
* [Zebra Crossing](https://github.com/zxing/zxing) - Android barcode scanning from Zebra-Crossing project.
* [Zxng-android-embedded](https://github.com/journeyapps/zxing-android-embedded) - Android port of Zxing by jorney apps


## Acknowledgments

* Hat tip to anyone who's code was used
* Shravan the great
