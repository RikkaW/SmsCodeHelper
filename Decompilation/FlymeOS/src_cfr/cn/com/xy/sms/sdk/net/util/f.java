/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.security.SecureRandom
 */
package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.util.ParseManager;
import java.security.Key;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

public final class f {
    private static byte[] a = new byte[]{18, 52, 86, 120, -112, -85, -51, -17};

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] a(String string2, String object) {
        try {
            SecureRandom secureRandom = new SecureRandom();
            object = new DESKeySpec(object.getBytes());
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret((KeySpec)object);
            if (ParseManager.ismUseNewDes()) {
                object = Cipher.getInstance("DES/CBC/PKCS5Padding");
                object.init(1, (Key)secretKey, new IvParameterSpec(a));
                do {
                    return object.doFinal(string2.getBytes());
                    break;
                } while (true);
            }
            object = Cipher.getInstance("DES");
            object.init(1, (Key)secretKey, secureRandom);
            return object.doFinal(string2.getBytes());
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] a(byte[] arrby, byte[] object) {
        if (object == null || arrby == null) {
            return null;
        }
        try {
            object = new DESKeySpec((byte[])object);
            SecretKey secretKey = SecretKeyFactory.getInstance("DES").generateSecret((KeySpec)object);
            if (ParseManager.ismUseNewDes()) {
                object = Cipher.getInstance("DES/CBC/PKCS5Padding");
                object.init(2, (Key)secretKey, new IvParameterSpec(a));
                do {
                    return object.doFinal(arrby);
                    break;
                } while (true);
            }
            SecureRandom secureRandom = new SecureRandom();
            object = Cipher.getInstance("DES");
            object.init(2, (Key)secretKey, secureRandom);
            return object.doFinal(arrby);
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }
}

