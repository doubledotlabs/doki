package dev.doubledot.doki.ui

interface DokiContentCallback {
    fun onStartedToLoad() {}
    fun onLoaded() {}
    fun onFailedToLoad() {}
}