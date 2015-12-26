package com.xiaomi.mms.mx.cache;

import android.graphics.Bitmap.CompressFormat;

public class ImageCache$ImageCacheParams
{
  public boolean clearDiskCacheOnStart = false;
  public Bitmap.CompressFormat compressFormat = ImageCache.access$000();
  public int compressQuality = 80;
  public boolean diskCacheEnabled = true;
  public int diskCacheSize = 314572800;
  public int memCacheSize = 10485760;
  public boolean memoryCacheEnabled = true;
  public String uniqueName;
  
  public ImageCache$ImageCacheParams(String paramString)
  {
    this(paramString, 10485760);
  }
  
  public ImageCache$ImageCacheParams(String paramString, int paramInt)
  {
    uniqueName = paramString;
    memCacheSize = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.ImageCache.ImageCacheParams
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */