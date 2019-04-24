package dev.doubledot.doki.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import dev.doubledot.doki.R
import dev.doubledot.doki.api.extensions.fullAndroidVersion
import dev.doubledot.doki.api.extensions.hasContent
import dev.doubledot.doki.api.models.DokiManufacturer
import dev.doubledot.doki.extensions.*
import dev.doubledot.doki.databinding.ViewDokiContentBinding
import dev.doubledot.doki.models.Device
import dev.doubledot.doki.models.DokiResponse
import kotlin.math.roundToInt


@Suppress("MemberVisibilityCanBePrivate")
open class DokiContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    private val contentWebViewMarginVertical: Int by lazy {
        (resources.getDimension(R.dimen.twelve_dp) / 3F).roundToInt()
    }

    private val contentWebViewMarginHorizontal: Int by lazy {
        (resources.getDimension(R.dimen.twenty_four_dp) / 3F).roundToInt()
    }

    @ColorInt
    var primaryTextColor: Int = Color.BLACK
        set(value) {
            field = value
            initContent()
        }

    @ColorInt
    var secondaryTextColor: Int = Color.BLACK
        set(value) {
            field = value
            initContent()
        }

    @ColorInt
    var buttonsTextColor: Int = context.extractColor(intArrayOf(R.attr.colorAccent))
        set(value) {
            field = value
            initContent()
        }

    @ColorInt
    var dividerColor: Int = Color.BLACK
        set(value) {
            field = value
            initContent()
        }

    @ColorInt
    var headerBgColor: Int = 0
        set(value) {
            field = value
            //headerContainer?.setBackgroundColor(value)
        }

    var activeIconsDrawable: Drawable? = null
        set(value) {
            field = value
            //ratingView?.activeIconsDrawable = value
        }

    var inactiveIconsDrawable: Drawable? = null
        set(value) {
            field = value
            //ratingView?.inactiveIconsDrawable = value
        }

    @ColorInt
    var activeIconsColor: Int = Color.BLACK
        set(value) {
            field = value
            //ratingView?.activeIconsColor = value
        }

    @ColorInt
    var inactiveIconsColor: Int = Color.BLACK
        set(value) {
            field = value
            //ratingView?.inactiveIconsColor = value
        }

    private var devSolutionMessage: String = ""
    var explanationTitleText: String = ""
    var solutionTitleText: String = ""
    var webLineHeight: Float = 1.8F
    @FloatRange(from = 0.0, to = 1.0, fromInclusive = true, toInclusive = true)
    var maxImgWidth: Float = .75F
    @ColorInt
    var imgBorderColor: Int = Color.BLACK

    val binding : ViewDokiContentBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v = inflater.inflate(R.layout.view_doki_content, this, true)
        binding = ViewDokiContentBinding.bind(v).apply {
            device = Device()
            response = DokiResponse()
        }

        initFromAttrs(attrs)
        initContent()
    }

    @SuppressLint("Recycle")
    private fun initFromAttrs(attrs: AttributeSet?) {
        val styledAttrs: TypedArray? = try {
            context.obtainStyledAttributes(attrs, R.styleable.DokiContentView, 0, 0)
        } catch (e: Exception) {
            null
        }

        val defaultMinHeight = try {
            context.resources.displayMetrics.heightPixels / 2.0F
        } catch (e: Exception) {
            360F.dpToPx
        }
        val dokiMinHeight =
            styledAttrs?.getDimension(R.styleable.DokiContentView_dokiContentMinHeight, defaultMinHeight)
        //contentLayout?.minimumHeight = (dokiMinHeight ?: 0F).roundToInt()

        dividerColor =
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiDividerColor, Color.parseColor("#1F000000"))
                ?: Color.parseColor("#1F000000")
        imgBorderColor =
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiImgBorderColor, Color.BLACK) ?: Color.BLACK

        primaryTextColor =
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiPrimaryTextColor, Color.BLACK) ?: Color.BLACK
        secondaryTextColor =
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiSecondaryTextColor, Color.BLACK) ?: Color.BLACK
        buttonsTextColor =
            styledAttrs?.getColor(
                R.styleable.DokiContentView_dokiButtonsTextColor,
                context.extractColor(intArrayOf(R.attr.colorAccent))
            ) ?: context.extractColor(intArrayOf(R.attr.colorAccent))

        val defaultExplanationTitleText = try {
            context.getString(R.string.explanation)
        } catch (e: Exception) {
            ""
        }
        explanationTitleText = try {
            styledAttrs?.getString(R.styleable.DokiContentView_dokiExplanationTitle) ?: defaultExplanationTitleText
        } catch (e: Exception) {
            defaultExplanationTitleText
        }

        val defaultSolutionTitleText = try {
            context.getString(R.string.solution)
        } catch (e: Exception) {
            ""
        }
        solutionTitleText = try {
            styledAttrs?.getString(R.styleable.DokiContentView_dokiSolutionTitle) ?: defaultSolutionTitleText
        } catch (e: Exception) {
            defaultSolutionTitleText
        }

        val bgColor: Int = try {
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiBackgroundColor, 0) ?: 0
        } catch (e: Exception) {
            0
        }
        setBackgroundColor(bgColor)

        headerBgColor = try {
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiHeaderBackgroundColor, 0) ?: 0
        } catch (e: Exception) {
            0
        }


        activeIconsColor =
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiActiveIconsColor, Color.BLACK) ?: Color.BLACK
        inactiveIconsColor =
            styledAttrs?.getColor(R.styleable.DokiContentView_dokiInactiveIconsColor, Color.BLACK) ?: Color.BLACK

        val iconsStyleId = try {
            styledAttrs?.getInt(R.styleable.DokiContentView_dokiIconsStyle, -1) ?: -1
        } catch (e: Exception) {
            -1
        }
        if (iconsStyleId >= 0) setIconsStyle(DokiRatingView.Style.getFromId(iconsStyleId))
        else {
            val defaultActiveIconsDrawable = try {
                ContextCompat.getDrawable(context, R.drawable.ic_thumb)
            } catch (e: Exception) {
                null
            }
            activeIconsDrawable = try {
                styledAttrs?.getDrawable(R.styleable.DokiContentView_dokiActiveIconsDrawable)
                    ?: defaultActiveIconsDrawable
            } catch (e: Exception) {
                defaultActiveIconsDrawable
            }

            val defaultInactiveIconsDrawable = try {
                ContextCompat.getDrawable(context, R.drawable.ic_thumb_outline)
            } catch (e: Exception) {
                null
            }
            inactiveIconsDrawable = try {
                styledAttrs?.getDrawable(R.styleable.DokiContentView_dokiInactiveIconsDrawable)
                    ?: defaultInactiveIconsDrawable
            } catch (e: Exception) {
                defaultInactiveIconsDrawable
            }
        }

        styledAttrs?.recycle()
    }

    override fun setMinimumHeight(minHeight: Int) {
        super.setMinimumHeight(minHeight)
        //contentLayout?.minimumHeight = minHeight
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
    }

    private fun initContent() {

        //reportBtn?.setTextColor(buttonsTextColor)
        //closeBtn?.setTextColor(buttonsTextColor)
        //loadingView?.indeterminateDrawable?.tint(buttonsTextColor)

        //dividerA?.setBackgroundColor(dividerColor)
        //dividerB?.setBackgroundColor(dividerColor)
        //dividerC?.setBackgroundColor(dividerColor)
    }

    fun setContent(content: DokiManufacturer?) {
        content ?: return

        //devSolutionMessage = content.dev_solution.orEmpty()
        //reportBtn?.visibleIf(devSolutionMessage.hasContent())
        //ratingView?.rating = content.award
        //ratingContainer?.visibleIf(content.award > 0)

        binding.response?.manufacturer = content
    }

    fun setOnReportListener(listener: (view: View?, message: String) -> Unit = { _, _ -> }) {
        //reportBtn?.setOnClickListener { listener(it, devSolutionMessage) }
    }

    fun setOnCloseListener(listener: (view: View?) -> Unit = { _ -> }) {
        //closeBtn?.setOnClickListener { listener(it) }
    }

    fun setActiveIconRes(@DrawableRes resId: Int) {
        //ratingView?.activeIconsDrawable = try {
        //    ContextCompat.getDrawable(context, resId)
        //} catch (e: Exception) {
        //    null
        //}
    }

    fun setInactiveIconRes(@DrawableRes resId: Int) {
        //ratingView?.inactiveIconsDrawable = try {
        //    ContextCompat.getDrawable(context, resId)
        //} catch (e: Exception) {
        //    null
        //}
    }

    fun setActiveIconsColorRes(@ColorRes resId: Int) {
        //ratingView?.activeIconsColor = try {
        //    ContextCompat.getColor(context, resId)
        //} catch (e: Exception) {
        //    Color.BLACK
        //}
    }

    fun setInactiveIconsColorRes(@ColorRes resId: Int) {
        //ratingView?.inactiveIconsColor = try {
        //    ContextCompat.getColor(context, resId)
        //} catch (e: Exception) {
        //    Color.BLACK
        //}
    }

    fun setIconsStyle(style: DokiRatingView.Style) {
        activeIconsDrawable = try {
            ContextCompat.getDrawable(context, style.activeResId)
        } catch (e: Exception) {
            null
        }
        inactiveIconsDrawable = try {
            ContextCompat.getDrawable(context, style.inactiveResId)
        } catch (e: Exception) {
            null
        }
    }

    fun setButtonsVisibility(visible: Boolean) {
        //btnsContainer?.visibleIf(visible)
    }
}
