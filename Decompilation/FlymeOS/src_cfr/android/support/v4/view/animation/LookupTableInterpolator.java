/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Interpolator
 *  java.lang.Math
 *  java.lang.Object
 */
package android.support.v4.view.animation;

import android.view.animation.Interpolator;

abstract class LookupTableInterpolator
implements Interpolator {
    private final float mStepSize;
    private final float[] mValues;

    public LookupTableInterpolator(float[] arrf) {
        this.mValues = arrf;
        this.mStepSize = 1.0f / (float)(this.mValues.length - 1);
    }

    public float getInterpolation(float f2) {
        if (f2 >= 1.0f) {
            return 1.0f;
        }
        if (f2 <= 0.0f) {
            return 0.0f;
        }
        int n2 = Math.min((int)((int)((float)(this.mValues.length - 1) * f2)), (int)(this.mValues.length - 2));
        f2 = (f2 - (float)n2 * this.mStepSize) / this.mStepSize;
        float f3 = this.mValues[n2];
        return (this.mValues[n2 + 1] - this.mValues[n2]) * f2 + f3;
    }
}

