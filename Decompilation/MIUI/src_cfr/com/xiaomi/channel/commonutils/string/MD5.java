/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.security.MessageDigest
 */
package com.xiaomi.channel.commonutils.string;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String MD5_16(String string2) {
        return MD5.MD5_32(string2).subSequence(8, 24).toString();
    }

    public static String MD5_32(String string2) {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance((String)"MD5");
        }
        catch (NoSuchAlgorithmException var0_1) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        messageDigest.update(string2.getBytes(), 0, string2.length());
        string2 = (String)messageDigest.digest();
        for (int i = 0; i < string2.length; ++i) {
            stringBuffer.append(MD5.byte2Hex((byte)string2[i]));
        }
        return stringBuffer.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static String byte2Hex(byte by) {
        String string2;
        int n = by < 0 ? 128 : 0;
        by = (byte)((by & 127) + n);
        StringBuilder stringBuilder = new StringBuilder();
        if (by < 16) {
            string2 = "0";
            return stringBuilder.append(string2).append(Integer.toHexString((int)by).toLowerCase()).toString();
        }
        string2 = "";
        return stringBuilder.append(string2).append(Integer.toHexString((int)by).toLowerCase()).toString();
    }
}

