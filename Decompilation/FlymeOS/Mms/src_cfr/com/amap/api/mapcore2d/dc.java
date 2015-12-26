/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.Map$Entry
 */
package com.amap.api.mapcore2d;

import android.content.Context;
import android.os.Build;
import com.amap.api.mapcore2d.da;
import com.amap.api.mapcore2d.dd;
import com.amap.api.mapcore2d.de;
import com.amap.api.mapcore2d.df;
import com.amap.api.mapcore2d.dh;
import com.amap.api.mapcore2d.di;
import com.amap.api.mapcore2d.ed;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class dc {
    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a() {
        String string2;
        try {
            string2 = String.valueOf((long)System.currentTimeMillis());
        }
        catch (Throwable var2_3) {
            void var2_4;
            string2 = null;
            ed.a((Throwable)var2_4, "CInfo", "getTS");
            var2_4.printStackTrace();
            return string2;
        }
        int n2 = string2.length();
        return string2.substring(0, n2 - 2) + "1" + string2.substring(n2 - 1);
        {
            catch (Throwable throwable) {}
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(da.f(context)).append("\",\"ct\":\"android\",\"ime\":\"").append(dd.m(context)).append("\",\"pkg\":\"").append(da.b(context)).append("\",\"mod\":\"").append(Build.MODEL).append("\",\"apn\":\"").append(da.a(context)).append("\",\"apv\":\"").append(da.c(context)).append("\",\"sv\":\"").append(Build.VERSION.RELEASE).append("\",");
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "CInfo", "getPublicJSONInfo");
            var0_1.printStackTrace();
            return stringBuilder.toString();
        }
        do {
            return stringBuilder.toString();
            break;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context context, dh dh2) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String string2 = dd.q(context);
            stringBuilder.append("\"sim\":\"").append(string2).append("\",\"av\":\"").append(dh2.a).append("\",\"pro\":\"").append(dh2.c).append("\",\"ed\":\"").append(dh2.d()).append("\",\"nt\":\"").append(dd.k(context)).append("\",\"np\":\"").append(dd.p(context)).append("\",\"mnc\":\"").append(dd.e(context)).append("\",\"lnt\":\"").append(dd.f(context)).append("\",\"ant\":\"").append(dd.h(context)).append("\"");
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return stringBuilder.toString();
        }
        do {
            return stringBuilder.toString();
            break;
        } while (true);
    }

    public static String a(Context object, dh object2, Map<String, String> map) {
        block3 : {
            try {
                object2 = dc.b((Context)object, (dh)object2, map);
                if (!"".equals(object2)) break block3;
                return null;
            }
            catch (UnsupportedEncodingException var0_1) {
                ed.a(var0_1, "CInfo", "rsaInfo");
                var0_1.printStackTrace();
                return null;
            }
        }
        object = dc.b((Context)object, object2.getBytes("utf-8"));
        return object;
    }

    public static String a(Context object, String string2, String string3) {
        try {
            object = da.d((Context)object);
            object = df.a((String)object + ":" + string2.substring(0, string2.length() - 3) + ":" + string3);
            return object;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "CInfo", "Scode");
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(Context object, byte[] arrby) {
        try {
            return dc.c((Context)object, arrby);
        }
        catch (InvalidKeyException var0_1) {
            var0_1.printStackTrace();
            do {
                return "";
                break;
            } while (true);
        }
        catch (NoSuchAlgorithmException var0_2) {
            var0_2.printStackTrace();
            return "";
        }
        catch (NoSuchPaddingException var0_3) {
            var0_3.printStackTrace();
            return "";
        }
        catch (IllegalBlockSizeException var0_4) {
            var0_4.printStackTrace();
            return "";
        }
        catch (BadPaddingException var0_5) {
            var0_5.printStackTrace();
            return "";
        }
        catch (InvalidKeySpecException var0_6) {
            var0_6.printStackTrace();
            return "";
        }
        catch (CertificateException var0_7) {
            var0_7.printStackTrace();
            return "";
        }
        catch (IOException var0_8) {
            var0_8.printStackTrace();
            return "";
        }
        catch (Throwable var0_9) {
            var0_9.printStackTrace();
            return "";
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String b(Context iterator, dh object, Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            void var1_6;
            void var2_10;
            String string2 = dd.m(iterator);
            stringBuilder.append("ct=android");
            stringBuilder.append("&ime=").append(string2);
            stringBuilder.append("&pkg=").append(da.b(iterator));
            stringBuilder.append("&mod=");
            stringBuilder.append(Build.MODEL);
            stringBuilder.append("&apn=").append(da.a(iterator));
            stringBuilder.append("&apv=").append(da.c(iterator));
            stringBuilder.append("&sv=");
            stringBuilder.append(Build.VERSION.RELEASE);
            string2 = dd.n(iterator);
            stringBuilder.append("&sim=").append(string2);
            stringBuilder.append("&av=").append(object.a);
            stringBuilder.append("&pro=").append(object.c);
            stringBuilder.append("&cid=").append(dd.d(iterator));
            stringBuilder.append("&dmac=").append(dd.c(iterator));
            stringBuilder.append("&wmac=").append(dd.b(iterator));
            stringBuilder.append("&nt=");
            stringBuilder.append(dd.l(iterator));
            String string3 = dd.o(iterator);
            stringBuilder.append("&np=");
            stringBuilder.append(string3);
            stringBuilder.append("&ia=1&");
            String string4 = string2 = dd.a(iterator);
            if (string2 == null) {
                String string5 = "";
            }
            stringBuilder.append("utd=").append((String)var1_6).append("&");
            String string6 = da.f(iterator);
            if (string6 != null && string6.length() > 0) {
                stringBuilder.append("key=");
                stringBuilder.append(string6);
                stringBuilder.append("&");
            }
            stringBuilder.append("ctm=" + System.currentTimeMillis());
            stringBuilder.append("&re=" + dd.j(iterator));
            if (var2_10 == null) return stringBuilder.toString();
            {
                for (Map.Entry entry : var2_10.entrySet()) {
                    stringBuilder.append("&").append((String)entry.getKey()).append("=").append((String)entry.getValue());
                }
                return stringBuilder.toString();
            }
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "CInfo", "InitXInfo");
            var0_1.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String b(Context object, byte[] arrby) {
        try {
            return dc.c((Context)object, arrby);
        }
        catch (InvalidKeyException var0_1) {
            ed.a(var0_1, "CInfo", "AESData");
            var0_1.printStackTrace();
            do {
                return "";
                break;
            } while (true);
        }
        catch (NoSuchAlgorithmException var0_2) {
            ed.a(var0_2, "CInfo", "AESData");
            var0_2.printStackTrace();
            return "";
        }
        catch (NoSuchPaddingException var0_3) {
            ed.a(var0_3, "CInfo", "AESData");
            var0_3.printStackTrace();
            return "";
        }
        catch (IllegalBlockSizeException var0_4) {
            ed.a(var0_4, "CInfo", "AESData");
            var0_4.printStackTrace();
            return "";
        }
        catch (BadPaddingException var0_5) {
            ed.a(var0_5, "CInfo", "AESData");
            var0_5.printStackTrace();
            return "";
        }
        catch (InvalidKeySpecException var0_6) {
            ed.a(var0_6, "CInfo", "AESData");
            var0_6.printStackTrace();
            return "";
        }
        catch (CertificateException var0_7) {
            ed.a(var0_7, "CInfo", "AESData");
            var0_7.printStackTrace();
            return "";
        }
        catch (IOException var0_8) {
            ed.a(var0_8, "CInfo", "AESData");
            var0_8.printStackTrace();
            return "";
        }
        catch (Throwable var0_9) {
            ed.a(var0_9, "CInfo", "AESData");
            var0_9.printStackTrace();
            return "";
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static String c(Context object, byte[] arrby) {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        if (keyGenerator == null) {
            return null;
        }
        keyGenerator.init(256);
        byte[] arrby2 = keyGenerator.generateKey().getEncoded();
        if ((object = di.a((Context)object)) == null) return null;
        object = de.a(arrby2, (Key)object);
        arrby = de.a(arrby2, arrby);
        byte[] arrby3 = new byte[object.length + arrby.length];
        System.arraycopy((Object)object, (int)0, (Object)arrby3, (int)0, (int)object.length);
        System.arraycopy((Object)arrby, (int)0, (Object)arrby3, (int)object.length, (int)arrby.length);
        object = di.a(arrby3);
        if (object == null) return "";
        return de.a((byte[])object);
    }
}

