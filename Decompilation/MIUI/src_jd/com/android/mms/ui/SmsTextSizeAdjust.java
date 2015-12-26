package com.android.mms.ui;

import android.app.Activity;
import android.app.Application;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.view.MotionEvent;
import com.android.mms.MmsApp;
import miui.R.dimen;
import miui.os.Build;

public class SmsTextSizeAdjust
  extends ContextWrapper
{
  private static SmsTextSizeAdjust mInstance;
  private Activity mActivity;
  private float mDefaultTextSize;
  private ISmsTextSizeAdjustHost mHost;
  private ScaleDetector.SimpleOnScaleListener mListener;
  private int mPreviousScaleMode;
  private ScaleDetector mScaleDetector;
  private float mTextSize;
  
  private SmsTextSizeAdjust()
  {
    super(MmsApp.getApp().getApplicationContext());
    if (Build.IS_CM_CUSTOMIZATION)
    {
      mListener = new SmsOnScaleListener(getResources().getDimension(2131427389), getResources().getDimension(2131427390));
      mScaleDetector = new ScaleDetector(mListener);
    }
    mPreviousScaleMode = MiuiConfiguration.getScaleMode();
    mDefaultTextSize = getResources().getDimension(2131427413);
    setTextSizeBySysFS();
  }
  
  public static void clear(ISmsTextSizeAdjustHost paramISmsTextSizeAdjustHost)
  {
    if ((mInstance != null) && (mInstancemHost == paramISmsTextSizeAdjustHost)) {
      mInstance.unInit();
    }
  }
  
  public static SmsTextSizeAdjust getInstance()
  {
    if (mInstance == null) {
      mInstance = new SmsTextSizeAdjust();
    }
    return mInstance;
  }
  
  public static boolean hasInstance()
  {
    return mInstance != null;
  }
  
  private void setTextSizeBySysFS()
  {
    if (MiuiConfiguration.getScaleMode() == 11)
    {
      MessageUtils.setTextSize(Float.MAX_VALUE);
      return;
    }
    if (MiuiConfiguration.getScaleMode() != 1)
    {
      MessageUtils.setTextSize(MmsApp.getApp().getResources().getDimension(R.dimen.normal_text_size));
      return;
    }
    float f2 = getResourcesgetConfigurationfontScale;
    float f1 = f2;
    if (f2 == 0.0F) {
      f1 = 1.0F;
    }
    MessageUtils.setTextSize(mDefaultTextSize * f1);
  }
  
  private void unInit()
  {
    mHost = null;
    mActivity = null;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((mScaleDetector != null) && (mActivity != null)) {
      return mScaleDetector.onTouchEvent(mActivity, paramMotionEvent);
    }
    return false;
  }
  
  public float getTextSize()
  {
    return mTextSize;
  }
  
  public void init(ISmsTextSizeAdjustHost paramISmsTextSizeAdjustHost, Activity paramActivity)
  {
    mHost = paramISmsTextSizeAdjustHost;
    mActivity = paramActivity;
    mTextSize = 0.0F;
    if (mListener != null) {
      mListener.setTextSize(mTextSize);
    }
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    int i = MiuiConfiguration.getScaleMode();
    if (i == mPreviousScaleMode) {
      return;
    }
    mPreviousScaleMode = i;
    setTextSizeBySysFS();
  }
  
  public void refresh()
  {
    float f = MessageUtils.getTextSize(mDefaultTextSize);
    if (mTextSize != f)
    {
      mTextSize = f;
      if (mListener != null) {
        mListener.setTextSize(mTextSize);
      }
      if (mHost != null) {
        mHost.setTextSize(mTextSize);
      }
    }
  }
  
  public class SmsOnScaleListener
    extends ScaleDetector.SimpleOnScaleListener
  {
    public SmsOnScaleListener(float paramFloat1, float paramFloat2)
    {
      super(paramFloat2);
    }
    
    protected void performChangeText(float paramFloat)
    {
      if (mTextSize != paramFloat)
      {
        SmsTextSizeAdjust.access$002(SmsTextSizeAdjust.this, paramFloat);
        if (mHost != null) {
          mHost.setTextSize(paramFloat);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SmsTextSizeAdjust
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */