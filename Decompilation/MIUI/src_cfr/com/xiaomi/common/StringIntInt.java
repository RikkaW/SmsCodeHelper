/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.common;

public class StringIntInt
implements Comparable<StringIntInt> {
    private int endIndex;
    private String name;
    private int startIndex;

    public StringIntInt(String string2, int n, int n2) {
        this.startIndex = n;
        this.endIndex = n2;
        this.name = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(StringIntInt stringIntInt) {
        if (this.startIndex < stringIntInt.startIndex) {
            return -1;
        }
        if (this.startIndex > stringIntInt.startIndex) {
            return 1;
        }
        if (this.endIndex < stringIntInt.endIndex) return -1;
        if (this.endIndex <= stringIntInt.endIndex) return 0;
        return 1;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    public String getName() {
        return this.name;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    public void setEndIndex(int n) {
        this.endIndex = n;
    }

    public void setStartIndex(int n) {
        this.startIndex = n;
    }

    public String toString() {
        return String.valueOf((Object)this.name) + "\t" + this.startIndex + "\t" + this.endIndex;
    }
}

