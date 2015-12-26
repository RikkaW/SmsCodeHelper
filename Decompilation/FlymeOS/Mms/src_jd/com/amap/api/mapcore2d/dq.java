package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class dq
  implements du<dr>
{
  private static final String a = dm.l;
  private static final String b = dm.m;
  private static final String c = dm.n;
  private static final String d = dm.f;
  private dr e = null;
  
  public static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append(b).append("=").append(paramInt);
      return localStringBuilder.toString();
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
      }
    }
  }
  
  public static String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      localStringBuilder.append(a).append("='").append(paramString).append("'");
      return localStringBuilder.toString();
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        paramString.printStackTrace();
      }
    }
  }
  
  public dr a(Cursor paramCursor)
  {
    Object localObject = null;
    if (paramCursor == null) {
      return null;
    }
    try
    {
      String str1 = paramCursor.getString(1);
      int i = paramCursor.getInt(2);
      String str2 = paramCursor.getString(4);
      int j = paramCursor.getInt(3);
      paramCursor = new dr();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        paramCursor.a(str1);
        paramCursor.a(i);
        paramCursor.b(dy.b(str2));
        paramCursor.b(j);
        return paramCursor;
      }
      catch (Throwable localThrowable2)
      {
        for (;;) {}
      }
      localThrowable1 = localThrowable1;
      paramCursor = (Cursor)localObject;
    }
    return paramCursor;
  }
  
  public void a(dr paramdr)
  {
    e = paramdr;
  }
  
  public ContentValues b()
  {
    localObject1 = null;
    try
    {
      if (e == null) {
        return null;
      }
      ContentValues localContentValues = new ContentValues();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localContentValues.put(a, e.b());
        localContentValues.put(b, Integer.valueOf(e.a()));
        localContentValues.put(d, dy.a(e.c()));
        localContentValues.put(c, Integer.valueOf(e.d()));
        return localContentValues;
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          localObject1 = localThrowable1;
          Object localObject2 = localThrowable2;
        }
      }
      localThrowable1 = localThrowable1;
    }
    return (ContentValues)localObject1;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */