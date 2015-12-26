package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.meizu.common.R.color;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;
import com.meizu.common.R.string;
import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

public class WeekdayPicker
  extends LinearLayout
{
  static final int DIFF_FRIDAY = 3;
  static final int DIFF_MONDAY = 0;
  static final int DIFF_SATURDAY = 2;
  static final int DIFF_SUNDAY = 1;
  static final int DIFF_THURSDAY = 4;
  static final int DIFF_TUESDAY = 6;
  static final int DIFF_WEDNESDAY = 5;
  static final int FIXED_DIFF_NORMAL_DAY = 0;
  static final String FIXED_WEEK_START_NORMAL_DAY = "-1";
  public static final int FRIDAY = 16;
  static final int HEIGHT_DELAY = 32;
  static final String IMG_SELECTED_TAG = "selected";
  static final String IMG_UNSELECTED_TAG = "unselected";
  public static final int MONDAY = 1;
  public static final int NO_DAY = 0;
  static final int PADDING_SIZE = 12;
  public static final int SATURDAY = 32;
  static final int SQUARE_WIDTH = 64;
  public static final int SUNDAY = 64;
  public static final int THURSDAY = 8;
  static final int TOTAL_ITEM_COUNT = 7;
  public static final int TUESDAY = 2;
  public static final int WEDNESDAY = 4;
  static final String WEEK_START_FRIDAY = "6";
  static final String WEEK_START_MONDAY = "2";
  static final String WEEK_START_NORMAL = "-1";
  static final String WEEK_START_NULL = "-2";
  static final String WEEK_START_SATURDAY = "7";
  static final String WEEK_START_SUNDAY = "1";
  static final String WEEK_START_THURSDAY = "5";
  static final String WEEK_START_TUESDAY = "3";
  static final String WEEK_START_WEDNESDAY = "4";
  private ImageView[] mBackgrouds;
  private Context mContext;
  private int mDiffWeekStart = 0;
  private int mHeightDelay = -1;
  private int mLastChangedIndex = -1;
  private int mLastLastChangedIndex = -1;
  private boolean mOutSide = false;
  private DaysOfWeek mRepeatData;
  private OnSelectChangedListener mSelectChangedListener = null;
  private int mSquareWidth = -1;
  private TextView[] mTexts;
  private String mWeekStart = "-1";
  
  public WeekdayPicker(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    initView();
  }
  
  public WeekdayPicker(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
    initView();
  }
  
  public WeekdayPicker(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    initView();
  }
  
  private int currentTouchIndex(float paramFloat)
  {
    float f1 = getWidth() / 7;
    float f2 = (f1 - mSquareWidth) / 2.0F;
    int i = (int)(paramFloat / f1);
    if (i >= 7) {}
    do
    {
      return -1;
      int j = i - mDiffWeekStart;
      i = j;
      if (j < 0) {
        i = j + 7;
      }
    } while ((paramFloat % f1 < f2) || (paramFloat % f1 - f2 > mSquareWidth));
    return i;
  }
  
  private void initView()
  {
    mSquareWidth = mContext.getResources().getDimensionPixelSize(R.dimen.mc_chooser_item_width);
    mHeightDelay = (mSquareWidth / 2);
    mBackgrouds = new ImageView[7];
    mTexts = new TextView[7];
    String str1 = getResources().getString(R.string.mc_monday);
    String str2 = getResources().getString(R.string.mc_tuesday);
    String str3 = getResources().getString(R.string.mc_wednesday);
    String str4 = getResources().getString(R.string.mc_thursday);
    String str5 = getResources().getString(R.string.mc_friday);
    String str6 = getResources().getString(R.string.mc_saturday);
    String str7 = getResources().getString(R.string.mc_sunday);
    label170:
    int j;
    label226:
    View localView;
    TextView localTextView;
    ImageView localImageView;
    if ("-2".equals(mWeekStart))
    {
      mWeekStart = String.valueOf(Calendar.getInstance().getFirstDayOfWeek());
      if (!"1".equals(mWeekStart)) {
        break label453;
      }
      mDiffWeekStart = 1;
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
      weight = 1.0F;
      gravity = 17;
      if (mRepeatData == null) {
        mRepeatData = new DaysOfWeek(0);
      }
      boolean[] arrayOfBoolean = mRepeatData.getBooleanArray();
      j = 0;
      int i = 0;
      if (j >= 7) {
        return;
      }
      localView = LayoutInflater.from(mContext).inflate(R.layout.mc_weekday_picker_item, null);
      localView.setLayoutParams(localLayoutParams);
      localTextView = (TextView)localView.findViewById(R.id.mc_chooser_text);
      localTextView.setText(new String[] { str1, str2, str3, str4, str5, str6, str7 }[j]);
      localImageView = (ImageView)localView.findViewById(R.id.mc_background_img);
      if (arrayOfBoolean == null) {
        break label657;
      }
      if (arrayOfBoolean[j] == 0) {
        break label585;
      }
      localImageView.setTag("selected");
      if (!isEnabled()) {
        break label574;
      }
      localImageView.setBackgroundResource(R.drawable.mc_bg_week_switch_on);
      label362:
      localTextView.setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_selected));
      label380:
      mBackgrouds[j] = localImageView;
      mTexts[j] = localTextView;
      if (mDiffWeekStart + j < 7) {
        break label693;
      }
      addView(localView, i);
      i += 1;
    }
    for (;;)
    {
      j += 1;
      break label226;
      if (!"-1".equals(mWeekStart)) {
        break;
      }
      mWeekStart = String.valueOf(Calendar.getInstance().getFirstDayOfWeek());
      break;
      label453:
      if ("2".equals(mWeekStart))
      {
        mDiffWeekStart = 0;
        break label170;
      }
      if ("3".equals(mWeekStart))
      {
        mDiffWeekStart = 6;
        break label170;
      }
      if ("4".equals(mWeekStart))
      {
        mDiffWeekStart = 5;
        break label170;
      }
      if ("5".equals(mWeekStart))
      {
        mDiffWeekStart = 4;
        break label170;
      }
      if ("6".equals(mWeekStart))
      {
        mDiffWeekStart = 3;
        break label170;
      }
      if (!"7".equals(mWeekStart)) {
        break label170;
      }
      mDiffWeekStart = 2;
      break label170;
      label574:
      localImageView.setBackgroundResource(R.drawable.mc_bg_week_switch_on_disable);
      break label362;
      label585:
      localImageView.setTag("unselected");
      if (isEnabled())
      {
        localImageView.setBackgroundResource(R.drawable.mc_bg_week_switch_off);
        localTextView.setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_unselected));
        break label380;
      }
      localImageView.setBackgroundResource(R.drawable.mc_bg_week_switch_off_disable);
      localTextView.setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_unselected_disable));
      break label380;
      label657:
      localImageView.setTag("unselected");
      localImageView.setBackgroundResource(R.drawable.mc_bg_week_switch_off);
      localTextView.setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_unselected));
      break label380;
      label693:
      addView(localView);
    }
  }
  
  private void onBackgoundSelected(int paramInt, boolean paramBoolean)
  {
    if ((mBackgrouds == null) || (paramInt < 0) || (paramInt >= mBackgrouds.length) || (mBackgrouds[paramInt] == null) || (mRepeatData == null)) {
      return;
    }
    if ((paramBoolean) && (mLastLastChangedIndex == paramInt) && (mLastLastChangedIndex >= 0) && (mLastLastChangedIndex < mBackgrouds.length) && (mBackgrouds[mLastChangedIndex] != null))
    {
      if (!"selected".equals(mBackgrouds[mLastChangedIndex].getTag())) {
        break label251;
      }
      mBackgrouds[mLastChangedIndex].setTag("unselected");
      mBackgrouds[mLastChangedIndex].setBackgroundResource(R.drawable.mc_bg_week_switch_off);
      mTexts[mLastChangedIndex].setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_unselected));
      mRepeatData.set(mLastChangedIndex, false);
    }
    for (;;)
    {
      mLastLastChangedIndex = mLastChangedIndex;
      mLastChangedIndex = paramInt;
      if (!"selected".equals(mBackgrouds[paramInt].getTag())) {
        break;
      }
      mBackgrouds[paramInt].setTag("unselected");
      mBackgrouds[paramInt].setBackgroundResource(R.drawable.mc_bg_week_switch_off);
      mTexts[paramInt].setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_unselected));
      mRepeatData.set(paramInt, false);
      return;
      label251:
      mBackgrouds[mLastChangedIndex].setTag("selected");
      mBackgrouds[mLastChangedIndex].setBackgroundResource(R.drawable.mc_bg_week_switch_on);
      mTexts[mLastChangedIndex].setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_selected));
      mRepeatData.set(mLastChangedIndex, true);
    }
    mBackgrouds[paramInt].setTag("selected");
    mBackgrouds[paramInt].setBackgroundResource(R.drawable.mc_bg_week_switch_on);
    mTexts[paramInt].setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_selected));
    mRepeatData.set(paramInt, true);
  }
  
  private void updateView()
  {
    if ((mRepeatData == null) || (mBackgrouds == null)) {
      return;
    }
    boolean[] arrayOfBoolean = mRepeatData.getBooleanArray();
    int i = 0;
    label25:
    if (i < 7)
    {
      if (arrayOfBoolean[i] == 0) {
        break label89;
      }
      mBackgrouds[i].setTag("selected");
      mBackgrouds[i].setBackgroundResource(R.drawable.mc_bg_week_switch_on);
      mTexts[i].setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_selected));
    }
    for (;;)
    {
      i += 1;
      break label25;
      break;
      label89:
      mBackgrouds[i].setTag("unselected");
      mBackgrouds[i].setBackgroundResource(R.drawable.mc_bg_week_switch_off);
      mTexts[i].setTextColor(mContext.getResources().getColor(R.color.mc_chooser_text_color_unselected));
    }
  }
  
  public boolean[] getSelectedArray()
  {
    return mRepeatData.getBooleanArray();
  }
  
  public int getSelectedDays()
  {
    return mRepeatData.getCoded();
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = true;
    boolean bool1 = false;
    if (!isEnabled()) {
      if ((isClickable()) || (isLongClickable())) {
        bool1 = true;
      }
    }
    do
    {
      int i;
      do
      {
        do
        {
          do
          {
            float f1;
            do
            {
              float f2;
              do
              {
                do
                {
                  do
                  {
                    return bool1;
                    i = paramMotionEvent.getAction();
                    f1 = paramMotionEvent.getX();
                    f2 = paramMotionEvent.getY();
                    paramMotionEvent = getParent();
                    switch (i)
                    {
                    default: 
                      return true;
                    case 0: 
                      if (paramMotionEvent != null) {
                        paramMotionEvent.requestDisallowInterceptTouchEvent(true);
                      }
                      mOutSide = false;
                      i = currentTouchIndex(f1);
                      bool1 = bool2;
                    }
                  } while (i < 0);
                  bool1 = bool2;
                } while (i >= 7);
                onBackgoundSelected(i, false);
                return true;
                if (!mOutSide) {
                  break;
                }
                bool1 = bool2;
              } while (paramMotionEvent == null);
              paramMotionEvent.requestDisallowInterceptTouchEvent(false);
              return true;
              if ((f1 >= -mHeightDelay) && (f1 <= getWidth() + mHeightDelay) && (f2 >= -mHeightDelay) && (f2 <= getHeight() + mHeightDelay)) {
                break;
              }
              mLastChangedIndex = -1;
              mLastLastChangedIndex = -1;
              if (mSelectChangedListener != null) {
                mSelectChangedListener.OnSelectChanged(mRepeatData.getCoded());
              }
              mOutSide = true;
              bool1 = bool2;
            } while (paramMotionEvent == null);
            paramMotionEvent.requestDisallowInterceptTouchEvent(false);
            return true;
            i = currentTouchIndex(f1);
            bool1 = bool2;
          } while (i < 0);
          bool1 = bool2;
        } while (i >= 7);
        bool1 = bool2;
      } while (i == mLastChangedIndex);
      onBackgoundSelected(i, true);
      return true;
      mOutSide = false;
      if (paramMotionEvent != null) {
        paramMotionEvent.requestDisallowInterceptTouchEvent(false);
      }
      mLastChangedIndex = -1;
      mLastLastChangedIndex = -1;
      bool1 = bool2;
    } while (mSelectChangedListener == null);
    mSelectChangedListener.OnSelectChanged(mRepeatData.getCoded());
    return true;
  }
  
  public void resetView()
  {
    removeAllViews();
    initView();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
    removeAllViews();
    initView();
  }
  
  public void setFirstDayOfWeek(int paramInt)
  {
    int i;
    if (paramInt >= 1)
    {
      i = paramInt;
      if (paramInt <= 7) {}
    }
    else
    {
      i = 1;
    }
    mWeekStart = String.valueOf(i);
    resetView();
  }
  
  public void setOnSelectChangedListener(OnSelectChangedListener paramOnSelectChangedListener)
  {
    mSelectChangedListener = paramOnSelectChangedListener;
  }
  
  public void setSelectedDays(int paramInt)
  {
    mRepeatData.setDays(paramInt);
    updateView();
  }
  
  static final class DaysOfWeek
  {
    private static int[] DAY_MAP = { 2, 3, 4, 5, 6, 7, 1 };
    private int mDays;
    
    public DaysOfWeek(int paramInt)
    {
      mDays = paramInt;
    }
    
    private boolean isSet(int paramInt)
    {
      return (mDays & 1 << paramInt) > 0;
    }
    
    public boolean[] getBooleanArray()
    {
      boolean[] arrayOfBoolean = new boolean[7];
      int i = 0;
      while (i < 7)
      {
        arrayOfBoolean[i] = isSet(i);
        i += 1;
      }
      return arrayOfBoolean;
    }
    
    public int getCoded()
    {
      return mDays;
    }
    
    public void set(int paramInt, boolean paramBoolean)
    {
      if (paramBoolean)
      {
        mDays |= 1 << paramInt;
        return;
      }
      mDays &= (1 << paramInt ^ 0xFFFFFFFF);
    }
    
    public void set(DaysOfWeek paramDaysOfWeek)
    {
      mDays = mDays;
    }
    
    public void setDays(int paramInt)
    {
      mDays = paramInt;
    }
    
    public String toString(Context paramContext, boolean paramBoolean)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      if (mDays == 0)
      {
        if (paramBoolean) {
          return paramContext.getText(R.string.mc_never).toString();
        }
        return "";
      }
      if (mDays == 127) {
        return paramContext.getText(R.string.mc_every_day).toString();
      }
      if (mDays == 31) {
        return paramContext.getText(R.string.mc_working_day).toString();
      }
      if (mDays == 96) {
        return paramContext.getText(R.string.mc_weekend).toString();
      }
      int j = mDays;
      int k;
      for (int i = 0; j > 0; i = k)
      {
        k = i;
        if ((j & 0x1) == 1) {
          k = i + 1;
        }
        j >>= 1;
      }
      String[] arrayOfString = new DateFormatSymbols().getShortWeekdays();
      j = 0;
      if (j < 7)
      {
        if ((mDays & 1 << j) == 0) {
          break label269;
        }
        String str = arrayOfString[DAY_MAP[j]];
        paramContext = str;
        if (TextUtils.equals(Locale.getDefault().getLanguage(), "zh"))
        {
          paramContext = str;
          if (localStringBuilder.length() > 0) {
            paramContext = str.substring(1);
          }
        }
        localStringBuilder.append(paramContext);
        k = i - 1;
        i = k;
        if (k > 0)
        {
          localStringBuilder.append("  ");
          i = k;
        }
      }
      label269:
      for (;;)
      {
        j += 1;
        break;
        return localStringBuilder.toString();
      }
    }
  }
  
  public static abstract interface OnSelectChangedListener
  {
    public abstract void OnSelectChanged(int paramInt);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.WeekdayPicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */