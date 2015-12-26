/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  org.json.JSONObject
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.TrainManager;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.net.util.h;
import cn.com.xy.sms.sdk.util.XyUtil;
import org.json.JSONObject;

final class g
implements XyCallBack {
    private final /* synthetic */ XyCallBack a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ String d;
    private final /* synthetic */ String e;

    g(XyCallBack xyCallBack, String string2, String string3, String string4, String string5) {
        this.a = xyCallBack;
        this.b = string2;
        this.c = string3;
        this.d = string4;
        this.e = string5;
    }

    @Override
    public final /* varargs */ void execute(Object ... sQLiteDatabase) {
        SQLiteDatabase sQLiteDatabase2;
        SQLiteDatabase sQLiteDatabase3;
        block13 : {
            if (sQLiteDatabase == null || sQLiteDatabase.length != 2 || !sQLiteDatabase[0].toString().equals((Object)"0")) {
                XyUtil.doXycallBackResult(this.a, this.b);
                return;
            }
            SQLiteDatabase sQLiteDatabase4 = null;
            SQLiteDatabase sQLiteDatabase5 = null;
            SQLiteDatabase sQLiteDatabase6 = null;
            sQLiteDatabase2 = sQLiteDatabase4;
            sQLiteDatabase3 = sQLiteDatabase5;
            JSONObject jSONObject = h.g(sQLiteDatabase[1].toString());
            sQLiteDatabase = sQLiteDatabase6;
            if (jSONObject == null) break block13;
            sQLiteDatabase = sQLiteDatabase6;
            sQLiteDatabase2 = sQLiteDatabase4;
            sQLiteDatabase3 = sQLiteDatabase5;
            if (jSONObject.length() <= 0) break block13;
            sQLiteDatabase2 = sQLiteDatabase4;
            sQLiteDatabase3 = sQLiteDatabase5;
            sQLiteDatabase2 = sQLiteDatabase6 = DBManager.getSQLiteDatabase();
            sQLiteDatabase3 = sQLiteDatabase6;
            jSONObject.put("cc", (Object)this.c);
            sQLiteDatabase2 = sQLiteDatabase6;
            sQLiteDatabase3 = sQLiteDatabase6;
            sQLiteDatabase4 = TrainManager.getContentValues(jSONObject);
            sQLiteDatabase = sQLiteDatabase6;
            if (sQLiteDatabase4 == null) break block13;
            sQLiteDatabase2 = sQLiteDatabase6;
            sQLiteDatabase3 = sQLiteDatabase6;
            BaseManager.saveOrUpdate(sQLiteDatabase6, "tb_train", (ContentValues)sQLiteDatabase4, "train_num = ? ", new String[]{this.c});
            sQLiteDatabase2 = sQLiteDatabase6;
            sQLiteDatabase3 = sQLiteDatabase6;
            XyUtil.doXycallBackResult(this.a, new Object[]{this.b, jSONObject, this.c, this.d, this.e, true});
            DBManager.close(sQLiteDatabase6);
            return;
        }
        sQLiteDatabase2 = sQLiteDatabase;
        sQLiteDatabase3 = sQLiteDatabase;
        try {
            XyUtil.doXycallBackResult(this.a, this.b);
        }
        catch (Throwable var1_2) {
            sQLiteDatabase3 = sQLiteDatabase2;
            try {
                var1_2.printStackTrace();
                sQLiteDatabase3 = sQLiteDatabase2;
            }
            catch (Throwable var1_3) {
                DBManager.close(sQLiteDatabase3);
                throw var1_3;
            }
            XyUtil.doXycallBackResult(this.a, this.b);
            DBManager.close(sQLiteDatabase2);
            return;
        }
        DBManager.close(sQLiteDatabase);
        return;
    }
}

