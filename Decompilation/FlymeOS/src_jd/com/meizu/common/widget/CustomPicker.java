package com.meizu.common.widget;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;

public class CustomPicker
  extends FrameLayout
{
  private int mColumnCount;
  private TextView[] mColumnTextViews;
  private int[] mCurrentItems;
  private int mFocusedPosition = -1;
  private OnCurrentItemChangedListener mOnItemChangedListener;
  private int mOneScreenCount = 0;
  private ScrollTextView[] mPickers = new ScrollTextView[3];
  private String[] mText;
  
  public CustomPicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomPicker(Context paramContext, int paramInt, ColumnData... paramVarArgs)
  {
    this(paramContext, null, 0);
    initPicker(paramInt, paramVarArgs);
  }
  
  public CustomPicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CustomPicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private boolean isDefaultFocusedPosition()
  {
    return mFocusedPosition == -1;
  }
  
  private void setTextPadding(TextView paramTextView, float paramFloat)
  {
    if (isDefaultFocusedPosition())
    {
      paramTextView.setGravity(17);
      return;
    }
    paramTextView.setGravity(48);
    float f = (paramFloat - paramTextView.getTextSize()) / 2.0F;
    paramTextView.setPadding(paramTextView.getPaddingLeft(), (int)(f + mFocusedPosition * paramFloat), paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
  }
  
  public int getCurrentItem(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mColumnCount)) {
      return 0;
    }
    return mCurrentItems[paramInt];
  }
  
  public int[] getCurrentItems()
  {
    return mCurrentItems;
  }
  
  public void initPicker(int paramInt, ColumnData... paramVarArgs)
  {
    if (getChildCount() > 0) {
      removeAllViews();
    }
    int i;
    label193:
    ScrollTextView localScrollTextView;
    TextView localTextView;
    int j;
    ColumnData localColumnData;
    label267:
    Object localObject;
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("columnCount only be 1 or 2 or 3.");
    case 1: 
      inflate(getContext(), R.layout.mc_picker_column_1, this);
      mColumnCount = paramInt;
      mCurrentItems = new int[mColumnCount];
      mColumnTextViews = new TextView[3];
      mText = new String[3];
      mPickers[0] = ((ScrollTextView)findViewById(R.id.mc_scroll1));
      mColumnTextViews[0] = ((TextView)findViewById(R.id.mc_scroll1_text));
      mPickers[1] = ((ScrollTextView)findViewById(R.id.mc_scroll2));
      mColumnTextViews[1] = ((TextView)findViewById(R.id.mc_scroll2_text));
      mPickers[2] = ((ScrollTextView)findViewById(R.id.mc_scroll3));
      mColumnTextViews[2] = ((TextView)findViewById(R.id.mc_scroll3_text));
      paramInt = 0;
      i = 0;
      if (i >= mColumnCount) {
        break label584;
      }
      localScrollTextView = mPickers[i];
      localTextView = mColumnTextViews[i];
      j = paramInt;
      if (localScrollTextView != null)
      {
        j = paramInt;
        if (localTextView != null)
        {
          if ((paramVarArgs == null) || (paramVarArgs.length <= 0) || (paramVarArgs[0] == null)) {
            break label435;
          }
          localColumnData = paramVarArgs[paramInt];
          if (localColumnData != null) {
            break label409;
          }
          paramInt -= 1;
          localColumnData = paramVarArgs[paramInt];
          if (mOneScreenCount < mOneScreenCount) {
            mOneScreenCount = mOneScreenCount;
          }
          if (mDataAdapter == null) {
            break label416;
          }
          localObject = mDataAdapter;
          label303:
          localTextView.setText(mColumnText);
          mText[i] = mColumnText;
          localScrollTextView.setData((ScrollTextView.IDataAdapter)localObject, mLineOffset, mCurrentItem, mCount, mOneScreenCount, mValidStart, mValidEnd, mCycleEnabled);
          j = paramInt;
        }
      }
      break;
    }
    for (;;)
    {
      i += 1;
      paramInt = j;
      break label193;
      inflate(getContext(), R.layout.mc_picker_column_2, this);
      break;
      inflate(getContext(), R.layout.mc_picker_column_3, this);
      break;
      label409:
      paramInt += 1;
      break label267;
      label416:
      localObject = new DataAdapter(i, mStartValue);
      break label303;
      switch (i)
      {
      default: 
        j = paramInt;
        break;
      case 0: 
        localTextView.setText(R.string.mc_hour);
        localScrollTextView.setData(new DataAdapter(0, 0), mCurrentItems[0], 13, 5);
        j = paramInt;
        break;
      case 1: 
        localTextView.setText(R.string.mc_minute);
        localScrollTextView.setData(new DataAdapter(1, 0), mCurrentItems[1], 60, 5);
        j = paramInt;
        break;
      case 2: 
        label435:
        localTextView.setText(R.string.mc_second);
        localScrollTextView.setData(new DataAdapter(2, 0), mCurrentItems[2], 60, 5);
        j = paramInt;
      }
    }
    label584:
    if (!isEnabled()) {
      setEnabled(false);
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    updateCurrentItems(paramParcelable.getCurrentItems());
  }
  
  protected Parcelable onSaveInstanceState()
  {
    return new SavedState(super.onSaveInstanceState(), mCurrentItems, null);
  }
  
  public void setCurrentItem(int paramInt1, int paramInt2)
  {
    if ((paramInt1 >= 0) && (paramInt1 < mColumnCount))
    {
      mCurrentItems[paramInt1] = paramInt2;
      updateCurrentItems(mCurrentItems);
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    int i = 0;
    while (i < mColumnCount)
    {
      mPickers[i].setEnabled(paramBoolean);
      i += 1;
    }
  }
  
  public void setOnCurrentItemChangedListener(OnCurrentItemChangedListener paramOnCurrentItemChangedListener)
  {
    mOnItemChangedListener = paramOnCurrentItemChangedListener;
  }
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 0;
    while (i < mColumnCount)
    {
      mPickers[i].setTextColor(paramInt1, paramInt2);
      mColumnTextViews[i].setTextColor(paramInt3);
      i += 1;
    }
  }
  
  public void updateCurrentItems(int... paramVarArgs)
  {
    if (paramVarArgs == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while ((i < paramVarArgs.length) && (i < mColumnCount))
      {
        mCurrentItems[i] = paramVarArgs[i];
        mPickers[i].refreshCurrent(paramVarArgs[i]);
        i += 1;
      }
    }
  }
  
  public static class ColumnData
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
    
    public ColumnData(float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean, String paramString)
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
    
    public ColumnData(ScrollTextView.IDataAdapter paramIDataAdapter, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean, String paramString)
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
  
  class DataAdapter
    implements ScrollTextView.IDataAdapter
  {
    private int mColumnIndex = 0;
    private int mStartValue;
    
    DataAdapter(int paramInt1, int paramInt2)
    {
      mColumnIndex = paramInt1;
      mStartValue = paramInt2;
    }
    
    public String getItemText(int paramInt)
    {
      return String.valueOf(mStartValue + paramInt);
    }
    
    public void onChanged(View paramView, int paramInt1, int paramInt2)
    {
      mCurrentItems[mColumnIndex] = paramInt2;
      if (mOnItemChangedListener != null) {
        mOnItemChangedListener.onCurrentItemChanged(CustomPicker.this, mCurrentItems);
      }
    }
  }
  
  public static abstract interface OnCurrentItemChangedListener
  {
    public abstract void onCurrentItemChanged(CustomPicker paramCustomPicker, int... paramVarArgs);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new CustomPicker.SavedState.1();
    private final int[] mSaveCurrentItems;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      mSaveCurrentItems = paramParcel.createIntArray();
    }
    
    private SavedState(Parcelable paramParcelable, int... paramVarArgs)
    {
      super();
      mSaveCurrentItems = ((int[])paramVarArgs.clone());
    }
    
    public int[] getCurrentItems()
    {
      return mSaveCurrentItems;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeIntArray(mSaveCurrentItems);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomPicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */