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
package cn.com.xy.sms.sdk.db.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.XyCursor;
import cn.com.xy.sms.sdk.db.a.b;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.Hashtable;

public final class a {
    private static final String a = "bizport.db";
    private static int b = 1;
    private static b c = null;
    private static int d = 1000;
    private static int e = 100;
    private static Object f = new Object();
    private static Hashtable<SQLiteDatabase, Integer> g = new Hashtable();

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static int a(String string2, ContentValues contentValues, String string3, String[] arrstring) {
        void var0_2;
        SQLiteDatabase sQLiteDatabase;
        block8 : {
            int n2;
            Object object = f;
            // MONITORENTER : object
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase2 = sQLiteDatabase = a.a();
                n2 = sQLiteDatabase.update(string2, contentValues, string3, arrstring);
            }
            catch (Throwable var0_1) {
                a.a(sQLiteDatabase2);
                return -1;
            }
            a.a(sQLiteDatabase);
            // MONITOREXIT : object
            return n2;
            catch (Throwable throwable) {
                break block8;
            }
            catch (Throwable throwable2) {
                sQLiteDatabase = null;
            }
        }
        a.a(sQLiteDatabase);
        throw var0_2;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static int a(String string2, String string3, String[] arrstring) {
        void var0_2;
        SQLiteDatabase sQLiteDatabase;
        block8 : {
            int n2;
            Object object = f;
            // MONITORENTER : object
            SQLiteDatabase sQLiteDatabase2 = null;
            try {
                sQLiteDatabase2 = sQLiteDatabase = a.a();
                n2 = sQLiteDatabase.delete(string2, string3, arrstring);
            }
            catch (Throwable var0_1) {
                a.a(sQLiteDatabase2);
                return -1;
            }
            a.a(sQLiteDatabase);
            // MONITOREXIT : object
            return n2;
            catch (Throwable throwable) {
                break block8;
            }
            catch (Throwable throwable2) {
                sQLiteDatabase = null;
            }
        }
        a.a(sQLiteDatabase);
        throw var0_2;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private static long a(String string2, ContentValues contentValues) {
        void var0_2;
        SQLiteDatabase sQLiteDatabase;
        block8 : {
            long l2;
            SQLiteDatabase sQLiteDatabase2 = null;
            Object object = f;
            // MONITORENTER : object
            try {
                sQLiteDatabase2 = sQLiteDatabase = a.a();
                l2 = sQLiteDatabase.insert(string2, null, contentValues);
            }
            catch (Throwable var0_1) {
                a.a(sQLiteDatabase2);
                return -1;
            }
            a.a(sQLiteDatabase);
            // MONITOREXIT : object
            return l2;
            catch (Throwable throwable) {
                break block8;
            }
            catch (Throwable throwable2) {
                sQLiteDatabase = null;
            }
        }
        a.a(sQLiteDatabase);
        throw var0_2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static SQLiteDatabase a() {
        SQLiteDatabase sQLiteDatabase = null;
        long l2 = System.currentTimeMillis();
        while (sQLiteDatabase == null) {
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase = a.a(Constant.getContext());
            if (sQLiteDatabase != null) return sQLiteDatabase2;
            sQLiteDatabase2 = sQLiteDatabase;
            if (System.currentTimeMillis() - l2 >= (long)d) return sQLiteDatabase2;
            try {
                Thread.sleep((long)e);
                continue;
            }
            catch (InterruptedException var3_3) {
                new StringBuilder("BizportDBManager getSQLiteDatabase ").append(var3_3.getMessage());
                continue;
            }
            break;
        }
        return sQLiteDatabase;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static SQLiteDatabase a(Context context) {
        Hashtable<SQLiteDatabase, Integer> hashtable = g;
        synchronized (hashtable) {
            if (g.size() >= 10) {
                return null;
            }
            SQLiteDatabase sQLiteDatabase = a.b(context.getApplicationContext()).getReadableDatabase();
            if (sQLiteDatabase != null) {
                context = (Integer)g.get((Object)sQLiteDatabase);
                if (context == null) {
                    context = Integer.valueOf((int)1);
                } else {
                    int n2 = context.intValue();
                    context = Integer.valueOf((int)(n2 + 1));
                }
                g.put((Object)sQLiteDatabase, (Object)context);
                if (!sQLiteDatabase.isOpen()) {
                    g.remove((Object)sQLiteDatabase);
                    return null;
                }
            }
            return sQLiteDatabase;
        }
    }

    public static XyCursor a(SQLiteDatabase object, boolean bl2, String string2, String[] arrstring, String string3, String[] arrstring2, String string4, String string5, String string6, String string7) {
        try {
            object = new XyCursor(null, object.query(false, string2, arrstring, string3, arrstring2, null, null, null, string7), 1);
            return object;
        }
        catch (Throwable var0_1) {
            new StringBuilder("BizportDBManager 2query ").append(var0_1.getMessage());
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
    private static XyCursor a(String object, String[] arrstring) {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = a.a();
        }
        catch (Throwable var0_1) {
            void var0_2;
            sQLiteDatabase = null;
            a.a(sQLiteDatabase);
            new StringBuilder("BizportDBManager rawQuery ").append(var0_2.getMessage());
            return null;
        }
        return new XyCursor(sQLiteDatabase, sQLiteDatabase.rawQuery((String)object, arrstring));
        {
            catch (Throwable throwable) {}
        }
    }

    private static XyCursor a(String string2, String[] arrstring, String string3, String[] arrstring2) {
        return a.a(false, string2, arrstring, string3, arrstring2, null, null, null, null);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private static XyCursor a(String var0, String[] var1_1, String var2_5, String[] var3_6, String var4_7, String var5_8, String var6_9, String var7_10) {
        var9_11 = null;
        var8_12 = a.a();
        return new XyCursor(var8_12, var8_12.query((String)var0, var1_1, var2_5, var3_6, var4_7, var5_8, var6_9, var7_10), 1);
        catch (Throwable var1_2) {
            var0 = var9_11;
            ** GOTO lbl11
            catch (Throwable var1_4) {
                var0 = var8_12;
            }
lbl11: // 2 sources:
            a.a((SQLiteDatabase)var0);
            new StringBuilder("BizportDBManager 1query ").append(var1_3.getMessage());
            return null;
        }
    }

    public static XyCursor a(boolean bl2, String object, String[] arrstring, String string2, String[] arrstring2, String string3, String string4, String string5, String string6) {
        string3 = null;
        try {
            string3 = string4 = a.a();
        }
        catch (Throwable var1_2) {
            a.a((SQLiteDatabase)string3);
            return null;
        }
        object = new XyCursor((SQLiteDatabase)string4, string4.query(false, (String)object, arrstring, string2, arrstring2, null, null, null, string6), 1);
        return object;
    }

    private static void a(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase == null) {
            return;
        }
        try {
            Hashtable<SQLiteDatabase, Integer> hashtable = g;
            // MONITORENTER : hashtable
        }
        catch (Throwable var0_1) {
            new StringBuilder("BizportDBManager close ").append(var0_1.getMessage());
            return;
        }
        Integer n2 = (Integer)g.get((Object)sQLiteDatabase);
        if (n2 == null) {
            new StringBuilder("$$$$$ db close cnt is null ").append(sQLiteDatabase.hashCode());
        } else if ((n2 = Integer.valueOf((int)(n2 - 1))) == 0) {
            g.remove((Object)sQLiteDatabase);
            sQLiteDatabase.close();
        } else {
            g.put((Object)sQLiteDatabase, (Object)n2);
        }
        // MONITOREXIT : hashtable
        if (g.size() != 0) return;
        if (c == null) return;
        c.close();
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void a(String var0, boolean var1_1) {
        var5_2 = null;
        var6_7 = null;
        var3_10 = null;
        if (!d.a(var0)) {
            return;
        }
        var2_14 = new BufferedReader((Reader)new FileReader(new File(var0)));
        var4_15 = new LineNumberReader((Reader)var2_14);
        var5_2 = var6_7;
        var5_2 = var3_10 = a.a();
        var3_10.beginTransaction();
        do {
            block23 : {
                var5_2 = var3_10;
                var6_7 = var4_15.readLine();
                if (var6_7 != null) break block23;
                var5_2 = var3_10;
                var4_15.close();
                a.a(var0, false, var4_15, var2_14, (SQLiteDatabase)var3_10);
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
            try {
                throw var6_8;
            }
            catch (Throwable var5_3) lbl-1000: // 4 sources:
            {
                var5_2.printStackTrace();
                a.a(var0, false, var4_15, var2_14, (SQLiteDatabase)var3_10);
                return;
            }
        }
        catch (Throwable var3_11) {
            var4_15 = null;
            var2_14 = null;
lbl45: // 4 sources:
            do {
                a.a(var0, false, var4_15, var2_14, (SQLiteDatabase)var5_2);
                throw var3_10;
                break;
            } while (true);
        }
        catch (Throwable var3_12) {
            var4_15 = null;
            ** GOTO lbl45
        }
        {
            catch (Throwable var3_13) {
                ** GOTO lbl45
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
     * Exception decompiling
     */
    public static void a(String var0, boolean var1_7, LineNumberReader var2_8, BufferedReader var3_9, SQLiteDatabase var4_10) {
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

    private static SQLiteDatabase b() {
        return a.a();
    }

    private static b b(Context object) {
        synchronized (a.class) {
            if (c == null) {
                c = new b((Context)object, "bizport.db", null, 1);
            }
            object = c;
            return object;
        }
    }

    static /* synthetic */ void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table  if not exists tb_regex (scene_id TEXT,match_id TEXT,regex_text TEXT,version_code TEXT,regex_type INTEGER  DEFAULT '0',state INTEGER  DEFAULT '0' )");
            sQLiteDatabase.execSQL("create index if not exists indx_s_m on tb_regex (scene_id,match_id)");
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table  if not exists tb_regex (scene_id TEXT,match_id TEXT,regex_text TEXT,version_code TEXT,regex_type INTEGER  DEFAULT '0',state INTEGER  DEFAULT '0' )");
            sQLiteDatabase.execSQL("create index if not exists indx_s_m on tb_regex (scene_id,match_id)");
            return;
        }
        catch (Throwable var0_1) {
            return;
        }
    }
}

