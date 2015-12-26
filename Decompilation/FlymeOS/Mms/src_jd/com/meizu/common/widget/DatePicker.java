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
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.meizu.common.R.array;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import com.meizu.common.R.styleable;
import com.meizu.common.util.LunarCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePicker
  extends FrameLayout
{
  private static final int DEFAULT_END_YEAR = 2037;
  private static final int DEFAULT_START_YEAR = 1970;
  private static final int NUMBER_OF_MONTHS = 12;
  private boolean isLunar = false;
  private int mDay;
  private ScrollTextView mDayPicker;
  private TextView mDayUnit;
  private int mEndYear;
  private boolean mIsAccessibilityEnable = false;
  private boolean mIsDrawLine;
  private int mLayoutResId = R.layout.mc_date_picker;
  private int mLineOneHeight;
  private Paint mLinePaint;
  private int mLineTwoHeight;
  private int mLunarNormalTextSize;
  private int mLunarSelectTextSize;
  private int mMonth;
  private volatile Locale mMonthLocale;
  private int mMonthOfDays;
  private ScrollTextView mMonthPicker;
  private TextView mMonthUnit;
  private Object mMonthUpdateLock = new Object();
  private String[] mMonths;
  private float mNormalItemHeight;
  private OnDateChangedListener mOnDateChangedListener;
  private int mOneScreenCount = 5;
  String mOrder;
  private LinearLayout mPickerHolder;
  private float mSelectItemHeight;
  private String[] mShortMonths;
  private int mSolarNormalTextSize;
  private int mSolarSelectTextSize;
  private int mStartYear;
  private int mWidthPadding;
  private int mYear;
  private int mYearOfMonths;
  private ScrollTextView mYearPicker;
  private TextView mYearUnit;
  
  public DatePicker(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public DatePicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public DatePicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.DatePicker);
    mStartYear = paramAttributeSet.getInt(R.styleable.DatePicker_mcStartYear, 1970);
    mEndYear = paramAttributeSet.getInt(R.styleable.DatePicker_mcEndYear, 2037);
    mLayoutResId = paramAttributeSet.getResourceId(R.styleable.DatePicker_mcInternalLayout, mLayoutResId);
    mOneScreenCount = paramAttributeSet.getInt(R.styleable.DatePicker_mcVisibleRow, mOneScreenCount);
    mSelectItemHeight = paramAttributeSet.getDimension(R.styleable.DatePicker_mcSelectItemHeight, mSelectItemHeight);
    mNormalItemHeight = paramAttributeSet.getDimension(R.styleable.DatePicker_mcNormalItemHeight, mNormalItemHeight);
    paramAttributeSet.recycle();
    inflate(getContext(), mLayoutResId, this);
    mMonthUnit = ((TextView)findViewById(R.id.mc_scroll1_text));
    if (mMonthUnit != null) {
      mMonthUnit.setText(R.string.mc_date_time_month);
    }
    mDayUnit = ((TextView)findViewById(R.id.mc_scroll2_text));
    if (mDayUnit != null) {
      mDayUnit.setText(R.string.mc_date_time_day);
    }
    mYearUnit = ((TextView)findViewById(R.id.mc_scroll3_text));
    if (mYearUnit != null) {
      mYearUnit.setText(R.string.mc_date_time_year);
    }
    paramAttributeSet = Calendar.getInstance();
    mYear = paramAttributeSet.get(1);
    mMonth = paramAttributeSet.get(2);
    mDay = paramAttributeSet.get(5);
    mOnDateChangedListener = null;
    paramInt = paramAttributeSet.getActualMaximum(5);
    mPickerHolder = ((LinearLayout)findViewById(R.id.mc_column_parent));
    mDayPicker = ((ScrollTextView)findViewById(R.id.mc_scroll2));
    if ((mSelectItemHeight != 0.0F) && (mNormalItemHeight != 0.0F)) {
      mDayPicker.setItemHeight((int)mSelectItemHeight, (int)mNormalItemHeight);
    }
    mDayPicker.setData(new DateAdapter(3), -1.0F, mDay - 1, paramInt, mOneScreenCount, 0, paramInt - 1, true);
    mMonthPicker = ((ScrollTextView)findViewById(R.id.mc_scroll1));
    if ((mSelectItemHeight != 0.0F) && (mNormalItemHeight != 0.0F)) {
      mMonthPicker.setItemHeight((int)mSelectItemHeight, (int)mNormalItemHeight);
    }
    mMonths = getShortMonths();
    mMonthPicker.setData(new DateAdapter(2), -1.0F, mMonth, 12, mOneScreenCount, 0, 11, true);
    mYearPicker = ((ScrollTextView)findViewById(R.id.mc_scroll3));
    mStartYear -= mOneScreenCount;
    mEndYear += mOneScreenCount;
    if ((mSelectItemHeight != 0.0F) && (mNormalItemHeight != 0.0F)) {
      mYearPicker.setItemHeight((int)mSelectItemHeight, (int)mNormalItemHeight);
    }
    updateYearPicker();
    reorderPickers(mMonths);
    paramAttributeSet = mMonthUnit;
    if (isNumeric(mMonths[0])) {}
    for (paramInt = 0;; paramInt = 8)
    {
      paramAttributeSet.setVisibility(paramInt);
      adjustLayout4FocusedPosition();
      if (!isEnabled()) {
        setEnabled(false);
      }
      mLunarNormalTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_date_picker_normal_lunar_size);
      mLunarSelectTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_date_picker_selected_lunar_size);
      mSolarNormalTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_picker_normal_number_size);
      mSolarSelectTextSize = paramContext.getResources().getDimensionPixelOffset(R.dimen.mc_picker_selected_number_size);
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
      paramContext = (AccessibilityManager)paramContext.getSystemService("accessibility");
      if (paramContext != null) {
        mIsAccessibilityEnable = paramContext.isEnabled();
      }
      if (mIsAccessibilityEnable) {
        setFocusable(true);
      }
      return;
    }
  }
  
  private void adjustLayout4FocusedPosition()
  {
    mMonthUnit = ((TextView)findViewById(R.id.mc_scroll1_text));
    if (mMonthUnit != null) {
      mMonthUnit.setText(R.string.mc_date_time_month);
    }
    mDayUnit = ((TextView)findViewById(R.id.mc_scroll2_text));
    if (mDayUnit != null) {
      mDayUnit.setText(R.string.mc_date_time_day);
    }
    mYearUnit = ((TextView)findViewById(R.id.mc_scroll3_text));
    if (mYearUnit != null) {
      mYearUnit.setText(R.string.mc_date_time_year);
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
  
  private String getLunarMonths(int paramInt)
  {
    int i = LunarCalendar.leapMonth(mYear);
    if (i == 0)
    {
      if (paramInt < 12) {}
    }
    else {
      while (paramInt >= 13) {
        return null;
      }
    }
    String[] arrayOfString = getResources().getStringArray(R.array.mc_custom_time_picker_lunar_month);
    String str = getResources().getString(R.string.mc_time_picker_leap);
    if ((i != 0) && (paramInt > i - 1))
    {
      if (paramInt == i) {
        return str + arrayOfString[(paramInt - 1)] + "  ";
      }
      return arrayOfString[(paramInt - 1)];
    }
    return arrayOfString[paramInt];
  }
  
  private int getMonthDays()
  {
    boolean bool1 = false;
    boolean bool2 = true;
    int i;
    if (isLunar)
    {
      i = mMonth;
      int j = LunarCalendar.leapMonth(mYear);
      if (j != 0)
      {
        if (j == i) {
          bool1 = bool2;
        }
      }
      else
      {
        if ((j != 0) && ((j == 0) || (mMonth >= j))) {
          break label111;
        }
        i += 1;
      }
    }
    label111:
    for (;;)
    {
      return LunarCalendar.daysInMonth(mYear, i, bool1);
      bool1 = false;
      break;
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(5, 1);
      localCalendar.set(1, mYear);
      localCalendar.set(2, mMonth);
      return localCalendar.getActualMaximum(5);
    }
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
        mShortMonths = new String[12];
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
        mMonthLocale = localLocale;
      }
      return mShortMonths;
    }
  }
  
  private String getTimeText(int paramInt)
  {
    switch (paramInt)
    {
    default: 
    case 1: 
    case 2: 
      do
      {
        return "";
        return String.valueOf(mYear);
        paramInt = mMonth;
        if (isLunar) {
          return getLunarMonths(paramInt);
        }
        if (mMonths == null) {
          mMonths = getShortMonths();
        }
      } while (paramInt >= mMonths.length);
      return mMonths[paramInt];
    }
    paramInt = mDay - 1;
    if (isLunar) {
      return getLunarDays(paramInt);
    }
    return String.valueOf(paramInt + 1);
  }
  
  private int getYearMonths()
  {
    if ((!isLunar) || (LunarCalendar.leapMonth(mYear) == 0)) {
      return 12;
    }
    return 13;
  }
  
  public static boolean isNumeric(String paramString)
  {
    return Pattern.compile("[0-9]*").matcher(paramString).matches();
  }
  
  private void reorderPickers(String[] paramArrayOfString)
  {
    label38:
    FrameLayout localFrameLayout1;
    FrameLayout localFrameLayout2;
    ImageView localImageView2;
    LinearLayout localLinearLayout;
    int i;
    int j;
    int k;
    int m;
    int i4;
    int i6;
    int i5;
    label133:
    int i12;
    int n;
    label173:
    int i10;
    int i11;
    int i7;
    int i8;
    int i9;
    int i3;
    int i2;
    int i1;
    if (paramArrayOfString[0].startsWith("1"))
    {
      paramArrayOfString = DateFormat.getDateFormat(getContext());
      if (!(paramArrayOfString instanceof SimpleDateFormat)) {
        break label329;
      }
      mOrder = ((SimpleDateFormat)paramArrayOfString).toPattern();
      paramArrayOfString = (FrameLayout)findViewById(R.id.mc_column1Layout);
      localFrameLayout1 = (FrameLayout)findViewById(R.id.mc_column2Layout);
      localFrameLayout2 = (FrameLayout)findViewById(R.id.mc_column3Layout);
      ImageView localImageView1 = (ImageView)findViewById(R.id.mc_divider_bar1);
      localImageView2 = (ImageView)findViewById(R.id.mc_divider_bar2);
      localLinearLayout = (LinearLayout)findViewById(R.id.mc_column_parent);
      localLinearLayout.removeAllViews();
      i = 0;
      j = 0;
      k = 0;
      m = 0;
      i4 = 0;
      i6 = 0;
      i5 = 0;
      if (i5 >= mOrder.length()) {
        break label512;
      }
      i12 = mOrder.charAt(i5);
      n = i;
      if (i12 == 39)
      {
        if (i != 0) {
          break label350;
        }
        n = 1;
      }
      i10 = i6;
      i11 = i4;
      i7 = m;
      i8 = k;
      i9 = j;
      if (n == 0)
      {
        i7 = 0;
        if ((i12 != 100) || (j != 0)) {
          break label356;
        }
        localLinearLayout.addView(localFrameLayout1);
        i3 = 1;
        i = 1;
        i2 = k;
        i1 = m;
        label231:
        i10 = i6;
        i11 = i4;
        i7 = i1;
        i8 = i2;
        i9 = i3;
        if (1 == i)
        {
          if (i4 != 0) {
            break label458;
          }
          localLinearLayout.addView(localImageView1);
          i11 = 1;
          i9 = i3;
          i8 = i2;
          i7 = i1;
          i10 = i6;
        }
      }
    }
    for (;;)
    {
      i5 += 1;
      i6 = i10;
      i4 = i11;
      m = i7;
      k = i8;
      j = i9;
      i = n;
      break label133;
      paramArrayOfString = DateFormat.getMediumDateFormat(getContext());
      break;
      label329:
      mOrder = new String(DateFormat.getDateFormatOrder(getContext()));
      break label38;
      label350:
      n = 0;
      break label173;
      label356:
      if (((i12 == 77) || (i12 == 76)) && (k == 0))
      {
        localLinearLayout.addView(paramArrayOfString);
        i2 = 1;
        i = 1;
        i1 = m;
        i3 = j;
        break label231;
      }
      i1 = m;
      i2 = k;
      i3 = j;
      i = i7;
      if (i12 != 121) {
        break label231;
      }
      i1 = m;
      i2 = k;
      i3 = j;
      i = i7;
      if (m != 0) {
        break label231;
      }
      localLinearLayout.addView(localFrameLayout2);
      i1 = 1;
      i = 1;
      i2 = k;
      i3 = j;
      break label231;
      label458:
      i10 = i6;
      i11 = i4;
      i7 = i1;
      i8 = i2;
      i9 = i3;
      if (i6 == 0)
      {
        localLinearLayout.addView(localImageView2);
        i10 = 1;
        i11 = i4;
        i7 = i1;
        i8 = i2;
        i9 = i3;
      }
    }
    label512:
    if (k == 0) {
      localLinearLayout.addView(paramArrayOfString);
    }
    if (j == 0) {
      localLinearLayout.addView(localFrameLayout1);
    }
    if (m == 0) {
      localLinearLayout.addView(localFrameLayout2);
    }
  }
  
  private void sendAccessibilityEvent()
  {
    if (mIsAccessibilityEnable)
    {
      setContentDescription((getTimeText(1) + mYearUnit.getText() + getTimeText(2) + mMonthUnit.getText() + getTimeText(3) + mDayUnit.getText()).replace(" ", "").replace("廿十", "二十").replace("廿", "二十"));
      sendAccessibilityEvent(4);
    }
  }
  
  private void updateYearPicker()
  {
    mYearPicker.setData(new DateAdapter(1), -1.0F, mYear - mStartYear, mEndYear - mStartYear + 1, mOneScreenCount, mOneScreenCount, mEndYear - mStartYear - mOneScreenCount, false);
  }
  
  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if ((mIsAccessibilityEnable) && (paramAccessibilityEvent.getEventType() == 32))
    {
      String str = (getTimeText(1) + mYearUnit.getText() + getTimeText(2) + mMonthUnit.getText() + getTimeText(3) + mDayUnit.getText()).replace(" ", "").replace("廿十", "二十").replace("廿", "二十");
      paramAccessibilityEvent.getText().add(str);
      return false;
    }
    return super.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchThawSelfOnly(paramSparseArray);
  }
  
  public int getDayOfMonth()
  {
    return mDay;
  }
  
  public TextView getDayUnit()
  {
    return mDayUnit;
  }
  
  public int getMonth()
  {
    return mMonth;
  }
  
  public int getYear()
  {
    return mYear;
  }
  
  public void init(int paramInt1, int paramInt2, int paramInt3, OnDateChangedListener paramOnDateChangedListener, boolean paramBoolean)
  {
    if ((mYear != paramInt1) || (mMonth != paramInt2) || (mDay != paramInt3)) {
      updateDate(paramInt1, paramInt2, paramInt3, paramBoolean);
    }
    mOnDateChangedListener = paramOnDateChangedListener;
    sendAccessibilityEvent();
  }
  
  public void init(int paramInt1, int paramInt2, int paramInt3, OnDateChangedListener paramOnDateChangedListener, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((mYear != paramInt1) || (mMonth != paramInt2) || (mDay != paramInt3) || (isLunar != paramBoolean1))
    {
      if (!paramBoolean1) {
        break label163;
      }
      isLunar = paramBoolean1;
      mMonthPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayUnit.setAlpha(0.0F);
      int j = LunarCalendar.leapMonth(paramInt1);
      paramBoolean1 = paramBoolean2;
      if (j != 0)
      {
        paramBoolean1 = paramBoolean2;
        if (paramInt2 + 1 != j) {
          paramBoolean1 = false;
        }
      }
      int i = paramInt2;
      if (j != 0) {
        if (!paramBoolean1)
        {
          i = paramInt2;
          if (paramInt2 <= j) {}
        }
        else
        {
          i = paramInt2 + 1;
        }
      }
      updateDate(paramInt1, i, paramInt3, false);
    }
    for (;;)
    {
      mOnDateChangedListener = paramOnDateChangedListener;
      sendAccessibilityEvent();
      return;
      label163:
      updateDate(paramInt1, paramInt2, paramInt3, false);
    }
  }
  
  public boolean isLunar()
  {
    return isLunar;
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
    mYear = paramParcelable.getYear();
    mMonth = paramParcelable.getMonth();
    mDay = paramParcelable.getDay();
  }
  
  protected Parcelable onSaveInstanceState()
  {
    return new SavedState(super.onSaveInstanceState(), mYear, mMonth, mDay, null);
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (!paramBoolean) {}
    label86:
    label102:
    for (;;)
    {
      return;
      String[] arrayOfString = getShortMonths();
      if (arrayOfString[0].startsWith("1"))
      {
        localObject = DateFormat.getDateFormat(getContext());
        if (!(localObject instanceof SimpleDateFormat)) {
          break label86;
        }
      }
      for (Object localObject = ((SimpleDateFormat)localObject).toPattern();; localObject = new String(DateFormat.getDateFormatOrder(getContext())))
      {
        if (mOrder.equals(localObject)) {
          break label102;
        }
        mMonths = arrayOfString;
        reorderPickers(mMonths);
        return;
        localObject = DateFormat.getMediumDateFormat(getContext());
        break;
      }
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    mDayPicker.setEnabled(paramBoolean);
    mMonthPicker.setEnabled(paramBoolean);
    mYearPicker.setEnabled(paramBoolean);
  }
  
  public void setIsDrawLine(boolean paramBoolean)
  {
    mIsDrawLine = paramBoolean;
  }
  
  public void setItemHeight(int paramInt1, int paramInt2)
  {
    mDayPicker.setItemHeight(paramInt1, paramInt2);
    mMonthPicker.setItemHeight(paramInt1, paramInt2);
    mYearPicker.setItemHeight(paramInt1, paramInt2);
  }
  
  public void setLineHeight(int paramInt1, int paramInt2)
  {
    mLineOneHeight = paramInt1;
    mLineTwoHeight = paramInt2;
  }
  
  public void setLunar(boolean paramBoolean)
  {
    setLunar(paramBoolean, true);
  }
  
  public void setLunar(boolean paramBoolean1, boolean paramBoolean2)
  {
    isLunar = paramBoolean1;
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = (mYearPicker.getCurrentItem() + mStartYear);
    arrayOfInt[1] = (mMonthPicker.getCurrentItem() + 1);
    arrayOfInt[2] = (mDayPicker.getCurrentItem() + 1);
    arrayOfInt[3] = 0;
    int i = arrayOfInt[0];
    int j = LunarCalendar.leapMonth(arrayOfInt[0]);
    int k = LunarCalendar.leapMonth(arrayOfInt[0] - 1);
    if (isLunar)
    {
      arrayOfInt = LunarCalendar.solarToLunar(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2]);
      if (((i != arrayOfInt[0]) && (k != 0) && ((arrayOfInt[3] == 1) || (arrayOfInt[1] > k))) || ((i == arrayOfInt[0]) && (j != 0) && ((arrayOfInt[3] == 1) || (arrayOfInt[1] > j)))) {
        arrayOfInt[1] += 1;
      }
      mMonthPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayPicker.setTextSize(mLunarSelectTextSize, mLunarNormalTextSize);
      mDayUnit.setAlpha(0.0F);
      j = arrayOfInt[0];
      if (arrayOfInt[1] - 1 < 0)
      {
        i = 12;
        label236:
        updateDate(j, i, arrayOfInt[2], paramBoolean2);
      }
    }
    else if ((j == 0) || (j >= arrayOfInt[1]))
    {
      i = arrayOfInt[1];
      paramBoolean1 = false;
    }
    for (;;)
    {
      mMonthPicker.setTextSize(mSolarSelectTextSize, mSolarNormalTextSize);
      mDayPicker.setTextSize(mSolarSelectTextSize, mSolarNormalTextSize);
      mDayUnit.setAlpha(1.0F);
      arrayOfInt = LunarCalendar.lunarToSolar(arrayOfInt[0], i, arrayOfInt[2], paramBoolean1);
      break;
      if (j + 1 == arrayOfInt[1])
      {
        i = arrayOfInt[1] - 1;
        paramBoolean1 = true;
      }
      else
      {
        if (j + 1 < arrayOfInt[1])
        {
          i = arrayOfInt[1] - 1;
          paramBoolean1 = false;
          continue;
          i = arrayOfInt[1] - 1;
          break label236;
        }
        i = 0;
        paramBoolean1 = false;
      }
    }
  }
  
  public void setMaxDate(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(1);
    if ((i != mEndYear) && (i > mStartYear))
    {
      mEndYear = i;
      mEndYear += mOneScreenCount;
      updateYearPicker();
    }
  }
  
  public void setMinDate(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(1);
    if ((i != mStartYear) && (i < mEndYear))
    {
      mStartYear = i;
      mStartYear -= mOneScreenCount;
      updateYearPicker();
    }
  }
  
  public void setTextColor(int paramInt1, int paramInt2, int paramInt3)
  {
    mDayPicker.setTextColor(paramInt1, paramInt2);
    mMonthPicker.setTextColor(paramInt1, paramInt2);
    mYearPicker.setTextColor(paramInt1, paramInt2);
    mDayUnit.setTextColor(paramInt3);
    mMonthUnit.setTextColor(paramInt3);
    mYearUnit.setTextColor(paramInt3);
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3)
  {
    updateDate(paramInt1, paramInt2, paramInt3, true);
  }
  
  public void updateDate(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    mYear = paramInt1;
    mMonth = paramInt2;
    mDay = paramInt3;
    mMonths = null;
    mMonths = getShortMonths();
    mYearPicker.setCurrentItem(mYear - mStartYear, paramBoolean);
    if (mYearOfMonths != getYearMonths())
    {
      mYearOfMonths = getYearMonths();
      mMonthPicker.refreshCount(mYearOfMonths);
    }
    mMonthPicker.setCurrentItem(mMonth, paramBoolean);
    if (mMonthOfDays != getMonthDays())
    {
      mMonthOfDays = getMonthDays();
      mDayPicker.refreshCount(getMonthDays());
    }
    mDayPicker.setCurrentItem(mDay - 1, paramBoolean);
    reorderPickers(mMonths);
  }
  
  class DateAdapter
    implements ScrollTextView.IDataAdapter
  {
    static final int SET_DAY = 3;
    static final int SET_MONTH = 2;
    static final int SET_YEAR = 1;
    int mType = 0;
    
    DateAdapter(int paramInt)
    {
      mType = paramInt;
    }
    
    public String getItemText(int paramInt)
    {
      switch (mType)
      {
      default: 
      case 1: 
      case 2: 
        do
        {
          return null;
          return String.valueOf(mStartYear + paramInt);
          if (isLunar) {
            return DatePicker.this.getLunarMonths(paramInt);
          }
        } while (paramInt >= mMonths.length);
        return mMonths[paramInt];
      }
      if (isLunar) {
        return DatePicker.this.getLunarDays(paramInt);
      }
      return String.valueOf(paramInt + 1);
    }
    
    public void onChanged(View paramView, int paramInt1, int paramInt2)
    {
      paramInt1 = DatePicker.this.getMonthDays();
      int i = DatePicker.this.getYearMonths();
      switch (mType)
      {
      default: 
        return;
      case 1: 
        DatePicker.access$702(DatePicker.this, mStartYear + paramInt2);
        if ((i != DatePicker.this.getYearMonths()) && (mMonthPicker != null))
        {
          paramInt2 = DatePicker.this.getYearMonths();
          mMonthPicker.refreshCount(paramInt2);
          if (paramInt2 - 1 < mMonth)
          {
            DatePicker.access$1002(DatePicker.this, paramInt1);
            DatePicker.access$902(DatePicker.this, paramInt2 - 1);
            mMonthPicker.setCurrentItem(mMonth, true);
          }
        }
        if ((paramInt1 != DatePicker.this.getMonthDays()) && (mDayPicker != null))
        {
          paramInt1 = DatePicker.this.getMonthDays();
          mDayPicker.refreshCount(paramInt1);
          if (paramInt1 < mDay)
          {
            DatePicker.access$1002(DatePicker.this, paramInt1);
            mDayPicker.setCurrentItem(mDay - 1, true);
          }
        }
        break;
      }
      for (;;)
      {
        if (mOnDateChangedListener != null) {
          mOnDateChangedListener.onDateChanged(DatePicker.this, mYear, mMonth, mDay);
        }
        DatePicker.this.sendAccessibilityEvent();
        return;
        DatePicker.access$902(DatePicker.this, paramInt2);
        if ((paramInt1 != DatePicker.this.getMonthDays()) && (mDayPicker != null))
        {
          paramInt1 = DatePicker.this.getMonthDays();
          mDayPicker.refreshCount(paramInt1);
          if (paramInt1 < mDay)
          {
            DatePicker.access$1002(DatePicker.this, paramInt1);
            mDayPicker.setCurrentItem(mDay - 1, true);
            continue;
            DatePicker.access$1002(DatePicker.this, paramInt2 + 1);
          }
        }
      }
    }
  }
  
  public static abstract interface OnDateChangedListener
  {
    public abstract void onDateChanged(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3);
  }
  
  static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new DatePicker.SavedState.1();
    private final int mDay;
    private final int mMonth;
    private final int mYear;
    
    private SavedState(Parcel paramParcel)
    {
      super();
      mYear = paramParcel.readInt();
      mMonth = paramParcel.readInt();
      mDay = paramParcel.readInt();
    }
    
    private SavedState(Parcelable paramParcelable, int paramInt1, int paramInt2, int paramInt3)
    {
      super();
      mYear = paramInt1;
      mMonth = paramInt2;
      mDay = paramInt3;
    }
    
    public int getDay()
    {
      return mDay;
    }
    
    public int getMonth()
    {
      return mMonth;
    }
    
    public int getYear()
    {
      return mYear;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeInt(mYear);
      paramParcel.writeInt(mMonth);
      paramParcel.writeInt(mDay);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.DatePicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */