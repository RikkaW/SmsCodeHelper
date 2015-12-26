/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db.entity.a;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.g;

final class h
implements Runnable {
    private final /* synthetic */ int a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ int d;
    private final /* synthetic */ String e;
    private final /* synthetic */ String f;

    h(int n2, String string2, String string3, int n3, String string4, String string5) {
        this.a = n2;
        this.b = string2;
        this.c = string3;
        this.d = n3;
        this.e = string4;
        this.f = string5;
    }

    @Override
    public final void run() {
        try {
            DBManager.saveOrUpdateTableData("tb_public_num_info", BaseManager.getContentValues(null, "pubId", String.valueOf((int)this.a), "areaCode", this.b, "num", this.c, "ptype", String.valueOf((int)this.d), "lastloadtime", String.valueOf((long)System.currentTimeMillis()), "isrulenum", "1", "purpose", this.e, "extend", this.f), " num = ? and ptype = ? ", new String[]{this.c, String.valueOf((int)this.d)});
            g.b(this.c, this.b);
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }
}

