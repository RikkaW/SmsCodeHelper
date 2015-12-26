/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  java.lang.Object
 */
package android.support.v7.internal.widget;

import android.graphics.Rect;

public interface FitWindowsViewGroup {
    public void setOnFitSystemWindowsListener(OnFitSystemWindowsListener var1);

    public static interface OnFitSystemWindowsListener {
        public void onFitSystemWindows(Rect var1);
    }

}

