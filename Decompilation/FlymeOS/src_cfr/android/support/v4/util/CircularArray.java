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

public final class CircularArray<E> {
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;

    public CircularArray() {
        this(8);
    }

    public CircularArray(int n2) {
        if (n2 <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        int n3 = n2;
        if (Integer.bitCount((int)n2) != 1) {
            n3 = 1 << Integer.highestOneBit((int)n2) + 1;
        }
        this.mCapacityBitmask = n3 - 1;
        this.mElements = new Object[n3];
    }

    private void doubleCapacity() {
        int n2 = this.mElements.length;
        int n3 = n2 - this.mHead;
        int n4 = n2 << 1;
        if (n4 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] arrobject = new Object[n4];
        System.arraycopy(this.mElements, (int)this.mHead, (Object)arrobject, (int)0, (int)n3);
        System.arraycopy(this.mElements, (int)0, (Object)arrobject, (int)n3, (int)this.mHead);
        this.mElements = arrobject;
        this.mHead = 0;
        this.mTail = n2;
        this.mCapacityBitmask = n4 - 1;
    }

    public void addFirst(E e2) {
        this.mHead = this.mHead - 1 & this.mCapacityBitmask;
        this.mElements[this.mHead] = e2;
        if (this.mHead == this.mTail) {
            this.doubleCapacity();
        }
    }

    public void addLast(E e2) {
        this.mElements[this.mTail] = e2;
        this.mTail = this.mTail + 1 & this.mCapacityBitmask;
        if (this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }

    public void clear() {
        this.removeFromStart(this.size());
    }

    public E get(int n2) {
        if (n2 < 0 || n2 >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mHead + n2 & this.mCapacityBitmask];
    }

    public E getFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.mElements[this.mHead];
    }

    public E getLast() {
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

    public E popFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        E e2 = this.mElements[this.mHead];
        this.mElements[this.mHead] = null;
        this.mHead = this.mHead + 1 & this.mCapacityBitmask;
        return e2;
    }

    public E popLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n2 = this.mTail - 1 & this.mCapacityBitmask;
        E e2 = this.mElements[n2];
        this.mElements[n2] = null;
        this.mTail = n2;
        return e2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void removeFromEnd(int n2) {
        if (n2 <= 0) {
            return;
        }
        if (n2 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n3 = 0;
        if (n2 < this.mTail) {
            n3 = this.mTail - n2;
        }
        for (int i2 = n3; i2 < this.mTail; ++i2) {
            this.mElements[i2] = null;
        }
        n3 = this.mTail - n3;
        this.mTail -= n3;
        if ((n2 -= n3) <= 0) return;
        this.mTail = this.mElements.length;
        n2 = n3 = this.mTail - n2;
        do {
            if (n2 >= this.mTail) {
                this.mTail = n3;
                return;
            }
            this.mElements[n2] = null;
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void removeFromStart(int n2) {
        int n3;
        if (n2 <= 0) {
            return;
        }
        if (n2 > this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int n4 = n3 = this.mElements.length;
        if (n2 < n3 - this.mHead) {
            n4 = this.mHead + n2;
        }
        for (n3 = this.mHead; n3 < n4; ++n3) {
            this.mElements[n3] = null;
        }
        n3 = n4 - this.mHead;
        n4 = n2 - n3;
        this.mHead = n3 + this.mHead & this.mCapacityBitmask;
        if (n4 <= 0) return;
        n2 = 0;
        do {
            if (n2 >= n4) {
                this.mHead = n4;
                return;
            }
            this.mElements[n2] = null;
            ++n2;
        } while (true);
    }

    public int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}

