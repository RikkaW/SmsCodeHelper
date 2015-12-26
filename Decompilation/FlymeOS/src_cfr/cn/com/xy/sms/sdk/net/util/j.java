/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;

final class j
implements Runnable {
    private final /* synthetic */ String a;

    j(String string2) {
        this.a = string2;
    }

    @Override
    public final void run() {
        String string2 = this.a;
        try {
            DBManager.insert("tb_update_task", BaseManager.getContentValues(null, "content", string2, "t_group", "2", "t_version", "0"));
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

