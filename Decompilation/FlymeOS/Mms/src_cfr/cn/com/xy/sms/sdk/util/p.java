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
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.net.util.h;

final class p
implements XyCallBack {
    private final /* synthetic */ String a;

    p(String string2) {
        this.a = string2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final /* varargs */ void execute(Object ... object) {
        if (object != null && object[0].toString().equals((Object)"0") && object.length == 2 && h.d(object[1].toString())) {
            SysParamEntityManager.setParam("LastSceneCountActionUpdate", this.a);
            object = this.a;
            try {
                DBManager.delete("tb_popup_action_scene", "date < ?", new String[]{object});
            }
            catch (Throwable var1_2) {
                var1_2.printStackTrace();
            }
            k.a(this.a);
        }
    }
}

