package dev.doubledot.doki.api.remote

import dev.doubledot.doki.api.extensions.DONT_KILL_MY_APP_BASE_ENDPOINT
import dev.doubledot.doki.api.models.DokiResponse
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface DokiApiService {

    @GET("{manufacturer}.json")
    fun getManufacturer(@Path("manufacturer") manufacturer : String) : Observable<DokiResponse>

    companion object {
        fun create() : DokiApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DONT_KILL_MY_APP_BASE_ENDPOINT)
                .build()

            return retrofit.create(DokiApiService::class.java)
        }
    }
}
