package com.meizu.common.interpolator;

import android.view.animation.Interpolator;

public class PathInterpolatorCompat
  implements Interpolator
{
  private int count = 100;
  private float[] mX;
  private float[] mY;
  private float precision = 0.01F;
  
  public PathInterpolatorCompat(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    initPath(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
  }
  
  public PathInterpolatorCompat(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt)
  {
    paramInt /= 5;
    if (paramInt > count) {}
    for (;;)
    {
      count = paramInt;
      precision = (1.0F / count);
      initPath(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
      return;
      paramInt = count;
    }
  }
  
  private void initPath(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    mX = new float[count];
    mY = new float[count];
    float f1 = 0.0F;
    int i = 0;
    while (i < count)
    {
      float f2 = f1 * f1;
      float f3 = f2 * f1;
      mX[i] = ((1.0F + 3.0F * paramFloat1 - 3.0F * paramFloat3) * f3 + (3.0F * paramFloat3 - 6.0F * paramFloat1) * f2 + 3.0F * paramFloat1 * f1);
      mY[i] = (f2 * (3.0F * paramFloat4 - 6.0F * paramFloat2) + f3 * (1.0F + 3.0F * paramFloat2 - 3.0F * paramFloat4) + 3.0F * paramFloat2 * f1);
      f1 += precision;
      i += 1;
    }
  }
  
  public float getInterpolation(float paramFloat)
  {
    float f = 1.0F;
    if (paramFloat <= 0.0F) {
      f = 0.0F;
    }
    while (paramFloat >= 1.0F) {
      return f;
    }
    int j = 0;
    int i = mX.length - 1;
    if (i - j > 1)
    {
      int k = (j + i) / 2;
      if (paramFloat < mX[k]) {
        i = k;
      }
      for (;;)
      {
        break;
        j = k;
      }
    }
    f = mX[i] - mX[j];
    if (f == 0.0F) {
      return mY[j];
    }
    paramFloat = (paramFloat - mX[j]) / f;
    f = mY[j];
    return paramFloat * (mY[i] - f) + f;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.interpolator.PathInterpolatorCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */