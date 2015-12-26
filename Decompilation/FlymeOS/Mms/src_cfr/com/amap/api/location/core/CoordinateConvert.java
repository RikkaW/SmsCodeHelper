/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Double
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package com.amap.api.location.core;

import com.amap.api.location.core.GeoPoint;

public class CoordinateConvert {
    public static GeoPoint fromGpsToAMap(double d2, double d3) {
        try {
            Object object = aia.a(d3, d2);
            object = new GeoPoint((int)(object[1] * 1000000.0), (int)(object[0] * 1000000.0));
            return object;
        }
        catch (Throwable var4_3) {
            var4_3.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static double[] fromSeveralGpsToAMap(String string2) {
        int n2;
        String[] arrstring;
        double[] arrd;
        int n3 = 0;
        try {
            arrstring = string2.split(",");
            n2 = arrstring.length;
            arrd = new double[n2];
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        do {
            string2 = (String)arrd;
            if (n3 >= n2 / 2) return string2;
            string2 = (String)aia.a(Double.parseDouble((String)arrstring[n3 * 2]), Double.parseDouble((String)arrstring[n3 * 2 + 1]));
            arrd[n3 * 2] = string2[0];
            arrd[n3 * 2 + 1] = string2[1];
            ++n3;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static double[] fromSeveralGpsToAMap(double[] arrd) {
        int n2;
        double[] arrd2;
        int n3 = 0;
        try {
            n2 = arrd.length;
            arrd2 = new double[n2];
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        do {
            Object object = arrd2;
            if (n3 >= n2 / 2) return object;
            object = aia.a(arrd[n3 * 2], arrd[n3 * 2 + 1]);
            arrd2[n3 * 2] = object[0];
            arrd2[n3 * 2 + 1] = object[1];
            ++n3;
        } while (true);
    }
}

