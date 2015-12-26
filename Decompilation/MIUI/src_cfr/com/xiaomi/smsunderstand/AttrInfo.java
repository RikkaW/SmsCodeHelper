/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smsunderstand;

public class AttrInfo {
    private int endPosition;
    private int has;
    private String name;
    private int startPosition;
    private int type;
    private String value;

    public AttrInfo(String string2, String string3, int n, int n2, int n3, int n4) {
        this.name = string2;
        this.value = string3;
        this.type = n;
        this.has = n2;
        this.startPosition = n3;
        this.endPosition = n4;
    }

    public int getEndPosition() {
        return this.endPosition;
    }

    public int getHas() {
        return this.has;
    }

    public String getName() {
        return this.name;
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    public int getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    public void setEndPosition(int n) {
        this.endPosition = n;
    }

    public void setHas(int n) {
        this.has = n;
    }

    public void setStartPosition(int n) {
        this.startPosition = n;
    }

    public void setValue(String string2) {
        this.value = string2;
    }
}

