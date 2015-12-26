package com.amap.api.mapcore2d;

import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

class cn$4
  implements View.OnTouchListener
{
  cn$4(cn paramcn) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (cn.c(a).f() <= cn.c(a).i()) {}
    do
    {
      return false;
      if (paramMotionEvent.getAction() == 0)
      {
        cn.b(a).setImageBitmap(cn.j(a));
        return false;
      }
    } while (paramMotionEvent.getAction() != 1);
    cn.b(a).setImageBitmap(cn.a(a));
    try
    {
      cn.c(a).b(u.c());
      return false;
    }
    catch (RemoteException paramView)
    {
      cy.a(paramView, "ZoomControllerView", "onTouch");
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cn.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */