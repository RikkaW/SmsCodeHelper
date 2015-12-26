/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
import android.view.animation.Interpolator;

public final class aro
implements Interpolator {
    public float getInterpolation(float f2) {
        return f2 * (f2 * f2 * f2 * (f2 -= 1.0f)) + 1.0f;
    }
}

