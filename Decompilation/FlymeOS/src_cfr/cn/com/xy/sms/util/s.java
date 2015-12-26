/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.util.PublicInfoParseManager;
import cn.com.xy.sms.util.SdkCallBack;

final class s
implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;
    private final /* synthetic */ int f;
    private final /* synthetic */ int g;
    private final /* synthetic */ SdkCallBack h;

    s(Context context, String string2, String string3, String string4, String string5, int n2, int n3, SdkCallBack sdkCallBack) {
        this.a = context;
        this.b = string2;
        this.c = string3;
        this.d = string4;
        this.e = string5;
        this.f = n2;
        this.g = n3;
        this.h = sdkCallBack;
    }

    @Override
    public final void run() {
        PublicInfoParseManager.a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h);
    }
}

