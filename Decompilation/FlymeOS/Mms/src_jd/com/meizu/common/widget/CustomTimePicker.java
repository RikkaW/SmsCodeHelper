package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.BaseSavedState;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.R.array;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.util.LunarCalendar;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class CustomTimePicker
  extends FrameLayout
{
  private static final int NUMBER_OF_MONTHS = 12;
  private static final String TAG = "CustomTimePicker";
  private boolean isLeapMonth = false;
  private boolean isLunar = false;
  private ScrollTextView mAmPmPicker;
  private final String mAmText;
  private final Calendar mCalendar = Calendar.getInstance();
  private int mCurrentHour = 0;
  private int mCurrentMinute = 0;
  private int mDay;
  private DayPicker mDayPicker;
  private TextView mDayUnit;
  private int mGregorianColor;
  private ScrollTextView mHourPicker;
  private TextView mHourUnit;
  private Boolean mIs24HourView = Boolean.valueOf(false);
  private boolean mIsAm = true;
  private int mLunarColor;
  private int mLunarMonthCount;
  private int mLunarNormalTextSize;
  private int mLunarSelectTextSize;
  private ScrollTextView mMinutePicker;
  private TextView mMinuteUnit;
  private int mMonth;
  private volatile Locale mMonthLocale;
  private int mMonthOfDays;
  private MonthPicker mMonthPicker;
  private TextView mMonthUnit;
  private Object mMonthUpdateLock = new Object();
  private int mOneScreenCount = 5;
  private FrameLayout mPickerHolder;
  private final String mPmText;
  private String[] mShortMonths;
  private int mSolarNormalTextSize;
  private int mSolarSelectTextSize;
  private int mUnSlectColor;
  private int mYear;
  
  public CustomTimePicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public CustomTimePicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public CustomTimePicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    try
    {
      mCurrentHour = mCalendar.get(11);
      mCurrentMinute = mCalendar.get(12);
      mIs24HourView = Boolean.valueOf(DateFormat.is24HourFormat(paramContext));
      if ((!mIs24HourView.booleanValue()) && (mCurrentHour >= 12))
      {
        mCurrentHour -= 12;
        mIsAm = false;
      }
      paramAttributeSet = new DateFormatSymbols().getAmPmStrings();
      mAmText = paramAttributeSet[0];
      mPmText = paramAttributeSet[1];
      mLunarNormalTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_date_picker_normal_lunar_size);
      mLunarSelectTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_date_picker_selected_lunar_size);
      mSolarNormalTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_picker_normal_number_size);
      mSolarSelectTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_picker_selected_number_size);
      inflateLayout();
      setBackgroundColor(-1);
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
  
  private void doTabAnimate()
  {
    int i;
    if (isLunar)
    {
      i = mGregorianColor;
      if (isLunar) {
        break label47;
      }
    }
    label47:
    for (boolean bool = true;; bool = false)
    {
      setTabColor(i, bool, true);
      setTextColor(i, mUnSlectColor, i);
      return;
      i = mLunarColor;
      break;
    }
  }
  
  private String getLunarDays(int paramInt)
  {
    String[] arrayOfString = getResources().getStringArray(R.array.mc_custom_time_picker_lunar_day);
    if (paramInt > arrayOfString.length - 1) {
      return null;
    }
    return arrayOfString[paramInt];
  }
  
  private int getMonthDays(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    boolean bool = true;
    if (paramBoolean)
    {
      int i = LunarCalendar.leapMonth(paramInt1);
      if (i == 0) {
        break label74;
      }
      if (i == paramInt2) {
        paramBoolean = bool;
      }
    }
    for (;;)
    {
      return LunarCalendar.daysInMonth(paramInt1, paramInt2, paramBoolean);
      paramBoolean = false;
      continue;
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(5, 1);
      localCalendar.set(1, paramInt1);
      localCalendar.set(2, paramInt2 - 1);
      return localCalendar.getActualMaximum(5);
      label74:
      paramBoolean = false;
    }
  }
  
  private int getMonthIndex(int paramInt)
  {
    int i = mOneScreenCount / 2;
    if ((paramInt < 0) || (paramInt > 11)) {
      return i;
    }
    int j = mCalendar.get(2);
    if (paramInt >= j) {
      return i + (paramInt - j);
    }
    return i + (12 - j + paramInt);
  }
  
  private void inflateLayout()
  {
    if (getChildCount() > 0) {
      removeAllViews();
    }
    getResources().getDisplayMetrics();
    mOneScreenCount = 3;
    int i = R.layout.mc_custom_picker_24hour;
    int k = getResources().getDimensionPixelOffset(R.dimen.mc_custom_time_picker_select_h);
    inflate(getContext(), i, this);
    mPickerHolder = ((FrameLayout)findViewById(R.id.picker_holder));
    mHourUnit = ((TextView)findViewById(R.id.mc_scroll_hour_text));
    if (mHourUnit != null) {
      mHourUnit.setText(R.string.mc_date_time_hour);
    }
    mMinuteUnit = ((TextView)findViewById(R.id.mc_scroll_min_text));
    if (mMinuteUnit != null) {
      mMinuteUnit.setText(R.string.mc_date_time_min);
    }
    mHourPicker = ((ScrollTextView)findViewById(R.id.mc_scroll_hour));
    Object localObject = mHourPicker;
    TimeAdapter localTimeAdapter = new TimeAdapter(1);
    int m = mCurrentHour;
    int j;
    if (mIs24HourView.booleanValue())
    {
      i = 24;
      int n = mOneScreenCount;
      if (!mIs24HourView.booleanValue()) {
        break label649;
      }
      j = 23;
      label194:
      ((ScrollTextView)localObject).setData(localTimeAdapter, -1.0F, m, i, n, 0, j, true);
      mHourPicker.setSelectItemHeight(k);
      mMinutePicker = ((ScrollTextView)findViewById(R.id.mc_scroll_min));
      mMinutePicker.setData(new TimeAdapter(2), -1.0F, mCurrentMinute, 60, mOneScreenCount, 0, 59, true);
      mMinutePicker.setSelectItemHeight(k);
      mAmPmPicker = ((ScrollTextView)findViewById(R.id.mc_scroll_ampm));
      localObject = mAmPmPicker;
      localTimeAdapter = new TimeAdapter(3);
      if (!mIsAm) {
        break label655;
      }
      i = 0;
      label317:
      ((ScrollTextView)localObject).setData(localTimeAdapter, -1.0F, i, 2, mOneScreenCount, 0, 1, false);
      mAmPmPicker.setTextSize(getContext().getResources().getDimension(R.dimen.mz_picker_selected_ampm_size), getContext().getResources().getDimension(R.dimen.mz_picker_unselected_ampm_size));
      mAmPmPicker.setSelectItemHeight(k);
      localObject = (FrameLayout)findViewById(R.id.mc_column_ampm);
      if (!mIs24HourView.booleanValue()) {
        break label660;
      }
      ((FrameLayout)localObject).setVisibility(8);
    }
    for (;;)
    {
      mMonthUnit = ((TextView)findViewById(R.id.mc_scroll_month_text));
      if (mMonthUnit != null) {
        mMonthUnit.setText(R.string.mc_date_time_month);
      }
      mDayUnit = ((TextView)findViewById(R.id.mc_scroll_day_text));
      if (mDayUnit != null) {
        mDayUnit.setText(R.string.mc_date_time_day);
      }
      localObject = Calendar.getInstance();
      mYear = ((Calendar)localObject).get(1);
      mMonth = ((Calendar)localObject).get(2);
      mDay = ((Calendar)localObject).get(5);
      i = ((Calendar)localObject).getActualMaximum(5);
      mDayPicker = new DayPicker((ScrollTextView)findViewById(R.id.mc_scroll_day));
      mDayPicker.setData(new TimeAdapter(5), -1, mDay - 1, i, mOneScreenCount, mDay - 1, i - 1, true);
      mDayPicker.setSelectItemHeight(k);
      mMonthPicker = new MonthPicker((ScrollTextView)findViewById(R.id.mc_scroll_month));
      mMonthPicker.setData(null, -1.0F, getMonthIndex(mMonth), 12, mOneScreenCount, 0, 11, false);
      mMonthPicker.setSelectItemHeight(k);
      initTabView();
      return;
      i = 12;
      break;
      label649:
      j = 11;
      break label194;
      label655:
      i = 1;
      break label317;
      label660:
      ((FrameLayout)localObject).setVisibility(0);
    }
  }
  
  private void initTabView()
  {
    mLunarColor = getResources().getColor(R.color.mc_custom_date_picker_selected_lunar_color);
    mGregorianColor = getResources().getColor(R.color.mc_custom_date_picker_selected_gregorian_color);
    mUnSlectColor = getResources().getColor(R.color.mc_custom_date_picker_unselected_color);
  }
  
  private boolean isInternational()
  {
    return (!getResourcesgetConfigurationlocale.getCountry().equals("CN")) && (!getResourcesgetConfigurationlocale.getCountry().equals("TW")) && (!getResourcesgetConfigurationlocale.getCountry().equals("HK"));
  }
  
  private void setTabColor(int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    getContext().getResources().getColor(R.color.mc_custom_date_picker_unselected_tab_color);
    if (paramBoolean1) {}
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
  
  public void getTime(int[] paramArrayOfInt)
  {
    int i = 1;
    if (isLunar)
    {
      int[] arrayOfInt = new int[3];
      arrayOfInt = LunarCalendar.lunarToSolar(mYear, mMonth, mDay, false);
      paramArrayOfInt[0] = arrayOfInt[0];
      paramArrayOfInt[1] = arrayOfInt[1];
      paramArrayOfInt[2] = arrayOfInt[2];
      paramArrayOfInt[3] = getCurrentHour();
      paramArrayOfInt[4] = getCurrentMinute().intValue();
      if (!isLunar) {
        break label101;
      }
    }
    for (;;)
    {
      paramArrayOfInt[5] = i;
      return;
      paramArrayOfInt[0] = mYear;
      paramArrayOfInt[1] = mMonth;
      paramArrayOfInt[2] = mDay;
      break;
      label101:
      i = 0;
    }
  }
  
  public long getTimeMillis()
  {
    int[] arrayOfInt = new int[6];
    getTime(arrayOfInt);
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.set(arrayOfInt[0], arrayOfInt[1] - 1, arrayOfInt[2], arrayOfInt[3], arrayOfInt[4], 0);
    return localCalendar.getTimeInMillis();
  }
  
  public boolean is24HourView()
  {
    return mIs24HourView.booleanValue();
  }
  
  protected void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    int i = getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_padding);
    int j = getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_select_h);
    int k = getResources().getDimensionPixelSize(R.dimen.mc_custom_time_picker_picker_height);
    mMonthPicker.setSelectItemHeight(j);
    mDayPicker.setSelectItemHeight(j);
    mHourPicker.setSelectItemHeight(j);
    mMinutePicker.setSelectItemHeight(j);
    paramConfiguration = (LinearLayout.LayoutParams)mPickerHolder.getLayoutParams();
    leftMargin = i;
    rightMargin = i;
    height = k;
    mPickerHolder.setLayoutParams(paramConfiguration);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    boolean bool3 = true;
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    updateTime(h, min, mIs24HourView.booleanValue());
    int i = y;
    int j = mon;
    int k = d;
    boolean bool2;
    if (lunar == 1)
    {
      bool1 = true;
      if (leapmonth != 1) {
        break label126;
      }
      bool2 = true;
      label73:
      updateDate(i, j, k, bool1, bool2, false);
      if (lunar != 1) {
        break label132;
      }
      i = mLunarColor;
      label99:
      if (lunar != 1) {
        break label140;
      }
    }
    label126:
    label132:
    label140:
    for (boolean bool1 = bool3;; bool1 = false)
    {
      setTabColor(i, bool1, false);
      return;
      bool1 = false;
      break;
      bool2 = false;
      break label73;
      i = mGregorianColor;
      break label99;
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    return new SavedState(super.onSaveInstanceState(), getCurrentHour(), mCurrentMinute, mYear, mMonth, mDay, isLunar, isLeapMonth, null);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (!paramBoolean) {
      return;
    }
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
        continue;
        mMonthPicker.setTextSize(mSolarSelectTextSize, mSolarNormalTextSize);
        mDayPicker.setTextSize(mSolarSelectTextSize, mSolarNormalTextSize);
        mDayUnit.setAlpha(1.0F);
        int i = mGregorianColor;
      }
    }
    if (paramBoolean != mIs24HourView.booleanValue()) {
      updateTime(getCurrentHour(), mCurrentMinute, paramBoolean);
    }
    if (isLunar)
    {
      mMonthPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayUnit.setAlpha(0.0F);
      i = mLunarColor;
      setTabColor(i, isLunar, false);
      mMonthPicker.setTextColor(i, mUnSlectColor);
      mDayPicker.setTextColor(i, mUnSlectColor);
      mHourPicker.setTextColor(i, mUnSlectColor);
      mMinutePicker.setTextColor(i, mUnSlectColor);
      mAmPmPicker.setTextColor(i, mUnSlectColor);
      mMonthUnit.setTextColor(i);
      mDayUnit.setTextColor(i);
      mHourUnit.setTextColor(i);
      mMinuteUnit.setTextColor(i);
      return;
    }
  }
  
  public void setCurrentHour(Integer paramInteger)
  {
    if ((paramInteger == null) || (paramInteger.intValue() == getCurrentHour())) {
      return;
    }
    updateTime(paramInteger.intValue(), mCurrentMinute, mIs24HourView.booleanValue());
  }
  
  public void setCurrentMinute(Integer paramInteger)
  {
    updateTime(getCurrentHour(), paramInteger.intValue(), mIs24HourView.booleanValue());
  }
  
  public void setLunar(boolean paramBoolean)
  {
    boolean bool2 = false;
    doTabAnimate();
    int[] arrayOfInt1 = new int[4];
    int[] arrayOfInt2 = new int[2];
    arrayOfInt1[0] = mYear;
    arrayOfInt1[1] = mMonthPicker.getMonth(arrayOfInt2);
    arrayOfInt1[2] = (mDayPicker.getCurrentItem() + 1);
    arrayOfInt1[3] = 0;
    if (paramBoolean)
    {
      arrayOfInt1 = LunarCalendar.solarToLunar(arrayOfInt1[0], arrayOfInt1[1], arrayOfInt1[2]);
      i = arrayOfInt1[0];
      j = arrayOfInt1[1];
      k = arrayOfInt1[2];
      bool1 = bool2;
      if (arrayOfInt2[1] == 1) {
        bool1 = true;
      }
      updateDate(i, j, k, paramBoolean, bool1);
      return;
    }
    int i = arrayOfInt1[0];
    int j = arrayOfInt1[1];
    int k = arrayOfInt1[2];
    if (arrayOfInt2[1] == 1) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      arrayOfInt1 = LunarCalendar.lunarToSolar(i, j, k, bool1);
      break;
    }
  }
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    mMonthPicker.setTextColor(paramInt1, paramInt2);
    mDayPicker.setTextColor(paramInt1, paramInt2);
    mHourPicker.setTextColor(paramInt1, paramInt2);
    mMinutePicker.setTextColor(paramInt1, paramInt2);
    if (mAmPmPicker != null) {
      mAmPmPicker.setTextColor(paramInt1, paramInt2);
    }
    mHourUnit.setTextColor(paramInt3);
    mMinuteUnit.setTextColor(paramInt3);
    mMonthUnit.setTextColor(paramInt3);
    mDayUnit.setTextColor(paramInt3);
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
  {
    updateDate(paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2, true);
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    isLunar = paramBoolean1;
    mYear = paramInt1;
    mMonth = paramInt2;
    mDay = paramInt3;
    mMonthPicker.setMonth(mYear, mMonth, mDay, paramBoolean2);
    if (mMonthOfDays != getMonthDays(mYear, mMonth, paramBoolean1))
    {
      mMonthOfDays = getMonthDays(mYear, mMonth, paramBoolean1);
      mDayPicker.refreshCount(getMonthDays(mYear, mMonth, paramBoolean1));
    }
    mDayPicker.setCurrentItem(mDay - 1, paramBoolean3);
    if (isLunar)
    {
      mMonthPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayUnit.setAlpha(0.0F);
      return;
    }
    mMonthPicker.setTextSize(mSolarSelectTextSize, mSolarNormalTextSize);
    mDayPicker.setTextSize(mSolarSelectTextSize, mSolarNormalTextSize);
    mDayUnit.setAlpha(1.0F);
  }
  
  public void updateTime(int paramInt1, int paramInt2, boolean paramBoolean)
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
  
  public void updateTimeMillis(long paramLong, boolean paramBoolean)
  {
    mCalendar.setTimeInMillis(paramLong);
    updateTime(mCalendar.get(11), mCalendar.get(12), mIs24HourView.booleanValue());
    updateDate(mCalendar.get(1), mCalendar.get(2) + 1, mCalendar.get(5), false, false, paramBoolean);
  }
  
  class DayPicker
    implements ScrollTextView.OnScrollTextViewScrollListener
  {
    private ScrollTextView picker;
    private int validEnd;
    private int validStart;
    
    public DayPicker(ScrollTextView paramScrollTextView)
    {
      picker = paramScrollTextView;
    }
    
    public int getCurrentItem()
    {
      return picker.getCurrentItem();
    }
    
    public int getValidEnd()
    {
      return validEnd;
    }
    
    public int getValidStart()
    {
      return validStart;
    }
    
    public void onScrollingFinished(ScrollTextView paramScrollTextView)
    {
      int i = Math.max(Math.min(picker.getCurrentItem(), getValidEnd()), getValidStart());
      picker.setCurrentItem(i, true);
    }
    
    public void onScrollingStarted(ScrollTextView paramScrollTextView) {}
    
    public void refreshCount(int paramInt)
    {
      picker.refreshCount(paramInt);
    }
    
    public void setCurrentItem(int paramInt, boolean paramBoolean)
    {
      picker.setCurrentItem(paramInt, paramBoolean);
    }
    
    public void setData(CustomTimePicker.TimeAdapter paramTimeAdapter, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
    {
      setValidEnd(paramInt6);
      setValidStart(paramInt5);
      picker.setData(paramTimeAdapter, paramInt1, paramInt2, paramInt3, paramInt4, 0, paramInt3 - 1, paramBoolean);
      picker.addScrollingListener(this);
    }
    
    public void setSelectItemHeight(int paramInt)
    {
      picker.setSelectItemHeight(paramInt);
    }
    
    public void setTextColor(int paramInt1, int paramInt2)
    {
      picker.setTextColor(paramInt1, paramInt2);
    }
    
    public void setTextSize(int paramInt1, int paramInt2)
    {
      picker.setTextSize(paramInt1, paramInt2);
    }
    
    public void setValidEnd(int paramInt)
    {
      validEnd = paramInt;
    }
    
    public void setValidStart(int paramInt)
    {
      validStart = paramInt;
    }
  }
  
  class MonthPicker
    implements ScrollTextView.IDataAdapter
  {
    private int[] endDate = new int[4];
    private int endIndex;
    private int[] endLunarDate = new int[4];
    private int leapMonthIn1stYear;
    private int leapMonthIn2ndYear;
    private int lunarMonthsIn1stYear;
    private int lunarMonthsIn2ndYear;
    private String[] mMonths;
    private ScrollTextView picker;
    private int[] startDate = new int[4];
    private int startIndex;
    private int[] startLunarDate = new int[4];
    
    public MonthPicker(ScrollTextView paramScrollTextView)
    {
      picker = paramScrollTextView;
      mMonths = getShortMonths();
      calculateValidDateField();
    }
    
    private void calculateValidDateField()
    {
      mCalendar.setTimeInMillis(System.currentTimeMillis());
      startDate[0] = mCalendar.get(1);
      startDate[1] = (mCalendar.get(2) + 1);
      startDate[2] = mCalendar.get(5);
      startDate[3] = 0;
      int[] arrayOfInt = endDate;
      if (startDate[1] == 1)
      {
        i = startDate[0];
        arrayOfInt[0] = i;
        arrayOfInt = endDate;
        if (startDate[1] - 1 > 0) {
          break label274;
        }
      }
      label274:
      for (int i = 12;; i = startDate[1] - 1)
      {
        arrayOfInt[1] = i;
        endDate[2] = CustomTimePicker.this.getMonthDays(endDate[0], endDate[1], false);
        endDate[3] = 0;
        startLunarDate = LunarCalendar.solarToLunar(startDate[0], startDate[1], startDate[2]);
        endLunarDate = LunarCalendar.solarToLunar(endDate[0], endDate[1], endDate[2]);
        if (startLunarDate[0] != endLunarDate[0]) {
          break label286;
        }
        lunarMonthsIn1stYear = (endLunarDate[1] - startLunarDate[1] + 1);
        lunarMonthsIn2ndYear = 0;
        CustomTimePicker.access$1802(CustomTimePicker.this, lunarMonthsIn1stYear + lunarMonthsIn2ndYear);
        return;
        i = startDate[0] + 1;
        break;
      }
      label286:
      lunarMonthsIn1stYear = (12 - startLunarDate[1] + 1);
      i = LunarCalendar.leapMonth(startLunarDate[0]);
      leapMonthIn1stYear = i;
      if ((i != 0) && ((startLunarDate[1] < i) || ((i == startLunarDate[1]) && (startLunarDate[3] != 1)))) {
        lunarMonthsIn1stYear += 1;
      }
      lunarMonthsIn2ndYear = endLunarDate[1];
      i = LunarCalendar.leapMonth(endLunarDate[0]);
      leapMonthIn2ndYear = i;
      if ((i != 0) && ((endLunarDate[1] > i) || ((endLunarDate[1] == i) && (endLunarDate[3] == 1)))) {
        lunarMonthsIn2ndYear += 1;
      }
      CustomTimePicker.access$1802(CustomTimePicker.this, lunarMonthsIn1stYear + lunarMonthsIn2ndYear);
    }
    
    private String getLunarMonths(int paramInt1, int paramInt2)
    {
      paramInt1 %= 13;
      paramInt2 = LunarCalendar.leapMonth(paramInt2);
      if (paramInt2 == 0)
      {
        if (paramInt1 < 12) {}
      }
      else {
        while (paramInt1 >= 13) {
          return null;
        }
      }
      String[] arrayOfString = getResources().getStringArray(R.array.mc_custom_time_picker_lunar_month);
      String str = getResources().getString(R.string.mc_time_picker_leap);
      if ((paramInt2 != 0) && (paramInt1 > paramInt2 - 1))
      {
        if (paramInt1 == paramInt2) {
          return str + arrayOfString[(paramInt1 - 1)];
        }
        return arrayOfString[(paramInt1 - 1)];
      }
      return arrayOfString[paramInt1];
    }
    
    private String[] getShortMonths()
    {
      int j = 0;
      Locale localLocale = Locale.getDefault();
      if ((localLocale.equals(mMonthLocale)) && (mShortMonths != null)) {
        return mShortMonths;
      }
      synchronized (mMonthUpdateLock)
      {
        if (!localLocale.equals(mMonthLocale))
        {
          CustomTimePicker.access$2602(CustomTimePicker.this, new String[12]);
          int i = 0;
          while (i < 12)
          {
            mShortMonths[i] = DateUtils.getMonthString(i + 0, 20);
            i += 1;
          }
          if (mShortMonths[0].startsWith("1"))
          {
            i = j;
            while (i < mShortMonths.length)
            {
              mShortMonths[i] = String.valueOf(i + 1);
              i += 1;
            }
          }
          CustomTimePicker.access$2502(CustomTimePicker.this, localLocale);
        }
        return mShortMonths;
      }
    }
    
    public int getCurrentItem()
    {
      return picker.getCurrentItem() - mOneScreenCount / 2;
    }
    
    public String getItemText(int paramInt)
    {
      if (isLunar)
      {
        paramInt -= mOneScreenCount / 2;
        if ((paramInt < 0) || (paramInt > mLunarMonthCount - 1)) {
          return "";
        }
        if (paramInt >= lunarMonthsIn1stYear) {
          paramInt -= lunarMonthsIn1stYear;
        }
        for (i = endLunarDate[0];; i = startLunarDate[0])
        {
          return getLunarMonths(paramInt, i);
          i = paramInt + (startLunarDate[1] - 1);
          paramInt = i;
          if (leapMonthIn1stYear != 0) {
            if ((startLunarDate[1] <= leapMonthIn1stYear) && (startLunarDate[3] != 1))
            {
              paramInt = i;
              if (startLunarDate[1] < leapMonthIn1stYear)
              {
                paramInt = i;
                if (i < leapMonthIn1stYear) {}
              }
            }
            else
            {
              paramInt = i + 1;
            }
          }
        }
      }
      if ((paramInt < mOneScreenCount / 2) || (paramInt > mOneScreenCount / 2 + 11)) {
        return "";
      }
      int i = startDate[1];
      int j = mOneScreenCount / 2;
      return mMonths[((i - 1 + paramInt - j) % 12)];
    }
    
    public int getMonth(int[] paramArrayOfInt)
    {
      int i = getCurrentItem();
      int j;
      if (isLunar) {
        if (i >= lunarMonthsIn1stYear)
        {
          j = i - (lunarMonthsIn1stYear - 1);
          if (paramArrayOfInt != null) {
            paramArrayOfInt[0] = endLunarDate[0];
          }
          if ((j == leapMonthIn2ndYear + 1) && (paramArrayOfInt != null))
          {
            paramArrayOfInt[1] = 1;
            i = j;
            if (leapMonthIn2ndYear != 0)
            {
              i = j;
              if (j > leapMonthIn2ndYear) {
                i = j - 1;
              }
            }
          }
        }
      }
      label238:
      do
      {
        do
        {
          return i;
          paramArrayOfInt[1] = 0;
          break;
          i += startLunarDate[1];
          if (paramArrayOfInt != null) {
            paramArrayOfInt[0] = startLunarDate[0];
          }
          if (leapMonthIn1stYear != 0)
          {
            if ((startLunarDate[3] == 1) && (i == leapMonthIn1stYear))
            {
              paramArrayOfInt[1] = 1;
              return i;
            }
            if ((lunarMonthsIn1stYear > 12 - leapMonthIn1stYear + 1) && (i > leapMonthIn1stYear))
            {
              paramArrayOfInt[1] = 1;
              return i - 1;
            }
            paramArrayOfInt[1] = 0;
            return i;
          }
          paramArrayOfInt[1] = 0;
          return i;
          if (i <= 12 - startDate[1]) {
            break label238;
          }
          j = i - (12 - startDate[1]);
          i = j;
        } while (paramArrayOfInt == null);
        paramArrayOfInt[0] = endDate[0];
        paramArrayOfInt[1] = 0;
        return j;
        j = i + startDate[1];
        i = j;
      } while (paramArrayOfInt == null);
      paramArrayOfInt[0] = startDate[0];
      paramArrayOfInt[1] = 0;
      return j;
    }
    
    public void onChanged(View paramView, int paramInt1, int paramInt2)
    {
      int i = CustomTimePicker.this.getMonthDays(mYear, mMonth, isLunar);
      paramInt1 = paramInt2 - mOneScreenCount / 2;
      if (isLunar) {
        if (paramInt1 >= lunarMonthsIn1stYear)
        {
          CustomTimePicker.access$1902(CustomTimePicker.this, endLunarDate[0]);
          if (!isLunar) {
            break label359;
          }
          if (paramInt1 < lunarMonthsIn1stYear) {
            break label297;
          }
          paramInt2 = paramInt1 - (lunarMonthsIn1stYear - 1);
          paramInt1 = paramInt2;
          if (leapMonthIn2ndYear != 0)
          {
            paramInt1 = paramInt2;
            if (paramInt2 > leapMonthIn2ndYear) {
              paramInt1 = paramInt2 - 1;
            }
          }
        }
      }
      for (;;)
      {
        CustomTimePicker.access$2002(CustomTimePicker.this, paramInt1);
        if ((i != CustomTimePicker.this.getMonthDays(mYear, mMonth, isLunar)) && (mDayPicker != null))
        {
          paramInt1 = CustomTimePicker.this.getMonthDays(mYear, mMonth, isLunar);
          mDayPicker.refreshCount(paramInt1);
        }
        setDayPickerValidField(mDayPicker.getCurrentItem() + 1);
        return;
        CustomTimePicker.access$1902(CustomTimePicker.this, startLunarDate[0]);
        break;
        if (paramInt1 > 12 - startDate[1])
        {
          CustomTimePicker.access$1902(CustomTimePicker.this, endDate[0]);
          break;
        }
        CustomTimePicker.access$1902(CustomTimePicker.this, startDate[0]);
        break;
        label297:
        paramInt2 = paramInt1 + startLunarDate[1];
        paramInt1 = paramInt2;
        if (leapMonthIn1stYear != 0)
        {
          paramInt1 = paramInt2;
          if (startLunarDate[1] <= leapMonthIn1stYear)
          {
            paramInt1 = paramInt2;
            if (startLunarDate[3] != 1)
            {
              paramInt1 = paramInt2;
              if (paramInt2 > leapMonthIn1stYear)
              {
                paramInt1 = paramInt2 - 1;
                continue;
                label359:
                if (paramInt1 > 12 - startDate[1]) {
                  paramInt1 -= 12 - startDate[1];
                } else {
                  paramInt1 += startDate[1];
                }
              }
            }
          }
        }
      }
    }
    
    public void refreshCountAndCurrent(int paramInt1, int paramInt2)
    {
      picker.refreshCountAndCurrent(mOneScreenCount / 2 * 2 + paramInt1, mOneScreenCount / 2 + paramInt2);
    }
    
    public void setData(ScrollTextView.IDataAdapter paramIDataAdapter, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
    {
      startIndex = paramInt4;
      endIndex = (paramInt3 / 2 * 2 + paramInt5 + 1);
      picker.setData(this, paramFloat, paramInt1, paramInt2 + paramInt3 / 2 * 2, paramInt3, startIndex, endIndex, paramBoolean);
    }
    
    public void setDayPickerValidField(int paramInt)
    {
      int i = CustomTimePicker.this.getMonthDays(mYear, mMonth, isLunar);
      if (isLunar) {
        if ((mYear == startLunarDate[0]) && (mMonth == startLunarDate[1]))
        {
          mDayPicker.setValidStart(startLunarDate[2] - 1);
          if ((mYear != endLunarDate[0]) || (mMonth != endLunarDate[1])) {
            break label262;
          }
          mDayPicker.setValidEnd(endLunarDate[2] - 1);
        }
      }
      for (;;)
      {
        CustomTimePicker.access$302(CustomTimePicker.this, paramInt);
        CustomTimePicker.access$302(CustomTimePicker.this, Math.min(mDay, i));
        CustomTimePicker.access$302(CustomTimePicker.this, Math.min(mDay, mDayPicker.getValidEnd() + 1));
        CustomTimePicker.access$302(CustomTimePicker.this, Math.max(mDay, mDayPicker.getValidStart() + 1));
        mDayPicker.setCurrentItem(mDay - 1, true);
        return;
        mDayPicker.setValidStart(0);
        break;
        label262:
        mDayPicker.setValidEnd(i - 1);
        continue;
        if ((mYear == startDate[0]) && (mMonth == startDate[1])) {
          mDayPicker.setValidStart(startDate[2] - 1);
        }
        for (;;)
        {
          if ((mYear != endDate[0]) || (mMonth != endDate[1])) {
            break label395;
          }
          mDayPicker.setValidEnd(endDate[2] - 1);
          break;
          mDayPicker.setValidStart(0);
        }
        label395:
        mDayPicker.setValidEnd(i - 1);
      }
    }
    
    public void setMonth(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      if (paramInt2 < 0) {
        return;
      }
      CustomTimePicker.access$1702(CustomTimePicker.this, paramBoolean);
      if (isLunar) {
        if (paramInt1 == startLunarDate[0])
        {
          paramInt1 = paramInt2;
          if (leapMonthIn1stYear != 0)
          {
            paramInt1 = paramInt2;
            if (startLunarDate[0] <= leapMonthIn1stYear)
            {
              paramInt1 = paramInt2;
              if (startLunarDate[3] != 1) {
                if (paramInt2 <= leapMonthIn1stYear)
                {
                  paramInt1 = paramInt2;
                  if (leapMonthIn1stYear == paramInt2)
                  {
                    paramInt1 = paramInt2;
                    if (!paramBoolean) {}
                  }
                }
                else
                {
                  paramInt1 = paramInt2 + 1;
                }
              }
            }
          }
          refreshCountAndCurrent(mLunarMonthCount, paramInt1 - startLunarDate[1]);
        }
      }
      for (;;)
      {
        setDayPickerValidField(paramInt3);
        return;
        if (paramInt1 == endLunarDate[0])
        {
          paramInt1 = paramInt2;
          if (leapMonthIn2ndYear != 0) {
            if (paramInt2 <= leapMonthIn2ndYear)
            {
              paramInt1 = paramInt2;
              if (leapMonthIn2ndYear == paramInt2)
              {
                paramInt1 = paramInt2;
                if (!paramBoolean) {}
              }
            }
            else
            {
              paramInt1 = paramInt2 + 1;
            }
          }
          refreshCountAndCurrent(mLunarMonthCount, lunarMonthsIn1stYear + paramInt1 - 1);
          continue;
          if (paramInt2 <= 12) {
            if ((paramInt1 == startDate[0]) && (paramInt2 >= startDate[1])) {
              refreshCountAndCurrent(12, Math.min(11, paramInt2 - startDate[1]));
            } else if ((paramInt1 == endDate[0]) && (paramInt2 <= endDate[1])) {
              refreshCountAndCurrent(12, 11 - (endDate[1] - paramInt2));
            }
          }
        }
      }
    }
    
    public void setSelectItemHeight(int paramInt)
    {
      picker.setSelectItemHeight(paramInt);
    }
    
    public void setTextColor(int paramInt1, int paramInt2)
    {
      picker.setTextColor(paramInt1, paramInt2);
    }
    
    public void setTextSize(int paramInt1, int paramInt2)
    {
      picker.setTextSize(paramInt1, paramInt2);
    }
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new CustomTimePicker.SavedState.1();
    private final int d;
    private final int h;
    private final int leapmonth;
    private final int lunar;
    private final int min;
    private final int mon;
    private final int y;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      h = paramParcel.readInt();
      min = paramParcel.readInt();
      y = paramParcel.readInt();
      mon = paramParcel.readInt();
      d = paramParcel.readInt();
      lunar = paramParcel.readInt();
      leapmonth = paramParcel.readInt();
    }
    
    private SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean1, boolean paramBoolean2)
    {
      super();
      h = paramInt1;
      min = paramInt2;
      y = paramInt3;
      mon = paramInt4;
      d = paramInt5;
      if (paramBoolean1)
      {
        paramInt1 = 1;
        lunar = paramInt1;
        if (!paramBoolean2) {
          break label67;
        }
      }
      label67:
      for (paramInt1 = i;; paramInt1 = 0)
      {
        leapmonth = paramInt1;
        return;
        paramInt1 = 0;
        break;
      }
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(h);
      paramParcel.writeInt(min);
      paramParcel.writeInt(y);
      paramParcel.writeInt(mon);
      paramParcel.writeInt(d);
      paramParcel.writeInt(lunar);
      paramParcel.writeInt(leapmonth);
    }
  }
  
  class TimeAdapter
    implements ScrollTextView.IDataAdapter
  {
    static final int SET_AMPM = 3;
    static final int SET_DAY = 5;
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
      case 4: 
      default: 
      case 1: 
      case 2: 
      case 3: 
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
      if (isLunar) {
        return CustomTimePicker.this.getLunarDays(paramInt);
      }
      return String.valueOf(paramInt + 1);
    }
    
    public void onChanged(View paramView, int paramInt1, int paramInt2)
    {
      switch (mType)
      {
      case 4: 
      default: 
        return;
      case 1: 
        CustomTimePicker.access$002(CustomTimePicker.this, paramInt2);
        return;
      case 2: 
        CustomTimePicker.access$102(CustomTimePicker.this, paramInt2);
        return;
      case 3: 
        paramView = CustomTimePicker.this;
        if (paramInt2 == 0) {}
        for (boolean bool = true;; bool = false)
        {
          CustomTimePicker.access$202(paramView, bool);
          return;
        }
      }
      CustomTimePicker.access$302(CustomTimePicker.this, paramInt2 + 1);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomTimePicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */