package dev.doubledot.doki.api.util

import dev.doubledot.doki.api.models.content.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import java.lang.Exception

class DokiContentParser(
    val strictMode: Boolean = false
) {

    fun parseContent(content: String) : List<DokiContent> {
        val list : MutableList<DokiContent> = ArrayList()
        val doc : Document = Jsoup.parse(content)

        doc.children().forEach { element ->
            parseElement(element, list)
        }

        return list
    }

    fun parseElement(element: Element, list: MutableList<DokiContent>) {
        errorHandling {
            when (element.tagName()) {
                "h1", "h2", "h3", "h4", "h5" -> list.add(
                    DokiContentHeading(
                        element
                    )
                )
                "p", "figcaption" -> list.add(
                    DokiContentText(
                        element
                    )
                )
                "blockquote" -> list.add(
                    DokiContentQuote(
                        element
                    )
                )
                "img" -> list.add(
                    DokiContentImage(
                        element
                    )
                )
                "span" -> {
                    if (element.hasAttr("data-action"))
                        list.add(
                            DokiContentAction(
                                element
                            )
                        )
                    else list.add(
                        DokiContentText(
                            element
                        )
                    )
                }
                else -> element.children().forEach { e: Element -> parseElement(e, list) }
            }
        }
    }

    fun <T> errorHandling(block: () -> T) : T? {
        return try {
            block()
        } catch (e: Exception) {
            if (strictMode)
                throw e
            else null
        }
    }

}