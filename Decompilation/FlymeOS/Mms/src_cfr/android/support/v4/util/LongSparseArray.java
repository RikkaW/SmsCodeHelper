/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package android.support.v4.util;

import android.support.v4.util.ContainerHelpers;

public class LongSparseArray<E>
implements Cloneable {
    private static final Object DELETED = new Object();
    private boolean mGarbage = false;
    private long[] mKeys;
    private int mSize;
    private Object[] mValues;

    public LongSparseArray() {
        this(10);
    }

    /*
     * Enabled aggressive block sorting
     */
    public LongSparseArray(int n2) {
        if (n2 == 0) {
            this.mKeys = ContainerHelpers.EMPTY_LONGS;
            this.mValues = ContainerHelpers.EMPTY_OBJECTS;
        } else {
            n2 = ContainerHelpers.idealLongArraySize(n2);
            this.mKeys = new long[n2];
            this.mValues = new Object[n2];
        }
        this.mSize = 0;
    }

    private void gc() {
        int n2 = this.mSize;
        long[] arrl = this.mKeys;
        Object[] arrobject = this.mValues;
        int n3 = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            Object object = arrobject[i2];
            int n4 = n3;
            if (object != DELETED) {
                if (i2 != n3) {
                    arrl[n3] = arrl[i2];
                    arrobject[n3] = object;
                    arrobject[i2] = null;
                }
                n4 = n3 + 1;
            }
            n3 = n4;
        }
        this.mGarbage = false;
        this.mSize = n3;
    }

    public void append(long l2, E e2) {
        int n2;
        if (this.mSize != 0 && l2 <= this.mKeys[this.mSize - 1]) {
            this.put(l2, e2);
            return;
        }
        if (this.mGarbage && this.mSize >= this.mKeys.length) {
            this.gc();
        }
        if ((n2 = this.mSize) >= this.mKeys.length) {
            int n3 = ContainerHelpers.idealLongArraySize(n2 + 1);
            long[] arrl = new long[n3];
            Object[] arrobject = new Object[n3];
            System.arraycopy((Object)this.mKeys, (int)0, (Object)arrl, (int)0, (int)this.mKeys.length);
            System.arraycopy((Object)this.mValues, (int)0, (Object)arrobject, (int)0, (int)this.mValues.length);
            this.mKeys = arrl;
            this.mValues = arrobject;
        }
        this.mKeys[n2] = l2;
        this.mValues[n2] = e2;
        this.mSize = n2 + 1;
    }

    public void clear() {
        int n2 = this.mSize;
        Object[] arrobject = this.mValues;
        for (int i2 = 0; i2 < n2; ++i2) {
            arrobject[i2] = null;
        }
        this.mSize = 0;
        this.mGarbage = false;
    }

    public LongSparseArray<E> clone() {
        LongSparseArray longSparseArray;
        try {
            longSparseArray = (LongSparseArray)super.clone();
        }
        catch (CloneNotSupportedException var1_2) {
            return null;
        }
        try {
            longSparseArray.mKeys = (long[])this.mKeys.clone();
            longSparseArray.mValues = (Object[])this.mValues.clone();
            return longSparseArray;
        }
        catch (CloneNotSupportedException var2_3) {
            return longSparseArray;
        }
    }

    public void delete(long l2) {
        int n2 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, l2);
        if (n2 >= 0 && this.mValues[n2] != DELETED) {
            this.mValues[n2] = DELETED;
            this.mGarbage = true;
        }
    }

    public E get(long l2) {
        return this.get(l2, null);
    }

    public E get(long l2, E e2) {
        int n2 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, l2);
        if (n2 < 0 || this.mValues[n2] == DELETED) {
            return e2;
        }
        return (E)this.mValues[n2];
    }

    public int indexOfKey(long l2) {
        if (this.mGarbage) {
            this.gc();
        }
        return ContainerHelpers.binarySearch(this.mKeys, this.mSize, l2);
    }

    public int indexOfValue(E e2) {
        if (this.mGarbage) {
            this.gc();
        }
        for (int i2 = 0; i2 < this.mSize; ++i2) {
            if (this.mValues[i2] != e2) continue;
            return i2;
        }
        return -1;
    }

    public long keyAt(int n2) {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mKeys[n2];
    }

    public void put(long l2, E e2) {
        int n2 = ContainerHelpers.binarySearch(this.mKeys, this.mSize, l2);
        if (n2 >= 0) {
            this.mValues[n2] = e2;
            return;
        }
        int n3 = ~ n2;
        if (n3 < this.mSize && this.mValues[n3] == DELETED) {
            this.mKeys[n3] = l2;
            this.mValues[n3] = e2;
            return;
        }
        n2 = n3;
        if (this.mGarbage) {
            n2 = n3;
            if (this.mSize >= this.mKeys.length) {
                this.gc();
                n2 = ~ ContainerHelpers.binarySearch(this.mKeys, this.mSize, l2);
            }
        }
        if (this.mSize >= this.mKeys.length) {
            n3 = ContainerHelpers.idealLongArraySize(this.mSize + 1);
            long[] arrl = new long[n3];
            Object[] arrobject = new Object[n3];
            System.arraycopy((Object)this.mKeys, (int)0, (Object)arrl, (int)0, (int)this.mKeys.length);
            System.arraycopy((Object)this.mValues, (int)0, (Object)arrobject, (int)0, (int)this.mValues.length);
            this.mKeys = arrl;
            this.mValues = arrobject;
        }
        if (this.mSize - n2 != 0) {
            System.arraycopy((Object)this.mKeys, (int)n2, (Object)this.mKeys, (int)(n2 + 1), (int)(this.mSize - n2));
            System.arraycopy((Object)this.mValues, (int)n2, (Object)this.mValues, (int)(n2 + 1), (int)(this.mSize - n2));
        }
        this.mKeys[n2] = l2;
        this.mValues[n2] = e2;
        ++this.mSize;
    }

    public void remove(long l2) {
        this.delete(l2);
    }

    public void removeAt(int n2) {
        if (this.mValues[n2] != DELETED) {
            this.mValues[n2] = DELETED;
            this.mGarbage = true;
        }
    }

    public void setValueAt(int n2, E e2) {
        if (this.mGarbage) {
            this.gc();
        }
        this.mValues[n2] = e2;
    }

    public int size() {
        if (this.mGarbage) {
            this.gc();
        }
        return this.mSize;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        if (this.size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder = new StringBuilder(this.mSize * 28);
        stringBuilder.append('{');
        int n2 = 0;
        do {
            if (n2 >= this.mSize) {
                stringBuilder.append('}');
                return stringBuilder.toString();
            }
            if (n2 > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(this.keyAt(n2));
            stringBuilder.append('=');
            E e2 = this.valueAt(n2);
            if (e2 != this) {
                stringBuilder.append(e2);
            } else {
                stringBuilder.append("(this Map)");
            }
            ++n2;
        } while (true);
    }

    public E valueAt(int n2) {
        if (this.mGarbage) {
            this.gc();
        }
        return (E)this.mValues[n2];
    }
}

