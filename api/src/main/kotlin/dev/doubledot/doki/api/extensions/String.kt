package dev.doubledot.doki.api.extensions

import androidx.annotation.IntRange
import java.math.RoundingMode
import java.text.DecimalFormat

fun String.hasContent() = isNotEmpty() && isNotBlank()

internal fun Number.round(@IntRange(from = 1L) decimalCount: Int): String {
    val expression = StringBuilder().append("#.")
    (1..decimalCount).forEach { _ -> expression.append("#") }
    val formatter = DecimalFormat(expression.toString())
    formatter.roundingMode = RoundingMode.HALF_UP
    return formatter.format(this)
}
