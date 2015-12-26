package com.amap.api.mapcore2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.RemoteException;
import android.view.View;

class bu
  extends View
{
  private String a = "";
  private int b = 0;
  private b c;
  private Paint d;
  private Paint e;
  private Rect f;
  
  public bu(Context paramContext, b paramb)
  {
    super(paramContext);
    c = paramb;
    d = new Paint();
    f = new Rect();
    d.setAntiAlias(true);
    d.setColor(-16777216);
    d.setStrokeWidth(2.0F * y.a);
    d.setStyle(Paint.Style.STROKE);
    e = new Paint();
    e.setAntiAlias(true);
    e.setColor(-16777216);
    e.setTextSize(20.0F * y.a);
  }
  
  public void a()
  {
    d = null;
    e = null;
    f = null;
    a = null;
  }
  
  public void a(int paramInt)
  {
    b = paramInt;
  }
  
  public void a(String paramString)
  {
    a = paramString;
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    int j;
    int i;
    try
    {
      boolean bool = c.q().a();
      if (!bool) {
        return;
      }
    }
    catch (RemoteException localRemoteException)
    {
      do
      {
        localRemoteException.printStackTrace();
      } while ((a.equals("")) || (b == 0));
      j = b;
      i = j;
    }
    try
    {
      if (j > c.getWidth() / 5) {
        i = c.getWidth() / 5;
      }
      Point localPoint = c.A();
      e.getTextBounds(a, 0, a.length(), f);
      if (x + i > c.getWidth() - 10)
      {
        j = c.getWidth() - 10 - (f.width() + i) / 2;
        int k = y - f.height() + 5;
        paramCanvas.drawText(a, j, k, e);
        j -= (i - f.width()) / 2;
        k += f.height() - 5;
        paramCanvas.drawLine(j, k - 2, j, k + 2, d);
        paramCanvas.drawLine(j, k, j + i, k, d);
        paramCanvas.drawLine(j + i, k - 2, j + i, k + 2, d);
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        cy.a(localException, "ScaleView", "onDraw");
        i = j;
        continue;
        j = x + (i - f.width()) / 2;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */