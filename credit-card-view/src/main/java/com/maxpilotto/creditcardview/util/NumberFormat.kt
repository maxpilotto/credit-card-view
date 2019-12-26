/*
 * Copyright 2018 Max Pilotto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.maxpilotto.creditcardview.util

import kotlin.math.ceil
import kotlin.math.min

/**
 * Formatting utility specialized in number formatting
 *
 * **Patterns**
 *
 * Patterns are placeholders that can be placed inside a string and will be replaced by a specific part of the input or the input itself
 *
 * + **%d** , placeholder for a single or multiple digits, this can be followed by a number
 * that indicates how many digits should be placed there
 *
 * + **%s** , placeholder for a group of 1 digit, a number can be added to specify how big each group is,
 * the number of groups is variable and their size is specified by the number parameter (the size is not guaranteed)
 *
 * + **%r** , placeholder for the remaining characters, this will be replaced by the remaining characters that
 * were not used by the other placeholders
 *
 * + **%c** , moves the cursor ahead, this is generally used to skip characters, it also accepts a number parameter
 * that tells the format how many characters should be skipped
 *
 * **Examples**
 *
 * ```
 * 12345678     %d4 %d4     =>      1234 5678
 * 12345678     %g2         =>      1234 5678
 * 12345678     %d2 %d2 %4  =>      12 34 5 6 7 8
 * 12345678     %s3         =>      123 456 78
 * 12345678     %d3 %s2     =>      123 45 67 8
 * 12345678     %d4         =>      1234      (no extra digits)
 * 12345678     %d4_        =>      1234_5678 (extra digits)
 * 1234         %d2/%d2     =>      12/34
 * 12345678     %c3%r       =>      45678
 * 12345        ***%c3%r    =>      ***45
 * ```
 */
open class NumberFormat(
    val format: String?,
    val showExtraDigits: Boolean = true
) : Cloneable {
    /**
     * Formats the given [input] using the [format] specified
     * when the class was instantiated
     */
    fun format(input: String): String {
        format?.let {
            var result = StringBuilder(format)
            var param: MatchResult?
            var cursor = 0

            if (format.isEmpty()) {
                return input
            }

            result = fixParameters(result)
            param = Patterns.BASE.find(result)

            while (cursor < input.length && param != null) {
                val value = param.value
                val range = param.range

                Patterns.D.find(value)?.let {
                    val count = it.value.toInt()
                    val size = min(
                        input.length,
                        cursor + count
                    )
                    val replacement = input.substring(
                        cursor,
                        size
                    )

                    cursor += replacement.length
                    result = result.replace(
                        range.first,
                        range.last + 1,
                        replacement
                    )
                }
                Patterns.S.find(value)?.let {
                    val remaining = input.length - cursor
                    val size = it.value.toInt()
                    val count = ceil(remaining / size.toDouble()).toInt()
                    var replacement = ""

                    for (i in 0 until count) {
                        val actualSize = min(
                            input.length,
                            cursor + size
                        )

                        with(input.substring(cursor, actualSize)) {
                            replacement += this
                            cursor += length
                        }

                        if (i < count - 1) {
                            replacement += " "
                        }
                    }

                    result = result.replace(
                        range.first,
                        range.last + 1,
                        replacement
                    )
                }
                Patterns.C.find(value)?.let {
                    val count = it.value.toInt()

                    if (count > input.length) {
                        throw Exception("Cursor cannot be placed out of the input range, characters to skip: $count, input length: ${input.length}")
                    }

                    cursor += count
                    result = result.replace(
                        range.first,
                        range.last + 1,
                        ""
                    )
                }

                if (Patterns.R.containsMatchIn(value)) {
                    val remaining = input.length - cursor

                    if (remaining > 0) {
                        result = result.replace(
                            range.first,
                            range.last + 1,
                            input.substring(cursor)
                        )

                        cursor += remaining
                    }
                }

                param = Patterns.BASE.find(result)
            }

            if (cursor < input.length && showExtraDigits) {
                result.append(
                    input.substring(cursor)
                )
            }

            return removeParameters(result).toString()
        }

        return input
    }

    /**
     * Returns a clone of this class
     */
    fun format(any: Number): String {
        return format(any.toString())
    }

    /**
     * Fixes all the parameters that are missing the numeric value
     */
    private fun fixParameters(input: StringBuilder): StringBuilder {
        var builder = StringBuilder(input)
        var param = Patterns.MISSING_PARAMS.find(builder)

        while (param != null) {
            builder = builder.replace(
                param.range.first,
                param.range.last + 1,
                "${param.value}1"
            )

            param = Patterns.MISSING_PARAMS.find(builder)
        }

        return builder
    }

    /**
     * Removes the parameters from the given [input]
     */
    private fun removeParameters(input: StringBuilder): StringBuilder {
        var builder = StringBuilder(input)
        var param = Patterns.EXTRA_PARAMS.find(builder)

        while (param != null) {
            builder = builder.replace(
                param.range.first,
                param.range.last + 1,
                ""
            )

            param = Patterns.EXTRA_PARAMS.find(builder)
        }

        return builder
    }

    override fun clone(): NumberFormat {
        return NumberFormat(format)
    }

    override fun toString(): String {
        return format ?: ""
    }

    override fun equals(other: Any?): Boolean {
        other?.let {
            it as NumberFormat

            return it.format == format
        }

        return false
    }
}

/**
 * Regex patterns used by the [NumberFormat] utility
 */
private class Patterns {
    companion object {
        val EXTRA_PARAMS = Regex("\\s?%[drsc]\\d*")
        val MISSING_PARAMS = Regex("%[dsc](?!\\d)")
        val BASE = Regex("%[dsrc]\\d*")
        val D = Regex("(?<=%d)\\d*")
        val S = Regex("(?<=%s)\\d*")
        val C = Regex("(?<=%c)\\d*")
        val R = Regex("%r")
    }
}