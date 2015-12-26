/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.mms.mx.audio.player;

public class PlayerStatus {
    public final int mDuration;
    public final int mProgress;
    public final int mType;

    public PlayerStatus(int n) {
        this(n, 0, 0);
    }

    public PlayerStatus(int n, int n2, int n3) {
        this.mType = n;
        this.mDuration = n2;
        this.mProgress = n3;
    }

    public String toString() {
        return String.format((String)"Plat Status, type = %1$s, duration = %2$s, progress = %3$s", (Object[])new Object[]{this.mType, this.mDuration, this.mProgress});
    }
}

