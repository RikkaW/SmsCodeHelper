package com.amap.api.services.core;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class l$a
  extends Handler
{
  String a = "handleMessage";
  
  public l$a(l paraml, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    if (paramMessage == null) {
      return;
    }
    switch (what)
    {
    default: 
      return;
    }
    try
    {
      o localo = (o)obj;
      paramMessage = localo;
      if (localo == null) {
        paramMessage = new o(false, false);
      }
      ay.a(l.a(), c.a(paramMessage.a()));
      l.a = c.a(paramMessage.a());
      return;
    }
    catch (Throwable paramMessage)
    {
      d.a(paramMessage, "ManifestConfig", a);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.l.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */