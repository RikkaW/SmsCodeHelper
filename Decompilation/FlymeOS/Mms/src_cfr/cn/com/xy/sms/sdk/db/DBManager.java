/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  java.io.BufferedReader
 *  java.io.File
 *  java.io.Reader
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.Hashtable
 */
package cn.com.xy.sms.sdk.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.b;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.db.entity.PhoneSmsParseManager;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Hashtable;

public class DBManager {
    private static int a = 24;
    private static b b = null;
    private static int c = 0;
    private static int d = 0;
    public static final String dataBaseName = "smssdk.db";
    public static Object dblock;
    private static Hashtable<SQLiteDatabase, Integer> e;

    static {
        c = 1000;
        d = 100;
        dblock = new Object();
        e = new Hashtable();
    }

    private static SQLiteDatabase a() {
        return DBManager.getSQLiteDatabase();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static SQLiteDatabase a(Context context) {
        Hashtable<SQLiteDatabase, Integer> hashtable = e;
        synchronized (hashtable) {
            if (e.size() >= 10) {
                return null;
            }
            if (context == null) {
                LogManager.e("xiaoyuan", "createSQLiteDatabase: contexts is null");
                return null;
            }
            SQLiteDatabase sQLiteDatabase = DBManager.b(context).getReadableDatabase();
            if (sQLiteDatabase != null) {
                context = (Integer)e.get((Object)sQLiteDatabase);
                if (context == null) {
                    context = Integer.valueOf((int)1);
                } else {
                    int n2 = context.intValue();
                    context = Integer.valueOf((int)(n2 + 1));
                }
                e.put((Object)sQLiteDatabase, (Object)context);
                new StringBuilder("$$$$$ db code : ").append(sQLiteDatabase.hashCode()).append(" cnt: ").append((Object)context);
                if (!sQLiteDatabase.isOpen()) {
                    e.remove((Object)sQLiteDatabase);
                    return null;
                }
            }
            return sQLiteDatabase;
        }
    }

    private static void a(SQLiteDatabase sQLiteDatabase, String string2) {
        try {
            sQLiteDatabase.execSQL(string2);
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    private static boolean a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null && sQLiteDatabase.inTransaction()) {
            DBManager.close(sQLiteDatabase);
            new StringBuilder("DBManager db inTransaction threadName:").append(Thread.currentThread().getName());
            return true;
        }
        return false;
    }

    private static b b(Context object) {
        synchronized (DBManager.class) {
            if (b == null) {
                b = new b((Context)object, "smssdk.db", null, 24);
            }
            object = b;
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void close(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        try {
            Hashtable<SQLiteDatabase, Integer> hashtable = e;
            // MONITORENTER : hashtable
        }
        catch (Throwable var0_1) {
            LogManager.e("xiaoyuan", "DBManager close error: " + var0_1.getMessage());
            return;
        }
        if (!sQLiteDatabase.isOpen()) {
            e.remove((Object)sQLiteDatabase);
        } else {
            Integer n2 = (Integer)e.get((Object)sQLiteDatabase);
            if (n2 == null) {
                new StringBuilder("$$$$$ db close cnt is null ").append(sQLiteDatabase.hashCode());
            } else if ((n2 = Integer.valueOf((int)(n2 - 1))) == 0) {
                e.remove((Object)sQLiteDatabase);
                sQLiteDatabase.close();
            } else {
                e.put((Object)sQLiteDatabase, (Object)n2);
            }
        }
        // MONITOREXIT : hashtable
        int n3 = e.size();
        if (n3 != 0) return;
    }

    public static void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    /*
     * Exception decompiling
     */
    public static void closeDB(String var0, boolean var1_7, LineNumberReader var2_8, BufferedReader var3_9, SQLiteDatabase var4_10) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
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

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void createDb(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table  if not exists tb_sdk_param (id int primary key,p_key TEXT,p_value TEXT,pextend_value TEXT)");
            sQLiteDatabase.execSQL("create table  if not exists tb_phone_info (id INTEGER PRIMARY KEY,iccid TEXT ,city TEXT,provinces TEXT,operator TEXT,areacode TEXT,ispost INTEGER DEFAULT 0,num TEXT,cnum TEXT,updateTime LONG,deft  INTEGER DEFAULT 0,net_updateTime LONG DEFAULT 0,user_provinces TEXT,user_areacode TEXT,user_operator TEXT,sim_index INTEGER DEFAULT -1)");
            sQLiteDatabase.execSQL(" create table  if not exists tb_public_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null unique, pubName TEXT not null, pubType TEXT, classifyCode TEXT, weiXin TEXT, weiBoName TEXT, weiBoUrl TEXT, introduce TEXT, address TEXT, faxNum TEXT, webSite TEXT, moveWebSite TEXT, versionCode TEXT, email TEXT, parentPubId int, slogan TEXT, rectLogoName TEXT, circleLogoName TEXT, extend TEXT, hasmenu int, loadMenuTime long, updateInfoTime long )");
            sQLiteDatabase.execSQL("create table  if not exists tb_public_menu_info ( id INTEGER PRIMARY KEY, menuCode text not null, pubId INTEGER, menuName text not null, menuType text not null, sendTo text, sp text , menuDesc text , sms text, url text, phoneNum text  , actionData text  , extend text  )");
            sQLiteDatabase.execSQL("create table  if not exists tb_public_num_info ( id INTEGER PRIMARY KEY, pubId INTEGER not null, num text not null, purpose text , areaCode text not null, ptype int default 1, main INTEGER default 0, communication INTEGER default 0, isfull INTEGER default 0, minLen INTEGER default 0, maxLen INTEGER default 0, len INTEGER default 0, ntype text, extend text, lastloadtime LONG default 0, isrulenum INTEGER default 0)");
            sQLiteDatabase.execSQL(" create table  if not exists tb_centernum_location_info ( id INTEGER PRIMARY KEY, cnum TEXT not null unique, areaCode TEXT, city TEXT, checkTime long, operator TEXT )");
            sQLiteDatabase.execSQL("create table  if not exists tb_scene_config (scene_id TEXT,sceneType INTEGER DEFAULT '0',isCheck INTEGER DEFAULT '0',sceneVersion TEXT,isUse INTEGER DEFAULT '0',useCount INTEGER DEFAULT '0')");
            sQLiteDatabase.execSQL("create table  if not exists tb_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' )");
            sQLiteDatabase.execSQL("create table  if not exists tb_scenerule_config (id TEXT,sceneRuleVersion TEXT,scene_id TEXT,province TEXT,operator TEXT,expire_date TEXT,Func_call INTEGER,Func_acc_url INTEGER,Func_reply_sms INTEGER,Func_config TEXT,res_urls TEXT,s_version TEXT,Scene_page_config TEXT,sceneType INTEGER DEFAULT '-1',isdownload INTEGER DEFAULT '0')");
            sQLiteDatabase.execSQL("create table  if not exists tb_jar_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' ,is_use INTEGER DEFAULT '0')");
            sQLiteDatabase.execSQL("create table  if not exists tb_count_scene (scene_id TEXT,count INT)");
            sQLiteDatabase.execSQL("create table  if not exists tb_popup_action_scene (scene_id TEXT, date TEXT, parse_times INTEGER DEFAULT '0', popup_times INTEGER DEFAULT '0' ) ");
            sQLiteDatabase.execSQL("create table  if not exists tb_button_action_scene (scene_id TEXT, date TEXT, action_type INTEGER DEFAULT '0', times INTEGER DEFAULT '0' ) ");
            sQLiteDatabase.execSQL("create table  if not exists select_pub_time ( id INTEGER PRIMARY KEY AUTOINCREMENT, num TEXT, areaCode TEXT, selectTime long default 0 )");
            sQLiteDatabase.execSQL("create table  if not exists tb_train (id INTEGER PRIMARY KEY,train_num TEXT not null unique,start_city TEXT,end_city TEXT,train_type INTEGER default 0,start_time TEXT,end_time TEXT,mileage TEXT,station_list TEXT,duration TEXT,data_time LONG default 0)");
            sQLiteDatabase.execSQL("create table  if not exists tb_air (id INTEGER PRIMARY KEY,air_num TEXT not null unique,start_city TEXT,end_city TEXT,start_place TEXT,end_place TEXT,start_time TEXT,end_time TEXT,company TEXT)");
            sQLiteDatabase.execSQL("create table  if not exists tb_menu_list (id INTEGER PRIMARY KEY,name TEXT,version TEXT,url TEXT,status INTEGER DEFAULT '0',update_time INTEGER DEFAULT '0',delaystart INTEGER DEFAULT '0',delayend INTEGER DEFAULT '0',count INTEGER DEFAULT '0',last_load_time INTEGER DEFAULT '0' )");
            sQLiteDatabase.execSQL(MatchCacheManager.getCreateTableSql());
            sQLiteDatabase.execSQL("create table  if not exists tb_update_task ( id INTEGER PRIMARY KEY,content TEXT,t_group TEXT,t_version long )");
            sQLiteDatabase.execSQL("create table  if not exists tb_xml_res_download (id INTEGER PRIMARY KEY,scene_id TEXT,url TEXT,status INTEGER,pos INTEGER,last_load_time INTEGER DEFAULT '0' ,sceneType INTEGER DEFAULT '0',insert_time INTEGER DEFAULT '0' )");
            sQLiteDatabase.execSQL(" create table  if not exists tb_resourse_queue ( id INTEGER PRIMARY KEY, res_type INTEGER, res_version INTEGER, res_url TEXT, down_statu INTEGER DEFAULT '0', temp_filename TEXT, down_failed_time LONG DEFAULT '0')");
            sQLiteDatabase.execSQL(PhoneSmsParseManager.getCreateTableSql());
            sQLiteDatabase.execSQL(" create table  if not exists tb_netquery_time ( id INTEGER PRIMARY KEY, phone_num TEXT,area_code TEXT, request_time LONG DEFAULT '0')");
            sQLiteDatabase.execSQL("CREATE TABLE  IF NOT EXISTS tb_num_name (id INTEGER PRIMARY KEY, num TEXT NOT NULL UNIQUE, name TEXT NOT NULL, cnum TEXT,mark_time  LONG DEFAULT 0)");
            sQLiteDatabase.execSQL(" create table  if not exists tb_emergency_queue ( id INTEGER PRIMARY KEY, emVersion INTEGER, emContent TEXT )");
        }
        catch (Throwable var1_11) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_public_info ADD COLUMN classifyCode TEXT");
        }
        catch (Throwable var1_10) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_scene_config ADD COLUMN isCheck INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_9) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_scene_config ADD COLUMN useCount INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_8) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_scene_config ADD COLUMN isUse INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_7) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_jar_list ADD COLUMN is_use INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_6) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_train ADD COLUMN station_list TEXT ");
        }
        catch (Throwable var1_5) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_train ADD COLUMN data_time LONG default 0 ");
        }
        catch (Throwable var1_4) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_match_cache ADD COLUMN bubble_lasttime INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_3) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_match_cache ADD COLUMN card_lasttime INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_2) {}
        try {
            sQLiteDatabase.execSQL("ALTER TABLE tb_match_cache ADD COLUMN session_lasttime INTEGER DEFAULT '0'");
        }
        catch (Throwable var1_1) {}
        DBManager.a(sQLiteDatabase, " ALTER TABLE tb_public_num_info ADD COLUMN lastloadtime LONG default 0");
        DBManager.a(sQLiteDatabase, " ALTER TABLE tb_public_num_info ADD COLUMN isrulenum INTEGER default 0");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_netquery_time ADD COLUMN area_code TEXT");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN user_provinces TEXT ");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN user_areacode TEXT ");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN user_operator TEXT ");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_phone_info ADD COLUMN sim_index INTEGER DEFAULT -1 ");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_num_name ADD COLUMN cnum TEXT ");
        DBManager.a(sQLiteDatabase, "ALTER TABLE tb_num_name ADD COLUMN mark_time LONG DEFAULT 0");
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static int delete(String string2, String string3, String[] arrstring) {
        void var0_2;
        SQLiteDatabase sQLiteDatabase;
        block8 : {
            int n2;
            Object object = dblock;
            // MONITORENTER : object
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase2 = sQLiteDatabase = DBManager.getSQLiteDatabase();
                n2 = sQLiteDatabase.delete(string2, string3, arrstring);
            }
            catch (Throwable var0_1) {
                DBManager.close(sQLiteDatabase2);
                return -1;
            }
            DBManager.close(sQLiteDatabase);
            // MONITOREXIT : object
            return n2;
            catch (Throwable throwable) {
                break block8;
            }
            catch (Throwable throwable2) {
                sQLiteDatabase = null;
            }
        }
        DBManager.close(sQLiteDatabase);
        throw var0_2;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void excSql(String var0, boolean var1_1) {
        var5_2 = null;
        var6_7 = null;
        var3_10 = null;
        if (d.a(var0) == false) return;
        var2_14 = new BufferedReader((Reader)new FileReader(new File(var0)));
        var4_15 = new LineNumberReader((Reader)var2_14);
        var5_2 = var6_7;
        var5_2 = var3_10 = DBManager.getSQLiteDatabase();
        var3_10.beginTransaction();
        do {
            block23 : {
                var5_2 = var3_10;
                var6_7 = var4_15.readLine();
                if (var6_7 != null) break block23;
                var5_2 = var3_10;
                var4_15.close();
                DBManager.closeDB(var0, var1_1, var4_15, var2_14, (SQLiteDatabase)var3_10);
                return;
            }
            var5_2 = var3_10;
            if (StringUtils.isNull((String)var6_7)) continue;
            var5_2 = var3_10;
            var3_10.execSQL((String)var6_7);
            continue;
            break;
        } while (true);
        catch (Throwable var6_8) {
            var5_2 = var3_10;
            var6_8.printStackTrace();
            var5_2 = var3_10;
            try {
                throw var6_8;
            }
            catch (Throwable var5_3) lbl-1000: // 4 sources:
            {
                var5_2.printStackTrace();
                DBManager.closeDB(var0, var1_1, var4_15, var2_14, (SQLiteDatabase)var3_10);
                return;
            }
        }
        catch (Throwable var3_11) {
            var4_15 = null;
            var2_14 = null;
lbl47: // 4 sources:
            do {
                DBManager.closeDB(var0, var1_1, var4_15, var2_14, (SQLiteDatabase)var5_2);
                throw var3_10;
                break;
            } while (true);
        }
        catch (Throwable var3_12) {
            var4_15 = null;
            ** GOTO lbl47
        }
        {
            catch (Throwable var3_13) {
                ** GOTO lbl47
            }
            {
                catch (Throwable var6_9) {
                    var5_2 = var3_10;
                    var3_10 = var6_9;
                    ** continue;
                }
            }
        }
        catch (Throwable var5_4) {
            var4_15 = null;
            var6_7 = null;
            var2_14 = var3_10;
            var3_10 = var6_7;
            ** GOTO lbl-1000
        }
        catch (Throwable var5_5) {
            var4_15 = null;
            var3_10 = null;
            ** GOTO lbl-1000
        }
        catch (Throwable var5_6) {
            var3_10 = null;
            ** GOTO lbl-1000
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static SQLiteDatabase getSQLiteDatabase() {
        SQLiteDatabase sQLiteDatabase = null;
        long l2 = System.currentTimeMillis();
        while (sQLiteDatabase == null) {
            SQLiteDatabase sQLiteDatabase2;
            sQLiteDatabase = DBManager.a(Constant.getContext());
            if (sQLiteDatabase != null) {
                sQLiteDatabase2 = sQLiteDatabase;
                if (!sQLiteDatabase.inTransaction()) return sQLiteDatabase2;
                new StringBuilder("getSQLiteDatabase inTransaction hashcode: ").append(sQLiteDatabase.hashCode());
                return sQLiteDatabase;
            }
            sQLiteDatabase2 = sQLiteDatabase;
            if (System.currentTimeMillis() - l2 >= (long)c) return sQLiteDatabase2;
            try {
                Thread.sleep((long)d);
                continue;
            }
            catch (InterruptedException var3_3) {
                LogManager.e("xiaoyuan", "DBManager getSQLiteDatabase error: " + var3_3.getMessage(), var3_3);
                continue;
            }
            break;
        }
        return sQLiteDatabase;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static long insert(String string2, ContentValues contentValues) {
        void var0_2;
        SQLiteDatabase sQLiteDatabase;
        block8 : {
            long l2;
            SQLiteDatabase sQLiteDatabase2 = null;
            Object object = dblock;
            // MONITORENTER : object
            try {
                sQLiteDatabase2 = sQLiteDatabase = DBManager.getSQLiteDatabase();
                l2 = sQLiteDatabase.insert(string2, null, contentValues);
            }
            catch (Throwable var0_1) {
                DBManager.close(sQLiteDatabase2);
                return -1;
            }
            DBManager.close(sQLiteDatabase);
            // MONITOREXIT : object
            return l2;
            catch (Throwable throwable) {
                break block8;
            }
            catch (Throwable throwable2) {
                sQLiteDatabase = null;
            }
        }
        DBManager.close(sQLiteDatabase);
        throw var0_2;
    }

    public static XyCursor query(String string2, String[] arrstring, String string3, String[] arrstring2) {
        return DBManager.query(false, string2, arrstring, string3, arrstring2, null, null, null, null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static XyCursor query(String var0, String[] var1_1, String var2_5, String[] var3_6, String var4_7, String var5_8, String var6_9, String var7_10) {
        var9_11 = null;
        var8_12 = DBManager.getSQLiteDatabase();
        if (DBManager.a(var8_12) == false) return new XyCursor(var8_12, var8_12.query((String)var0, var1_1, var2_5, var3_6, var4_7, var5_8, var6_9, var7_10));
        return null;
        catch (Throwable var1_2) {
            var0 = var9_11;
            ** GOTO lbl12
            catch (Throwable var1_4) {
                var0 = var8_12;
            }
lbl12: // 2 sources:
            DBManager.close((SQLiteDatabase)var0);
            LogManager.e("xiaoyuan", "DBManager 2query error: " + var1_3.getMessage(), (Throwable)var1_3);
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static XyCursor query(boolean var0, String var1_1, String[] var2_2, String var3_6, String[] var4_7, String var5_8, String var6_9, String var7_10, String var8_11) {
        block4 : {
            var10_12 = null;
            var9_13 = DBManager.getSQLiteDatabase();
            if (var9_13 != null) break block4;
            return null;
        }
        if (DBManager.a(var9_13) == false) return new XyCursor(var9_13, var9_13.query(var0, (String)var1_1, var2_2, var3_6, var4_7, var5_8, var6_9, var7_10, var8_11));
        return null;
        catch (Throwable var2_3) {
            var1_1 = var10_12;
            ** GOTO lbl15
            catch (Throwable var2_5) {
                var1_1 = var9_13;
            }
lbl15: // 2 sources:
            DBManager.close((SQLiteDatabase)var1_1);
            LogManager.e("xiaoyuan", "DBManager 1query error: " + var2_4.getMessage(), (Throwable)var2_4);
            return null;
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static XyCursor rawQuery(String object, String[] arrstring) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = DBManager.getSQLiteDatabase();
        }
        catch (Throwable var0_1) {
            sQLiteDatabase = null;
            DBManager.close(sQLiteDatabase);
            LogManager.e("xiaoyuan", "DBManager rawQuery error: " + object.getMessage(), (Throwable)object);
            return null;
        }
        if (!DBManager.a(sQLiteDatabase)) return new XyCursor(sQLiteDatabase, sQLiteDatabase.rawQuery((String)object, arrstring));
        return null;
        {
            catch (Throwable throwable) {}
        }
    }

    public static long saveOrUpdateTableData(String string2, ContentValues contentValues, String string3, String[] arrstring) {
        long l2;
        block3 : {
            long l3 = 0;
            try {
                l2 = DBManager.update(string2, contentValues, string3, arrstring);
                if (l2 >= 1) break block3;
                l3 = l2;
            }
            catch (Throwable var0_1) {
                LogManager.e("xiaoyuan", "DBManager saveOrUpdateTableData error: " + var0_1.getMessage(), var0_1);
                return l3;
            }
            l2 = DBManager.insert(string2, contentValues);
            return l2;
        }
        return - l2;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static int update(String string2, ContentValues contentValues, String string3, String[] arrstring) {
        void var0_2;
        SQLiteDatabase sQLiteDatabase;
        block8 : {
            int n2;
            Object object = dblock;
            // MONITORENTER : object
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase2 = sQLiteDatabase = DBManager.getSQLiteDatabase();
                n2 = sQLiteDatabase.update(string2, contentValues, string3, arrstring);
            }
            catch (Throwable var0_1) {
                DBManager.close(sQLiteDatabase2);
                return -1;
            }
            DBManager.close(sQLiteDatabase);
            // MONITOREXIT : object
            return n2;
            catch (Throwable throwable) {
                break block8;
            }
            catch (Throwable throwable2) {
                sQLiteDatabase = null;
            }
        }
        DBManager.close(sQLiteDatabase);
        throw var0_2;
    }
}

