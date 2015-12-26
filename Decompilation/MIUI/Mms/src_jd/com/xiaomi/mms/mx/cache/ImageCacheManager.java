package com.xiaomi.mms.mx.cache;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ImageCacheManager
{
  protected static Map<String, ImageCache> mImageCacheMap = new ConcurrentHashMap();
  
  public static ImageCache get(Context paramContext, ImageCache.ImageCacheParams paramImageCacheParams)
  {
    synchronized (mImageCacheMap)
    {
      ImageCache localImageCache2 = (ImageCache)mImageCacheMap.get(uniqueName);
      ImageCache localImageCache1 = localImageCache2;
      if (localImageCache2 == null)
      {
        localImageCache1 = new ImageCache(paramContext, paramImageCacheParams);
        mImageCacheMap.put(uniqueName, localImageCache1);
      }
      return localImageCache1;
    }
  }
  
  public static ImageCache get(Context paramContext, String paramString)
  {
    synchronized (mImageCacheMap)
    {
      ImageCache localImageCache2 = (ImageCache)mImageCacheMap.get(paramString);
      ImageCache localImageCache1 = localImageCache2;
      if (localImageCache2 == null)
      {
        localImageCache1 = new ImageCache(paramContext, paramString);
        mImageCacheMap.put(paramString, localImageCache1);
      }
      return localImageCache1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.ImageCacheManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */