/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.ParseItemManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.a.a;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.util.d;
import java.util.HashMap;

final class e
extends Thread {
    e() {
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public final void run() {
        block16 : {
            block17 : {
                this.setName("xiaoyuan");
                var2_1 = a.a(false, "tb_regex", new String[]{"regex_text"}, null, null, null, null, null, "1");
                if (var2_1 == null) break block16;
                var1_5 = var2_1;
                if (var2_1.getCount() > 0) break block16;
lbl8: // 2 sources:
                var1_5 = var2_1;
                cn.com.xy.sms.sdk.db.entity.e.a("parseUtilMain", "1", 1);
                var1_5 = var2_1;
                SysParamEntityManager.cacheMap.clear();
                var1_5 = var2_1;
                cn.com.xy.sms.sdk.util.e.c();
                var1_5 = var2_1;
                if (!d.a(Constant.getInidb_PATH())) break block17;
                var1_5 = var2_1;
                try {
                    ParseItemManager.updateParse(Constant.getContext());
                }
                catch (Throwable var3_8) {
                    ** GOTO lbl29
                }
            }
lbl24: // 2 sources:
            do {
                XyCursor.closeCursor(var2_1, true);
                return;
                break;
            } while (true);
            catch (Throwable var3_6) {
                var2_1 = null;
lbl29: // 2 sources:
                var1_5 = var2_1;
                var3_7.printStackTrace();
                XyCursor.closeCursor(var2_1, true);
                return;
            }
            catch (Throwable var2_2) {
                var1_5 = null;
lbl36: // 2 sources:
                do {
                    XyCursor.closeCursor(var1_5, true);
                    throw var2_3;
                    break;
                } while (true);
            }
            {
                catch (Throwable var2_4) {
                    ** continue;
                }
            }
        }
        ** while (var2_1 != null)
lbl45: // 1 sources:
        ** GOTO lbl8
    }
}

