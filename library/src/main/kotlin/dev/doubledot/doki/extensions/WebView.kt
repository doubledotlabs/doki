package dev.doubledot.doki.extensions

import android.webkit.WebView

fun WebView.loadHTML(htmlString: String?) = loadData(htmlString.orEmpty(), "text/html", null)

