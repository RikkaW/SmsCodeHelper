package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.CalenderHelper;
import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringProcess.ASCType;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.ParseType;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeRecognition
{
  private static double acceptScore;
  private static double acceptThr;
  private static String dateTimeFrontGuideFileName;
  private static HashSet<String> dateTimeFrontGuideWords;
  private static int dateTimeFrontGuideWordsMaxLength;
  private static String dateTimePatternsFileName;
  private static SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static int defaultHour;
  private static boolean festival;
  private static final byte[] festivalDate;
  private static boolean ifInitial = false;
  private static boolean intercalary;
  private static int lastSign;
  private static int lastValue;
  private static int maxVagueDistance;
  private static SimpleDateFormat[] numberTimePattern;
  private static String[] numberTimePatternStr;
  private static SimpleDateFormat onlyDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private static SimpleDateFormat onlyTimeFormat = new SimpleDateFormat("HH:mm:ss");
  private static Parser parser;
  public static Pattern patNumberSequence = Pattern.compile("(((\\d)+ (\\d)+)|((\\d)+))");
  private static double rejectThr;
  private static Calendar smsTime;
  private static boolean solar;
  private static Calendar time;
  private static boolean[] timeItemSet;
  
  static
  {
    dateTimeFrontGuideWords = new HashSet();
    dateTimeFrontGuideWordsMaxLength = -1;
    dateTimeFrontGuideFileName = null;
    dateTimePatternsFileName = null;
    maxVagueDistance = 2;
    acceptScore = 0.3D;
    festivalDate = new byte[] { 1, 23, 1, 30, 1, 31, 2, 14, 4, 5, 6, 2, 9, 8, 2, 11, 2, 18, 2, 19, 3, 5, 4, 5, 6, 20, 9, 27, 2, 1, 2, 7, 2, 8, 2, 22, 4, 4, 6, 9, 9, 15 };
    numberTimePatternStr = new String[] { "yyyyMMdd", "yyyyMMddHHmmss", "yyyyMMdd HHmmss", "yyyyMMddHHmm" };
    numberTimePattern = new SimpleDateFormat[numberTimePatternStr.length];
    acceptThr = 0.5D;
    rejectThr = 0.1D;
    defaultHour = 9;
    smsTime = Calendar.getInstance();
    festival = false;
    intercalary = false;
    solar = true;
    lastValue = -1;
    lastSign = 0;
  }
  
  private static Calendar changeResult(List<String> paramList1, List<String> paramList2)
    throws ParseException
  {
    lastSign = 0;
    time = Calendar.getInstance();
    time.setFirstDayOfWeek(2);
    time.set(smsTime.get(1), smsTime.get(2), smsTime.get(5), defaultHour, 0, 0);
    timeItemSet = new boolean[6];
    intercalary = false;
    solar = true;
    lastValue = -1;
    festival = false;
    int m = 0;
    int j = -1;
    int i = 0;
    if (m >= paramList1.size()) {}
    Object localObject;
    int k;
    for (;;)
    {
      if ((timeItemSet[2] == 0) && (timeItemSet[3] == 0))
      {
        return null;
        if (i == 0)
        {
          localObject = (String)paramList2.get(m);
          if (localObject != null) {
            if (((String)localObject).trim().equals(""))
            {
              k = j;
              j = i;
              i = k;
            }
          }
        }
      }
    }
    for (;;)
    {
      m += 1;
      k = j;
      j = i;
      i = k;
      break;
      String str = ((String)paramList1.get(m)).trim();
      if (str.equals(""))
      {
        k = i;
        i = j;
        j = k;
      }
      else
      {
        int n;
        if (((String)localObject).equals("<#yyyy>"))
        {
          if (timeItemSet[0] == 0)
          {
            n = Integer.parseInt(str);
            k = n;
            if (n < 100) {
              if (n >= 18) {
                break label282;
              }
            }
            label282:
            for (k = n + 2000;; k = n + 1900)
            {
              time.set(1, k);
              timeItemSet[0] = true;
              k = i;
              i = j;
              j = k;
              break;
            }
          }
        }
        else if (((String)localObject).equals("<#MM>"))
        {
          if (timeItemSet[1] == 0)
          {
            time.set(2, Integer.parseInt(str) - 1);
            timeItemSet[1] = true;
            k = i;
            i = j;
            j = k;
          }
        }
        else if (((String)localObject).equals("<#dd>"))
        {
          if (timeItemSet[2] == 0)
          {
            time.set(5, Integer.parseInt(str));
            timeItemSet[2] = true;
            k = i;
            i = j;
            j = k;
          }
        }
        else if (((String)localObject).equals("<#HH>"))
        {
          if (timeItemSet[3] == 0)
          {
            time.set(11, Integer.parseInt(str));
            timeItemSet[3] = true;
            k = i;
            i = j;
            j = k;
          }
        }
        else if (((String)localObject).equals("<#mm>"))
        {
          if (timeItemSet[4] == 0)
          {
            time.set(12, Integer.parseInt(str));
            timeItemSet[4] = true;
            k = i;
            i = j;
            j = k;
          }
        }
        else if (((String)localObject).equals("<#ss>"))
        {
          if (timeItemSet[5] == 0)
          {
            time.set(13, Integer.parseInt(str));
            timeItemSet[5] = true;
            k = i;
            i = j;
            j = k;
          }
        }
        else
        {
          localObject = segTargetSegStr(str);
          n = localObject.length;
          k = 0;
          label548:
          if (k >= n)
          {
            k = i;
            i = j;
            j = k;
            continue;
          }
          str = localObject[k];
          if (str.startsWith("week")) {
            getTime(str, 5);
          }
          for (;;)
          {
            k += 1;
            break label548;
            if (str.endsWith("year"))
            {
              getTime(str, 1);
            }
            else if (str.endsWith("month"))
            {
              getTime(str, 2);
            }
            else if (str.endsWith("day"))
            {
              getTime(str, 5);
            }
            else if (str.endsWith("hour"))
            {
              getTime(str, 11);
            }
            else if (str.endsWith("min"))
            {
              getTime(str, 12);
            }
            else if (str.equals("Fhalf"))
            {
              j = 0;
            }
            else if (str.equals("Shalf"))
            {
              j = 1;
            }
            else if (str.equals("night"))
            {
              j = 2;
            }
            else if (str.equals("年"))
            {
              paramList1.set(m, "year");
              getTime(str, 1);
            }
            else if (str.equals("月"))
            {
              paramList1.set(m, "month");
              getTime(str, 2);
            }
            else if (str.equals("分"))
            {
              paramList1.set(m, "min");
              getTime(str, 12);
            }
            else if (str.equals("日"))
            {
              paramList1.set(m, "day");
              getTime(str, 5);
            }
            else if (str.equals("秒"))
            {
              paramList1.set(m, "second");
              getTime(str, 13);
            }
            else
            {
              if (str.equals("to"))
              {
                i = j;
                j = 1;
                break;
              }
              if (str.equals("xiaonian")) {
                getFestivalDate(0);
              } else if (str.equals("chuxi")) {
                getFestivalDate(1);
              } else if (str.equals("chunjie")) {
                getFestivalDate(2);
              } else if (str.equals("yuanxiao")) {
                getFestivalDate(3);
              } else if (str.equals("qingming")) {
                getFestivalDate(4);
              } else if (str.equals("duanwu")) {
                getFestivalDate(5);
              } else if (str.equals("zhongqiu")) {
                getFestivalDate(6);
              } else {
                parseSign(str);
              }
            }
          }
          if ((j > 0) && (timeItemSet[3] != 0))
          {
            i = time.get(11);
            if ((j == 1) && (i >= 0) && (i < 12)) {
              time.add(11, 12);
            }
          }
          while ((!solar) && (!festival))
          {
            paramList1 = CalenderHelper.getSunDate(time.get(1), time.get(2) + 1, time.get(5));
            if (paramList1.size() == 0)
            {
              return null;
              if ((j == 2) && (i >= 6) && (i < 12))
              {
                time.add(11, 12);
                continue;
                if (j == 1) {
                  time.set(11, 13);
                } else if (j == 2) {
                  time.set(11, 20);
                }
              }
            }
            else
            {
              if ((intercalary) && (paramList1.size() != 2)) {
                return null;
              }
              if (!intercalary) {
                break label1290;
              }
            }
          }
          label1290:
          for (paramList1 = (int[])paramList1.get(1);; paramList1 = (int[])paramList1.get(0))
          {
            time.set(1, paramList1[0]);
            time.set(2, paramList1[1] - 1);
            time.set(5, paramList1[2]);
            return time;
          }
        }
        k = i;
        i = j;
        j = k;
      }
    }
  }
  
  private static List<ParseResult> filterResults(List<ParseResult> paramList, String paramString)
  {
    if (paramList.size() == 0) {
      return paramList;
    }
    ArrayList localArrayList = new ArrayList(paramList.size());
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localArrayList;
      }
      ParseResult localParseResult = (ParseResult)paramList.next();
      if ((!startKuohao(localParseResult.getSource(), localParseResult.getStartPositionInSentence(), localParseResult.getEndPositionInSentence(), paramString)) && (!frontBackChar(localParseResult.getSource(), localParseResult.getStartPositionInSentence(), localParseResult.getEndPositionInSentence(), paramString)))
      {
        int i;
        if ((localParseResult.getSource().length() <= 3) && (localParseResult.getStartPositionInSentence() > 0))
        {
          i = localParseResult.getSource().charAt(0);
          if ((i >= 48) && (i <= 57) && (paramString.charAt(localParseResult.getStartPositionInSentence() - 1) == '.') && (localParseResult.getStartPositionInSentence() > 1))
          {
            i = paramString.charAt(localParseResult.getStartPositionInSentence() - 2);
            if ((i >= 48) || (i <= 57)) {
              continue;
            }
          }
        }
        if ((parser.match("<#m>+<.|/|:|：|->+<#m>", localParseResult.getSource())) || (StringProcess.isNumber(localParseResult.getSource())))
        {
          int j;
          if (localParseResult.getEndPositionInSentence() < paramString.length())
          {
            i = paramString.charAt(localParseResult.getEndPositionInSentence());
            if (((i >= 97) && (i <= 122)) || ((i >= 65) && (i <= 90) && (localParseResult.getEndPositionInSentence() + 1 < paramString.length())))
            {
              j = paramString.charAt(localParseResult.getEndPositionInSentence() + 1);
              if (((j < 97) || (j > 122)) && ((j < 65) || (j > 90))) {
                continue;
              }
            }
            switch (i)
            {
            }
          }
          if (localParseResult.getStartPositionInSentence() > 0)
          {
            i = paramString.charAt(localParseResult.getStartPositionInSentence() - 1);
            if (((i >= 97) && (i <= 122)) || ((i >= 65) && (i <= 90) && (localParseResult.getStartPositionInSentence() > 1)))
            {
              j = paramString.charAt(localParseResult.getStartPositionInSentence() - 2);
              if (((j < 97) || (j > 122)) && ((j < 65) || (j > 90))) {
                continue;
              }
            }
            switch (i)
            {
            }
          }
        }
        localArrayList.add(localParseResult);
      }
    }
  }
  
  public static List<EntityInfo> findKnowledge(String paramString, long paramLong)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    if (!ifInitial) {
      return localArrayList;
    }
    smsTime.setTimeInMillis(paramLong);
    Iterator localIterator = mergeResults(filterResults(parser.findKnowledge(paramString, 0.0D), paramString), paramString).iterator();
    ParseResult localParseResult;
    Calendar localCalendar;
    do
    {
      if (!localIterator.hasNext())
      {
        findNumberSequenceTime(paramString, localArrayList);
        return localArrayList;
      }
      localParseResult = (ParseResult)localIterator.next();
      localCalendar = changeResult(localParseResult.getSegments_replace(), localParseResult.getSegments_pattern());
    } while ((localCalendar == null) || ((localParseResult.getSource().length() == 2) && (localParseResult.getSource().endsWith("时"))) || (((localParseResult.getSource().length() == 2) || (localParseResult.getSource().length() == 3)) && (localParseResult.getSource().endsWith("号"))));
    EntityInfo localEntityInfo = new EntityInfo();
    localEntityInfo.setTarget(localParseResult.getSource());
    localEntityInfo.setTargetNomal(datetimeFormat.format(localCalendar.getTime()));
    localEntityInfo.setEntityType(EntityType.TIME);
    localEntityInfo.setRemark(localParseResult.getPattern().toString());
    localEntityInfo.setConfidence(localParseResult.getConfidence().doubleValue());
    localEntityInfo.setStartPosition(localParseResult.getStartPositionInSentence());
    localEntityInfo.setEndPosition(localParseResult.getEndPositionInSentence());
    double d1 = localEntityInfo.getConfidence();
    int j = localEntityInfo.getStartPosition();
    if (j > 0)
    {
      if ((paramString.charAt(j - 1) != 65306) && (paramString.charAt(j - 1) != ':') && (paramString.charAt(j - 1) != '是'))
      {
        i = j;
        if (paramString.charAt(j - 1) != '为') {}
      }
      else
      {
        i = j - 1;
      }
      int i = StringProcess.EndWithDicWithVagueDistance(paramString.substring(0, i), dateTimeFrontGuideWords, dateTimeFrontGuideWordsMaxLength, maxVagueDistance);
      if (i >= 0) {
        d1 = (1.5D + maxVagueDistance - i) * d1;
      }
    }
    for (;;)
    {
      double d2 = d1;
      if (d1 >= 1.0D) {
        d2 = 1.0D;
      }
      localEntityInfo.setConfidence(d2);
      localArrayList.add(localEntityInfo);
      break;
    }
  }
  
  public static void findNumberSequenceTime(String paramString, List<EntityInfo> paramList)
  {
    Matcher localMatcher = patNumberSequence.matcher(paramString);
    int j;
    do
    {
      if (!localMatcher.find()) {
        return;
      }
      i = localMatcher.start();
      j = localMatcher.end();
    } while ((j - i < 8) || (j - i > 15));
    EntityInfo localEntityInfo = new EntityInfo();
    localEntityInfo.setStartPosition(i);
    localEntityInfo.setEndPosition(j);
    localEntityInfo.setTarget(paramString.substring(i, j));
    double d2 = 0.0D;
    int i = 0;
    for (;;)
    {
      if (i >= numberTimePattern.length) {
        i = 0;
      }
      while (i != 0)
      {
        j = localEntityInfo.getStartPosition();
        double d1 = d2;
        if (j > 0)
        {
          if ((paramString.charAt(j - 1) != 65306) && (paramString.charAt(j - 1) != ':') && (paramString.charAt(j - 1) != '是'))
          {
            i = j;
            if (paramString.charAt(j - 1) != '为') {}
          }
          else
          {
            i = j - 1;
          }
          i = StringProcess.EndWithDicWithVagueDistance(paramString.substring(0, i), dateTimeFrontGuideWords, dateTimeFrontGuideWordsMaxLength, maxVagueDistance);
          d1 = d2;
          if (i >= 0) {
            d1 = d2 * (1.5D + maxVagueDistance - i);
          }
        }
        d2 = d1;
        if (d1 > 1.0D) {
          d2 = 1.0D;
        }
        localEntityInfo.setConfidence(d2);
        paramList.add(localEntityInfo);
        break;
        try
        {
          Date localDate = numberTimePattern[i].parse(localEntityInfo.getTarget());
          if (numberTimePattern[i].format(Long.valueOf(localDate.getTime())).equals(localEntityInfo.getTarget()))
          {
            j = localDate.getYear();
            j += 1900;
            if ((j >= 2010) && (j <= 2018) && (localDate != null))
            {
              d2 = numberTimePattern[i].toPattern().length() / 14.0D;
              if (i < 1) {
                localDate.setHours(defaultHour);
              }
              localEntityInfo.setTargetNomal(datetimeFormat.format(Long.valueOf(localDate.getTime())));
              localEntityInfo.setEntityType(EntityType.TIME);
              localEntityInfo.setRemark("<?数字时间> ::= " + numberTimePattern[i].toPattern());
              i = 1;
            }
          }
        }
        catch (Exception localException)
        {
          i += 1;
        }
      }
    }
  }
  
  public static String formatDate(long paramLong)
  {
    if (paramLong <= 0L) {
      return "";
    }
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    return onlyDateFormat.format(localCalendar.getTime());
  }
  
  public static String formatDate(String paramString, long paramLong)
  {
    try
    {
      paramString = findKnowledge(paramString, paramLong);
      if (paramString.size() != 1) {
        return "";
      }
    }
    catch (Exception paramString)
    {
      return "";
    }
    try
    {
      paramString = datetimeFormat.parse(((EntityInfo)paramString.get(0)).getTarget_nomal());
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(paramString);
      return onlyDateFormat.format(localCalendar.getTime());
    }
    catch (ParseException paramString) {}
    return "";
  }
  
  public static String formatDateTime(long paramLong)
  {
    if (paramLong <= 0L) {
      return "";
    }
    return datetimeFormat.format(Long.valueOf(paramLong));
  }
  
  public static String formatDateTime(String paramString, long paramLong)
  {
    try
    {
      paramString = findKnowledge(paramString, paramLong);
      if (paramString.size() != 1) {
        return "";
      }
    }
    catch (Exception paramString)
    {
      return "";
    }
    return ((EntityInfo)paramString.get(0)).getTarget_nomal();
  }
  
  public static String formatTime(String paramString, long paramLong)
  {
    try
    {
      paramString = findKnowledge(paramString, paramLong);
      if (paramString.size() != 1) {
        return "";
      }
    }
    catch (Exception paramString)
    {
      return "";
    }
    try
    {
      paramString = datetimeFormat.parse(((EntityInfo)paramString.get(0)).getTarget_nomal());
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.setTime(paramString);
      return onlyTimeFormat.format(localCalendar.getTime());
    }
    catch (ParseException paramString) {}
    return "";
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    dateTimeFrontGuideWords.clear();
    parser = null;
    ifInitial = false;
    return true;
  }
  
  private static boolean frontBackChar(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    paramString1 = StringProcess.getASCType(paramString2.charAt(paramInt1));
    StringProcess.ASCType localASCType;
    if ((paramString1 == StringProcess.ASCType.Number) || (paramString1 == StringProcess.ASCType.EnglishUpper) || (paramString1 == StringProcess.ASCType.EnglishLowerCase))
    {
      paramInt1 -= 1;
      if (paramInt1 >= 0)
      {
        localASCType = StringProcess.getASCType(paramString2.charAt(paramInt1));
        if (paramString1 != localASCType) {}
      }
    }
    do
    {
      do
      {
        return true;
      } while (((paramString1 == StringProcess.ASCType.EnglishUpper) && (localASCType == StringProcess.ASCType.EnglishLowerCase)) || ((paramString1 == StringProcess.ASCType.EnglishLowerCase) && (localASCType == StringProcess.ASCType.EnglishUpper)));
      paramString1 = StringProcess.getASCType(paramString2.charAt(paramInt2 - 1));
      if (((paramString1 != StringProcess.ASCType.Number) && (paramString1 != StringProcess.ASCType.EnglishUpper) && (paramString1 != StringProcess.ASCType.EnglishLowerCase)) || (paramInt2 >= paramString2.length())) {
        break;
      }
      paramString2 = StringProcess.getASCType(paramString2.charAt(paramInt2));
    } while ((paramString1 == paramString2) || ((paramString1 == StringProcess.ASCType.EnglishUpper) && (paramString2 == StringProcess.ASCType.EnglishLowerCase)) || ((paramString1 == StringProcess.ASCType.EnglishLowerCase) && (paramString2 == StringProcess.ASCType.EnglishUpper)));
    return false;
  }
  
  private static boolean getFestivalDate(int paramInt)
  {
    festival = true;
    int i;
    int k;
    int m;
    int n;
    int j;
    int i1;
    if (timeItemSet[0] != 0)
    {
      i = time.get(1) - 2014;
      if (i >= 0)
      {
        k = i;
        if (i <= 2) {}
      }
      else
      {
        k = 1;
      }
      i = k * 14 + paramInt * 2;
      m = festivalDate[i];
      n = festivalDate[(i + 1)];
      i = n;
      j = m;
      if (smsTime.get(1) - 2014 == k)
      {
        i1 = smsTime.get(2) + 1;
        int i2 = smsTime.get(5);
        if (i1 <= m)
        {
          i = n;
          j = m;
          if (i1 == m)
          {
            i = n;
            j = m;
            if (n >= i2) {}
          }
        }
        else
        {
          i = n;
          j = m;
          if (k == 0)
          {
            time.add(1, 1);
            paramInt = (k + 1) * 14 + paramInt * 2;
            j = festivalDate[paramInt];
            i = festivalDate[(paramInt + 1)];
          }
        }
      }
    }
    for (;;)
    {
      time.set(2, j - 1);
      time.set(5, i);
      timeItemSet[1] = true;
      timeItemSet[2] = true;
      return true;
      j = smsTime.get(1) - 2014;
      n = smsTime.get(2) + 1;
      i1 = smsTime.get(5);
      if (j <= 2)
      {
        i = j;
        if (j >= 0) {}
      }
      else
      {
        i = 1;
      }
      i = i * 14 + paramInt * 2;
      k = festivalDate[i];
      m = festivalDate[(i + 1)];
      if (n <= k)
      {
        i = m;
        j = k;
        if (n == k)
        {
          i = m;
          j = k;
          if (m >= i1) {}
        }
      }
      else
      {
        i = m;
        j = k;
        if (0 == 0)
        {
          time.add(1, 1);
          paramInt = paramInt * 2 + 14;
          j = festivalDate[paramInt];
          i = festivalDate[(paramInt + 1)];
        }
      }
      timeItemSet[0] = true;
    }
  }
  
  private static int getSegCount(ParseResult paramParseResult)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramParseResult.getSegments().size()) {}
      while (paramParseResult.getSegments().get(i) == null) {
        return i;
      }
      i += 1;
    }
  }
  
  private static void getTime(String paramString, int paramInt)
  {
    int i;
    switch (paramInt)
    {
    case 3: 
    case 4: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
    case 10: 
    default: 
      i = 0;
    }
    int j;
    int k;
    int m;
    int i1;
    int n;
    for (;;)
    {
      if (paramInt != 1)
      {
        j = 0;
        k = 0;
        if ((j == 0) && (k < i)) {
          break;
        }
      }
      paramString = paramString.split(" ");
      m = lastValue;
      int i3 = paramString.length;
      k = 0;
      j = 0;
      i1 = 0;
      n = 0;
      if (n < i3) {
        break label499;
      }
      if (i >= 3) {
        break label658;
      }
      if (j != 0) {
        lastSign = k;
      }
      switch (paramInt)
      {
      case 3: 
      case 4: 
      case 6: 
      case 7: 
      case 8: 
      case 9: 
      case 10: 
      default: 
        label139:
        Log.i("DateTimeRecognition", "getTime Error!!");
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  do
                  {
                    return;
                  } while (timeItemSet[0] != 0);
                  i = 0;
                  break;
                } while (timeItemSet[1] != 0);
                i = 1;
                break;
              } while (timeItemSet[2] != 0);
              i = 2;
              break;
            } while (timeItemSet[3] != 0);
            i = 3;
            break;
          } while (timeItemSet[4] != 0);
          i = 4;
          break;
        } while (timeItemSet[5] != 0);
        i = 5;
      }
    }
    switch (k)
    {
    default: 
      if (timeItemSet[k] == 0)
      {
        Log.i("DateTimeRecognition", "getTime Error ,Not find:" + paramString + "\t" + datetimeFormat.format(time) + "\t" + paramInt);
        return;
      }
      break;
    case 0: 
      if (timeItemSet[0] != 0) {
        j = 1;
      }
      break;
    }
    for (;;)
    {
      k += 1;
      break;
      time.set(1, smsTime.get(1));
      timeItemSet[0] = true;
      continue;
      if (timeItemSet[1] != 0)
      {
        j = 1;
      }
      else
      {
        time.set(2, smsTime.get(2));
        timeItemSet[1] = true;
        continue;
        if (timeItemSet[2] != 0)
        {
          j = 1;
        }
        else
        {
          time.set(5, smsTime.get(5));
          timeItemSet[2] = true;
        }
      }
    }
    label499:
    String str = paramString[n];
    if (str.equals("last"))
    {
      j = 1;
      k -= 1;
    }
    for (;;)
    {
      n += 1;
      break;
      if (str.equals("next"))
      {
        j = 1;
        k += 1;
      }
      else if (str.equals("current"))
      {
        j = 1;
      }
      else if (str.equals("week"))
      {
        i1 = 1;
      }
      else if (str.equals("lunar"))
      {
        solar = false;
      }
      else if (str.equals("solar"))
      {
        solar = true;
      }
      else if (str.equals("intercalary"))
      {
        intercalary = true;
      }
      else
      {
        label658:
        try
        {
          int i2 = Integer.valueOf(str).intValue();
          m = i2;
        }
        catch (Exception localException) {}
        lastSign = 0;
        break label139;
        if (m != -1) {
          time.set(1, m);
        }
        for (;;)
        {
          timeItemSet[0] = true;
          return;
          time.set(1, smsTime.get(1));
          time.add(1, lastSign);
        }
        if (m != -1) {
          time.set(2, m - 1);
        }
        for (;;)
        {
          timeItemSet[1] = true;
          return;
          if (time.get(1) == smsTime.get(1))
          {
            time.set(2, smsTime.get(2));
            time.add(2, lastSign);
          }
          else
          {
            Log.i("DateTimeRecognition", "getTime Error!!");
          }
        }
        if (i1 == 0) {
          if (m != -1) {
            time.set(5, m);
          }
        }
        for (;;)
        {
          timeItemSet[2] = true;
          return;
          if (time.get(2) == smsTime.get(2))
          {
            time.set(5, smsTime.get(5));
            time.add(6, lastSign);
          }
          else
          {
            Log.i("DateTimeRecognition", "getTime Error!!");
            continue;
            if (m >= 1)
            {
              time.set(5, smsTime.get(5));
              i = time.get(3);
              paramInt = time.get(7);
              if (paramInt == 1) {
                paramInt = 7;
              }
              for (;;)
              {
                j = lastSign;
                time.add(6, m - paramInt + (j + i - i) * 7);
                break;
                paramInt -= 1;
              }
            }
            Log.i("DateTimeRecognition", "getTime Error!!");
          }
        }
        if (m != -1) {
          time.set(11, m);
        }
        for (;;)
        {
          timeItemSet[3] = true;
          return;
          Log.i("DateTimeRecognition", "getTime Error!!");
        }
        if (m != -1) {
          time.set(12, m);
        }
        for (;;)
        {
          timeItemSet[4] = true;
          return;
          Log.i("DateTimeRecognition", "getTime Error!!");
        }
        if (m != -1) {
          time.set(13, m);
        }
        for (;;)
        {
          timeItemSet[5] = true;
          return;
          Log.i("DateTimeRecognition", "getTime Error!!");
        }
      }
    }
  }
  
  public static boolean initial()
    throws Exception
  {
    if (ifInitial) {
      return true;
    }
    Log.i("DateTimeRecognition", "inital Time!");
    dateTimeFrontGuideFileName = SMSUnderstand.dictionaryPath + "/dateTimeFrontGuide.txt";
    dateTimePatternsFileName = SMSUnderstand.dictionaryPath + "/patterns/DateTime.pattern";
    dateTimeFrontGuideWordsMaxLength = FileOperator.readDic2Set(dateTimeFrontGuideFileName, dateTimeFrontGuideWords);
    parser = new Parser(dateTimePatternsFileName);
    int i = 0;
    for (;;)
    {
      if (i >= numberTimePattern.length)
      {
        ifInitial = true;
        return true;
      }
      numberTimePattern[i] = new SimpleDateFormat(numberTimePatternStr[i]);
      i += 1;
    }
  }
  
  private static List<ParseResult> mergeResults(List<ParseResult> paramList, String paramString)
  {
    if (paramList.size() == 0) {
      return paramList;
    }
    Object localObject = new boolean[paramList.size() - 1];
    int k = -10;
    int i = -1;
    int j = 0;
    if (j >= paramList.size()) {
      if ((i >= 0) && (paramString.length() > k)) {
        switch (paramString.charAt(k))
        {
        }
      }
    }
    ParseResult localParseResult1;
    int m;
    for (;;)
    {
      j = -1;
      i = paramList.size() - 1;
      if (i > 0) {
        break label339;
      }
      paramString = new ArrayList(paramList.size());
      paramList = paramList.iterator();
      if (paramList.hasNext()) {
        break label958;
      }
      return paramString;
      localParseResult1 = (ParseResult)paramList.get(j);
      if (j == 0)
      {
        k = localParseResult1.getEndPositionInSentence();
        j += 1;
        break;
      }
      m = localParseResult1.getStartPositionInSentence();
      int i2;
      if (m == k) {
        i2 = 1;
      }
      for (;;)
      {
        localObject[(j - 1)] = i2;
        k = localParseResult1.getEndPositionInSentence();
        break;
        if (m == k + 1)
        {
          k = paramString.charAt(k);
          if (k == 30340)
          {
            i2 = 1;
            continue;
          }
          if (k == 32)
          {
            i2 = 1;
            continue;
          }
        }
        switch (k)
        {
        default: 
        case 40: 
        case 65288: 
          do
          {
            i2 = 0;
            break;
          } while (i >= 0);
          i2 = 0;
          i = j;
          break;
        case 41: 
        case 65289: 
          if (i > 0) {
            localObject[(i - 1)] = 1;
          }
          i2 = 1;
          i = -1;
        }
      }
      if (i > 0) {
        localObject[(i - 1)] = 1;
      }
    }
    label339:
    if (i > 1)
    {
      if ((j == -1) && (localObject[(i - 1)] != 0)) {
        k = i;
      }
      do
      {
        do
        {
          i -= 1;
          j = k;
          break;
          k = j;
        } while (j == -1);
        k = j;
      } while (localObject[(i - 1)] != 0);
      label391:
      k = j;
      j = i;
    }
    for (;;)
    {
      label396:
      localParseResult1 = (ParseResult)paramList.get(j);
      m = 0;
      int n;
      for (;;)
      {
        if (m >= localParseResult1.getSegments().size()) {}
        while (localParseResult1.getSegments().get(m) == null)
        {
          n = localParseResult1.getEndPositionInSentence();
          int i1 = j + 1;
          j = m;
          m = i1;
          if (m <= k) {
            break label517;
          }
          k = -1;
          break;
          if (localObject[(i - 1)] == 0)
          {
            k = j;
            if (j == -1) {
              break;
            }
          }
          if (localObject[(i - 1)] == 0) {
            break label391;
          }
          if (j != -1) {
            break label1061;
          }
          k = 1;
          j = 0;
          break label396;
        }
        m += 1;
      }
      label517:
      ParseResult localParseResult2 = (ParseResult)paramList.get(m);
      String str;
      if (localParseResult2.getStartPositionInSentence() - n == 1)
      {
        str = paramString.substring(localParseResult1.getEndPositionInSentence(), localParseResult1.getEndPositionInSentence() + 1);
        if (j < localParseResult1.getSegments().size())
        {
          localParseResult1.getSegments().set(j, str);
          localParseResult1.getSegments_pattern().set(j, str);
          List localList = localParseResult1.getSegments_replace();
          n = j + 1;
          localList.set(j, str);
          j = n;
        }
      }
      for (;;)
      {
        label622:
        n = 0;
        if (n >= localParseResult2.getSegments().size()) {}
        while (localParseResult2.getSegments().get(n) == null)
        {
          localParseResult2.setType(ParseType.NoPass);
          n = localParseResult2.getEndPositionInSentence();
          localParseResult1.setConfidence(Double.valueOf(localParseResult2.getConfidence().doubleValue() + localParseResult1.getConfidence().doubleValue() - localParseResult2.getConfidence().doubleValue() * localParseResult1.getConfidence().doubleValue()));
          localParseResult1.setEndPositionInSentence(n);
          localParseResult1.setSource(paramString.substring(localParseResult1.getStartPositionInSentence(), n));
          m += 1;
          break;
          localParseResult1.getSegments().add(str);
          localParseResult1.getSegments_pattern().add(str);
          localParseResult1.getSegments_replace().add(str);
          j += 1;
          break label622;
        }
        if (j < localParseResult1.getSegments().size())
        {
          localParseResult1.getSegments().set(j, (String)localParseResult2.getSegments().get(n));
          localParseResult1.getSegments_pattern().set(j, (String)localParseResult2.getSegments_pattern().get(n));
          localParseResult1.getSegments_replace().set(j, (String)localParseResult2.getSegments_replace().get(n));
          j += 1;
        }
        for (;;)
        {
          n += 1;
          break;
          localParseResult1.getSegments().add((String)localParseResult2.getSegments().get(n));
          localParseResult1.getSegments_pattern().add((String)localParseResult2.getSegments_pattern().get(n));
          localParseResult1.getSegments_replace().add((String)localParseResult2.getSegments_replace().get(n));
          j += 1;
        }
        label958:
        localObject = (ParseResult)paramList.next();
        if ((((ParseResult)localObject).getType() == ParseType.NoPass) || (((ParseResult)localObject).getConfidence().doubleValue() < acceptScore) || ((getSegCount((ParseResult)localObject) == 1) && ((((String)((ParseResult)localObject).getSegments_replace().get(0)).equals("lunar")) || (((String)((ParseResult)localObject).getSegments().get(0)).length() <= 1)))) {
          break;
        }
        paramString.add(localObject);
        break;
      }
      label1061:
      k = j;
      j = 0;
    }
  }
  
  private static void parseSign(String paramString)
  {
    parseSign(paramString.split(" "));
  }
  
  private static void parseSign(String[] paramArrayOfString)
  {
    int m = paramArrayOfString.length;
    int k = 0;
    int i = 0;
    int j = 0;
    if (k >= m)
    {
      if (i != 0) {
        lastSign = j;
      }
      return;
    }
    String str = paramArrayOfString[k];
    if (str.equals("last"))
    {
      j -= 1;
      i = 1;
    }
    for (;;)
    {
      k += 1;
      break;
      if (str.equals("next"))
      {
        j += 1;
        i = 1;
      }
      else if (str.equals("current"))
      {
        i = 1;
      }
      else if (str.equals("lunar"))
      {
        solar = false;
      }
      else if (str.equals("solar"))
      {
        solar = true;
      }
      else if (str.equals("intercalary"))
      {
        intercalary = true;
      }
      else
      {
        try
        {
          lastValue = Integer.valueOf(str).intValue();
        }
        catch (Exception localException) {}
      }
    }
  }
  
  private static String[] segTargetSegStr(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramString.indexOf(" ", 0);
    int k;
    for (int j = 0;; j = k)
    {
      if (i <= 0)
      {
        localArrayList.add(paramString.substring(j));
        return (String[])localArrayList.toArray(new String[localArrayList.size()]);
      }
      String str = paramString.substring(j, i);
      if ((!str.endsWith("year")) && (!str.endsWith("month")))
      {
        k = j;
        if (!str.endsWith("day")) {}
      }
      else
      {
        k = j;
        if (i < paramString.length())
        {
          int m = paramString.charAt(i + 1);
          k = j;
          if (m >= 48)
          {
            k = j;
            if (m <= 57)
            {
              localArrayList.add(str);
              k = i + 1;
            }
          }
        }
      }
      i = paramString.indexOf(" ", i + 1);
    }
  }
  
  private static boolean startKuohao(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if (parser.match("<#m>+</|-|.>+<#m>", paramString1))
    {
      int i = paramInt1 - 1;
      if ((i < 0) || (paramInt2 >= paramString2.length())) {
        break label123;
      }
      if ((paramString2.charAt(i) == '(') && (paramString2.charAt(paramInt2) == ')')) {}
      while (((paramString2.charAt(i) == '[') && (paramString2.charAt(paramInt2) == ']')) || ((paramString2.charAt(i) == '【') && (paramString2.charAt(paramInt2) == '】')) || ((paramString2.charAt(i) == 65288) && (paramString2.charAt(paramInt2) == 65289))) {
        return true;
      }
    }
    label123:
    do
    {
      do
      {
        return false;
      } while ((paramInt1 != 0) || (paramInt2 >= paramString2.length()));
      if (paramString2.charAt(paramInt2) == ':') {
        break;
      }
    } while (paramString2.charAt(paramInt2) != 65306);
    return true;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.DateTimeRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */