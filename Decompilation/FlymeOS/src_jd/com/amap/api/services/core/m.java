package com.amap.api.services.core;

import android.os.HandlerThread;
import android.os.Message;

class m
  extends HandlerThread
{
  m(l paraml, String paramString)
  {
    super(paramString);
  }
  
  public void run()
  {
    Thread.currentThread().setName("ManifestConfigThread");
    Message localMessage = new Message();
    try
    {
      obj = new n(l.a()).a();
      return;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable = localThrowable;
        d.a(localThrowable, "ManifestConfig", "run");
        what = 3;
        if (l.a(a) != null) {
          l.a(a).sendMessage(localMessage);
        }
      }
    }
    finally
    {
      what = 3;
      if (l.a(a) != null) {
        l.a(a).sendMessage(localMessage);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */