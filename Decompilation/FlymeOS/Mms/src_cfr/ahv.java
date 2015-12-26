/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.text.DecimalFormat
 *  java.text.DecimalFormatSymbols
 *  java.util.Locale
 */
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ahv {
    private static ahv a = null;

    static String a(Object object, String string2) {
        DecimalFormat decimalFormat = new DecimalFormat("#", new DecimalFormatSymbols(Locale.US));
        decimalFormat.applyPattern(string2);
        return decimalFormat.format(object);
    }

    static byte[] a(int n2) {
        return new byte[]{(byte)(n2 & 255), (byte)(n2 >> 8 & 255), (byte)(n2 >> 16 & 255), (byte)(n2 >> 24 & 255)};
    }

    public static byte[] a(long l2) {
        byte[] arrby = new byte[8];
        for (int i2 = 0; i2 < arrby.length; ++i2) {
            arrby[i2] = (byte)(l2 >> i2 * 8 & 255);
        }
        return arrby;
    }

    static byte[] a(String string2) {
        return ahv.a(Integer.parseInt((String)string2));
    }

    static byte[] b(int n2) {
        return new byte[]{(byte)(n2 & 255), (byte)(n2 >> 8 & 255)};
    }

    static byte[] b(String string2) {
        return ahv.b(Integer.parseInt((String)string2));
    }
}

