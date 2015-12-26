package com.meizu.common.widget;

import android.content.Context;
import android.text.TextUtils;
import com.meizu.common.R.string;
import java.text.DateFormatSymbols;
import java.util.Locale;

final class WeekdayPicker$DaysOfWeek
{
  private static int[] DAY_MAP = { 2, 3, 4, 5, 6, 7, 1 };
  private int mDays;
  
  public WeekdayPicker$DaysOfWeek(int paramInt)
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

/* Location:
 * Qualified Name:     com.meizu.common.widget.WeekdayPicker.DaysOfWeek
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */