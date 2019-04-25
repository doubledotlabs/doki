package dev.doubledot.doki.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import dev.doubledot.doki.R
import dev.doubledot.doki.api.models.DokiManufacturer
import dev.doubledot.doki.extensions.*
import dev.doubledot.doki.models.Device

@Suppress("MemberVisibilityCanBePrivate")
class DokiContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {

    // View bindings ---------------------------------------

    private val deviceManufacturerHeader: TextView? by bind(R.id.deviceManufacturerHeader)
    private val deviceManufacturer: TextView? by bind(R.id.deviceManufacturer)

    private val deviceModelHeader: TextView? by bind(R.id.deviceModelHeader)
    private val deviceModel: TextView? by bind(R.id.deviceModel)

    private val deviceAndroidVersionHeader: TextView? by bind(R.id.deviceAndroidVersionHeader)
    private val deviceAndroidVersion: TextView? by bind(R.id.deviceAndroidVersion)

    private val contentLayout: FrameLayout? by bind(R.id.doki_actual_content)
    private val loadingView: ProgressBar? by bind(R.id.doki_loading_view)

    private val manufacturerRatingContainer : View? = null
    private val manufacturerRatingHeader: TextView? by bind(R.id.manufacturerRatingHeader)
    private val manufacturerRating: DokiRatingView? by bind(R.id.manufacturerRating)

    //private val headerContainer: LinearLayout? by bind(R.id.doki_details_header)

    private val buttonContainer: View? by bind(R.id.buttonContainer)
    private val reportBtn: TextView? by bind(R.id.buttonReport)
    private val closeBtn: TextView? by bind(R.id.buttonClose)

    private val divider1: View? by bind(R.id.divider1)
    private val divider2: View? by bind(R.id.divider2)
    private val divider3: View? by bind(R.id.divider3)

    // Theming attrs ---------------------------------------

    var primaryTextColor: Int = Color.BLACK
        set(value) {
            deviceManufacturer?.setTextColor(value)
            deviceModel?.setTextColor(value)
            deviceAndroidVersion?.setTextColor(value)
            field = value
        }

    var secondaryTextColor: Int = Color.BLACK
        set(value) {
            deviceManufacturerHeader?.setTextColor(value)
            deviceModelHeader?.setTextColor(value)
            deviceAndroidVersionHeader?.setTextColor(value)
            manufacturerRatingHeader?.setTextColor(value)
            field = value
        }

    var buttonsTextColor: Int = context.extractColor(intArrayOf(R.attr.colorAccent))
        set(value) {
            reportBtn?.setTextColor(value)
            closeBtn?.setTextColor(value)
            field = value
        }

    var dividerColor: Int = Color.parseColor("#1F000000")
        set(value) {
            divider1?.setBackgroundColor(value)
            divider2?.setBackgroundColor(value)
            divider3?.setBackgroundColor(value)
            field = value
        }

    var headerBackgroundColor: Int = Color.TRANSPARENT
        set(value) {
            // TODO: set header color
            field = value
        }

    var activeIconsDrawable: Drawable? = null
        set(value) {
            manufacturerRating?.activeIconsDrawable = value
            field = value
        }

    var inactiveIconsDrawable: Drawable? = null
        set(value) {
            manufacturerRating?.inactiveIconsDrawable = value
            field = value
        }

    var iconsStyle : DokiRatingView.Style? = DokiRatingView.Style.THUMB
        set(value) {
            value?.let {
                activeIconsDrawable = try {
                    ContextCompat.getDrawable(context, it.activeResId)
                } catch (e: Exception) {
                    null
                }

                inactiveIconsDrawable = try {
                    ContextCompat.getDrawable(context, it.inactiveResId)
                } catch (e: Exception) {
                    null
                }
            }

            field = value
        }

    var activeIconsColor: Int = Color.BLACK
        set(value) {
            manufacturerRating?.activeIconsColor = value
            field = value
        }

    var inactiveIconsColor: Int = Color.BLACK
        set(value) {
            manufacturerRating?.inactiveIconsColor = value
            field = value
        }

    var lineHeight : Float = 1.8F
        set(value) {
            field = value
        }

    // Data / models ------------------------------------

    private var devSolutionMessage: String = ""
    var explanationTitleText: String = ""
    var solutionTitleText: String = ""

    var device : Device? = null
        set(value) {
            field = value
            value ?: return

            deviceManufacturer?.text = value.manufacturer
            deviceModel?.text = value.model
            deviceAndroidVersion?.text = value.androidVersion
        }

    var manufacturer : DokiManufacturer? = null
        set(value) {
            field = value
            value ?: return

            manufacturerRating?.rating = value.award
            manufacturerRatingContainer?.visibleIf(value.award > 0)

            loadingView?.gone()
        }

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_doki_content, this, true)

        initFromAttrs(attrs)
        device = Device()
    }

    @SuppressLint("Recycle")
    private fun initFromAttrs(attrs: AttributeSet?) {
        val styledAttrs: TypedArray? = try {
            context.obtainStyledAttributes(attrs, R.styleable.DokiContentView, 0, 0)
        } catch (e: Exception) {
            null
        }

        // Styling

        primaryTextColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiPrimaryTextColor) ?: primaryTextColor
        secondaryTextColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiSecondaryTextColor) ?: secondaryTextColor
        buttonsTextColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiButtonsTextColor) ?: buttonsTextColor
        dividerColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiDividerColor) ?: dividerColor
        headerBackgroundColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiHeaderBackgroundColor) ?: headerBackgroundColor
        activeIconsDrawable = styledAttrs?.getDrawableOrNull(R.styleable.DokiContentView_dokiActiveIconsDrawable) ?: activeIconsDrawable
        inactiveIconsDrawable = styledAttrs?.getDrawableOrNull(R.styleable.DokiContentView_dokiInactiveIconsDrawable) ?: inactiveIconsDrawable
        iconsStyle = getStyledIconsStyle(styledAttrs) ?: iconsStyle
        activeIconsColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiActiveIconsColor) ?: activeIconsColor
        inactiveIconsColor = styledAttrs?.getColorOrNull(R.styleable.DokiContentView_dokiInactiveIconsColor) ?: inactiveIconsColor

        // Data

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

        styledAttrs?.recycle()
    }

    fun setContent(content: DokiManufacturer?) {
        manufacturer = content
    }

    fun setOnReportListener(listener: (view: View?, message: String) -> Unit = { _, _ -> }) {
        reportBtn?.setOnClickListener { listener(it, devSolutionMessage) }
    }

    fun setOnCloseListener(listener: (view: View?) -> Unit = { _ -> }) {
        closeBtn?.setOnClickListener { listener(it) }
    }

    fun setButtonsVisibility(visible: Boolean) {
        buttonContainer?.visibleIf(visible)
    }

    private fun getStyledIconsStyle(attrs : TypedArray?) : DokiRatingView.Style? {
        val iconsStyleId = try {
            attrs?.getInt(R.styleable.DokiContentView_dokiIconsStyle, -1) ?: -1
        } catch (e: Exception) {
            -1
        }

        return if (iconsStyleId >= 0)
            DokiRatingView.Style.getFromId(iconsStyleId)
        else null
    }
}
