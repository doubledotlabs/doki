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
import dev.doubledot.doki.api.models.DokiResponse
import dev.doubledot.doki.api.tasks.DokiTask
import dev.doubledot.doki.api.tasks.DokiTaskCallback
import dev.doubledot.doki.views.DokiContentView

class DokiDialog : DialogFragment() {

    private val task: DokiTask by lazy { DokiTask() }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        task.callback = object : DokiTaskCallback {
            override fun onSuccess(response: DokiResponse?) {
                updateContent(response)
            }
        }

        val dokiCustomView = View.inflate(context, R.layout.activity_doki, null)
        dokiCustomView?.findViewById<DokiContentView?>(R.id.doki_content)?.setButtonsVisibility(false)

        return MaterialDialog(context!!).show {
            customView(view = dokiCustomView)
            onShow { task.execute() }
            positiveButton(R.string.close) {
                dismiss()
            }
        }
    }

    private fun updateContent(response: DokiResponse?) {
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
        task.cancel()
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
