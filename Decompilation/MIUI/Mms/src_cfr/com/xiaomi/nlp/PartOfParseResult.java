/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.nlp;

public class PartOfParseResult {
    private String leftStr;
    private int nextADDStartPositon;
    private int nextStartPositionInTarget;
    private Boolean parseOK;

    public PartOfParseResult(Boolean bl, int n, int n2, String string2) {
        this.parseOK = bl;
        this.nextADDStartPositon = n;
        this.nextStartPositionInTarget = n2;
        this.leftStr = string2;
    }

    public String getLeftStr() {
        return this.leftStr;
    }

    public int getNextADDStartPositon() {
        return this.nextADDStartPositon;
    }

    public int getNextStartPositionInTarget() {
        return this.nextStartPositionInTarget;
    }

    public Boolean getParseOK() {
        return this.parseOK;
    }
}

