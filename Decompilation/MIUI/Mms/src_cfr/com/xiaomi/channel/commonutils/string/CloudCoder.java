/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.net.Uri
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.security.MessageDigest
 *  java.util.ArrayList
 *  java.util.Map$Entry
 */
package com.xiaomi.channel.commonutils.string;

import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class CloudCoder {
    public static String generateSignature(String object, String object22, Map<String, String> object32, String string2) {
        void var3_6;
        String string32;
        if (TextUtils.isEmpty((CharSequence)var3_6)) {
            throw new InvalidParameterException("security is not nullable");
        }
        ArrayList arrayList = new ArrayList();
        if (object != null) {
            arrayList.add(object.toUpperCase());
        }
        if (object22 != null) {
            arrayList.add(Uri.parse((String)object22).getEncodedPath());
        }
        if (string32 != null && !string32.isEmpty()) {
            for (Map.Entry entry : new TreeMap(string32).entrySet()) {
                arrayList.add(String.format((String)"%s=%s", (Object[])new Object[]{entry.getKey(), entry.getValue()}));
            }
        }
        arrayList.add(var3_6);
        boolean bl = true;
        object = new StringBuilder();
        for (String string32 : arrayList) {
            if (!bl) {
                object.append('&');
            }
            object.append(string32);
            bl = false;
        }
        return CloudCoder.hash4SHA1(object.toString());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String hash4SHA1(String string2) {
        try {
            return String.valueOf((char[])Base64Coder.encode(MessageDigest.getInstance((String)"SHA1").digest(string2.getBytes("UTF-8"))));
        }
        catch (NoSuchAlgorithmException var0_1) {
            MyLog.e("CloudCoder.hash4SHA1 ", var0_1);
            do {
                throw new IllegalStateException("failed to SHA1");
                break;
            } while (true);
        }
        catch (UnsupportedEncodingException var0_2) {
            MyLog.e("CloudCoder.hash4SHA1 ", var0_2);
            throw new IllegalStateException("failed to SHA1");
        }
        catch (Exception var0_3) {
            MyLog.e("CloudCoder.hash4SHA1 ", var0_3);
            throw new IllegalStateException("failed to SHA1");
        }
    }
}

