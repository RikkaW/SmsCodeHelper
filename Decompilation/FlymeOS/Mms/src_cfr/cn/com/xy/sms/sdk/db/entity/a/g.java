/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.util.SdkCallBack;

final class g
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ SdkCallBack f;
    private final /* synthetic */ boolean g;

    g(String string2, String string3, String string4, String string5, String string6, SdkCallBack sdkCallBack, boolean bl2) {
        this.a = string2;
        this.b = string3;
        this.c = string4;
        this.d = string5;
        this.e = string6;
        this.f = sdkCallBack;
        this.g = bl2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public final void run() {
        if (NetUtil.isEnhance()) {
            bs.a(false, this.a, this.b, this.c, this.d, this.e, this.f, 1, false, this.g);
        } else if (this.f != null) {
            this.f.execute(new Object[0]);
        }
        if (NetUtil.checkAccessNetWork(1)) {
            bx.a(this.c, this.d);
        }
    }
}

