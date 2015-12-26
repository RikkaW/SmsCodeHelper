package com.xiaomi.push.providers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.xiaomi.channel.commonutils.logger.MyLog;

public class TrafficDatabaseHelper
  extends SQLiteOpenHelper
{
  private static int DATABASE_VERSION = 1;
  public static final Object DataBaseLock = new Object();
  private static final String[] TRAFFIC_Columns = { "package_name", "TEXT", "message_ts", " LONG DEFAULT 0 ", "bytes", " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", "TEXT" };
  
  public TrafficDatabaseHelper(Context paramContext)
  {
    super(paramContext, "traffic.db", null, DATABASE_VERSION);
  }
  
  private void createTrafficTable(SQLiteDatabase paramSQLiteDatabase)
  {
    StringBuilder localStringBuilder = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
    int i = 0;
    while (i < TRAFFIC_Columns.length - 1)
    {
      if (i != 0) {
        localStringBuilder.append(",");
      }
      localStringBuilder.append(TRAFFIC_Columns[i]).append(" ").append(TRAFFIC_Columns[(i + 1)]);
      i += 2;
    }
    localStringBuilder.append(");");
    paramSQLiteDatabase.execSQL(localStringBuilder.toString());
  }
  
  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    synchronized (DataBaseLock)
    {
      try
      {
        createTrafficTable(paramSQLiteDatabase);
        return;
      }
      catch (SQLException paramSQLiteDatabase)
      {
        for (;;)
        {
          MyLog.e(paramSQLiteDatabase);
        }
      }
    }
  }
  
  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2) {}
}

/* Location:
 * Qualified Name:     com.xiaomi.push.providers.TrafficDatabaseHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */