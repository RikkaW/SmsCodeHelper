package com.amap.api.mapcore2d;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

class s
{
  private static float I;
  private static final float[] J;
  private static final float[] K;
  private static float O;
  private static float P = 1.0F / a(1.0F);
  private float A;
  private float B;
  private boolean C = true;
  private Interpolator D;
  private boolean E;
  private float F;
  private int G;
  private float H = ViewConfiguration.getScrollFriction();
  private float L;
  private final float M;
  private float N;
  private int a;
  private int b;
  private int c;
  private float d;
  private float e;
  private float f;
  private int g;
  private int h;
  private float i;
  private float j;
  private float k;
  private int l;
  private int m;
  private int n;
  private int o;
  private int p;
  private int q;
  private float r;
  private float s;
  private float t;
  private long u;
  private long v;
  private float w;
  private float x;
  private float y;
  private float z;
  
  static
  {
    float f2 = 0.0F;
    I = (float)(Math.log(0.78D) / Math.log(0.9D));
    J = new float[101];
    K = new float[101];
    int i1 = 0;
    float f1 = 0.0F;
    if (i1 < 100)
    {
      float f5 = i1 / 100.0F;
      float f3 = 1.0F;
      label55:
      float f4 = (f3 - f1) / 2.0F + f1;
      float f6 = 3.0F * f4 * (1.0F - f4);
      float f7 = ((1.0F - f4) * 0.175F + 0.35000002F * f4) * f6 + f4 * f4 * f4;
      if (Math.abs(f7 - f5) < 1.0E-5D)
      {
        J[i1] = (f4 * (f4 * f4) + f6 * ((1.0F - f4) * 0.5F + f4));
        f3 = 1.0F;
      }
      for (;;)
      {
        f4 = (f3 - f2) / 2.0F + f2;
        f6 = 3.0F * f4 * (1.0F - f4);
        f7 = ((1.0F - f4) * 0.5F + f4) * f6 + f4 * f4 * f4;
        if (Math.abs(f7 - f5) < 1.0E-5D)
        {
          K[i1] = (f4 * (f4 * f4) + ((1.0F - f4) * 0.175F + 0.35000002F * f4) * f6);
          i1 += 1;
          break;
          if (f7 > f5)
          {
            f3 = f4;
            break label55;
          }
          f1 = f4;
          break label55;
        }
        if (f7 > f5) {
          f3 = f4;
        } else {
          f2 = f4;
        }
      }
    }
    float[] arrayOfFloat = J;
    K[100] = 1.0F;
    arrayOfFloat[100] = 1.0F;
    O = 8.0F;
    P = 1.0F;
  }
  
  public s(Context paramContext)
  {
    this(paramContext, null);
  }
  
  private s(Context paramContext, Interpolator paramInterpolator) {}
  
  private s(Context paramContext, Interpolator paramInterpolator, boolean paramBoolean)
  {
    D = paramInterpolator;
    M = (getResourcesgetDisplayMetricsdensity * 160.0F);
    L = b(ViewConfiguration.getScrollFriction());
    E = paramBoolean;
    N = b(0.84F);
  }
  
  static float a(float paramFloat)
  {
    paramFloat = O * paramFloat;
    if (paramFloat < 1.0F) {}
    for (paramFloat -= 1.0F - (float)Math.exp(-paramFloat);; paramFloat = (1.0F - (float)Math.exp(1.0F - paramFloat)) * (1.0F - 0.36787945F) + 0.36787945F) {
      return paramFloat * P;
    }
  }
  
  private float b(float paramFloat)
  {
    return 386.0878F * M * paramFloat;
  }
  
  public final void a(boolean paramBoolean)
  {
    C = paramBoolean;
  }
  
  public final boolean a()
  {
    return C;
  }
  
  public final int b()
  {
    return p;
  }
  
  public final int c()
  {
    return q;
  }
  
  public final float d()
  {
    return r;
  }
  
  public final float e()
  {
    return s;
  }
  
  public final float f()
  {
    return t;
  }
  
  public boolean g()
  {
    if (C) {
      return false;
    }
    int i1 = (int)(AnimationUtils.currentAnimationTimeMillis() - u);
    if (i1 < v) {
      switch (a)
      {
      }
    }
    for (;;)
    {
      return true;
      float f3 = i1 / (float)v;
      i1 = (int)(100.0F * f3);
      float f2 = 1.0F;
      float f1 = 0.0F;
      if (i1 < 100)
      {
        f2 = i1 / 100.0F;
        f1 = (i1 + 1) / 100.0F;
        float f4 = J[i1];
        f1 = (J[(i1 + 1)] - f4) / (f1 - f2);
        f2 = (f3 - f2) * f1 + f4;
      }
      F = (f1 * G / (float)v * 1000.0F);
      p = (b + Math.round((g - b) * f2));
      p = Math.min(p, m);
      p = Math.max(p, l);
      q = (c + Math.round(f2 * (h - c)));
      q = Math.min(q, o);
      q = Math.max(q, n);
      if ((p == g) && (q == h))
      {
        C = true;
        continue;
        f1 = i1 * w;
        if (D == null) {}
        for (f1 = a(f1);; f1 = D.getInterpolation(f1))
        {
          p = (b + Math.round(x * f1));
          q = (c + Math.round(y * f1));
          r = (d + z * f1);
          s = (e + A * f1);
          f2 = f;
          t = (f1 * B + f2);
          break;
        }
        p = g;
        q = h;
        r = i;
        s = j;
        t = k;
        C = true;
      }
    }
  }
  
  public final int h()
  {
    return a;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.s
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */