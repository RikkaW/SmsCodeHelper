/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
public class bk {
    private static long[] a = new long[]{305419896, 591751049, 878082192};

    private static short a() {
        int n2 = (int)(a[2] & 65535 | 2);
        return (short)(n2 * (n2 ^ 1) >> 8);
    }

    public static void a(String string2) {
        int n2 = 0;
        bk.a[0] = 305419896;
        bk.a[1] = 591751049;
        bk.a[2] = 878082192;
        while (n2 < string2.length()) {
            bk.a((byte)string2.charAt(n2));
            ++n2;
        }
        return;
    }

    private static void a(short s2) {
        bk.a[0] = bf.a(a[0], s2);
        byte by2 = (byte)a[0];
        s2 = by2;
        if ((byte)a[0] < 0) {
            s2 = (short)(by2 + 256);
        }
        bk.a[1] = a[1] + (long)s2;
        bk.a[1] = a[1] * 134775813;
        bk.a[1] = a[1] + 1;
        bk.a[2] = bf.a(a[2], (byte)(a[1] >> 24));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static byte[] a(byte[] arrby, int n2) {
        byte[] arrby2 = new byte[n2];
        int n3 = 0;
        while (n3 < n2) {
            byte by2 = (byte)(bk.a() ^ arrby[n3]);
            if (by2 < 0) {
                bk.a((short)((short)by2 + 256));
                arrby2[n3] = (byte)((short)by2 + 256);
            } else {
                bk.a(by2);
                arrby2[n3] = by2;
            }
            ++n3;
        }
        return arrby2;
    }
}

