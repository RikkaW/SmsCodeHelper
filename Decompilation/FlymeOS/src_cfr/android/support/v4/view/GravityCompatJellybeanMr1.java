/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.view.Gravity
 *  java.lang.Object
 */
package android.support.v4.view;

import android.graphics.Rect;
import android.view.Gravity;

class GravityCompatJellybeanMr1 {
    GravityCompatJellybeanMr1() {
    }

    public static void apply(int n2, int n3, int n4, Rect rect, int n5, int n6, Rect rect2, int n7) {
        Gravity.apply((int)n2, (int)n3, (int)n4, (Rect)rect, (int)n5, (int)n6, (Rect)rect2, (int)n7);
    }

    public static void apply(int n2, int n3, int n4, Rect rect, Rect rect2, int n5) {
        Gravity.apply((int)n2, (int)n3, (int)n4, (Rect)rect, (Rect)rect2, (int)n5);
    }

    public static void applyDisplay(int n2, Rect rect, Rect rect2, int n3) {
        Gravity.applyDisplay((int)n2, (Rect)rect, (Rect)rect2, (int)n3);
    }

    public static int getAbsoluteGravity(int n2, int n3) {
        return Gravity.getAbsoluteGravity((int)n2, (int)n3);
    }
}

