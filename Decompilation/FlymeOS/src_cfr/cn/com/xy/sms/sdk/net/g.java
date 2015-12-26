/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.net;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.net.NetWebUtil;

final class g
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ XyCallBack c;

    g(String string2, String string3, XyCallBack xyCallBack) {
        this.a = string2;
        this.b = string3;
        this.c = xyCallBack;
    }

    @Override
    public final void run() {
        NetWebUtil.a(this.a, this.b, this.c);
    }
}

