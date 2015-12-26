package com.amap.api.mapcore2d;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import java.util.List;

class q$1
  implements p
{
  q$1(q paramq, List paramList) {}
  
  public void a(Canvas paramCanvas)
  {
    if (q.a(b) == null)
    {
      q.a(b, new Paint());
      q.a(b).setStyle(Paint.Style.STROKE);
      q.a(b).setDither(true);
      q.a(b).setAntiAlias(true);
      q.a(b).setStrokeJoin(Paint.Join.ROUND);
      q.a(b).setStrokeCap(Paint.Cap.ROUND);
    }
    if (q.b(b) == null) {
      q.a(b, new Path());
    }
    int m = a.size();
    int j = 0;
    while (j < m)
    {
      Object localObject = (cf)a.get(j);
      q.a(b).setStrokeWidth(3.0F);
      int i = ((cf)localObject).b();
      int k;
      label201:
      PointF localPointF;
      if (i == 1)
      {
        q.a(b).setColor(-65536);
        localObject = ((cf)localObject).a();
        int n = ((List)localObject).size();
        k = 0;
        i = 1;
        if (k >= n) {
          break label321;
        }
        localPointF = (PointF)((List)localObject).get(k);
        if (i == 0) {
          break label298;
        }
        q.b(b).moveTo(x, y);
        i = 0;
      }
      for (;;)
      {
        k += 1;
        break label201;
        if (i == 2)
        {
          q.a(b).setColor(65280);
          break;
        }
        if (i != 3) {
          break;
        }
        q.a(b).setColor(-16711936);
        break;
        label298:
        q.b(b).lineTo(x, y);
      }
      label321:
      paramCanvas.drawPath(q.b(b), q.a(b));
      q.b(b).reset();
      j += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.q.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */