package com.amap.api.mapcore2d;

import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import com.amap.api.maps2d.AMap.OnMapTouchListener;

class d
  extends Handler
{
  String a = "onTouchHandler";
  
  d(b paramb) {}
  
  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    try
    {
      if (b.a(b) != null) {
        b.a(b).onTouch((MotionEvent)obj);
      }
      return;
    }
    catch (Throwable paramMessage)
    {
      cy.a(paramMessage, "AMapDelegateImpGLSurfaceView", a);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */