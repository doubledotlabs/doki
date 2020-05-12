package dev.doubledot.doki.api.models.content

import dev.doubledot.doki.api.models.content.DokiContent
import org.jsoup.nodes.Element

class DokiContentHeading(
    element: Element
) : DokiContent(element) {

    val size: Int = element.tagName()[1].toInt()

}