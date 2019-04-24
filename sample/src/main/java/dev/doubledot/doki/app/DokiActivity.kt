package dev.doubledot.doki.app

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.api.models.DokiManufacturer
import dev.doubledot.doki.api.tasks.DokiApi
import dev.doubledot.doki.api.tasks.DokiApiCallback
import dev.doubledot.doki.views.DokiContentView

open class DokiActivity : AppCompatActivity() {

    private val dokiContent: DokiContentView? by lazy {
        findViewById<DokiContentView?>(R.id.doki_content)
    }

    private val api: DokiApi by lazy { DokiApi() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dokiLayout)

        dokiContent?.setOnCloseListener { supportFinishAfterTransition() }

        api.callback = object : DokiApiCallback {
            override fun onSuccess(response: DokiManufacturer?) {
                dokiContent?.setContent(response)
            }
        }
        api.getManufacturer()
    }

    override fun onDestroy() {
        super.onDestroy()
        api.cancel()
    }

    @LayoutRes
    open val dokiLayout: Int = R.layout.activity_doki
}
