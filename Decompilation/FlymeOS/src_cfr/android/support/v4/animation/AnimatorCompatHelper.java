/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package android.support.v4.animation;

import android.os.Build;
import android.support.v4.animation.AnimatorProvider;
import android.support.v4.animation.DonutAnimatorCompatProvider;
import android.support.v4.animation.HoneycombMr1AnimatorCompatProvider;
import android.support.v4.animation.ValueAnimatorCompat;

public abstract class AnimatorCompatHelper {
    static AnimatorProvider IMPL = Build.VERSION.SDK_INT >= 12 ? new HoneycombMr1AnimatorCompatProvider() : new DonutAnimatorCompatProvider();

    AnimatorCompatHelper() {
    }

    public static ValueAnimatorCompat emptyValueAnimator() {
        return IMPL.emptyValueAnimator();
    }
}

