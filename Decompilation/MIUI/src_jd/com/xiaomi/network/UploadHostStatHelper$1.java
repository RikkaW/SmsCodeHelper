package com.xiaomi.network;

import com.xiaomi.channel.commonutils.logger.MyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import org.apache.thrift.TException;

class UploadHostStatHelper$1
  extends TimerTask
{
  UploadHostStatHelper$1(UploadHostStatHelper paramUploadHostStatHelper) {}
  
  public void run()
  {
    Object localObject2 = new ArrayList();
    synchronized (this$0)
    {
      ((List)localObject2).addAll(UploadHostStatHelper.access$000(this$0));
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
              UploadHostStatHelper.access$100(this$0, localList, d);
            }
          }
          catch (TException localTException)
          {
            MyLog.warn("uploadHostStat exception" + localTException.toString());
          }
        }
      }
    }
    UploadHostStatHelper.access$202(this$0, false);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.network.UploadHostStatHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */