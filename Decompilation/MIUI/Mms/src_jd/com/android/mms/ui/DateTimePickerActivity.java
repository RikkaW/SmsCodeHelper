package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import java.util.Calendar;
import miui.R.layout;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.widget.DatePicker;
import miui.widget.DatePicker.OnDateChangedListener;
import miui.widget.TimePicker;
import miui.widget.TimePicker.OnTimeChangedListener;

public class DateTimePickerActivity
  extends Activity
  implements View.OnClickListener, DatePicker.OnDateChangedListener, TimePicker.OnTimeChangedListener
{
  public static String CONTENT_TYPE = "vnd.android.cursor.item/datetime";
  public static String FIELD_TIME = "time";
  public static String FIELD_TITLE = "title";
  private int mActionBarCustomPaddingH;
  private int mActionBarCustomPaddingV;
  private Calendar mCalendar;
  private Button mCancelButton;
  private RadioButton mDateButton;
  private DatePicker mDatePicker;
  private RadioGroup mDateTimeGroup;
  private boolean mIs24HourFormat = false;
  private boolean mIsDate = true;
  private Button mOkButton;
  private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener()
  {
    public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
    {
      if (paramAnonymousRadioGroup.getCheckedRadioButtonId() == 2131820610)
      {
        DateTimePickerActivity.access$002(DateTimePickerActivity.this, true);
        DateTimePickerActivity.this.setView(mIsDate);
        return;
      }
      DateTimePickerActivity.access$002(DateTimePickerActivity.this, false);
      DateTimePickerActivity.this.setView(mIsDate);
    }
  };
  private RadioButton mTimeButton;
  private TimePicker mTimePicker;
  private String mTitle;
  private TextView mTitleText;
  
  private void configureTitleView()
  {
    Object localObject = getActionBar();
    ((ActionBar)localObject).setCustomView(R.layout.edit_mode_title);
    localObject = ((ActionBar)localObject).getCustomView();
    ((View)localObject).setBackgroundDrawable(null);
    ((View)localObject).setPaddingRelative(mActionBarCustomPaddingH, mActionBarCustomPaddingV, mActionBarCustomPaddingH, 0);
    mTitleText = ((TextView)((View)localObject).findViewById(16908310));
    mCancelButton = ((Button)((View)localObject).findViewById(16908313));
    mCancelButton.setText(17039360);
    mOkButton = ((Button)((View)localObject).findViewById(16908314));
    mOkButton.setText(17039370);
    mOkButton.setOnClickListener(this);
    mCancelButton.setOnClickListener(this);
    mTitleText.setText(mTitle);
  }
  
  private void formatDateAndShow()
  {
    long l = mCalendar.getTimeInMillis();
    String str1 = DateUtils.formatDateTime(this, l, 20);
    String str2 = DateUtils.formatDateTime(this, l, 2);
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append(str1);
    if (!TextUtils.isEmpty(str2))
    {
      localStringBuffer.append("      ");
      localStringBuffer.append(str2);
    }
    mDateButton.setText(localStringBuffer.toString());
  }
  
  private void formatTimeAndShow()
  {
    long l = mCalendar.getTimeInMillis();
    if (mIs24HourFormat) {}
    for (int i = 128;; i = 64)
    {
      String str = DateUtils.formatDateTime(this, l, i | 0x1);
      mTimeButton.setText(str);
      return;
    }
  }
  
  private void initLayout()
  {
    setContentView(2130968601);
    mTimePicker = ((TimePicker)findViewById(2131820613));
    mTimePicker.setIs24HourView(Boolean.valueOf(mIs24HourFormat));
    mDatePicker = ((DatePicker)findViewById(2131820612));
    mDateTimeGroup = ((RadioGroup)findViewById(2131820609));
    mDateTimeGroup.setOnCheckedChangeListener(mOnCheckedChangeListener);
    mTimeButton = ((RadioButton)findViewById(2131820611));
    mDateButton = ((RadioButton)findViewById(2131820610));
    setView(mIsDate);
    initPickers();
    formatDateAndShow();
    formatTimeAndShow();
  }
  
  private void initPickers()
  {
    int i = mCalendar.get(1);
    int j = mCalendar.get(2);
    int k = mCalendar.get(5);
    int m = mCalendar.get(11);
    int n = mCalendar.get(12);
    mDatePicker.init(i, j, k, this);
    long l = Math.min(mCalendar.getTimeInMillis(), System.currentTimeMillis()) / 86400000L * 86400000L;
    mDatePicker.setMinDate(l);
    mDatePicker.setMaxDate(l + 315360000000L);
    mTimePicker.setCurrentHour(Integer.valueOf(m));
    mTimePicker.setCurrentMinute(Integer.valueOf(n));
    mTimePicker.setOnTimeChangedListener(this);
  }
  
  private void setView(boolean paramBoolean)
  {
    int j = 0;
    Object localObject = mTimePicker;
    if (paramBoolean)
    {
      i = 8;
      ((TimePicker)localObject).setVisibility(i);
      localObject = mDatePicker;
      if (!paramBoolean) {
        break label45;
      }
    }
    label45:
    for (int i = j;; i = 8)
    {
      ((DatePicker)localObject).setVisibility(i);
      return;
      i = 0;
      break;
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      return;
    case 16908314: 
      if (mCalendar.getTimeInMillis() < System.currentTimeMillis())
      {
        new AlertDialog.Builder(this).setTitle(2131362153).setPositiveButton(17039370, null).setCancelable(true).show();
        return;
      }
      mCalendar.set(13, 0);
      mCalendar.set(14, 0);
      paramView = new Intent();
      paramView.putExtra(FIELD_TIME, mCalendar.getTimeInMillis());
      setResult(-1, paramView);
      finish();
      return;
    }
    paramView = new Intent();
    paramView.putExtra(FIELD_TIME, mCalendar.getTimeInMillis());
    setResult(0, paramView);
    finish();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    mCalendar = Calendar.getInstance();
    paramBundle = getIntent();
    long l = paramBundle.getLongExtra(FIELD_TIME, System.currentTimeMillis());
    mCalendar.setTimeInMillis(l);
    mTitle = paramBundle.getStringExtra(FIELD_TITLE);
    mIs24HourFormat = DateFormat.is24HourFormat(this);
    mActionBarCustomPaddingH = getResources().getDimensionPixelSize(2131427427);
    mActionBarCustomPaddingV = getResources().getDimensionPixelSize(2131427428);
    configureTitleView();
    initLayout();
  }
  
  public void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    mCalendar.set(1, paramInt1);
    mCalendar.set(2, paramInt2);
    mCalendar.set(5, paramInt3);
    formatDateAndShow();
  }
  
  public void onTimeChanged(TimePicker paramTimePicker, int paramInt1, int paramInt2)
  {
    mCalendar.set(11, paramInt1);
    mCalendar.set(12, paramInt2);
    formatTimeAndShow();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.DateTimePickerActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */