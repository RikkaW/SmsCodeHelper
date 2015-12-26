/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smsunderstand;

import com.xiaomi.common.ACAutomationable;
import java.util.List;

public class StringAndIntArray
implements ACAutomationable {
    private List<Integer> indexs;
    private String key;

    public StringAndIntArray(String string2, List<Integer> list) {
        this.key = string2;
        this.indexs = list;
    }

    public List<Integer> getIndexs() {
        return this.indexs;
    }

    @Override
    public String getWord() {
        return this.key;
    }
}

