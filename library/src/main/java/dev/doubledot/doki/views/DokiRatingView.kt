@file:Suppress("LeakingThis")

package dev.doubledot.doki.views

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import dev.doubledot.doki.R
import dev.doubledot.doki.extensions.bind
import dev.doubledot.doki.extensions.tint


@Suppress("MemberVisibilityCanBePrivate")
open class DokiRatingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val iconA: AppCompatImageView? by bind(R.id.rating_icon_a)
    private val iconB: AppCompatImageView? by bind(R.id.rating_icon_b)
    private val iconC: AppCompatImageView? by bind(R.id.rating_icon_c)
    private val iconD: AppCompatImageView? by bind(R.id.rating_icon_d)
    private val iconE: AppCompatImageView? by bind(R.id.rating_icon_e)

    var activeIconsDrawable: Drawable? = null
        set(value) {
            field = value
            updateIcons()
        }

    var inactiveIconsDrawable: Drawable? = null
        set(value) {
            field = value
            updateIcons()
        }

    @ColorInt
    var activeIconsColor: Int = Color.BLACK
        set(value) {
            field = value
            updateIcons()
        }

    @ColorInt
    var inactiveIconsColor: Int = Color.BLACK
        set(value) {
            field = value
            updateIcons()
        }

    var rating: Int = 0
        set(value) {
            field = value
            updateIcons()
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_rating, this, true)

        activeIconsDrawable = try {
            ContextCompat.getDrawable(context, R.drawable.ic_thumb)
        } catch (e: Exception) {
            null
        }
        inactiveIconsDrawable = try {
            ContextCompat.getDrawable(context, R.drawable.ic_thumb_outline)
        } catch (e: Exception) {
            null
        }
        updateIcons()
    }

    fun setActiveIconRes(@DrawableRes resId: Int) {
        activeIconsDrawable = try {
            ContextCompat.getDrawable(context, resId)
        } catch (e: Exception) {
            null
        }
    }

    fun setInactiveIconRes(@DrawableRes resId: Int) {
        inactiveIconsDrawable = try {
            ContextCompat.getDrawable(context, resId)
        } catch (e: Exception) {
            null
        }
    }

    fun setActiveIconsColorRes(@ColorRes resId: Int) {
        activeIconsColor = try {
            ContextCompat.getColor(context, resId)
        } catch (e: Exception) {
            Color.BLACK
        }
    }

    fun setInactiveIconsColorRes(@ColorRes resId: Int) {
        inactiveIconsColor = try {
            ContextCompat.getColor(context, resId)
        } catch (e: Exception) {
            Color.BLACK
        }
    }

    private fun updateIcons() {
        iconA?.setImageDrawable(
            (if (rating > 0) activeIconsDrawable else inactiveIconsDrawable)?.tint(
                if (rating > 0) activeIconsColor else inactiveIconsColor
            )
        )
        iconB?.setImageDrawable(
            (if (rating > 1) activeIconsDrawable else inactiveIconsDrawable)?.tint(
                if (rating > 1) activeIconsColor else inactiveIconsColor
            )
        )
        iconC?.setImageDrawable(
            (if (rating > 2) activeIconsDrawable else inactiveIconsDrawable)?.tint(
                if (rating > 2) activeIconsColor else inactiveIconsColor
            )
        )
        iconD?.setImageDrawable(
            (if (rating > 3) activeIconsDrawable else inactiveIconsDrawable)?.tint(
                if (rating > 3) activeIconsColor else inactiveIconsColor
            )
        )
        iconE?.setImageDrawable(
            (if (rating > 4) activeIconsDrawable else inactiveIconsDrawable)?.tint(
                if (rating > 4) activeIconsColor else inactiveIconsColor
            )
        )
    }

    fun setIconsStyle(style: Style) {
        setActiveIconRes(style.activeResId)
        setInactiveIconRes(style.inactiveResId)
    }

    enum class Style(@DrawableRes val activeResId: Int, @DrawableRes val inactiveResId: Int) {
        THUMB(R.drawable.ic_thumb, R.drawable.ic_thumb_outline),
        FACE(R.drawable.ic_angry, R.drawable.ic_angry_outline),
        POOP(R.drawable.ic_poop, R.drawable.ic_poop_outline),
        TRASH(R.drawable.ic_trash, R.drawable.ic_trash_outline);

        companion object {
            fun getFromId(id: Int): Style = when (id) {
                1 -> FACE
                2 -> POOP
                3 -> TRASH
                else -> THUMB
            }
        }
    }
}
