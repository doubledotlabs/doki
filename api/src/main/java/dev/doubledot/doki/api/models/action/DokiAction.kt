package dev.doubledot.doki.api.models.action

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.lang.RuntimeException

fun JSONObject.getStringOrNull(key: String): String? {
    return try {
        getString(key)
    } catch (e: JSONException) {
        null
    }
}

open class DokiAction(obj: JSONObject) {

    val type: String = obj.getString("type")

    open fun launch(context: Context) {
        throw RuntimeException("No .launch() implementation for $type action type.")
    }

}