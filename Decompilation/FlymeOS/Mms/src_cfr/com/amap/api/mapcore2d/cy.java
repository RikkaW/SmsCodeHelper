/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.net.ConnectivityManager
 *  android.net.NetworkInfo
 *  android.net.NetworkInfo$State
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Arrays
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.amap.api.mapcore2d.ae;
import com.amap.api.mapcore2d.ed;
import com.amap.api.mapcore2d.y;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class cy {
    public static double[] a = new double[]{7453.642, 3742.9905, 1873.333, 936.89026, 468.472, 234.239, 117.12, 58.56, 29.28, 14.64, 7.32, 3.66, 1.829, 0.915, 0.4575, 0.228, 0.1144};

    public static double a(LatLng latLng, LatLng latLng2) {
        double d2 = latLng.longitude;
        double d3 = latLng.latitude;
        double d4 = latLng2.longitude;
        double d5 = latLng2.latitude;
        double d6 = d2 * 0.01745329251994329;
        double d7 = d3 * 0.01745329251994329;
        d3 = d4 * 0.01745329251994329;
        d2 = d5 * 0.01745329251994329;
        d5 = Math.sin((double)d6);
        d4 = Math.sin((double)d7);
        d6 = Math.cos((double)d6);
        d7 = Math.cos((double)d7);
        double d8 = Math.sin((double)d3);
        double d9 = Math.sin((double)d2);
        d3 = Math.cos((double)d3);
        d2 = Math.cos((double)d2);
        latLng = (LatLng)new double[3];
        latLng2 = (LatLng)new double[3];
        latLng[0] = (LatLng)(d6 * d7);
        latLng[1] = (LatLng)(d7 * d5);
        latLng[2] = (LatLng)d4;
        latLng2[0] = (LatLng)(d2 * d3);
        latLng2[1] = (LatLng)(d2 * d8);
        latLng2[2] = (LatLng)d9;
        return Math.asin((double)(Math.sqrt((double)((latLng[0] - latLng2[0]) * (latLng[0] - latLng2[0]) + (latLng[1] - latLng2[1]) * (latLng[1] - latLng2[1]) + (latLng[2] - latLng2[2]) * (latLng[2] - latLng2[2]))) / 2.0)) * 1.27420015798544E7;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float a(float f2) {
        if (f2 < 0.0f) {
            return 0.0f;
        }
        float f3 = f2;
        if (f2 <= 45.0f) return f3;
        return 45.0f;
    }

    public static int a(int n2) {
        int n3 = (int)(Math.log((double)n2) / Math.log((double)2.0));
        if (1 << n3 >= n2) {
            return 1 << n3;
        }
        return 1 << n3 + 1;
    }

    public static int a(Object[] arrobject) {
        return Arrays.hashCode((Object[])arrobject);
    }

    public static Bitmap a(Bitmap bitmap, float f2) {
        if (bitmap == null) {
            return null;
        }
        return Bitmap.createScaledBitmap((Bitmap)bitmap, (int)((int)((float)bitmap.getWidth() * f2)), (int)((int)((float)bitmap.getHeight() * f2)), (boolean)true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Bitmap a(Bitmap bitmap, int n2, int n3) {
        Bitmap.Config config = bitmap.hasAlpha() ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        config = Bitmap.createBitmap((int)n2, (int)n3, (Bitmap.Config)config);
        Canvas canvas = new Canvas((Bitmap)config);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return config;
    }

    public static Bitmap a(String object) {
        try {
            object = BitmapDescriptorFactory.class.getResourceAsStream("/assets/" + (String)object);
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)object);
            object.close();
            return bitmap;
        }
        catch (Exception var0_1) {
            cy.a(var0_1, "Util", "fromAsset");
            return null;
        }
    }

    public static ae a(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return new ae((int)(latLng.latitude * 1000000.0), (int)(latLng.longitude * 1000000.0));
    }

    public static String a(String string2, Object object) {
        return string2 + "=" + String.valueOf((Object)object);
    }

    public static /* varargs */ String a(String ... arrstring) {
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = arrstring.length;
        int n3 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            stringBuilder.append(arrstring[i2]);
            if (n3 != arrstring.length - 1) {
                stringBuilder.append(",");
            }
            ++n3;
        }
        return stringBuilder.toString();
    }

    public static void a(Throwable throwable, String string2, String string3) {
        ed ed2 = ed.b();
        if (ed2 != null) {
            ed2.b(throwable, string2, string3);
        }
        throwable.printStackTrace();
    }

    public static boolean a() {
        if (Build.VERSION.SDK_INT >= 8) {
            return true;
        }
        return false;
    }

    public static boolean a(double d2, double d3, double d4, double d5, double d6, double d7) {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (Math.abs((double)cy.b(d2, d3, d4, d5, d6, d7)) < 1.0E-9) {
            bl3 = bl2;
            if ((d2 - d4) * (d2 - d6) <= 0.0) {
                bl3 = bl2;
                if ((d3 - d5) * (d3 - d7) <= 0.0) {
                    bl3 = true;
                }
            }
        }
        return bl3;
    }

    public static boolean a(double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9) {
        boolean bl2 = false;
        double d10 = (d4 - d2) * (d9 - d7) - (d5 - d3) * (d8 - d6);
        boolean bl3 = bl2;
        if (d10 != 0.0) {
            d8 = ((d3 - d7) * (d8 - d6) - (d2 - d6) * (d9 - d7)) / d10;
            d2 = ((d3 - d7) * (d4 - d2) - (d2 - d6) * (d5 - d3)) / d10;
            bl3 = bl2;
            if (d8 >= 0.0) {
                bl3 = bl2;
                if (d8 <= 1.0) {
                    bl3 = bl2;
                    if (d2 >= 0.0) {
                        bl3 = bl2;
                        if (d2 <= 1.0) {
                            bl3 = true;
                        }
                    }
                }
            }
        }
        return bl3;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean a(Context context) {
        synchronized (cy.class) {
            block8 : {
                block7 : {
                    if (context == null) {
                        return false;
                    }
                    context = (ConnectivityManager)context.getSystemService("connectivity");
                    if (context != null) break block7;
                    return false;
                }
                context = context.getActiveNetworkInfo();
                if (context != null) break block8;
                return false;
            }
            context = context.getState();
            if (context == null) return false;
            if (context == NetworkInfo.State.DISCONNECTED) return false;
            NetworkInfo.State state = NetworkInfo.State.DISCONNECTING;
            if (context != state) return true;
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean a(LatLng latLng, List<LatLng> list) {
        double d2 = latLng.longitude;
        double d3 = latLng.latitude;
        double d4 = latLng.latitude;
        int n2 = 0;
        for (int i2 = 0; i2 < list.size() - 1; ++i2) {
            double d5 = list.get((int)i2).longitude;
            double d6 = list.get((int)i2).latitude;
            double d7 = list.get((int)(i2 + 1)).longitude;
            double d8 = list.get((int)(i2 + 1)).latitude;
            if (cy.a(d2, d3, d5, d6, d7, d8)) {
                return true;
            }
            if (Math.abs((double)(d8 - d6)) < 1.0E-9) continue;
            if (cy.a(d5, d6, d2, d3, 180.0, d4)) {
                if (d6 <= d8) continue;
                ++n2;
                continue;
            }
            if (cy.a(d7, d8, d2, d3, 180.0, d4)) {
                if (d8 <= d6) continue;
                ++n2;
                continue;
            }
            if (!cy.a(d5, d6, d7, d8, d2, d3, 180.0, d4)) continue;
            ++n2;
        }
        if (n2 % 2 != 0) {
            return true;
        }
        return false;
    }

    public static double b(double d2, double d3, double d4, double d5, double d6, double d7) {
        return (d4 - d2) * (d7 - d3) - (d6 - d2) * (d5 - d3);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static float b(float f2) {
        if (f2 > (float)y.c) {
            return y.c;
        }
        float f3 = f2;
        if (f2 >= (float)y.d) return f3;
        return y.d;
    }

    public static String b(int n2) {
        if (n2 < 1000) {
            return "" + n2 + "m";
        }
        return "" + n2 / 1000 + "km";
    }

    public static boolean b() {
        if (Build.VERSION.SDK_INT >= 9) {
            return true;
        }
        return false;
    }

    public static boolean c() {
        if (Build.VERSION.SDK_INT >= 11) {
            return true;
        }
        return false;
    }

    public static boolean d() {
        if (Build.VERSION.SDK_INT >= 12) {
            return true;
        }
        return false;
    }
}

