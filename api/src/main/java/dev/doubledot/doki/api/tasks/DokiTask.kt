package dev.doubledot.doki.api.tasks

import android.os.Build
import dev.doubledot.doki.api.extensions.DONT_KILL_MY_APP_FALLBACK_MANUFACTURER
import dev.doubledot.doki.api.models.DokiResponse
import dev.doubledot.doki.api.remote.DokiApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

@Suppress("MemberVisibilityCanBePrivate")
open class DokiTask {

    val dokiApiService by lazy {
        DokiApiService.create()
    }

    var disposable: Disposable? = null
    var callback: DokiTaskCallback? = null
    var shouldFallback : Boolean = true

    fun execute(manufacturer : String = Build.MANUFACTURER.toLowerCase().replace(" ", "-")) {
        disposable = dokiApiService.getManufacturer(manufacturer)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result : DokiResponse -> callback?.onSuccess(result) },
                { error ->
                    if ((error as? HttpException)?.code() == 404 && shouldFallback) {
                        execute(DONT_KILL_MY_APP_FALLBACK_MANUFACTURER)
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
