/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.PrintStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.lang.Throwable
 *  java.security.KeyFactory
 *  java.security.MessageDigest
 *  java.security.Signature
 */
package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.a;
import java.io.PrintStream;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public final class k {
    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String a(String var0) {
        try {
            var0_1 = MessageDigest.getInstance((String)"SHA-256").digest(var0 /* !! */ .getBytes("UTF-8"));
            var3_4 = new StringBuffer();
            var1_5 = 0;
            do {
                if (var1_5 < var0_1.length) ** break block4
                return var3_4.toString();
                break;
            } while (true);
        }
        catch (Throwable var0_2) {
            System.out.println(var0_2.toString());
            var0_2.printStackTrace();
            return "";
        }
        {
            
            var2_6 = var0_1[var1_5] & 255;
            if (var2_6 >= 16) ** GOTO lbl17
            var3_4.append("0");
lbl17: // 2 sources:
            var3_4.append(Integer.toHexString((int)var2_6));
            ++var1_5;
            continue;
        }
    }

    public static String a(String string2, String string3) {
        return new String(k.a(string2.getBytes(), string3)).replaceAll("\r\n", "").replaceAll("\n", "");
    }

    private static boolean a(String string2, String string3, String string4) {
        return k.a(string2.getBytes(), string3, string4.getBytes());
    }

    private static boolean a(byte[] object, String string2, byte[] arrby) {
        boolean bl2 = false;
        try {
            object = new X509EncodedKeySpec(a.b((byte[])object));
            object = KeyFactory.getInstance((String)"RSA").generatePublic((KeySpec)object);
            arrby = a.b(arrby);
            Signature signature = Signature.getInstance((String)"SHA256WithRSA");
            signature.initVerify((PublicKey)object);
            signature.update(string2.getBytes());
            boolean bl3 = signature.verify(arrby);
            if (bl3) {
                bl2 = true;
            }
            return bl2;
        }
        catch (Throwable var0_1) {
            LogManager.e("Signaturer", "sign failed", var0_1);
            var0_1.printStackTrace();
            return false;
        }
    }

    private static byte[] a(byte[] object, String string2) {
        try {
            object = new PKCS8EncodedKeySpec(a.b((byte[])object));
            object = KeyFactory.getInstance((String)"RSA").generatePrivate((KeySpec)object);
            Signature signature = Signature.getInstance((String)"SHA256WithRSA");
            signature.initSign((PrivateKey)object);
            signature.update(string2.getBytes());
            object = a.a(signature.sign());
            return object;
        }
        catch (Throwable var0_1) {
            LogManager.e("Signaturer", "sign failed", var0_1);
            var0_1.printStackTrace();
            return null;
        }
    }

    private static byte[] a(byte[] object, String string2, String string3) {
        try {
            object = new PKCS8EncodedKeySpec(a.b((byte[])object));
            object = KeyFactory.getInstance((String)"RSA").generatePrivate((KeySpec)object);
            string3 = Signature.getInstance((String)string3);
            string3.initSign((PrivateKey)object);
            string3.update(string2.getBytes());
            object = a.a(string3.sign());
            return object;
        }
        catch (Throwable var0_1) {
            LogManager.e("Signaturer", "sign failed", var0_1);
            var0_1.printStackTrace();
            return null;
        }
    }
}

