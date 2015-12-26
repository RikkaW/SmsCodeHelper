/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package android.support.v4.util;

class ContainerHelpers {
    static final int[] EMPTY_INTS = new int[0];
    static final long[] EMPTY_LONGS = new long[0];
    static final Object[] EMPTY_OBJECTS = new Object[0];

    ContainerHelpers() {
    }

    static int binarySearch(int[] arrn, int n2, int n3) {
        int n4 = 0;
        int n5 = n2 - 1;
        n2 = n4;
        n4 = n5;
        while (n2 <= n4) {
            n5 = n2 + n4 >>> 1;
            int n6 = arrn[n5];
            if (n6 < n3) {
                n2 = n5 + 1;
                continue;
            }
            if (n6 > n3) {
                n4 = n5 - 1;
                continue;
            }
            return n5;
        }
        return ~ n2;
    }

    static int binarySearch(long[] arrl, int n2, long l2) {
        int n3 = 0;
        int n4 = n2 - 1;
        n2 = n3;
        n3 = n4;
        while (n2 <= n3) {
            n4 = n2 + n3 >>> 1;
            long l3 = arrl[n4];
            if (l3 < l2) {
                n2 = n4 + 1;
                continue;
            }
            if (l3 > l2) {
                n3 = n4 - 1;
                continue;
            }
            return n4;
        }
        return ~ n2;
    }

    public static boolean equal(Object object, Object object2) {
        if (object == object2 || object != null && object.equals(object2)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static int idealByteArraySize(int n2) {
        int n3 = 4;
        do {
            int n4 = n2;
            if (n3 >= 32) return n4;
            if (n2 <= (1 << n3) - 12) {
                return (1 << n3) - 12;
            }
            ++n3;
        } while (true);
    }

    public static int idealIntArraySize(int n2) {
        return ContainerHelpers.idealByteArraySize(n2 * 4) / 4;
    }

    public static int idealLongArraySize(int n2) {
        return ContainerHelpers.idealByteArraySize(n2 * 8) / 8;
    }
}

