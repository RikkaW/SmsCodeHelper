/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public class anq {
    public static int a(char c2) {
        if (c2 >= '0' && c2 <= '9') {
            return c2 - 48;
        }
        if (c2 >= 'A' && c2 <= 'F') {
            return c2 - 65 + 10;
        }
        if (c2 >= 'a' && c2 <= 'f') {
            return c2 - 97 + 10;
        }
        throw new RuntimeException("invalid hex char '" + c2 + "'");
    }

    public static String a(byte[] arrby) {
        if (arrby == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder(arrby.length * 2);
        int n2 = 0;
        while (n2 < arrby.length) {
            stringBuilder.append("0123456789abcdef".charAt(arrby[n2] >> 4 & 15));
            stringBuilder.append("0123456789abcdef".charAt(arrby[n2] & 15));
            ++n2;
        }
        return stringBuilder.toString();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static byte[] a(String string2) {
        if (string2 == null) {
            return null;
        }
        int n2 = string2.length();
        byte[] arrby = new byte[n2 / 2];
        int n3 = 0;
        do {
            Object object = arrby;
            if (n3 >= n2) return object;
            arrby[n3 / 2] = (byte)(anq.a(string2.charAt(n3)) << 4 | anq.a(string2.charAt(n3 + 1)));
            n3 += 2;
        } while (true);
    }
}

