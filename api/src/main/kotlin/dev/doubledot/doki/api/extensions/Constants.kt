@file:Suppress("SpellCheckingInspection")

package dev.doubledot.doki.api.extensions

import android.os.Build

internal const val REQUESTS_CONNECT_TIMEOUT = 20000
internal const val REQUESTS_READ_TIMEOUT = 20000
internal const val DONT_KILL_MY_APP_BASE_URL = "https://dontkillmyapp.com"
private const val DONT_KILL_MY_APP_BASE_ENDPOINT = "$DONT_KILL_MY_APP_BASE_URL/api/v2/"

internal val DONT_KILL_MY_APP_ENDPOINT =
    "$DONT_KILL_MY_APP_BASE_ENDPOINT${Build.MANUFACTURER.toLowerCase().replace(" ", "-")}.json"

internal const val DKMA_NAME_JSON_KEY = "name"
internal const val DKMA_MANUFACTURER_JSON_KEY = "manufacturer"
internal const val DKMA_URL_JSON_KEY = "url"
internal const val DKMA_AWARD_JSON_KEY = "award"
internal const val DKMA_POSITION_JSON_KEY = "position"
internal const val DKMA_EXPLANATION_JSON_KEY = "explanation"
internal const val DKMA_USER_SOLUTION_JSON_KEY = "user_solution"
internal const val DKMA_DEV_SOLUTION_JSON_KEY = "developer_solution"

internal const val DKMA_NO_DEV_SOLUTION_MSG = "At the moment we don’t know of any solution on dev end"
