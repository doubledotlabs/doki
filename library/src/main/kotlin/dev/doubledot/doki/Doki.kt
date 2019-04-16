package dev.doubledot.doki

import android.content.Context
import android.content.Intent

object Doki {
    fun launch(context: Context) {
        context.startActivity(Intent(context, DokiActivity::class.java))
    }
}
