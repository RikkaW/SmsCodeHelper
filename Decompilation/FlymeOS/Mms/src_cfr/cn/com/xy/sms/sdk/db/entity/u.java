/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.s;
import java.util.List;

final class u
implements XyCallBack {
    private final /* synthetic */ List a;

    u(List list) {
        this.a = list;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final /* varargs */ void execute(Object ... object) {
        object = s.a(this.a);
        try {
            DBManager.delete("tb_update_task", "id IN (" + (String)object + ")", null);
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        s.b("2");
    }
}

