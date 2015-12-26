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
import java.util.Calendar;

public class DatePickerDialog
  extends AlertDialog
  implements DialogInterface.OnClickListener, DatePicker.OnDateChangedListener
{
  private static final String DAY = "day";
  private static final String MONTH = "month";
  private static final String YEAR = "year";
  private final OnDateSetListener mCallBack;
  private final DatePicker mDatePicker;
  
  public DatePickerDialog(Context paramContext, int paramInt1, OnDateSetListener paramOnDateSetListener, int paramInt2, int paramInt3, int paramInt4)
  {
    this(paramContext, paramInt1, paramOnDateSetListener, paramInt2, paramInt3, paramInt4, false, false);
  }
  
  public DatePickerDialog(Context paramContext, int paramInt1, OnDateSetListener paramOnDateSetListener, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2)
  {
    super(paramContext, paramInt1);
    mCallBack = paramOnDateSetListener;
    setButton(-1, paramContext.getText(R.string.mc_yes), this);
    setButton(-2, paramContext.getText(17039360), (DialogInterface.OnClickListener)null);
    paramOnDateSetListener = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.mc_date_picker_dialog, null);
    setView(paramOnDateSetListener);
    mDatePicker = ((DatePicker)paramOnDateSetListener.findViewById(R.id.datePicker));
    mDatePicker.init(paramInt2, paramInt3, paramInt4, this, paramBoolean1, paramBoolean2);
    paramOnDateSetListener = paramContext.obtainStyledAttributes(R.styleable.MZTheme);
    paramInt1 = paramOnDateSetListener.getInt(R.styleable.MZTheme_mzThemeColor, paramContext.getResources().getColor(R.color.mc_custom_date_picker_selected_gregorian_color));
    paramOnDateSetListener.recycle();
    setTextColor(paramInt1, paramContext.getResources().getColor(R.color.mc_custom_date_picker_unselected_color), paramInt1);
    mDatePicker.setIsDrawLine(true);
    mDatePicker.setLineHeight(paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_line_one_height), paramContext.getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_line_two_height));
    setOnShowListener(new DatePickerDialog.1(this, paramInt1));
  }
  
  public DatePickerDialog(Context paramContext, OnDateSetListener paramOnDateSetListener, int paramInt1, int paramInt2, int paramInt3)
  {
    this(paramContext, 0, paramOnDateSetListener, paramInt1, paramInt2, paramInt3);
  }
  
  public DatePicker getDatePicker()
  {
    return mDatePicker;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (mCallBack != null)
    {
      mDatePicker.clearFocus();
      mCallBack.onDateSet(mDatePicker, mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
    }
  }
  
  public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
  {
    mDatePicker.init(paramInt1, paramInt2, paramInt3, null, true);
  }
  
  public void onRestoreInstanceState(Bundle paramBundle)
  {
    super.onRestoreInstanceState(paramBundle);
    int i = paramBundle.getInt("year");
    int j = paramBundle.getInt("month");
    int k = paramBundle.getInt("day");
    mDatePicker.init(i, j, k, this, false);
  }
  
  public Bundle onSaveInstanceState()
  {
    Bundle localBundle = super.onSaveInstanceState();
    localBundle.putInt("year", mDatePicker.getYear());
    localBundle.putInt("month", mDatePicker.getMonth());
    localBundle.putInt("day", mDatePicker.getDayOfMonth());
    return localBundle;
  }
  
  public void setMaxYear(int paramInt)
  {
    int i = paramInt;
    if (paramInt > 2099) {
      i = 2099;
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(i, 1, 1);
    mDatePicker.setMaxDate(localCalendar.getTimeInMillis());
  }
  
  public void setMinYear(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1901) {
      i = 1901;
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(i, 1, 1);
    mDatePicker.setMinDate(localCalendar.getTimeInMillis());
  }
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    mDatePicker.setTextColor(paramInt1, paramInt2, paramInt3);
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3)
  {
    updateDate(paramInt1, paramInt2, paramInt3, true);
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    mDatePicker.updateDate(paramInt1, paramInt2, paramInt3, paramBoolean);
  }
  
  public static abstract interface OnDateSetListener
  {
    public abstract void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.DatePickerDialog
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */