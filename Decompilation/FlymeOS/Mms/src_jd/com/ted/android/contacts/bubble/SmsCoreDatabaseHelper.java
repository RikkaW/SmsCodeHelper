package com.ted.android.contacts.bubble;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import bn;
import java.io.File;
import java.util.List;

public class SmsCoreDatabaseHelper
{
  public static final String DATABASE_NAME = "sms_core.db";
  public static final boolean IS_DB_DECRYPT = true;
  private static final String TABLE_NAME = "service";
  private static final String TAG = SmsCoreDatabaseHelper.class.getSimpleName();
  private static volatile SmsCoreDatabaseHelper instance;
  private Context context;
  private SQLiteDatabase db;
  
  private SmsCoreDatabaseHelper(Context paramContext)
  {
    context = paramContext;
  }
  
  private SQLiteDatabase a()
  {
    if ((db == null) || (!db.isOpen())) {
      db = SQLiteDatabase.openDatabase(new File(context.getFilesDir(), "sms_core.db").getAbsolutePath(), null, 0);
    }
    return db;
  }
  
  public static SmsCoreDatabaseHelper a(Context paramContext)
  {
    if (instance == null) {}
    try
    {
      if (instance == null) {
        instance = new SmsCoreDatabaseHelper(paramContext);
      }
      return instance;
    }
    finally {}
  }
  
  private void a(Cursor paramCursor)
  {
    b(paramCursor);
    b();
  }
  
  private boolean a(String paramString)
  {
    boolean bool = false;
    try
    {
      i = Integer.valueOf(paramString).intValue();
      if (i > 0) {
        bool = true;
      }
      return bool;
    }
    catch (Exception paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
        int i = 0;
      }
    }
  }
  
  private Cursor b(int[] paramArrayOfInt)
  {
    int i = 0;
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("b_id");
    localStringBuffer.append(" in (");
    int k;
    for (int j = 0;; j = k)
    {
      if (i >= paramArrayOfInt.length)
      {
        localStringBuffer.append(")");
        if (j != 0) {
          break;
        }
        return null;
      }
      String str = String.valueOf(paramArrayOfInt[i]);
      k = j;
      if (a(str))
      {
        if (j != 0) {
          localStringBuffer.append(",");
        }
        localStringBuffer.append(str);
        k = 1;
      }
      i += 1;
    }
    try
    {
      paramArrayOfInt = "SELECT * FROM service WHERE " + localStringBuffer.toString();
      paramArrayOfInt = a().rawQuery(paramArrayOfInt, null);
      return paramArrayOfInt;
    }
    catch (Exception paramArrayOfInt)
    {
      for (;;)
      {
        paramArrayOfInt.printStackTrace();
        paramArrayOfInt = null;
      }
    }
  }
  
  private void b()
  {
    if ((db != null) && (db.isOpen())) {
      db.close();
    }
  }
  
  private void b(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  public bn a(int paramInt)
  {
    Object localObject3 = null;
    try
    {
      Cursor localCursor = b(new int[] { paramInt });
      List localList = bn.a(localCursor, true);
      Object localObject1 = localObject3;
      if (localList != null)
      {
        localObject1 = localObject3;
        if (localList.size() > 0) {
          localObject1 = (bn)localList.get(0);
        }
      }
      a(localCursor);
      return (bn)localObject1;
    }
    finally {}
  }
  
  public List<bn> a(int[] paramArrayOfInt)
  {
    paramArrayOfInt = b(paramArrayOfInt);
    List localList = bn.a(paramArrayOfInt, true);
    a(paramArrayOfInt);
    return localList;
  }
}

/* Location:
 * Qualified Name:     com.ted.android.contacts.bubble.SmsCoreDatabaseHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */