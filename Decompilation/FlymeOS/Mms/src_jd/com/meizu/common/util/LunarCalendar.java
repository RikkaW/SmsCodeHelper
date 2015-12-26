package com.meizu.common.util;

import java.security.InvalidParameterException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LunarCalendar
{
  private static final Pattern DATE_PATTERN = Pattern.compile("(19|20)[0-9]{2}-((0)?[1-9]|1[012])-((0)?[1-9]|(1|2)[0-9]|30)$");
  public static final String DATE_SEPARATOR = "-";
  private static final int[] DAYS_BEFORE_MONTH = { 0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334, 365 };
  public static final String LUNAR_DATE_REGEXP = "(19|20)[0-9]{2}-((0)?[1-9]|1[012])-((0)?[1-9]|(1|2)[0-9]|30)$";
  private static final int[] LUNAR_INFO = { 8697535, 306771, 677704, 5580477, 861776, 890180, 4631225, 354893, 634178, 2404022, 306762, 6966718, 675154, 861510, 6116026, 742478, 879171, 2714935, 613195, 7642049, 300884, 674632, 5973436, 435536, 447557, 4905656, 177741, 612162, 2398135, 300874, 6703934, 870993, 959814, 5690554, 372046, 177732, 3749688, 601675, 8165055, 824659, 870984, 7185723, 742735, 354885, 4894137, 154957, 601410, 2921910, 693578, 8080061, 445009, 742726, 5593787, 318030, 678723, 3484600, 338764, 9082175, 955730, 436808, 7001404, 701775, 308805, 4871993, 677709, 337474, 4100917, 890185, 7711422, 354897, 617798, 5549755, 306511, 675139, 5056183, 861515, 9261759, 742482, 748103, 6909244, 613200, 301893, 4869049, 674637, 11216322, 435540, 447561, 7002685, 702033, 612166, 5543867, 300879, 412484, 3581239, 959818, 8827583, 371795, 702023, 5846716, 601680, 824901, 5065400, 870988, 894273, 2468534, 354889, 8039869, 154962, 601415, 6067642, 693582, 739907, 4937015, 709962, 9788095, 309843, 678728, 6630332, 338768, 693061, 4672185, 436812, 709953, 2415286, 308810, 6969149, 675409, 861766, 6198074, 873293, 371267, 3585335, 617803, 11841215, 306515, 675144, 7153084, 861519, 873028, 6138424, 744012, 355649, 2403766, 301898, 8014782, 674641, 697670, 5984954, 447054, 711234, 3496759, 603979, 8689601, 300883, 412488, 6726972, 959823, 436804, 4896312, 699980, 601666, 3970869, 824905, 8211133, 870993, 894277, 5614266, 354894, 683331, 4533943, 339275, 9082303, 693587, 739911, 7034171, 709967, 350789, 4873528, 678732, 338754, 3838902, 430921, 7809469, 436817, 709958, 5561018, 308814, 677699, 4532024, 861770, 9343806, 873042, 895559, 6731067, 355663, 306757, 4869817, 675148, 857409, 2986677 };
  public static final int MAX_YEAR = 2099;
  public static final int MIN_YEAR = 1900;
  private static final TimeZone TZ_UTC = new SimpleTimeZone(0, "UTC");
  
  private static int daysInLunarMonth(int paramInt1, int paramInt2)
  {
    if ((LUNAR_INFO[(paramInt1 - 1900)] & 1048576 >> paramInt2) == 0) {
      return 29;
    }
    return 30;
  }
  
  private static int daysInLunarYear(int paramInt)
  {
    int i = 348;
    if (leapMonth(paramInt) != 0) {
      i = 377;
    }
    int k = LUNAR_INFO[(paramInt - 1900)];
    paramInt = 524288;
    while (paramInt > 7)
    {
      int j = i;
      if ((0xFFF80 & k & paramInt) != 0) {
        j = i + 1;
      }
      paramInt >>= 1;
      i = j;
    }
    return i;
  }
  
  public static final int daysInMonth(int paramInt1, int paramInt2)
  {
    return daysInMonth(paramInt1, paramInt2, false);
  }
  
  public static final int daysInMonth(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = 0;
    int k = leapMonth(paramInt1);
    if ((k != 0) && (paramInt2 > k)) {}
    for (int i = 1;; i = 0)
    {
      if (!paramBoolean) {
        i = daysInLunarMonth(paramInt1, paramInt2 + i);
      }
      do
      {
        do
        {
          return i;
          i = j;
        } while (k == 0);
        i = j;
      } while (k != paramInt2);
      return daysInLunarMonth(paramInt1, paramInt2 + 1);
    }
  }
  
  public static int leapMonth(int paramInt)
  {
    return (LUNAR_INFO[(paramInt - 1900)] & 0xF00000) >> 20;
  }
  
  public static final int[] lunarToSolar(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if ((paramInt1 < 1900) || (paramInt1 > 2099) || (paramInt2 < 1) || (paramInt2 > 12) || (paramInt3 < 1) || (paramInt3 > 30)) {
      throw new IllegalArgumentException("Illegal lunar date, must be like that:\n\tyear : 1900~2099\n\tmonth : 1~12\n\tday : 1~30\n\terror date:" + paramInt1 + " " + paramInt2 + " " + paramInt3);
    }
    int j = (LUNAR_INFO[(paramInt1 - 1900)] & 0x1F) - 1;
    int i = j;
    if ((LUNAR_INFO[(paramInt1 - 1900)] & 0x60) >> 5 == 2) {
      i = j + 31;
    }
    j = 1;
    if (j < paramInt2)
    {
      if ((LUNAR_INFO[(paramInt1 - 1900)] & 524288 >> j - 1) == 0) {
        i += 29;
      }
      for (;;)
      {
        j += 1;
        break;
        i += 30;
      }
    }
    i += paramInt3;
    j = (LUNAR_INFO[(paramInt1 - 1900)] & 0xF00000) >> 20;
    paramInt3 = i;
    if (j != 0) {
      if (paramInt2 <= j)
      {
        paramInt3 = i;
        if (paramInt2 == j)
        {
          paramInt3 = i;
          if (!paramBoolean) {}
        }
      }
      else
      {
        if ((LUNAR_INFO[(paramInt1 - 1900)] & 524288 >> paramInt2 - 1) != 0) {
          break label398;
        }
        paramInt3 = i + 29;
      }
    }
    if (paramInt3 <= 366)
    {
      paramInt2 = paramInt3;
      i = paramInt1;
      if (paramInt1 % 4 != 0)
      {
        paramInt2 = paramInt3;
        i = paramInt1;
        if (paramInt3 <= 365) {}
      }
    }
    else
    {
      i = paramInt1 + 1;
      if (i % 4 != 1) {
        break label407;
      }
      paramInt2 = paramInt3 - 366;
    }
    label314:
    int[] arrayOfInt = new int[3];
    paramInt1 = 1;
    for (;;)
    {
      if (paramInt1 < 13)
      {
        j = DAYS_BEFORE_MONTH[paramInt1];
        paramInt3 = j;
        if (i % 4 == 0)
        {
          paramInt3 = j;
          if (paramInt1 > 2) {
            paramInt3 = j + 1;
          }
        }
        if ((i % 4 != 0) || (paramInt1 != 2) || (paramInt3 + 1 != paramInt2)) {
          break label416;
        }
        arrayOfInt[1] = paramInt1;
        arrayOfInt[2] = (paramInt2 - 31);
      }
      for (;;)
      {
        arrayOfInt[0] = i;
        return arrayOfInt;
        label398:
        paramInt3 = i + 30;
        break;
        label407:
        paramInt2 = paramInt3 - 365;
        break label314;
        label416:
        if (paramInt3 < paramInt2) {
          break label540;
        }
        arrayOfInt[1] = paramInt1;
        j = DAYS_BEFORE_MONTH[(paramInt1 - 1)];
        paramInt3 = j;
        if (i % 4 == 0)
        {
          paramInt3 = j;
          if (paramInt1 > 2) {
            paramInt3 = j + 1;
          }
        }
        if (paramInt2 > paramInt3) {
          arrayOfInt[2] = (paramInt2 - paramInt3);
        } else if (paramInt2 == paramInt3)
        {
          if ((i % 4 == 0) && (paramInt1 == 2)) {
            arrayOfInt[2] = (DAYS_BEFORE_MONTH[paramInt1] - DAYS_BEFORE_MONTH[(paramInt1 - 1)] + 1);
          } else {
            arrayOfInt[2] = (DAYS_BEFORE_MONTH[paramInt1] - DAYS_BEFORE_MONTH[(paramInt1 - 1)]);
          }
        }
        else {
          arrayOfInt[2] = paramInt2;
        }
      }
      label540:
      paramInt1 += 1;
    }
  }
  
  public static final int[] parseLunarDate(String paramString)
  {
    if (!validateDateFormat(paramString)) {
      throw new InvalidParameterException("Invalid date : " + paramString + " to parse. Must be from 1900-1-1 to 2099-12-31 in format YYYY-MM-DD");
    }
    paramString = paramString.split("-");
    return new int[] { Integer.valueOf(paramString[0]).intValue(), Integer.valueOf(paramString[1]).intValue(), Integer.valueOf(paramString[2]).intValue() };
  }
  
  public static final int[] solarToLunar(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 1900;
    int k = 1;
    Object localObject = new GregorianCalendar(1900, 0, 31);
    ((GregorianCalendar)localObject).setTimeZone(TZ_UTC);
    GregorianCalendar localGregorianCalendar = new GregorianCalendar(paramInt1, paramInt2 - 1, paramInt3);
    localGregorianCalendar.setTimeZone(TZ_UTC);
    localObject = ((GregorianCalendar)localObject).getTime();
    paramInt3 = (int)((localGregorianCalendar.getTime().getTime() - ((Date)localObject).getTime()) / 86400000L);
    int j = 0;
    paramInt1 = i;
    while ((paramInt1 <= 2099) && (paramInt3 > 0))
    {
      j = daysInLunarYear(paramInt1);
      paramInt3 -= j;
      paramInt1 += 1;
    }
    i = paramInt1;
    paramInt2 = paramInt3;
    if (paramInt3 < 0)
    {
      paramInt2 = paramInt3 + j;
      i = paramInt1 - 1;
    }
    j = leapMonth(i);
    paramInt3 = 0;
    paramInt1 = paramInt2;
    paramInt2 = 1;
    while ((paramInt2 <= 13) && (paramInt1 > 0))
    {
      paramInt3 = daysInLunarMonth(i, paramInt2);
      paramInt1 -= paramInt3;
      paramInt2 += 1;
    }
    if (paramInt1 < 0)
    {
      paramInt3 = paramInt1 + paramInt3;
      paramInt2 -= 1;
    }
    for (;;)
    {
      paramInt1 = paramInt2;
      if (j != 0)
      {
        paramInt1 = paramInt2;
        if (paramInt2 > j)
        {
          paramInt2 -= 1;
          paramInt1 = paramInt2;
          if (paramInt2 == j)
          {
            j = 1;
            paramInt1 = paramInt2;
          }
        }
      }
      for (;;)
      {
        if (j != 0) {}
        for (paramInt2 = k;; paramInt2 = 0) {
          return new int[] { i, paramInt1, paramInt3 + 1, paramInt2 };
        }
        j = 0;
      }
      paramInt3 = paramInt1;
    }
  }
  
  public static final boolean validateDateFormat(String paramString)
  {
    return DATE_PATTERN.matcher(paramString).matches();
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.LunarCalendar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */