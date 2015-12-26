package com.meizu.common.drawble;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.view.ViewDebug.ExportedProperty;
import java.lang.reflect.Method;

public class BlurDrawable
  extends Drawable
{
  public static final int DEFAULT_BLUR_COLOR = -637534209;
  public static final PorterDuff.Mode DEFAULT_BLUR_COLOR_MODE = PorterDuff.Mode.SRC_OVER;
  public static final float DEFAULT_BLUR_LEVEL = 0.9F;
  public static final Method sDrawBlurRectMethod = getDrawBlurRectMethod();
  private boolean mMutated;
  @ViewDebug.ExportedProperty(deepExport=true, prefix="state_")
  private BlurState mState;
  
  public BlurDrawable()
  {
    this(null);
  }
  
  public BlurDrawable(float paramFloat)
  {
    this(null);
    setBlurLevel(paramFloat);
  }
  
  private BlurDrawable(BlurState paramBlurState)
  {
    mState = new BlurState(paramBlurState);
    if (paramBlurState == null) {
      setColorFilter(-637534209, DEFAULT_BLUR_COLOR_MODE);
    }
  }
  
  private static Method getDrawBlurRectMethod()
  {
    try
    {
      Method localMethod = Canvas.class.getMethod("drawBlurRect", new Class[] { Rect.class, Float.TYPE, Paint.class });
      return localMethod;
    }
    catch (Exception localException) {}
    return null;
  }
  
  private static int getUseColor(int paramInt1, int paramInt2, float paramFloat)
  {
    float f = (0xFF000000 & paramInt1) >>> 24;
    return ((int)((f + (255.0F - f) * paramFloat) * paramInt2 / 255.0F) & 0xFF) << 24 | 0xFFFFFF & paramInt1;
  }
  
  private boolean updateUseColor()
  {
    if (sDrawBlurRectMethod == null)
    {
      int i = getUseColor(mState.mBaseColor, mState.mAlpha, mState.mLevel);
      if (mState.mUseColor != i)
      {
        mState.mUseColor = i;
        mState.mPaint.setColor(i);
      }
    }
    else
    {
      return true;
    }
    return false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    if (sDrawBlurRectMethod != null) {
      try
      {
        sDrawBlurRectMethod.invoke(paramCanvas, new Object[] { getBounds(), Float.valueOf(mState.mLevel), mState.mPaint });
        return;
      }
      catch (Exception localException)
      {
        paramCanvas.drawRect(getBounds(), mState.mPaint);
        return;
      }
    }
    paramCanvas.drawRect(getBounds(), mState.mPaint);
  }
  
  public int getAlpha()
  {
    return mState.mPaint.getAlpha();
  }
  
  public float getBlurLevel()
  {
    return mState.mLevel;
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | mState.mChangingConfigurations;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    mState.mChangingConfigurations = getChangingConfigurations();
    return mState;
  }
  
  public int getOpacity()
  {
    if (sDrawBlurRectMethod == null) {}
    switch (mState.mPaint.getAlpha())
    {
    default: 
      return -3;
    case 255: 
      return -1;
    }
    return -2;
  }
  
  public Drawable mutate()
  {
    if ((!mMutated) && (super.mutate() == this))
    {
      mState = new BlurState(mState);
      mMutated = true;
    }
    return this;
  }
  
  public void setAlpha(int paramInt)
  {
    if (mState.mAlpha != paramInt)
    {
      mState.mAlpha = paramInt;
      if (updateUseColor())
      {
        if (sDrawBlurRectMethod != null) {
          mState.mPaint.setAlpha(paramInt);
        }
        invalidateSelf();
      }
    }
  }
  
  public void setBlurLevel(float paramFloat)
  {
    if (mState.mLevel != paramFloat)
    {
      mState.mLevel = paramFloat;
      if (updateUseColor()) {
        invalidateSelf();
      }
    }
  }
  
  public void setColorFilter(int paramInt, PorterDuff.Mode paramMode)
  {
    if (sDrawBlurRectMethod == null)
    {
      if (mState.mBaseColor != paramInt)
      {
        mState.mBaseColor = paramInt;
        if (updateUseColor()) {
          invalidateSelf();
        }
      }
      return;
    }
    super.setColorFilter(paramInt, paramMode);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    mState.mPaint.setColorFilter(paramColorFilter);
    invalidateSelf();
  }
  
  public void setXfermode(Xfermode paramXfermode)
  {
    mState.mPaint.setXfermode(paramXfermode);
    invalidateSelf();
  }
  
  static final class BlurState
    extends Drawable.ConstantState
  {
    int mAlpha = 255;
    int mBaseColor = -637534209;
    @ViewDebug.ExportedProperty
    int mChangingConfigurations;
    float mLevel = 0.9F;
    Paint mPaint = new Paint();
    int mUseColor = BlurDrawable.getUseColor(-637534209, 255, 0.9F);
    
    BlurState(BlurState paramBlurState)
    {
      if (paramBlurState != null)
      {
        mLevel = mLevel;
        mPaint = new Paint(mPaint);
        mChangingConfigurations = mChangingConfigurations;
      }
      while (BlurDrawable.sDrawBlurRectMethod != null) {
        return;
      }
      mPaint.setColor(mUseColor);
    }
    
    public int getChangingConfigurations()
    {
      return mChangingConfigurations;
    }
    
    public Drawable newDrawable()
    {
      return new BlurDrawable(this, null);
    }
    
    public Drawable newDrawable(Resources paramResources)
    {
      return new BlurDrawable(this, null);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.drawble.BlurDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */