package com.amap.api.services.core;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ai
{
  private ah a;
  private SQLiteDatabase b;
  
  public ai(Context paramContext)
  {
    a = new ah(paramContext, "logdb.db", null, 1);
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
  
  public <T> void a(ap<T> paramap)
  {
    if (paramap == null) {}
    for (;;)
    {
      return;
      ContentValues localContentValues = paramap.b();
      if ((localContentValues == null) || (paramap.a() == null)) {
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
        b.insert(paramap.a(), null, localContentValues);
        return;
      }
      catch (Throwable paramap)
      {
        for (;;)
        {
          ay.a(paramap, "DataBase", "insertData");
          paramap.printStackTrace();
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
  
  public <T> void a(String paramString, ap<T> paramap)
  {
    if ((paramap.a() == null) || (paramString == null)) {}
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
        b.delete(paramap.a(), paramString, null);
        return;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          ay.a(paramString, "DataBase", "deleteData");
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
  
  public <T> void b(String paramString, ap<T> paramap)
  {
    if ((paramap == null) || (paramString == null) || (paramap.a() == null)) {}
    for (;;)
    {
      return;
      ContentValues localContentValues = paramap.b();
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
        b.update(paramap.a(), localContentValues, paramString, null);
        return;
      }
      catch (Throwable paramString)
      {
        for (;;)
        {
          ay.a(paramString, "DataBase", "updateData");
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
  public <T> java.util.List<T> c(String paramString, ap<T> paramap)
  {
    // Byte code:
    //   0: new 90	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 91	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aload_0
    //   10: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   13: ifnonnull +11 -> 24
    //   16: aload_0
    //   17: aload_0
    //   18: invokespecial 93	com/amap/api/services/core/ai:a	()Landroid/database/sqlite/SQLiteDatabase;
    //   21: putfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   24: aload_0
    //   25: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   28: ifnull +16 -> 44
    //   31: aload_2
    //   32: invokeinterface 43 1 0
    //   37: ifnull +7 -> 44
    //   40: aload_1
    //   41: ifnonnull +6 -> 47
    //   44: aload 4
    //   46: areturn
    //   47: aload_0
    //   48: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   51: aload_2
    //   52: invokeinterface 43 1 0
    //   57: aconst_null
    //   58: aload_1
    //   59: aconst_null
    //   60: aconst_null
    //   61: aconst_null
    //   62: aconst_null
    //   63: invokevirtual 97	android/database/sqlite/SQLiteDatabase:query	(Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   66: astore_3
    //   67: aload_3
    //   68: ifnonnull +51 -> 119
    //   71: aload_3
    //   72: astore_1
    //   73: aload_0
    //   74: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   77: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   80: aload_3
    //   81: astore_1
    //   82: aload_0
    //   83: aconst_null
    //   84: putfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   87: aload_3
    //   88: ifnull +9 -> 97
    //   91: aload_3
    //   92: invokeinterface 100 1 0
    //   97: aload_0
    //   98: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   101: ifnull +15 -> 116
    //   104: aload_0
    //   105: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   108: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   111: aload_0
    //   112: aconst_null
    //   113: putfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   116: aload 4
    //   118: areturn
    //   119: aload_3
    //   120: astore_1
    //   121: aload_3
    //   122: invokeinterface 103 1 0
    //   127: ifeq +202 -> 329
    //   130: aload_3
    //   131: astore_1
    //   132: aload 4
    //   134: aload_2
    //   135: aload_3
    //   136: invokeinterface 106 2 0
    //   141: invokeinterface 112 2 0
    //   146: pop
    //   147: goto -28 -> 119
    //   150: astore_2
    //   151: aload_3
    //   152: astore_1
    //   153: aload_2
    //   154: ldc 60
    //   156: ldc 114
    //   158: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   161: aload_3
    //   162: astore_1
    //   163: aload_2
    //   164: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   167: aload_3
    //   168: ifnull +9 -> 177
    //   171: aload_3
    //   172: invokeinterface 100 1 0
    //   177: aload_0
    //   178: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   181: ifnull +15 -> 196
    //   184: aload_0
    //   185: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   188: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   191: aload_0
    //   192: aconst_null
    //   193: putfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   196: aload 4
    //   198: areturn
    //   199: astore_2
    //   200: aconst_null
    //   201: astore_1
    //   202: aload_1
    //   203: ifnull +9 -> 212
    //   206: aload_1
    //   207: invokeinterface 100 1 0
    //   212: aload_0
    //   213: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   216: ifnull +15 -> 231
    //   219: aload_0
    //   220: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   223: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   226: aload_0
    //   227: aconst_null
    //   228: putfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   231: aload_2
    //   232: athrow
    //   233: astore_1
    //   234: aload_1
    //   235: ldc 60
    //   237: ldc 114
    //   239: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   242: aload_1
    //   243: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   246: goto -34 -> 212
    //   249: astore_1
    //   250: aload_1
    //   251: ldc 60
    //   253: ldc 114
    //   255: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   258: aload_1
    //   259: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   262: goto -31 -> 231
    //   265: astore_1
    //   266: aload_1
    //   267: ldc 60
    //   269: ldc 114
    //   271: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   274: aload_1
    //   275: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   278: goto -101 -> 177
    //   281: astore_1
    //   282: aload_1
    //   283: ldc 60
    //   285: ldc 114
    //   287: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   290: aload_1
    //   291: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   294: goto -98 -> 196
    //   297: astore_1
    //   298: aload_1
    //   299: ldc 60
    //   301: ldc 114
    //   303: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   306: aload_1
    //   307: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   310: goto -213 -> 97
    //   313: astore_1
    //   314: aload_1
    //   315: ldc 60
    //   317: ldc 114
    //   319: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   322: aload_1
    //   323: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   326: goto -210 -> 116
    //   329: aload_3
    //   330: ifnull +9 -> 339
    //   333: aload_3
    //   334: invokeinterface 100 1 0
    //   339: aload_0
    //   340: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   343: ifnull -147 -> 196
    //   346: aload_0
    //   347: getfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   350: invokevirtual 58	android/database/sqlite/SQLiteDatabase:close	()V
    //   353: aload_0
    //   354: aconst_null
    //   355: putfield 29	com/amap/api/services/core/ai:b	Landroid/database/sqlite/SQLiteDatabase;
    //   358: goto -162 -> 196
    //   361: astore_1
    //   362: aload_1
    //   363: ldc 60
    //   365: ldc 114
    //   367: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   370: goto -80 -> 290
    //   373: astore_1
    //   374: aload_1
    //   375: ldc 60
    //   377: ldc 114
    //   379: invokestatic 67	com/amap/api/services/core/ay:a	(Ljava/lang/Throwable;Ljava/lang/String;Ljava/lang/String;)V
    //   382: aload_1
    //   383: invokevirtual 70	java/lang/Throwable:printStackTrace	()V
    //   386: goto -47 -> 339
    //   389: astore_2
    //   390: goto -188 -> 202
    //   393: astore_2
    //   394: aconst_null
    //   395: astore_3
    //   396: goto -245 -> 151
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	399	0	this	ai
    //   0	399	1	paramString	String
    //   0	399	2	paramap	ap<T>
    //   66	330	3	localCursor	android.database.Cursor
    //   7	190	4	localArrayList	java.util.ArrayList
    // Exception table:
    //   from	to	target	type
    //   73	80	150	java/lang/Throwable
    //   82	87	150	java/lang/Throwable
    //   121	130	150	java/lang/Throwable
    //   132	147	150	java/lang/Throwable
    //   47	67	199	finally
    //   206	212	233	java/lang/Throwable
    //   212	231	249	java/lang/Throwable
    //   171	177	265	java/lang/Throwable
    //   177	196	281	java/lang/Throwable
    //   91	97	297	java/lang/Throwable
    //   97	116	313	java/lang/Throwable
    //   339	358	361	java/lang/Throwable
    //   333	339	373	java/lang/Throwable
    //   73	80	389	finally
    //   82	87	389	finally
    //   121	130	389	finally
    //   132	147	389	finally
    //   153	161	389	finally
    //   163	167	389	finally
    //   47	67	393	java/lang/Throwable
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ai
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */