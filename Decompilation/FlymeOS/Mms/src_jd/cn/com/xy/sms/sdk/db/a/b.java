package cn.com.xy.sms.sdk.db.a;

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
    super(paramContext, paramString, null, 1);
  }
  
  public final void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    try
    {
      a.b(paramSQLiteDatabase);
      return;
    }
    catch (Throwable paramSQLiteDatabase)
    {
      paramSQLiteDatabase.printStackTrace();
    }
  }
  
  public final void onDowngrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    try
    {
      paramSQLiteDatabase.execSQL(" DROP TABLE IF EXISTS tb_regex");
      a.b(paramSQLiteDatabase);
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        LogManager.e("xiaoyaun", "BizportDBManager onDowngrade" + localThrowable.getMessage(), localThrowable);
      }
    }
  }
  
  public final void onOpen(SQLiteDatabase paramSQLiteDatabase)
  {
    super.onOpen(paramSQLiteDatabase);
  }
  
  public final void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    a.b(paramSQLiteDatabase);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.db.a.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */