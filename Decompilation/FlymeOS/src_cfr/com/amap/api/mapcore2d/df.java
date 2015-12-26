/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.security.MessageDigest
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.di;
import com.amap.api.mapcore2d.ed;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class df {
    public static String a(String string2) {
        if (string2 == null) {
            return null;
        }
        return di.c(df.c(string2));
    }

    public static String a(byte[] arrby) {
        return di.c(df.b(arrby));
    }

    public static String b(String string2) {
        return di.d(df.d(string2));
    }

    private static byte[] b(byte[] arrby) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            messageDigest.update(arrby);
            arrby = messageDigest.digest();
            return arrby;
        }
        catch (NoSuchAlgorithmException var0_1) {
            ed.a(var0_1, "MD5", "getMd5Bytes");
            var0_1.printStackTrace();
            return null;
        }
        catch (Throwable var0_2) {
            ed.a(var0_2, "MD5", "getMd5Bytes1");
            var0_2.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] c(String string2) {
        try {
            return df.e(string2);
        }
        catch (NoSuchAlgorithmException var0_2) {
            ed.a(var0_2, "MD5", "getMd5Bytes");
            var0_2.printStackTrace();
            do {
                return new byte[0];
                break;
            } while (true);
        }
        catch (UnsupportedEncodingException var0_3) {
            ed.a(var0_3, "MD5", "getMd5Bytes");
            var0_3.printStackTrace();
            return new byte[0];
        }
        catch (Throwable var0_4) {
            ed.a(var0_4, "MD5", "getMd5Bytes");
            var0_4.printStackTrace();
            return new byte[0];
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static byte[] d(String string2) {
        try {
            return df.e(string2);
        }
        catch (NoSuchAlgorithmException var0_2) {
            var0_2.printStackTrace();
            do {
                return new byte[0];
                break;
            } while (true);
        }
        catch (UnsupportedEncodingException var0_3) {
            var0_3.printStackTrace();
            return new byte[0];
        }
        catch (Throwable var0_4) {
            var0_4.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] e(String string2) {
        if (string2 == null) {
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
        messageDigest.update(string2.getBytes("utf-8"));
        return messageDigest.digest();
    }
}

