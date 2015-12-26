/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Comparator
 */
package com.google.common.primitives;

import java.util.Comparator;

public final class UnsignedBytes {
    private UnsignedBytes() {
    }

    public static int compare(byte by, byte by2) {
        return UnsignedBytes.toInt(by) - UnsignedBytes.toInt(by2);
    }

    static Comparator<byte[]> lexicographicalComparatorJavaImpl() {
        return LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
    }

    public static int toInt(byte by) {
        return by & 255;
    }

    static class LexicographicalComparatorHolder {
        static final Comparator<byte[]> BEST_COMPARATOR;
        static final String UNSAFE_COMPARATOR_NAME;

        static {
            UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator";
            BEST_COMPARATOR = UnsignedBytes.lexicographicalComparatorJavaImpl();
        }

        LexicographicalComparatorHolder() {
        }

        static enum PureJavaComparator implements Comparator<byte[]>
        {
            INSTANCE;
            

            private PureJavaComparator() {
            }

            public int compare(byte[] arrby, byte[] arrby2) {
                int n = Math.min((int)arrby.length, (int)arrby2.length);
                for (int i = 0; i < n; ++i) {
                    int n2 = UnsignedBytes.compare(arrby[i], arrby2[i]);
                    if (n2 == 0) continue;
                    return n2;
                }
                return arrby.length - arrby2.length;
            }
        }

    }

}

