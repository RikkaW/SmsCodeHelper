package com.xiaomi.mms.utils.logger;

import java.io.File;
import java.io.FilenameFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class MiXinDebugLog$1$1
  implements FilenameFilter
{
  MiXinDebugLog$1$1(MiXinDebugLog.1 param1, long paramLong) {}
  
  public boolean accept(File paramFile, String paramString)
  {
    if (!paramString.endsWith(".txt")) {}
    for (;;)
    {
      return false;
      try
      {
        long l1 = MiXinDebugLog.access$200().parse(paramString.substring(0, paramString.length() - 4)).getTime();
        long l2 = val$now;
        int i = MiXinDebugLog.access$300();
        if (l2 - l1 > 86400000L * i) {
          return true;
        }
      }
      catch (ParseException paramFile) {}
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.logger.MiXinDebugLog.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */