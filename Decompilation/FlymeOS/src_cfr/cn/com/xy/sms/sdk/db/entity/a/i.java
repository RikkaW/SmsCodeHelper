/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db.entity.a;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.g;
import cn.com.xy.sms.sdk.log.LogManager;

final class i
implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;

    i(String string2, String string3) {
        this.a = string2;
        this.b = string3;
    }

    @Override
    public final void run() {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("lastloadtime", Long.valueOf((long)System.currentTimeMillis()));
            DBManager.update("tb_public_num_info", contentValues, " num = ? ", new String[]{this.a});
            LogManager.e("pubInfo", "updateNumLoadTime: " + this.a + " areaCode: " + this.b);
            g.b(this.a, this.b);
            return;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }
}

