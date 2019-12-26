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
package com.maxpilotto.creditcardview

import android.content.res.XmlResourceParser
import android.graphics.Point
import com.maxpilotto.creditcardview.models.Brand
import com.maxpilotto.creditcardview.models.CardArea

/**
 * Basic empty callback
 */
typealias Callback = () -> Unit

/**
 * Advanced click listener that makes use of the clicked position and area
 */
typealias AreaClickListener = (
    card: CreditCardView,
    area: CardArea
) -> Unit

/**
 * Click listener for the [CreditCardView.setGridClickListener] method,
 * this gives the coordinates (x and y) on the grid you have specified and the clicked point
 * (the actual x and y on the screen)
 */
typealias GridClickListener = (
    card: CreditCardView,
    gridPosition: Point
) -> Unit

/**
 * Map where each Brand has its own card style
 */
typealias StyleMap = MutableMap<Brand, Int?>

/**
 * XML parser handler that should handle the parsing of a xml resource
 */
typealias XmlParserHandler<T> = (XmlResourceParser?) -> T