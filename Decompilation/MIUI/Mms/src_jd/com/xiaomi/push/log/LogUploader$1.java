package com.xiaomi.push.log;

import android.content.Context;
import com.xiaomi.channel.commonutils.file.SDCardUtils;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

class LogUploader$1
  extends LogUploader.Task
{
  File file;
  
  LogUploader$1(LogUploader paramLogUploader, int paramInt, Date paramDate1, Date paramDate2, String paramString1, String paramString2, boolean paramBoolean)
  {
    super(paramLogUploader);
  }
  
  public void postProcess()
  {
    if ((file != null) && (file.exists())) {
      LogUploader.access$100(this$0).add(new LogUploader.UploadTask(this$0, val$url, val$token, file, val$force));
    }
    LogUploader.access$200(this$0, 0L);
  }
  
  public void process()
  {
    if (!SDCardUtils.isSDCardUseful()) {}
    for (;;)
    {
      return;
      try
      {
        File localFile = new File(LogUploader.access$000(this$0).getExternalFilesDir(null) + "/.logcache");
        localFile.mkdirs();
        if (localFile.isDirectory())
        {
          LogFilter localLogFilter = new LogFilter();
          localLogFilter.setMaxLen(val$maxlen);
          file = localLogFilter.filter(LogUploader.access$000(this$0), val$from, val$to, localFile);
          return;
        }
      }
      catch (NullPointerException localNullPointerException) {}
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.log.LogUploader.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */