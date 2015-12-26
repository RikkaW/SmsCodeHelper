package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.R.styleable;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.List;

public class TimePicker
  extends FrameLayout
{
  private ScrollTextView mAmPmPicker;
  private final String mAmText;
  private int mCurrentHour = 0;
  private int mCurrentMinute = 0;
  private ScrollTextView mHourPicker;
  private TextView mHourUnit;
  private Boolean mIs24HourView = Boolean.valueOf(false);
  private boolean mIsAccessibilityEnable = false;
  private boolean mIsAm = true;
  private boolean mIsDrawLine;
  private int mLineOneHeight;
  private Paint mLinePaint;
  private int mLineTwoHeight;
  private ScrollTextView mMinutePicker;
  private TextView mMinuteUnit;
  private OnTimeChangedListener mOnTimeChangedListener;
  private LinearLayout mPickerHolder;
  private final String mPmText;
  private int mWidthPadding;
  
  public TimePicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TimePicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public TimePicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = Calendar.getInstance();
    try
    {
      mCurrentHour = paramAttributeSet.get(11);
      mCurrentMinute = paramAttributeSet.get(12);
      mIs24HourView = Boolean.valueOf(DateFormat.is24HourFormat(paramContext));
      if ((!mIs24HourView.booleanValue()) && (mCurrentHour >= 12))
      {
        mCurrentHour -= 12;
        mIsAm = false;
      }
      paramAttributeSet = new DateFormatSymbols().getAmPmStrings();
      mAmText = paramAttributeSet[0];
      mPmText = paramAttributeSet[1];
      inflateLayout();
      mLineOneHeight = 0;
      mLineTwoHeight = 0;
      mWidthPadding = paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_line_width_padding);
      mLinePaint = new Paint();
      paramAttributeSet = paramContext.obtainStyledAttributes(R.styleable.MZTheme);
      paramInt = paramAttributeSet.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_gregorian_color));
      paramAttributeSet.recycle();
      mLinePaint.setColor(paramInt);
      mLinePaint.setAntiAlias(true);
      mLinePaint.setStrokeWidth(paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_line_stroke_width));
      mIsDrawLine = false;
      setWillNotDraw(false);
      mPickerHolder = ((LinearLayout)findViewById(R.id.mc_column_parent));
      paramContext = (AccessibilityManager)paramContext.getSystemService("accessibility");
      if (paramContext != null) {
        mIsAccessibilityEnable = paramContext.isEnabled();
      }
      if (mIsAccessibilityEnable) {
        setFocusable(true);
      }
      sendAccessibilityEvent();
      return;
    }
    catch (Exception paramAttributeSet)
    {
      for (;;)
      {
        mCurrentHour = 12;
        mCurrentMinute = 30;
        mIs24HourView = Boolean.valueOf(true);
      }
    }
  }
  
  private String getTimeText(int paramInt)
  {
    switch (paramInt)
    {
    }
    do
    {
      return "";
      int i = mCurrentHour;
      if (is24HourView()) {
        return String.valueOf(i);
      }
      paramInt = i;
      if (i == 0) {
        paramInt = 12;
      }
      return String.valueOf(paramInt);
      return String.valueOf(mCurrentMinute);
      if (mIsAm) {}
      for (paramInt = 0; paramInt == 0; paramInt = 1) {
        return mAmText;
      }
    } while (paramInt != 1);
    return mPmText;
  }
  
  private void inflateLayout()
  {
    if (getChildCount() > 0) {
      removeAllViews();
    }
    if (mIs24HourView.booleanValue()) {
      init24HourView();
    }
    for (;;)
    {
      if (!isEnabled()) {
        setEnabled(false);
      }
      return;
      init12HourView();
    }
  }
  
  private void init12HourView()
  {
    if (mIs24HourView.booleanValue()) {
      return;
    }
    inflate(getContext(), R.layout.mc_time_picker_column_12, this);
    mHourUnit = ((TextView)findViewById(R.id.mc_scroll1_text));
    if (mHourUnit != null) {
      mHourUnit.setText(R.string.mc_date_time_hour);
    }
    mMinuteUnit = ((TextView)findViewById(R.id.mc_scroll2_text));
    if (mMinuteUnit != null) {
      mMinuteUnit.setText(R.string.mc_date_time_min);
    }
    mHourPicker = ((ScrollTextView)findViewById(R.id.mc_scroll1));
    mHourPicker.setData(new TimeAdapter(1), -1.0F, mCurrentHour, 12, 3, 0, 11, true);
    mMinutePicker = ((ScrollTextView)findViewById(R.id.mc_scroll2));
    mMinutePicker.setData(new TimeAdapter(2), -1.0F, mCurrentMinute, 60, 3, 0, 59, true);
    mAmPmPicker = ((ScrollTextView)findViewById(R.id.mc_scroll3));
    ScrollTextView localScrollTextView = mAmPmPicker;
    TimeAdapter localTimeAdapter = new TimeAdapter(3);
    if (mIsAm) {}
    for (int i = 0;; i = 1)
    {
      localScrollTextView.setData(localTimeAdapter, -1.0F, i, 2, 3, 0, 1, false);
      mAmPmPicker.setTextSize(getContext().getResources().getDimension(R.dimen.mz_picker_selected_ampm_size), getContext().getResources().getDimension(R.dimen.mz_picker_unselected_ampm_size));
      return;
    }
  }
  
  private void init24HourView()
  {
    if (!mIs24HourView.booleanValue()) {
      return;
    }
    inflate(getContext(), R.layout.mc_time_picker_column_24, this);
    mHourUnit = ((TextView)findViewById(R.id.mc_scroll1_text));
    if (mHourUnit != null) {
      mHourUnit.setText(R.string.mc_date_time_hour);
    }
    mMinuteUnit = ((TextView)findViewById(R.id.mc_scroll2_text));
    if (mMinuteUnit != null) {
      mMinuteUnit.setText(R.string.mc_date_time_min);
    }
    mHourPicker = ((ScrollTextView)findViewById(R.id.mc_scroll1));
    mHourPicker.setData(new TimeAdapter(1), -1.0F, mCurrentHour, 24, 3, 0, 23, true);
    mMinutePicker = ((ScrollTextView)findViewById(R.id.mc_scroll2));
    mMinutePicker.setData(new TimeAdapter(2), -1.0F, mCurrentMinute, 60, 3, 0, 59, true);
    mAmPmPicker = null;
  }
  
  private void sendAccessibilityEvent()
  {
    if (mIsAccessibilityEnable)
    {
      String str = "";
      if (!mIs24HourView.booleanValue()) {
        str = "" + getTimeText(3);
      }
      setContentDescription(str + getTimeText(1) + mHourUnit.getText() + getTimeText(2) + mMinuteUnit.getText());
      sendAccessibilityEvent(4);
    }
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if ((mIsAccessibilityEnable) && (paramAccessibilityEvent.getEventType() == 32))
    {
      String str = "";
      if (!mIs24HourView.booleanValue()) {
        str = "" + getTimeText(3);
      }
      str = str + getTimeText(1) + mHourUnit.getText() + getTimeText(2) + mMinuteUnit.getText();
      paramAccessibilityEvent.getText().add(str);
      return false;
    }
    return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
  }
  
  public int getCurrentHour()
  {
    if (mIs24HourView.booleanValue()) {
      return mCurrentHour;
    }
    if (mIsAm) {
      return mCurrentHour;
    }
    return mCurrentHour + 12;
  }
  
  public Integer getCurrentMinute()
  {
    return Integer.valueOf(mCurrentMinute);
  }
  
  public boolean is24HourView()
  {
    return mIs24HourView.booleanValue();
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (mIsDrawLine)
    {
      int j = getWidth();
      int i = mPickerHolder.getWidth() - mWidthPadding * 2;
      j = (j - i) / 2;
      paramCanvas.drawLine(j, mLineOneHeight, j + i, mLineOneHeight, mLinePaint);
      paramCanvas.drawLine(j, mLineTwoHeight, j + i, mLineTwoHeight, mLinePaint);
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    update(mHour, mMinute, mIs24HourView.booleanValue());
  }
  
  protected Parcelable onSaveInstanceState()
  {
    return new SavedState(super.onSaveInstanceState(), getCurrentHour(), mCurrentMinute, null);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (!paramBoolean) {}
    do
    {
      return;
      paramBoolean = mIs24HourView.booleanValue();
      try
      {
        boolean bool = DateFormat.is24HourFormat(getContext());
        paramBoolean = bool;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
        }
      }
    } while (paramBoolean == mIs24HourView.booleanValue());
    setIs24HourView(Boolean.valueOf(paramBoolean));
  }
  
  public void setCurrentHour(Integer paramInteger)
  {
    if ((paramInteger == null) || (paramInteger.intValue() == getCurrentHour())) {
      return;
    }
    update(paramInteger.intValue(), mCurrentMinute, mIs24HourView.booleanValue());
  }
  
  public void setCurrentMinute(Integer paramInteger)
  {
    update(getCurrentHour(), paramInteger.intValue(), mIs24HourView.booleanValue());
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    mMinutePicker.setEnabled(paramBoolean);
    mHourPicker.setEnabled(paramBoolean);
    if (mAmPmPicker != null) {
      mAmPmPicker.setEnabled(paramBoolean);
    }
  }
  
  public void setIs24HourView(Boolean paramBoolean)
  {
    update(getCurrentHour(), mCurrentMinute, paramBoolean.booleanValue());
  }
  
  public void setIsDrawLine(boolean paramBoolean)
  {
    mIsDrawLine = paramBoolean;
  }
  
  public void setLineHeight(int paramInt1, int paramInt2)
  {
    mLineOneHeight = paramInt1;
    mLineTwoHeight = paramInt2;
  }
  
  public void setOnTimeChangedListener(OnTimeChangedListener paramOnTimeChangedListener)
  {
    mOnTimeChangedListener = paramOnTimeChangedListener;
  }
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    mHourPicker.setTextColor(paramInt1, paramInt2);
    mMinutePicker.setTextColor(paramInt1, paramInt2);
    if (mAmPmPicker != null) {
      mAmPmPicker.setTextColor(paramInt1, paramInt2);
    }
    mHourUnit.setTextColor(paramInt3);
    mMinuteUnit.setTextColor(paramInt3);
  }
  
  public void update(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = 0;
    if (paramBoolean)
    {
      if (mCurrentHour == paramInt1) {
        break label191;
      }
      mCurrentHour = paramInt1;
      paramInt1 = 1;
    }
    for (;;)
    {
      if (mCurrentMinute != paramInt2)
      {
        mCurrentMinute = paramInt2;
        paramInt2 = 1;
        label37:
        if (paramBoolean != mIs24HourView.booleanValue())
        {
          mIs24HourView = Boolean.valueOf(paramBoolean);
          inflateLayout();
        }
        if (paramInt1 != 0) {
          mHourPicker.refreshCurrent(mCurrentHour);
        }
        if (paramInt2 != 0) {
          mMinutePicker.refreshCurrent(mCurrentMinute);
        }
        if (mAmPmPicker != null)
        {
          ScrollTextView localScrollTextView = mAmPmPicker;
          if (mIsAm)
          {
            paramInt1 = j;
            label113:
            localScrollTextView.refreshCurrent(paramInt1);
          }
        }
        else
        {
          return;
          mIsAm = true;
          if (mCurrentHour == paramInt1) {
            break label185;
          }
          mCurrentHour = paramInt1;
        }
      }
      label185:
      for (int i = 1;; i = 0)
      {
        paramInt1 = i;
        if (mCurrentHour < 12) {
          break;
        }
        mCurrentHour -= 12;
        mIsAm = false;
        paramInt1 = i;
        break;
        paramInt1 = 1;
        break label113;
        paramInt2 = 0;
        break label37;
      }
      label191:
      paramInt1 = 0;
    }
  }
  
  public static abstract interface OnTimeChangedListener
  {
    public abstract void onTimeChanged(TimePicker paramTimePicker, int paramInt1, int paramInt2);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new TimePicker.SavedState.1();
    private final int mHour;
    private final int mMinute;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      mHour = paramParcel.readInt();
      mMinute = paramParcel.readInt();
    }
    
    private SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2)
    {
      super();
      mHour = paramInt1;
      mMinute = paramInt2;
    }
    
    public int getHour()
    {
      return mHour;
    }
    
    public int getMinute()
    {
      return mMinute;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(mHour);
      paramParcel.writeInt(mMinute);
    }
  }
  
  class TimeAdapter
    implements ScrollTextView.IDataAdapter
  {
    static final int SET_AMPM = 3;
    static final int SET_HOUR = 1;
    static final int SET_MIN = 2;
    int mType = 0;
    
    TimeAdapter(int paramInt)
    {
      mType = paramInt;
    }
    
    public String getItemText(int paramInt)
    {
      switch (mType)
      {
      }
      do
      {
        return null;
        if (is24HourView()) {
          return String.valueOf(paramInt);
        }
        int i = paramInt;
        if (paramInt == 0) {
          i = 12;
        }
        return String.valueOf(i);
        return String.valueOf(paramInt);
        if (paramInt == 0) {
          return mAmText;
        }
      } while (paramInt != 1);
      return mPmText;
    }
    
    public void onChanged(View paramView, int paramInt1, int paramInt2)
    {
      switch (mType)
      {
      default: 
        return;
      case 1: 
        TimePicker.access$002(TimePicker.this, paramInt2);
      case 2: 
        for (;;)
        {
          if (mOnTimeChangedListener != null) {
            mOnTimeChangedListener.onTimeChanged(TimePicker.this, getCurrentHour(), getCurrentMinute().intValue());
          }
          TimePicker.this.sendAccessibilityEvent();
          return;
          TimePicker.access$102(TimePicker.this, paramInt2);
        }
      }
      paramView = TimePicker.this;
      if (paramInt2 == 0) {}
      for (boolean bool = true;; bool = false)
      {
        TimePicker.access$202(paramView, bool);
        break;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.TimePicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */