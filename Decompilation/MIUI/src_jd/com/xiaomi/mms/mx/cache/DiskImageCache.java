package com.xiaomi.mms.mx.cache;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import com.xiaomi.mms.mx.bitmap.reader.BitmapReader;
import com.xiaomi.mms.mx.utils.Log;
import java.io.File;
import java.io.IOException;

public class DiskImageCache
{
  private BitmapReader mBitmapReader;
  private final File mCacheDir;
  private Bitmap.CompressFormat mCompressFormat = Bitmap.CompressFormat.JPEG;
  private int mCompressQuality = 80;
  private DiskLruCache mDiskLruCache = null;
  
  private DiskImageCache(File paramFile, long paramLong)
    throws IOException
  {
    mCacheDir = paramFile;
    mDiskLruCache = DiskLruCache.open(mCacheDir, 1, 1, paramLong);
    mBitmapReader = new BitmapReader();
  }
  
  public static DiskImageCache openCache(Context paramContext, File paramFile, long paramLong)
  {
    try
    {
      if ((!paramFile.exists()) || (!paramFile.isDirectory())) {
        paramFile.mkdirs();
      }
      if ((paramFile.isDirectory()) && (paramFile.canWrite()))
      {
        paramContext = new DiskImageCache(paramFile, Math.max(Math.min(ImageCacheUtils.getUsableSpace(paramFile) / 3L, paramLong), 1L));
        return paramContext;
      }
    }
    catch (IOException paramContext)
    {
      Log.e("DiskImageCache", " Error in openCache: ", paramContext);
    }
    return null;
  }
  
  public void clearCache()
  {
    try
    {
      mDiskLruCache.delete();
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("DiskImageCache", " Error in clearCache: ", localIOException);
    }
  }
  
  public DiskLruCache getDiskLruCache()
  {
    return mDiskLruCache;
  }
  
  public void setCompressParams(Bitmap.CompressFormat paramCompressFormat, int paramInt)
  {
    mCompressFormat = paramCompressFormat;
    mCompressQuality = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.cache.DiskImageCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */