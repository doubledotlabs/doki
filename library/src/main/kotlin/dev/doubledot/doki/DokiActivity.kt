package dev.doubledot.doki

import android.os.Build
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import dev.doubledot.doki.extensions.bind
import dev.doubledot.doki.extensions.fullAndroidVersion

internal class DokiActivity : AppCompatActivity() {

    private val manufacturerText: AppCompatTextView? by bind(R.id.doki_manufacturer)
    private val deviceText: AppCompatTextView? by bind(R.id.doki_device)
    private val versionText: AppCompatTextView? by bind(R.id.doki_android_version)

    private val closeBtn: LinearLayout? by bind(R.id.doki_close_btn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doki)

        manufacturerText?.text = Build.MANUFACTURER
        deviceText?.text = Build.MODEL
        versionText?.text = fullAndroidVersion

        closeBtn?.setOnClickListener { supportFinishAfterTransition() }
    }
}
