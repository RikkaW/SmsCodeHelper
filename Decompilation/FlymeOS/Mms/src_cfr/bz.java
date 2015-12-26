/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  org.json.JSONObject
 */
import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.log.LogManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

final class bz
implements XyCallBack {
    private final /* synthetic */ ArrayList a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ boolean d;

    bz(ArrayList arrayList, String string2, String string3, boolean bl2) {
        this.a = arrayList;
        this.b = string2;
        this.c = string3;
        this.d = bl2;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    @Override
    public final /* varargs */ void execute(Object ... var1_1) {
        block8 : {
            if (var1_1 == null) ** GOTO lbl4
            try {
                if (var1_1.length > 0 && !"-1".equals((Object)var1_1[0].toString())) break block8;
lbl4: // 2 sources:
                SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf((long)System.currentTimeMillis()));
                var1_1 = bx.a;
                // MONITORENTER : var1_1
            }
            catch (Throwable var1_2) {
                if (LogManager.debug == false) return;
                var1_2.printStackTrace();
                return;
            }
            bx.b = false;
            // MONITOREXIT : var1_1
            return;
        }
        var2_3 = this.a;
        var1_1 = new ContentValues();
        var1_1.put("lastloadtime", Integer.valueOf((int)0));
        var2_3 = var2_3.iterator();
        do {
            if (!var2_3.hasNext()) {
                bx.a(this.b, this.c, this.d);
                return;
            }
            DBManager.update("tb_public_num_info", (ContentValues)var1_1, "num = ?", new String[]{new JSONObject((String)var2_3.next()).getString("num")});
        } while (true);
    }
}

