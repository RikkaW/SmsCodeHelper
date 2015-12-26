package com.xiaomi.nlp;

import com.xiaomi.common.ConstantData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

public class NLP
{
  public static Pattern rxChinese = Pattern.compile("^[，。；？~！：‘“”’【】（）一-龥豈-鶴]$");
  public static Pattern rxChineseWord = Pattern.compile("[一-龥豈-鶴]+");
  private static HashMap<String, HashSet<String>> sentenceTrimStartPos = new HashMap();
  private RuleFunction ruleFunction = null;
  
  public NLP() {}
  
  public NLP(int paramInt) {}
  
  public NLP(int paramInt, Map<String, ? extends ArrayList<? extends KeyWordAccessable>> paramMap)
  {
    ruleFunction = new RuleFunction(paramInt);
    ruleFunction.loadConstants(paramMap);
  }
  
  public static boolean EqualDic_Seg(String paramString1, String paramString2)
  {
    return ((HashSet)ConstantData.DICs.get(paramString2)).contains(getWordsFromSeg(paramString1));
  }
  
  public static String getWordsFromSeg(String paramString)
  {
    String str = "";
    String[] arrayOfString = paramString.split(" ", 0);
    int j = arrayOfString.length;
    int i = 0;
    paramString = str;
    if (i >= j) {
      return paramString;
    }
    str = arrayOfString[i];
    int k = str.lastIndexOf("/");
    if (k >= 0) {}
    for (paramString = paramString + str.substring(0, k);; paramString = paramString + str)
    {
      i += 1;
      break;
    }
  }
  
  public final boolean contain(String paramString1, String paramString2)
  {
    if (paramString1.equals("")) {
      return false;
    }
    return ruleFunction.contain(paramString1, paramString2);
  }
  
  public final boolean containDic(String paramString1, String paramString2)
  {
    if (paramString2.charAt(0) == '#') {
      return contain(paramString1, "<" + paramString2.substring(1) + ">");
    }
    if (paramString2.charAt(0) != '<') {
      return contain(paramString1, "<" + paramString2.substring(1) + ">");
    }
    return contain(paramString1, paramString2);
  }
  
  public final boolean containPOS(String paramString1, String paramString2)
  {
    if (paramString1.length() == 0) {}
    do
    {
      return false;
      if (paramString2.charAt(0) == '#') {
        return contain(paramString1, "<*/" + paramString2.substring(1) + ">");
      }
      localObject = (HashSet)ConstantData.DICs.get(paramString2);
    } while (((HashSet)localObject).size() == 0);
    paramString2 = new StringBuffer();
    Object localObject = ((HashSet)localObject).iterator();
    for (;;)
    {
      if (!((Iterator)localObject).hasNext()) {
        return contain(paramString1, "<" + paramString2.deleteCharAt(paramString2.length() - 1).toString() + ">");
      }
      String str = (String)((Iterator)localObject).next();
      paramString2.append("*/" + str + "|");
    }
  }
  
  public final boolean endWithDic(String paramString1, String paramString2)
  {
    if (paramString2.charAt(0) == '#') {
      return ruleFunction.endWith(paramString1, "<" + paramString2.substring(1) + ">");
    }
    if (paramString2.charAt(0) != '<') {
      return ruleFunction.endWith(paramString1, "<!" + paramString2 + ">");
    }
    return ruleFunction.endWith(paramString1, paramString2);
  }
  
  public final boolean endWithPOS(String paramString1, String paramString2)
  {
    if (paramString1.length() == 0) {}
    int k;
    do
    {
      return false;
      k = paramString1.lastIndexOf("/");
    } while (k < 0);
    int j = paramString1.indexOf(" ", k + 1);
    int i = j;
    if (j < 0) {
      i = paramString1.length();
    }
    if (paramString2.charAt(0) == '#') {
      return paramString1.substring(k + 1, i).equals(paramString2.substring(1));
    }
    return ((HashSet)ConstantData.DICs.get(paramString2)).contains(paramString1.substring(k + 1, i));
  }
  
  public final boolean noEndWithPOS(String paramString1, String paramString2)
  {
    return !endWithPOS(paramString1, paramString2);
  }
  
  public boolean patMatch(String paramString1, String paramString2, ArrayList<Integer> paramArrayList)
  {
    return ruleFunction.strEqual(paramString1, paramString2, paramArrayList);
  }
  
  public final boolean startWithDic(String paramString1, String paramString2)
  {
    if (paramString2.charAt(0) == '#') {
      return ruleFunction.startWith(paramString1, "<" + paramString2.substring(1) + ">");
    }
    if (paramString2.charAt(0) != '<') {
      return ruleFunction.startWith(paramString1, "<!" + paramString2 + ">");
    }
    return ruleFunction.startWith(paramString1, paramString2);
  }
  
  public final boolean startWithPOS(String paramString1, String paramString2)
  {
    if (paramString1.length() == 0) {}
    int k;
    do
    {
      return false;
      k = paramString1.indexOf("/");
    } while (k < 0);
    int j = paramString1.indexOf(" ", k + 1);
    int i = j;
    if (j < 0) {
      i = paramString1.length();
    }
    if (paramString2.charAt(0) == '#') {
      return paramString1.substring(k + 1, i).equals(paramString2.substring(1));
    }
    return ((HashSet)ConstantData.DICs.get(paramString2)).contains(paramString1.substring(k + 1, i));
  }
  
  public final boolean strEqual(String paramString1, String paramString2)
  {
    if (paramString1.equals("")) {
      return false;
    }
    return ruleFunction.strEqual(paramString1, paramString2);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.NLP
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */