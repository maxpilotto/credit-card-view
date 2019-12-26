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
 * Areas of the card that can be notified when clicked
 */
enum class CardArea {
    /**
     * Top left corner of the cards, in a 3x3 grid this is 1:1
     *
     * This could be triggered together with [TOP] and [LEFT]
     */
    TOP_LEFT,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 1:2
     *
     * This could be triggered together [TOP], [RIGHT] and [LEFT]
     */
    TOP_CENTER,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 1:3
     *
     * This could be triggered together [TOP] and [RIGHT]
     */
    TOP_RIGHT,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 2:1
     *
     * This could be triggered together [TOP], [BOTTOM] and [LEFT]
     */
    CENTER_LEFT,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 3:1
     *
     * This could be triggered together [TOP], [BOTTOM], [LEFT] and [RIGHT]
     */
    CENTER,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 2:1
     *
     * This could be triggered together [TOP], [BOTTOM] and [RIGHT]
     */
    CENTER_RIGHT,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 2:1
     *
     * This could be triggered together [LEFT] and [BOTTOM]
     */
    BOTTOM_LEFT,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 2:1
     *
     * This could be triggered together [BOTTOM], [RIGHT] and [LEFT]
     */
    BOTTOM_CENTER,

    /**
     * Top left corner of the cards, in a 3x3 grid this is 2:1
     *
     * This could be triggered together [BOTTOM] and [RIGHT]
     */
    BOTTOM_RIGHT,

    /**
     * Top part of the card
     *
     * It has the same width of the card and half of its height
     *
     * This could be triggered together with [TOP_LEFT], [TOP_CENTER], [TOP_RIGHT], [CENTER_LEFT], [CENTER] and [CENTER_RIGHT]
     */
    TOP,

    /**
     * Bottom part of the card
     *
     * It has the same width of the card and half of its height
     *
     * This could be triggered together with [BOTTOM_LEFT], [BOTTOM_CENTER], [BOTTOM_RIGHT], [CENTER_LEFT], [CENTER] and [CENTER_RIGHT]
     */
    BOTTOM,

    /**
     * Left part of the card
     *
     * It has the same height of the card and half of its width
     *
     * This could be triggered together with [TOP_LEFT], [CENTER_LEFT], [BOTTOM_LEFT], [CENTER_LEFT], [CENTER] and [CENTER_RIGHT]
     */
    LEFT,

    /**
     * Right part of the card
     *
     * It has the same height of the card and half of its width
     *
     * This could be triggered together with [TOP_RIGHT], [CENTER_RIGHT], [BOTTOM_RIGHT], [CENTER_LEFT], [CENTER] and [CENTER_RIGHT]
     */
    RIGHT,

}