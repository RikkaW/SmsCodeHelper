package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

class x
  extends LinearLayout
{
  private Bitmap a;
  private Bitmap b;
  private ImageView c;
  private bd d;
  private ag e;
  
  public x(Context paramContext, bd parambd, ag paramag)
  {
    super(paramContext);
    d = parambd;
    e = paramag;
    try
    {
      parambd = cy.a("maps_dav_compass_needle_large2d.png");
      b = cy.a(parambd, y.a * 0.8F);
      parambd = cy.a(parambd, y.a * 0.7F);
      a = Bitmap.createBitmap(b.getWidth(), b.getHeight(), Bitmap.Config.ARGB_8888);
      paramag = new Canvas(a);
      Paint localPaint = new Paint();
      localPaint.setAntiAlias(true);
      localPaint.setFilterBitmap(true);
      paramag.drawBitmap(parambd, (b.getWidth() - parambd.getWidth()) / 2, (b.getHeight() - parambd.getHeight()) / 2, localPaint);
      c = new ImageView(paramContext);
      c.setScaleType(ImageView.ScaleType.MATRIX);
      c.setImageBitmap(a);
      c.setOnClickListener(new x.1(this));
      c.setOnTouchListener(new x.2(this));
      addView(c);
      return;
    }
    catch (Exception parambd)
    {
      for (;;)
      {
        cy.a(parambd, "CompassView", "CompassView");
      }
    }
  }
  
  public void a()
  {
    try
    {
      a.recycle();
      b.recycle();
      a = null;
      b = null;
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "CompassView", "destory");
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.x
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */