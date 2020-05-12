package dev.doubledot.doki.api.models.action

import android.content.Context
import dev.doubledot.doki.api.models.action.DokiAction
import org.json.JSONObject

class DokiActionADB(obj: JSONObject): DokiAction(obj) {

    val command: String = obj.getString("command")

    override fun launch(context: Context) {
        TODO("not implemented")
    }
}