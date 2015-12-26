package com.meizu.common.widget;

public class CustomPicker$ColumnData
{
  String mColumnText;
  int mCount;
  int mCurrentItem;
  boolean mCycleEnabled;
  ScrollTextView.IDataAdapter mDataAdapter;
  float mLineOffset;
  int mOneScreenCount;
  int mStartValue;
  int mValidEnd;
  int mValidStart;
  
  public CustomPicker$ColumnData(float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, String paramString)
  {
    mDataAdapter = null;
    mLineOffset = paramFloat;
    mCurrentItem = paramInt1;
    mCount = paramInt2;
    mOneScreenCount = paramInt3;
    mValidStart = paramInt4;
    mValidEnd = paramInt5;
    mStartValue = paramInt6;
    mCycleEnabled = paramBoolean;
    mColumnText = paramString;
  }
  
  public CustomPicker$ColumnData(ScrollTextView.IDataAdapter paramIDataAdapter, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, String paramString)
  {
    mDataAdapter = paramIDataAdapter;
    mLineOffset = paramFloat;
    mCurrentItem = paramInt1;
    mCount = paramInt2;
    mOneScreenCount = paramInt3;
    mValidStart = paramInt4;
    mValidEnd = paramInt5;
    mStartValue = 0;
    mCycleEnabled = paramBoolean;
    mColumnText = paramString;
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomPicker.ColumnData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */