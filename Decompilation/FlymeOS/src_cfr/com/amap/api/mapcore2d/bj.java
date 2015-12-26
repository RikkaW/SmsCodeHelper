/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.Random
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.y;
import java.util.Random;

class bj {
    private static bj b;
    private String a = "http://tm.mapabc.com";

    private bj() {
    }

    public static bj a() {
        synchronized (bj.class) {
            if (b == null) {
                b = new bj();
            }
            bj bj2 = b;
            return bj2;
        }
    }

    public String b() {
        switch (new Random(System.currentTimeMillis()).nextInt(100000) % 4) {
            default: {
                return "";
            }
            case 0: {
                if (y.l == 2) {
                    return "http://wprd01.is.autonavi.com";
                }
                return "http://webrd01.is.autonavi.com";
            }
            case 1: {
                if (y.l == 2) {
                    return "http://wprd02.is.autonavi.com";
                }
                return "http://webrd02.is.autonavi.com";
            }
            case 2: {
                if (y.l == 2) {
                    return "http://wprd03.is.autonavi.com";
                }
                return "http://webrd03.is.autonavi.com";
            }
            case 3: 
        }
        if (y.l == 2) {
            return "http://wprd04.is.autonavi.com";
        }
        return "http://webrd04.is.autonavi.com";
    }

    public String c() {
        return this.a;
    }

    public String d() {
        switch (new Random(System.currentTimeMillis()).nextInt(100000) % 4) {
            default: {
                return "";
            }
            case 0: {
                return "http://mst01.is.autonavi.com";
            }
            case 1: {
                return "http://mst02.is.autonavi.com";
            }
            case 2: {
                return "http://mst03.is.autonavi.com";
            }
            case 3: 
        }
        return "http://mst04.is.autonavi.com";
    }
}

