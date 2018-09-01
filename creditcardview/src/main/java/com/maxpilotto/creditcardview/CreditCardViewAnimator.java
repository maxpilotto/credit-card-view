package com.maxpilotto.creditcardview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Project: credit-card-view
 * Created by: Max
 * Date: 28/08/2018 @ 19:38
 * Package: com.maxpilotto.creditcardview
 */
public class CreditCardViewAnimator {
    /**
     * Animates the transition between the first and the second view with the given duration
     * @param frontView View that is going in background
     * @param backView View that is going in foreground
     * @param duration Duration of the animation
     */
    public static void animate(View frontView,View backView,long duration){
        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(frontView, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(backView, "scaleX", 0f, 1f);
        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.setDuration(duration);
        oa2.setDuration(duration);
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                oa2.start();

            }
        });
        oa1.start();
    }

    /**
     * Animates the transition between the first and the second view with the default duration
     * @param frontView View that is going in background
     * @param backView View that is going in foreground
     */
    public static void animate(View frontView,View backView){
        animate(frontView,backView,100);
    }

    /**
     * Animates the transition between the first and the second view with the default duration
     * If smooth is False, it has the same effect of calling animate(frontView,backView,0)
     * @param frontView View that is going in background
     * @param backView View that is going in foreground
     * @param smooth True if the animation has to be smooth, False otherwise (duration will be 0)
     */
    public static void animate(View frontView,View backView,boolean smooth){
        if (smooth){
            animate(frontView,backView,100);
        }else{
            animate(frontView,backView,0);
        }
    }
}
