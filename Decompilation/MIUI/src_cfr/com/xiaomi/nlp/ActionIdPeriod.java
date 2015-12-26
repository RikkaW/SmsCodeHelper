/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.System
 */
package com.xiaomi.nlp;

public class ActionIdPeriod {
    private int actionId;
    private long endPeriodOfValidity = 4102416000000L;
    private long startPeriodOfValidity = 1104508800000L;

    public ActionIdPeriod(int n) {
        this.actionId = n;
    }

    public ActionIdPeriod(int n, long l, long l2) {
        this.actionId = n;
        this.startPeriodOfValidity = l;
        this.endPeriodOfValidity = l2;
    }

    public int getActionId() {
        return this.actionId;
    }

    public boolean matchPeriod() {
        long l = System.currentTimeMillis();
        if (this.startPeriodOfValidity > l || this.endPeriodOfValidity < l) {
            return false;
        }
        return true;
    }
}

