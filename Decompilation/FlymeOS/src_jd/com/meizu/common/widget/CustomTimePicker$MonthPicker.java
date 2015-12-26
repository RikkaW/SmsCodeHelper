package com.meizu.common.widget;

import android.content.res.Resources;
import android.text.format.DateUtils;
import android.view.View;
import com.meizu.common.R.array;
import com.meizu.common.R.string;
import com.meizu.common.util.LunarCalendar;
import java.util.Calendar;
import java.util.Locale;

class CustomTimePicker$MonthPicker
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
  
  public CustomTimePicker$MonthPicker(CustomTimePicker paramCustomTimePicker, ScrollTextView paramScrollTextView)
  {
    picker = paramScrollTextView;
    mMonths = getShortMonths();
    calculateValidDateField();
  }
  
  private void calculateValidDateField()
  {
    CustomTimePicker.access$2400(this$0).setTimeInMillis(System.currentTimeMillis());
    startDate[0] = CustomTimePicker.access$2400(this$0).get(1);
    startDate[1] = (CustomTimePicker.access$2400(this$0).get(2) + 1);
    startDate[2] = CustomTimePicker.access$2400(this$0).get(5);
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
      endDate[2] = CustomTimePicker.access$2100(this$0, endDate[0], endDate[1], false);
      endDate[3] = 0;
      startLunarDate = LunarCalendar.solarToLunar(startDate[0], startDate[1], startDate[2]);
      endLunarDate = LunarCalendar.solarToLunar(endDate[0], endDate[1], endDate[2]);
      if (startLunarDate[0] != endLunarDate[0]) {
        break label286;
      }
      lunarMonthsIn1stYear = (endLunarDate[1] - startLunarDate[1] + 1);
      lunarMonthsIn2ndYear = 0;
      CustomTimePicker.access$1802(this$0, lunarMonthsIn1stYear + lunarMonthsIn2ndYear);
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
    CustomTimePicker.access$1802(this$0, lunarMonthsIn1stYear + lunarMonthsIn2ndYear);
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
    String[] arrayOfString = this$0.getResources().getStringArray(R.array.mc_custom_time_picker_lunar_month);
    String str = this$0.getResources().getString(R.string.mc_time_picker_leap);
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
    if ((localLocale.equals(CustomTimePicker.access$2500(this$0))) && (CustomTimePicker.access$2600(this$0) != null)) {
      return CustomTimePicker.access$2600(this$0);
    }
    synchronized (CustomTimePicker.access$2700(this$0))
    {
      if (!localLocale.equals(CustomTimePicker.access$2500(this$0)))
      {
        CustomTimePicker.access$2602(this$0, new String[12]);
        int i = 0;
        while (i < 12)
        {
          CustomTimePicker.access$2600(this$0)[i] = DateUtils.getMonthString(i + 0, 20);
          i += 1;
        }
        if (CustomTimePicker.access$2600(this$0)[0].startsWith("1"))
        {
          i = j;
          while (i < CustomTimePicker.access$2600(this$0).length)
          {
            CustomTimePicker.access$2600(this$0)[i] = String.valueOf(i + 1);
            i += 1;
          }
        }
        CustomTimePicker.access$2502(this$0, localLocale);
      }
      return CustomTimePicker.access$2600(this$0);
    }
  }
  
  public int getCurrentItem()
  {
    return picker.getCurrentItem() - CustomTimePicker.access$2300(this$0) / 2;
  }
  
  public String getItemText(int paramInt)
  {
    if (CustomTimePicker.access$600(this$0))
    {
      paramInt -= CustomTimePicker.access$2300(this$0) / 2;
      if ((paramInt < 0) || (paramInt > CustomTimePicker.access$1800(this$0) - 1)) {
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
    if ((paramInt < CustomTimePicker.access$2300(this$0) / 2) || (paramInt > CustomTimePicker.access$2300(this$0) / 2 + 11)) {
      return "";
    }
    int i = startDate[1];
    int j = CustomTimePicker.access$2300(this$0) / 2;
    return mMonths[((i - 1 + paramInt - j) % 12)];
  }
  
  public int getMonth(int[] paramArrayOfInt)
  {
    int i = getCurrentItem();
    int j;
    if (CustomTimePicker.access$600(this$0)) {
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
    int i = CustomTimePicker.access$2100(this$0, CustomTimePicker.access$1900(this$0), CustomTimePicker.access$2000(this$0), CustomTimePicker.access$600(this$0));
    paramInt1 = paramInt2 - CustomTimePicker.access$2300(this$0) / 2;
    if (CustomTimePicker.access$600(this$0)) {
      if (paramInt1 >= lunarMonthsIn1stYear)
      {
        CustomTimePicker.access$1902(this$0, endLunarDate[0]);
        if (!CustomTimePicker.access$600(this$0)) {
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
      CustomTimePicker.access$2002(this$0, paramInt1);
      if ((i != CustomTimePicker.access$2100(this$0, CustomTimePicker.access$1900(this$0), CustomTimePicker.access$2000(this$0), CustomTimePicker.access$600(this$0))) && (CustomTimePicker.access$2200(this$0) != null))
      {
        paramInt1 = CustomTimePicker.access$2100(this$0, CustomTimePicker.access$1900(this$0), CustomTimePicker.access$2000(this$0), CustomTimePicker.access$600(this$0));
        CustomTimePicker.access$2200(this$0).refreshCount(paramInt1);
      }
      setDayPickerValidField(CustomTimePicker.access$2200(this$0).getCurrentItem() + 1);
      return;
      CustomTimePicker.access$1902(this$0, startLunarDate[0]);
      break;
      if (paramInt1 > 12 - startDate[1])
      {
        CustomTimePicker.access$1902(this$0, endDate[0]);
        break;
      }
      CustomTimePicker.access$1902(this$0, startDate[0]);
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
    picker.refreshCountAndCurrent(CustomTimePicker.access$2300(this$0) / 2 * 2 + paramInt1, CustomTimePicker.access$2300(this$0) / 2 + paramInt2);
  }
  
  public void setData(ScrollTextView.IDataAdapter paramIDataAdapter, float paramFloat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, boolean paramBoolean)
  {
    startIndex = paramInt4;
    endIndex = (paramInt3 / 2 * 2 + paramInt5 + 1);
    picker.setData(this, paramFloat, paramInt1, paramInt2 + paramInt3 / 2 * 2, paramInt3, startIndex, endIndex, paramBoolean);
  }
  
  public void setDayPickerValidField(int paramInt)
  {
    int i = CustomTimePicker.access$2100(this$0, CustomTimePicker.access$1900(this$0), CustomTimePicker.access$2000(this$0), CustomTimePicker.access$600(this$0));
    if (CustomTimePicker.access$600(this$0)) {
      if ((CustomTimePicker.access$1900(this$0) == startLunarDate[0]) && (CustomTimePicker.access$2000(this$0) == startLunarDate[1]))
      {
        CustomTimePicker.access$2200(this$0).setValidStart(startLunarDate[2] - 1);
        if ((CustomTimePicker.access$1900(this$0) != endLunarDate[0]) || (CustomTimePicker.access$2000(this$0) != endLunarDate[1])) {
          break label262;
        }
        CustomTimePicker.access$2200(this$0).setValidEnd(endLunarDate[2] - 1);
      }
    }
    for (;;)
    {
      CustomTimePicker.access$302(this$0, paramInt);
      CustomTimePicker.access$302(this$0, Math.min(CustomTimePicker.access$300(this$0), i));
      CustomTimePicker.access$302(this$0, Math.min(CustomTimePicker.access$300(this$0), CustomTimePicker.access$2200(this$0).getValidEnd() + 1));
      CustomTimePicker.access$302(this$0, Math.max(CustomTimePicker.access$300(this$0), CustomTimePicker.access$2200(this$0).getValidStart() + 1));
      CustomTimePicker.access$2200(this$0).setCurrentItem(CustomTimePicker.access$300(this$0) - 1, true);
      return;
      CustomTimePicker.access$2200(this$0).setValidStart(0);
      break;
      label262:
      CustomTimePicker.access$2200(this$0).setValidEnd(i - 1);
      continue;
      if ((CustomTimePicker.access$1900(this$0) == startDate[0]) && (CustomTimePicker.access$2000(this$0) == startDate[1])) {
        CustomTimePicker.access$2200(this$0).setValidStart(startDate[2] - 1);
      }
      for (;;)
      {
        if ((CustomTimePicker.access$1900(this$0) != endDate[0]) || (CustomTimePicker.access$2000(this$0) != endDate[1])) {
          break label395;
        }
        CustomTimePicker.access$2200(this$0).setValidEnd(endDate[2] - 1);
        break;
        CustomTimePicker.access$2200(this$0).setValidStart(0);
      }
      label395:
      CustomTimePicker.access$2200(this$0).setValidEnd(i - 1);
    }
  }
  
  public void setMonth(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (paramInt2 < 0) {
      return;
    }
    CustomTimePicker.access$1702(this$0, paramBoolean);
    if (CustomTimePicker.access$600(this$0)) {
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
        refreshCountAndCurrent(CustomTimePicker.access$1800(this$0), paramInt1 - startLunarDate[1]);
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
        refreshCountAndCurrent(CustomTimePicker.access$1800(this$0), lunarMonthsIn1stYear + paramInt1 - 1);
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

/* Location:
 * Qualified Name:     com.meizu.common.widget.CustomTimePicker.MonthPicker
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */