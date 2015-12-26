package com.xiaomi.smsunderstand.task;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringInt;
import com.xiaomi.common.StringProcess;
import com.xiaomi.common.StringProcess.ContainEnum;
import com.xiaomi.smsunderstand.EntityInfo;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class BankCardNumberRecognition
{
  private static HashSet<String> allBankNames = new HashSet();
  private static int allBankNamesMaxLength = -1;
  private static String bankCardBinNumberPrefixFileName;
  private static String bankCardNoFrontGuideFileName;
  private static HashSet<String> bankCardNoFrontGuideWords;
  private static int bankCardNoFrontGuideWordsMaxLength;
  private static HashMap<String, String> bankName2NormalizationBankName = new HashMap();
  private static String bankNamesFileName;
  private static boolean ifInitial = false;
  private static HashMap<String, String> lengthAndPrefixIndex2BankName;
  
  static
  {
    bankCardNoFrontGuideWords = new HashSet();
    bankCardNoFrontGuideWordsMaxLength = -1;
    bankCardNoFrontGuideFileName = null;
    bankCardBinNumberPrefixFileName = null;
    bankNamesFileName = null;
    lengthAndPrefixIndex2BankName = new HashMap();
  }
  
  public static boolean freeResource()
  {
    if (!ifInitial) {
      return true;
    }
    bankCardNoFrontGuideWordsMaxLength = -1;
    allBankNamesMaxLength = -1;
    bankCardNoFrontGuideWords.clear();
    lengthAndPrefixIndex2BankName.clear();
    allBankNames.clear();
    bankName2NormalizationBankName.clear();
    ifInitial = false;
    return true;
  }
  
  private static String getNormalizationName(String paramString)
  {
    if (bankName2NormalizationBankName.containsKey(paramString)) {
      return (String)bankName2NormalizationBankName.get(paramString);
    }
    return paramString;
  }
  
  public static boolean initial()
    throws IOException
  {
    if (ifInitial) {
      return true;
    }
    bankCardNoFrontGuideFileName = SMSUnderstand.dictionaryPath + "/bankCardNoFrontGuide.txt";
    bankCardBinNumberPrefixFileName = SMSUnderstand.dictionaryPath + "/bankCardBinNumberPrefix.txt";
    bankNamesFileName = SMSUnderstand.dictionaryPath + "/bankNames.txt";
    bankCardNoFrontGuideWordsMaxLength = FileOperator.readDic2Set(bankCardNoFrontGuideFileName, bankCardNoFrontGuideWords);
    readBankCardBinNumberPrefix();
    Iterator localIterator = FileOperator.readFile(bankNamesFileName).iterator();
    if (!localIterator.hasNext())
    {
      ifInitial = true;
      return true;
    }
    String[] arrayOfString = ((String)localIterator.next()).split("\\t");
    Object localObject1 = null;
    int i = 0;
    label146:
    Object localObject2;
    String str;
    if (i < arrayOfString.length)
    {
      localObject2 = localObject1;
      if (!arrayOfString[i].equals(""))
      {
        str = arrayOfString[i].toLowerCase();
        allBankNames.add(str);
        if (str.length() > allBankNamesMaxLength) {
          allBankNamesMaxLength = str.length();
        }
        if (i != 0) {
          break label215;
        }
        localObject2 = str;
      }
    }
    for (;;)
    {
      i += 1;
      localObject1 = localObject2;
      break label146;
      break;
      label215:
      localObject2 = localObject1;
      if (i > 0)
      {
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          bankName2NormalizationBankName.put(str, localObject1);
          localObject2 = localObject1;
        }
      }
    }
  }
  
  public static double isRightNumber(EntityInfo paramEntityInfo, String paramString)
  {
    int k = 0;
    double d2;
    if (!ifInitial)
    {
      d2 = 0.0D;
      return d2;
    }
    int m = paramEntityInfo.getNumberCount();
    if (m != paramEntityInfo.getTarget_nomal().length()) {
      return 0.0D;
    }
    if ((m < 15) || (m > 19)) {
      return 0.0D;
    }
    String str1 = "";
    int j = 6;
    int i = 0;
    double d1;
    if ((j < 2) || (j > 10))
    {
      d1 = 0.1D;
      label79:
      if (d1 <= 0.1D) {
        break label892;
      }
    }
    label276:
    label885:
    label892:
    for (i = 1;; i = 0)
    {
      m = paramEntityInfo.getStartPosition();
      double d3 = d1;
      Object localObject;
      if (m > 0)
      {
        if ((paramString.charAt(m - 1) != 65306) && (paramString.charAt(m - 1) != ':') && (paramString.charAt(m - 1) != '是'))
        {
          j = m;
          if (paramString.charAt(m - 1) != '为') {}
        }
        else
        {
          j = m - 1;
        }
        d2 = d1;
        if (StringProcess.EndWithDic(paramString.substring(0, j), bankCardNoFrontGuideWords, bankCardNoFrontGuideWordsMaxLength) != StringProcess.ContainEnum.None) {
          d2 = d1 * 2.5D;
        }
        d3 = d2;
        if (i == 0)
        {
          localObject = StringProcess.findAllResultsFromList(paramString, allBankNames, allBankNamesMaxLength);
          j = -1;
          m = Integer.MAX_VALUE;
          i = k;
          int n;
          if (i >= ((List)localObject).size()) {
            n = j;
          }
          for (;;)
          {
            if (n >= 0)
            {
              d1 = d2 * 4.0D;
              paramString = ((StringInt)((List)localObject).get(n)).getName();
              if (!paramString.equals("")) {
                paramEntityInfo.setRemark(getNormalizationName(paramString));
              }
              d2 = d1;
              if (d1 < 1.0D) {
                break;
              }
              return 1.0D;
              localObject = paramEntityInfo.getTarget_nomal().substring(0, j);
              String str2 = m + "$" + (String)localObject;
              if (lengthAndPrefixIndex2BankName.containsKey(str2))
              {
                str1 = (String)lengthAndPrefixIndex2BankName.get(str2);
                d1 = j * 0.15D;
                break label79;
              }
              str2 = m + 1 + "$" + (String)localObject;
              if (lengthAndPrefixIndex2BankName.containsKey(str2))
              {
                str1 = (String)lengthAndPrefixIndex2BankName.get(str2);
                d1 = j * 0.13D;
                break label79;
              }
              str2 = m - 1 + "$" + (String)localObject;
              if (lengthAndPrefixIndex2BankName.containsKey(str2))
              {
                str1 = (String)lengthAndPrefixIndex2BankName.get(str2);
                d1 = j * 0.13D;
                break label79;
              }
              str2 = m + 2 + "$" + (String)localObject;
              if (lengthAndPrefixIndex2BankName.containsKey(str2))
              {
                str1 = (String)lengthAndPrefixIndex2BankName.get(str2);
                d1 = 0.11D * j;
                break label79;
              }
              localObject = m - 2 + "$" + (String)localObject;
              if (lengthAndPrefixIndex2BankName.containsKey(localObject))
              {
                str1 = (String)lengthAndPrefixIndex2BankName.get(localObject);
                d1 = 0.11D * j;
                break label79;
              }
              if (i > 0) {
                i *= -1;
              }
              for (;;)
              {
                j = i + 6;
                break;
                i = i * -1 + 1;
              }
              k = ((StringInt)((List)localObject).get(i)).getNum();
              if (k < paramEntityInfo.getStartPosition()) {
                k = paramEntityInfo.getStartPosition() - k - ((StringInt)((List)localObject).get(i)).getName().length();
              }
              for (;;)
              {
                if (k <= 0)
                {
                  if (j >= 0)
                  {
                    n = j;
                    if (m - k * -1 <= 5) {
                      break;
                    }
                    n = j;
                    if (m / (k * -1 + 0.01D) < 2.0D) {
                      break;
                    }
                    n = i;
                    break;
                    if (k >= paramEntityInfo.getEndPosition())
                    {
                      k = paramEntityInfo.getEndPosition() - k;
                      continue;
                    }
                    Log.i("BankCardNumberRecognition", "ifRightNumber Error:\t" + paramString);
                    continue;
                  }
                  n = i;
                  break;
                }
              }
              if (m <= k) {
                break label885;
              }
              j = i;
            }
          }
        }
      }
      for (;;)
      {
        i += 1;
        m = k;
        break;
        d3 = d2;
        if (((List)localObject).size() == 0)
        {
          paramString = str1;
          d1 = 0.01D;
          break label276;
        }
        paramString = str1;
        d1 = d3;
        break label276;
        k = m;
      }
    }
  }
  
  private static boolean readBankCardBinNumberPrefix()
    throws IOException
  {
    Iterator localIterator = FileOperator.readFile(bankCardBinNumberPrefixFileName).iterator();
    for (;;)
    {
      if (!localIterator.hasNext()) {
        return true;
      }
      String str = (String)localIterator.next();
      String[] arrayOfString = str.split("\t");
      if (arrayOfString.length >= 3) {
        try
        {
          lengthAndPrefixIndex2BankName.put(arrayOfString[(arrayOfString.length - 2)].trim() + "$" + arrayOfString[(arrayOfString.length - 1)], arrayOfString[0].trim());
        }
        catch (Exception localException)
        {
          Log.i("BankCardNumberRecognition", "readBankCardBinNumberPrefix Error:\t" + str);
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.task.BankCardNumberRecognition
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */