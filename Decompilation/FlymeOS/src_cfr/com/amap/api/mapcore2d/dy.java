/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.de;

class dy {
    dy() {
    }

    static String a(String string2) {
        if (string2 == null) {
            return null;
        }
        string2 = de.b(string2.getBytes());
        return "" + (char)(string2.length() % 26 + 65) + string2;
    }

    static String b(String string2) {
        if (string2.length() < 2) {
            return "";
        }
        return de.a(string2.substring(1));
    }
}

