package dev.doubledot.doki.app

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.views.DokiContentView

open class DokiActivity : AppCompatActivity() {

    private val dokiContent: DokiContentView? by lazy {
        findViewById<DokiContentView?>(R.id.doki_content)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(dokiLayout)

        dokiContent?.setOnCloseListener { supportFinishAfterTransition() }
        dokiContent?.loadContent()
    }

    @LayoutRes
    open val dokiLayout: Int = R.layout.activity_doki
}
