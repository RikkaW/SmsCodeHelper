/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.DuoquUtils;
import java.util.Map;

final class r
extends Thread {
    private final /* synthetic */ String a;
    private final /* synthetic */ Map b;

    r(String string2, Map map) {
        this.a = string2;
        this.b = map;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void run() {
        Map<String, Object> map;
        int n2;
        this.setName("xiaoyuan-parseSmsToBubble1");
        try {
            n2 = Integer.valueOf((String)this.a);
            map = DexUtil.handerValueMapByType(n2, this.b);
            if (map == null) return;
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return;
        }
        DuoquUtils.getSdkDoAction().parseMsgCallBack(n2, map);
    }
}

