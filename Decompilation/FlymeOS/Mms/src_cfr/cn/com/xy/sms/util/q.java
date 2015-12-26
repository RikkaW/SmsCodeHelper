/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.lang.Thread
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.util.ParseSmsToBubbleUtil;
import java.util.Map;

final class q
extends Thread {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ long e;
    private final /* synthetic */ boolean f;
    private final /* synthetic */ boolean g;
    private final /* synthetic */ Map h;

    q(String string2, String string3, String string4, String string5, long l2, boolean bl2, boolean bl3, Map map) {
        this.a = string2;
        this.b = string3;
        this.c = string4;
        this.d = string5;
        this.e = l2;
        this.f = bl2;
        this.g = bl3;
        this.h = map;
    }

    public final void run() {
        this.setName("xiaoyuan-parseSmsToBubble");
        ParseSmsToBubbleUtil.a(this.a, this.b, this.c, this.d, this.e, 3, this.f, this.g, this.h);
    }
}

