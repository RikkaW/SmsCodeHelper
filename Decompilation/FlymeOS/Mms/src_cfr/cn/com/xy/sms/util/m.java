/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.util.ParseManager;
import cn.com.xy.sms.util.SdkCallBack;
import cn.com.xy.sms.util.n;

final class m
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ String f;
    private final /* synthetic */ String g;
    private final /* synthetic */ String h;
    private final /* synthetic */ SdkCallBack i;

    m(String string2, String string3, String string4, String string5, String string6, String string7, String string8, String string9, SdkCallBack sdkCallBack) {
        this.a = string2;
        this.b = string3;
        this.c = string4;
        this.d = string5;
        this.e = string6;
        this.f = string7;
        this.g = string8;
        this.h = string9;
        this.i = sdkCallBack;
    }

    @Override
    public final void run() {
        n n2 = new n(this, this.e, this.f, this.g, this.h, this.i);
        ParseManager.a(this.a, this.b, this.c, this.d, n2);
    }
}

