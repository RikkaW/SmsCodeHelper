package cn.com.xy.sms.sdk.util;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class m
{
  private static final String a = "MemoryCache";
  private Map<String, BitmapDrawable> b = Collections.synchronizedMap(new LinkedHashMap(10, 1.5F, true));
  private long c = 0L;
  private long d = 1000000L;
  
  public m()
  {
    new StringBuilder("MemoryCache will use up to ").append(d / 1024.0D / 1024.0D).append("MB");
  }
  
  private static long a(BitmapDrawable paramBitmapDrawable)
  {
    if (paramBitmapDrawable == null) {}
    do
    {
      return 0L;
      paramBitmapDrawable = paramBitmapDrawable.getBitmap();
    } while (paramBitmapDrawable == null);
    return paramBitmapDrawable.getRowBytes() * paramBitmapDrawable.getHeight();
  }
  
  private BitmapDrawable a(String paramString)
  {
    try
    {
      if (!b.containsKey(paramString)) {
        return null;
      }
      paramString = (BitmapDrawable)b.get(paramString);
      return paramString;
    }
    catch (NullPointerException paramString) {}
    return null;
  }
  
  private void a()
  {
    new StringBuilder("cache size=").append(c).append(" length=").append(b.size());
    Iterator localIterator;
    if (c > d) {
      localIterator = b.entrySet().iterator();
    }
    for (;;)
    {
      if (!localIterator.hasNext()) {}
      do
      {
        new StringBuilder("Clean cache. New size ").append(b.size());
        return;
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        c -= a((BitmapDrawable)localEntry.getValue());
        localIterator.remove();
      } while (c <= d);
    }
  }
  
  private void a(long paramLong)
  {
    d = paramLong;
    new StringBuilder("MemoryCache will use up to ").append(d / 1024.0D / 1024.0D).append("MB");
  }
  
  private void a(String paramString, BitmapDrawable paramBitmapDrawable)
  {
    try
    {
      if (b.containsKey(paramString)) {
        c -= a((BitmapDrawable)b.get(paramString));
      }
      b.put(paramString, paramBitmapDrawable);
      c += a(paramBitmapDrawable);
      new StringBuilder("cache size=").append(c).append(" length=").append(b.size());
      if (c > d)
      {
        paramString = b.entrySet().iterator();
        for (;;)
        {
          if (!paramString.hasNext()) {}
          long l1;
          long l2;
          do
          {
            new StringBuilder("Clean cache. New size ").append(b.size());
            return;
            paramBitmapDrawable = (Map.Entry)paramString.next();
            c -= a((BitmapDrawable)paramBitmapDrawable.getValue());
            paramString.remove();
            l1 = c;
            l2 = d;
          } while (l1 <= l2);
        }
      }
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private void b()
  {
    b.clear();
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */