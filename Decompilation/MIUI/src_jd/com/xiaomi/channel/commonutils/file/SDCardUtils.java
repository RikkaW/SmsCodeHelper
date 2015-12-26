package com.xiaomi.channel.commonutils.file;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

public class SDCardUtils
{
  public static long getSDCardAvailableBytes()
  {
    if (isSDCardBusy()) {}
    do
    {
      return 0L;
      localObject = Environment.getExternalStorageDirectory();
    } while ((localObject == null) || (TextUtils.isEmpty(((File)localObject).getPath())));
    Object localObject = new StatFs(((File)localObject).getPath());
    long l = ((StatFs)localObject).getBlockSize();
    return (((StatFs)localObject).getAvailableBlocks() - 4L) * l;
  }
  
  public static boolean isSDCardBusy()
  {
    return !Environment.getExternalStorageState().equals("mounted");
  }
  
  public static boolean isSDCardFull()
  {
    return getSDCardAvailableBytes() <= 102400L;
  }
  
  public static boolean isSDCardUnavailable()
  {
    return Environment.getExternalStorageState().equals("removed");
  }
  
  public static boolean isSDCardUseful()
  {
    return (!isSDCardBusy()) && (!isSDCardFull()) && (!isSDCardUnavailable());
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.channel.commonutils.file.SDCardUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */