/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Process
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 */
package cn.com.xy.sms.util;

import android.os.Process;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.c;
import cn.com.xy.sms.sdk.db.entity.f;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.queue.g;
import cn.com.xy.sms.sdk.util.XyUtil;
import cn.com.xy.sms.sdk.util.i;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.Map;

final class h
implements Runnable {
    private final /* synthetic */ Map a;
    private final /* synthetic */ SdkCallBack b;

    h(Map map, SdkCallBack sdkCallBack) {
        this.a = map;
        this.b = sdkCallBack;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        Thread.currentThread().setName("xiaoyuan_pool_netutil");
        Process.setThreadPriority((int)g.b);
        try {
            if (!NetUtil.checkAccessNetWork(this.a)) {
                XyUtil.doXycallBack(this.b, "-1");
                return;
            }
            if (!ParseItemManager.isInitData()) {
                XyUtil.doXycallBack(this.b, "2");
                return;
            }
            bx.a(this.a, null);
            Object object = c.c();
            if (!c.c((f)object)) {
                Map map = this.a;
                if (!i.b()) {
                    XyUtil.doXycallBack(this.b, "2");
                    return;
                }
            }
            if (c.c((f)object)) {
                c.a((f)object);
            }
            object = this.a;
            if (i.b()) {
                i.a(this.a, this.b);
                return;
            }
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
        if (c.c(c.c())) {
            XyUtil.doXycallBack(this.b, "0");
            return;
        }
        XyUtil.doXycallBack(this.b, "1");
    }
}

