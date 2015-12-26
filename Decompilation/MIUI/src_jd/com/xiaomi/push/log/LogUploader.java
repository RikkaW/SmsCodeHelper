package com.xiaomi.push.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xiaomi.channel.commonutils.file.SDCardUtils;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.smack.util.TaskExecutor;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class LogUploader
{
  private static LogUploader sInstance = null;
  private Context mContext;
  private final ConcurrentLinkedQueue<Task> mTasks = new ConcurrentLinkedQueue();
  
  private LogUploader(Context paramContext)
  {
    mContext = paramContext;
    mTasks.add(new CleanUpTask());
    executeTask(0L);
  }
  
  private void cleanExpiredTask()
  {
    while ((!mTasks.isEmpty()) && ((((Task)mTasks.peek()).isExpired()) || (mTasks.size() > 6)))
    {
      MyLog.v("remove Expired task");
      mTasks.remove();
    }
  }
  
  private void cleanUp()
  {
    if ((SDCardUtils.isSDCardBusy()) || (SDCardUtils.isSDCardUnavailable())) {}
    for (;;)
    {
      return;
      try
      {
        Object localObject = new File(mContext.getExternalFilesDir(null) + "/.logcache");
        if ((!((File)localObject).exists()) || (!((File)localObject).isDirectory())) {
          continue;
        }
        localObject = ((File)localObject).listFiles();
        int j = localObject.length;
        int i = 0;
        while (i < j)
        {
          localObject[i].delete();
          i += 1;
        }
        return;
      }
      catch (NullPointerException localNullPointerException) {}
    }
  }
  
  private void executeTask(long paramLong)
  {
    if (!mTasks.isEmpty()) {
      TaskExecutor.execute(new SerializedAsyncTaskProcessor.SerializedAsyncTask()
      {
        SerializedAsyncTaskProcessor.SerializedAsyncTask current;
        
        public void postProcess()
        {
          if (current != null) {
            current.postProcess();
          }
        }
        
        public void process()
        {
          LogUploader.Task localTask = (LogUploader.Task)mTasks.peek();
          if ((localTask != null) && (localTask.canExcuteNow()))
          {
            current = ((SerializedAsyncTaskProcessor.SerializedAsyncTask)mTasks.remove());
            current.process();
          }
        }
      }, paramLong);
    }
  }
  
  public static LogUploader getInstance(Context paramContext)
  {
    if (sInstance == null) {}
    try
    {
      if (sInstance == null) {
        sInstance = new LogUploader(paramContext);
      }
      sInstancemContext = paramContext;
      return sInstance;
    }
    finally {}
  }
  
  private void uploadIfNeed(long paramLong)
  {
    Task localTask = (Task)mTasks.peek();
    if ((localTask != null) && (localTask.canExcuteNow())) {
      executeTask(paramLong);
    }
  }
  
  public void checkUpload()
  {
    cleanExpiredTask();
    uploadIfNeed(0L);
  }
  
  public void upload(final String paramString1, final String paramString2, final Date paramDate1, final Date paramDate2, final int paramInt, final boolean paramBoolean)
  {
    mTasks.add(new Task(paramInt)
    {
      File file;
      
      public void postProcess()
      {
        if ((file != null) && (file.exists())) {
          mTasks.add(new LogUploader.UploadTask(LogUploader.this, paramString1, paramString2, file, paramBoolean));
        }
        LogUploader.this.uploadIfNeed(0L);
      }
      
      public void process()
      {
        if (!SDCardUtils.isSDCardUseful()) {}
        for (;;)
        {
          return;
          try
          {
            File localFile = new File(mContext.getExternalFilesDir(null) + "/.logcache");
            localFile.mkdirs();
            if (localFile.isDirectory())
            {
              LogFilter localLogFilter = new LogFilter();
              localLogFilter.setMaxLen(paramInt);
              file = localLogFilter.filter(mContext, paramDate1, paramDate2, localFile);
              return;
            }
          }
          catch (NullPointerException localNullPointerException) {}
        }
      }
    });
    executeTask(0L);
  }
  
  class CleanUpTask
    extends LogUploader.Task
  {
    CleanUpTask()
    {
      super();
    }
    
    public void process()
    {
      LogUploader.this.cleanUp();
    }
  }
  
  class Task
    extends SerializedAsyncTaskProcessor.SerializedAsyncTask
  {
    long timestamp = System.currentTimeMillis();
    
    Task() {}
    
    public boolean canExcuteNow()
    {
      return true;
    }
    
    final boolean isExpired()
    {
      return System.currentTimeMillis() - timestamp > 172800000L;
    }
    
    public void process() {}
  }
  
  class UploadTask
    extends LogUploader.Task
  {
    File file;
    boolean force;
    int retryNum;
    String token;
    boolean uploaded;
    String url;
    
    UploadTask(String paramString1, String paramString2, File paramFile, boolean paramBoolean)
    {
      super();
      url = paramString1;
      token = paramString2;
      file = paramFile;
      force = paramBoolean;
    }
    
    private boolean checkLimit()
    {
      SharedPreferences localSharedPreferences = mContext.getSharedPreferences("log.timestamp", 0);
      Object localObject = localSharedPreferences.getString("log.requst", "");
      long l2 = System.currentTimeMillis();
      int i = 0;
      long l1 = l2;
      try
      {
        localObject = new JSONObject((String)localObject);
        l1 = l2;
        l2 = ((JSONObject)localObject).getLong("time");
        l1 = l2;
        j = ((JSONObject)localObject).getInt("times");
        i = j;
        l1 = l2;
      }
      catch (JSONException localJSONException2)
      {
        int j;
        for (;;) {}
      }
      if (System.currentTimeMillis() - l1 < 86400000L)
      {
        j = i;
        if (i > 10) {
          return false;
        }
      }
      else
      {
        l1 = System.currentTimeMillis();
        j = 0;
      }
      localObject = new JSONObject();
      try
      {
        ((JSONObject)localObject).put("time", l1);
        ((JSONObject)localObject).put("times", j + 1);
        localSharedPreferences.edit().putString("log.requst", ((JSONObject)localObject).toString()).commit();
        return true;
      }
      catch (JSONException localJSONException1)
      {
        for (;;)
        {
          MyLog.v("JSONException on put " + localJSONException1.getMessage());
        }
      }
    }
    
    public boolean canExcuteNow()
    {
      return (Network.isWIFIConnected(mContext)) || ((force) && (Network.hasNetwork(mContext)));
    }
    
    public void postProcess()
    {
      if (!uploaded)
      {
        retryNum += 1;
        if (retryNum < 3) {
          mTasks.add(this);
        }
      }
      if ((uploaded) || (retryNum >= 3)) {
        file.delete();
      }
      LogUploader.this.uploadIfNeed((1 << retryNum) * 1000);
    }
    
    public void process()
    {
      try
      {
        if (checkLimit())
        {
          HashMap localHashMap = new HashMap();
          localHashMap.put("uid", SystemUtils.getDeviceUUID());
          localHashMap.put("token", token);
          localHashMap.put("net", Network.getActiveConnPoint(mContext));
          Network.uploadFile(url, localHashMap, file, "file");
        }
        uploaded = true;
        return;
      }
      catch (IOException localIOException) {}
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.log.LogUploader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */