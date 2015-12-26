package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;

final class n$1
  implements p
{
  public void a(Canvas paramCanvas)
  {
    Paint localPaint = n.b();
    paramCanvas.drawColor(n.a());
    for (int i = 0; i < y.i - y.j; i = y.j + i)
    {
      paramCanvas.drawLine(i, 0.0F, i, y.i, localPaint);
      paramCanvas.drawLine(0.0F, i, y.i, i, localPaint);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.n.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */