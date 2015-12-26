package com.amap.api.mapcore2d;

import android.location.Location;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.amap.api.maps2d.model.LatLng;

class ay$2
  implements View.OnTouchListener
{
  ay$2(ay paramay) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (!ay.a(a)) {}
    for (;;)
    {
      return false;
      if (paramMotionEvent.getAction() == 0)
      {
        ay.c(a).setImageBitmap(ay.b(a));
        return false;
      }
      if (paramMotionEvent.getAction() == 1) {
        try
        {
          ay.c(a).setImageBitmap(ay.d(a));
          ay.e(a).c(true);
          paramView = ay.e(a).p();
          if (paramView != null)
          {
            paramMotionEvent = new LatLng(paramView.getLatitude(), paramView.getLongitude());
            ay.e(a).a(paramView);
            ay.e(a).a(u.a(paramMotionEvent, ay.e(a).f()));
            return false;
          }
        }
        catch (Exception paramView)
        {
          cy.a(paramView, "LocationView", "onTouch");
        }
      }
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ay.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */