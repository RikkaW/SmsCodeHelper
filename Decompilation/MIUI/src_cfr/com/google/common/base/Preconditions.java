/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.google.common.base;

public final class Preconditions {
    private Preconditions() {
    }

    private static String badPositionIndex(int n, int n2, String string2) {
        if (n < 0) {
            return Preconditions.format("%s (%s) must not be negative", string2, n);
        }
        if (n2 < 0) {
            throw new IllegalArgumentException("negative size: " + n2);
        }
        return Preconditions.format("%s (%s) must not be greater than size (%s)", string2, n, n2);
    }

    public static void checkArgument(boolean bl) {
        if (!bl) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean bl, Object object) {
        if (!bl) {
            throw new IllegalArgumentException(String.valueOf((Object)object));
        }
    }

    public static /* varargs */ void checkArgument(boolean bl, String string2, Object ... arrobject) {
        if (!bl) {
            throw new IllegalArgumentException(Preconditions.format(string2, arrobject));
        }
    }

    public static <T> T checkNotNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    public static <T> T checkNotNull(T t, Object object) {
        if (t == null) {
            throw new NullPointerException(String.valueOf((Object)object));
        }
        return t;
    }

    public static int checkPositionIndex(int n, int n2) {
        return Preconditions.checkPositionIndex(n, n2, "index");
    }

    public static int checkPositionIndex(int n, int n2, String string2) {
        if (n < 0 || n > n2) {
            throw new IndexOutOfBoundsException(Preconditions.badPositionIndex(n, n2, string2));
        }
        return n;
    }

    public static void checkState(boolean bl) {
        if (!bl) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean bl, Object object) {
        if (!bl) {
            throw new IllegalStateException(String.valueOf((Object)object));
        }
    }

    public static /* varargs */ void checkState(boolean bl, String string2, Object ... arrobject) {
        if (!bl) {
            throw new IllegalStateException(Preconditions.format(string2, arrobject));
        }
    }

    static /* varargs */ String format(String string2, Object ... arrobject) {
        StringBuilder stringBuilder;
        block3 : {
            string2 = String.valueOf((Object)string2);
            stringBuilder = new StringBuilder(string2.length() + arrobject.length * 16);
            int n = 0;
            int n2 = 0;
            do {
                int n3;
                if (n2 >= arrobject.length || (n3 = string2.indexOf("%s", n)) == -1) {
                    stringBuilder.append(string2.substring(n));
                    if (n2 >= arrobject.length) break block3;
                    stringBuilder.append(" [");
                    stringBuilder.append(arrobject[n2]);
                    ++n2;
                    while (n2 < arrobject.length) {
                        stringBuilder.append(", ");
                        stringBuilder.append(arrobject[n2]);
                        ++n2;
                    }
                    break;
                }
                stringBuilder.append(string2.substring(n, n3));
                stringBuilder.append(arrobject[n2]);
                n = n3 + 2;
                ++n2;
            } while (true);
            stringBuilder.append(']');
        }
        return stringBuilder.toString();
    }
}

