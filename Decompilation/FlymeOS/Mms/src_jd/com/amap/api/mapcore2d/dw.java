package com.amap.api.mapcore2d;

import android.content.ContentValues;
import android.database.Cursor;

public class dw
  implements du<dx>
{
  private static final String b = dm.o;
  private static final String c = dm.p;
  private static final String d = dm.q;
  private dx a = null;
  
  public dx a(Cursor paramCursor)
  {
    boolean bool3 = true;
    for (;;)
    {
      int j;
      int k;
      try
      {
        int i = paramCursor.getInt(1);
        j = paramCursor.getInt(2);
        k = paramCursor.getInt(3);
        if (i == 0)
        {
          bool1 = false;
          break label94;
          paramCursor = new dx();
        }
      }
      catch (Throwable localThrowable1)
      {
        boolean bool1;
        paramCursor = null;
        localThrowable1.printStackTrace();
        return paramCursor;
      }
      try
      {
        paramCursor.a(bool1);
        paramCursor.c(bool3);
        paramCursor.b(bool2);
        return paramCursor;
      }
      catch (Throwable localThrowable2)
      {
        continue;
      }
      bool1 = true;
      break label94;
      boolean bool2 = true;
      label94:
      if (j == 0)
      {
        bool2 = false;
        if (k == 0) {
          bool3 = false;
        }
      }
    }
  }
  
  public String a()
  {
    return dm.e;
  }
  
  public void a(dx paramdx)
  {
    a = paramdx;
  }
  
  public ContentValues b()
  {
    localObject1 = null;
    try
    {
      if (a == null) {
        return null;
      }
      ContentValues localContentValues = new ContentValues();
      localThrowable1.printStackTrace();
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        localContentValues.put(b, Boolean.valueOf(a.a()));
        localContentValues.put(c, Boolean.valueOf(a.b()));
        localContentValues.put(d, Boolean.valueOf(a.c()));
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
 * Qualified Name:     com.amap.api.mapcore2d.dw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */