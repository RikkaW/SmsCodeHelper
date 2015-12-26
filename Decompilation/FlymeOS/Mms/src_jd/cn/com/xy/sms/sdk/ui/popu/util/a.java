package cn.com.xy.sms.sdk.ui.popu.util;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;

public final class a
{
  private int a = 1;
  private int b = 1;
  private int c = 1;
  private int d = 0;
  private float e = 0.0F;
  private float f = 0.0F;
  private float g = 0.0F;
  private float h = 0.0F;
  private int i = 0;
  private int j = 1;
  private int k = 0;
  private int l = 0;
  private int m = 0;
  private int n = 0;
  private int o = 0;
  private int p = 0;
  private int q = 0;
  private int r = -1;
  private int s = 0;
  
  public static a a(Context paramContext, String paramString)
  {
    if (paramString != null) {}
    for (;;)
    {
      a locala;
      int i2;
      int i1;
      try
      {
        locala = new a();
        try
        {
          paramString = paramString.split(";");
          i2 = paramString.length;
          i1 = 0;
        }
        catch (Throwable paramString)
        {
          paramContext = locala;
        }
        localObject = paramString[i1];
        if (((String)localObject).startsWith("TL")) {
          e = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
        } else if (((String)localObject).startsWith("TR")) {
          f = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
        }
      }
      catch (Throwable paramString)
      {
        Object localObject;
        paramContext = null;
        continue;
      }
      paramString.printStackTrace();
      return paramContext;
      if (((String)localObject).startsWith("BL"))
      {
        g = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
      }
      else if (((String)localObject).startsWith("BR"))
      {
        h = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
      }
      else if (((String)localObject).startsWith("TP"))
      {
        i = Integer.valueOf(((String)localObject).substring(2)).intValue();
      }
      else if (((String)localObject).startsWith("SC"))
      {
        j = Color.parseColor(((String)localObject).substring(2));
      }
      else if (((String)localObject).startsWith("SW"))
      {
        k = Integer.valueOf(((String)localObject).substring(2)).intValue();
      }
      else if (((String)localObject).startsWith("DW"))
      {
        l = Integer.valueOf(((String)localObject).substring(2)).intValue();
      }
      else if (((String)localObject).startsWith("DG"))
      {
        m = Integer.valueOf(((String)localObject).substring(2)).intValue();
      }
      else if (((String)localObject).startsWith("CX"))
      {
        p = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
      }
      else if (((String)localObject).startsWith("CY"))
      {
        q = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
      }
      else if (((String)localObject).startsWith("GT"))
      {
        r = Integer.valueOf(((String)localObject).substring(2)).intValue();
      }
      else if (((String)localObject).startsWith("GR"))
      {
        s = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(2)).intValue());
      }
      else if (((String)localObject).startsWith("W"))
      {
        n = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(1)).intValue());
      }
      else if (((String)localObject).startsWith("H"))
      {
        o = ViewUtil.dp2px(paramContext, Integer.valueOf(((String)localObject).substring(1)).intValue());
      }
      else if (((String)localObject).startsWith("S"))
      {
        a = Color.parseColor(((String)localObject).substring(1));
      }
      else if (((String)localObject).startsWith("C"))
      {
        b = Color.parseColor(((String)localObject).substring(1));
      }
      else if (((String)localObject).startsWith("E"))
      {
        c = Color.parseColor(((String)localObject).substring(1));
      }
      else if (((String)localObject).startsWith("A"))
      {
        d = Integer.valueOf(((String)localObject).substring(1)).intValue();
        break label657;
        return null;
      }
      while (i1 >= i2)
      {
        return locala;
        label657:
        i1 += 1;
      }
    }
  }
  
  public final Drawable a()
  {
    label261:
    Object localObject2;
    label295:
    label489:
    label494:
    for (;;)
    {
      try
      {
        localObject1 = GradientDrawable.Orientation.LEFT_RIGHT;
        try
        {
          int i1 = d;
          switch (i1)
          {
          }
        }
        catch (Throwable localThrowable2)
        {
          Object localObject3;
          localThrowable2.printStackTrace();
          continue;
          if (c == 1) {
            break label494;
          }
        }
        if ((b != 1) && (a != 1) && (c != 1))
        {
          localObject3 = new int[3];
          localObject3[0] = a;
          localObject3[1] = b;
          localObject3[2] = c;
          if (localObject3 == null) {
            break label261;
          }
        }
      }
      catch (Throwable localThrowable1)
      {
        Object localObject1;
        localThrowable1.printStackTrace();
        return null;
      }
      try
      {
        localObject3 = new GradientDrawable((GradientDrawable.Orientation)localObject1, (int[])localObject3);
        localObject1 = localObject3;
      }
      catch (Throwable localThrowable3)
      {
        localObject2 = null;
        localThrowable3.printStackTrace();
        continue;
        ((GradientDrawable)localObject2).setCornerRadii(new float[] { e, e, f, f, g, g, h, h });
        if (j != 1) {
          ((GradientDrawable)localObject2).setStroke(k, j, l, m);
        }
        if ((n > 0) && (o > 0)) {
          ((GradientDrawable)localObject2).setSize(n, o);
        }
        if ((p > 0) && (q > 0)) {
          ((GradientDrawable)localObject2).setGradientCenter(p, q);
        }
        if (r >= 0) {
          ((GradientDrawable)localObject2).setGradientType(r);
        }
        if (s > 0) {
          ((GradientDrawable)localObject2).setGradientRadius(s);
        }
        return (Drawable)localObject2;
      }
      try
      {
        ((GradientDrawable)localObject3).setShape(i);
        localObject1 = localObject3;
      }
      catch (Throwable localThrowable4)
      {
        break label295;
        localObject2 = null;
        continue;
        Object localObject5 = null;
      }
      if (localObject1 == null)
      {
        return null;
        localObject3 = GradientDrawable.Orientation.LEFT_RIGHT;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.RIGHT_LEFT;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.TOP_BOTTOM;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.BOTTOM_TOP;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.TL_BR;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.TR_BL;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.BL_TR;
        localObject1 = localObject3;
        continue;
        localObject3 = GradientDrawable.Orientation.BR_TL;
        localObject1 = localObject3;
        continue;
        if (a == 1) {
          break label494;
        }
        Object localObject4 = new int[2];
        localObject4[0] = a;
        localObject4[1] = c;
        continue;
        if (a == 1) {
          break label489;
        }
        localObject4 = new GradientDrawable();
        localObject2 = localObject4;
        ((GradientDrawable)localObject4).setColor(a);
        localObject2 = localObject4;
        continue;
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */