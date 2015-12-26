/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package com.xiaomi.channel.commonutils.string;

public class Base64Coder {
    private static char[] map1;
    private static byte[] map2;
    private static final String systemLineSeparator;

    static {
        systemLineSeparator = System.getProperty((String)"line.separator");
        map1 = new char[64];
        int n = 65;
        int n2 = 0;
        while (n <= 90) {
            Base64Coder.map1[n2] = n;
            n = (char)(n + 1);
            ++n2;
        }
        n = 97;
        while (n <= 122) {
            Base64Coder.map1[n2] = n;
            n = (char)(n + 1);
            ++n2;
        }
        n = 48;
        while (n <= 57) {
            Base64Coder.map1[n2] = n;
            n = (char)(n + 1);
            ++n2;
        }
        char[] arrc = map1;
        int n3 = n2 + 1;
        arrc[n2] = 43;
        Base64Coder.map1[n3] = 47;
        map2 = new byte[128];
        for (n2 = 0; n2 < map2.length; ++n2) {
            Base64Coder.map2[n2] = -1;
        }
        for (n2 = 0; n2 < 64; ++n2) {
            Base64Coder.map2[Base64Coder.map1[n2]] = (byte)n2;
        }
    }

    private Base64Coder() {
    }

    public static byte[] decode(String string2) {
        return Base64Coder.decode(string2.toCharArray());
    }

    public static byte[] decode(char[] arrc) {
        return Base64Coder.decode(arrc, 0, arrc.length);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static byte[] decode(char[] arrc, int n, int n2) {
        int n3;
        if (n2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        for (n3 = n2; n3 > 0 && arrc[n + n3 - 1] == '='; --n3) {
        }
        int n4 = n3 * 3 / 4;
        byte[] arrby = new byte[n4];
        int n5 = n + n3;
        n2 = 0;
        while (n < n5) {
            int n6;
            int n7;
            n3 = n + 1;
            char c2 = arrc[n];
            n = n3 + 1;
            char c3 = arrc[n3];
            if (n < n5) {
                n3 = arrc[n];
                ++n;
            } else {
                n3 = 65;
            }
            if (n < n5) {
                n6 = n + 1;
                n7 = arrc[n];
                n = n6;
                n6 = n7;
            } else {
                n6 = 65;
            }
            if (c2 > '' || c3 > '' || n3 > 127 || n6 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            c2 = map2[c2];
            c3 = map2[c3];
            n7 = map2[n3];
            n6 = map2[n6];
            if (c2 < '\u0000' || c3 < '\u0000' || n7 < 0 || n6 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            n3 = n2 + 1;
            arrby[n2] = (byte)(c2 << 2 | c3 >>> 4);
            if (n3 < n4) {
                n2 = n3 + 1;
                arrby[n3] = (byte)((c3 & 15) << 4 | n7 >>> 2);
            } else {
                n2 = n3;
            }
            if (n2 >= n4) continue;
            n3 = n2 + 1;
            arrby[n2] = (byte)((n7 & 3) << 6 | n6);
            n2 = n3;
        }
        return arrby;
    }

    public static char[] encode(byte[] arrby) {
        return Base64Coder.encode(arrby, 0, arrby.length);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static char[] encode(byte[] arrby, int n, int n2) {
        int n3 = (n2 * 4 + 2) / 3;
        char[] arrc = new char[(n2 + 2) / 3 * 4];
        int n4 = n + n2;
        n2 = 0;
        while (n < n4) {
            int n5;
            int n6;
            int n7 = n + 1;
            int n8 = arrby[n] & 255;
            if (n7 < n4) {
                n = n7 + 1;
                n7 = arrby[n7] & 255;
            } else {
                n6 = 0;
                n = n7;
                n7 = n6;
            }
            if (n < n4) {
                n6 = n + 1;
                n5 = arrby[n] & 255;
                n = n6;
                n6 = n5;
            } else {
                n6 = 0;
            }
            n5 = n2 + 1;
            arrc[n2] = map1[n8 >>> 2];
            n2 = n5 + 1;
            arrc[n5] = map1[(n8 & 3) << 4 | n7 >>> 4];
            int n9 = n2 < n3 ? map1[(n7 & 15) << 2 | n6 >>> 6] : 61;
            arrc[n2] = n9;
            n9 = ++n2 < n3 ? map1[n6 & 63] : 61;
            arrc[n2] = n9;
            ++n2;
        }
        return arrc;
    }
}

