package com.xiaomi.mms.utils.logger;

import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.appender.FileAppender;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import miui.os.Build;

class MiXinDebugLog$1
  extends SerializedAsyncTaskProcessor.SerializedAsyncTask
{
  MiXinDebugLog$1(MiXinDebugLog paramMiXinDebugLog) {}
  
  public void process()
  {
    try
    {
      if ((SDCardUtils.isSDCardBusy()) || (SDCardUtils.isSDCardFull()) || (Build.IS_CTS_BUILD)) {}
      while (!MiXinDebugLog.access$000().isEmpty())
      {
        Object localObject1 = (Pair)MiXinDebugLog.access$000().remove(0);
        if (second == null)
        {
          MiXinDebugLog.access$100().info(first, (Throwable)second);
        }
        else
        {
          MiXinDebugLog.access$100().error(first, (Throwable)second);
          continue;
          for (;;)
          {
            try
            {
              final long l = System.currentTimeMillis();
              localObject1 = new File(Environment.getExternalStorageDirectory(), MiXinDebugLog.LOG_ROOT);
              if (((File)localObject1).isDirectory())
              {
                localObject1 = ((File)localObject1).listFiles(new FilenameFilter()
                {
                  public boolean accept(File paramAnonymousFile, String paramAnonymousString)
                  {
                    if (!paramAnonymousString.endsWith(".txt")) {}
                    for (;;)
                    {
                      return false;
                      try
                      {
                        long l1 = MiXinDebugLog.access$200().parse(paramAnonymousString.substring(0, paramAnonymousString.length() - 4)).getTime();
                        long l2 = l;
                        int i = MiXinDebugLog.access$300();
                        if (l2 - l1 > 86400000L * i) {
                          return true;
                        }
                      }
                      catch (ParseException paramAnonymousFile) {}
                    }
                    return false;
                  }
                });
                if (localObject1 != null)
                {
                  int j = localObject1.length;
                  int i = 0;
                  if (i < j)
                  {
                    localObject1[i].delete();
                    i += 1;
                    continue;
                  }
                }
              }
              else
              {
                ((File)localObject1).mkdirs();
              }
              localObject1 = MiXinDebugLog.access$200().format(new Date(l));
              localObject1 = MiXinDebugLog.LOG_ROOT + (String)localObject1 + ".txt";
              Object localObject2 = (FileAppender)MiXinDebugLog.access$100().getAppender(0);
              ((FileAppender)localObject2).setFileName((String)localObject1);
              ((FileAppender)localObject2).setAppend(true);
              if (MiXinDebugLog.access$000().isEmpty()) {
                break;
              }
              localObject2 = (Pair)MiXinDebugLog.access$000().remove(0);
              if (second != null)
              {
                Throwable localThrowable = (Throwable)second;
                localObject1 = new StringWriter();
                localThrowable.printStackTrace(new PrintWriter((Writer)localObject1));
                localObject2 = new StringBuilder((String)first);
                ((StringBuilder)localObject2).append("\n");
                ((StringBuilder)localObject2).append(localObject1.toString());
                MiXinDebugLog.access$100().debug(((StringBuilder)localObject2).toString());
              }
              else
              {
                MiXinDebugLog.access$100().debug(first, (Throwable)second);
              }
            }
            catch (Exception localException1)
            {
              Log.e(MiXinDebugLog.access$400(this$0), null, localException1);
              return;
            }
          }
        }
      }
      return;
    }
    catch (Exception localException2) {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.logger.MiXinDebugLog.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */