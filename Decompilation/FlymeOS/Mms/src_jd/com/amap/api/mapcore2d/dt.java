package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.database.Cursor;

public class dt
  implements du<dh>
{
  private static String a = dm.f;
  private static String b = dm.g;
  private static String c = dm.k;
  private static String d = dm.h;
  private static String e = dm.i;
  private static String f = dm.j;
  private dh g = null;
  
  public static String a(String paramString)
  {
    return a + "='" + dy.a(paramString) + "'";
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
  
  public dh a(Cursor paramCursor)
  {
    boolean bool = true;
    try
    {
      String str1 = dy.b(paramCursor.getString(1));
      String str2 = dy.b(paramCursor.getString(2));
      String str3 = dy.b(paramCursor.getString(3));
      String[] arrayOfString = b(dy.b(paramCursor.getString(4)));
      String str4 = dy.b(paramCursor.getString(5));
      if (paramCursor.getInt(6) == 0) {
        bool = false;
      }
      paramCursor = new dh.a(str1, str2, str3).a(bool).a(str4).a(arrayOfString).a();
      return paramCursor;
    }
    catch (cz paramCursor)
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
    return dm.a;
  }
  
  public void a(dh paramdh)
  {
    g = paramdh;
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
        localContentValues.put(a, dy.a(g.a()));
        localContentValues.put(b, dy.a(g.b()));
        localContentValues.put(c, Boolean.valueOf(g.e()));
        localContentValues.put(d, dy.a(g.c()));
        localContentValues.put(f, dy.a(g.d()));
        localContentValues.put(e, dy.a(a(g.f())));
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
 * Qualified Name:     com.amap.api.mapcore2d.dt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */