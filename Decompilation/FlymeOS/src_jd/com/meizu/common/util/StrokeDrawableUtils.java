package com.meizu.common.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BlurMaskFilter;
import android.graphics.BlurMaskFilter.Blur;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.PictureDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.LruCache;

public class StrokeDrawableUtils
{
  private static final int DEF_STROKE_RADIUS = 1;
  private static final int EFFECTIVE_COLOR = -16777216;
  private static final int MAX_LENGTH = 1000;
  private static final int STROKE_ALPHA_VALUE = 78;
  private static final int STROKE_RECT_ALPHA_VALUE = 26;
  private static final Object syncStroke = new Object();
  private static final Object syncStrokeRect = new Object();
  
  public static Drawable createRectStrokeDrawable(Drawable paramDrawable, Resources paramResources)
  {
    Object localObject2 = syncStrokeRect;
    Object localObject1 = null;
    for (;;)
    {
      try
      {
        int i = paramDrawable.getIntrinsicWidth();
        int j = paramDrawable.getIntrinsicHeight();
        Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
        if (localBitmap == null) {
          break label150;
        }
        localBitmap.eraseColor(0);
        localObject1 = StrokeLruCache.obtainStrokeCanvas();
        ((Canvas)localObject1).setBitmap(localBitmap);
        paramDrawable.setBounds(0, 0, i, j);
        paramDrawable.draw((Canvas)localObject1);
        ((Canvas)localObject1).save();
        Paint localPaint = StrokeLruCache.obtainStokePaint();
        localPaint.setStrokeWidth(1.0F);
        localPaint.setColor(-16777216);
        localPaint.setAlpha(26);
        localPaint.setStyle(Paint.Style.STROKE);
        ((Canvas)localObject1).drawRect(1.0F, 1.0F, i - 1, j - 1, localPaint);
        localObject1 = new BitmapDrawable(paramResources, localBitmap);
      }
      finally {}
      return paramDrawable;
      label150:
      if (localObject1 != null) {
        paramDrawable = (Drawable)localObject1;
      }
    }
  }
  
  @Deprecated
  public static Drawable createStrokeDrawable(Drawable paramDrawable, Resources paramResources)
  {
    return createStrokeDrawable(paramDrawable, paramResources, Boolean.valueOf(true));
  }
  
  public static Drawable createStrokeDrawable(Drawable paramDrawable, Resources paramResources, Boolean paramBoolean)
  {
    Paint localPaint = null;
    for (;;)
    {
      synchronized (syncStroke)
      {
        Bitmap localBitmap1 = drawable2Bitmap(paramDrawable);
        paramBoolean = localPaint;
        if (localBitmap1 != null)
        {
          int i = paramDrawable.getIntrinsicHeight() + 2;
          int j = paramDrawable.getIntrinsicWidth() + 2;
          Bitmap localBitmap2 = Bitmap.createBitmap(j, i, Bitmap.Config.ARGB_8888);
          paramBoolean = localPaint;
          if (localBitmap2 != null)
          {
            int k = paramDrawable.hashCode();
            localBitmap2.eraseColor(0);
            paramBoolean = StrokeLruCache.obtainStrokeCanvas();
            paramBoolean.setBitmap(localBitmap2);
            localPaint = StrokeLruCache.obtainStokePaint();
            Bitmap localBitmap3 = StrokeLruCache.getExtraAlphaBitmap(j, i, k, localBitmap1);
            localPaint.reset();
            localPaint.setAlpha(78);
            paramBoolean.drawBitmap(localBitmap3, j - localBitmap3.getWidth() >> 1, i - localBitmap3.getHeight() >> 1, localPaint);
            paramBoolean.drawBitmap(localBitmap1, j - localBitmap1.getWidth() >> 1, i - localBitmap1.getHeight() >> 1, null);
            paramBoolean = new BitmapDrawable(paramResources, localBitmap2);
            paramBoolean.setBounds(0, 0, j, i);
            break label201;
            return paramDrawable;
          }
        }
      }
      label201:
      if (paramBoolean != null) {
        paramDrawable = paramBoolean;
      }
    }
  }
  
  private static Bitmap drawable2Bitmap(Drawable paramDrawable)
  {
    if (paramDrawable == null) {}
    Bitmap localBitmap;
    do
    {
      do
      {
        do
        {
          return null;
        } while ((paramDrawable.getIntrinsicHeight() > 1000) || (paramDrawable.getIntrinsicWidth() > 1000));
        if ((paramDrawable instanceof BitmapDrawable)) {
          return ((BitmapDrawable)paramDrawable).getBitmap();
        }
      } while (((!(paramDrawable instanceof NinePatchDrawable)) && (!(paramDrawable instanceof StateListDrawable)) && (!(paramDrawable instanceof GradientDrawable)) && (!(paramDrawable instanceof InsetDrawable)) && (!(paramDrawable instanceof LayerDrawable)) && (!(paramDrawable instanceof LevelListDrawable)) && (!(paramDrawable instanceof PaintDrawable)) && (!(paramDrawable instanceof PictureDrawable)) && (!(paramDrawable instanceof RotateDrawable)) && (!(paramDrawable instanceof ScaleDrawable)) && (!(paramDrawable instanceof ShapeDrawable)) && (!(paramDrawable instanceof ClipDrawable))) || (paramDrawable.getIntrinsicWidth() <= 0) || (paramDrawable.getIntrinsicHeight() <= 0));
      localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
    } while (localBitmap == null);
    Canvas localCanvas = StrokeLruCache.obtainStrokeCanvas();
    localCanvas.setBitmap(localBitmap);
    paramDrawable.setBounds(0, 0, paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight());
    paramDrawable.draw(localCanvas);
    return localBitmap;
  }
  
  static class StrokeLruCache
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
}

/* Location:
 * Qualified Name:     com.meizu.common.util.StrokeDrawableUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */