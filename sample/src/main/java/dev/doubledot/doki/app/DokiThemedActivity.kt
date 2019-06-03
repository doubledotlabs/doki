package dev.doubledot.doki.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.views.DokiContentView

class DokiThemedActivity : AppCompatActivity() {

    private val dokiContent: DokiContentView? by lazy {
        findViewById<DokiContentView?>(R.id.doki_content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doki)

        dokiContent?.setOnCloseListener { supportFinishAfterTransition() }
        dokiContent?.loadContent()
    }

}
