package dev.doubledot.doki.api.tasks

import dev.doubledot.doki.api.models.DokiResponse

interface DokiTaskCallback {
    fun onStart() {}
    fun onSuccess(response: DokiResponse?)
    fun onError(e: Throwable?) = e?.printStackTrace()
}
