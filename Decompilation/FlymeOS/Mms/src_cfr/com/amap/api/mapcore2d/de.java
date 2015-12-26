/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.ed;
import java.io.ByteArrayOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class de {
    private static final char[] a;
    private static final byte[] b;

    static {
        int n2;
        a = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        b = new byte[128];
        for (n2 = 0; n2 < 128; ++n2) {
            de.b[n2] = -1;
        }
        for (n2 = 65; n2 <= 90; ++n2) {
            de.b[n2] = (byte)(n2 - 65);
        }
        for (n2 = 97; n2 <= 122; ++n2) {
            de.b[n2] = (byte)(n2 - 97 + 26);
        }
        for (n2 = 48; n2 <= 57; ++n2) {
            de.b[n2] = (byte)(n2 - 48 + 52);
        }
        de.b[43] = 62;
        de.b[47] = 63;
    }

    public static String a(String string2) {
        return new String(de.b(string2));
    }

    public static String a(byte[] object) {
        try {
            object = de.c((byte[])object);
            return object;
        }
        catch (Throwable var0_1) {
            ed.a(var0_1, "Encrypt", "encodeBase64");
            var0_1.printStackTrace();
            return null;
        }
    }

    static byte[] a(byte[] arrby, Key key) {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, key);
        return cipher.doFinal(arrby);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    static byte[] a(byte[] arrby, byte[] arrby2) {
        try {
            return de.b(arrby, arrby2);
        }
        catch (InvalidKeyException var0_1) {
            ed.a(var0_1, "Encrypt", "aesEncrypt");
            var0_1.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (NoSuchAlgorithmException var0_2) {
            ed.a(var0_2, "Encrypt", "aesEncrypt");
            var0_2.printStackTrace();
            return null;
        }
        catch (NoSuchPaddingException var0_3) {
            ed.a(var0_3, "Encrypt", "aesEncrypt");
            var0_3.printStackTrace();
            return null;
        }
        catch (IllegalBlockSizeException var0_4) {
            ed.a(var0_4, "Encrypt", "aesEncrypt");
            var0_4.printStackTrace();
            return null;
        }
        catch (BadPaddingException var0_5) {
            ed.a(var0_5, "Encrypt", "aesEncrypt");
            var0_5.printStackTrace();
            return null;
        }
        catch (Throwable var0_6) {
            ed.a(var0_6, "Encrypt", "aesEncrypt");
            var0_6.printStackTrace();
            return null;
        }
    }

    public static String b(byte[] object) {
        try {
            object = de.c((byte[])object);
            return object;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public static byte[] b(String var0) {
        var1_2 = 0;
        if (var0 /* !! */  == null) {
            return new byte[0];
        }
        var0_1 = var0 /* !! */ .getBytes();
        var3_3 = var0_1.length;
        var6_4 = new ByteArrayOutputStream(var3_3);
        block0 : do {
            if (var1_2 >= var3_3) return var6_4.toByteArray();
            var2_5 = var1_2;
            do {
                var7_8 = de.b;
                var1_2 = var2_5 + 1;
                var5_7 = var7_8[var0_1[var2_5]];
                if (var1_2 < var3_3 && var5_7 == -1) ** GOTO lbl19
                var2_5 = var1_2;
                if (var5_7 == -1) {
                    return var6_4.toByteArray();
                }
                ** GOTO lbl22
lbl19: // 1 sources:
                var2_5 = var1_2;
            } while (true);
lbl-1000: // 1 sources:
            {
                var2_5 = var1_2;
lbl22: // 2 sources:
                var7_8 = de.b;
                var1_2 = var2_5 + 1;
                var4_6 = var7_8[var0_1[var2_5]];
                ** while (var1_2 < var3_3 && var4_6 == -1)
            }
lbl26: // 1 sources:
            if (var4_6 == -1) return var6_4.toByteArray();
            var6_4.write(var5_7 << 2 | (var4_6 & 48) >>> 4);
            var2_5 = var1_2;
            do {
                var1_2 = var2_5 + 1;
                if ((var2_5 = var0_1[var2_5]) == 61) {
                    return var6_4.toByteArray();
                }
                var5_7 = de.b[var2_5];
                if (var1_2 >= var3_3 || var5_7 != -1) break;
                var2_5 = var1_2;
            } while (true);
            if (var5_7 == -1) return var6_4.toByteArray();
            var6_4.write((var4_6 & 15) << 4 | (var5_7 & 60) >>> 2);
            var2_5 = var1_2;
            do {
                var1_2 = var2_5 + 1;
                if ((var2_5 = var0_1[var2_5]) == 61) {
                    return var6_4.toByteArray();
                }
                var2_5 = de.b[var2_5];
                if (var1_2 >= var3_3 || var2_5 != -1) {
                    if (var2_5 == -1) return var6_4.toByteArray();
                    var6_4.write(var2_5 | (var5_7 & 3) << 6);
                    continue block0;
                }
                var2_5 = var1_2;
            } while (true);
            break;
        } while (true);
    }

    private static byte[] b(byte[] object, byte[] arrby) {
        object = new SecretKeySpec((byte[])object, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(1, (Key)object);
        return cipher.doFinal(arrby);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String c(byte[] arrby) {
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = arrby.length;
        int n3 = 0;
        while (n3 < n2) {
            int n4 = n3 + 1;
            int n5 = arrby[n3] & 255;
            if (n4 == n2) {
                stringBuffer.append(a[n5 >>> 2]);
                stringBuffer.append(a[(n5 & 3) << 4]);
                stringBuffer.append("==");
                do {
                    return stringBuffer.toString();
                    break;
                } while (true);
            }
            int n6 = n4 + 1;
            n4 = arrby[n4] & 255;
            if (n6 == n2) {
                stringBuffer.append(a[n5 >>> 2]);
                stringBuffer.append(a[(n5 & 3) << 4 | (n4 & 240) >>> 4]);
                stringBuffer.append(a[(n4 & 15) << 2]);
                stringBuffer.append("=");
                return stringBuffer.toString();
            }
            n3 = n6 + 1;
            n6 = arrby[n6] & 255;
            stringBuffer.append(a[n5 >>> 2]);
            stringBuffer.append(a[(n5 & 3) << 4 | (n4 & 240) >>> 4]);
            stringBuffer.append(a[(n4 & 15) << 2 | (n6 & 192) >>> 6]);
            stringBuffer.append(a[n6 & 63]);
        }
        return stringBuffer.toString();
    }
}

