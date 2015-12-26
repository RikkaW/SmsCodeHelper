/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Base64
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.math.BigInteger
 *  java.security.MessageDigest
 */
import android.util.Base64;
import android.util.Log;
import com.ted.android.contacts.common.DataBus;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class apm {
    private static final byte[] a = "0000000000000000".getBytes();
    private static final byte[] b = apm.a();
    private static final byte[] c = apm.b();

    public static String a(String string2) {
        return apm.a(string2, b);
    }

    private static String a(String string2, byte[] object) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(a);
            object = new SecretKeySpec((byte[])object, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(1, (Key)object, ivParameterSpec);
            string2 = new String(Base64.encode((byte[])cipher.doFinal(apm.d(string2).getBytes("utf-8")), (int)0));
            return string2;
        }
        catch (Exception var0_1) {
            Log.e((String)"EncryptUtil", (String)"encrypt error:", (Throwable)var0_1);
            return null;
        }
    }

    public static byte[] a() {
        return apm.e(apm.c());
    }

    public static String b(String string2) {
        return apm.b(string2, b);
    }

    private static String b(String string2, byte[] object) {
        try {
            string2 = (String)Base64.decode((String)string2, (int)0);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(a);
            object = new SecretKeySpec((byte[])object, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(2, (Key)object, ivParameterSpec);
            string2 = new String(cipher.doFinal((byte[])string2)).trim();
            return string2;
        }
        catch (Exception var0_1) {
            Log.e((String)"EncryptUtil", (String)"decrypt error:", (Throwable)var0_1);
            return null;
        }
    }

    private static byte[] b() {
        return apm.e(DataBus.FILE_MASK);
    }

    private static String c() {
        return DataBus.CONTACT;
    }

    public static String c(String string2) {
        String string3 = string2;
        if (string2 != null) {
            string3 = string2;
            MessageDigest messageDigest = MessageDigest.getInstance((String)"MD5");
            string3 = string2;
            messageDigest.update(string2.getBytes());
            string3 = string2;
            try {
                string2 = new BigInteger(1, messageDigest.digest()).toString(16);
            }
            catch (NoSuchAlgorithmException var0_1) {
                Log.e((String)"EncryptUtil", (String)"init MessageDigest instance error", (Throwable)var0_1);
            }
            do {
                string3 = string2;
                if (string2.length() >= 32) {
                    return string2;
                }
                string3 = string2;
                string2 = "0" + string2;
                continue;
                break;
            } while (true);
        }
        return string3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static String d(String charSequence) {
        int n2;
        try {
            n2 = charSequence.getBytes("UTF-8").length % 16;
        }
        catch (UnsupportedEncodingException var3_2) {
            n2 = charSequence.length();
        }
        charSequence = new StringBuilder((String)charSequence);
        int n3 = 0;
        while (n3 < 16 - n2) {
            charSequence.append(' ');
            ++n3;
        }
        return charSequence.toString();
    }

    private static byte[] e(String string2) {
        return apm.c(string2).getBytes();
    }
}

