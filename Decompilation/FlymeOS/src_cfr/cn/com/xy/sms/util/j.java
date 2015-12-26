/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.util.ParseManager;

final class j
implements Runnable {
    private final /* synthetic */ String a;

    j(String string2) {
        this.a = string2;
    }

    @Override
    public final void run() {
        try {
            String string2 = DexUtil.getBubbleViewVersion(null);
            ParseManager.addViewVersion(this.a, string2);
            return;
        }
        catch (Throwable var1_2) {
            return;
        }
    }
}

