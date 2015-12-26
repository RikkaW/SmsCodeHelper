/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.a;
import cn.com.xy.sms.sdk.util.XyUtil;
import java.io.LineNumberReader;

public class AirManager {
    public static final String AIR_NUM = "air_num";
    public static final String COMPANY = "company";
    public static final String CREATE_TABLE = "create table  if not exists tb_air (id INTEGER PRIMARY KEY,air_num TEXT not null unique,start_city TEXT,end_city TEXT,start_place TEXT,end_place TEXT,start_time TEXT,end_time TEXT,company TEXT)";
    public static final String DROP_TABLE = " DROP TABLE IF EXISTS tb_air";
    public static final String END_CITY = "end_city";
    public static final String END_PALCE = "end_place";
    public static final String END_TIME = "end_time";
    public static final String ID = "id";
    public static final String START_CITY = "start_city";
    public static final String START_PLACE = "start_place";
    public static final String START_TIME = "start_time";
    public static final String TABLE_NAME = "tb_air";

    public static boolean checkUpdateData() {
        new a().start();
        return true;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void importAirData(Context var0) {
        block24 : {
            var5_1 = String.valueOf((Object)Constant.getDRAWBLE_PATH()) + "air_data.txt";
            var0 = XyUtil.getLineByCompressFile(var5_1);
            var1_2 = DBManager.getSQLiteDatabase();
            var3_7 = var0;
            var2_8 = var1_2;
            var1_2.beginTransaction();
            var3_7 = var0;
            var2_8 = var1_2;
            var4_9 = var0.readLine();
            if (var4_9 != null) break block24;
            DBManager.closeDB(var5_1, true, (LineNumberReader)var0, null, var1_2);
            return;
        }
        var3_7 = var0;
        var2_8 = var1_2;
        try {
            var4_9 = var4_9.split("=");
        }
        catch (Throwable var4_10) lbl-1000: // 3 sources:
        {
            var3_7 = var0;
            var2_8 = var1_2;
            var4_11.printStackTrace();
            DBManager.closeDB(var5_1, true, (LineNumberReader)var0, null, var1_2);
            return;
        }
        do {
            block25 : {
                var3_7 = var0;
                var2_8 = var1_2;
                var6_14 = var0.readLine();
                if (var6_14 != null) break block25;
                DBManager.closeDB(var5_1, true, (LineNumberReader)var0, null, var1_2);
                return;
            }
            var3_7 = var0;
            var2_8 = var1_2;
            var6_14 = var6_14.split(" ");
            var3_7 = var0;
            var2_8 = var1_2;
            if (var6_14.length < 3) continue;
            var3_7 = var0;
            var2_8 = var1_2;
            var7_15 = new ContentValues();
            var3_7 = var0;
            var2_8 = var1_2;
            var7_15.put("air_num", var6_14[0]);
            var3_7 = var0;
            var2_8 = var1_2;
            var7_15.put("start_place", var4_9[Integer.valueOf((String)var6_14[1])]);
            var3_7 = var0;
            var2_8 = var1_2;
            var7_15.put("end_place", var4_9[Integer.valueOf((String)var6_14[2])]);
            var3_7 = var0;
            var2_8 = var1_2;
            if ((long)var1_2.update("tb_air", var7_15, "air_num=?", new String[]{var6_14[0]}) >= 1) continue;
            var3_7 = var0;
            var2_8 = var1_2;
            var1_2.insert("tb_air", null, var7_15);
            continue;
            break;
        } while (true);
        catch (Throwable var1_3) {
            var0 = null;
            var2_8 = null;
lbl74: // 3 sources:
            do {
                DBManager.closeDB(var5_1, true, (LineNumberReader)var0, null, var2_8);
                throw var1_4;
                break;
            } while (true);
        }
        catch (Throwable var1_5) {
            var2_8 = null;
            ** GOTO lbl74
        }
        {
            catch (Throwable var1_6) {
                var0 = var3_7;
                ** continue;
            }
        }
        catch (Throwable var4_12) {
            var0 = null;
            var1_2 = null;
            ** GOTO lbl-1000
        }
        catch (Throwable var4_13) {
            var1_2 = null;
            ** GOTO lbl-1000
        }
    }

    /*
     * Exception decompiling
     */
    public static String[] queryStartEndPlace(String var0) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 8[SIMPLE_IF_TAKEN]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }
}

