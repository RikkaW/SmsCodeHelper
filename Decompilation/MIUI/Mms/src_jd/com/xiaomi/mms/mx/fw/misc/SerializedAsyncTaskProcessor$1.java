package com.xiaomi.mms.mx.fw.misc;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class SerializedAsyncTaskProcessor$1
  extends Handler
{
  SerializedAsyncTaskProcessor$1(SerializedAsyncTaskProcessor paramSerializedAsyncTaskProcessor, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    SerializedAsyncTaskProcessor.SerializedAsyncTask localSerializedAsyncTask = (SerializedAsyncTaskProcessor.SerializedAsyncTask)obj;
    if (what == 0) {
      localSerializedAsyncTask.preProcess();
    }
    for (;;)
    {
      super.handleMessage(paramMessage);
      return;
      if (what == 1) {
        localSerializedAsyncTask.postProcess();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.misc.SerializedAsyncTaskProcessor.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */