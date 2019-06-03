@file:Suppress("SpellCheckingInspection")

package dev.doubledot.doki.api.extensions

import android.os.Build

internal const val DONT_KILL_MY_APP_BASE_URL = "https://dontkillmyapp.com"
internal const val DONT_KILL_MY_APP_BASE_ENDPOINT = "$DONT_KILL_MY_APP_BASE_URL/api/v2/"
internal const val DONT_KILL_MY_APP_FALLBACK_MANUFACTURER = "general"
val DONT_KILL_MY_APP_DEFAULT_MANUFACTURER = Build.MANUFACTURER.toLowerCase().replace(" ", "-")
