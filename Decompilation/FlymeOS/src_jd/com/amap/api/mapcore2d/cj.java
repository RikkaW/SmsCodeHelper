package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;

class cj
  extends Handler
{
  cj(ci paramci) {}
  
  public void handleMessage(Message paramMessage)
  {
    if ((paramMessage == null) || (ci.a(a) == null)) {
      return;
    }
    try
    {
      switch (what)
      {
      case 0: 
        ci.a(a).d(ci.b(a));
        return;
      }
    }
    catch (Throwable paramMessage)
    {
      cy.a(paramMessage, "UiSettingsDelegateImp", "handle_handleMessage");
      return;
    }
    ci.a(a).g(ci.c(a));
    return;
    ci.a(a).f(ci.d(a));
    return;
    ci.a(a).e(ci.e(a));
    return;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */