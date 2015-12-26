package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.View;
import java.io.IOException;
import java.io.InputStream;

class cm
  extends View
{
  private Bitmap a;
  private Bitmap b;
  private Paint c = new Paint();
  private boolean d = false;
  private int e = 0;
  private b f;
  private int g = 0;
  private Rect h = new Rect();
  private int i = 10;
  
  public cm(Context paramContext, b paramb)
  {
    super(paramContext);
    f = paramb;
    paramb = paramContext.getResources().getAssets();
    for (;;)
    {
      try
      {
        if (y.e != y.a.b) {
          continue;
        }
        paramContext = paramb.open("apl2d.data");
        a = BitmapFactory.decodeStream(paramContext);
        a = cy.a(a, y.a);
        paramContext.close();
        if (y.e != y.a.b) {
          continue;
        }
        paramContext = paramb.open("apl12d.data");
        b = BitmapFactory.decodeStream(paramContext);
        b = cy.a(b, y.a);
        paramContext.close();
        e = b.getHeight();
      }
      catch (IOException paramContext)
      {
        cy.a(paramContext, "WaterMarkerView", "WaterMarkerView");
        continue;
      }
      c.setAntiAlias(true);
      c.setColor(-16777216);
      c.setStyle(Paint.Style.STROKE);
      return;
      paramContext = paramb.open("ap2d.data");
      continue;
      paramContext = paramb.open("ap12d.data");
    }
  }
  
  public void a()
  {
    try
    {
      if (a != null) {
        a.recycle();
      }
      if (b != null) {
        b.recycle();
      }
      a = null;
      b = null;
      c = null;
      return;
    }
    catch (Exception localException)
    {
      cy.a(localException, "WaterMarkerView", "destory");
    }
  }
  
  public void a(int paramInt)
  {
    g = paramInt;
  }
  
  public void a(boolean paramBoolean)
  {
    d = paramBoolean;
    invalidate();
  }
  
  public Bitmap b()
  {
    if (d) {
      return b;
    }
    return a;
  }
  
  public Point c()
  {
    return new Point(i, getHeight() - e - 10);
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (b == null) {
      return;
    }
    int j = b.getWidth() + 3;
    if (g == 1) {
      i = ((f.getWidth() - j) / 2);
    }
    while (y.e == y.a.b)
    {
      paramCanvas.drawBitmap(b(), i + 15, getHeight() - e - 8, c);
      return;
      if (g == 2) {
        i = (f.getWidth() - j - 10);
      } else {
        i = 10;
      }
    }
    paramCanvas.drawBitmap(b(), i, getHeight() - e - 8, c);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */