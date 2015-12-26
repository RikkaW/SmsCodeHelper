/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.nlp;

import com.xiaomi.common.ConstraintACAutomationable;
import java.util.List;

public class ParserKeywords
implements ConstraintACAutomationable {
    private String keyWords;
    private int[] toPatIndex;
    private int type = 1;

    public ParserKeywords(String string2, int n) {
        this.keyWords = string2;
        this.type = n;
    }

    public void addPatIndex(List<Integer> list) {
        this.toPatIndex = new int[list.size()];
        int n = 0;
        while (n < list.size()) {
            this.toPatIndex[n] = list.get(n);
            ++n;
        }
        return;
    }

    public String getKeyWords() {
        return this.keyWords;
    }

    public int[] getPatIndex() {
        return this.toPatIndex;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getWord() {
        return this.keyWords;
    }

    public boolean isEndkeyWords() {
        if ((this.type & 4) > 0) {
            return true;
        }
        return false;
    }

    public boolean isStartKeyWords() {
        if ((this.type & 2) > 0) {
            return true;
        }
        return false;
    }

    public boolean isStartKeywordsContainsWildcard() {
        if ((this.type & 16) > 0) {
            return true;
        }
        return false;
    }

    public boolean isTypeEqual(int n) {
        if ((this.type & n) > 0) {
            return true;
        }
        return false;
    }

    public void setKeyWordsType(int n) {
        this.type |= n;
    }

    public String toString() {
        return String.valueOf((Object)this.keyWords) + ":" + this.type;
    }
}

