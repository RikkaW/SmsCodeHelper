/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteException
 *  android.util.Log
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.util.IOUtils
 */
package com.android.mms.data;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.data.FestivalUpdater;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import miui.util.IOUtils;

public class FestivalDatabase {
    private static String CATEGORY_TABLE_CREATE = String.format((String)"CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT, %s STRING, %s STRING, %s TEXT, %s INTEGER)", (Object[])new Object[]{"categories", "_id", "category_id", "row", "title", "image_id", "desc_image_id", "image_text", "sms_count"});
    private static String DATA_TABLE_CREATE;
    private static String EASTER_EGG_TABLE_CREATE;
    private static String MESSAGE_TABLE_CREATE;
    private static volatile FestivalDatabase sInstance;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    static {
        MESSAGE_TABLE_CREATE = String.format((String)"CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT)", (Object[])new Object[]{"messages", "_id", "message_id", "category_id", "text"});
        EASTER_EGG_TABLE_CREATE = String.format((String)"CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s TEXT, %s STRING)", (Object[])new Object[]{"easterEggs", "_id", "begin_date", "end_date", "key_words", "animation_id"});
        DATA_TABLE_CREATE = String.format((String)"CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s STRING, %s BLOB)", (Object[])new Object[]{"data", "_id", "type", "url", "data"});
        sInstance = null;
    }

    private FestivalDatabase(Context context) {
        this.mContext = context;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean checkTable(String string, String string2) {
        try {
            string = this.mDatabase.query("sqlite_master", new String[]{"sql"}, "type=? AND name=?", new String[]{"table", string}, null, null, null);
            if (string == null) return false;
        }
        catch (SQLiteException var1_2) {
            return false;
        }
        try {
            if (!string.moveToFirst()) return false;
            boolean bl = string2.equals((Object)string.getString(0));
            return bl;
        }
        finally {
            string.close();
        }
    }

    private boolean checkTables() {
        if (this.checkTable("categories", CATEGORY_TABLE_CREATE) && this.checkTable("messages", MESSAGE_TABLE_CREATE) && this.checkTable("easterEggs", EASTER_EGG_TABLE_CREATE) && this.checkTable("data", DATA_TABLE_CREATE)) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean copyRawToFile(int var1_1, String var2_2) {
        block7 : {
            var5_4 = null;
            var4_8 = null;
            var7_12 = null;
            var6_13 = null;
            var4_9 = var3_14 = this.mContext.getResources().openRawResource(var1_1);
            var5_4 = var3_14;
            var2_2 = new FileOutputStream(var2_2);
            try {
                var4_10 = new byte[4096];
                while ((var1_1 = var3_14.read(var4_10)) != -1) {
                    var2_2.write(var4_10, 0, var1_1);
                }
                break block7;
            }
            catch (IOException var4_11) {}
            ** GOTO lbl-1000
        }
        IOUtils.closeQuietly((InputStream)var3_14);
        IOUtils.closeQuietly((OutputStream)var2_2);
        return true;
        catch (Throwable var5_5) {
            var2_2 = var7_12;
            var3_14 = var4_8;
            ** GOTO lbl26
            catch (Throwable var5_7) {}
lbl26: // 2 sources:
            IOUtils.closeQuietly((InputStream)var3_14);
            IOUtils.closeQuietly((OutputStream)var2_2);
            throw var5_6;
        }
        catch (IOException var2_3) {
            var3_14 = var5_4;
            var2_2 = var6_13;
        }
lbl-1000: // 2 sources:
        {
            IOUtils.closeQuietly((InputStream)var3_14);
            IOUtils.closeQuietly((OutputStream)var2_2);
            return false;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean ensureOpen() {
        boolean bl = true;
        synchronized (this) {
            block7 : {
                if (this.mDatabase != null) {
                    boolean bl2 = this.mDatabase.isOpen();
                    if (bl2) return bl;
                }
                this.close();
                if (!new File(this.getDatabaseFullPath()).exists()) {
                    Log.w((String)"FestivalSmsDatabase", (String)"Festival database not exist. Initialize");
                    return this.init();
                }
                try {
                    this.mDatabase = this.mContext.openOrCreateDatabase(this.getDatabaseFullPath(), 0, null);
                    if (this.mDatabase == null) break block7;
                }
                catch (SQLiteException var3_3) {
                    return this.init();
                }
                if (this.checkTables()) return bl;
            }
            Log.w((String)"FestivalSmsDatabase", (String)"Open database or check tables fail.");
            return this.init();
        }
    }

    private String getDatabaseFullPath() {
        return this.mContext.getFilesDir().getAbsolutePath() + "/" + "festival_sms.db";
    }

    public static FestivalDatabase getInstance() {
        synchronized (FestivalDatabase.class) {
            if (sInstance == null) {
                sInstance = new FestivalDatabase((Context)MmsApp.getApp());
            }
            FestivalDatabase festivalDatabase = sInstance;
            return festivalDatabase;
        }
    }

    public void close() {
        if (this.mDatabase != null) {
            this.mDatabase.close();
            this.mDatabase = null;
        }
    }

    public boolean getMoreMessages(long l) {
        if (!this.ensureOpen()) {
            return false;
        }
        try {
            new FestivalUpdater(this.mContext, this.mDatabase).getMoreMessages(l);
            return true;
        }
        catch (Exception var3_2) {
            Log.e((String)"FestivalSmsDatabase", (String)"getMoreMessages error", (Throwable)var3_2);
            return false;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean init() {
        boolean bl = false;
        synchronized (this) {
            this.close();
            Log.d((String)"FestivalSmsDatabase", (String)"Use init database. Copy raw file.");
            this.copyRawToFile(2131165184, this.getDatabaseFullPath());
            try {
                SQLiteDatabase sQLiteDatabase = this.mDatabase = this.mContext.openOrCreateDatabase(this.getDatabaseFullPath(), 0, null);
                if (sQLiteDatabase == null) return bl;
                return true;
            }
            catch (SQLiteException var2_3) {
                try {
                    Log.e((String)"FestivalSmsDatabase", (String)"Cannot open new database");
                    return bl;
                }
                catch (Throwable var2_4) {
                    throw var2_4;
                }
                finally {
                }
            }
        }
    }

    public Cursor query(String string, String[] arrstring, String string2, String[] arrstring2, String string3, String string4, String string5) {
        if (!this.ensureOpen()) {
            return null;
        }
        try {
            string = this.mDatabase.query(string, arrstring, string2, arrstring2, string3, string4, string5);
            return string;
        }
        catch (SQLiteException var1_2) {
            return null;
        }
    }

    public boolean updateMessages() {
        if (!this.ensureOpen()) {
            return false;
        }
        try {
            new FestivalUpdater(this.mContext, this.mDatabase).updateMessages();
            return true;
        }
        catch (Exception var1_1) {
            Log.e((String)"FestivalSmsDatabase", (String)"update error", (Throwable)var1_1);
            return false;
        }
    }
}

