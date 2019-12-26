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

import com.maxpilotto.creditcardview.extensions.isNumeric
import java.util.*

/**
 * Object that represents a credit card
 */
open class CreditCard : Cloneable {
    /**
     * Name of the holder on the front of the credit card
     */
    var holder: String

    /**
     * Digits on the front of the credit card
     */
    var number: String
        set(value) {
            field = value

            brand = Brand.parse(value)
        }

    /**
     * 3 digits number on the back of the credit card
     */
    var cvv: String

    /**
     * Expiration date on the back of the credit card
     */
    var expiry: String

    /**
     * Pin code used to confirm payments or withdraw money,
     *
     * This code is not necessary used, you should ask the user this code only if you're
     * building a credit card vault or generic credential vault
     */
    var pinCode: String

    /**
     * Brand of the credit card, this field is read-only and will change based on the [number]
     */
    var brand: Brand
        private set

    constructor(card: CreditCard) : this(
        card.holder,
        card.number,
        card.cvv,
        card.expiry,
        card.pinCode
    )

    @JvmOverloads
    constructor(
        holder: String = "",
        number: String = "",
        cvv: String = "",
        expiry: String = "",
        pinCode: String = ""
    ) {
        this.brand = Brand.GENERIC
        this.holder = holder
        this.number = number
        this.cvv = cvv
        this.expiry = expiry
        this.pinCode = pinCode
    }

    /**
     * Checks if the card's number only contains numbers
     */
    fun isNumberValid(): Boolean {
        return number.isNumeric()
    }

    /**
     * Checks if the expiry is valid, both month and year must start from 1
     */
    fun isExpiryValid(): Boolean {
        val c = Calendar.getInstance()
        val month: Int
        val year: Int

        return try {
            month = expiry.substring(0, 2).toInt()
            year = expiry.substring(2, 4).toInt()

            year >= c.get(Calendar.YEAR) % 100 && month in 1..12
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Checks if the CVV is valid, a valid CVV must be 3 digits long and contain numbers only
     */
    fun isCvvValid(): Boolean {
        return cvv.length == 3 && cvv.isNumeric()
    }

    override fun toString(): String {
        return "Holder: $holder, Number: $number, Expiry: $expiry, CVV: $cvv"
    }

    override fun equals(other: Any?): Boolean {
        other?.let {
            it as CreditCard

            return it.holder == holder &&
                    it.number == number &&
                    it.cvv == cvv &&
                    it.expiry == expiry
        }

        return false
    }

    override fun clone(): Any {
        return CreditCard(
            holder,
            number,
            cvv,
            expiry
        )
    }
}