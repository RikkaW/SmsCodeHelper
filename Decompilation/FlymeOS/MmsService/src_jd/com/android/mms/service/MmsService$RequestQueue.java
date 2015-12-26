package com.android.mms.service;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;

class MmsService$RequestQueue
  extends Handler
{
  public MmsService$RequestQueue(MmsService paramMmsService, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message arg1)
  {
    ??? = (MmsRequest)obj;
    if (??? != null) {
      try
      {
        ???.execute(this$0, MmsService.access$000(this$0, ???.getSubId()));
        int i;
        int j;
        synchronized (this$0)
        {
          MmsService.access$110(this$0);
          if (MmsService.access$100(this$0) <= 0)
          {
            MmsService.access$200(this$0);
            i = 0;
            while (i < MmsService.access$300(this$0).size())
            {
              j = MmsService.access$300(this$0).keyAt(i);
              MmsService.access$400(this$0, j);
              i += 1;
            }
          }
          return;
        }
        Log.e("MmsService", "RequestQueue: handling empty request");
      }
      finally {}
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsService.RequestQueue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */