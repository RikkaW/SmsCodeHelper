SMSCodeHelper
===============

![](./app/src/main/res/mipmap-xxxhdpi/ic_launcher.png)

Copy verification code from SMS automatically.

[![Download from  Google Play](http://steverichey.github.io/google-play-badge-svg/img/en_get.svg)](https://play.google.com/store/apps/details?id=rikka.smscodehelper)

## Build with Gradle

1. `git clone https://github.com/RikkaW/SmsCodeHelper`
2.  create a key store file and change configuration
3. `cd SmsCodeHelper`
4. `gradle clean build`
5. `find -name "rikka.smscodehelper*.apk"`

## Signing Configs
```
releaseSigning : [
        storeFile : System.getenv("KEYSTORE"),
        storePassword : System.getenv("KEYSTORE_PASSWORD"),
        alias : System.getenv("KEY_ALIAS"),
        aliasPassword: System.getenv("KEY_PASSWORD")
],
```

## Licenses

~~GPLv3+~~

## Change Log

### v0.0.1(1)

[Release-v1.0.6.apk](./apk/me.gitai.smscodehelper-release-c1-v0.0.1-t12251803.apk)

* Add Mokee Captchas Parser
* Modularization

### v1.0.6(10006)

[Release-v1.0.6.apk](./apk/rikka.smscodehelper-release-c10006-v1.0.6-t12131130.apk)

* Rewrite
* Add Preferences Activity
* Add Regex Parser type
* Notification
* Minify apk size(52.2kb)
* more...

by gitai/dphdjy

### v1.0.5(10005)

* 支持含有大写字母的验证码
* 减少了一点点体积
* 新的图标

## Images

- @萌萌的小雅酱
	+ mipmap-hdpi/ic_launcher.png
	+ mipmap-mdpi/ic_launcher.png
	+ mipmap-xhdpi/ic_launcher.png
	+ mipmap-xxhdpi/ic_launcher.png
	+ mipmap-xxxhdpi/ic_launcher.png

## Libraries

- [systembartint](https://github.com/jgilfelt/SystemBarTint)

- [HostsEditor-HostsHub](https://github.com/HostsHub/HostsEditor-for-Android)
