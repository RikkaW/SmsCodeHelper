/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.net.util;

import android.content.Context;
import cn.com.xy.sms.sdk.net.util.b;

final class c
extends Thread {
    private final /* synthetic */ Context a;

    c(Context context) {
        this.a = context;
    }

    public final void run() {
        try {
            b.b(this.a);
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }
}

