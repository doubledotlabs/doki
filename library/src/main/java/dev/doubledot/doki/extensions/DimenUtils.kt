package dev.doubledot.doki.extensions

import android.content.res.Resources

internal inline val Float.dpToPx: Float
    get() = this * Resources.getSystem().displayMetrics.density
