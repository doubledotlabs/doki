package dev.doubledot.doki.app

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.api.models.DokiResponse
import dev.doubledot.doki.api.tasks.DokiTask
import dev.doubledot.doki.api.tasks.DokiTaskCallback
import dev.doubledot.doki.views.DokiContentView

open class DokiActivity : AppCompatActivity() {

    private val dokiContent: DokiContentView? by lazy {
        findViewById<DokiContentView?>(R.id.doki_content)
    }

    private val task: DokiTask by lazy { DokiTask() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dokiLayout)

        dokiContent?.setOnCloseListener { supportFinishAfterTransition() }

        task.callback = object : DokiTaskCallback {
            override fun onSuccess(response: DokiResponse?) {
                dokiContent?.setContent(response)
            }
        }
        task.execute()
    }

    override fun onDestroy() {
        super.onDestroy()
        task.cancel()
    }

    @LayoutRes
    open val dokiLayout: Int = R.layout.activity_doki
}
