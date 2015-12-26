/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.mms.data;

public class MxCapability {
    private long mCapability = Integer.MIN_VALUE;

    public MxCapability(long l) {
        this.mCapability = l;
    }

    public boolean allowAudio() {
        if ((this.mCapability & 0x200000000L) > 0) {
            return true;
        }
        return false;
    }

    public boolean allowMms() {
        if ((this.mCapability & 2) > 0) {
            return true;
        }
        return false;
    }

    public boolean allowMxV2() {
        if ((this.mCapability & 0x400000000L) > 0) {
            return true;
        }
        return false;
    }

    public boolean allowSms() {
        if ((this.mCapability & 1) > 0) {
            return true;
        }
        return false;
    }
}

