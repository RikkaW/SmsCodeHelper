/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package android.support.v4.util;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.ContainerHelpers;
import java.util.Map;

public class SimpleArrayMap<K, V> {
    private static final int BASE_SIZE = 4;
    private static final int CACHE_SIZE = 10;
    private static final boolean DEBUG = false;
    private static final String TAG = "ArrayMap";
    static Object[] mBaseCache;
    static int mBaseCacheSize;
    static Object[] mTwiceBaseCache;
    static int mTwiceBaseCacheSize;
    Object[] mArray;
    int[] mHashes;
    int mSize;

    public SimpleArrayMap() {
        this.mHashes = ContainerHelpers.EMPTY_INTS;
        this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        this.mSize = 0;
    }

    /*
     * Enabled aggressive block sorting
     */
    public SimpleArrayMap(int n2) {
        if (n2 == 0) {
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            this.allocArrays(n2);
        }
        this.mSize = 0;
    }

    public SimpleArrayMap(SimpleArrayMap simpleArrayMap) {
        this();
        if (simpleArrayMap != null) {
            this.putAll(simpleArrayMap);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void allocArrays(int n2) {
        if (n2 == 8) {
            synchronized (ArrayMap.class) {
                if (mTwiceBaseCache != null) {
                    Object[] arrobject;
                    this.mArray = arrobject = mTwiceBaseCache;
                    mTwiceBaseCache = (Object[])arrobject[0];
                    this.mHashes = (int[])arrobject[1];
                    arrobject[1] = null;
                    arrobject[0] = null;
                    --mTwiceBaseCacheSize;
                    return;
                }
            }
        } else if (n2 == 4) {
            synchronized (ArrayMap.class) {
                if (mBaseCache != null) {
                    Object[] arrobject;
                    this.mArray = arrobject = mBaseCache;
                    mBaseCache = (Object[])arrobject[0];
                    this.mHashes = (int[])arrobject[1];
                    arrobject[1] = null;
                    arrobject[0] = null;
                    --mBaseCacheSize;
                    return;
                }
            }
        }
        this.mHashes = new int[n2];
        this.mArray = new Object[n2 << 1];
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private static void freeArrays(int[] var0, Object[] var1_1, int var2_2) {
        if (var0.length != 8) ** GOTO lbl7
        // MONITORENTER : android.support.v4.util.ArrayMap.class
        if (SimpleArrayMap.mTwiceBaseCacheSize >= 10) ** GOTO lbl18
        var1_1[0] = SimpleArrayMap.mTwiceBaseCache;
        var1_1[1] = var0;
        ** GOTO lbl13
lbl7: // 1 sources:
        if (var0.length != 4) return;
        // MONITORENTER : android.support.v4.util.ArrayMap.class
        if (SimpleArrayMap.mBaseCacheSize >= 10) ** GOTO lbl25
        var1_1[0] = SimpleArrayMap.mBaseCache;
        var1_1[1] = var0;
        ** GOTO lbl20
lbl13: // 2 sources:
        for (var2_2 = (var2_2 << 1) - 1; var2_2 >= 2; --var2_2) {
            var1_1[var2_2] = null;
        }
        SimpleArrayMap.mTwiceBaseCache = var1_1;
        ++SimpleArrayMap.mTwiceBaseCacheSize;
lbl18: // 2 sources:
        // MONITOREXIT : android.support.v4.util.ArrayMap.class
        return;
lbl20: // 2 sources:
        for (var2_2 = (var2_2 << 1) - 1; var2_2 >= 2; --var2_2) {
            var1_1[var2_2] = null;
        }
        SimpleArrayMap.mBaseCache = var1_1;
        ++SimpleArrayMap.mBaseCacheSize;
lbl25: // 2 sources:
        // MONITOREXIT : android.support.v4.util.ArrayMap.class
    }

    public void clear() {
        if (this.mSize != 0) {
            SimpleArrayMap.freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
        }
    }

    public boolean containsKey(Object object) {
        if (this.indexOfKey(object) >= 0) {
            return true;
        }
        return false;
    }

    public boolean containsValue(Object object) {
        if (this.indexOfValue(object) >= 0) {
            return true;
        }
        return false;
    }

    public void ensureCapacity(int n2) {
        if (this.mHashes.length < n2) {
            int[] arrn = this.mHashes;
            Object[] arrobject = this.mArray;
            this.allocArrays(n2);
            if (this.mSize > 0) {
                System.arraycopy((Object)arrn, (int)0, (Object)this.mHashes, (int)0, (int)this.mSize);
                System.arraycopy((Object)arrobject, (int)0, (Object)this.mArray, (int)0, (int)(this.mSize << 1));
            }
            SimpleArrayMap.freeArrays(arrn, arrobject, this.mSize);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Map)) return false;
        object = (Map)object;
        if (this.size() != object.size()) {
            return false;
        }
        int n2 = 0;
        try {
            while (n2 < this.mSize) {
                K k2 = this.keyAt(n2);
                V v2 = this.valueAt(n2);
                Object v3 = object.get(k2);
                if (v2 == null) {
                    if (v3 != null) return false;
                    if (!object.containsKey(k2)) {
                        return false;
                    }
                } else {
                    boolean bl2 = v2.equals(v3);
                    if (!bl2) {
                        return false;
                    }
                }
                ++n2;
            }
            return true;
        }
        catch (NullPointerException var1_2) {
            return false;
        }
        catch (ClassCastException var1_3) {
            return false;
        }
    }

    public V get(Object object) {
        int n2 = this.indexOfKey(object);
        if (n2 >= 0) {
            return (V)this.mArray[(n2 << 1) + 1];
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int hashCode() {
        int[] arrn = this.mHashes;
        Object[] arrobject = this.mArray;
        int n2 = this.mSize;
        int n3 = 1;
        int n4 = 0;
        int n5 = 0;
        while (n4 < n2) {
            Object object = arrobject[n3];
            int n6 = arrn[n4];
            int n7 = object == null ? 0 : object.hashCode();
            n5 += n7 ^ n6;
            ++n4;
            n3 += 2;
        }
        return n5;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    int indexOf(Object object, int n2) {
        int n3;
        int n4;
        int n5 = this.mSize;
        if (n5 == 0) {
            return -1;
        }
        int n6 = n4 = ContainerHelpers.binarySearch(this.mHashes, n5, n2);
        if (n4 < 0) return n6;
        n6 = n4;
        if (object.equals(this.mArray[n4 << 1])) return n6;
        for (n3 = n4 + 1; n3 < n5 && this.mHashes[n3] == n2; ++n3) {
            if (!object.equals(this.mArray[n3 << 1])) continue;
            return n3;
        }
        --n4;
        while (n4 >= 0) {
            if (this.mHashes[n4] != n2) return ~ n3;
            n6 = n4;
            if (object.equals(this.mArray[n4 << 1])) return n6;
            --n4;
        }
        return ~ n3;
    }

    public int indexOfKey(Object object) {
        if (object == null) {
            return this.indexOfNull();
        }
        return this.indexOf(object, object.hashCode());
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    int indexOfNull() {
        int n2;
        int n3;
        int n4 = this.mSize;
        if (n4 == 0) {
            return -1;
        }
        int n5 = n2 = ContainerHelpers.binarySearch(this.mHashes, n4, 0);
        if (n2 < 0) return n5;
        n5 = n2;
        if (this.mArray[n2 << 1] == null) return n5;
        for (n3 = n2 + 1; n3 < n4 && this.mHashes[n3] == 0; ++n3) {
            if (this.mArray[n3 << 1] != null) continue;
            return n3;
        }
        --n2;
        while (n2 >= 0) {
            if (this.mHashes[n2] != 0) return ~ n3;
            n5 = n2;
            if (this.mArray[n2 << 1] == null) return n5;
            --n2;
        }
        return ~ n3;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    int indexOfValue(Object var1_1) {
        var2_2 = 1;
        var3_3 = 1;
        var4_4 = this.mSize * 2;
        var5_5 = this.mArray;
        if (var1_1 != null) ** GOTO lbl14
        var2_2 = var3_3;
        while (var2_2 < var4_4) {
            if (var5_5[var2_2] == null) {
                return var2_2 >> 1;
            }
            var2_2 += 2;
        }
        return -1;
lbl-1000: // 1 sources:
        {
            var2_2 += 2;
lbl14: // 2 sources:
            if (var2_2 >= var4_4) return -1;
            ** while (!var1_1.equals((Object)var5_5[var2_2]))
        }
lbl16: // 1 sources:
        return var2_2 >> 1;
    }

    public boolean isEmpty() {
        if (this.mSize <= 0) {
            return true;
        }
        return false;
    }

    public K keyAt(int n2) {
        return (K)this.mArray[n2 << 1];
    }

    /*
     * Enabled aggressive block sorting
     */
    public V put(K object, V v2) {
        int n2;
        int n3;
        int n4 = 8;
        if (object == null) {
            n2 = this.indexOfNull();
            n3 = 0;
        } else {
            n3 = object.hashCode();
            n2 = this.indexOf(object, n3);
        }
        if (n2 >= 0) {
            n2 = (n2 << 1) + 1;
            object = this.mArray[n2];
            this.mArray[n2] = v2;
            return (V)object;
        }
        int n5 = ~ n2;
        if (this.mSize >= this.mHashes.length) {
            if (this.mSize >= 8) {
                n2 = this.mSize + (this.mSize >> 1);
            } else {
                n2 = n4;
                if (this.mSize < 4) {
                    n2 = 4;
                }
            }
            int[] arrn = this.mHashes;
            Object[] arrobject = this.mArray;
            this.allocArrays(n2);
            if (this.mHashes.length > 0) {
                System.arraycopy((Object)arrn, (int)0, (Object)this.mHashes, (int)0, (int)arrn.length);
                System.arraycopy((Object)arrobject, (int)0, (Object)this.mArray, (int)0, (int)arrobject.length);
            }
            SimpleArrayMap.freeArrays(arrn, arrobject, this.mSize);
        }
        if (n5 < this.mSize) {
            System.arraycopy((Object)this.mHashes, (int)n5, (Object)this.mHashes, (int)(n5 + 1), (int)(this.mSize - n5));
            System.arraycopy((Object)this.mArray, (int)(n5 << 1), (Object)this.mArray, (int)(n5 + 1 << 1), (int)(this.mSize - n5 << 1));
        }
        this.mHashes[n5] = n3;
        this.mArray[n5 << 1] = object;
        this.mArray[(n5 << 1) + 1] = v2;
        ++this.mSize;
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        int n2 = simpleArrayMap.mSize;
        this.ensureCapacity(this.mSize + n2);
        if (this.mSize == 0) {
            if (n2 <= 0) return;
            {
                System.arraycopy((Object)simpleArrayMap.mHashes, (int)0, (Object)this.mHashes, (int)0, (int)n2);
                System.arraycopy((Object)simpleArrayMap.mArray, (int)0, (Object)this.mArray, (int)0, (int)(n2 << 1));
                this.mSize = n2;
            }
            return;
        } else {
            for (int i2 = 0; i2 < n2; ++i2) {
                this.put(simpleArrayMap.keyAt(i2), simpleArrayMap.valueAt(i2));
            }
        }
    }

    public V remove(Object object) {
        int n2 = this.indexOfKey(object);
        if (n2 >= 0) {
            return this.removeAt(n2);
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public V removeAt(int n2) {
        int n3 = 8;
        Object object = this.mArray[(n2 << 1) + 1];
        if (this.mSize <= 1) {
            SimpleArrayMap.freeArrays(this.mHashes, this.mArray, this.mSize);
            this.mHashes = ContainerHelpers.EMPTY_INTS;
            this.mArray = ContainerHelpers.EMPTY_OBJECTS;
            this.mSize = 0;
            return (V)object;
        }
        if (this.mHashes.length > 8 && this.mSize < this.mHashes.length / 3) {
            if (this.mSize > 8) {
                n3 = this.mSize + (this.mSize >> 1);
            }
            int[] arrn = this.mHashes;
            Object[] arrobject = this.mArray;
            this.allocArrays(n3);
            --this.mSize;
            if (n2 > 0) {
                System.arraycopy((Object)arrn, (int)0, (Object)this.mHashes, (int)0, (int)n2);
                System.arraycopy((Object)arrobject, (int)0, (Object)this.mArray, (int)0, (int)(n2 << 1));
            }
            if (n2 >= this.mSize) return (V)object;
            {
                System.arraycopy((Object)arrn, (int)(n2 + 1), (Object)this.mHashes, (int)n2, (int)(this.mSize - n2));
                System.arraycopy((Object)arrobject, (int)(n2 + 1 << 1), (Object)this.mArray, (int)(n2 << 1), (int)(this.mSize - n2 << 1));
                return (V)object;
            }
        }
        --this.mSize;
        if (n2 < this.mSize) {
            System.arraycopy((Object)this.mHashes, (int)(n2 + 1), (Object)this.mHashes, (int)n2, (int)(this.mSize - n2));
            System.arraycopy((Object)this.mArray, (int)(n2 + 1 << 1), (Object)this.mArray, (int)(n2 << 1), (int)(this.mSize - n2 << 1));
        }
        this.mArray[this.mSize << 1] = null;
        this.mArray[(this.mSize << 1) + 1] = null;
        return (V)object;
    }

    public V setValueAt(int n2, V v2) {
        n2 = (n2 << 1) + 1;
        Object object = this.mArray[n2];
        this.mArray[n2] = v2;
        return (V)object;
    }

    public int size() {
        return this.mSize;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        if (this.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
        stringBuilder.append('{');
        int n2 = 0;
        do {
            Object object;
            if (n2 >= this.mSize) {
                stringBuilder.append('}');
                return stringBuilder.toString();
            }
            if (n2 > 0) {
                stringBuilder.append(", ");
            }
            if ((object = this.keyAt(n2)) != this) {
                stringBuilder.append(object);
            } else {
                stringBuilder.append("(this Map)");
            }
            stringBuilder.append('=');
            object = this.valueAt(n2);
            if (object != this) {
                stringBuilder.append(object);
            } else {
                stringBuilder.append("(this Map)");
            }
            ++n2;
        } while (true);
    }

    public V valueAt(int n2) {
        return (V)this.mArray[(n2 << 1) + 1];
    }
}

