package com.xiaomi.nlp;

import com.xiaomi.common.ConstraintACAutomation;
import com.xiaomi.common.StringProcess;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ParserKeywordsDic
{
  private ConstraintACAutomation keyWordsACAutomation;
  private List<ParserKeywords> keywords = new ArrayList();
  private int mustKeywordsCount = 0;
  private Map<String, Integer> tempKeywords2Index = new HashMap();
  
  public void addKeyWordsPatIndex(int paramInt, List<Integer> paramList)
  {
    ((ParserKeywords)keywords.get(paramInt)).addPatIndex(paramList);
  }
  
  public int addKeywords(String paramString, int paramInt)
  {
    if (tempKeywords2Index == null) {
      return -1;
    }
    int i = getKeyWordsIndex(paramString);
    if (i >= 0)
    {
      if ((paramInt == 8) && (!getKeywords(i).isTypeEqual(8))) {
        mustKeywordsCount += 1;
      }
      setKeyWordsType(i, paramInt);
      return i;
    }
    ParserKeywords localParserKeywords = new ParserKeywords(paramString, paramInt);
    if (paramInt == 8) {
      mustKeywordsCount += 1;
    }
    keywords.add(localParserKeywords);
    tempKeywords2Index.put(paramString, Integer.valueOf(keywords.size() - 1));
    return keywords.size() - 1;
  }
  
  public boolean buildDic()
  {
    if (tempKeywords2Index == null) {
      return false;
    }
    keyWordsACAutomation = new ConstraintACAutomation(keywords);
    tempKeywords2Index = null;
    return true;
  }
  
  public List<int[]> find(String paramString, int paramInt)
  {
    return keyWordsACAutomation.find(paramString, paramInt);
  }
  
  public int[] findFirst(String paramString, int paramInt1, int paramInt2)
  {
    return keyWordsACAutomation.findFirst(paramString, paramInt1, paramInt2);
  }
  
  public int[] findFirst(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return keyWordsACAutomation.findFirst(paramString, paramInt1, paramInt2, paramInt3);
  }
  
  public int[] findFirst(String paramString, int paramInt, Set<Integer> paramSet)
  {
    int j = Integer.MAX_VALUE;
    int k = -1;
    paramSet = paramSet.iterator();
    label180:
    for (;;)
    {
      if (!paramSet.hasNext())
      {
        if (k >= 0) {
          return new int[] { k, j, getKeywords(k).getKeyWords().length() + j - 1 };
        }
      }
      else
      {
        Integer localInteger = (Integer)paramSet.next();
        String str = getKeywords(localInteger.intValue()).getKeyWords();
        int i;
        if (str.equals("CHAR")) {
          i = StringProcess.findFirstChar(paramString, paramInt);
        }
        for (;;)
        {
          if ((i < 0) || (i >= j)) {
            break label180;
          }
          k = localInteger.intValue();
          j = i;
          break;
          if (str.equals("NUM")) {
            i = StringProcess.findFirstNumber(paramString, paramInt);
          } else if (str.equals("ENG")) {
            i = StringProcess.findFirstEng(paramString, paramInt);
          } else {
            i = paramString.indexOf(str, paramInt);
          }
        }
      }
    }
    return null;
  }
  
  public List<int[]> findFirstAll(String paramString, int paramInt1, int paramInt2)
  {
    return keyWordsACAutomation.findFirstAll(paramString, paramInt1, paramInt2);
  }
  
  public List<int[]> findFirstAll(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    return keyWordsACAutomation.findFirstAll(paramString, paramInt1, paramInt2, paramInt3);
  }
  
  public int getKeyWordsIndex(String paramString)
  {
    if (keyWordsACAutomation != null) {
      return keyWordsACAutomation.contains(paramString, 1);
    }
    paramString = (Integer)tempKeywords2Index.get(paramString);
    if (paramString == null) {
      return -1;
    }
    return paramString.intValue();
  }
  
  public ParserKeywords getKeywords(int paramInt)
  {
    return (ParserKeywords)keywords.get(paramInt);
  }
  
  public int getMustKeywordsCount()
  {
    return mustKeywordsCount;
  }
  
  public int[] getPatIndexByKeywords(int paramInt)
  {
    return ((ParserKeywords)keywords.get(paramInt)).getPatIndex();
  }
  
  public boolean isStartKeyWords(int paramInt)
  {
    return ((ParserKeywords)keywords.get(paramInt)).isStartKeyWords();
  }
  
  public void setKeyWordsType(int paramInt1, int paramInt2)
  {
    ((ParserKeywords)keywords.get(paramInt1)).setKeyWordsType(paramInt2);
  }
  
  public List<int[]> startWith(String paramString, int paramInt1, int paramInt2)
  {
    return keyWordsACAutomation.startWith(paramString, paramInt1, paramInt2);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.ParserKeywordsDic
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */