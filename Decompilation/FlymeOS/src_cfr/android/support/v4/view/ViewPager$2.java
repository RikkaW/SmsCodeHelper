/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.animation.Interpolator;

final class ViewPager$2
implements Interpolator {
    ViewPager$2() {
    }

    public float getInterpolation(float f2) {
        return f2 * (f2 * f2 * f2 * (f2 -= 1.0f)) + 1.0f;
    }
}

