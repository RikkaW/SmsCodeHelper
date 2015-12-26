/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.base;

public final class Ascii {
    private Ascii() {
    }

    public static boolean isUpperCase(char c2) {
        if (c2 >= 'A' && c2 <= 'Z') {
            return true;
        }
        return false;
    }

    public static char toLowerCase(char c2) {
        char c3 = c2;
        if (Ascii.isUpperCase(c2)) {
            c3 = (char)(c2 ^ 32);
        }
        return c3;
    }

    public static String toLowerCase(String string2) {
        int n = string2.length();
        StringBuilder stringBuilder = new StringBuilder(n);
        for (int i = 0; i < n; ++i) {
            stringBuilder.append(Ascii.toLowerCase(string2.charAt(i)));
        }
        return stringBuilder.toString();
    }
}

