package dev.doubledot.doki.api.tasks

import dev.doubledot.doki.api.extensions.DONT_KILL_MY_APP_DEFAULT_MANUFACTURER
import dev.doubledot.doki.api.extensions.DONT_KILL_MY_APP_FALLBACK_MANUFACTURER
import dev.doubledot.doki.api.models.DokiManufacturer
import dev.doubledot.doki.api.remote.DokiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

@Suppress("MemberVisibilityCanBePrivate")
open class DokiApi {

    val dokiApiService by lazy {
        DokiApiService.create()
    }

    var disposable: Disposable? = null
    var callback: DokiApiCallback? = null
    var shouldFallback : Boolean = true

    fun getManufacturer(manufacturer : String = DONT_KILL_MY_APP_DEFAULT_MANUFACTURER) {
        disposable = dokiApiService.getManufacturer(manufacturer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result : DokiManufacturer -> callback?.onSuccess(result) },
                { error ->
                    if ((error as? HttpException)?.code() == 404 && shouldFallback) {
                        getManufacturer(DONT_KILL_MY_APP_FALLBACK_MANUFACTURER)
                        shouldFallback = false
                    } else callback?.onError(error)
                }
            )

        callback?.onStart()
    }

    fun cancel() {
        disposable?.dispose()
        disposable = null
    }
}
