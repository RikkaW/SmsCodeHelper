/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.Random
 */
package com.xiaomi.mms.utils.sec;

import com.xiaomi.mms.utils.sec.Base64Utils;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AESUtil {
    public static String encrypt(String string2, String object) throws Exception {
        if (object == null) {
            throw new Exception("AES ENCRYPT : sKey is null");
        }
        if (object.length() != 16) {
            throw new Exception("AES ENCRYPT : sKey's length is not 16");
        }
        object = new SecretKeySpec(object.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, (Key)object, new IvParameterSpec("0102030405060708".getBytes()));
        return Base64Utils.encrypt(cipher.doFinal(string2.getBytes()));
    }

    public static String getAESKeyPlaintext() {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 16; ++i) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }
}

