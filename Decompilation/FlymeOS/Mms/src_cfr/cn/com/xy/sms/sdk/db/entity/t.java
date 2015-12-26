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
import cn.com.xy.sms.sdk.db.entity.r;
import cn.com.xy.sms.sdk.db.entity.s;

final class t
implements XyCallBack {
    private final /* synthetic */ r a;

    t(r r2) {
        this.a = r2;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public final /* varargs */ void execute(Object ... arrobject) {
        long l2 = this.a.b;
        try {
            DBManager.delete("tb_update_task", "id = ?", new String[]{String.valueOf((long)l2)});
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
        }
        s.b("1");
    }
}

