package dev.doubledot.doki.api.models.content

import dev.doubledot.doki.api.models.action.DokiAction
import dev.doubledot.doki.api.models.action.DokiActionADB
import dev.doubledot.doki.api.models.action.DokiActionIntent
import org.json.JSONObject
import org.jsoup.nodes.Element

class DokiContentAction(
    element: Element
) : DokiContent(element) {

    val action: DokiAction = run {
        val obj = JSONObject(element.attr("data-action"))
        when (obj.getString("type")) {
            "intent" -> DokiActionIntent(obj)
            "adb_command" -> DokiActionADB(
                obj
            )
            else -> DokiAction(obj)
        }
    }

}