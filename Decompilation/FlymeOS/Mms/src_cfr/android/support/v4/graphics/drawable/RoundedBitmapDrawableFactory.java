/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.Rect
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.graphics.BitmapCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable21;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import java.io.InputStream;

public class RoundedBitmapDrawableFactory {
    private static final String TAG = "RoundedBitmapDrawableFactory";

    public static RoundedBitmapDrawable create(Resources resources, Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new RoundedBitmapDrawable21(resources, bitmap);
        }
        return new DefaultRoundedBitmapDrawable(resources, bitmap);
    }

    public static RoundedBitmapDrawable create(Resources object, InputStream inputStream) {
        if ((object = RoundedBitmapDrawableFactory.create((Resources)object, BitmapFactory.decodeStream((InputStream)inputStream))).getBitmap() == null) {
            Log.w((String)"RoundedBitmapDrawableFactory", (String)("BitmapDrawable cannot decode " + inputStream));
        }
        return object;
    }

    public static RoundedBitmapDrawable create(Resources object, String string2) {
        if ((object = RoundedBitmapDrawableFactory.create((Resources)object, BitmapFactory.decodeFile((String)string2))).getBitmap() == null) {
            Log.w((String)"RoundedBitmapDrawableFactory", (String)("BitmapDrawable cannot decode " + string2));
        }
        return object;
    }

    static class DefaultRoundedBitmapDrawable
    extends RoundedBitmapDrawable {
        DefaultRoundedBitmapDrawable(Resources resources, Bitmap bitmap) {
            super(resources, bitmap);
        }

        @Override
        void gravityCompatApply(int n2, int n3, int n4, Rect rect, Rect rect2) {
            GravityCompat.apply(n2, n3, n4, rect, rect2, 0);
        }

        @Override
        public boolean hasMipMap() {
            if (this.mBitmap != null && BitmapCompat.hasMipMap(this.mBitmap)) {
                return true;
            }
            return false;
        }

        @Override
        public void setMipMap(boolean bl2) {
            if (this.mBitmap != null) {
                BitmapCompat.setHasMipMap(this.mBitmap, bl2);
                this.invalidateSelf();
            }
        }
    }

}

