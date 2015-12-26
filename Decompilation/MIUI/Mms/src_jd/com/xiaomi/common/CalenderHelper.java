package com.xiaomi.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalenderHelper
{
  private static Date baseDate = null;
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
  static final long[] lunarInfo = { 19416L, 19168L, 42352L, 21717L, 53856L, 55632L, 91476L, 22176L, 39632L, 21970L, 19168L, 42422L, 42192L, 53840L, 119381L, 46400L, 54944L, 44450L, 38320L, 84343L, 18800L, 42160L, 46261L, 27216L, 27968L, 109396L, 11104L, 38256L, 21234L, 18800L, 25958L, 54432L, 59984L, 28309L, 23248L, 11104L, 100067L, 37600L, 116951L, 51536L, 54432L, 120998L, 46416L, 22176L, 107956L, 9680L, 37584L, 53938L, 43344L, 46423L, 27808L, 46416L, 86869L, 19872L, 42448L, 83315L, 21200L, 43432L, 59728L, 27296L, 44710L, 43856L, 19296L, 43748L, 42352L, 21088L, 62051L, 55632L, 23383L, 22176L, 38608L, 19925L, 19152L, 42192L, 54484L, 53840L, 54616L, 46400L, 46496L, 103846L, 38320L, 18864L, 43380L, 42160L, 45690L, 27216L, 27968L, 44870L, 43872L, 38256L, 19189L, 18800L, 25776L, 29859L, 59984L, 27480L, 21952L, 43872L, 38613L, 37600L, 51552L, 55636L, 54432L, 55888L, 30034L, 22176L, 43959L, 9680L, 37584L, 51893L, 43344L, 46240L, 47780L, 44368L, 21977L, 19360L, 42416L, 86390L, 21168L, 43312L, 31060L, 27296L, 44368L, 23378L, 19296L, 42726L, 42208L, 53856L, 60005L, 54576L, 23200L, 30371L, 38608L, 19415L, 19152L, 42192L, 118966L, 53840L, 54560L, 56645L, 46496L, 22224L, 21938L, 18864L, 42359L, 42160L, 43600L, 111189L, 27936L, 44448L };
  
  private static boolean check(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    throws ParseException
  {
    paramInt1 = getLunarDate(paramInt1, paramInt2, paramInt3);
    paramInt2 = paramInt1 / 10000;
    paramInt3 = paramInt1 % 10000 / 100;
    return (paramInt2 == paramInt4) && (paramInt3 == paramInt5) && (paramInt1 % 100 == paramInt6);
  }
  
  private static int compare(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    throws ParseException
  {
    paramInt1 = getLunarDate(paramInt1, paramInt2, paramInt3);
    if (paramInt1 == paramInt4 * 10000 + paramInt5 * 100 + paramInt6) {
      return 0;
    }
    return (int)((dateFormat.parse(paramInt1 / 10000 + "年" + paramInt1 % 10000 / 100 + "月" + paramInt1 % 100 + "日").getTime() - dateFormat.parse(paramInt4 + "年" + paramInt5 + "月" + paramInt6 + "日").getTime()) / 86400000L);
  }
  
  public static int getLunarDate(int paramInt1, int paramInt2, int paramInt3)
    throws ParseException
  {
    if (baseDate == null) {
      baseDate = dateFormat.parse("1900年1月31日");
    }
    int j = (int)((dateFormat.parse(paramInt1 + "年" + paramInt2 + "月" + paramInt3 + "日").getTime() - baseDate.getTime()) / 86400000L);
    paramInt1 = 14;
    int i = 1900;
    int m = 0;
    int k;
    int i1;
    for (;;)
    {
      if ((i >= 2050) || (j <= 0))
      {
        k = i;
        paramInt2 = paramInt1;
        paramInt3 = j;
        if (j < 0)
        {
          paramInt3 = j + m;
          k = i - 1;
          paramInt2 = paramInt1 - 12;
        }
        i1 = leapMonth(k);
        j = 0;
        paramInt1 = 1;
        i = 0;
        if ((paramInt1 < 13) && (paramInt3 > 0)) {
          break;
        }
        if ((paramInt3 != 0) || (i1 <= 0) || (paramInt1 != i1 + 1)) {
          break label350;
        }
        if (i == 0) {
          break label339;
        }
        label177:
        i = paramInt1;
        paramInt2 = paramInt3;
        if (paramInt3 < 0)
        {
          paramInt2 = paramInt3 + j;
          i = paramInt1 - 1;
        }
        return k * 10000 + i * 100 + (paramInt2 + 1);
      }
      m = yearDays(i);
      j -= m;
      paramInt1 += 12;
      i += 1;
    }
    if ((i1 > 0) && (paramInt1 == i1 + 1) && (i == 0))
    {
      i = leapDays(k);
      j = paramInt1 - 1;
      paramInt1 = 1;
      label265:
      if ((paramInt1 == 0) || (j != i1 + 1)) {
        break label353;
      }
      paramInt1 = 0;
    }
    label339:
    label350:
    label353:
    for (;;)
    {
      m = paramInt2;
      if (paramInt1 == 0) {
        m = paramInt2 + 1;
      }
      j += 1;
      int n = paramInt1;
      paramInt3 -= i;
      paramInt2 = m;
      paramInt1 = j;
      j = i;
      i = n;
      break;
      m = monthDays(k, paramInt1);
      j = paramInt1;
      paramInt1 = i;
      i = m;
      break label265;
      paramInt2 -= 1;
      paramInt1 -= 1;
      break label177;
      break label177;
    }
  }
  
  public static List<int[]> getSunDate(int paramInt1, int paramInt2, int paramInt3)
    throws ParseException
  {
    int i = 2014;
    int j = 12;
    Date localDate;
    ArrayList localArrayList;
    for (int k = 10;; k = localDate.getDate())
    {
      m = compare(i, j, k, paramInt1, paramInt2, paramInt3);
      if (Math.abs(m) < 3)
      {
        localDate = dateFormat.parse(i + "年" + j + "月" + k + "日");
        localDate.setTime(localDate.getTime() - 3024000000L);
        localArrayList = new ArrayList();
        i = 0;
        if (i < 70) {
          break;
        }
        label115:
        return localArrayList;
      }
      localDate = new Date(dateFormat.parse(i + "年" + j + "月" + k + "日").getTime() - m * 86400000L);
      i = localDate.getYear() + 1900;
      j = localDate.getMonth() + 1;
    }
    j = localDate.getYear() + 1900;
    k = localDate.getMonth() + 1;
    int m = localDate.getDate();
    int n = compare(j, k, m, paramInt1, paramInt2, paramInt3);
    if (n < -3) {
      if (n < -30)
      {
        j = n + 6;
        label277:
        localDate.setTime(localDate.getTime() - j * 86400000L);
        i -= j;
      }
    }
    for (;;)
    {
      i += 1;
      break;
      j = n + 3;
      break label277;
      if ((n > 0) && (n < 5))
      {
        i += 25;
        localDate.setTime(localDate.getTime() + 2160000000L);
      }
      else
      {
        if (n > 32) {
          break label115;
        }
        if ((n == 0) && (check(j, k, m, paramInt1, paramInt2, paramInt3))) {
          localArrayList.add(new int[] { j, k, m });
        }
        localDate.setTime(localDate.getTime() + 86400000L);
      }
    }
  }
  
  private static final int leapDays(int paramInt)
  {
    if (leapMonth(paramInt) != 0)
    {
      if ((lunarInfo[(paramInt - 1900)] & 0x10000) != 0L) {
        return 30;
      }
      return 29;
    }
    return 0;
  }
  
  private static final int leapMonth(int paramInt)
  {
    return (int)(lunarInfo[(paramInt - 1900)] & 0xF);
  }
  
  private static final int monthDays(int paramInt1, int paramInt2)
  {
    if ((lunarInfo[(paramInt1 - 1900)] & 65536 >> paramInt2) == 0L) {
      return 29;
    }
    return 30;
  }
  
  private static final int yearDays(int paramInt)
  {
    int j = 348;
    int i = 32768;
    for (;;)
    {
      if (i <= 8) {
        return j + leapDays(paramInt);
      }
      int k = j;
      if ((lunarInfo[(paramInt - 1900)] & i) != 0L) {
        k = j + 1;
      }
      i >>= 1;
      j = k;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.CalenderHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */