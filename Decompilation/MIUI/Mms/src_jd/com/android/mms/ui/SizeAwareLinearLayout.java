package com.android.mms.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SizeAwareLinearLayout
  extends LinearLayout
{
  private boolean isInListener = false;
  private OnMeasureListener mOnMeasureListener = null;
  
  public SizeAwareLinearLayout(Context paramContext)
  {
    super(paramContext);
  }
  
  public SizeAwareLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((!isInListener) && (mOnMeasureListener != null))
    {
      isInListener = true;
      mOnMeasureListener.onPostLayout();
      isInListener = false;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if ((!isInListener) && (mOnMeasureListener != null))
    {
      isInListener = true;
      mOnMeasureListener.onPreMeasure(this, paramInt1, paramInt2);
      isInListener = false;
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public void setOnMeasureListener(OnMeasureListener paramOnMeasureListener)
  {
    mOnMeasureListener = paramOnMeasureListener;
  }
  
  static abstract interface OnMeasureListener
  {
    public abstract void onPostLayout();
    
    public abstract void onPreMeasure(SizeAwareLinearLayout paramSizeAwareLinearLayout, int paramInt1, int paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SizeAwareLinearLayout
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */