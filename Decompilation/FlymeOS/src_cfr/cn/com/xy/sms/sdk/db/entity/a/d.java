/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.constant.Constant;
import java.util.HashMap;

final class d
implements Runnable {
    private final /* synthetic */ String a;

    d(String string2) {
        this.a = string2;
    }

    @Override
    public final void run() {
        HashMap hashMap = new HashMap();
        hashMap.put((Object)"SUPPORT_NET_QUERY", (Object)"1");
        Constant.getContext();
        bx.a(this.a, 1, null, hashMap, null);
    }
}

