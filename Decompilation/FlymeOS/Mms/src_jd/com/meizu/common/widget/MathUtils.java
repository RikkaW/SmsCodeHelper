package com.meizu.common.widget;

final class MathUtils
{
  public static float abs(float paramFloat)
  {
    if (paramFloat > 0.0F) {
      return paramFloat;
    }
    return -paramFloat;
  }
  
  public static float constrain(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    if (paramFloat1 < paramFloat2) {
      return paramFloat2;
    }
    if (paramFloat1 > paramFloat3) {
      return paramFloat3;
    }
    return paramFloat1;
  }
  
  public static float log(float paramFloat)
  {
    return (float)Math.log(paramFloat);
  }
  
  public static float max(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 > paramFloat2) {
      return paramFloat1;
    }
    return paramFloat2;
  }
  
  public static float max(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f;
    if (paramFloat1 > paramFloat2)
    {
      f = paramFloat3;
      if (paramFloat1 > paramFloat3) {
        f = paramFloat1;
      }
    }
    do
    {
      return f;
      f = paramFloat3;
    } while (paramFloat2 <= paramFloat3);
    return paramFloat2;
  }
  
  public static float max(int paramInt1, int paramInt2)
  {
    if (paramInt1 > paramInt2) {
      return paramInt1;
    }
    return paramInt2;
  }
  
  public static float max(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 > paramInt2)
    {
      if (paramInt1 > paramInt3) {}
      for (;;)
      {
        return paramInt1;
        paramInt1 = paramInt3;
      }
    }
    if (paramInt2 > paramInt3) {}
    for (;;)
    {
      return paramInt2;
      paramInt2 = paramInt3;
    }
  }
  
  public static float min(float paramFloat1, float paramFloat2)
  {
    if (paramFloat1 < paramFloat2) {
      return paramFloat1;
    }
    return paramFloat2;
  }
  
  public static float min(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    float f;
    if (paramFloat1 < paramFloat2)
    {
      f = paramFloat3;
      if (paramFloat1 < paramFloat3) {
        f = paramFloat1;
      }
    }
    do
    {
      return f;
      f = paramFloat3;
    } while (paramFloat2 >= paramFloat3);
    return paramFloat2;
  }
  
  public static float min(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return paramInt1;
    }
    return paramInt2;
  }
  
  public static float min(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2)
    {
      if (paramInt1 < paramInt3) {}
      for (;;)
      {
        return paramInt1;
        paramInt1 = paramInt3;
      }
    }
    if (paramInt2 < paramInt3) {}
    for (;;)
    {
      return paramInt2;
      paramInt2 = paramInt3;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MathUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */