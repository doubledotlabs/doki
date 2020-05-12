package dev.doubledot.doki.api.models.content

import dev.doubledot.doki.api.models.content.DokiContent
import org.jsoup.nodes.Element

class DokiContentImage(
    element: Element
) : DokiContent(element) {

    val src: String? = element.attr("src")

}