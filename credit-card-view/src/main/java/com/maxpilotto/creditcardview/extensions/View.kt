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
package com.maxpilotto.creditcardview.extensions

import android.view.View

/**
 * Sets the width of the view
 */
internal fun View.setWidth(width: Int) {
    layoutParams.width = width
}

/**
 * Sets the height of the View
 */
internal fun View.setHeight(height: Int) {
    layoutParams.height = height
}

/**
 * Sets both the height and width of the View
 */
internal fun View.setSizes(sizes: Int) {
    setHeight(sizes)
    setWidth(sizes)
}