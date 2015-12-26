package com.xiaomi.mms.mx.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

class ImageCache$1
  extends LruCache<String, Bitmap>
{
  ImageCache$1(ImageCache paramImageCache, int paramInt)
  {
    super(paramInt);
  }
  
  protected int sizeOf(String paramString, Bitmap paramBitmap)
  {
    if (paramBitmap == null) {
      return 0;
    }
    return ImageCacheUtils.getBitmapSize(paramBitmap);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.ImageCache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */