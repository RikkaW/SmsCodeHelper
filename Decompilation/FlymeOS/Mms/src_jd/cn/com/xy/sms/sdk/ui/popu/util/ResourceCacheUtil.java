package cn.com.xy.sms.sdk.ui.popu.util;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.StringUtils;

public class ResourceCacheUtil
{
  private static LruCache<String, Integer> a = new LruCache(40);
  private static LruCache<String, BitmapDrawable> b = new LruCache(30);
  private static LruCache<String, Drawable> c = new LruCache(60);
  
  public static void clearCache()
  {
    a.evictAll();
    c.evictAll();
    b.evictAll();
  }
  
  public static Drawable getColorDrawable(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return (Drawable)c.get(paramString);
  }
  
  public static BitmapDrawable getImgDrawable(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    return (BitmapDrawable)b.get(paramString);
  }
  
  public static int parseColor(String paramString)
  {
    try
    {
      if ((StringUtils.isNull(paramString)) || (paramString.indexOf(".") != -1))
      {
        LogManager.e("ResourceCacheUtil parseColor", "参数color的颜色值错误,color=" + paramString, null);
        return -1;
      }
      Integer localInteger = (Integer)a.get(paramString);
      if (localInteger != null) {
        return localInteger.intValue();
      }
      i = Color.parseColor(paramString);
      try
      {
        a.put(paramString, Integer.valueOf(i));
        return i;
      }
      catch (Throwable paramString) {}
    }
    catch (Throwable paramString)
    {
      for (;;)
      {
        int i = -1;
      }
    }
    paramString.printStackTrace();
    return i;
  }
  
  public static void putColorDrawable(String paramString, Drawable paramDrawable)
  {
    if ((paramString == null) || (paramDrawable == null)) {
      return;
    }
    synchronized (c)
    {
      c.put(paramString, paramDrawable);
      return;
    }
  }
  
  public static void putImgDrawable(String paramString, BitmapDrawable paramBitmapDrawable)
  {
    if ((paramString == null) || (paramBitmapDrawable == null)) {
      return;
    }
    synchronized (b)
    {
      b.put(paramString, paramBitmapDrawable);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */