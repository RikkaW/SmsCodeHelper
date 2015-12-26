/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.io.ByteArrayOutputStream
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.security.KeyFactory
 *  java.security.MessageDigest
 *  java.security.cert.Certificate
 *  java.security.cert.CertificateFactory
 */
package com.amap.api.location.core;

import android.content.Context;
import com.amap.api.location.core.b;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class e {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final byte[] b = new byte[]{0, 1, 1, 2, 3, 5, 8, 13, 8, 7, 6, 5, 4, 3, 2, 1};
    private static final IvParameterSpec c = new IvParameterSpec(b);

    static String a(String string2) {
        if (string2 != null) {
            block4 : {
                if (string2.length() != 0) break block4;
                return null;
            }
            try {
                String string3 = e.a("SHA1", string2);
                string2 = e.a("MD5", string3 + string2);
                return string2;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
            }
        }
        return null;
    }

    public static String a(String string2, String string3) {
        if (string3 == null) {
            return null;
        }
        try {
            string2 = MessageDigest.getInstance((String)string2);
            string2.update(string3.getBytes("utf-8"));
            string2 = e.b(string2.digest());
            return string2;
        }
        catch (Exception var0_1) {
            throw new RuntimeException(var0_1);
        }
    }

    public static String a(byte[] arrby) {
        StringBuffer stringBuffer = new StringBuffer(arrby.length * 2);
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            String string2 = Integer.toHexString((int)(arrby[i2] & 255));
            if (string2.length() == 1) {
                stringBuffer.append("0");
            }
            stringBuffer.append(string2);
        }
        return stringBuffer.toString().toUpperCase();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static PublicKey a(Context object) {
        try {
            object = new ByteArrayInputStream(new byte[]{48, -126, 2, -98, 48, -126, 2, 7, -96, 3, 2, 1, 2, 2, 9, 0, -99, 15, 119, 58, 44, -19, -105, -40, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, 30, 23, 13, 49, 51, 48, 56, 49, 53, 48, 55, 53, 54, 53, 53, 90, 23, 13, 50, 51, 48, 56, 49, 51, 48, 55, 53, 54, 53, 53, 90, 48, 104, 49, 11, 48, 9, 6, 3, 85, 4, 6, 19, 2, 67, 78, 49, 19, 48, 17, 6, 3, 85, 4, 8, 12, 10, 83, 111, 109, 101, 45, 83, 116, 97, 116, 101, 49, 16, 48, 14, 6, 3, 85, 4, 7, 12, 7, 66, 101, 105, 106, 105, 110, 103, 49, 17, 48, 15, 6, 3, 85, 4, 10, 12, 8, 65, 117, 116, 111, 110, 97, 118, 105, 49, 31, 48, 29, 6, 3, 85, 4, 3, 12, 22, 99, 111, 109, 46, 97, 117, 116, 111, 110, 97, 118, 105, 46, 97, 112, 105, 115, 101, 114, 118, 101, 114, 48, -127, -97, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 1, 5, 0, 3, -127, -115, 0, 48, -127, -119, 2, -127, -127, 0, -15, -27, -128, -56, 118, -59, 62, -127, 79, 125, -36, 121, 0, 63, -125, -30, 118, 5, -85, -121, 91, 39, 90, 123, 72, -126, -83, -41, -45, -77, -42, -120, -81, 23, -2, -121, -29, 123, -7, 22, -114, -20, -25, 74, 67, -43, 65, 124, -7, 11, -72, 38, -123, 16, -58, 80, 32, 58, -33, 14, 11, 36, 60, 13, -121, 100, 105, -32, 123, -31, 114, -101, -41, 12, 100, 33, -120, 63, 126, -123, 48, 55, 80, -116, 28, -10, 125, 59, -41, -95, -126, 118, -70, 43, -127, 9, 93, -100, 81, -19, -114, -41, 85, -103, -37, -116, 118, 72, 86, 125, -43, -92, -11, 63, 69, -38, -10, -65, 126, -53, -115, 60, 62, -86, -80, 1, 39, 19, 2, 3, 1, 0, 1, -93, 80, 48, 78, 48, 29, 6, 3, 85, 29, 14, 4, 22, 4, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 31, 6, 3, 85, 29, 35, 4, 24, 48, 22, -128, 20, -29, 63, 48, -79, -113, -13, 26, 85, 22, -27, 93, -5, 122, -103, -109, 14, -18, 6, -13, -109, 48, 12, 6, 3, 85, 29, 19, 4, 5, 48, 3, 1, 1, -1, 48, 13, 6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 5, 5, 0, 3, -127, -127, 0, -32, -74, 55, -125, -58, -128, 15, -62, 100, -60, 3, -86, 81, 112, -61, -56, -69, -126, 8, 99, -100, -38, -108, -56, -122, 125, 19, -64, -61, 90, 85, -47, -8, -123, -103, 105, 77, -32, -65, -62, -28, 67, -28, -78, 116, -49, 120, -2, 33, 13, 47, 46, -5, -112, 3, -101, -125, -115, 92, -124, 58, 80, 107, -67, 82, 6, -63, 39, -90, -1, 85, -58, 82, -115, 119, 13, -4, -32, 0, -98, 100, -41, 94, -75, 75, -103, 126, -80, 85, 40, -27, 60, 105, 28, -27, -21, -15, -98, 103, -88, -109, 35, -119, -27, -26, -122, 113, 63, 35, -33, 70, 23, 33, -23, 66, 108, 56, 112, 46, -85, -123, -123, 33, 118, 27, 96, -7, -103});
            CertificateFactory certificateFactory = CertificateFactory.getInstance((String)"X.509");
            KeyFactory keyFactory = KeyFactory.getInstance((String)"RSA");
            certificateFactory = certificateFactory.generateCertificate((InputStream)object);
            object.close();
            return keyFactory.generatePublic((KeySpec)new X509EncodedKeySpec(certificateFactory.getPublicKey().getEncoded()));
        }
        catch (NoSuchAlgorithmException var0_1) {
            throw new Exception("\u65e0\u6b64\u7b97\u6cd5");
        }
        catch (InvalidKeySpecException var0_2) {
            throw new Exception("\u516c\u94a5\u975e\u6cd5");
        }
        catch (NullPointerException var0_3) {
            throw new Exception("\u516c\u94a5\u6570\u636e\u4e3a\u7a7a");
        }
        catch (CertificateException var0_4) {
            do {
                return null;
                break;
            } while (true);
        }
        catch (IOException var0_5) {
            return null;
        }
    }

    public static byte[] a(byte[] arrby, String object) {
        try {
            object = e.c((String)object);
            Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
            cipher.init(2, (Key)object);
            arrby = cipher.doFinal(arrby);
            return arrby;
        }
        catch (Exception var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static byte[] a(byte[] arrby, Key key) {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, key);
        return cipher.doFinal(arrby);
    }

    public static byte[] a(byte[] object, byte[] arrby) {
        try {
            object = new SecretKeySpec((byte[])object, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, (Key)object);
            object = cipher.doFinal(arrby);
            return object;
        }
        catch (NoSuchAlgorithmException var0_1) {
            var0_1.printStackTrace();
            return null;
        }
        catch (NoSuchPaddingException var0_2) {
            var0_2.printStackTrace();
            return null;
        }
        catch (Throwable var0_3) {
            var0_3.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String b(String string2, String string3) {
        if (string2 == null) return null;
        if (string2.length() <= 0) {
            return null;
        }
        try {
            try {
                string2 = (String)e.b(string2);
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                string2 = null;
            }
            if ((string2 = (String)e.a((byte[])string2, string3)) == null) return null;
            try {
                return new String((byte[])string2, "UTF-8");
            }
            catch (UnsupportedEncodingException var0_2) {
                var0_2.printStackTrace();
                return null;
            }
        }
        catch (Throwable var0_3) {
            var0_3.printStackTrace();
            return null;
        }
    }

    private static String b(byte[] arrby) {
        int n2 = arrby.length;
        StringBuilder stringBuilder = new StringBuilder(n2 * 2);
        for (int i2 = 0; i2 < n2; ++i2) {
            stringBuilder.append(a[arrby[i2] >> 4 & 15]);
            stringBuilder.append(a[arrby[i2] & 15]);
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private static byte[] b(String string2) {
        void var0_2;
        int n2 = 0;
        if (string2 == null || string2.length() < 2) {
            byte[] arrby = new byte[]{};
            return var0_2;
        } else {
            String string3 = string2.toLowerCase();
            int n3 = string3.length() / 2;
            byte[] arrby = new byte[n3];
            do {
                byte[] arrby2 = arrby;
                if (n2 >= n3) return var0_2;
                arrby[n2] = (byte)(Integer.parseInt((String)string3.substring(n2 * 2, n2 * 2 + 2), (int)16) & 255);
                ++n2;
            } while (true);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static byte[] b(byte[] arrby, String object) {
        int n2 = 0;
        synchronized (e.class) {
            Object object2;
            object2 = new PKCS8EncodedKeySpec(b.a((String)object2));
            object2 = KeyFactory.getInstance((String)"RSA").generatePrivate((KeySpec)object2);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(1, (Key)object2);
            int n3 = arrby.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int n4 = 0;
            do {
                if (n3 - n2 <= 0) {
                    arrby = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return arrby;
                }
                object2 = n3 - n2 > 245 ? (Object)cipher.doFinal(arrby, n2, 245) : (Object)cipher.doFinal(arrby, n2, n3 - n2);
                byteArrayOutputStream.write((byte[])object2, 0, object2.length);
                n2 = ++n4 * 245;
            } while (true);
        }
    }

    public static byte[] b(byte[] arrby, byte[] arrby2) {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(2, (Key)new SecretKeySpec(arrby, "AES"), c);
        return cipher.doFinal(arrby2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static SecretKeySpec c(String string2) {
        void var0_3;
        Object var2_6 = null;
        String string3 = string2;
        if (string2 == null) {
            string3 = "";
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(string3);
        while (stringBuffer.length() < 16) {
            stringBuffer.append("0");
        }
        if (stringBuffer.length() > 16) {
            stringBuffer.setLength(16);
        }
        try {
            byte[] arrby = stringBuffer.toString().getBytes("UTF-8");
            do {
                return new SecretKeySpec((byte[])var0_3, "AES");
                break;
            } while (true);
        }
        catch (UnsupportedEncodingException var0_4) {
            var0_4.printStackTrace();
            Object var0_5 = var2_6;
            return new SecretKeySpec((byte[])var0_3, "AES");
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static byte[] c(byte[] arrby, String object) {
        int n2 = 0;
        synchronized (e.class) {
            Object object2;
            object2 = new PKCS8EncodedKeySpec(b.a((String)object2));
            object2 = KeyFactory.getInstance((String)"RSA").generatePrivate((KeySpec)object2);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(2, (Key)object2);
            int n3 = arrby.length;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int n4 = 0;
            do {
                if (n3 - n2 <= 0) {
                    arrby = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    return arrby;
                }
                object2 = n3 - n2 > 256 ? (Object)cipher.doFinal(arrby, n2, 256) : (Object)cipher.doFinal(arrby, n2, n3 - n2);
                byteArrayOutputStream.write((byte[])object2, 0, object2.length);
                n2 = ++n4 * 256;
            } while (true);
        }
    }

    public static String d(byte[] object, String object2) {
        block3 : {
            try {
                object2 = e.c((String)object2);
                Cipher cipher = Cipher.getInstance("AES/ECB/ZeroBytePadding");
                cipher.init(1, (Key)object2);
                object = cipher.doFinal((byte[])object);
                if (object != null) break block3;
                return null;
            }
            catch (Exception var0_1) {
                var0_1.printStackTrace();
                return null;
            }
        }
        object = e.a((byte[])object);
        return object;
    }
}

