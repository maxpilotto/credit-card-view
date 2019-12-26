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

import android.content.res.TypedArray
import android.content.res.XmlResourceParser
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.annotation.StyleableRes
import com.maxpilotto.creditcardview.XmlParserHandler

/**
 * Returns the drawable for the attribute at [index]
 *
 * If the attribute is not defined, the drawable with id [default] will be returned
 */
internal fun TypedArray.getDrawable(
    @StyleableRes index: Int,
    @DrawableRes default: Int
): Drawable {
    return getDrawable(index) ?: resources.getDrawable(default)
}

/**
 * Returns the drawable for the attribute at [index]
 *
 * If the attribute is not defined, [default] will be returned
 */
internal fun TypedArray.getDrawable(
    @StyleableRes index: Int,
    default: Drawable?
): Drawable? {
    return getDrawable(index) ?: default
}

/**
 * Returns a pixel unit for the attribute at [index]
 *
 * If the attribute is not defined, [default] will be returned
 */
internal fun TypedArray.getDimensionPixelSize(
    @StyleableRes index: Int,
    default: Float
): Float {
    return if (hasValue(index)) {
        getDimensionPixelSize(index, 0).toFloat()
    } else {
        default
    }
}

/**
 * Returns an SP unit for the attribute at [index]
 *
 * If the attribute is not defined, [default] will be returned
 *
 * Note: [default] must be defined using the SP unit
 */
internal fun TypedArray.getDimensionFontSize(
    @StyleableRes index: Int,
    default: Float
): Float {
    return if (hasValue(index)) {
        getDimensionPixelSize(index, 0F).sp
    } else {
        default
    }
}

/**
 * Returns an object of type [T], which must be parsed using an [XmlResourceParser]
 *
 * The [handler] should take care of the parsing and return the parsed object
 */
internal inline fun <T> TypedArray.getXml(
    @StyleableRes index: Int,
    handler: XmlParserHandler<T>
): T? {
    return if (hasValue(index)) {
        val parser = resources.getXml(getResourceId(index, 0))

        handler(parser)
    } else {
        handler(null)
    }
}

/**
 * Returns an object of type [T], which must be parsed using an [XmlResourceParser]
 *
 * The [handler] should take care of the parsing and return the parsed object
 */
internal inline fun <T> TypedArray.getXmlOrNull(
    @StyleableRes index: Int,
    handler: XmlParserHandler<T>
): T? {
    return if (hasValue(index)) {
        getXml(index, handler)
    } else {
        null
    }
}

/**
 * Returns a resource id for the attribute at [index]
 *
 * If the attribute is not defined, [default] will be returned
 */
internal fun TypedArray.getResourceId(
    @StyleableRes index: Int,
    default: Int?
): Int? {
    return if (hasValue(index)) {
        getResourceId(index, 0)
    } else {
        default
    }
}