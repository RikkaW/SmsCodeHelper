package com.android.mms.ui;

import android.app.Activity;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;

public class ScaleDetector
{
  private static String LOGTAG = "ScaleDetector";
  private int mActiveId0;
  private int mActiveId1;
  private MotionEvent mCurrEvent;
  private float mCurrFingerDiffX;
  private float mCurrFingerDiffY;
  private float mCurrLen;
  private float mCurrPressure;
  private boolean mGestureInProgress;
  private boolean mInvalidGesture;
  private OnScaleListener mListener;
  private MotionEvent mPrevEvent;
  private float mPrevFingerDiffX;
  private float mPrevFingerDiffY;
  private float mPrevLen;
  private float mPrevPressure;
  private float mScaleFactor;
  
  public ScaleDetector(OnScaleListener paramOnScaleListener)
  {
    mListener = paramOnScaleListener;
    reset();
  }
  
  private void log(String paramString)
  {
    Log.e(LOGTAG, paramString);
  }
  
  private void reset()
  {
    if (mPrevEvent != null)
    {
      mPrevEvent.recycle();
      mPrevEvent = null;
    }
    if (mCurrEvent != null)
    {
      mCurrEvent.recycle();
      mCurrEvent = null;
    }
    mActiveId0 = -1;
    mActiveId1 = -1;
    mGestureInProgress = false;
    mInvalidGesture = false;
  }
  
  private void setContext(MotionEvent paramMotionEvent)
  {
    if (mCurrEvent != null) {
      mCurrEvent.recycle();
    }
    mCurrEvent = MotionEvent.obtain(paramMotionEvent);
    mCurrLen = -1.0F;
    mPrevLen = -1.0F;
    mScaleFactor = -1.0F;
    MotionEvent localMotionEvent = mPrevEvent;
    int i = localMotionEvent.findPointerIndex(mActiveId0);
    int j = localMotionEvent.findPointerIndex(mActiveId1);
    int k = paramMotionEvent.findPointerIndex(mActiveId0);
    int m = paramMotionEvent.findPointerIndex(mActiveId1);
    if ((i < 0) || (j < 0) || (k < 0) || (m < 0))
    {
      mInvalidGesture = true;
      if (mGestureInProgress) {
        mListener.onScaleEnd(this);
      }
      return;
    }
    float f1 = localMotionEvent.getX(i);
    float f2 = localMotionEvent.getY(i);
    float f3 = localMotionEvent.getX(j);
    float f4 = localMotionEvent.getY(j);
    float f5 = paramMotionEvent.getX(k);
    float f6 = paramMotionEvent.getY(k);
    float f7 = paramMotionEvent.getX(m);
    float f8 = paramMotionEvent.getY(m);
    mPrevFingerDiffX = (f3 - f1);
    mPrevFingerDiffY = (f4 - f2);
    mCurrFingerDiffX = (f7 - f5);
    mCurrFingerDiffY = (f8 - f6);
    mCurrPressure = (paramMotionEvent.getPressure(k) + paramMotionEvent.getPressure(m));
    mPrevPressure = (localMotionEvent.getPressure(i) + localMotionEvent.getPressure(j));
  }
  
  public float getCurrentSpan()
  {
    if (mCurrLen == -1.0F)
    {
      float f1 = mCurrFingerDiffX;
      float f2 = mCurrFingerDiffY;
      mCurrLen = FloatMath.sqrt(f1 * f1 + f2 * f2);
    }
    return mCurrLen;
  }
  
  public float getPreviousSpan()
  {
    if (mPrevLen == -1.0F)
    {
      float f1 = mPrevFingerDiffX;
      float f2 = mPrevFingerDiffY;
      mPrevLen = FloatMath.sqrt(f1 * f1 + f2 * f2);
    }
    return mPrevLen;
  }
  
  public float getScaleFactor()
  {
    if (mScaleFactor == -1.0F) {
      mScaleFactor = (getCurrentSpan() / getPreviousSpan());
    }
    return mScaleFactor;
  }
  
  public boolean onTouchEvent(Activity paramActivity, MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getActionMasked();
    if (i == 0) {
      reset();
    }
    switch (i)
    {
    }
    for (;;)
    {
      if (!mGestureInProgress) {
        log("return value is false, action = " + paramMotionEvent.getActionMasked());
      }
      return mGestureInProgress;
      mActiveId0 = paramMotionEvent.getPointerId(0);
      log("ACTION_DOWN: count = " + paramMotionEvent.getPointerCount());
      continue;
      i = paramMotionEvent.getPointerCount();
      int j = paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex());
      log("ACTION_POINTER_DOWN: count = " + i + ", actionId = " + j);
      if (i == 2)
      {
        mActiveId0 = paramMotionEvent.getPointerId(0);
        mActiveId1 = paramMotionEvent.getPointerId(1);
        mPrevEvent = MotionEvent.obtain(paramMotionEvent);
        setContext(paramMotionEvent);
        if (mListener != null)
        {
          mGestureInProgress = mListener.onScaleStart(this);
          if ((mGestureInProgress) && (paramActivity != null))
          {
            MotionEvent localMotionEvent = MotionEvent.obtain(0L, 0L, 3, 0.0F, 0.0F, 0);
            paramActivity.getWindow().superDispatchTouchEvent(localMotionEvent);
            localMotionEvent.recycle();
          }
        }
        mInvalidGesture = false;
      }
      if ((i > 2) && (!mInvalidGesture))
      {
        mInvalidGesture = true;
        setContext(paramMotionEvent);
        if ((mGestureInProgress) && (mListener != null))
        {
          mListener.onScaleEnd(this);
          continue;
          if ((mGestureInProgress) && (!mInvalidGesture))
          {
            setContext(paramMotionEvent);
            if ((mCurrPressure / mPrevPressure > 0.67F) && (mListener.onScale(this)))
            {
              mPrevEvent.recycle();
              mPrevEvent = MotionEvent.obtain(paramMotionEvent);
              continue;
              i = paramMotionEvent.getPointerCount();
              j = paramMotionEvent.getPointerId(paramMotionEvent.getActionIndex());
              log("ACTION_POINTER_UP, count = " + i + ", ActionId = " + j);
              if ((mGestureInProgress) && (i == 2) && (!mInvalidGesture))
              {
                setContext(paramMotionEvent);
                if (mListener != null) {
                  mListener.onScaleEnd(this);
                }
                mInvalidGesture = true;
                continue;
                log("ACTION_UP");
                reset();
                continue;
                log("ACTION_CANCEL");
                reset();
              }
            }
          }
        }
      }
    }
  }
  
  public static abstract interface OnScaleListener
  {
    public abstract boolean onScale(ScaleDetector paramScaleDetector);
    
    public abstract void onScaleEnd(ScaleDetector paramScaleDetector);
    
    public abstract boolean onScaleStart(ScaleDetector paramScaleDetector);
  }
  
  public static abstract class SimpleOnScaleListener
    implements ScaleDetector.OnScaleListener
  {
    private float mMaxTextSize;
    private float mMinTextSize;
    private float mTextSize = 0.0F;
    
    public SimpleOnScaleListener(float paramFloat1, float paramFloat2)
    {
      mMinTextSize = paramFloat1;
      mMaxTextSize = paramFloat2;
    }
    
    public boolean onScale(ScaleDetector paramScaleDetector)
    {
      float f2 = mTextSize * paramScaleDetector.getScaleFactor();
      if ((mTextSize > 0.0F) && (Math.abs(f2 - mTextSize) < 0.2F)) {
        return false;
      }
      float f1;
      if (f2 < mMinTextSize) {
        f1 = mMinTextSize;
      }
      for (;;)
      {
        mTextSize = f1;
        performChangeText(f1);
        return true;
        f1 = f2;
        if (f2 > mMaxTextSize) {
          f1 = mMaxTextSize;
        }
      }
    }
    
    public void onScaleEnd(ScaleDetector paramScaleDetector)
    {
      MessageUtils.setTextSize(mTextSize);
    }
    
    public boolean onScaleStart(ScaleDetector paramScaleDetector)
    {
      return true;
    }
    
    protected abstract void performChangeText(float paramFloat);
    
    public void setTextSize(float paramFloat)
    {
      mTextSize = paramFloat;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ScaleDetector
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */