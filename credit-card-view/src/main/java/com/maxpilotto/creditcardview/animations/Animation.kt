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
package com.maxpilotto.creditcardview.animations

import android.animation.AnimatorSet
import android.view.View
import com.maxpilotto.creditcardview.CreditCardView

/**
 * Base animation class
 */
abstract class Animation(val time: Long) {
    /**
     * Animates the given [card]'s [frontView] and [backView]
     *
     * Keep in mind that [CreditCardView.isFlipped]'s value is changed after the animation is done
     */
    abstract fun animate(
        frontView: View,
        backView: View,
        card: CreditCardView
    ): AnimatorSet

    /**
     * Extension of Kotlin's [MutableCollection.addAll] which takes a variable number of elements
     * and adds them to the mutable list
     */
    protected fun <E> MutableCollection<E>.addAll(vararg elements: E): Boolean {
        for (e in elements) {
            if (!add(e)) {
                return false
            }
        }

        return true
    }
}