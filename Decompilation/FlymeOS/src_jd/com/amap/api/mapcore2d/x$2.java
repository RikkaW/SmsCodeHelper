package com.amap.api.mapcore2d;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.amap.api.maps2d.model.CameraPosition;

class x$2
  implements View.OnTouchListener
{
  x$2(x paramx) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      x.b(a).setImageBitmap(x.a(a));
    }
    for (;;)
    {
      return false;
      if (paramMotionEvent.getAction() == 1) {
        try
        {
          x.b(a).setImageBitmap(x.c(a));
          paramView = x.d(a).g();
          x.d(a).b(u.a(new CameraPosition(target, zoom, 0.0F, 0.0F)));
        }
        catch (Exception paramView)
        {
          cy.a(paramView, "CompassView", "onTouch");
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.x.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */