package com.xiaomi.mms.utils.logger;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.util.Pair;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.google.code.microlog4android.appender.FileAppender;
import com.google.code.microlog4android.config.PropertyConfigurator;
import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import miui.os.Build;

public class MiXinDebugLog
  implements LoggerInterface
{
  private static int EFFECTIVE_DAY = 2;
  public static String LOG_ROOT;
  private static SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd HH:mm:ss aaa");
  private static List<Pair<String, Throwable>> logs = Collections.synchronizedList(new ArrayList());
  private static SerializedAsyncTaskProcessor mAsyncProcessor;
  private static SimpleDateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");
  private static Logger sLogger;
  private Context mContext;
  private String mTag;
  
  public void initialize(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    mContext = paramContext.getApplicationContext();
    LOG_ROOT = paramString1;
    PropertyConfigurator.getConfigurator(mContext).configure(2131165185);
    sLogger = LoggerFactory.getLogger(paramString2);
    mAsyncProcessor = new SerializedAsyncTaskProcessor(true);
    EFFECTIVE_DAY = paramInt;
    mTag = paramString2;
  }
  
  public void log(String paramString)
  {
    log(paramString, null);
  }
  
  public void log(String paramString, Throwable paramThrowable)
  {
    if (mAsyncProcessor == null) {
      return;
    }
    logs.add(new Pair(String.format("%1$s %2$s", new Object[] { dateFormatter.format(new Date()), paramString }), paramThrowable));
    mAsyncProcessor.addNewTask(new SerializedAsyncTaskProcessor.SerializedAsyncTask()
    {
      public void process()
      {
        try
        {
          if ((SDCardUtils.isSDCardBusy()) || (SDCardUtils.isSDCardFull()) || (Build.IS_CTS_BUILD)) {}
          while (!MiXinDebugLog.logs.isEmpty())
          {
            Object localObject1 = (Pair)MiXinDebugLog.logs.remove(0);
            if (second == null)
            {
              MiXinDebugLog.sLogger.info(first, (Throwable)second);
            }
            else
            {
              MiXinDebugLog.sLogger.error(first, (Throwable)second);
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
                      public boolean accept(File paramAnonymous2File, String paramAnonymous2String)
                      {
                        if (!paramAnonymous2String.endsWith(".txt")) {}
                        for (;;)
                        {
                          return false;
                          try
                          {
                            long l1 = MiXinDebugLog.mFormatter.parse(paramAnonymous2String.substring(0, paramAnonymous2String.length() - 4)).getTime();
                            long l2 = l;
                            int i = MiXinDebugLog.EFFECTIVE_DAY;
                            if (l2 - l1 > 86400000L * i) {
                              return true;
                            }
                          }
                          catch (ParseException paramAnonymous2File) {}
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
                  localObject1 = MiXinDebugLog.mFormatter.format(new Date(l));
                  localObject1 = MiXinDebugLog.LOG_ROOT + (String)localObject1 + ".txt";
                  Object localObject2 = (FileAppender)MiXinDebugLog.sLogger.getAppender(0);
                  ((FileAppender)localObject2).setFileName((String)localObject1);
                  ((FileAppender)localObject2).setAppend(true);
                  if (MiXinDebugLog.logs.isEmpty()) {
                    break;
                  }
                  localObject2 = (Pair)MiXinDebugLog.logs.remove(0);
                  if (second != null)
                  {
                    Throwable localThrowable = (Throwable)second;
                    localObject1 = new StringWriter();
                    localThrowable.printStackTrace(new PrintWriter((Writer)localObject1));
                    localObject2 = new StringBuilder((String)first);
                    ((StringBuilder)localObject2).append("\n");
                    ((StringBuilder)localObject2).append(localObject1.toString());
                    MiXinDebugLog.sLogger.debug(((StringBuilder)localObject2).toString());
                  }
                  else
                  {
                    MiXinDebugLog.sLogger.debug(first, (Throwable)second);
                  }
                }
                catch (Exception localException1)
                {
                  Log.e(mTag, null, localException1);
                  return;
                }
              }
            }
          }
          return;
        }
        catch (Exception localException2) {}
      }
    });
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.logger.MiXinDebugLog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */