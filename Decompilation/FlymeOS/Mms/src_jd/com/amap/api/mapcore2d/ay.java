package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;

class ay
  extends LinearLayout
{
  private Bitmap a;
  private Bitmap b;
  private Bitmap c;
  private ImageView d;
  private ag e;
  private boolean f = false;
  
  public ay(Context paramContext, bd parambd, ag paramag)
  {
    super(paramContext);
    e = paramag;
    try
    {
      a = cy.a("location_selected2d.png");
      b = cy.a("location_pressed2d.png");
      a = cy.a(a, y.a);
      b = cy.a(b, y.a);
      c = cy.a("location_unselected2d.png");
      c = cy.a(c, y.a);
      d = new ImageView(paramContext);
      d.setImageBitmap(a);
      d.setPadding(0, 20, 20, 0);
      d.setOnClickListener(new ay.1(this));
      d.setOnTouchListener(new ay.2(this));
      addView(d);
      return;
    }
    catch (Exception parambd)
    {
      for (;;)
      {
        cy.a(parambd, "LocationView", "LocationView");
      }
    }
  }
  
  public void a()
  {
    try
    {
      a.recycle();
      b.recycle();
      c.recycle();
      a = null;
      b = null;
      c = null;
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "LocationView", "destory");
    }
  }
  
  public void a(boolean paramBoolean)
  {
    f = paramBoolean;
    if (paramBoolean) {
      d.setImageBitmap(a);
    }
    for (;;)
    {
      d.invalidate();
      return;
      d.setImageBitmap(c);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ay
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */