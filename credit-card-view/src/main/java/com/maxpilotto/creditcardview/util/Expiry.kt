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

import java.text.DateFormat
import java.util.*

/**
 * Utility for parsing the expiry
 */
class Expiry {
    companion object {
        /**
         * Converts the given formats into the MMyy format
         *
         * Accepted formats (using september 2018 as an example):
         * + null
         * + EMPTY
         * + 09/18
         * + 0918
         * + 09-18
         * + 09/2018
         * + 092018
         * + 09-2018
         * + 2018/09
         * + 2018-09
         */
        @JvmStatic
        fun from(expiry: String?): String {
            val test1 = Regex("[0-9]{2}/[0-9]{2}")  // 09/18
            val test2 = Regex("[0-9]{4}")           // 0918
            val test3 = Regex("[0-9]{2}-[0-9]{2}")  // 09-18
            val test4 = Regex("[0-9]{6}")           // 092018
            val test5 = Regex("[0-9]{2}/[0-9]{4}")  // 09/2018
            val test6 = Regex("[0-9]{2}-[0-9]{4}")  // 09-2018
            val test7 = Regex("[0-9]{4}/[0-9]{2}")  // 2018/09
            val test8 = Regex("[0-9]{4}-[0-9]{2}")  // 2018-09

            if (expiry.isNullOrEmpty()) {
                return ""
            }

            return with(expiry) {
                when {
                    matches(test1) -> expiry.replace("/", "")
                    matches(test2) -> expiry
                    matches(test3) -> expiry.replace("-", "")
                    matches(test4) -> expiry.substring(0, 2) + expiry.substring(4, 6)
                    matches(test5) || matches(test6) -> expiry.substring(0, 2) + expiry.substring(5, 7)
                    matches(test7) || matches(test8) -> expiry.substring(5, 7) + expiry.substring(2, 4)

                    else -> throw Exception("Cannot parse date, unrecognized format")
                }
            }
        }

        /**
         * Creates an expiry from the given [month] and [year]
         */
        @JvmStatic
        fun from(month: Number, year: Number): String {
            return "$month$year"
        }

        /**
         * Creates an expiry from the given date string which is formatted using the given date format
         */
        @JvmStatic
        fun from(date: String, dateFormat: DateFormat): String {
            val calendar = Calendar.getInstance()

            calendar.time = dateFormat.parse(date)!!

            return from(calendar)
        }

        /**
         * Creates an expiry from the given [date]
         */
        @JvmStatic
        fun from(date: Date): String {
            return from(
                date.month,
                date.year
            )
        }

        /**
         * Creates an expiry from the given [calendar]
         */
        @JvmStatic
        fun from(calendar: Calendar): String {
            return from(
                calendar[Calendar.MONTH],
                calendar[Calendar.YEAR]
            )
        }
    }
}