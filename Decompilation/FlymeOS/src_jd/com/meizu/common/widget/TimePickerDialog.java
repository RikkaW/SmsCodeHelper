package com.meizu.common.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.R.styleable;

public class TimePickerDialog
  extends AlertDialog
  implements DialogInterface.OnClickListener, TimePicker.OnTimeChangedListener
{
  private static final String HOUR = "hour";
  private static final String IS_24_HOUR = "is24hour";
  private static final String MINUTE = "minute";
  private final OnTimeSetListener mCallback;
  int mInitialHourOfDay;
  int mInitialMinute;
  boolean mIs24HourView;
  private final TimePicker mTimePicker;
  
  public TimePickerDialog(Context paramContext, int paramInt1, OnTimeSetListener paramOnTimeSetListener, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    super(paramContext, paramInt1);
    mCallback = paramOnTimeSetListener;
    mInitialHourOfDay = paramInt2;
    mInitialMinute = paramInt3;
    mIs24HourView = paramBoolean;
    setButton(-1, paramContext.getText(R.string.mc_yes), this);
    setButton(-2, paramContext.getText(17039360), (DialogInterface.OnClickListener)null);
    paramOnTimeSetListener = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.mc_time_picker_dialog, null);
    setView(paramOnTimeSetListener);
    mTimePicker = ((TimePicker)paramOnTimeSetListener.findViewById(R.id.timePicker));
    mTimePicker.setIs24HourView(Boolean.valueOf(mIs24HourView));
    mTimePicker.setCurrentHour(Integer.valueOf(mInitialHourOfDay));
    mTimePicker.setCurrentMinute(Integer.valueOf(mInitialMinute));
    mTimePicker.setOnTimeChangedListener(this);
    paramOnTimeSetListener = paramContext.obtainStyledAttributes(R.styleable.MZTheme);
    paramInt1 = paramOnTimeSetListener.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_gregorian_color));
    paramOnTimeSetListener.recycle();
    setTextColor(paramInt1, paramContext.getResources().getColor(R.color.mc_custom_date_picker_unselected_color), paramInt1);
    mTimePicker.setIsDrawLine(true);
    mTimePicker.setLineHeight(paramContext.getResources().getDimensionPixelSize(R.dimen.mc_time_picker_line_one_height), paramContext.getResources().getDimensionPixelSize(R.dimen.mc_time_picker_line_two_height));
    setOnShowListener(new TimePickerDialog.1(this, paramInt1));
  }
  
  public TimePickerDialog(Context paramContext, OnTimeSetListener paramOnTimeSetListener, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this(paramContext, 0, paramOnTimeSetListener, paramInt1, paramInt2, paramBoolean);
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (mCallback != null)
    {
      mTimePicker.clearFocus();
      mCallback.onTimeSet(mTimePicker, mTimePicker.getCurrentHour(), mTimePicker.getCurrentMinute().intValue());
    }
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    int i = paramBundle.getInt("hour");
    int j = paramBundle.getInt("minute");
    mTimePicker.setIs24HourView(Boolean.valueOf(paramBundle.getBoolean("is24hour")));
    mTimePicker.setCurrentHour(Integer.valueOf(i));
    mTimePicker.setCurrentMinute(Integer.valueOf(j));
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = super.onSaveInstanceState();
    localBundle.putInt("hour", mTimePicker.getCurrentHour());
    localBundle.putInt("minute", mTimePicker.getCurrentMinute().intValue());
    localBundle.putBoolean("is24hour", mTimePicker.is24HourView());
    return localBundle;
  }
  
  public void onTimeChanged(TimePicker paramTimePicker, int paramInt1, int paramInt2) {}
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    mTimePicker.setTextColor(paramInt1, paramInt2, paramInt3);
  }
  
  public void updateTime(int paramInt1, int paramInt2)
  {
    mTimePicker.setCurrentHour(Integer.valueOf(paramInt1));
    mTimePicker.setCurrentMinute(Integer.valueOf(paramInt2));
  }
  
  public static abstract interface OnTimeSetListener
  {
    public abstract void onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.TimePickerDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */