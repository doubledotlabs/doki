package dev.doubledot.doki.api.tasks

import dev.doubledot.doki.api.models.DokiManufacturer

interface DokiApiCallback {
    fun onStart() {}
    fun onSuccess(response: DokiManufacturer?)

    fun onError(e: Throwable?) {
        e?.printStackTrace()
    }
}
