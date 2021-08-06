package dev.doubledot.doki.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.doubledot.doki.api.extensions.DONT_KILL_MY_APP_DEFAULT_MANUFACTURER
import dev.doubledot.doki.api.tasks.DokiApi
import dev.doubledot.doki.views.DokiContentView

public class DokiActivity : AppCompatActivity() {

    var api : DokiApi? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dokiView = DokiContentView(context = this)
        setContentView(dokiView)

        api = dokiView.loadContent(appName = "Doki App", manufacturerId = intent.extras?.run {
            this[MANUFACTURER_EXTRA] as? String
        } ?: DONT_KILL_MY_APP_DEFAULT_MANUFACTURER)

        dokiView.setOnCloseListener { finish() }
    }

    override fun onDestroy() {
        super.onDestroy()
        api?.cancel()
    }

    companion object {

        public const val MANUFACTURER_EXTRA = "dev.doubledot.doki.ui.DokiActivity.MANUFACTURER_EXTRA"

        @JvmOverloads
        public fun newIntent(context: Context, manufacturerId: String = DONT_KILL_MY_APP_DEFAULT_MANUFACTURER): Intent {
            val intent = Intent(context, DokiActivity::class.java)
            intent.putExtra(MANUFACTURER_EXTRA, manufacturerId)
            return intent
        }

        @JvmOverloads
        public fun start(context : Context, manufacturerId : String = DONT_KILL_MY_APP_DEFAULT_MANUFACTURER) {
            context.startActivity(newIntent(context, manufacturerId))
        }
    }

}
