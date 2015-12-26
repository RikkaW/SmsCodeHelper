package com.meizu.common.widget;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Log;
import java.lang.reflect.Method;

class MultiWaveView$TargetDrawable
{
  private static final boolean DEBUG = false;
  public static final int[] STATE_ACTIVE = { 16842910, 16842914 };
  public static final int[] STATE_FOCUSED = { 16842910, -16842914, 16842908 };
  public static final int[] STATE_INACTIVE = { 16842910, -16842914 };
  private static final String TAG = "TargetDrawable";
  private static final Xfermode sMode = new PorterDuffXfermode(PorterDuff.Mode.XOR);
  private float mAlpha = 1.0F;
  private Bitmap mBitmap;
  private int mCircleColor;
  private float mCircleRadius;
  private Drawable mDrawable;
  private boolean mEnabled = true;
  private boolean mIsCircle = false;
  private Paint mPaint;
  private float mPositionX = 0.0F;
  private float mPositionY = 0.0F;
  private final int mResourceId;
  private float mRotate = 0.0F;
  private float mScaleX = 1.0F;
  private float mScaleY = 1.0F;
  private float mTranslationX = 0.0F;
  private float mTranslationY = 0.0F;
  
  public MultiWaveView$TargetDrawable(int paramInt, float paramFloat)
  {
    mResourceId = -1;
    mDrawable = null;
    mBitmap = null;
    mIsCircle = true;
    mCircleColor = paramInt;
    mCircleRadius = paramFloat;
    mPaint = new Paint();
    mPaint.setFilterBitmap(true);
    mPaint.setAntiAlias(true);
  }
  
  public MultiWaveView$TargetDrawable(Resources paramResources, int paramInt)
  {
    mResourceId = paramInt;
    setDrawable(paramResources, paramInt);
    mPaint = new Paint();
    mPaint.setFilterBitmap(true);
    mPaint.setAntiAlias(true);
    mIsCircle = false;
  }
  
  public MultiWaveView$TargetDrawable(TargetDrawable paramTargetDrawable)
  {
    mResourceId = mResourceId;
    if (mDrawable != null) {}
    for (paramTargetDrawable = mDrawable.mutate();; paramTargetDrawable = null)
    {
      mDrawable = paramTargetDrawable;
      resizeDrawables();
      setState(STATE_INACTIVE);
      mPaint = new Paint();
      mPaint.setFilterBitmap(true);
      mPaint.setAntiAlias(true);
      mIsCircle = false;
      return;
    }
  }
  
  private Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565) {
      try
      {
        localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
        Canvas localCanvas = new Canvas((Bitmap)localObject);
        paramDrawable.setBounds(0, 0, i, j);
        paramDrawable.draw(localCanvas);
        return (Bitmap)localObject;
      }
      catch (OutOfMemoryError paramDrawable)
      {
        Log.w("TargetDrawable", paramDrawable.toString() + "");
        return null;
      }
      catch (IllegalArgumentException paramDrawable)
      {
        Log.w("TargetDrawable", paramDrawable.toString() + "");
      }
    }
    return null;
  }
  
  private int getStateCount(StateListDrawable paramStateListDrawable)
  {
    try
    {
      int i = ((Integer)paramStateListDrawable.getClass().getMethod("getStateCount", new Class[0]).invoke(paramStateListDrawable, new Object[0])).intValue();
      return i;
    }
    catch (Exception paramStateListDrawable)
    {
      paramStateListDrawable.printStackTrace();
    }
    return 0;
  }
  
  private Drawable getStateDrawable(StateListDrawable paramStateListDrawable, int paramInt)
  {
    try
    {
      paramStateListDrawable = (Drawable)paramStateListDrawable.getClass().getMethod("getStateDrawable", new Class[] { Integer.TYPE }).invoke(paramStateListDrawable, new Object[] { Integer.valueOf(paramInt) });
      return paramStateListDrawable;
    }
    catch (Exception paramStateListDrawable)
    {
      paramStateListDrawable.printStackTrace();
    }
    return null;
  }
  
  private int getStateDrawableIndex(StateListDrawable paramStateListDrawable, int[] paramArrayOfInt)
  {
    try
    {
      int i = ((Integer)paramStateListDrawable.getClass().getMethod("getStateDrawableIndex", new Class[] { int[].class }).invoke(paramStateListDrawable, new Object[] { paramArrayOfInt })).intValue();
      return i;
    }
    catch (Exception paramStateListDrawable)
    {
      paramStateListDrawable.printStackTrace();
    }
    return -1;
  }
  
  private void resizeDrawables()
  {
    if ((mDrawable instanceof StateListDrawable))
    {
      StateListDrawable localStateListDrawable = (StateListDrawable)mDrawable;
      int k = 0;
      int i = 0;
      int j = 0;
      while (k < getStateCount(localStateListDrawable))
      {
        Drawable localDrawable = getStateDrawable(localStateListDrawable, k);
        j = Math.max(j, localDrawable.getIntrinsicWidth());
        i = Math.max(i, localDrawable.getIntrinsicHeight());
        k += 1;
      }
      localStateListDrawable.setBounds(0, 0, j, i);
      k = 0;
      while (k < getStateCount(localStateListDrawable))
      {
        getStateDrawable(localStateListDrawable, k).setBounds(0, 0, j, i);
        k += 1;
      }
    }
    if (mDrawable != null) {
      mDrawable.setBounds(0, 0, mDrawable.getIntrinsicWidth(), mDrawable.getIntrinsicHeight());
    }
    if (mDrawable != null) {
      mBitmap = drawableToBitmap(mDrawable);
    }
  }
  
  public void draw(Canvas paramCanvas)
  {
    draw(paramCanvas, false);
  }
  
  public void draw(Canvas paramCanvas, boolean paramBoolean)
  {
    if (!mEnabled) {
      return;
    }
    int i = getWidth() / 2;
    int j = getHeight() / 2;
    paramCanvas.save();
    paramCanvas.translate(mTranslationX + mPositionX, mTranslationY + mPositionY);
    paramCanvas.translate(-i, -j);
    Matrix localMatrix = new Matrix();
    localMatrix.preScale(mScaleX, mScaleY, i, j);
    localMatrix.postRotate(mRotate, i, j);
    if (mBitmap != null) {
      paramCanvas.drawBitmap(mBitmap, localMatrix, mPaint);
    }
    for (;;)
    {
      paramCanvas.restore();
      return;
      if (mIsCircle)
      {
        mPaint.setColor(mCircleColor);
        paramCanvas.drawCircle(i, j, mCircleRadius * mScaleX, mPaint);
      }
    }
  }
  
  public float getAlpha()
  {
    return mAlpha;
  }
  
  public int getHeight()
  {
    if (mDrawable != null) {
      return mDrawable.getIntrinsicHeight();
    }
    if (mBitmap != null) {
      return mBitmap.getHeight();
    }
    if (mIsCircle) {
      return (int)(mCircleRadius * 2.0F);
    }
    return 0;
  }
  
  public float getPositionX()
  {
    return mPositionX;
  }
  
  public float getPositionY()
  {
    return mPositionY;
  }
  
  public int getResourceId()
  {
    return mResourceId;
  }
  
  public float getRotation()
  {
    return mRotate;
  }
  
  public float getScaleX()
  {
    return mScaleX;
  }
  
  public float getScaleY()
  {
    return mScaleY;
  }
  
  public int getWidth()
  {
    if (mDrawable != null) {
      return mDrawable.getIntrinsicWidth();
    }
    if (mBitmap != null) {
      return mBitmap.getWidth();
    }
    if (mIsCircle) {
      return (int)(mCircleRadius * 2.0F);
    }
    return 0;
  }
  
  public float getX()
  {
    return mTranslationX;
  }
  
  public float getY()
  {
    return mTranslationY;
  }
  
  public boolean hasState(int[] paramArrayOfInt)
  {
    if ((mDrawable instanceof StateListDrawable)) {
      return getStateDrawableIndex((StateListDrawable)mDrawable, paramArrayOfInt) != -1;
    }
    return false;
  }
  
  public boolean isActive()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    int[] arrayOfInt;
    int i;
    if ((mDrawable instanceof StateListDrawable))
    {
      arrayOfInt = ((StateListDrawable)mDrawable).getState();
      i = 0;
    }
    for (;;)
    {
      bool1 = bool2;
      if (i < arrayOfInt.length)
      {
        if (arrayOfInt[i] == 16842908) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public boolean isEnabled()
  {
    if (mEnabled)
    {
      if (mIsCircle) {}
      while ((mBitmap != null) || (mDrawable != null)) {
        return true;
      }
    }
    return false;
  }
  
  public void setAlpha(float paramFloat)
  {
    mAlpha = paramFloat;
  }
  
  public void setDrawable(Resources paramResources, int paramInt)
  {
    Drawable localDrawable = null;
    if (paramInt == 0) {}
    for (paramResources = null;; paramResources = paramResources.getDrawable(paramInt))
    {
      if (paramResources != null) {
        localDrawable = paramResources.mutate();
      }
      mDrawable = localDrawable;
      resizeDrawables();
      setState(STATE_INACTIVE);
      return;
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    mEnabled = paramBoolean;
  }
  
  public void setPositionX(float paramFloat)
  {
    mPositionX = paramFloat;
  }
  
  public void setPositionY(float paramFloat)
  {
    mPositionY = paramFloat;
  }
  
  public void setRotation(float paramFloat)
  {
    mRotate = paramFloat;
  }
  
  public void setScaleX(float paramFloat)
  {
    mScaleX = paramFloat;
  }
  
  public void setScaleY(float paramFloat)
  {
    mScaleY = paramFloat;
  }
  
  public void setState(int[] paramArrayOfInt)
  {
    if ((mDrawable instanceof StateListDrawable)) {
      ((StateListDrawable)mDrawable).setState(paramArrayOfInt);
    }
  }
  
  public void setX(float paramFloat)
  {
    mTranslationX = paramFloat;
  }
  
  public void setY(float paramFloat)
  {
    mTranslationY = paramFloat;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.MultiWaveView.TargetDrawable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */