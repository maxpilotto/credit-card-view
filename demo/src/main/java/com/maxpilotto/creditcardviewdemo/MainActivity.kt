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
package com.maxpilotto.creditcardviewdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maxpilotto.creditcardview.animations.RotationAnimation
import com.maxpilotto.creditcardview.models.CardArea
import com.maxpilotto.creditcardview.models.CardInput
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        card.flipOnEdit = true
        card.flipOnEditAnimation = RotationAnimation()
        card.setAreaClickListener { card, area ->
            if (area == CardArea.LEFT) {
                card.flip(
                    RotationAnimation(RotationAnimation.LEFT)
                )
            } else if (area == CardArea.RIGHT) {
                card.flip(
                    RotationAnimation(RotationAnimation.RIGHT)
                )
            }
        }

        card.apply {
            pairInput(CardInput.HOLDER, _holder)
            pairInput(CardInput.NUMBER, _number)
            pairInput(CardInput.EXPIRY, _expiry)
            pairInput(CardInput.CVV, _cvv)
        }
    }
}
