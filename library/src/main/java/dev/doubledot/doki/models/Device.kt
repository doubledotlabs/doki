package dev.doubledot.doki.models

import android.os.Build
import dev.doubledot.doki.api.extensions.fullAndroidVersion

internal data class Device(
    var manufacturer : String = Build.MANUFACTURER,
    var model : String = Build.MODEL,
    var androidVersion : String = fullAndroidVersion
)