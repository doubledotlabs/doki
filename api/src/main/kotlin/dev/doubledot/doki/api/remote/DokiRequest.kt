package dev.doubledot.doki.api.remote

import android.os.Build
import android.util.Log
import dev.doubledot.doki.api.extensions.REQUESTS_CONNECT_TIMEOUT
import dev.doubledot.doki.api.extensions.REQUESTS_READ_TIMEOUT
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

internal object DokiRequest {
    fun requestJson(
        url: String,
        connectTimeout: Int = -1,
        readTimeout: Int = -1
    ): String {
        val result = StringBuilder()
        val urlConnection: HttpURLConnection? = buildHttpUrlConnection(url, connectTimeout, readTimeout)
        urlConnection ?: return result.toString()
        try {
            val ins = BufferedInputStream(urlConnection.inputStream)
            val reader = BufferedReader(InputStreamReader(ins))
            var line: String? = null
            while ({ line = reader.readLine(); line }() != null) {
                result.append(line)
            }
            ins.close()
            reader.close()
        } catch (e: Exception) {
            Log.e("Error", e.message)
        } finally {
            urlConnection.disconnect()
        }
        return result.toString()
    }

    private fun buildHttpUrlConnection(
        url: String,
        connectTimeout: Int = -1,
        readTimeout: Int = -1
    ): HttpURLConnection? {
        return (if (url.matches("^(https?)://.*$".toRegex())) {
            (URL(url).openConnection() as HttpsURLConnection).apply {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) sslSocketFactory = SocketFactory()
            }
        } else {
            URL(url).openConnection() as HttpURLConnection
        }).apply {
            this.connectTimeout = if (connectTimeout < 0) REQUESTS_CONNECT_TIMEOUT else connectTimeout
            this.readTimeout = if (readTimeout < 0) REQUESTS_READ_TIMEOUT else readTimeout
        }
    }
}
