package com.xiaomi.network;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.channel.commonutils.string.Base64Coder;
import com.xiaomi.common.logger.thrift.Common;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.common.logger.thrift.mfs.HttpLog;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TreeMap;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TCompactProtocol.Factory;

public class UploadHostStatHelper
{
  private static UploadHostStatHelper sInstance;
  private List<HttpRecordCallback> callbacks = new ArrayList();
  private final Random mRandomGenerator = new Random();
  private boolean mTaskPending = false;
  private Timer mTimer = new Timer("Upload Http Record Timer");
  private Context sAppContext = null;
  
  private UploadHostStatHelper(Context paramContext)
  {
    sAppContext = paramContext.getApplicationContext();
  }
  
  private byte[] getBytes(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("UTF-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException) {}
    return paramString.getBytes();
  }
  
  public static UploadHostStatHelper getInstance()
  {
    try
    {
      UploadHostStatHelper localUploadHostStatHelper = sInstance;
      return localUploadHostStatHelper;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private String getMd5Digest(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(getBytes(paramString));
      paramString = String.format("%1$032X", new Object[] { new BigInteger(1, localMessageDigest.digest()) });
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      throw new RuntimeException(paramString);
    }
  }
  
  public static void init(Context paramContext)
  {
    try
    {
      if (sInstance == null) {
        sInstance = new UploadHostStatHelper(paramContext);
      }
      return;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  private void uploadHostStat(String paramString1, String paramString2)
    throws IOException
  {
    String str1 = String.valueOf(System.nanoTime());
    String str2 = String.valueOf(System.currentTimeMillis());
    TreeMap localTreeMap = new TreeMap();
    localTreeMap.put("n", str1);
    localTreeMap.put("d", paramString2);
    localTreeMap.put("t", str2);
    localTreeMap.put("s", getMd5Digest(str1 + paramString2 + str2 + "56C6A520%$C99119A0&^229(!@2746C7"));
    paramString1 = String.format("http://%1$s/diagnoses/v1/report", new Object[] { paramString1 });
    Network.doHttpPost(sAppContext, paramString1, localTreeMap);
  }
  
  private void uploadHostStat(List<HttpApi> paramList, double paramDouble)
    throws TException
  {
    paramList = paramList.iterator();
    for (;;)
    {
      Object localObject;
      if (paramList.hasNext())
      {
        localObject = (HttpApi)paramList.next();
        HttpLog localHttpLog = new HttpLog();
        localHttpLog.setCategory("httpapi");
        localHttpLog.setHttpApi((HttpApi)localObject);
        localHttpLog.setCommon(new Common());
        localObject = new String(Base64Coder.encode(new TSerializer(new TCompactProtocol.Factory()).serialize(localHttpLog)));
        if (mRandomGenerator.nextInt(10000) >= 10000.0D * paramDouble) {}
      }
      else
      {
        try
        {
          uploadHostStat("f3.mi-stat.gslb.mi-idc.com", (String)localObject);
        }
        catch (IOException localIOException)
        {
          continue;
          return;
        }
        catch (Exception localException) {}
      }
    }
  }
  
  public void addCallBack(HttpRecordCallback paramHttpRecordCallback)
  {
    try
    {
      callbacks.add(paramHttpRecordCallback);
      return;
    }
    finally
    {
      paramHttpRecordCallback = finally;
      throw paramHttpRecordCallback;
    }
  }
  
  public void fireHostEvent()
  {
    if (!mTaskPending)
    {
      mTaskPending = true;
      mTimer.schedule(new TimerTask()
      {
        public void run()
        {
          Object localObject2 = new ArrayList();
          synchronized (UploadHostStatHelper.this)
          {
            ((List)localObject2).addAll(callbacks);
            ??? = ((List)localObject2).iterator();
            for (;;)
            {
              if (((Iterator)???).hasNext())
              {
                localObject2 = (UploadHostStatHelper.HttpRecordCallback)((Iterator)???).next();
                List localList = ((UploadHostStatHelper.HttpRecordCallback)localObject2).generateStat();
                double d = ((UploadHostStatHelper.HttpRecordCallback)localObject2).getPercentage();
                if (localList == null) {
                  continue;
                }
                try
                {
                  if (localList.size() > 0) {
                    UploadHostStatHelper.this.uploadHostStat(localList, d);
                  }
                }
                catch (TException localTException)
                {
                  MyLog.warn("uploadHostStat exception" + localTException.toString());
                }
              }
            }
          }
          UploadHostStatHelper.access$202(UploadHostStatHelper.this, false);
        }
      }, 60000L);
    }
  }
  
  public void removeCallBack(HttpRecordCallback paramHttpRecordCallback)
  {
    try
    {
      callbacks.remove(paramHttpRecordCallback);
      return;
    }
    finally
    {
      paramHttpRecordCallback = finally;
      throw paramHttpRecordCallback;
    }
  }
  
  public static abstract interface HttpRecordCallback
  {
    public abstract List<HttpApi> generateStat();
    
    public abstract double getPercentage();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.UploadHostStatHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */