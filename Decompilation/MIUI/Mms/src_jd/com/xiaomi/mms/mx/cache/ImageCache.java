package com.xiaomi.mms.mx.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.support.v4.util.LruCache;
import java.io.File;

public class ImageCache
{
  private static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.JPEG;
  private DiskImageCache mDiskCache;
  private ImageCacheParams mImageCacheParams;
  private LruCache<String, Bitmap> mMemoryCache;
  
  public ImageCache(Context paramContext, ImageCacheParams paramImageCacheParams)
  {
    init(paramContext, paramImageCacheParams);
  }
  
  public ImageCache(Context paramContext, String paramString)
  {
    init(paramContext, new ImageCacheParams(paramString));
  }
  
  private static File getDiskCacheDir(Context paramContext, String paramString)
  {
    if (("mounted".equals(Environment.getExternalStorageState())) || (!ImageCacheUtils.isExternalStorageRemovable())) {}
    for (paramContext = ImageCacheUtils.getExternalCacheDir(paramContext).getPath();; paramContext = paramContext.getCacheDir().getPath()) {
      return new File(paramContext + File.separator + paramString);
    }
  }
  
  private void init(Context paramContext, ImageCacheParams paramImageCacheParams)
  {
    mImageCacheParams = paramImageCacheParams;
    if (memoryCacheEnabled) {
      mMemoryCache = new LruCache(memCacheSize)
      {
        protected int sizeOf(String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
          if (paramAnonymousBitmap == null) {
            return 0;
          }
          return ImageCacheUtils.getBitmapSize(paramAnonymousBitmap);
        }
      };
    }
  }
  
  /* Error */
  public DiskImageCache getDiskImageCache()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   13: astore_1
    //   14: aload_0
    //   15: monitorexit
    //   16: aload_1
    //   17: areturn
    //   18: aload_0
    //   19: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   22: getfield 115	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:diskCacheEnabled	Z
    //   25: ifeq +99 -> 124
    //   28: invokestatic 121	com/xiaomi/mms/mx/common/GlobalData:app	()Landroid/content/Context;
    //   31: aload_0
    //   32: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   35: getfield 124	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:uniqueName	Ljava/lang/String;
    //   38: invokestatic 126	com/xiaomi/mms/mx/cache/ImageCache:getDiskCacheDir	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   41: astore_1
    //   42: aload_0
    //   43: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   46: getfield 115	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:diskCacheEnabled	Z
    //   49: ifeq +67 -> 116
    //   52: aload_0
    //   53: invokestatic 121	com/xiaomi/mms/mx/common/GlobalData:app	()Landroid/content/Context;
    //   56: aload_1
    //   57: aload_0
    //   58: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   61: getfield 129	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:diskCacheSize	I
    //   64: i2l
    //   65: invokestatic 135	com/xiaomi/mms/mx/cache/DiskImageCache:openCache	(Landroid/content/Context;Ljava/io/File;J)Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   68: putfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   71: aload_0
    //   72: getfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   75: ifnull +41 -> 116
    //   78: aload_0
    //   79: getfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   82: aload_0
    //   83: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   86: getfield 138	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:compressFormat	Landroid/graphics/Bitmap$CompressFormat;
    //   89: aload_0
    //   90: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   93: getfield 141	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:compressQuality	I
    //   96: invokevirtual 145	com/xiaomi/mms/mx/cache/DiskImageCache:setCompressParams	(Landroid/graphics/Bitmap$CompressFormat;I)V
    //   99: aload_0
    //   100: getfield 95	com/xiaomi/mms/mx/cache/ImageCache:mImageCacheParams	Lcom/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams;
    //   103: getfield 148	com/xiaomi/mms/mx/cache/ImageCache$ImageCacheParams:clearDiskCacheOnStart	Z
    //   106: ifeq +10 -> 116
    //   109: aload_0
    //   110: getfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   113: invokevirtual 151	com/xiaomi/mms/mx/cache/DiskImageCache:clearCache	()V
    //   116: aload_0
    //   117: getfield 112	com/xiaomi/mms/mx/cache/ImageCache:mDiskCache	Lcom/xiaomi/mms/mx/cache/DiskImageCache;
    //   120: astore_1
    //   121: goto -107 -> 14
    //   124: aconst_null
    //   125: astore_1
    //   126: goto -112 -> 14
    //   129: astore_1
    //   130: aload_0
    //   131: monitorexit
    //   132: aload_1
    //   133: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	134	0	this	ImageCache
    //   13	113	1	localObject1	Object
    //   129	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	14	129	finally
    //   18	116	129	finally
    //   116	121	129	finally
  }
  
  public DiskLruCache getDiskLruCache()
  {
    if (getDiskImageCache() == null) {
      return null;
    }
    return getDiskImageCache().getDiskLruCache();
  }
  
  public static class ImageCacheParams
  {
    public boolean clearDiskCacheOnStart = false;
    public Bitmap.CompressFormat compressFormat = ImageCache.DEFAULT_COMPRESS_FORMAT;
    public int compressQuality = 80;
    public boolean diskCacheEnabled = true;
    public int diskCacheSize = 314572800;
    public int memCacheSize = 10485760;
    public boolean memoryCacheEnabled = true;
    public String uniqueName;
    
    public ImageCacheParams(String paramString)
    {
      this(paramString, 10485760);
    }
    
    public ImageCacheParams(String paramString, int paramInt)
    {
      uniqueName = paramString;
      memCacheSize = paramInt;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.ImageCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */