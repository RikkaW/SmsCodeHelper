/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.nio.ByteBuffer
 *  java.util.Comparator
 *  java.util.Map$Entry
 */
package org.apache.thrift;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public final class TBaseHelper {
    private static final Comparator comparator = new NestedStructureComparator();

    private TBaseHelper() {
    }

    public static int byteBufferToByteArray(ByteBuffer byteBuffer, byte[] arrby, int n) {
        int n2 = byteBuffer.remaining();
        System.arraycopy((Object)byteBuffer.array(), (int)(byteBuffer.arrayOffset() + byteBuffer.position()), (Object)arrby, (int)n, (int)n2);
        return n2;
    }

    public static byte[] byteBufferToByteArray(ByteBuffer byteBuffer) {
        if (TBaseHelper.wrapsFullArray(byteBuffer)) {
            return byteBuffer.array();
        }
        byte[] arrby = new byte[byteBuffer.remaining()];
        TBaseHelper.byteBufferToByteArray(byteBuffer, arrby, 0);
        return arrby;
    }

    public static int compareTo(byte by, byte by2) {
        if (by < by2) {
            return -1;
        }
        if (by2 < by) {
            return 1;
        }
        return 0;
    }

    public static int compareTo(int n, int n2) {
        if (n < n2) {
            return -1;
        }
        if (n2 < n) {
            return 1;
        }
        return 0;
    }

    public static int compareTo(long l, long l2) {
        if (l < l2) {
            return -1;
        }
        if (l2 < l) {
            return 1;
        }
        return 0;
    }

    public static int compareTo(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public static int compareTo(String string2, String string3) {
        return string2.compareTo(string3);
    }

    public static int compareTo(List list, List list2) {
        int n = TBaseHelper.compareTo(list.size(), list2.size());
        if (n != 0) {
            return n;
        }
        for (n = 0; n < list.size(); ++n) {
            int n2 = comparator.compare(list.get(n), list2.get(n));
            if (n2 == 0) continue;
            return n2;
        }
        return 0;
    }

    public static int compareTo(Map object, Map object2) {
        int n = TBaseHelper.compareTo(object.size(), object2.size());
        if (n != 0) {
            return n;
        }
        Map.Entry entry = new Map.Entry(comparator);
        entry.putAll((Map)object);
        object = entry.entrySet().iterator();
        entry = new TreeMap(comparator);
        entry.putAll((Map)object2);
        object2 = entry.entrySet().iterator();
        while (object.hasNext() && object2.hasNext()) {
            entry = (Map.Entry)object.next();
            Map.Entry entry2 = (Map.Entry)object2.next();
            n = comparator.compare(entry.getKey(), entry2.getKey());
            if (n != 0) {
                return n;
            }
            n = comparator.compare(entry.getValue(), entry2.getValue());
            if (n == 0) continue;
            return n;
        }
        return 0;
    }

    public static int compareTo(Set object, Set object2) {
        int n = TBaseHelper.compareTo(object.size(), object2.size());
        if (n != 0) {
            return n;
        }
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.addAll((Collection)object);
        object = new TreeSet(comparator);
        object.addAll((Collection)object2);
        object2 = treeSet.iterator();
        object = object.iterator();
        while (object2.hasNext() && object.hasNext()) {
            n = comparator.compare(object2.next(), object.next());
            if (n == 0) continue;
            return n;
        }
        return 0;
    }

    public static int compareTo(boolean bl, boolean bl2) {
        return Boolean.valueOf((boolean)bl).compareTo(Boolean.valueOf((boolean)bl2));
    }

    public static int compareTo(byte[] arrby, byte[] arrby2) {
        int n = TBaseHelper.compareTo(arrby.length, arrby2.length);
        if (n != 0) {
            return n;
        }
        for (n = 0; n < arrby.length; ++n) {
            int n2 = TBaseHelper.compareTo(arrby[n], arrby2[n]);
            if (n2 == 0) continue;
            return n2;
        }
        return 0;
    }

    public static String paddedByteString(byte by) {
        return Integer.toHexString((int)((by | 256) & 511)).toUpperCase().substring(1);
    }

    public static ByteBuffer rightSize(ByteBuffer byteBuffer) {
        if (TBaseHelper.wrapsFullArray(byteBuffer)) {
            return byteBuffer;
        }
        return ByteBuffer.wrap((byte[])TBaseHelper.byteBufferToByteArray(byteBuffer));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void toString(ByteBuffer byteBuffer, StringBuilder stringBuilder) {
        byte[] arrby = byteBuffer.array();
        int n = byteBuffer.arrayOffset();
        int n2 = byteBuffer.limit();
        int n3 = n2 - n > 128 ? n + 128 : n2;
        for (int i = n; i < n3; ++i) {
            if (i > n) {
                stringBuilder.append(" ");
            }
            stringBuilder.append(TBaseHelper.paddedByteString(arrby[i]));
        }
        if (n2 != n3) {
            stringBuilder.append("...");
        }
    }

    public static boolean wrapsFullArray(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray() && byteBuffer.position() == 0 && byteBuffer.arrayOffset() == 0 && byteBuffer.remaining() == byteBuffer.capacity()) {
            return true;
        }
        return false;
    }

    private static class NestedStructureComparator
    implements Comparator {
        private NestedStructureComparator() {
        }

        public int compare(Object object, Object object2) {
            if (object == null && object2 == null) {
                return 0;
            }
            if (object == null) {
                return -1;
            }
            if (object2 == null) {
                return 1;
            }
            if (object instanceof List) {
                return TBaseHelper.compareTo((List)object, (List)object2);
            }
            if (object instanceof Set) {
                return TBaseHelper.compareTo((Set)object, (Set)object2);
            }
            if (object instanceof Map) {
                return TBaseHelper.compareTo((Map)object, (Map)object2);
            }
            if (object instanceof byte[]) {
                return TBaseHelper.compareTo((byte[])object, (byte[])object2);
            }
            return TBaseHelper.compareTo((Comparable)object, (Comparable)object2);
        }
    }

}

