package com.amap.api.services.core;

import android.content.ContentValues;
import android.database.Cursor;

public class ao
  implements ap<ad>
{
  private static String a = ah.f;
  private static String b = ah.g;
  private static String c = ah.k;
  private static String d = ah.h;
  private static String e = ah.i;
  private static String f = ah.j;
  private ad g = null;
  
  public static String a(String paramString)
  {
    return a + "='" + at.a(paramString) + "'";
  }
  
  private String a(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return null;
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int j = paramArrayOfString.length;
      int i = 0;
      while (i < j)
      {
        localStringBuilder.append(paramArrayOfString[i]).append(";");
        i += 1;
      }
      paramArrayOfString = localStringBuilder.toString();
      return paramArrayOfString;
    }
    catch (Throwable paramArrayOfString)
    {
      paramArrayOfString.printStackTrace();
    }
    return null;
  }
  
  private String[] b(String paramString)
  {
    try
    {
      paramString = paramString.split(";");
      return paramString;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static String c()
  {
    return c + "=1";
  }
  
  public ad a(Cursor paramCursor)
  {
    boolean bool = true;
    try
    {
      String str1 = at.b(paramCursor.getString(1));
      String str2 = at.b(paramCursor.getString(2));
      String str3 = at.b(paramCursor.getString(3));
      String[] arrayOfString = b(at.b(paramCursor.getString(4)));
      String str4 = at.b(paramCursor.getString(5));
      if (paramCursor.getInt(6) == 0) {
        bool = false;
      }
      paramCursor = new ad.a(str1, str2, str3).a(bool).a(str4).a(arrayOfString).a();
      return paramCursor;
    }
    catch (v paramCursor)
    {
      paramCursor.printStackTrace();
      return null;
    }
    catch (Throwable paramCursor)
    {
      paramCursor.printStackTrace();
    }
    return null;
  }
  
  public String a()
  {
    return ah.a;
  }
  
  public void a(ad paramad)
  {
    g = paramad;
  }
  
  public ContentValues b()
  {
    localObject1 = null;
    try
    {
      if (g == null) {
        return null;
      }
      ContentValues localContentValues = new ContentValues();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localContentValues.put(a, at.a(g.a()));
        localContentValues.put(b, at.a(g.b()));
        localContentValues.put(c, Boolean.valueOf(g.e()));
        localContentValues.put(d, at.a(g.c()));
        localContentValues.put(f, at.a(g.d()));
        localContentValues.put(e, at.a(a(g.f())));
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
 * Qualified Name:     com.amap.api.services.core.ao
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */