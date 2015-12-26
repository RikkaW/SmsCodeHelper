package com.meizu.common.util;

import android.graphics.Bitmap;
import android.util.LruCache;

final class StrokeDrawableUtils$StrokeLruCache$1
  extends LruCache<String, Bitmap>
{
  StrokeDrawableUtils$StrokeLruCache$1(int paramInt)
  {
    super(paramInt);
  }
  
  protected int sizeOf(String paramString, Bitmap paramBitmap)
  {
    return paramBitmap.getByteCount() / 1024;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.StrokeDrawableUtils.StrokeLruCache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */