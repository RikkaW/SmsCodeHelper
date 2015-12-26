/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Path
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.animation.Interpolator
 *  java.lang.Object
 */
package android.support.v4.view.animation;

import android.graphics.Path;
import android.os.Build;
import android.support.v4.view.animation.PathInterpolatorCompatApi21;
import android.support.v4.view.animation.PathInterpolatorCompatBase;
import android.view.animation.Interpolator;

public class PathInterpolatorCompat {
    private PathInterpolatorCompat() {
    }

    public static Interpolator create(float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(f2, f3);
        }
        return PathInterpolatorCompatBase.create(f2, f3);
    }

    public static Interpolator create(float f2, float f3, float f4, float f5) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(f2, f3, f4, f5);
        }
        return PathInterpolatorCompatBase.create(f2, f3, f4, f5);
    }

    public static Interpolator create(Path path) {
        if (Build.VERSION.SDK_INT >= 21) {
            return PathInterpolatorCompatApi21.create(path);
        }
        return PathInterpolatorCompatBase.create(path);
    }
}

