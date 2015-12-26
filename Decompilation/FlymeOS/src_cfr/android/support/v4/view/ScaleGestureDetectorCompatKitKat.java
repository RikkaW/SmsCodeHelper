/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.ScaleGestureDetector
 *  java.lang.Object
 */
package android.support.v4.view;

import android.view.ScaleGestureDetector;

class ScaleGestureDetectorCompatKitKat {
    private ScaleGestureDetectorCompatKitKat() {
    }

    public static boolean isQuickScaleEnabled(Object object) {
        return ((ScaleGestureDetector)object).isQuickScaleEnabled();
    }

    public static void setQuickScaleEnabled(Object object, boolean bl2) {
        ((ScaleGestureDetector)object).setQuickScaleEnabled(bl2);
    }
}

