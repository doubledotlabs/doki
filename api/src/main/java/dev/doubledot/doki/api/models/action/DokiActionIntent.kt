package dev.doubledot.doki.api.models.action

import android.content.Context
import dev.doubledot.doki.api.models.action.DokiAction
import dev.doubledot.doki.api.models.action.getStringOrNull
import org.json.JSONObject

class DokiActionIntent(obj: JSONObject): DokiAction(obj) {

    val componentName: String? = obj.getStringOrNull("component_name")
    val action: String? = obj.getStringOrNull("action")

    override fun launch(context: Context) {
        TODO("not implemented")
    }
}