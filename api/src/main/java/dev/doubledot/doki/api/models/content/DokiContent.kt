package dev.doubledot.doki.api.models.content

import org.jsoup.nodes.Element

open class DokiContent(
    element: Element
) {

    val value: String = element.text()
    val html: String = element.html()

    override fun toString(): String = value

}