package dev.doubledot.doki

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.api.models.DokiResponse
import dev.doubledot.doki.api.tasks.DokiTask
import dev.doubledot.doki.api.tasks.DokiTaskCallback
import dev.doubledot.doki.extensions.bind
import dev.doubledot.doki.views.DokiContentView

internal class DokiActivity : AppCompatActivity() {

    private val dokiContent: DokiContentView? by bind(R.id.doki_content)
    private val task: DokiTask by lazy { DokiTask() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doki)

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
}
