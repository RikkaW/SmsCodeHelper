package com.xiaomi.nlp;

import com.xiaomi.common.Bisection;
import com.xiaomi.common.IntInt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class PatternForNER
{
  public static final String endPatternReplaceStr = String.valueOf('\026');
  public static final String startPatternReplaceStr = String.valueOf('\025');
  private static String[] toBeRepace = { "<", "+<", ">", ">+", "yyyy", "+<#yyyy>+", "MM", "+<#MM>+", "dd", "+<#dd>+", "HH", "+<#HH>+", "mm", "+<#mm>+", "ss", "+<#ss>+", "$End", endPatternReplaceStr, "$Start", startPatternReplaceStr, "\\r", "\r", "\\n", "\n" };
  public List<PatternActionContent> constraintContent;
  public int containSign = 0;
  private int[] endKeywords;
  public boolean isRegularExpression = false;
  private int[] keywords;
  public String leftProduction;
  private IntInt[] mustKeywords;
  public String pattern;
  public String remark;
  public List<PatternActionContent> resultContent;
  public double score;
  private int[] startKeywords;
  
  public PatternForNER(String paramString1, String paramString2, String paramString3)
  {
    initail(paramString1, paramString2, paramString3);
  }
  
  private String changePattern(String paramString)
  {
    if (isRegularExpression) {
      return paramString;
    }
    paramString = replaceAll(paramString.trim().replace(65306, ':').replace(65292, ',')).split("[\\+]+");
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    if (i >= paramString.length)
    {
      if (localStringBuffer.charAt(localStringBuffer.length() - 1) != '+') {
        break label235;
      }
      localStringBuffer.deleteCharAt(localStringBuffer.length() - 1);
    }
    for (;;)
    {
      return localStringBuffer.toString();
      if (paramString[i].length() == 0) {}
      for (;;)
      {
        i += 1;
        break;
        if ((paramString[i].charAt(0) == '<') && (paramString[i].charAt(paramString[i].length() - 1) == '>'))
        {
          if (paramString[i].charAt(1) == '?')
          {
            if ((localStringBuffer.length() > 0) && (localStringBuffer.charAt(localStringBuffer.length() - 1) == '+')) {
              localStringBuffer.replace(localStringBuffer.length() - 1, localStringBuffer.length(), "&&&");
            }
            localStringBuffer.append(paramString[i]).append("&&&");
          }
          else
          {
            localStringBuffer.append(paramString[i]).append('+');
          }
        }
        else {
          localStringBuffer.append('<').append(paramString[i]).append(">+");
        }
      }
      label235:
      if (localStringBuffer.substring(localStringBuffer.length() - "&&&".length()).equals("&&&")) {
        localStringBuffer.delete(localStringBuffer.length() - "&&&".length(), localStringBuffer.length());
      }
    }
  }
  
  private void initail(String paramString1, String paramString2, String paramString3)
  {
    leftProduction = paramString1;
    if (paramString1.endsWith("-R>")) {
      isRegularExpression = true;
    }
    score = 0.0D;
    pattern = changePattern(paramString2);
    paramString1 = paramString3.split("\t");
    constraintContent = new ArrayList(3);
    resultContent = new ArrayList(3);
    int i = 0;
    if (i >= paramString1.length) {
      return;
    }
    if (paramString1[i].equals("")) {}
    for (;;)
    {
      i += 1;
      break;
      Parser.printMemory("PatternActionContent begin " + paramString1[i]);
      paramString2 = new PatternActionContent(paramString1[i]);
      Parser.printMemory("PatternActionContent end " + paramString1[i]);
      switch (keywords)
      {
      case yyyy: 
      default: 
        constraintContent.add(paramString2);
        break;
      case Extract: 
        remark = parameter;
        break;
      case EqualDic: 
        score = Double.valueOf(parameter).doubleValue();
        break;
      case Accept: 
        resultContent.add(paramString2);
      }
    }
  }
  
  private String replaceAll(String paramString)
  {
    boolean[] arrayOfBoolean = new boolean[toBeRepace.length / 2];
    StringBuffer localStringBuffer = new StringBuffer(paramString.length() + 20);
    int k = 0;
    int j = 1;
    int m;
    int i;
    int n;
    for (;;)
    {
      if (j == 0)
      {
        if (k < paramString.length()) {
          localStringBuffer.append(paramString.substring(k));
        }
        return localStringBuffer.toString();
      }
      m = Integer.MAX_VALUE;
      i = 0;
      n = -1;
      j = 0;
      if (j < toBeRepace.length / 2) {
        break;
      }
      j = i;
      if (n >= 0)
      {
        localStringBuffer.append(paramString.substring(k, m)).append(toBeRepace[(n * 2 + 1)]);
        k = toBeRepace[(n * 2)].length() + m;
        j = i;
      }
    }
    int i1;
    if (arrayOfBoolean[j] != 0) {
      i1 = m;
    }
    for (;;)
    {
      j += 1;
      m = i1;
      break;
      int i2 = paramString.indexOf(toBeRepace[(j * 2)], k);
      if (i2 < 0)
      {
        arrayOfBoolean[j] = true;
        i1 = m;
      }
      else
      {
        i1 = m;
        if (m > i2)
        {
          n = j;
          i1 = i2;
          i = 1;
        }
      }
    }
  }
  
  public boolean containsEnd()
  {
    return (containSign & 0x1) > 0;
  }
  
  public boolean containsStart()
  {
    return (containSign & 0x2) > 0;
  }
  
  public boolean containsStartRegularExpress()
  {
    return (containSign & 0x4) > 0;
  }
  
  public boolean containsWildcard()
  {
    return (containSign & 0x8) > 0;
  }
  
  public int findKeywords(int paramInt1, int paramInt2)
  {
    switch (paramInt2)
    {
    case 3: 
    case 5: 
    case 6: 
    case 7: 
    default: 
      return -1;
    case 1: 
      return Bisection.search(paramInt1, keywords);
    case 2: 
      return Bisection.search(paramInt1, startKeywords);
    case 4: 
      return Bisection.search(paramInt1, endKeywords);
    }
    return Bisection.search(paramInt1, mustKeywords);
  }
  
  public int[] getKeywords()
  {
    return keywords;
  }
  
  public IntInt[] getMustKeywords()
  {
    return mustKeywords;
  }
  
  public int[] getStartKeywords()
  {
    return startKeywords;
  }
  
  public void setAllKeywords(TreeMap<Integer, Integer> paramTreeMap, int[] paramArrayOfInt)
  {
    keywords = new int[paramArrayOfInt[0]];
    int n = 0;
    startKeywords = new int[paramArrayOfInt[1]];
    int j = 0;
    endKeywords = new int[paramArrayOfInt[2]];
    int k = 0;
    mustKeywords = new IntInt[paramArrayOfInt[3]];
    int i = 0;
    paramTreeMap = paramTreeMap.entrySet().iterator();
    for (;;)
    {
      if (!paramTreeMap.hasNext()) {
        return;
      }
      paramArrayOfInt = (Map.Entry)paramTreeMap.next();
      int i3 = ((Integer)paramArrayOfInt.getValue()).intValue();
      int m = n;
      if (KeywordsType.isTypeEqual(i3, 1))
      {
        keywords[n] = ((Integer)paramArrayOfInt.getKey()).intValue();
        m = n + 1;
      }
      int i1 = j;
      if (KeywordsType.isTypeEqual(i3, 4))
      {
        endKeywords[j] = ((Integer)paramArrayOfInt.getKey()).intValue();
        i1 = j + 1;
      }
      int i2 = k;
      if (KeywordsType.isTypeEqual(i3, 2))
      {
        startKeywords[k] = ((Integer)paramArrayOfInt.getKey()).intValue();
        i2 = k + 1;
      }
      k = i2;
      n = m;
      j = i1;
      if (KeywordsType.isTypeEqual(i3, 8))
      {
        mustKeywords[i] = new IntInt(((Integer)paramArrayOfInt.getKey()).intValue(), i3 >> 16);
        i += 1;
        k = i2;
        n = m;
        j = i1;
      }
    }
  }
  
  public void setContainsEnd()
  {
    containSign |= 0x1;
  }
  
  public void setContainsStart()
  {
    containSign |= 0x2;
  }
  
  public void setContainsStartRegularExpress()
  {
    containSign |= 0x4;
  }
  
  public void setContainsWildcard()
  {
    containSign |= 0x8;
  }
  
  public String toString()
  {
    return leftProduction + " ::= " + pattern;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.PatternForNER
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */