package dev.doubledot.doki.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.views.DokiContentView

class DokiCustomizedActivity : AppCompatActivity() {

    private val dokiContent: DokiContentView? by lazy {
        findViewById<DokiContentView?>(R.id.doki_content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doki_custom)

        dokiContent?.apply {
            setOnCloseListener { supportFinishAfterTransition() }
            setExplanationVisibility(false)
            setDeveloperSolutionVisibility(false)
            loadContent(appName = "Doki App")
        }
    }

}
