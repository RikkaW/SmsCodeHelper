/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.database.sqlite.SQLiteOpenHelper
 *  java.lang.String
 *  java.lang.Throwable
 */
package cn.com.xy.sms.sdk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.log.LogManager;

final class b
extends SQLiteOpenHelper {
    public b(Context context, String string2, SQLiteDatabase.CursorFactory cursorFactory, int n2) {
        super(context, string2, null, 24);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            DBManager.createDb(sQLiteDatabase);
            return;
        }
        catch (Throwable var1_2) {
            LogManager.e("xiaoyuan", "MyDbHelper onCreate error: " + var1_2.getMessage(), var1_2);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int n2, int n3) {
        try {
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_sdk_param");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_phone_info");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_public_info");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_public_menu_info");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_public_num_info");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_centernum_location_info");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_scene_config");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_res_download");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_scenerule_config");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_jar_list");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_count_scene");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_popup_action_scene");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_button_action_scene");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS select_pub_time");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_train");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_air");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_menu_list");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_match_cache");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_update_task");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_xml_res_download");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_resourse_queue");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_phone_bubble_cache");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_netquery_time");
            sQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_num_name");
        }
        catch (Throwable var4_4) {
            LogManager.e("xiaoyaun", "DBManager onDowngrade" + var4_4.getMessage(), var4_4);
        }
        DBManager.createDb(sQLiteDatabase);
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        try {
            super.onOpen(sQLiteDatabase);
            return;
        }
        catch (Throwable var1_2) {
            LogManager.e("xiaoyuan", "MyDbHelper onOpen error: " + var1_2.getMessage(), var1_2);
            return;
        }
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int n2, int n3) {
        DBManager.createDb(sQLiteDatabase);
    }
}

