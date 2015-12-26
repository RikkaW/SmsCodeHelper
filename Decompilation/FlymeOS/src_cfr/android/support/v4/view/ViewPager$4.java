/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v4.view;

import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;

final class ViewPager$4
implements Interpolator {
    ViewPager$4() {
    }

    public float getInterpolation(float f2) {
        if (f2 <= 0.0f) {
            return ViewPager.access$500()[0];
        }
        if (f2 >= 1.0f) {
            return ViewPager.access$500()[ViewPager.access$500().length - 1];
        }
        float f3 = 1.0f / (float)(ViewPager.access$500().length - 1);
        int n2 = (int)(f2 / f3);
        float f4 = n2;
        float f5 = ViewPager.access$500()[n2];
        return (ViewPager.access$500()[n2 + 1] - ViewPager.access$500()[n2]) * (f2 - f4 * f3) / f3 + f5;
    }
}

