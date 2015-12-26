/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 */
package cn.com.xy.sms.sdk.log;

public class TimeLog {
    public long currentTime = 0;
    public long startTime = this.currentTime = System.currentTimeMillis();

    public void log(String string2, String string3) {
        long l2 = System.currentTimeMillis();
        new StringBuilder("timeLog: ").append(string3).append(" stTime: ").append(l2 - this.startTime).append(" lastTime: ").append(l2 - this.currentTime);
        this.currentTime = l2;
    }
}

