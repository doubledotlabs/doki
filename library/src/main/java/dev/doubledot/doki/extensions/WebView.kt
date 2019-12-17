package dev.doubledot.doki.extensions

import android.webkit.WebView

internal fun WebView.loadHTML(htmlString: String?) = loadData(htmlString.orEmpty(), "text/html", null)

