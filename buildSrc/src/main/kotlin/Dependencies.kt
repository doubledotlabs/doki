// Library version
object Doki {
    const val versionCode = 1
    const val versionName = "0.0.1"

    const val groupId = "dev.doubledot.doki"
    const val appId = "$groupId.app"

    const val compileSdkVersion = 28
    const val minSdkVersion = 16
}

// Core dependencies
object Kotlin {
    const val version = "1.3.30"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Kotlin.version}"
    const val plugin = "kotlin"
    const val androidPlugin = "kotlin-android"
}

object Android {
    const val appPlugin = "com.android.application"
    const val libPlugin = "com.android.library"
}

// Gradle
object ClassPath {
    private const val version = "3.3.2"
    const val plugin = "com.android.tools.build:gradle:$version"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
    const val libs = "com.github.dcendents:android-maven-gradle-plugin:2.1"
}

object Libraries {
    const val appCompat = "androidx.appcompat:appcompat:1.0.2"
    const val mdDialogs = "com.afollestad.material-dialogs:core:2.8.0"
    const val material = "com.google.android.material:material:1.0.0"
}
