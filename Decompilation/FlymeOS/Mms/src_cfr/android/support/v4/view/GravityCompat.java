/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.Gravity
 *  java.lang.Object
 */
package android.support.v4.view;

import android.graphics.Rect;
import android.os.Build;
import android.support.v4.view.GravityCompatJellybeanMr1;
import android.view.Gravity;

public class GravityCompat {
    public static final int END = 8388613;
    static final GravityCompatImpl IMPL = Build.VERSION.SDK_INT >= 17 ? new GravityCompatImplJellybeanMr1() : new GravityCompatImplBase();
    public static final int RELATIVE_HORIZONTAL_GRAVITY_MASK = 8388615;
    public static final int RELATIVE_LAYOUT_DIRECTION = 8388608;
    public static final int START = 8388611;

    public static void apply(int n2, int n3, int n4, Rect rect, int n5, int n6, Rect rect2, int n7) {
        IMPL.apply(n2, n3, n4, rect, n5, n6, rect2, n7);
    }

    public static void apply(int n2, int n3, int n4, Rect rect, Rect rect2, int n5) {
        IMPL.apply(n2, n3, n4, rect, rect2, n5);
    }

    public static void applyDisplay(int n2, Rect rect, Rect rect2, int n3) {
        IMPL.applyDisplay(n2, rect, rect2, n3);
    }

    public static int getAbsoluteGravity(int n2, int n3) {
        return IMPL.getAbsoluteGravity(n2, n3);
    }

    static interface GravityCompatImpl {
        public void apply(int var1, int var2, int var3, Rect var4, int var5, int var6, Rect var7, int var8);

        public void apply(int var1, int var2, int var3, Rect var4, Rect var5, int var6);

        public void applyDisplay(int var1, Rect var2, Rect var3, int var4);

        public int getAbsoluteGravity(int var1, int var2);
    }

    static class GravityCompatImplBase
    implements GravityCompatImpl {
        GravityCompatImplBase() {
        }

        @Override
        public void apply(int n2, int n3, int n4, Rect rect, int n5, int n6, Rect rect2, int n7) {
            Gravity.apply((int)n2, (int)n3, (int)n4, (Rect)rect, (int)n5, (int)n6, (Rect)rect2);
        }

        @Override
        public void apply(int n2, int n3, int n4, Rect rect, Rect rect2, int n5) {
            Gravity.apply((int)n2, (int)n3, (int)n4, (Rect)rect, (Rect)rect2);
        }

        @Override
        public void applyDisplay(int n2, Rect rect, Rect rect2, int n3) {
            Gravity.applyDisplay((int)n2, (Rect)rect, (Rect)rect2);
        }

        @Override
        public int getAbsoluteGravity(int n2, int n3) {
            return -8388609 & n2;
        }
    }

    static class GravityCompatImplJellybeanMr1
    implements GravityCompatImpl {
        GravityCompatImplJellybeanMr1() {
        }

        @Override
        public void apply(int n2, int n3, int n4, Rect rect, int n5, int n6, Rect rect2, int n7) {
            GravityCompatJellybeanMr1.apply(n2, n3, n4, rect, n5, n6, rect2, n7);
        }

        @Override
        public void apply(int n2, int n3, int n4, Rect rect, Rect rect2, int n5) {
            GravityCompatJellybeanMr1.apply(n2, n3, n4, rect, rect2, n5);
        }

        @Override
        public void applyDisplay(int n2, Rect rect, Rect rect2, int n3) {
            GravityCompatJellybeanMr1.applyDisplay(n2, rect, rect2, n3);
        }

        @Override
        public int getAbsoluteGravity(int n2, int n3) {
            return GravityCompatJellybeanMr1.getAbsoluteGravity(n2, n3);
        }
    }

}

