/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.entity.SceneRule;
import cn.com.xy.sms.sdk.db.entity.l;
import cn.com.xy.sms.sdk.db.entity.m;
import cn.com.xy.sms.sdk.db.entity.o;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.net.util.h;
import cn.com.xy.sms.sdk.util.SceneconfigUtil;
import cn.com.xy.sms.sdk.util.z;
import java.util.List;

final class t
implements XyCallBack {
    private final /* synthetic */ int a;

    t(int n2) {
        this.a = n2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... var1_1) {
        if (var1_1 == null) return;
        try {
            if (var1_1[0].toString().equals((Object)"0") == false) return;
            if (var1_1.length != 2) return;
            var1_1 = var1_1[1].toString();
            if (LogManager.debug) {
                // empty if block
            }
            if ((var1_1 = h.b((String)var1_1)) == null) return;
            if (var1_1.isEmpty() != false) return;
            var4_3 = var1_1.size();
            var2_4 = 0;
            ** GOTO lbl38
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
lbl-1000: // 1 sources:
        {
            var6_7 = var1_1.get(var2_4);
            o.b(var6_7, this.a);
            var7_8 = SceneconfigUtil.getUrls(var6_7.res_urls);
            if (var7_8 != null && !var7_8.isEmpty()) {
                var5_6 = var7_8.size();
                var3_5 = 0;
                do {
                    if (var3_5 >= var5_6) {
                        z.a(false);
                        break;
                    }
                    var8_9 = var7_8.get(var3_5);
                    var9_10 = new l();
                    var9_10.e = 0;
                    var9_10.b = var6_7.scene_id;
                    var9_10.d = 0;
                    var9_10.c = var8_9;
                    if (!m.b(var8_9)) {
                        m.a(var9_10);
                        z.a(var8_9);
                    }
                    ++var3_5;
                } while (true);
            }
            ++var2_4;
lbl38: // 2 sources:
            ** while (var2_4 < var4_3)
        }
lbl39: // 1 sources:
    }
}

