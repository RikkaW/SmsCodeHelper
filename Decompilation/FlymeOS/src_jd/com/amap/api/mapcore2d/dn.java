package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class dn
{
  private dm a;
  private SQLiteDatabase b;
  
  public dn(Context paramContext)
  {
    a = new dm(paramContext, "logdb.db", null, 1);
  }
  
  private SQLiteDatabase a()
  {
    b = a.getReadableDatabase();
    return b;
  }
  
  private SQLiteDatabase b()
  {
    b = a.getWritableDatabase();
    return b;
  }
  
  public <T> void a(du<T> paramdu)
  {
    if (paramdu == null) {}
    for (;;)
    {
      return;
      ContentValues localContentValues = paramdu.b();
      if ((localContentValues == null) || (paramdu.a() == null)) {
        continue;
      }
      if ((b == null) || (b.isReadOnly())) {
        b = b();
      }
      if (b == null) {
        continue;
      }
      try
      {
        b.insert(paramdu.a(), null, localContentValues);
        return;
      }
      catch (Throwable paramdu)
      {
        for (;;)
        {
          ed.a(paramdu, "DataBase", "insertData");
          paramdu.printStackTrace();
          if (b == null) {
            break;
          }
          b.close();
        }
      }
      finally
      {
        if (b != null)
        {
          b.close();
          b = null;
        }
      }
    }
  }
  
  public <T> void a(String paramString, du<T> paramdu)
  {
    if ((paramdu.a() == null) || (paramString == null)) {}
    for (;;)
    {
      return;
      if ((b == null) || (b.isReadOnly())) {
        b = b();
      }
      if (b == null) {
        continue;
      }
      try
      {
        b.delete(paramdu.a(), paramString, null);
        return;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          ed.a(paramString, "DataBase", "deleteData");
          paramString.printStackTrace();
          if (b == null) {
            break;
          }
          b.close();
        }
      }
      finally
      {
        if (b != null)
        {
          b.close();
          b = null;
        }
      }
    }
  }
  
  public <T> void b(String paramString, du<T> paramdu)
  {
    if ((paramdu == null) || (paramString == null) || (paramdu.a() == null)) {}
    for (;;)
    {
      return;
      ContentValues localContentValues = paramdu.b();
      if (localContentValues == null) {
        continue;
      }
      if ((b == null) || (b.isReadOnly())) {
        b = b();
      }
      if (b == null) {
        continue;
      }
      try
      {
        b.update(paramdu.a(), localContentValues, paramString, null);
        return;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          ed.a(paramString, "DataBase", "updateData");
          paramString.printStackTrace();
          if (b == null) {
            break;
          }
          b.close();
        }
      }
      finally
      {
        if (b != null)
        {
          b.close();
          b = null;
        }
      }
    }
  }
  
  /* Error */
  public <T> java.util.List<T> c(String paramString, du<T> paramdu)
  {
    // Byte code:
    //   0: new 90	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 91	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   12: ifnonnull +11 -> 23
    //   15: aload_0
    //   16: aload_0
    //   17: invokespecial 93	com/amap/api/mapcore2d/dn:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   20: putfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   23: aload_0
    //   24: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   27: ifnull +16 -> 43
    //   30: aload_2
    //   31: invokeinterface 43 1 0
    //   36: ifnull +7 -> 43
    //   39: aload_1
    //   40: ifnonnull +5 -> 45
    //   43: aload_3
    //   44: areturn
    //   45: aload_0
    //   46: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   49: aload_2
    //   50: invokeinterface 43 1 0
    //   55: aconst_null
    //   56: aload_1
    //   57: aconst_null
    //   58: aconst_null
    //   59: aconst_null
    //   60: aconst_null
    //   61: invokevirtual 97	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   64: astore_1
    //   65: aload_1
    //   66: ifnonnull +36 -> 102
    //   69: aload_0
    //   70: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   73: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   76: aload_0
    //   77: aconst_null
    //   78: putfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   81: aload_0
    //   82: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   85: ifnull +15 -> 100
    //   88: aload_0
    //   89: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   92: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   95: aload_0
    //   96: aconst_null
    //   97: putfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   100: aload_3
    //   101: areturn
    //   102: aload_1
    //   103: invokeinterface 102 1 0
    //   108: ifeq +42 -> 150
    //   111: aload_3
    //   112: aload_2
    //   113: aload_1
    //   114: invokeinterface 105 2 0
    //   119: invokeinterface 111 2 0
    //   124: pop
    //   125: goto -23 -> 102
    //   128: astore_1
    //   129: aload_0
    //   130: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   133: ifnull +15 -> 148
    //   136: aload_0
    //   137: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   140: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   143: aload_0
    //   144: aconst_null
    //   145: putfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   148: aload_3
    //   149: areturn
    //   150: aload_1
    //   151: invokeinterface 112 1 0
    //   156: aload_0
    //   157: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   160: ifnull -12 -> 148
    //   163: aload_0
    //   164: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   167: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   170: goto -27 -> 143
    //   173: astore_1
    //   174: aload_0
    //   175: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   178: ifnull +15 -> 193
    //   181: aload_0
    //   182: getfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   185: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   188: aload_0
    //   189: aconst_null
    //   190: putfield 29	com/amap/api/mapcore2d/dn:b	Landroid/database/sqlite/SQLiteDatabase;
    //   193: aload_1
    //   194: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	195	0	this	dn
    //   0	195	1	paramString	String
    //   0	195	2	paramdu	du<T>
    //   7	142	3	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   45	65	128	java/lang/Throwable
    //   69	81	128	java/lang/Throwable
    //   102	125	128	java/lang/Throwable
    //   150	156	128	java/lang/Throwable
    //   45	65	173	finally
    //   69	81	173	finally
    //   102	125	173	finally
    //   150	156	173	finally
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */