# Doki 

**A Don't Kill My App Library**

The aim of [dontkillmyapp.com](https://dontkillmyapp.com/problem) is to create a collection of guides and information to help users and developers troubleshoot issues on their devices related to background tasks being killed unnecessarily by the Android system. This library displays relevant information from this site to the user of an affected application, and attempts to walk users through the process of solving the problem themselves, hopefully reducing the amount of time that developers have to spend providing support as a result of these issues.

[![Freenode IRC channel.](https://img.shields.io/badge/irc.freenode.net-%23%23doubledotlabs-brightgreen.svg)](https://webchat.freenode.net/?channels=%23%23doubledotlabs&uio=MTY9dHJ1ZSY5PXRydWUmMTE9MjE1e1)
[![Twitter account.](https://img.shields.io/badge/twitter-%40doubledotlabs-blue.svg?color=43b4f9&logo=twitter)](https://twitter.com/doubledotlabs)
![API](https://img.shields.io/badge/API-16%2B-34bf49.svg)
[![JitPack](https://jitpack.io/v/dev.doubledot/doki.svg)](https://jitpack.io/#dev.doubledot/doki)
[![Build Status](https://travis-ci.org/DoubleDotLabs/doki.svg?branch=master)](https://travis-ci.org/DoubleDotLabs/doki)

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

2. Alternatively, just use the api wrapper
```gradle
dependencies {
    compile('dev.doubledot.doki:api:{latest version}@aar') {
        transitive = true
    }
}
```

### DokiContentView

In the layout you want to include our custom view, just add it like this:
```xml
<dev.doubledot.doki.views.DokiContentView
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/doki_content"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

**Note: This view is not available in the `api` module**

### DokiTask

You must run this task in order to get the content from the [Don't Kill My App website](https://dontkillmyapp.com/)
```kotlin
private val task: DokiTask by lazy { DokiTask() }

...

task.callback = object : DokiTaskCallback {
    override fun onSuccess(response: DokiResponse?) {
        myDokiView?.setContent(response)
    }
}
task.execute()
```

The `DokiResponse` object will have the following values:
* `name`: The exact name of device manufacturer
* `manufacturer`: An array with multiple manufacturers (if available)
* `url`: The url to open the [Don't Kill My App website](https://dontkillmyapp.com/) for the specific manufacturer
* `award`: An integer from 1 to 5 inclusive which is also the rating or score provided by the site. [More info](https://dontkillmyapp.com/about_score)
* `position`: The position to show this manufacturer. Used in [Don't Kill My App website](https://dontkillmyapp.com/)
* `explanation`: The explanation as to why common issues might occur
* `user_solution`: The solution users can follow to prevent the issues explained
* `dev_solution`: The solution you (developers) can follow to improve the app experience

## Customization

If you are using the `DokiContentView`, you can customize it by setting custom attributes. Please check [this file](https://github.com/DoubleDotLabs/doki/blob/master/app/src/main/res/layout/layout_doki_view_custom.xml) which implements every single option available.

For custom fonts and text styles, you can override the following styles:
```xml
<style name="Doki.Custom.Headline" parent="Doki.Headline"/>
<style name="Doki.Custom.Overline" parent="Doki.Overline"/>
<style name="Doki.Custom.Button" parent="Doki.Button"/>
```

And add the following attributes as you wish and with the values you want:
```xml
<item name="fontFamily">sans-serif</item>
<item name="android:fontFamily">sans-serif</item>
<item name="android:textStyle">bold</item>
<item name="android:textAllCaps">false</item>
<item name="android:textSize">16sp</item>
<item name="android:letterSpacing">0.1</item>
```

**Note: don't change the parent styles, just add the attributes you want to modify**

## Previews

| Default Activity | Activity w Custom Theme | Dialog                                  | Customized Doki View |
|------------------|-------------------------|-----------------------------------------|----------------------|
| ![The default doki activity.](https://github.com/DoubleDotLabs/doki/raw/master/art/1.png) | ![Doki in a custom-themed activity.](https://github.com/DoubleDotLabs/doki/raw/master/art/2.png) | ![Doki inside of a dialog.](https://github.com/DoubleDotLabs/doki/raw/master/art/3.png) | ![Doki with nice, fun, shiny colours.](https://github.com/DoubleDotLabs/doki/raw/master/art/4.png) |

# Contributors :sparkles:

* [Jahir Fiquitiva](https://jahir.xyz/) :man_technologist:
    * [GitHub](https://github.com/jahirfiquitiva/)
    * [Twitter](https://twitter.com/jahirfiquitiva)

* [James Fenn](https://jfenn.me/) :man_technologist:
    * [GitHub](https://github.com/fennifith/)
    * [Twitter](https://twitter.com/fennifith)

* [Eduardo Pratti](https://pratti.design) :art:
    * [Twitter](https://twitter.com/edpratti)


# License

```
MIT License

Copyright (c) 2019 Doki Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
