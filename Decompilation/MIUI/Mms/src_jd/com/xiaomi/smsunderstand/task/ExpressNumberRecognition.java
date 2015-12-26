package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringProcess.ASCType;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class ExpressNumberRecognition
{
  private static String cleanNumberRegex;
  private static HashMap<String, String> expName2NormalizationexpName = new HashMap();
  private static HashMap<String, Integer> expName2NumberLength = new HashMap();
  private static HashSet<String> expNoFrontGuideWords;
  private static int expNoFrontGuideWordsMaxLength;
  private static HashSet<String> expressName = new HashSet();
  private static String expressNameFileName;
  private static int expressNameMaxLength = -1;
  private static String expressNoFrontGuideWordsFileName;
  private static boolean ifInitial = false;
  private static int maxVagueDistance;
  private static Pattern patForCleanNum;
  
  static
  {
    expressNameFileName = null;
    expressNoFrontGuideWordsFileName = null;
    maxVagueDistance = 10;
    cleanNumberRegex = "(\\\\n)|(\\\\r)|(\\\\t)";
    patForCleanNum = Pattern.compile("[0-9a-zA-Z]*[0-9]{5,}[0-9a-zA-Z]*");
    expNoFrontGuideWords = new HashSet();
    expNoFrontGuideWordsMaxLength = -1;
  }
  
  public static List<StringInt> findExpNamesFromText(String paramString)
  {
    return StringProcess.findAllResultsFromList(paramString, expressName, expressNameMaxLength);
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    expressNameMaxLength = -1;
    expNoFrontGuideWordsMaxLength = -1;
    expressName.clear();
    expNoFrontGuideWords.clear();
    expName2NormalizationexpName.clear();
    expName2NumberLength.clear();
    Express.clear();
    ifInitial = false;
    return true;
  }
  
  private static String getNormalizationName(String paramString)
  {
    if (expName2NormalizationexpName.containsKey(paramString)) {
      return (String)expName2NormalizationexpName.get(paramString);
    }
    return paramString;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    expressNameFileName = SMSUnderstand.dictionaryPath + "/expressName.txt";
    expressNoFrontGuideWordsFileName = SMSUnderstand.dictionaryPath + "/expressNoFrontGuideWords.txt";
    Object localObject1 = FileOperator.readFile(expressNameFileName);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((List)localObject1).iterator();
    if (!localIterator.hasNext()) {
      localObject1 = localArrayList.iterator();
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext())
      {
        expNoFrontGuideWordsMaxLength = FileOperator.readDic2Set(expressNoFrontGuideWordsFileName, expNoFrontGuideWords);
        ifInitial = true;
        return true;
        String[] arrayOfString = ((String)localIterator.next()).split("\\t");
        int i = 0;
        int n = localArrayList.size();
        localObject1 = null;
        int j = 0;
        if (j >= arrayOfString.length)
        {
          if (i == 0) {
            break;
          }
          j = n;
          while (j < localArrayList.size())
          {
            if (!expName2NumberLength.containsKey(localArrayList.get(j))) {
              expName2NumberLength.put((String)localArrayList.get(j), Integer.valueOf(i));
            }
            j += 1;
          }
          break;
        }
        int m = arrayOfString[j].indexOf("---");
        int k;
        if ((m < 0) && (!arrayOfString[j].equals("")))
        {
          localObject3 = arrayOfString[j].toLowerCase();
          localArrayList.add(localObject3);
          if (j == 0)
          {
            localObject2 = localObject3;
            k = i;
          }
        }
        do
        {
          for (;;)
          {
            j += 1;
            i = k;
            localObject1 = localObject2;
            break;
            k = i;
            localObject2 = localObject1;
            if (j > 0)
            {
              k = i;
              localObject2 = localObject1;
              if (localObject1 != null)
              {
                expName2NormalizationexpName.put(localObject3, localObject1);
                k = i;
                localObject2 = localObject1;
              }
            }
          }
          k = i;
          localObject2 = localObject1;
        } while (m <= 0);
        Object localObject3 = arrayOfString[j].substring("---".length() + m).trim().split("\\^");
        int i1 = localObject3.length;
        m = 0;
        for (;;)
        {
          k = i;
          localObject2 = localObject1;
          if (m >= i1) {
            break;
          }
          localObject2 = localObject3[m];
          k = i;
          if (!((String)localObject2).equals("")) {
            k = i | 1 << Integer.parseInt((String)localObject2);
          }
          m += 1;
          i = k;
        }
      }
      Object localObject2 = (String)((Iterator)localObject1).next();
      expressName.add(localObject2);
      if (((String)localObject2).length() > expressNameMaxLength) {
        expressNameMaxLength = ((String)localObject2).length();
      }
    }
  }
  
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString)
  {
    double d2;
    if (!ifInitial)
    {
      d2 = 0.0D;
      return d2;
    }
    int i;
    int k;
    int j;
    if (paramEntityInfo.getTarget().toLowerCase().startsWith(paramEntityInfo.getRemark()))
    {
      i = paramEntityInfo.getEngCharCount();
      k = paramEntityInfo.getNumberCount();
      j = 0;
      if (j >= paramEntityInfo.getRemark().length())
      {
        paramEntityInfo.setEngCharCount(i);
        paramEntityInfo.setNumberCount(k);
        i = paramEntityInfo.getRemark().length();
        label77:
        if (i < paramEntityInfo.getTarget().length()) {
          break label324;
        }
        label89:
        paramEntityInfo.setTarget(paramEntityInfo.getTarget().substring(i));
        paramEntityInfo.setStartPosition(i + paramEntityInfo.getStartPosition());
        paramEntityInfo.setTarget_nomal(paramEntityInfo.getTarget());
      }
    }
    else
    {
      i = paramString.indexOf(paramEntityInfo.getRemark());
      if (i < 0) {
        break label690;
      }
      i += paramEntityInfo.getRemark().length();
      if (paramString.length() <= i) {
        break label690;
      }
      if ((paramString.charAt(i) != 65306) && (paramString.charAt(i) != ':') && (paramString.charAt(i) != '是') && (paramString.charAt(i) != '为')) {
        break label359;
      }
      if (i + 1 == paramEntityInfo.getStartPosition()) {
        break label690;
      }
      i = 1;
    }
    for (;;)
    {
      paramEntityInfo.setRemark(getNormalizationName(paramEntityInfo.getRemark()));
      if (paramEntityInfo.getStartPosition() == 0)
      {
        return 0.0D;
        StringProcess.ASCType localASCType = StringProcess.getASCType(paramEntityInfo.getRemark().charAt(j));
        int m;
        int n;
        if (localASCType == StringProcess.ASCType.Number)
        {
          m = k - 1;
          n = i;
        }
        for (;;)
        {
          j += 1;
          k = m;
          i = n;
          break;
          if (localASCType != StringProcess.ASCType.EnglishLowerCase)
          {
            m = k;
            n = i;
            if (localASCType != StringProcess.ASCType.EnglishUpper) {}
          }
          else
          {
            n = i - 1;
            m = k;
          }
        }
        label324:
        j = paramEntityInfo.getTarget().charAt(i);
        if ((j != 58) && (j != 65306)) {
          break label89;
        }
        i += 1;
        break label77;
        label359:
        if ((StringProcess.getASCType(paramString.charAt(i)) != StringProcess.ASCType.Number) || (i == paramEntityInfo.getStartPosition())) {
          break label690;
        }
        i = 1;
        continue;
      }
      if (paramEntityInfo.getTarget().length() < 6) {
        return 0.0D;
      }
      if (paramEntityInfo.getTarget().length() != paramEntityInfo.getEngCharCount() + paramEntityInfo.getNumberCount()) {
        return 0.0D;
      }
      d2 = -1.0D;
      k = paramEntityInfo.getStartPosition();
      double d1 = d2;
      if (k > 0)
      {
        if ((paramString.charAt(k - 1) != 65306) && (paramString.charAt(k - 1) != ':') && (paramString.charAt(k - 1) != '是'))
        {
          j = k;
          if (paramString.charAt(k - 1) != '为') {}
        }
        else
        {
          j = k - 1;
        }
        j = StringProcess.EndWithDicWithVagueDistance(paramString.substring(0, j), expNoFrontGuideWords, expNoFrontGuideWordsMaxLength, maxVagueDistance);
        d1 = d2;
        if (j >= 0)
        {
          d1 = 1.0D / (maxVagueDistance + 1);
          d1 = 1.0D - j * d1;
        }
      }
      if (expName2NumberLength.containsKey(paramEntityInfo.getRemark()))
      {
        if ((((Integer)expName2NumberLength.get(paramEntityInfo.getRemark())).intValue() & 1 << paramEntityInfo.getTarget_nomal().length()) == 0) {
          return 0.0D;
        }
        if (d1 > 0.5D) {
          d1 = 1.0D;
        }
      }
      for (;;)
      {
        label608:
        if (paramEntityInfo.getRemark().contains("京东"))
        {
          j = paramString.indexOf("您的订单");
          k = paramString.indexOf("发货");
          if ((j >= paramEntityInfo.getStartPosition()) || (k <= paramEntityInfo.getEndPosition())) {
            break label682;
          }
        }
        label682:
        for (d1 = 1.0D;; d1 = 0.0D)
        {
          d2 = d1;
          if (i == 0) {
            break;
          }
          return d1 * 0.8D;
          d1 = 2.0D * d1;
          break label608;
        }
      }
      label690:
      i = 0;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.ExpressNumberRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */