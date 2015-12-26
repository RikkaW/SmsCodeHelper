/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.queue;

import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.base.BaseManager;
import cn.com.xy.sms.sdk.db.entity.p;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class i {
    private static String c = "scene_id";
    private static String d = "count";
    private static String e = "tb_count_scene";
    private static String f = " DROP TABLE IF EXISTS tb_count_scene";
    private static String g = "create table  if not exists tb_count_scene (scene_id TEXT,count INT)";
    int a;
    HashMap<String, String> b;

    public i() {
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public /* varargs */ i(int n2, String ... arrstring) {
        this.b = null;
        this.a = n2;
        if (arrstring.length % 2 != 0 || arrstring.length <= 0) return;
        this.b = new HashMap();
        int n3 = arrstring.length;
        n2 = 0;
        while (n2 < n3) {
            if (arrstring[n2 + 1] != null && !arrstring[n2 + 1].equals((Object)"")) {
                this.b.put((Object)arrstring[n2], (Object)arrstring[n2 + 1]);
            }
            n2 += 2;
        }
        return;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    public static long a(HashMap<String, String> var0) {
        block13 : {
            block14 : {
                var3_1 = null;
                var4_7 = (String)var0.get((Object)"titleNo");
                if (StringUtils.isNull(var4_7)) break block13;
                var0 = DBManager.query("tb_count_scene", new String[]{"scene_id", "count"}, "scene_id = ? ", new String[]{var4_7});
lbl6: // 2 sources:
                if (var0 != null) {
                    if (var0.getCount() <= 0) break block14;
                    var0.moveToNext();
                    DBManager.update("tb_count_scene", BaseManager.getContentValues(null, new String[]{"scene_id", var4_7, "count", String.valueOf((int)(var0.getInt(var0.getColumnIndex("count")) + 1))}), "scene_id = ? ", new String[]{var4_7});
                    XyCursor.closeCursor((XyCursor)var0, true);
                    return 0;
                }
            }
            try {
                var1_9 = DBManager.insert("tb_count_scene", BaseManager.getContentValues(null, new String[]{"scene_id", var4_7, "count", "1"}));
            }
            catch (Throwable var3_4) {
                ** GOTO lbl27
            }
            XyCursor.closeCursor((XyCursor)var0, true);
            return var1_9;
            catch (Throwable var4_8) {
                var0 = var3_1;
                var3_1 = var4_8;
lbl22: // 3 sources:
                var3_1.printStackTrace();
                XyCursor.closeCursor((XyCursor)var0, true);
                return -1;
            }
            catch (Throwable var3_2) {
                var0 = null;
lbl27: // 3 sources:
                do {
                    XyCursor.closeCursor((XyCursor)var0, true);
                    throw var3_3;
                    break;
                } while (true);
            }
            {
                catch (Throwable var3_5) {
                    ** continue;
                }
            }
            catch (Throwable var3_6) {
                ** GOTO lbl22
            }
        }
        var0 = null;
        ** GOTO lbl6
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static List<p> a() {
        block9 : {
            var4 = null;
            var3_1 = null;
            var6_3 = new ArrayList();
            try {
                var5_4 = DBManager.query("tb_count_scene", new String[]{"scene_id", "count"}, null, null);
                if (var5_4 != null) {
                    var3_1 = var5_4;
                    var4 = var5_4;
                    if (var5_4.getCount() > 0) {
                        var3_1 = var5_4;
                        var4 = var5_4;
                        var0_6 = var5_4.getColumnIndex("scene_id");
                        var3_1 = var5_4;
                        var4 = var5_4;
                        var1_7 = var5_4.getColumnIndex("count");
                        do {
                            var3_1 = var5_4;
                            var4 = var5_4;
                            var2_8 = var5_4.moveToNext();
                            if (var2_8) {
                                var3_1 = var5_4;
                                var4 = var5_4;
                                var7_9 = new p();
                                var3_1 = var5_4;
                                var4 = var5_4;
                                var7_9.a = var5_4.getString(var0_6);
                                var3_1 = var5_4;
                                var4 = var5_4;
                                var7_9.c = var5_4.getInt(var1_7);
                                var3_1 = var5_4;
                                var4 = var5_4;
                                var6_3.add(var7_9);
                                continue;
                            }
                            ** GOTO lbl45
                            break;
                        } while (true);
                    }
                }
                break block9;
                catch (Throwable var5_5) {
                    var4 = var3_1;
                    var5_5.printStackTrace();
                    XyCursor.closeCursor(var3_1, true);
                    return var6_3;
                }
            }
            catch (Throwable var3_2) {
                XyCursor.closeCursor(var4, true);
                throw var3_2;
            }
        }
        XyCursor.closeCursor(var5_4, true);
        return var6_3;
    }

    private static void a(String string2) {
        try {
            DBManager.delete("tb_count_scene", "scene_id = ?", new String[]{string2});
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void b() {
        try {
            DBManager.delete("tb_count_scene", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }
}

