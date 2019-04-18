package dev.doubledot.doki.extensions

import android.view.View

internal fun <T : View> T.visible(): T {
    visibility = View.VISIBLE
    return this
}

internal fun <T : View> T.invisible(): T {
    visibility = View.INVISIBLE
    return this
}

internal fun <T : View> T.gone(): T {
    visibility = View.GONE
    return this
}

internal fun <T : View> T.invisibleIf(invisible: Boolean): T =
    if (invisible) invisible() else visible()

internal fun <T : View> T.visibleIf(visible: Boolean): T = if (visible) visible() else gone()

internal fun <T : View> T.goneIf(gone: Boolean): T = visibleIf(!gone)

internal inline val View.isVisible: Boolean get() = visibility == View.VISIBLE

internal inline val View.isInvisible: Boolean get() = visibility == View.INVISIBLE

internal inline val View.isGone: Boolean get() = visibility == View.GONE
