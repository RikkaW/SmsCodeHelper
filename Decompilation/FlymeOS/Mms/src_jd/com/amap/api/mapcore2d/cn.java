package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;

class cn
  extends LinearLayout
{
  private Bitmap a;
  private Bitmap b;
  private Bitmap c;
  private Bitmap d;
  private Bitmap e;
  private Bitmap f;
  private ImageView g;
  private ImageView h;
  private bb i;
  private ag j;
  private int k = 0;
  
  public cn(Context paramContext, bb parambb, ag paramag)
  {
    super(paramContext);
    setWillNotDraw(false);
    i = parambb;
    j = paramag;
    try
    {
      a = cy.a("zoomin_selected2d.png");
      a = cy.a(a, y.a);
      b = cy.a("zoomin_unselected2d.png");
      b = cy.a(b, y.a);
      c = cy.a("zoomout_selected2d.png");
      c = cy.a(c, y.a);
      d = cy.a("zoomout_unselected2d.png");
      d = cy.a(d, y.a);
      e = cy.a("zoomin_pressed2d.png");
      f = cy.a("zoomout_pressed2d.png");
      e = cy.a(e, y.a);
      f = cy.a(f, y.a);
      g = new ImageView(paramContext);
      g.setImageBitmap(a);
      g.setOnClickListener(new cn.1(this));
      h = new ImageView(paramContext);
      h.setImageBitmap(c);
      h.setOnClickListener(new cn.2(this));
      g.setOnTouchListener(new cn.3(this));
      h.setOnTouchListener(new cn.4(this));
      g.setPadding(0, 0, 20, -2);
      h.setPadding(0, 0, 20, 20);
      setOrientation(1);
      addView(g);
      addView(h);
      return;
    }
    catch (Exception parambb)
    {
      for (;;)
      {
        cy.a(parambb, "ZoomControllerView", "ZoomControllerView");
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
      d.recycle();
      e.recycle();
      f.recycle();
      a = null;
      b = null;
      c = null;
      d = null;
      e = null;
      f = null;
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "ZoomControllerView", "destory");
    }
  }
  
  public void a(float paramFloat)
  {
    if ((paramFloat < j.h()) && (paramFloat > j.i()))
    {
      g.setImageBitmap(a);
      h.setImageBitmap(c);
    }
    do
    {
      return;
      if (paramFloat <= j.i())
      {
        h.setImageBitmap(d);
        g.setImageBitmap(a);
        return;
      }
    } while (paramFloat < j.h());
    g.setImageBitmap(b);
    h.setImageBitmap(c);
  }
  
  public void a(int paramInt)
  {
    k = paramInt;
    removeView(g);
    removeView(h);
    addView(g);
    addView(h);
  }
  
  public int b()
  {
    return k;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */