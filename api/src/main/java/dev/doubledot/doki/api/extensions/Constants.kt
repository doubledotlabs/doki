@file:Suppress("SpellCheckingInspection")

package dev.doubledot.doki.api.extensions

import android.os.Build

internal const val REQUESTS_CONNECT_TIMEOUT = 20000
internal const val REQUESTS_READ_TIMEOUT = 20000
internal const val DONT_KILL_MY_APP_BASE_URL = "https://dontkillmyapp.com"
internal const val DONT_KILL_MY_APP_BASE_ENDPOINT = "$DONT_KILL_MY_APP_BASE_URL/api/v2/"
internal const val DONT_KILL_MY_APP_FALLBACK_MANUFACTURER = "general"

internal val DONT_KILL_MY_APP_ENDPOINT =
    "$DONT_KILL_MY_APP_BASE_ENDPOINT${Build.MANUFACTURER.toLowerCase().replace(" ", "-")}.json"

internal const val DKMA_NO_DEV_SOLUTION_MSG = "At the moment we don’t know of any solution on dev end"
