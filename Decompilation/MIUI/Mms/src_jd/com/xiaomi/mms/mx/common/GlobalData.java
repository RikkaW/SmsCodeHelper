package com.xiaomi.mms.mx.common;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.util.DisplayMetrics;

public class GlobalData
{
  public static int DEFAULT_PIC_THUMB_HEIGHT = 0;
  public static int DEFAULT_PIC_THUMB_WIDTH;
  public static int SCREEN_HEIGHT;
  public static int SCREEN_WIDTH;
  public static Context sAppContext;
  public static float screenDensity;
  public static DisplayMetrics screenMatrix;
  public static float screenRate = 0.0F;
  public static Matrix screenRateMatrix;
  
  static
  {
    screenDensity = 0.0F;
    SCREEN_WIDTH = 0;
    SCREEN_HEIGHT = 0;
    DEFAULT_PIC_THUMB_WIDTH = 0;
  }
  
  public static Context app()
  {
    return sAppContext;
  }
  
  private static void calculateScreenRate(Context paramContext)
  {
    screenMatrix = paramContext.getResources().getDisplayMetrics();
    SCREEN_WIDTH = screenMatrixwidthPixels;
    SCREEN_HEIGHT = screenMatrixheightPixels;
    screenRate = screenMatrixdensityDpi / 240.0F;
    screenDensity = screenMatrixdensity;
    screenRateMatrix = new Matrix();
    screenRateMatrix.setScale(screenRate, screenRate);
    if (SCREEN_WIDTH > SCREEN_HEIGHT)
    {
      int i = SCREEN_HEIGHT;
      SCREEN_HEIGHT = SCREEN_WIDTH;
      SCREEN_WIDTH = i;
    }
    paramContext = app().getResources();
    DEFAULT_PIC_THUMB_WIDTH = paramContext.getDimensionPixelSize(2131427446);
    DEFAULT_PIC_THUMB_HEIGHT = paramContext.getDimensionPixelSize(2131427446);
  }
  
  public static void initialize(Context paramContext)
  {
    sAppContext = paramContext;
    calculateScreenRate(paramContext);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.common.GlobalData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */