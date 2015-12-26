/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.util;

import android.content.Context;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.l;
import cn.com.xy.sms.sdk.queue.i;
import cn.com.xy.sms.sdk.util.DateUtils;
import cn.com.xy.sms.sdk.util.e;
import cn.com.xy.sms.util.ParseManager;
import java.util.Map;

final class g
implements Runnable {
    private final /* synthetic */ Context a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ boolean d;
    private final /* synthetic */ boolean e;
    private final /* synthetic */ Map f;

    g(Context context, String string2, String string3, boolean bl2, boolean bl3, Map map) {
        this.a = context;
        this.b = string2;
        this.c = string3;
        this.d = bl2;
        this.e = bl3;
        this.f = map;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final void run() {
        try {
            Context context;
            Object object = context = this.a.getApplicationContext();
            if (context == null) {
                object = this.a;
            }
            long l2 = System.currentTimeMillis();
            SysParamEntityManager.initParams((Context)object, this.b, this.c, this.d, this.e, this.f);
            new StringBuilder("initParams time=").append(System.currentTimeMillis() - l2);
            cn.com.xy.sms.sdk.queue.g.a();
            e.b();
            object = DexUtil.getBubbleViewVersion(null);
            ParseManager.addViewVersion(DateUtils.getCurrentTimeString("yyyyMMdd"), (String)object);
            cn.com.xy.sms.sdk.queue.g.a(new i(2, new String[0]));
            cn.com.xy.sms.sdk.queue.g.a(new i(7, new String[0]));
            object = this.b;
            l.a();
            return;
        }
        catch (Throwable var3_3) {
            LogManager.e("XIAOYUAN", "ParseManager.initSdk " + var3_3.getMessage(), var3_3);
            return;
        }
    }
}

