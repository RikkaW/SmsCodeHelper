/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.common;

import com.xiaomi.common.ACAutomationable;
import com.xiaomi.nlp.KeyWordAccessable;

public class StringString
implements ACAutomationable,
KeyWordAccessable,
Comparable<StringString> {
    private String first;
    private String second;

    public StringString(String string2, String string3) {
        this.first = string2;
        this.second = string3;
    }

    @Override
    public int compareTo(StringString stringString) {
        return this.first.compareTo(stringString.first);
    }

    public String getFirst() {
        return this.first;
    }

    @Override
    public String getKeyWord() {
        return this.first;
    }

    public String getSecond() {
        return this.second;
    }

    @Override
    public String getWord() {
        return this.first;
    }
}

