/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 */
package android.support.v4.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.graphics.BitmapCompatHoneycombMr1;
import android.support.v4.graphics.BitmapCompatJellybeanMR2;
import android.support.v4.graphics.BitmapCompatKitKat;

public class BitmapCompat {
    static final BitmapImpl IMPL;

    static {
        int n2 = Build.VERSION.SDK_INT;
        IMPL = n2 >= 19 ? new KitKatBitmapCompatImpl() : (n2 >= 18 ? new JbMr2BitmapCompatImpl() : (n2 >= 12 ? new HcMr1BitmapCompatImpl() : new BaseBitmapImpl()));
    }

    public static int getAllocationByteCount(Bitmap bitmap) {
        return IMPL.getAllocationByteCount(bitmap);
    }

    public static boolean hasMipMap(Bitmap bitmap) {
        return IMPL.hasMipMap(bitmap);
    }

    public static void setHasMipMap(Bitmap bitmap, boolean bl2) {
        IMPL.setHasMipMap(bitmap, bl2);
    }

    static class BaseBitmapImpl
    implements BitmapImpl {
        BaseBitmapImpl() {
        }

        @Override
        public int getAllocationByteCount(Bitmap bitmap) {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }

        @Override
        public boolean hasMipMap(Bitmap bitmap) {
            return false;
        }

        @Override
        public void setHasMipMap(Bitmap bitmap, boolean bl2) {
        }
    }

    static interface BitmapImpl {
        public int getAllocationByteCount(Bitmap var1);

        public boolean hasMipMap(Bitmap var1);

        public void setHasMipMap(Bitmap var1, boolean var2);
    }

    static class HcMr1BitmapCompatImpl
    extends BaseBitmapImpl {
        HcMr1BitmapCompatImpl() {
        }

        @Override
        public int getAllocationByteCount(Bitmap bitmap) {
            return BitmapCompatHoneycombMr1.getAllocationByteCount(bitmap);
        }
    }

    static class JbMr2BitmapCompatImpl
    extends HcMr1BitmapCompatImpl {
        JbMr2BitmapCompatImpl() {
        }

        @Override
        public boolean hasMipMap(Bitmap bitmap) {
            return BitmapCompatJellybeanMR2.hasMipMap(bitmap);
        }

        @Override
        public void setHasMipMap(Bitmap bitmap, boolean bl2) {
            BitmapCompatJellybeanMR2.setHasMipMap(bitmap, bl2);
        }
    }

    static class KitKatBitmapCompatImpl
    extends JbMr2BitmapCompatImpl {
        KitKatBitmapCompatImpl() {
        }

        @Override
        public int getAllocationByteCount(Bitmap bitmap) {
            return BitmapCompatKitKat.getAllocationByteCount(bitmap);
        }
    }

}

