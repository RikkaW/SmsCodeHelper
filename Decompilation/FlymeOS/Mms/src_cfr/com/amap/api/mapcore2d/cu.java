/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Rect
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import com.amap.api.mapcore2d.cs;
import com.amap.api.mapcore2d.cv;
import com.amap.api.mapcore2d.cw;
import java.io.FileDescriptor;

class cu
extends cv {
    private int b;
    private int c;

    cu(Context context, int n2, int n3) {
        super(context);
        this.a(n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int a(BitmapFactory.Options options, int n2, int n3) {
        int n4 = options.outHeight;
        int n5 = options.outWidth;
        int n6 = 1;
        if (n4 > n3 || n5 > n2) {
            int n7 = Math.round((float)((float)n4 / (float)n3));
            if (n7 >= (n6 = Math.round((float)((float)n5 / (float)n2)))) {
                n7 = n6;
            }
            float f2 = n5 * n4;
            float f3 = n2 * n3 * 2;
            do {
                n6 = n7;
                if (f2 / (float)(n7 * n7) <= f3) break;
                ++n7;
            } while (true);
        }
        return n6;
    }

    private Bitmap a(int n2) {
        cw.a("ImageResizer", "processBitmap - " + n2, 111);
        return cu.a(this.a, n2, this.b, this.c, this.a());
    }

    public static Bitmap a(Resources resources, int n2, int n3, int n4, cs cs2) {
        cs2 = new BitmapFactory.Options();
        cs2.inJustDecodeBounds = true;
        BitmapFactory.decodeResource((Resources)resources, (int)n2, (BitmapFactory.Options)cs2);
        cs2.inSampleSize = cu.a((BitmapFactory.Options)cs2, n3, n4);
        cs2.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource((Resources)resources, (int)n2, (BitmapFactory.Options)cs2);
    }

    public static Bitmap a(FileDescriptor fileDescriptor, int n2, int n3, cs cs2) {
        cs2 = new BitmapFactory.Options();
        cs2.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor((FileDescriptor)fileDescriptor, (Rect)null, (BitmapFactory.Options)cs2);
        cs2.inSampleSize = cu.a((BitmapFactory.Options)cs2, n2, n3);
        cs2.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor((FileDescriptor)fileDescriptor, (Rect)null, (BitmapFactory.Options)cs2);
    }

    @Override
    protected Bitmap a(Object object) {
        return this.a(Integer.parseInt((String)String.valueOf((Object)object)));
    }

    public void a(int n2, int n3) {
        this.b = n2;
        this.c = n3;
    }
}

