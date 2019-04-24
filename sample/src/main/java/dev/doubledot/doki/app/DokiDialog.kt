package dev.doubledot.doki.app

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.callbacks.onShow
import com.afollestad.materialdialogs.customview.customView
import com.afollestad.materialdialogs.customview.getCustomView
import dev.doubledot.doki.api.models.DokiManufacturer
import dev.doubledot.doki.api.tasks.DokiApi
import dev.doubledot.doki.api.tasks.DokiApiCallback
import dev.doubledot.doki.views.DokiContentView

class DokiDialog : DialogFragment() {

    private val api: DokiApi by lazy { DokiApi() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        api.callback = object : DokiApiCallback {
            override fun onSuccess(response: DokiManufacturer?) {
                updateContent(response)
            }
        }

        val dokiCustomView = View.inflate(context, R.layout.activity_doki, null)
        dokiCustomView?.findViewById<DokiContentView?>(R.id.doki_content)?.setButtonsVisibility(false)

        return MaterialDialog(context!!).show {
            customView(view = dokiCustomView)
            onShow { api.getManufacturer() }
            positiveButton(R.string.close) {
                dismiss()
            }
        }
    }

    private fun updateContent(response: DokiManufacturer?) {
        val actualDialog = (dialog as? MaterialDialog)
        actualDialog?.getCustomView()
            ?.findViewById<DokiContentView?>(R.id.doki_content)
            ?.let {
                it.setContent(response)
                it.setButtonsVisibility(false)
                if (response?.dev_solution.orEmpty().trim().isNotEmpty()) {
                    actualDialog.negativeButton(R.string.report) {
                        // TODO: Handle report button press
                        dismiss()
                    }
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        api.cancel()
    }

    fun show(context: FragmentActivity, tag: String = DOKI_DIALOG_TAG) {
        show(context.supportFragmentManager, tag)
    }

    companion object {
        private const val DOKI_DIALOG_TAG = "doki_dialog"
        fun show(context: FragmentActivity, tag: String = DOKI_DIALOG_TAG) {
            DokiDialog().show(context, tag)
        }
    }
}
