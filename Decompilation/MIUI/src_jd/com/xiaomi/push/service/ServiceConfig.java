package com.xiaomi.push.service;

import android.content.Context;
import android.util.Base64;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.xiaomi.channel.commonutils.logger.MyLog;
import com.xiaomi.channel.commonutils.misc.SerializedAsyncTaskProcessor.SerializedAsyncTask;
import com.xiaomi.network.HttpUtils;
import com.xiaomi.push.protobuf.ChannelConfig.PushServiceConfig;
import com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg;
import com.xiaomi.smack.util.SystemUtils;
import com.xiaomi.smack.util.TaskExecutor;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServiceConfig
{
  private static ServiceConfig sInstance = new ServiceConfig();
  private ChannelConfig.PushServiceConfig mConfig;
  private List<Listener> mListener = new ArrayList();
  private SerializedAsyncTaskProcessor.SerializedAsyncTask mPendingFetchTask;
  
  static
  {
    sInstance.load();
  }
  
  private void fetchConfig()
  {
    if (mPendingFetchTask != null) {
      return;
    }
    mPendingFetchTask = new SerializedAsyncTaskProcessor.SerializedAsyncTask()
    {
      boolean success = false;
      
      public void postProcess()
      {
        ServiceConfig.access$202(ServiceConfig.this, null);
        if (success)
        {
          Iterator localIterator = mListener.iterator();
          while (localIterator.hasNext()) {
            ((ServiceConfig.Listener)localIterator.next()).onConfigChange(mConfig);
          }
        }
      }
      
      public void process()
      {
        try
        {
          ChannelConfig.PushServiceConfig localPushServiceConfig = ChannelConfig.PushServiceConfig.parseFrom(Base64.decode(HttpUtils.get(SystemUtils.getContext(), "http://resolver.msg.xiaomi.net/psc/?t=a", null), 10));
          if (localPushServiceConfig != null)
          {
            ServiceConfig.access$002(ServiceConfig.this, localPushServiceConfig);
            success = true;
            ServiceConfig.this.save();
          }
          return;
        }
        catch (Exception localException)
        {
          MyLog.warn("fetch config failure: " + localException.getMessage());
        }
      }
    };
    TaskExecutor.execute(mPendingFetchTask);
  }
  
  public static ServiceConfig getInstance()
  {
    return sInstance;
  }
  
  /* Error */
  private void load()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: aconst_null
    //   6: astore_2
    //   7: aload_3
    //   8: astore_1
    //   9: aload_0
    //   10: getfield 39	com/xiaomi/push/service/ServiceConfig:mConfig	Lcom/xiaomi/push/protobuf/ChannelConfig$PushServiceConfig;
    //   13: ifnull +36 -> 49
    //   16: aload_3
    //   17: astore_1
    //   18: new 67	java/io/BufferedInputStream
    //   21: dup
    //   22: invokestatic 73	com/xiaomi/smack/util/SystemUtils:getContext	()Landroid/content/Context;
    //   25: ldc 75
    //   27: invokevirtual 81	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   30: invokespecial 84	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   33: astore_2
    //   34: aload_0
    //   35: aload_2
    //   36: invokestatic 90	com/google/protobuf/micro/CodedInputStreamMicro:newInstance	(Ljava/io/InputStream;)Lcom/google/protobuf/micro/CodedInputStreamMicro;
    //   39: invokestatic 96	com/xiaomi/push/protobuf/ChannelConfig$PushServiceConfig:parseFrom	(Lcom/google/protobuf/micro/CodedInputStreamMicro;)Lcom/xiaomi/push/protobuf/ChannelConfig$PushServiceConfig;
    //   42: putfield 39	com/xiaomi/push/service/ServiceConfig:mConfig	Lcom/xiaomi/push/protobuf/ChannelConfig$PushServiceConfig;
    //   45: aload_2
    //   46: invokevirtual 99	java/io/BufferedInputStream:close	()V
    //   49: aload_2
    //   50: invokestatic 104	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   53: return
    //   54: astore_3
    //   55: aload 4
    //   57: astore_2
    //   58: aload_2
    //   59: astore_1
    //   60: new 106	java/lang/StringBuilder
    //   63: dup
    //   64: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   67: ldc 109
    //   69: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload_3
    //   73: invokevirtual 117	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   76: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: invokevirtual 120	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   82: invokestatic 126	com/xiaomi/channel/commonutils/logger/MyLog:warn	(Ljava/lang/String;)V
    //   85: aload_2
    //   86: invokestatic 104	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   89: return
    //   90: astore_2
    //   91: aload_1
    //   92: invokestatic 104	com/xiaomi/channel/commonutils/file/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   95: aload_2
    //   96: athrow
    //   97: astore_3
    //   98: aload_2
    //   99: astore_1
    //   100: aload_3
    //   101: astore_2
    //   102: goto -11 -> 91
    //   105: astore_3
    //   106: goto -48 -> 58
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	109	0	this	ServiceConfig
    //   8	92	1	localObject1	Object
    //   6	80	2	localObject2	Object
    //   90	9	2	localObject3	Object
    //   101	1	2	localObject4	Object
    //   4	13	3	localObject5	Object
    //   54	19	3	localException1	Exception
    //   97	4	3	localObject6	Object
    //   105	1	3	localException2	Exception
    //   1	55	4	localObject7	Object
    // Exception table:
    //   from	to	target	type
    //   9	16	54	java/lang/Exception
    //   18	34	54	java/lang/Exception
    //   9	16	90	finally
    //   18	34	90	finally
    //   60	85	90	finally
    //   34	49	97	finally
    //   34	49	105	java/lang/Exception
  }
  
  private void save()
  {
    try
    {
      if (mConfig != null)
      {
        BufferedOutputStream localBufferedOutputStream = new BufferedOutputStream(SystemUtils.getContext().openFileOutput("XMCloudCfg", 0));
        CodedOutputStreamMicro localCodedOutputStreamMicro = CodedOutputStreamMicro.newInstance(localBufferedOutputStream);
        mConfig.writeTo(localCodedOutputStreamMicro);
        localCodedOutputStreamMicro.flush();
        localBufferedOutputStream.close();
      }
      return;
    }
    catch (Exception localException)
    {
      MyLog.warn("save config failure: " + localException.getMessage());
    }
  }
  
  public void addListener(Listener paramListener)
  {
    mListener.add(paramListener);
  }
  
  void clear()
  {
    mListener.clear();
  }
  
  public ChannelConfig.PushServiceConfig getConfig()
  {
    return mConfig;
  }
  
  int getConfigVersion()
  {
    if (mConfig != null) {
      return mConfig.getConfigVersion();
    }
    return 0;
  }
  
  void handle(ChannelMessage.PushServiceConfigMsg paramPushServiceConfigMsg)
  {
    if ((paramPushServiceConfigMsg.hasCloudVersion()) && (paramPushServiceConfigMsg.getCloudVersion() > getConfigVersion())) {
      fetchConfig();
    }
    Iterator localIterator = mListener.iterator();
    while (localIterator.hasNext()) {
      ((Listener)localIterator.next()).onConfigMsgReceive(paramPushServiceConfigMsg);
    }
  }
  
  public static abstract class Listener
  {
    public void onConfigChange(ChannelConfig.PushServiceConfig paramPushServiceConfig) {}
    
    public void onConfigMsgReceive(ChannelMessage.PushServiceConfigMsg paramPushServiceConfigMsg) {}
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.ServiceConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */