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
package com.maxpilotto.creditcardview.models

/**
 * Credit Card's brands
 */
enum class Brand {
    AMEX("^3[47][0-9]{13}$"),
    DINERS("^3(?:0[0-5]\\d|095|6\\d{0,2}|[89]\\d{2})\\d{12,15}$"),
    DISCOVER("^6(?:011|[45][0-9]{2})[0-9]{12}$"),
    GENERIC,
    JCB("^(?:2131|1800|35\\d{3})\\d{11}$"),
    MAESTRO("^(50|[56–69]|6759|676770|676774)[0-9]+\$"),
    MASTERCARD("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]{12}\$"),
    UNIONPAY("^62[0-9]{14,17}$"),
    VISA("^4[0-9]{12}(?:[0-9]{3}){0,2}$");

    private val regex: Regex?

    constructor() {
        this.regex = null
    }

    constructor(regex: String) {
        this.regex = regex.toRegex()
    }

    companion object {
        /**
         * Returns the brand of the given card's number
         */
        @JvmStatic
        fun parse(number: String): Brand {
            for (b in values()) {
                b.regex?.let {
                    if (it.matches(number)) {
                        return b
                    }
                }
            }

            return GENERIC
        }
    }
}