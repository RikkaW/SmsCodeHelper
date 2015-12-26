/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.iccid;

import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;

final class c
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;

    c(String string2, String string3, String string4) {
        this.a = string2;
        this.b = string3;
        this.c = string4;
    }

    @Override
    public final void run() {
        IccidLocationUtil.a(this.a, this.b, this.c, false);
    }
}

