package dev.doubledot.doki

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import dev.doubledot.doki.api.extensions.fullAndroidVersion
import dev.doubledot.doki.api.models.DokiResponse
import dev.doubledot.doki.api.tasks.DokiTask
import dev.doubledot.doki.api.tasks.DokiTaskCallback
import dev.doubledot.doki.extensions.bind
import dev.doubledot.doki.extensions.ignore
import dev.doubledot.doki.extensions.loadHTML
import kotlin.math.roundToInt

internal class DokiActivity : AppCompatActivity() {

    private val manufacturerText: AppCompatTextView? by bind(R.id.doki_manufacturer)
    private val deviceText: AppCompatTextView? by bind(R.id.doki_device)
    private val versionText: AppCompatTextView? by bind(R.id.doki_android_version)
    private val contentWebView: WebView? by bind(R.id.doki_main_content)
    private val closeBtn: LinearLayout? by bind(R.id.doki_close_btn)

    private val task: DokiTask by lazy { DokiTask() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doki)

        manufacturerText?.text = Build.MANUFACTURER
        deviceText?.text = Build.MODEL
        versionText?.text = fullAndroidVersion

        val typedValue = TypedValue()
        ignore { theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true) }
        val bgColor = try {
            ContextCompat.getColor(this, typedValue.resourceId)
        } catch (e: Exception) {
            0
        }

        contentWebView?.setBackgroundColor(bgColor)
        val contentWebViewMarginVertical = (resources.getDimension(R.dimen.twelve_dp) / 3F).roundToInt()
        val contentWebViewMarginHorizontal = (resources.getDimension(R.dimen.twenty_four_dp) / 3F).roundToInt()
        val linksColor = ContextCompat.getColor(this, R.color.accent_material_light)
        val explanationTitleText = getString(R.string.explanation)
        val solutionTitleText = getString(R.string.solution)

        task.callback = object : DokiTaskCallback {
            override fun onSuccess(response: DokiResponse?) {
                Log.d("Doki", response?.toString())
                runOnUiThread {
                    contentWebView?.loadHTML(
                        response?.getHTMLContent(
                            explanationTitle = explanationTitleText,
                            solutionTitle = solutionTitleText,
                            linksColor = linksColor,
                            marginTopPx = contentWebViewMarginVertical,
                            marginRightPx = contentWebViewMarginHorizontal
                        )
                    )
                }
            }
        }
        task.execute()

        closeBtn?.setOnClickListener { supportFinishAfterTransition() }
    }

    override fun onDestroy() {
        super.onDestroy()
        task.cancel()
    }
}
