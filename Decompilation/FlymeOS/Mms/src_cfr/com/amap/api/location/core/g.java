/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.security.MessageDigest
 */
package com.amap.api.location.core;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class g {
    private static final String[] a = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String a(String string2) {
        MessageDigest messageDigest;
        Object var2_2 = null;
        try {
            messageDigest = MessageDigest.getInstance((String)"MD5");
        }
        catch (NoSuchAlgorithmException var1_4) {
            var1_4.printStackTrace();
            messageDigest = null;
        }
        if (messageDigest != null) {
            try {
                messageDigest.update(string2.getBytes("utf-8"));
            }
            catch (UnsupportedEncodingException var0_1) {
                var0_1.printStackTrace();
            }
        }
        string2 = var2_2;
        if (messageDigest != null) {
            string2 = (String)messageDigest.digest();
        }
        return g.a((byte[])string2);
    }

    public static String a(byte[] arrby) {
        if (arrby == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            String string2;
            String string3 = string2 = Integer.toHexString((int)(arrby[i2] & 255));
            if (string2.length() == 1) {
                string3 = "" + '0' + string2;
            }
            stringBuilder.append(string3);
        }
        return stringBuilder.toString();
    }
}

