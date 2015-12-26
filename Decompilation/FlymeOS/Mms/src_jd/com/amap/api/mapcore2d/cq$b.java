package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;

class cq$b
  extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    cq.a locala = (cq.a)obj;
    try
    {
      switch (what)
      {
      case 1: 
        cq.c(a, b[0]);
        return;
      }
    }
    catch (Throwable paramMessage)
    {
      cy.a(paramMessage, "AsyncTask", "handle_handleMessage");
      return;
    }
    a.b(b);
    return;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cq.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */