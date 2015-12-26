package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;

public abstract class al
  implements ap<am>
{
  private static final String a = ah.l;
  private static final String b = ah.m;
  private static final String c = ah.n;
  private static final String d = ah.f;
  private am e = null;
  
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
  
  public am a(Cursor paramCursor)
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
      paramCursor = new am();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        paramCursor.a(str1);
        paramCursor.a(i);
        paramCursor.b(at.b(str2));
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
  
  public void a(am paramam)
  {
    e = paramam;
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
        localContentValues.put(d, at.a(e.c()));
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
 * Qualified Name:     com.amap.api.services.core.al
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */