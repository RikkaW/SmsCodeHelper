package com.amap.api.mapcore2d;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

class cn$3
  implements View.OnTouchListener
{
  cn$3(cn paramcn) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (cn.c(a).f() >= cn.c(a).h()) {}
    do
    {
      return false;
      if (paramMotionEvent.getAction() == 0)
      {
        cn.e(a).setImageBitmap(cn.i(a));
        return false;
      }
    } while (paramMotionEvent.getAction() != 1);
    cn.e(a).setImageBitmap(cn.f(a));
    try
    {
      cn.c(a).b(u.b());
      return false;
    }
    catch (RemoteException paramView)
    {
      cy.a(paramView, "ZoomControllerView", "ontouch");
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cn.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */