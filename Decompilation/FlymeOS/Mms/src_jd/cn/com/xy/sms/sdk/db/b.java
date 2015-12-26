package cn.com.xy.sms.sdk.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import cn.com.xy.sms.sdk.log.LogManager;

final class b
  extends SQLiteOpenHelper
{
  public b(Context paramContext, String paramString, SQLiteDatabase.CursorFactory paramCursorFactory, int paramInt)
  {
    super(paramContext, paramString, null, 24);
  }
  
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      DBManager.createDb(paramSQLiteDatabase);
      return;
    }
    catch (Throwable paramSQLiteDatabase)
    {
      LogManager.e("xiaoyuan", "MyDbHelper onCreate error: " + paramSQLiteDatabase.getMessage(), paramSQLiteDatabase);
    }
  }
  
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_sdk_param");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_phone_info");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_public_info");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_public_menu_info");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_public_num_info");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_centernum_location_info");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_scene_config");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_res_download");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_scenerule_config");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_jar_list");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_count_scene");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_popup_action_scene");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_button_action_scene");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS select_pub_time");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_train");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_air");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_menu_list");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_match_cache");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_update_task");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_xml_res_download");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_resourse_queue");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_phone_bubble_cache");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_netquery_time");
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_num_name");
      DBManager.createDb(paramSQLiteDatabase);
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        LogManager.e("xiaoyaun", "DBManager onDowngrade" + localThrowable.getMessage(), localThrowable);
      }
    }
  }
  
  public final void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      super.onOpen(paramSQLiteDatabase);
      return;
    }
    catch (Throwable paramSQLiteDatabase)
    {
      LogManager.e("xiaoyuan", "MyDbHelper onOpen error: " + paramSQLiteDatabase.getMessage(), paramSQLiteDatabase);
    }
  }
  
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    DBManager.createDb(paramSQLiteDatabase);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */