/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.AssetManager
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.NinePatch
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.NinePatchDrawable
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

class bo {
    private bo() {
    }

    private static int a(byte[] arrby, int n2) {
        return arrby[n2 + 0] & 255 | (arrby[n2 + 1] & 255) << 8 | (arrby[n2 + 2] & 255) << 16 | (arrby[n2 + 3] & 255) << 24;
    }

    private static Bitmap a(InputStream inputStream) {
        Bitmap bitmap = BitmapFactory.decodeStream((InputStream)inputStream);
        inputStream = (InputStream)bo.a(bitmap);
        if (NinePatch.isNinePatchChunk((byte[])inputStream)) {
            Bitmap bitmap2 = Bitmap.createBitmap((Bitmap)bitmap, (int)1, (int)1, (int)(bitmap.getWidth() - 2), (int)(bitmap.getHeight() - 2));
            bitmap.recycle();
            bitmap = bitmap2.getClass().getDeclaredField("mNinePatchChunk");
            bitmap.setAccessible(true);
            bitmap.set((Object)bitmap2, (Object)inputStream);
            return bitmap2;
        }
        return bitmap;
    }

    public static Drawable a(Context context, String string2) {
        if ((string2 = bo.b(context, string2)).getNinePatchChunk() == null) {
            return new BitmapDrawable((Bitmap)string2);
        }
        Rect rect = new Rect();
        bo.a(string2.getNinePatchChunk(), rect);
        return new NinePatchDrawable(context.getResources(), (Bitmap)string2, string2.getNinePatchChunk(), rect, null);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static void a(Bitmap bitmap, byte[] arrby) {
        int n2 = 0;
        int[] arrn = new int[bitmap.getWidth() - 2];
        bitmap.getPixels(arrn, 0, arrn.length, 1, bitmap.getHeight() - 1, arrn.length, 1);
        int n3 = 0;
        do {
            if (n3 >= arrn.length) break;
            if (-16777216 == arrn[n3]) {
                bo.a(arrby, 12, n3);
                break;
            }
            ++n3;
        } while (true);
        n3 = arrn.length - 1;
        do {
            if (n3 < 0) break;
            if (-16777216 == arrn[n3]) {
                bo.a(arrby, 16, arrn.length - n3 - 2);
                break;
            }
            --n3;
        } while (true);
        arrn = new int[bitmap.getHeight() - 2];
        bitmap.getPixels(arrn, 0, 1, bitmap.getWidth() - 1, 0, 1, arrn.length);
        n3 = n2;
        do {
            if (n3 >= arrn.length) break;
            if (-16777216 == arrn[n3]) {
                bo.a(arrby, 20, n3);
                break;
            }
            ++n3;
        } while (true);
        n3 = arrn.length - 1;
        while (n3 >= 0) {
            if (-16777216 == arrn[n3]) {
                bo.a(arrby, 24, arrn.length - n3 - 2);
                return;
            }
            --n3;
        }
    }

    private static void a(OutputStream outputStream, int n2) {
        outputStream.write(n2 >> 0 & 255);
        outputStream.write(n2 >> 8 & 255);
        outputStream.write(n2 >> 16 & 255);
        outputStream.write(n2 >> 24 & 255);
    }

    private static void a(byte[] arrby, int n2, int n3) {
        arrby[n2 + 0] = (byte)(n3 >> 0);
        arrby[n2 + 1] = (byte)(n3 >> 8);
        arrby[n2 + 2] = (byte)(n3 >> 16);
        arrby[n2 + 3] = (byte)(n3 >> 24);
    }

    private static void a(byte[] arrby, Rect rect) {
        rect.left = bo.a(arrby, 12);
        rect.right = bo.a(arrby, 16);
        rect.top = bo.a(arrby, 20);
        rect.bottom = bo.a(arrby, 24);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static byte[] a(Bitmap bitmap) {
        int n2;
        int n3;
        int n4;
        int n5 = bitmap.getWidth();
        int n6 = bitmap.getHeight();
        Object object = new ByteArrayOutputStream();
        for (n3 = 0; n3 < 32; ++n3) {
            object.write(0);
        }
        int[] arrn = new int[n5 - 2];
        bitmap.getPixels(arrn, 0, n5, 1, 0, n5 - 2, 1);
        boolean bl2 = arrn[0] == -16777216;
        int n7 = arrn[arrn.length - 1] == -16777216 ? 1 : 0;
        int n8 = arrn.length;
        int n9 = 0;
        n3 = 0;
        for (n5 = 0; n5 < n8; ++n5) {
            n2 = n9;
            n4 = n3;
            if (n9 != arrn[n5]) {
                n4 = n3 + 1;
                bo.a((OutputStream)object, n5);
                n2 = arrn[n5];
            }
            n9 = n2;
            n3 = n4;
        }
        n5 = n3;
        if (n7 != 0) {
            n5 = n3 + 1;
            bo.a((OutputStream)object, arrn.length);
        }
        n3 = n5 + 1;
        if (bl2) {
            --n3;
        }
        n7 = n7 != 0 ? n3 - 1 : n3;
        arrn = new int[n6 - 2];
        bitmap.getPixels(arrn, 0, 1, 0, 1, 1, n6 - 2);
        n4 = arrn[0] == -16777216 ? 1 : 0;
        bl2 = arrn[arrn.length - 1] == -16777216;
        int n10 = arrn.length;
        n6 = 0;
        n3 = 0;
        for (n9 = 0; n9 < n10; ++n9) {
            n8 = n6;
            n2 = n3;
            if (n6 != arrn[n9]) {
                n2 = n3 + 1;
                bo.a((OutputStream)object, n9);
                n8 = arrn[n9];
            }
            n6 = n8;
            n3 = n2;
        }
        n9 = n3;
        if (bl2) {
            n9 = n3 + 1;
            bo.a((OutputStream)object, arrn.length);
        }
        n3 = n9 + 1;
        if (n4 != 0) {
            --n3;
        }
        n4 = n3;
        if (bl2) {
            n4 = n3 - 1;
        }
        n3 = 0;
        do {
            if (n3 >= n7 * n4) {
                object = object.toByteArray();
                object[0] = true;
                object[1] = (byte)n5;
                object[2] = (byte)n9;
                object[3] = (byte)(n4 * n7);
                bo.a(bitmap, (byte[])object);
                return object;
            }
            bo.a((OutputStream)object, 1);
            ++n3;
        } while (true);
    }

    private static Bitmap b(Context object, String string2) {
        object = object.getAssets().open(string2);
        string2 = bo.a((InputStream)object);
        object.close();
        return string2;
    }
}

