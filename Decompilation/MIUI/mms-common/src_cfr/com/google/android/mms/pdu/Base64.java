/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.System
 */
package com.google.android.mms.pdu;

public class Base64 {
    static final int BASELENGTH = 255;
    static final int FOURBYTE = 4;
    static final byte PAD = 61;
    private static byte[] base64Alphabet;

    static {
        int n;
        base64Alphabet = new byte[255];
        for (n = 0; n < 255; ++n) {
            Base64.base64Alphabet[n] = -1;
        }
        for (n = 90; n >= 65; --n) {
            Base64.base64Alphabet[n] = (byte)(n - 65);
        }
        for (n = 122; n >= 97; --n) {
            Base64.base64Alphabet[n] = (byte)(n - 97 + 26);
        }
        for (n = 57; n >= 48; --n) {
            Base64.base64Alphabet[n] = (byte)(n - 48 + 52);
        }
        Base64.base64Alphabet[43] = 62;
        Base64.base64Alphabet[47] = 63;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static byte[] decodeBase64(byte[] arrby) {
        int n;
        byte[] arrby2 = Base64.discardNonBase64(arrby);
        if (arrby2.length == 0) {
            return new byte[0];
        }
        int n2 = arrby2.length / 4;
        int n3 = 0;
        int n4 = arrby2.length;
        while (arrby2[n4 - 1] == 61) {
            n4 = n = n4 - 1;
            if (n != 0) continue;
            return new byte[0];
        }
        byte[] arrby3 = new byte[n4 - n2];
        n4 = 0;
        do {
            arrby = arrby3;
            if (n4 >= n2) return arrby;
            int n5 = n4 * 4;
            byte by = arrby2[n5 + 2];
            byte by2 = arrby2[n5 + 3];
            n = base64Alphabet[arrby2[n5]];
            n5 = base64Alphabet[arrby2[n5 + 1]];
            if (by != 61 && by2 != 61) {
                by = base64Alphabet[by];
                by2 = base64Alphabet[by2];
                arrby3[n3] = (byte)(n << 2 | n5 >> 4);
                arrby3[n3 + 1] = (byte)((n5 & 15) << 4 | by >> 2 & 15);
                arrby3[n3 + 2] = (byte)(by << 6 | by2);
            } else if (by == 61) {
                arrby3[n3] = (byte)(n << 2 | n5 >> 4);
            } else if (by2 == 61) {
                by2 = base64Alphabet[by];
                arrby3[n3] = (byte)(n << 2 | n5 >> 4);
                arrby3[n3 + 1] = (byte)((n5 & 15) << 4 | by2 >> 2 & 15);
            }
            n3 += 3;
            ++n4;
        } while (true);
    }

    static byte[] discardNonBase64(byte[] arrby) {
        byte[] arrby2 = new byte[arrby.length];
        int n = 0;
        for (int i = 0; i < arrby.length; ++i) {
            int n2 = n;
            if (Base64.isBase64(arrby[i])) {
                arrby2[n] = arrby[i];
                n2 = n + 1;
            }
            n = n2;
        }
        arrby = new byte[n];
        System.arraycopy((Object)arrby2, (int)0, (Object)arrby, (int)0, (int)n);
        return arrby;
    }

    /*
     * Enabled aggressive block sorting
     */
    private static boolean isBase64(byte by) {
        if (by == 61 || base64Alphabet[by] != -1) {
            return true;
        }
        return false;
    }
}

