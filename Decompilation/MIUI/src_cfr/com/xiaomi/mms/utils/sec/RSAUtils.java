/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.math.BigInteger
 *  java.security.KeyFactory
 */
package com.xiaomi.mms.utils.sec;

import com.xiaomi.mms.utils.sec.Base64Utils;
import java.io.IOException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class RSAUtils {
    /*
     * Enabled aggressive block sorting
     */
    public static String bcd2Str(byte[] arrby) {
        char[] arrc = new char[arrby.length * 2];
        int n = 0;
        while (n < arrby.length) {
            int n2 = (arrby[n] & 240) >> 4 & 15;
            n2 = n2 > 9 ? n2 + 65 - 10 : (n2 += 48);
            arrc[n * 2] = (char)n2;
            n2 = (char)(arrby[n] & 15);
            n2 = n2 > 9 ? n2 + 65 - 10 : (n2 += 48);
            arrc[n * 2 + 1] = (char)n2;
            ++n;
        }
        return new String(arrc);
    }

    public static String encryptByPublicKey(String string2, RSAPublicKey arrstring) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, (Key)arrstring);
        arrstring = RSAUtils.splitString(string2, arrstring.getModulus().bitLength() / 8 - 11 - 15);
        string2 = "";
        for (String string3 : arrstring) {
            string2 = string2 + RSAUtils.bcd2Str(cipher.doFinal(string3.getBytes()));
        }
        return string2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static Key loadKey(String object, boolean bl) throws Exception {
        Object object2 = Base64Utils.decryptToByte((String)object);
        object = KeyFactory.getInstance((String)"RSA");
        object2 = new X509EncodedKeySpec((byte[])object2);
        if (!bl) return object.generatePublic((KeySpec)object2);
        try {
            return object.generatePrivate((KeySpec)object2);
        }
        catch (NoSuchAlgorithmException var0_1) {
            throw new Exception("NoSuchAlgorithmException:" + (Object)((Object)var0_1));
        }
        catch (InvalidKeySpecException var0_2) {
            throw new Exception("Key is invalid:" + (Object)((Object)var0_2));
        }
        catch (IOException var0_3) {
            throw new Exception("Failed to read key data:" + (Object)((Object)var0_3));
        }
        catch (NullPointerException var0_4) {
            throw new Exception("Key data is empty:" + (Object)((Object)var0_4));
        }
    }

    public static PublicKey loadPublicKey(String string2) throws Exception {
        return (PublicKey)RSAUtils.loadKey(string2, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String[] splitString(String string2, int n) {
        int n2 = string2.length() / n;
        int n3 = string2.length() % n;
        int n4 = 0;
        if (n3 != 0) {
            n4 = 1;
        }
        String[] arrstring = new String[n2 + n4];
        int n5 = 0;
        while (n5 < n2 + n4) {
            String string3 = n5 == n2 + n4 - 1 && n3 != 0 ? string2.substring(n5 * n, n5 * n + n3) : string2.substring(n5 * n, n5 * n + n);
            arrstring[n5] = string3;
            ++n5;
        }
        return arrstring;
    }
}

