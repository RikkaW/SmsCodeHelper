/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.a.a;
import cn.com.xy.sms.sdk.db.e;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.util.ArrayList;
import java.util.HashMap;

public class ParseItemManager {
    public static final String CREATE_INDEX = "create index if not exists indx_s_m on tb_regex (scene_id,match_id)";
    public static final String CREATE_TABLE = "create table  if not exists tb_regex (scene_id TEXT,match_id TEXT,regex_text TEXT,version_code TEXT,regex_type INTEGER  DEFAULT '0',state INTEGER  DEFAULT '0' )";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_regex";
    public static final int INITTAG = -2;
    public static final String MATCH_ID = "match_id";
    public static final int NEEDDEL = -1;
    public static final int NORMAL = 0;
    public static final String REGEX_TEXT = "regex_text";
    public static final String REGEX_TYPE = "regex_type";
    public static final int REGEX_TYPE_AD = 2;
    public static final int REGEX_TYPE_SCENE = 1;
    public static final String SCENE_ID = "scene_id";
    public static final String STATE = "state";
    public static final String TABLE_NAME = "tb_regex";
    public static final String VERSION_CODE = "version_code";
    private static boolean a = false;
    private static boolean b = false;
    private static HashMap<Long, SQLiteDatabase> c = new HashMap();
    private static boolean d = false;
    public static boolean execNqSql = false;

    private static void a() {
        synchronized (ParseItemManager.class) {
            long l2 = Thread.currentThread().getId();
            a.a((SQLiteDatabase)c.remove((Object)l2));
            return;
        }
    }

    private static void a(int n2) {
        try {
            a.a("tb_regex", "state=? ", new String[]{"-1"});
            return;
        }
        catch (Throwable var1_1) {
            var1_1.printStackTrace();
            return;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static void a(String var0, SQLiteDatabase var1_1) {
        block9 : {
            var3_2 = 0;
            if (StringUtils.isNull(var0) || var0.indexOf("values") == -1) ** GOTO lbl-1000
            var4_3 = var0.substring(0, var0.indexOf("values"));
            var0 = var0.replace((CharSequence)var4_3, (CharSequence)"");
            var4_3 = String.valueOf((Object)var4_3) + " values ";
            var5_5 = var0.replace((CharSequence)"values", (CharSequence)"").split("'\\),\\('");
            var0 = new ArrayList();
            if (var5_5 == null || var5_5.length <= 0) ** GOTO lbl12
            var2_6 = 0;
            block2 : do {
                if (var2_6 < var5_5.length) ** GOTO lbl17
lbl12: // 2 sources:
                if (var0.isEmpty()) ** GOTO lbl-1000
                var2_6 = var3_2;
lbl14: // 2 sources:
                if (var2_6 >= var0.size()) lbl-1000: // 3 sources:
                {
                    return;
                }
                break block9;
lbl17: // 1 sources:
                if (!StringUtils.isNull(var5_5[var2_6])) {
                    var6_7 = var5_5[var2_6].trim();
                    if (!var6_7.startsWith("(")) break;
                    var0.add(String.valueOf((Object)var4_3) + " " + var6_7 + "')");
                }
lbl21: // 6 sources:
                do {
                    ++var2_6;
                    continue block2;
                    break;
                } while (true);
                break;
            } while (true);
            if (!var6_7.endsWith(";")) ** GOTO lbl27
            var0.add(String.valueOf((Object)var4_3) + " ('" + var6_7);
            ** GOTO lbl21
lbl27: // 1 sources:
            if (!var6_7.endsWith(")")) ** GOTO lbl30
            var0.add(String.valueOf((Object)var4_3) + " ('" + var6_7 + "')");
            ** GOTO lbl21
lbl30: // 1 sources:
            var0.add(String.valueOf((Object)var4_3) + " ('" + var6_7 + "')");
            ** while (true)
        }
        var4_3 = (String)var0.get(var2_6);
        if (!StringUtils.isNull(var4_3)) {
            var1_1.execSQL(var4_3);
        }
lbl37: // 4 sources:
        do {
            ++var2_6;
            ** GOTO lbl14
            break;
        } while (true);
        catch (Throwable var4_4) {
            var4_4.printStackTrace();
            ** continue;
        }
    }

    public static void checkHasData() {
        LogManager.e("xiaoyuan_test", "checkHasData isCheckHasData: " + b, null);
        if (b) {
            return;
        }
        b = true;
        new e().start();
    }

    public static void closeSpecialDatebase() {
        ParseItemManager.a();
    }

    public static void deleteAll() {
        try {
            a.a("tb_regex", null, null);
            return;
        }
        catch (Throwable var0) {
            var0.printStackTrace();
            return;
        }
    }

    public static void deleteRepeatData() {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = null;
        SQLiteDatabase sQLiteDatabase3 = null;
        sQLiteDatabase3 = sQLiteDatabase = a.a();
        sQLiteDatabase2 = sQLiteDatabase;
        try {
            sQLiteDatabase.execSQL("DELETE FROM tb_regex WHERE state=-2 AND match_id IN (SELECT match_id FROM tb_regex GROUP BY match_id HAVING COUNT(match_id) > 1)");
        }
        catch (Throwable var2_4) {
            sQLiteDatabase2 = sQLiteDatabase3;
            try {
                var2_4.printStackTrace();
            }
            catch (Throwable var0_2) {
                a.a(sQLiteDatabase2);
                throw var0_2;
            }
            a.a(sQLiteDatabase3);
            return;
        }
        a.a(sQLiteDatabase);
        return;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static String getParsePatternString(String var0, String var1_4, int var2_5) {
        block19 : {
            block17 : {
                block16 : {
                    block18 : {
                        var4_6 = null;
                        // MONITORENTER : cn.com.xy.sms.sdk.db.ParseItemManager.class
                        var5_10 = ParseItemManager.getPatterDb();
                        var3_11 = ParseItemManager.d;
                        if (var3_11) {
                            var1_4 = null;
                            return var1_4;
                        }
                        Constant.getContext();
                        var0 = a.a(var5_10, false, "tb_regex", new String[]{"regex_text"}, "scene_id = ? and  match_id = ? and regex_type = ?", new String[]{var0, var1_4, String.valueOf((int)var2_5)}, null, null, null, "1");
                        if (var0 == null) break block16;
                        var1_4 = var0;
                        if (var0.getCount() <= 0) break block16;
                        var1_4 = var0;
                        var2_5 = var0.getColumnIndex("regex_text");
                        var1_4 = var0;
                        if (!var0.moveToNext()) break block17;
                        var1_4 = var0;
                        var1_4 = var4_6 = var0.getString(var2_5);
                        if (var0 != null) break block18;
                        // MONITOREXIT : cn.com.xy.sms.sdk.db.ParseItemManager.class
                        return var1_4;
                    }
                    XyCursor.closeCursor((XyCursor)var0, false);
                    return var4_6;
                }
                var1_4 = var0;
                try {
                    ParseItemManager.checkHasData();
                }
                catch (Throwable var4_9) {
                    ** continue;
                }
            }
            if (var0 == null) return null;
            XyCursor.closeCursor((XyCursor)var0, false);
            return null;
            catch (Throwable var4_7) {
                var0 = null;
                ** GOTO lbl41
                catch (Throwable var0_3) {
                    var1_4 = var4_6;
                    break block19;
                }
lbl41: // 2 sources:
                do {
                    var1_4 = var0;
                    try {
                        var4_8.printStackTrace();
                        if (var0 == null) return null;
                    }
                    catch (Throwable var0_2) {
                        break;
                    }
                    XyCursor.closeCursor((XyCursor)var0, false);
                    return null;
                    break;
                } while (true);
            }
        }
        if (var1_4 == null) throw var0_1;
        XyCursor.closeCursor((XyCursor)var1_4, false);
        throw var0_1;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static SQLiteDatabase getPatterDb() {
        // MONITORENTER : cn.com.xy.sms.sdk.db.ParseItemManager.class
        var0 = Thread.currentThread().getId();
        var3_1 = (SQLiteDatabase)ParseItemManager.c.get((Object)var0);
        if (var3_1 == null) ** GOTO lbl-1000
        var2_2 = var3_1;
        if (!var3_1.isOpen()) lbl-1000: // 2 sources:
        {
            var2_2 = a.a();
            ParseItemManager.c.put((Object)var0, (Object)var2_2);
        }
        // MONITOREXIT : cn.com.xy.sms.sdk.db.ParseItemManager.class
        return var2_2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isInitData() {
        if (!a) {
            boolean bl2 = SysParamEntityManager.getLongParam("init_xiaoyuan_sdk", 0, Constant.getContext()) == 1;
            a = bl2;
        }
        return a;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void updateNeiQianSql(Context object) {
        object = Constant.getNQSQL_PATH();
        if (!d.a((String)object)) return;
        try {
            execNqSql = true;
            DBManager.excSql((String)object, true);
            execNqSql = false;
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
            return;
        }
        finally {
            execNqSql = false;
        }
        return;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void updateParse(Context var0) {
        var0 = Constant.getInidb_PATH();
        if (!d.a((String)var0)) {
            return;
        }
        try {
            ParseItemManager.updateStatue(0, -1);
            ParseItemManager.d = true;
            a.a((String)var0, false);
            ParseItemManager.d = false;
            Constant.getContext();
            {
                catch (Throwable var1_2) {
                    ParseItemManager.updateStatue(-1, 0);
                    ParseItemManager.d = false;
                    a.a((String)var0, true, null, null, null);
                    return;
                }
            }
            try {
                a.a("tb_regex", "state=? ", new String[]{"-1"});
            }
            catch (Throwable var1_1) {
                var1_1.printStackTrace();
                ** continue;
            }
lbl25: // 2 sources:
            do {
                d.d((String)var0);
                return;
                break;
            } while (true);
        }
        finally {
            ParseItemManager.d = false;
            a.a((String)var0, true, null, null, null);
        }
    }

    public static void updateStatue(int n2, int n3) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("state", Integer.valueOf((int)n3));
            a.a("tb_regex", contentValues, "state = ? ", new String[]{String.valueOf((int)n2)});
            return;
        }
        catch (Throwable var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }
}

