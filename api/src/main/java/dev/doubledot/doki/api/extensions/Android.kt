package dev.doubledot.doki.api.extensions

import android.os.Build
import android.os.Build.VERSION_CODES.*

val androidVersion: String
    get() {
        var version = Build.VERSION.RELEASE ?: ""
        if (!version.hasContent()) version = Build.VERSION.CODENAME ?: ""
        return version
    }

val androidVersionName: String
    get() {
        return when (Build.VERSION.SDK_INT) {
            JELLY_BEAN, JELLY_BEAN_MR1, JELLY_BEAN_MR2 -> "JellyBean"
            KITKAT, KITKAT_WATCH -> "KitKat"
            LOLLIPOP, LOLLIPOP_MR1 -> "Lollipop"
            M -> "Marshmallow"
            N, N_MR1 -> "Nougat"
            O, O_MR1 -> "Oreo"
            P -> "Pie"
            else -> ""
        }
    }

val fullAndroidVersion: String
    get() = "Android $androidVersion $androidVersionName"
