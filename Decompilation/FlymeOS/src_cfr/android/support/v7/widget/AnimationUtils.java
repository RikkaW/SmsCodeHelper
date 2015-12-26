/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Animation
 *  android.view.animation.Animation$AnimationListener
 *  android.view.animation.DecelerateInterpolator
 *  android.view.animation.Interpolator
 *  android.view.animation.LinearInterpolator
 *  java.lang.Math
 *  java.lang.Object
 */
package android.support.v7.widget;

import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

public class AnimationUtils {
    public static final Interpolator DECELERATE_INTERPOLATOR;
    public static final Interpolator LINEAR_INTERPOLATOR;

    static {
        LINEAR_INTERPOLATOR = new LinearInterpolator();
        DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    }

    public static float lerp(float f2, float f3, float f4) {
        return (f3 - f2) * f4 + f2;
    }

    public static int lerp(int n2, int n3, float f2) {
        return Math.round((float)((float)(n3 - n2) * f2)) + n2;
    }

    static class AnimationListenerAdapter
    implements Animation.AnimationListener {
        AnimationListenerAdapter() {
        }

        public void onAnimationEnd(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

}

