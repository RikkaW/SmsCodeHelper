/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.db.entity.q;
import cn.com.xy.sms.sdk.net.util.h;
import cn.com.xy.sms.sdk.util.SceneconfigUtil;
import java.util.ArrayList;
import java.util.List;

final class s
implements XyCallBack {
    private final /* synthetic */ List a;
    private final /* synthetic */ int b;
    private final /* synthetic */ boolean c;

    s(List list, int n2, boolean bl2) {
        this.a = list;
        this.b = n2;
        this.c = bl2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    @Override
    public final /* varargs */ void execute(Object ... var1_1) {
        block10 : {
            block11 : {
                if (var1_1 == null) ** GOTO lbl41
                if (!var1_1[0].toString().equals((Object)"0") || var1_1.length != 2) break block10;
                var1_1 = var1_1[1].toString();
                if (this.a != null && !this.a.isEmpty()) {
                    var3_3 = this.a.size();
                    var2_4 = 0;
                    break block11;
                }
lbl9: // 3 sources:
                while ((var1_1 = h.e((String)var1_1)) != null) {
                    var5_5 = (ArrayList)var1_1.get((Object)"sceneconfigList");
                    new StringBuilder("\u5904\u7406\u56de\u6765\u7684 sceneconfigList=").append(var5_5);
                    SceneconfigUtil.handleSceneconfig(var5_5, this.b);
                    var1_1 = (ArrayList)var1_1.get((Object)"sceneUrllist");
                    new StringBuilder("\u5904\u7406\u56de\u6765\u7684 sceneUrllist=").append((Object)var1_1);
                    SceneconfigUtil.handleSceneUrllist(this.a, var1_1, this.b);
                    return;
                }
                break block10;
lbl18: // 1 sources:
                var5_5 = (p)this.a.get(var2_4);
                var6_7 = var5_5.a;
                var4_6 = this.b;
                try {
                    var7_9 = new ContentValues();
                    var7_9.put("scene_id", var6_7);
                    var7_9.put("sceneType", Integer.valueOf((int)var4_6));
                    var7_9.put("isCheck", Integer.valueOf((int)1));
                    DBManager.update("tb_scene_config", var7_9, "scene_id = ? and sceneType = ?", new String[]{var6_7, String.valueOf((int)var4_6)});
                }
                catch (Throwable var6_8) {
                    ** continue;
                }
lbl29: // 2 sources:
                do {
                    try {
                        if (this.c) {
                            q.a(var5_5.a);
                        }
                        ++var2_4;
                        break;
                    }
                    catch (Throwable var1_2) {
                        var1_2.printStackTrace();
                        return;
                    }
                    break;
                } while (true);
            }
            if (var2_4 < var3_3) ** GOTO lbl18
            ** GOTO lbl9
        }
    }
}

