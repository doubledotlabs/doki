package dev.doubledot.doki.views

import android.content.Context
import android.text.method.LinkMovementMethod
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import dev.doubledot.doki.extensions.dpToPx
import ru.noties.markwon.AbstractMarkwonPlugin
import ru.noties.markwon.Markwon
import ru.noties.markwon.MarkwonConfiguration
import ru.noties.markwon.RenderProps
import ru.noties.markwon.core.MarkwonTheme
import ru.noties.markwon.core.spans.CodeBlockSpan
import ru.noties.markwon.html.HtmlPlugin
import ru.noties.markwon.html.HtmlTag
import ru.noties.markwon.html.MarkwonHtmlRenderer
import ru.noties.markwon.html.tag.SimpleTagHandler
import ru.noties.markwon.image.ImagesPlugin
import ru.noties.markwon.image.okhttp.OkHttpImagesPlugin
import kotlin.math.roundToInt


class DokiHtmlTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    private val markwon = Markwon.builder(context)
        .usePlugin(ImagesPlugin.create(context))
        .usePlugin(OkHttpImagesPlugin.create())
        .usePlugin(HtmlPlugin.create())
        .usePlugin(object: AbstractMarkwonPlugin() {
            override fun configureHtmlRenderer(builder: MarkwonHtmlRenderer.Builder) {
                builder.setHandler("code", object: SimpleTagHandler() {
                    override fun getSpans(configuration: MarkwonConfiguration, renderProps: RenderProps, tag: HtmlTag): Any? {
                        return CodeBlockSpan(configuration.theme())
                    }
                })
            }

            override fun configureTheme(builder: MarkwonTheme.Builder) {
                builder.headingBreakHeight(0)
                    .linkColor(linkHighlightColor)
                    .blockMargin(24f.dpToPx.roundToInt())
                    .blockQuoteWidth(4f.dpToPx.roundToInt())
                    .codeBackgroundColor(0x00FFFFFF)
                    .codeBlockBackgroundColor(0x00FFFFFF)
            }
        })
        .build()

    var htmlText : String? = null
        set(value) {
            value?.let {
                markwon.setMarkdown(this, value)
            }
            field = value
        }

    var linkHighlightColor : Int = 0
        set(value) {
            field = value
            htmlText = htmlText
        }

    init {
        movementMethod = LinkMovementMethod.getInstance()
    }

}