package com.meizu.common.util;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.util.LruCache;

class StrokeDrawableUtils$StrokeLruCache
{
  private static BlurMaskFilter mBlurMaskFilter;
  private static final int mCacheSize = mMaxMemory / 8;
  private static final int mMaxMemory = (int)(Runtime.getRuntime().maxMemory() / 1024L);
  private static LruCache<String, Bitmap> mMemoryCache = new StrokeDrawableUtils.StrokeLruCache.1(mCacheSize);
  private static Canvas mStrokeCanvas;
  private static Paint mStrokePaint;
  
  public static Bitmap getExtraAlphaBitmap(int paramInt1, int paramInt2, int paramInt3, Bitmap paramBitmap)
  {
    String str = String.valueOf(paramInt3) + String.valueOf(paramInt1) + String.valueOf(paramInt2);
    Object localObject = (Bitmap)mMemoryCache.get(str);
    if (localObject != null) {
      return (Bitmap)localObject;
    }
    localObject = obtainBlurMaskFilter();
    Paint localPaint = obtainStokePaint();
    localPaint.setMaskFilter((MaskFilter)localObject);
    paramBitmap = paramBitmap.extractAlpha(localPaint, new int[2]);
    mMemoryCache.put(str, paramBitmap);
    return paramBitmap;
  }
  
  public static BlurMaskFilter obtainBlurMaskFilter()
  {
    if (mBlurMaskFilter == null) {
      mBlurMaskFilter = new BlurMaskFilter(1.0F, BlurMaskFilter.Blur.OUTER);
    }
    return mBlurMaskFilter;
  }
  
  public static Paint obtainStokePaint()
  {
    if (mStrokePaint == null) {
      mStrokePaint = new Paint();
    }
    return mStrokePaint;
  }
  
  public static Canvas obtainStrokeCanvas()
  {
    if (mStrokeCanvas == null) {
      mStrokeCanvas = new Canvas();
    }
    return mStrokeCanvas;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.StrokeDrawableUtils.StrokeLruCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */