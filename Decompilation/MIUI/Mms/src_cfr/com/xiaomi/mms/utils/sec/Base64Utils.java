/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Base64
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.utils.sec;

import android.util.Base64;
import java.io.UnsupportedEncodingException;

public class Base64Utils {
    public static byte[] decryptToByte(String string2) throws UnsupportedEncodingException {
        return Base64.decode((byte[])string2.getBytes("UTF-8"), (int)2);
    }

    public static String encrypt(String string2) throws UnsupportedEncodingException {
        return Base64Utils.encrypt(string2.getBytes("UTF-8"));
    }

    public static String encrypt(byte[] arrby) throws UnsupportedEncodingException {
        return new String(Base64.encode((byte[])arrby, (int)2), "UTF-8");
    }
}

