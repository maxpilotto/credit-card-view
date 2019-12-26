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

import android.content.res.XmlResourceParser

/**
 * Digit filler, this class is used to fill the credit card's number with characters
 * for when the user is inserting the number
 *
 * A filler has 2 properties:
 * + fillRange, the range that fill either be filled by the input or by the fillValue
 * + fillValue, value that is used to fill
 *
 * Example of how the filler works
 * ```
 * Input = "222233334444"
 * MaxLength = 16
 * FillValue = "*"
 * Result = "222233334444****"
 * ```
 * More examples, using 222233334444 as example number
 * ```
 * Filler(16,"*")                   =>   222233334444****
 * Filler(20,"*")                   =>   222233334444********
 * Filler(16,"X")                   =>   222233334444XXXX
 * Filler(16,"*") + Filler(20,"*")  =>   222233334444****
 * ```
 */
class Filler(val length: Int, val fillValue: String) : Comparable<Filler> {
    fun format(input: String): String {
        if (length < input.length) {
            throw Exception("Filler max length should be smalled or equal to the input's length; Max length: $length, Input length: ${input.length}")
        }

        if (length == input.length) {
            return input
        }

        return StringBuilder().apply {
            val diff = this@Filler.length - input.length

            append(input)

            for (i in 0 until diff) {
                append(fillValue)
            }
        }.toString()
    }

    override fun compareTo(other: Filler): Int {
        return length - other.length
    }

    companion object {
        /**
         * Parses the given XMLResource into a List of Filler
         */
        @JvmStatic
        fun parseList(xmlParser: XmlResourceParser?): List<Filler>? {
            xmlParser?.run {
                val list = mutableListOf<Filler>()
                var event = next()

                while (event != XmlResourceParser.END_DOCUMENT) {
                    if (event == XmlResourceParser.START_TAG && name == "Filler") {
                        list.add(
                            Filler(
                                getAttributeIntValue(null, "length", 0),
                                getAttributeValue(null, "fillValue") ?: "*"
                            )
                        )
                    }

                    event = next()
                }
            }

            return null
        }
    }
}