package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringIntInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.ParseResult;
import com.xiaomi.nlp.Parser;
import com.xiaomi.nlp.PatternForNER;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberRecognition
{
  private static HashMap<String, Integer> areaCode2numberLength = new HashMap();
  private static String areaCode2numberLengthFileName;
  private static HashSet<String> badFrontGuideWords;
  private static String badFrontGuideWordsFileName;
  private static int badFrontGuideWordsMaxLength;
  private static HashMap<String, String> commonServiceNumber2Name;
  private static String commonServiceNumberFileName;
  private static boolean ifInitial;
  private static int maxVagueDistance = 2;
  private static String mobileNoPrefixFileName;
  private static String mobileNoRegex = "((\\+86)|(86))?(12520)?(mobileNoPrefix)[0-9]{8}";
  private static Parser parser;
  private static Pattern patFormobileNo_full;
  private static HashSet<String> phoneNoFrontGuideWords;
  private static String phoneNoFrontGuideWordsFileName;
  private static int phoneNoFrontGuideWordsMaxLength;
  private static Pattern phoneNumberNomal = Pattern.compile("^((\\+|86|12520|12521|12522|12523|12524|12525|12526|12527|12528|12529)+)(.*?)$");
  
  static
  {
    ifInitial = false;
    phoneNoFrontGuideWords = new HashSet();
    phoneNoFrontGuideWordsMaxLength = -1;
    phoneNoFrontGuideWordsFileName = null;
    badFrontGuideWords = new HashSet();
    badFrontGuideWordsMaxLength = -1;
    badFrontGuideWordsFileName = null;
    mobileNoPrefixFileName = null;
    areaCode2numberLengthFileName = null;
    commonServiceNumberFileName = null;
    commonServiceNumber2Name = new HashMap();
  }
  
  public static List<EntityInfo> findKnowledge(String paramString1, String paramString2)
    throws Exception
  {
    ArrayList localArrayList = new ArrayList();
    if (!ifInitial) {
      return localArrayList;
    }
    List localList = parser.findKnowledge(paramString1, 0.3D);
    Iterator localIterator = localList.iterator();
    int k = 1;
    if (!localIterator.hasNext()) {
      return localArrayList;
    }
    ParseResult localParseResult = (ParseResult)localIterator.next();
    int n = localList.size();
    Object localObject = knowledge.entrySet().iterator();
    int i;
    for (int j = 0;; j = i)
    {
      if (!((Iterator)localObject).hasNext()) {}
      Map.Entry localEntry;
      label385:
      label440:
      String str;
      for (;;)
      {
        if ((j == 0) && (knowledge.size() == 1))
        {
          localObject = new EntityInfo();
          ((EntityInfo)localObject).setTarget(paramString2);
          ((EntityInfo)localObject).noNomal();
          ((EntityInfo)localObject).setEntityType(EntityType.SPECIALENTITY);
          ((EntityInfo)localObject).setGroupEntity(String.valueOf(k) + "_" + localParseResult.getTask().toLowerCase());
          ((EntityInfo)localObject).setRemark("sendtonumber");
          ((EntityInfo)localObject).setConfidence(getPatternscore);
          ((EntityInfo)localObject).setStartPosition(-1);
          ((EntityInfo)localObject).setEndPosition(-1);
          localArrayList.add(localObject);
        }
        k += 1;
        break;
        localEntry = (Map.Entry)((Iterator)localObject).next();
        int m = ((StringIntInt)localEntry.getValue()).getStartIndex();
        int i1 = ((StringIntInt)localEntry.getValue()).getEndIndex();
        i = j;
        int i2;
        if (i1 < paramString1.length())
        {
          i2 = paramString1.charAt(i1);
          if (((i2 < 48) || (i2 > 57)) && ((i2 < 97) || (i2 > 122)) && ((i2 < 65) || (i2 > 90))) {
            break label385;
          }
          i = 1;
        }
        for (;;)
        {
          if (i == 0) {
            break label440;
          }
          m = localList.size() - 1;
          for (;;)
          {
            j = i;
            if (m <= n) {
              break;
            }
            localList.remove(m);
            m -= 1;
          }
          i = j;
          if (i2 == 46)
          {
            i = j;
            if (i1 + 1 < paramString1.length())
            {
              i2 = paramString1.charAt(i1 + 1);
              i = j;
              if (i2 >= 48)
              {
                i = j;
                if (i2 <= 57) {
                  i = 1;
                }
              }
            }
          }
        }
        str = paramString1.substring(m, i1);
        if ((!str.equals(paramString2)) || (knowledge.size() != 1)) {
          break label476;
        }
        j = 1;
      }
      label476:
      EntityInfo localEntityInfo = new EntityInfo();
      localEntityInfo.setTarget(str);
      localEntityInfo.noNomal();
      localEntityInfo.setEntityType(EntityType.SPECIALENTITY);
      localEntityInfo.setGroupEntity(String.valueOf(k) + "_" + localParseResult.getTask().toLowerCase());
      localEntityInfo.setRemark(((String)localEntry.getKey()).toLowerCase());
      localEntityInfo.setConfidence(getPatternscore);
      localEntityInfo.setStartPosition(((StringIntInt)localEntry.getValue()).getStartIndex());
      localEntityInfo.setEndPosition(((StringIntInt)localEntry.getValue()).getEndIndex());
      localArrayList.add(localEntityInfo);
    }
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    phoneNoFrontGuideWordsMaxLength = -1;
    badFrontGuideWordsMaxLength = -1;
    phoneNoFrontGuideWords.clear();
    badFrontGuideWords.clear();
    commonServiceNumber2Name.clear();
    areaCode2numberLength.clear();
    parser = null;
    ifInitial = false;
    return true;
  }
  
  public static double ifLandLineNo(String paramString1, String paramString2)
  {
    if (!ifInitial) {
      return 0.0D;
    }
    int i = paramString2.length();
    if ((i != 7) && (i != 8) && (i != 11) && (i != 12) && (i != 15) && (i != 16)) {
      return 0.0D;
    }
    if (i < 10)
    {
      i = paramString1.charAt(0);
      if ((i < 50) || (i > 56)) {
        return 0.0D;
      }
      return 0.3D;
    }
    String str2 = paramString2.substring(0, 4);
    String str1 = str2;
    if (!areaCode2numberLength.containsKey(str2))
    {
      paramString2 = paramString2.substring(0, 3);
      str1 = paramString2;
      if (!areaCode2numberLength.containsKey(paramString2)) {
        return 0.0D;
      }
    }
    int j = str1.length();
    if (i != ((Integer)areaCode2numberLength.get(str1)).intValue() + j) {
      return 0.1D;
    }
    if ((paramString1.contains(str1 + "-")) || (paramString1.contains(str1 + "—"))) {
      return 1.0D;
    }
    return 0.9D;
  }
  
  public static boolean ifMobileNo(String paramString)
  {
    if (!ifInitial) {}
    while ((!patFormobileNo_full.matcher(paramString).find()) || (paramString.equals("13800138000"))) {
      return false;
    }
    return true;
  }
  
  public static double ifServiceNo(String paramString1, String paramString2)
  {
    if (!ifInitial) {}
    int i;
    label87:
    String str;
    do
    {
      do
      {
        do
        {
          return 0.0D;
          i = paramString2.length();
          if (i >= 10) {
            break label87;
          }
          if (i != 3) {
            break;
          }
        } while ((!commonServiceNumber2Name.containsKey(paramString2)) || (paramString1.length() != 3));
        return 1.0D;
      } while (((paramString2.charAt(0) != '1') && (paramString2.charAt(0) != '9')) || ((i <= 7) && (paramString1.length() != i)));
      return 0.3D;
      str = paramString2.substring(0, 3);
    } while (!areaCode2numberLength.containsKey(str));
    if ((str.charAt(0) >= '2') && (str.charAt(0) <= '8') && (str.charAt(1) == '0') && (str.charAt(2) == '0')) {}
    for (double d = 0.8D;; d = 0.0D)
    {
      int j = str.length();
      if (i == ((Integer)areaCode2numberLength.get(str)).intValue() + j) {
        break label294;
      }
      return d;
      if (str.charAt(0) == '0')
      {
        i = paramString1.indexOf(str);
        if (i <= 0) {
          break;
        }
        j = i + 3;
        if (paramString1.charAt(j + 3) != '-')
        {
          i = j;
          if (paramString1.charAt(j + 3) != '—') {}
        }
        else
        {
          i = j + 1;
        }
        return ifServiceNo(paramString1.substring(i), paramString2.substring(3));
      }
      if ((str.charAt(0) != '1') && (str.charAt(0) != '9')) {
        break;
      }
    }
    label294:
    if ((paramString1.contains(str + "-")) || (paramString1.contains(str + "—"))) {
      return 1.0D;
    }
    return d;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    mobileNoPrefixFileName = SMSUnderstand.dictionaryPath + "/mobileNoPrefix.txt";
    phoneNoFrontGuideWordsFileName = SMSUnderstand.dictionaryPath + "/phoneNoFrontGuideWords.txt";
    areaCode2numberLengthFileName = SMSUnderstand.dictionaryPath + "/areaCode2numberLength.txt";
    commonServiceNumberFileName = SMSUnderstand.dictionaryPath + "/commonServiceNumber.txt";
    badFrontGuideWordsFileName = SMSUnderstand.dictionaryPath + "/badPhoneNoFrontGuideWords.txt";
    for (;;)
    {
      Object localObject2;
      try
      {
        localObject2 = FileOperator.readFile(mobileNoPrefixFileName);
        Object localObject1 = new StringBuffer();
        localObject2 = ((List)localObject2).iterator();
        if (!((Iterator)localObject2).hasNext())
        {
          mobileNoRegex = mobileNoRegex.replace("mobileNoPrefix", ((StringBuffer)localObject1).toString());
          patFormobileNo_full = Pattern.compile("^" + mobileNoRegex + "$");
          phoneNoFrontGuideWordsMaxLength = FileOperator.readDic2Set(phoneNoFrontGuideWordsFileName, phoneNoFrontGuideWords);
          badFrontGuideWordsMaxLength = FileOperator.readDic2Set(badFrontGuideWordsFileName, badFrontGuideWords);
          localObject1 = FileOperator.readFile(areaCode2numberLengthFileName).iterator();
          if (((Iterator)localObject1).hasNext()) {
            break label402;
          }
          commonServiceNumber2Name = FileOperator.readToMapChangeCol(commonServiceNumberFileName);
          parser = new Parser(SMSUnderstand.dictionaryPath + "/patterns/SendTo.pattern");
          ifInitial = true;
          return true;
        }
      }
      catch (IOException localIOException)
      {
        return false;
      }
      Object localObject3 = (String)((Iterator)localObject2).next();
      if (!((String)localObject3).startsWith("//"))
      {
        localObject3 = ((String)localObject3).split("\\s");
        int j = localObject3.length;
        int i = 0;
        label344:
        String str;
        if (i < j)
        {
          str = localObject3[i];
          if (!localObject3.equals(""))
          {
            if (localIOException.length() <= 0) {
              break label392;
            }
            localIOException.append('|').append(str);
          }
        }
        for (;;)
        {
          i += 1;
          break label344;
          break;
          label392:
          localIOException.append(str);
        }
        label402:
        localObject2 = ((String)localIOException.next()).trim();
        if (!((String)localObject2).startsWith("//"))
        {
          localObject2 = ((String)localObject2).split("\t");
          if (localObject2.length >= 2)
          {
            i = 7;
            if (localObject2.length > 2) {
              i = Integer.parseInt(localObject2[2]);
            }
            if (!areaCode2numberLength.containsKey(localObject2[1])) {
              areaCode2numberLength.put(localObject2[1], Integer.valueOf(i));
            } else if (((Integer)areaCode2numberLength.get(localObject2[1])).intValue() < i) {
              areaCode2numberLength.put(localObject2[1], Integer.valueOf(i));
            }
          }
        }
      }
    }
  }
  
  public static String isPhoneNumber(String paramString1, String paramString2)
  {
    int i = StringProcess.getNumberCount(paramString2);
    if (i == 4) {
      return "非电话号码";
    }
    if (i != paramString2.length()) {
      return "非电话号码";
    }
    if ((paramString2.length() < 3) || (paramString2.length() > 16)) {
      return "非电话号码";
    }
    if (ifMobileNo(paramString1)) {
      return "移动电话";
    }
    if (ifLandLineNo(paramString1, paramString2) > 0.2D) {
      return "座机电话";
    }
    if (ifServiceNo(paramString1, paramString2) > 0.2D) {
      return "服务电话";
    }
    return "非电话号码";
  }
  
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString)
  {
    double d2;
    if (!ifInitial)
    {
      d2 = 0.0D;
      return d2;
    }
    int i = paramEntityInfo.getTarget_nomal().length();
    if (i == 4) {
      return 0.0D;
    }
    if (paramEntityInfo.getNumberCount() != i) {
      return 0.0D;
    }
    if ((i < 3) || (i > 16)) {
      return 0.0D;
    }
    double d1;
    String str;
    if (ifMobileNo(paramEntityInfo.getTarget_nomal()))
    {
      d1 = 1.0D;
      str = "移动电话";
    }
    for (;;)
    {
      d2 = d1;
      if (d1 <= 0.0D) {
        break;
      }
      int k = paramEntityInfo.getStartPosition();
      int j = k;
      d2 = d1;
      if (k > 0)
      {
        if ((paramString.charAt(k - 1) != 65306) && (paramString.charAt(k - 1) != ':') && (paramString.charAt(k - 1) != '是'))
        {
          i = k;
          if (paramString.charAt(k - 1) != '为') {}
        }
        else
        {
          i = k - 1;
        }
        j = StringProcess.EndWithDicWithVagueDistance(paramString.substring(0, i), phoneNoFrontGuideWords, phoneNoFrontGuideWordsMaxLength, maxVagueDistance);
        d2 = d1;
        if (j >= 0) {
          d2 = d1 * (1.5D + maxVagueDistance - j);
        }
        j = i;
        if (StringProcess.EndWithDicWithVagueDistance(paramString.substring(0, i), badFrontGuideWords, badFrontGuideWordsMaxLength, 1) >= 0)
        {
          d2 = 0.0D;
          j = i;
        }
      }
      if (paramEntityInfo.getEndPosition() == paramString.length()) {
        d1 = d2 * 2.0D;
      }
      for (;;)
      {
        d2 = d1;
        if (d1 > 1.0D) {
          d2 = 1.0D;
        }
        paramEntityInfo.setRemark(str);
        return d2;
        d1 = ifServiceNo(paramEntityInfo.getTarget(), paramEntityInfo.getTarget_nomal());
        if (d1 < 0.3D)
        {
          d2 = ifLandLineNo(paramEntityInfo.getTarget(), paramEntityInfo.getTarget_nomal());
          if ((d1 >= d2) || (d2 <= 0.3D)) {
            break label491;
          }
          str = "座机电话";
          d1 = d2;
          break;
        }
        str = "服务电话";
        break;
        if ((paramString.charAt(paramString.length() - 1) == '】') || (paramString.charAt(paramString.length() - 1) == ']'))
        {
          k = paramEntityInfo.getEndPosition();
          if ((k == paramString.length() - 1) && (j > 0) && ((paramString.charAt(j - 1) == '【') || (paramString.charAt(j - 1) == '[')))
          {
            d1 = d2 * 3.0D;
            continue;
          }
          j = paramString.lastIndexOf('【');
          i = j;
          if (j < 0) {
            i = paramString.lastIndexOf('[');
          }
          if ((i > 0) && (k == i))
          {
            d1 = d2 * 2.5D;
            continue;
          }
        }
        d1 = d2;
      }
      label491:
      d1 = -1.0D;
      str = "";
    }
  }
  
  public static String nomalPhoneNumber(String paramString)
  {
    String str;
    if (paramString == null) {
      str = "";
    }
    Matcher localMatcher;
    do
    {
      do
      {
        return str;
        localMatcher = phoneNumberNomal.matcher(paramString);
        str = paramString;
      } while (!localMatcher.find());
      str = paramString;
    } while (localMatcher.groupCount() < 1);
    return paramString.substring(localMatcher.group(1).length());
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.PhoneNumberRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */