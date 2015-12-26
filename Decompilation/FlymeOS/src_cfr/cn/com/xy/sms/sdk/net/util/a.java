/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.Arrays
 */
package cn.com.xy.sms.sdk.net.util;

import java.util.Arrays;

public final class a {
    private static final boolean a = true;
    private static final char[] b;
    private static final int[] c;

    static {
        int[] arrn;
        b = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        c = arrn = new int[256];
        Arrays.fill((int[])arrn, (int)-1);
        int n2 = b.length;
        int n3 = 0;
        do {
            if (n3 >= n2) {
                a.c[61] = 0;
                return;
            }
            a.c[a.b[n3]] = n3++;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final byte[] a(byte[] arrby) {
        int n2 = 0;
        if (arrby == null) return new byte[0];
        int n3 = arrby.length;
        if (n3 == 0) {
            return new byte[0];
        }
        int n4 = n3 / 3 * 3;
        int n5 = (n3 - 1) / 3 + 1 << 2;
        int n6 = n5 + ((n5 - 1) / 76 << 1);
        byte[] arrby2 = new byte[n6];
        n5 = 0;
        int n7 = 0;
        int n8 = 0;
        do {
            if (n8 >= n4) {
                n7 = n3 - n4;
                if (n7 > 0) {
                    n8 = arrby[n4];
                    n5 = n2;
                    if (n7 == 2) {
                        n5 = (arrby[n3 - 1] & 255) << 2;
                    }
                    arrby2[n6 - 4] = (byte)b[(n5 |= (n8 & 255) << 10) >> 12];
                    arrby2[n6 - 3] = (byte)b[n5 >>> 6 & 63];
                    int n9 = n7 == 2 ? (int)((byte)b[n5 & 63]) : 61;
                    arrby2[n6 - 2] = n9;
                    arrby2[n6 - 1] = 61;
                }
                return arrby2;
            }
            int n10 = n8 + 1;
            n8 = arrby[n8];
            int n11 = n10 + 1;
            int n12 = arrby[n10];
            n10 = n11 + 1;
            n8 = (n12 & 255) << 8 | (n8 & 255) << 16 | arrby[n11] & 255;
            n11 = n7 + 1;
            arrby2[n7] = (byte)b[n8 >>> 18 & 63];
            n7 = n11 + 1;
            arrby2[n11] = (byte)b[n8 >>> 12 & 63];
            n12 = n7 + 1;
            arrby2[n7] = (byte)b[n8 >>> 6 & 63];
            n11 = n12 + 1;
            arrby2[n12] = (byte)b[n8 & 63];
            n5 = n12 = n5 + 1;
            n7 = n11;
            n8 = n10;
            if (n12 != 19) continue;
            n5 = n12;
            n7 = n11;
            n8 = n10;
            if (n11 >= n6 - 2) continue;
            n5 = n11 + 1;
            arrby2[n11] = 13;
            arrby2[n5] = 10;
            n7 = n5 + 1;
            n5 = 0;
            n8 = n10;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static byte[] a(byte[] arrby, boolean bl2) {
        int n2 = 0;
        if (arrby == null) return new byte[0];
        int n3 = arrby.length;
        if (n3 == 0) {
            return new byte[0];
        }
        int n4 = n3 / 3 * 3;
        int n5 = (n3 - 1) / 3 + 1 << 2;
        int n6 = n5 + ((n5 - 1) / 76 << 1);
        byte[] arrby2 = new byte[n6];
        n5 = 0;
        int n7 = 0;
        int n8 = 0;
        do {
            if (n8 >= n4) {
                n7 = n3 - n4;
                if (n7 > 0) {
                    n8 = arrby[n4];
                    n5 = n2;
                    if (n7 == 2) {
                        n5 = (arrby[n3 - 1] & 255) << 2;
                    }
                    arrby2[n6 - 4] = (byte)b[(n5 |= (n8 & 255) << 10) >> 12];
                    arrby2[n6 - 3] = (byte)b[n5 >>> 6 & 63];
                    int n9 = n7 == 2 ? (int)((byte)b[n5 & 63]) : 61;
                    arrby2[n6 - 2] = n9;
                    arrby2[n6 - 1] = 61;
                }
                return arrby2;
            }
            int n10 = n8 + 1;
            n8 = arrby[n8];
            int n11 = n10 + 1;
            int n12 = arrby[n10];
            n10 = n11 + 1;
            n8 = (n12 & 255) << 8 | (n8 & 255) << 16 | arrby[n11] & 255;
            n11 = n7 + 1;
            arrby2[n7] = (byte)b[n8 >>> 18 & 63];
            n7 = n11 + 1;
            arrby2[n11] = (byte)b[n8 >>> 12 & 63];
            n12 = n7 + 1;
            arrby2[n7] = (byte)b[n8 >>> 6 & 63];
            n11 = n12 + 1;
            arrby2[n12] = (byte)b[n8 & 63];
            n5 = n12 = n5 + 1;
            n7 = n11;
            n8 = n10;
            if (n12 != 19) continue;
            n5 = n12;
            n7 = n11;
            n8 = n10;
            if (n11 >= n6 - 2) continue;
            n5 = n11 + 1;
            arrby2[n11] = 13;
            arrby2[n5] = 10;
            n7 = n5 + 1;
            n5 = 0;
            n8 = n10;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static final byte[] b(byte[] arrby) {
        int n2;
        int n3;
        int[] arrn;
        int n4 = arrby.length;
        int n5 = 0;
        int n6 = 0;
        do {
            if (n5 >= n4) {
                if ((n4 - n6) % 4 == 0) break;
                return null;
            }
            n2 = n6;
            if (c[arrby[n5] & 255] < 0) {
                n2 = n6 + 1;
            }
            ++n5;
            n6 = n2;
        } while (true);
        n5 = n4;
        n2 = 0;
        do {
            if (n5 <= 1 || (arrn = c)[arrby[n3 = n5 - 1] & 255] > 0) break;
            n5 = n3;
            if (arrby[n3] != 61) continue;
            ++n2;
            n5 = n3;
        } while (true);
        n3 = ((n4 - n6) * 6 >> 3) - n2;
        arrn = new byte[n3];
        n5 = 0;
        n6 = 0;
        block2 : while (n5 < n3) {
            n4 = 0;
            n2 = 0;
            do {
                if (n2 >= 4) {
                    n2 = n5 + 1;
                    arrn[n5] = (byte)(n4 >> 16);
                    n5 = n2;
                    if (n2 >= n3) continue block2;
                    n5 = n2 + 1;
                    arrn[n2] = (byte)(n4 >> 8);
                    if (n5 >= n3) continue block2;
                    n2 = n5 + 1;
                    arrn[n5] = (byte)n4;
                    n5 = n2;
                    continue block2;
                }
                int n7 = c[arrby[n6] & 255];
                if (n7 >= 0) {
                    n4 |= n7 << 18 - n2 * 6;
                } else {
                    --n2;
                }
                ++n2;
                ++n6;
            } while (true);
            break;
        }
        return arrn;
    }
}

