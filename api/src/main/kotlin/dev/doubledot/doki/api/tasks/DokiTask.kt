package dev.doubledot.doki.api.tasks

import dev.doubledot.doki.api.extensions.*
import dev.doubledot.doki.api.models.DokiResponse
import dev.doubledot.doki.api.remote.DokiRequest
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.util.concurrent.Future

@Suppress("MemberVisibilityCanBePrivate")
open class DokiTask {
    private var task: Future<*>? = null

    var allowInterruptionOnCancel: Boolean = true

    var connectTimeout: Int = REQUESTS_CONNECT_TIMEOUT
    var readTimeout: Int = REQUESTS_READ_TIMEOUT
    var callback: DokiTaskCallback? = null

    fun execute() {
        if (task != null) cancel()
        callback?.onStart()

        task = doAsync {
            try {
                val jsonResponse = DokiRequest.requestJson(DONT_KILL_MY_APP_ENDPOINT, connectTimeout, readTimeout)
                try {
                    val json = JSONObject(jsonResponse)

                    val name = json.optString(DKMA_NAME_JSON_KEY, "").orEmpty()
                    val manufacturers = json.optJSONArray(DKMA_MANUFACTURER_JSON_KEY).join(",")
                    val url = "$DONT_KILL_MY_APP_BASE_URL${json.optString(DKMA_URL_JSON_KEY, "").orEmpty()}"
                    val award = json.optInt(DKMA_AWARD_JSON_KEY)
                    val position = json.optInt(DKMA_POSITION_JSON_KEY)
                    val explanation = json.optString(DKMA_EXPLANATION_JSON_KEY, "").orEmpty()
                    val userSolution = json.optString(DKMA_USER_SOLUTION_JSON_KEY, "").orEmpty()
                    val devSolution = json.optString(DKMA_DEV_SOLUTION_JSON_KEY, "").orEmpty()
                    val actualDevSolution: String? = if (devSolution.hasContent()) {
                        if (devSolution.contains(DKMA_NO_DEV_SOLUTION_MSG, true)) null else devSolution
                    } else null

                    var response: DokiResponse? = null
                    if (name.hasContent() && manufacturers.hasContent() &&
                        explanation.hasContent() && userSolution.hasContent()
                    ) {
                        response = DokiResponse(
                            name,
                            manufacturers,
                            url,
                            award,
                            position,
                            explanation,
                            userSolution,
                            actualDevSolution
                        )
                    }
                    callback?.onSuccess(response)
                } catch (e: Exception) {
                    callback?.onError(e)
                }
            } catch (e: Exception) {
                callback?.onError(e)
            }
        }
    }

    fun cancel() {
        task?.cancel(allowInterruptionOnCancel)
        task = null
    }
}
