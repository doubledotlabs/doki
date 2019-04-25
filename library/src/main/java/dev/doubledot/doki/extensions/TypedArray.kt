package dev.doubledot.doki.extensions

import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes

internal fun TypedArray.getDrawableOrNull(styleable: Int) : Drawable? {
    return try {
        getDrawable(styleable)
    } catch (e: Exception) {
        null
    }
}

internal fun TypedArray.getColorOrNull(styleable : Int) : Int? {
    return if (hasValue(styleable))
        getColor(styleable, 0)
    else null
}
