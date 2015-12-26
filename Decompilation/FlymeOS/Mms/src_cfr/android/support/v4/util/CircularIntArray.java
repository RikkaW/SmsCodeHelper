/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package android.support.v4.util;

public final class CircularIntArray {
    private int mCapacityBitmask;
    private int[] mElements;
    private int mHead;
    private int mTail;

    public CircularIntArray() {
        this(8);
    }

    public CircularIntArray(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        int n3 = n2;
        if (Integer.bitCount((int)n2) != 1) {
            n3 = 1 << Integer.highestOneBit((int)n2) + 1;
        }
        this.mCapacityBitmask = n3 - 1;
        this.mElements = new int[n3];
    }

    private void doubleCapacity() {
        int n2 = this.mElements.length;
        int n3 = n2 - this.mHead;
        int n4 = n2 << 1;
        if (n4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        int[] arrn = new int[n4];
        System.arraycopy((Object)this.mElements, (int)this.mHead, (Object)arrn, (int)0, (int)n3);
        System.arraycopy((Object)this.mElements, (int)0, (Object)arrn, (int)n3, (int)this.mHead);
        this.mElements = arrn;
        this.mHead = 0;
        this.mTail = n2;
        this.mCapacityBitmask = n4 - 1;
    }

    public void addFirst(int n2) {
        this.mHead = this.mHead - 1 & this.mCapacityBitmask;
        this.mElements[this.mHead] = n2;
        if (this.mHead == this.mTail) {
            this.doubleCapacity();
        }
    }

    public void addLast(int n2) {
        this.mElements[this.mTail] = n2;
        this.mTail = this.mTail + 1 & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }

    public void clear() {
        this.mTail = this.mHead;
    }

    public int get(int n2) {
        if (n2 < 0 || n2 >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mHead + n2 & this.mCapacityBitmask];
    }

    public int getFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mHead];
    }

    public int getLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mTail - 1 & this.mCapacityBitmask];
    }

    public boolean isEmpty() {
        if (this.mHead == this.mTail) {
            return true;
        }
        return false;
    }

    public int popFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n2 = this.mElements[this.mHead];
        this.mHead = this.mHead + 1 & this.mCapacityBitmask;
        return n2;
    }

    public int popLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n2 = this.mTail - 1 & this.mCapacityBitmask;
        int n3 = this.mElements[n2];
        this.mTail = n2;
        return n3;
    }

    public void removeFromEnd(int n2) {
        if (n2 <= 0) {
            return;
        }
        if (n2 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mTail = this.mTail - n2 & this.mCapacityBitmask;
    }

    public void removeFromStart(int n2) {
        if (n2 <= 0) {
            return;
        }
        if (n2 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        this.mHead = this.mHead + n2 & this.mCapacityBitmask;
    }

    public int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}

