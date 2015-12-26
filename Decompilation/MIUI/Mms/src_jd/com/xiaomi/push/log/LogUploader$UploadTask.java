package com.xiaomi.push.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.smack.util.SystemUtils;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

class LogUploader$UploadTask
  extends LogUploader.Task
{
  File file;
  boolean force;
  int retryNum;
  String token;
  boolean uploaded;
  String url;
  
  LogUploader$UploadTask(LogUploader paramLogUploader, String paramString1, String paramString2, File paramFile, boolean paramBoolean)
  {
    super(paramLogUploader);
    url = paramString1;
    token = paramString2;
    file = paramFile;
    force = paramBoolean;
  }
  
  private boolean checkLimit()
  {
    SharedPreferences localSharedPreferences = LogUploader.access$000(this$0).getSharedPreferences("log.timestamp", 0);
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
    return (Network.isWIFIConnected(LogUploader.access$000(this$0))) || ((force) && (Network.hasNetwork(LogUploader.access$000(this$0))));
  }
  
  public void postProcess()
  {
    if (!uploaded)
    {
      retryNum += 1;
      if (retryNum < 3) {
        LogUploader.access$100(this$0).add(this);
      }
    }
    if ((uploaded) || (retryNum >= 3)) {
      file.delete();
    }
    LogUploader.access$200(this$0, (1 << retryNum) * 1000);
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
        localHashMap.put("net", Network.getActiveConnPoint(LogUploader.access$000(this$0)));
        Network.uploadFile(url, localHashMap, file, "file");
      }
      uploaded = true;
      return;
    }
    catch (IOException localIOException) {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.log.LogUploader.UploadTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */