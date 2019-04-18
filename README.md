# Don't Kill My App - Library

The aim of [dontkillmyapp.com](https://dontkillmyapp.com/problem) is to create a collection of guides and information to help users and developers troubleshoot issues on their devices related to background tasks being killed unnecessarily by the Android system. This library displays relevant information from this site to the user of an affected application, and attempts to walk users through the process of solving the problem themselves, hopefully reducing the amount of time that developers have to spend providing support as a result of these issues.

[![Freenode IRC channel.](https://img.shields.io/badge/irc.freenode.net-%23%23doubledotlabs-brightgreen.svg)](https://webchat.freenode.net/?channels=%23%23doubledotlabs&uio=MTY9dHJ1ZSY5PXRydWUmMTE9MjE1e1)
[![Twitter account.](https://img.shields.io/badge/twitter-%40doubledotlabs-blue.svg?color=43b4f9&logo=twitter)](https://twitter.com/doubledotlabs)


## Preview

| Default Activity | Activity w Custom Theme | Dialog |
|------------------|-------------------------|-----------------------------------------|
| ![1](https://github.com/DoubleDotLabs/doki/raw/master/art/1.png) | ![2](https://github.com/DoubleDotLabs/doki/raw/master/art/2.png) | ![3](https://github.com/DoubleDotLabs/doki/raw/master/art/3.png) |

| Customized Doki View | In an emulator |  |
|------------------|-------------------------|-----------------------------------------|
| ![4](https://github.com/DoubleDotLabs/doki/raw/master/art/4.png) | ![5](https://github.com/DoubleDotLabs/doki/raw/master/art/5.png) |  |


# How to use it
Doki is available via JitPack, so getting it as simple as adding it as a dependency, like this:

1. Add JitPack repository to your root `build.gradle` file
```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
2. Add the dependency in your project `build.gradle` file
```gradle
dependencies {
    compile('dev.doubledot:doki:{latest version}@aar') {
        transitive = true
    }
}
```
where `{latest version}` corresponds to published version in  [![JitPack](https://jitpack.io/v/DoubleDotLabs/doki.svg)](https://jitpack.io/#DoubleDotLabs/doki)

## Customization

If you are using the `DokiContentView`, you can customize it by settings custom attributes. Please check [this file](https://github.com/DoubleDotLabs/doki/blob/master/app/src/main/res/layout/layout_doki_view_custom.xml) which implements every single option available.
