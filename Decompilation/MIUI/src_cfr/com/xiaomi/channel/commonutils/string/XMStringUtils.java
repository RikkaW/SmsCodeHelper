/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.math.BigInteger
 *  java.security.MessageDigest
 *  java.util.Random
 */
package com.xiaomi.channel.commonutils.string;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class XMStringUtils {
    public static String generateRandomString(int n) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    public static byte[] getBytes(String string2) {
        try {
            byte[] arrby = string2.getBytes("UTF-8");
            return arrby;
        }
        catch (UnsupportedEncodingException var1_2) {
            return string2.getBytes();
        }
    }

    public static String getMd5Digest(String string2) {
        if (string2 != null) {
            try {
                Object object = MessageDigest.getInstance((String)"MD5");
                object.update(XMStringUtils.getBytes(string2));
                object = String.format((String)"%1$032X", (Object[])new Object[]{new BigInteger(1, object.digest())});
                return object;
            }
            catch (NoSuchAlgorithmException var1_2) {
                return string2;
            }
        }
        return "";
    }

    public static String getSHA1Digest(String string2) {
        if (string2 != null) {
            try {
                Object object = MessageDigest.getInstance((String)"SHA1");
                object.update(XMStringUtils.getBytes(string2));
                object = String.format((String)"%1$032X", (Object[])new Object[]{new BigInteger(1, object.digest())});
                return object;
            }
            catch (NoSuchAlgorithmException var1_2) {
                return string2;
            }
        }
        return null;
    }

    public static String join(Collection<?> collection, String string2) {
        if (collection == null) {
            return null;
        }
        return XMStringUtils.join(collection.iterator(), string2);
    }

    public static String join(Iterator<?> iterator, String string2) {
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object obj = iterator.next();
        if (!iterator.hasNext()) {
            return obj.toString();
        }
        StringBuffer stringBuffer = new StringBuffer(256);
        if (obj != null) {
            stringBuffer.append(obj);
        }
        while (iterator.hasNext()) {
            if (string2 != null) {
                stringBuffer.append(string2);
            }
            if ((obj = iterator.next()) == null) continue;
            stringBuffer.append(obj);
        }
        return stringBuffer.toString();
    }
}

