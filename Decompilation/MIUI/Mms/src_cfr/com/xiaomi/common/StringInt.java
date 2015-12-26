/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.common;

import com.xiaomi.common.ACAutomationable;

public class StringInt
implements ACAutomationable,
Comparable<StringInt> {
    private String name;
    private int num;

    public StringInt() {
    }

    public StringInt(int n, String string2) {
        this.num = n;
        this.name = string2;
    }

    public StringInt(String string2, int n) {
        this.num = n;
        this.name = string2;
    }

    @Override
    public int compareTo(StringInt stringInt) {
        if (this.num > stringInt.num) {
            return 1;
        }
        if (this.num == stringInt.num) {
            return 0;
        }
        return -1;
    }

    public String getName() {
        return this.name;
    }

    public int getNum() {
        return this.num;
    }

    @Override
    public String getWord() {
        return this.name;
    }

    public String toString() {
        return String.valueOf((Object)this.name) + ":" + this.num;
    }
}

