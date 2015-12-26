package com.amap.api.services.core;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

public class l
{
  public static ad a;
  private static l b;
  private static Context c;
  private a d;
  private HandlerThread e = new m(this, "manifestThread");
  
  private l(Context paramContext)
  {
    c = paramContext;
    a = c.a(false);
    try
    {
      e.start();
      d = new a(Looper.getMainLooper());
      return;
    }
    catch (Throwable paramContext)
    {
      d.a(paramContext, "ManifestConfig", "ManifestConfig");
    }
  }
  
  public static l a(Context paramContext)
  {
    if (b == null) {
      b = new l(paramContext);
    }
    return b;
  }
  
  class a
    extends Handler
  {
    String a = "handleMessage";
    
    public a(Looper paramLooper)
    {
      super();
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
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.l
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */