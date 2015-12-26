package com.meizu.common.util;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.Time;
import com.meizu.common.R.string;

public class DateTimeUtils
{
  public static final int FORMAT_TYPE_APP_VERSIONS = 7;
  public static final int FORMAT_TYPE_CALENDAR_APPWIDGET = 8;
  public static final int FORMAT_TYPE_CALL_LOGS = 5;
  public static final int FORMAT_TYPE_CALL_LOGS_NEW = 11;
  public static final int FORMAT_TYPE_CONTACTS_BIRTHDAY_MD = 10;
  public static final int FORMAT_TYPE_CONTACTS_BIRTHDAY_YMD = 9;
  public static final int FORMAT_TYPE_EMAIL = 2;
  public static final int FORMAT_TYPE_MMS = 1;
  public static final int FORMAT_TYPE_NORMAL = 0;
  public static final int FORMAT_TYPE_PERSONAL_FOOTPRINT = 6;
  public static final int FORMAT_TYPE_RECORDER = 3;
  public static final int FORMAT_TYPE_RECORDER_PHONE = 4;
  private static String FormatResultLast = null;
  private static int FormatTypeLast = 0;
  private static final int MILLISECONDS_OF_HOUR = 3600000;
  private static long NowMillisLast = 0L;
  private static Time NowTimeLast;
  private static Time ThenTimeLast;
  
  static
  {
    FormatTypeLast = -1;
  }
  
  public static String formatTimeStampString(Context paramContext, long paramLong, int paramInt)
  {
    Time localTime2 = new Time();
    localTime2.set(paramLong);
    Long localLong = Long.valueOf(System.currentTimeMillis());
    boolean bool = DateFormat.is24HourFormat(paramContext);
    int m;
    label39:
    int i;
    label52:
    Time localTime1;
    label129:
    int j;
    label169:
    int k;
    label209:
    int n;
    label244:
    int i1;
    label273:
    int i2;
    label294:
    int i3;
    if (paramInt == FormatTypeLast)
    {
      m = 1;
      FormatTypeLast = paramInt;
      if (NowTimeLast != null) {
        break label428;
      }
      i = 0;
      if (i != 0) {
        break label468;
      }
      localTime1 = new Time();
      localTime1.set(localLong.longValue());
      NowTimeLast = localTime1;
      NowMillisLast = localLong.longValue() - (hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000);
      j = 0;
      if (ThenTimeLast != null)
      {
        if ((ThenTimeLastyear != year) || (ThenTimeLastyearDay != yearDay)) {
          break label476;
        }
        j = 1;
      }
      k = 0;
      if (ThenTimeLast != null)
      {
        if ((ThenTimeLastyear != year) || (ThenTimeLastmonth != month)) {
          break label482;
        }
        k = 1;
      }
      ThenTimeLast = localTime2;
      i4 = yearDay;
      int i5 = weekDay;
      if (year > year) {
        break label488;
      }
      n = 1;
      if ((year != year) || (yearDay > yearDay)) {
        break label494;
      }
      i1 = 1;
      if ((i1 == 0) || (yearDay != yearDay)) {
        break label500;
      }
      i2 = 1;
      if ((i1 == 0) || (yearDay != yearDay - 1)) {
        break label506;
      }
      i3 = 1;
      label317:
      if ((i1 == 0) || (yearDay < i4 - i5) || (yearDay >= yearDay)) {
        break label512;
      }
    }
    label428:
    label468:
    label476:
    label482:
    label488:
    label494:
    label500:
    label506:
    label512:
    for (int i4 = 1;; i4 = 0)
    {
      paramContext = paramContext.getResources();
      switch (paramInt)
      {
      default: 
        return null;
        m = 0;
        break label39;
        if ((localLong.longValue() >= NowMillisLast) && (localLong.longValue() < NowMillisLast + 86400000L))
        {
          i = 1;
          break label52;
        }
        i = 0;
        break label52;
        localTime1 = NowTimeLast;
        break label129;
        j = 0;
        break label169;
        k = 0;
        break label209;
        n = 0;
        break label244;
        i1 = 0;
        break label273;
        i2 = 0;
        break label294;
        i3 = 0;
        break label317;
      }
    }
    if (i2 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute_12));
    }
    if (i4 != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_week));
      return FormatResultLast;
    }
    if (i1 != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
      return FormatResultLast;
    }
    if (n != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
      return FormatResultLast;
    }
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
    return FormatResultLast;
    if (i2 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute_12));
    }
    if (i4 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_week_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_week_hour_minute_12));
    }
    if (i1 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day_hour_minute_12));
    }
    if (n != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
      return FormatResultLast;
    }
    if (bool) {
      return localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute));
    }
    return localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute_12));
    if (i1 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_week_month_day_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_week_month_day_hour_minute_12));
    }
    if (n != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
      return FormatResultLast;
    }
    return localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute));
    if (i2 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute_12));
    }
    if (i1 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day_hour_minute_12));
    }
    if (n != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
      return FormatResultLast;
    }
    if (bool) {
      return localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute));
    }
    return localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute_12));
    if (i2 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute_12));
    }
    if (i1 != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
      return FormatResultLast;
    }
    if (n != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
      return FormatResultLast;
    }
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
    return FormatResultLast;
    if (i2 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute_12));
    }
    if (i4 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_week_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_week_hour_minute_12));
    }
    if (i1 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day_hour_minute_12));
    }
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    if (bool) {}
    for (FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute));; FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day_hour_minute_12))) {
      return FormatResultLast;
    }
    if (i2 != 0)
    {
      paramLong = localLong.longValue() - paramLong;
      if (paramLong >= 3600000L)
      {
        paramInt = (int)paramLong / 60 / 60 / 1000;
        if (paramInt == 1) {
          return paramContext.getString(R.string.mc_pattern_a_hour_before);
        }
        return paramContext.getString(R.string.mc_pattern_hour_before).replace(",", String.valueOf(paramInt));
      }
      paramInt = (int)paramLong / 60 / 1000;
      if (paramInt <= 1) {
        return paramContext.getString(R.string.mc_pattern_a_minute_before);
      }
      return paramContext.getString(R.string.mc_pattern_minute_before).replace(",", String.valueOf(paramInt));
    }
    if (i3 != 0) {
      return paramContext.getString(R.string.mc_pattern_yesterday);
    }
    if (i1 != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
      return FormatResultLast;
    }
    if (n != 0)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
      return FormatResultLast;
    }
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
    return FormatResultLast;
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    if (i1 != 0)
    {
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
    return FormatResultLast;
    if (year == year)
    {
      if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
        return FormatResultLast;
      }
      FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
      return FormatResultLast;
    }
    if ((i != 0) && (k != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month));
    return FormatResultLast;
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_year_month_day));
    return FormatResultLast;
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
    return FormatResultLast;
    if (i2 != 0)
    {
      if (bool) {
        return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute));
      }
      return localTime2.format(paramContext.getString(R.string.mc_pattern_hour_minute_12));
    }
    if (i4 != 0) {
      return localTime2.format(paramContext.getString(R.string.mc_pattern_week));
    }
    if (i1 != 0) {
      return localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
    }
    if ((i != 0) && (j != 0) && (m != 0) && (!TextUtils.isEmpty(FormatResultLast))) {
      return FormatResultLast;
    }
    FormatResultLast = localTime2.format(paramContext.getString(R.string.mc_pattern_month_day));
    return FormatResultLast;
  }
  
  public static String formatTimeStampString(Context paramContext, long paramLong, int paramInt, boolean paramBoolean)
  {
    return formatTimeStampString(paramContext, paramLong, paramInt);
  }
  
  public static String formatTimeStampString(Context paramContext, long paramLong, boolean paramBoolean)
  {
    return formatTimeStampString(paramContext, paramLong, 0, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.DateTimeUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */