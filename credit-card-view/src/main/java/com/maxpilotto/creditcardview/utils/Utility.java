package com.maxpilotto.creditcardview.utils;

import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/**
 * Project: credit-card-view
 * Created by: Max
 * Date: 29/07/2018 @ 23:14
 * Package: com.maxpilotto.creditcardview.utils
 */
public class Utility {
    public static final char SPACE = 32;

    /**
     * Tints a drawable resource with the given color
     * @param resource
     * @param color
     */
    public static void tintDrawable(Drawable resource,int color) {
        DrawableCompat.setTint(resource,color);
    }
}
