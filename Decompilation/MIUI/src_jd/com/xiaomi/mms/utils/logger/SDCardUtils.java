package com.xiaomi.mms.utils.logger;

import android.os.Environment;
import android.os.StatFs;
import java.io.File;

public class SDCardUtils
{
  public static long getSDCardAvailableBytes()
  {
    if (isSDCardBusy()) {
      return 0L;
    }
    StatFs localStatFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
    long l = localStatFs.getBlockSize();
    return (localStatFs.getAvailableBlocks() - 4L) * l;
  }
  
  public static boolean isSDCardBusy()
  {
    return !Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isSDCardFull()
  {
    return getSDCardAvailableBytes() <= 102400L;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.logger.SDCardUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */