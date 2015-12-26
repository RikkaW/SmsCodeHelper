/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package org.apache.thrift;

public class ShortStack {
    private int top = -1;
    private short[] vector;

    public ShortStack(int n) {
        this.vector = new short[n];
    }

    private void grow() {
        short[] arrs = new short[this.vector.length * 2];
        System.arraycopy((Object)this.vector, (int)0, (Object)arrs, (int)0, (int)this.vector.length);
        this.vector = arrs;
    }

    public void clear() {
        this.top = -1;
    }

    public short pop() {
        short[] arrs = this.vector;
        int n = this.top;
        this.top = n - 1;
        return arrs[n];
    }

    public void push(short s) {
        int n;
        if (this.vector.length == this.top + 1) {
            this.grow();
        }
        short[] arrs = this.vector;
        this.top = n = this.top + 1;
        arrs[n] = s;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<ShortStack vector:[");
        for (int i = 0; i < this.vector.length; ++i) {
            if (i != 0) {
                stringBuilder.append(" ");
            }
            if (i == this.top) {
                stringBuilder.append(">>");
            }
            stringBuilder.append(this.vector[i]);
            if (i != this.top) continue;
            stringBuilder.append("<<");
        }
        stringBuilder.append("]>");
        return stringBuilder.toString();
    }
}

