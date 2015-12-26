package com.xiaomi.nlp;

import com.xiaomi.common.ConstraintACAutomationable;
import java.util.List;

public class ParserKeywords
  implements ConstraintACAutomationable
{
  private String keyWords;
  private int[] toPatIndex;
  private int type = 1;
  
  public ParserKeywords(String paramString, int paramInt)
  {
    keyWords = paramString;
    type = paramInt;
  }
  
  public void addPatIndex(List<Integer> paramList)
  {
    toPatIndex = new int[paramList.size()];
    int i = 0;
    for (;;)
    {
      if (i >= paramList.size()) {
        return;
      }
      toPatIndex[i] = ((Integer)paramList.get(i)).intValue();
      i += 1;
    }
  }
  
  public String getKeyWords()
  {
    return keyWords;
  }
  
  public int[] getPatIndex()
  {
    return toPatIndex;
  }
  
  public int getType()
  {
    return type;
  }
  
  public String getWord()
  {
    return keyWords;
  }
  
  public boolean isEndkeyWords()
  {
    return (type & 0x4) > 0;
  }
  
  public boolean isStartKeyWords()
  {
    return (type & 0x2) > 0;
  }
  
  public boolean isStartKeywordsContainsWildcard()
  {
    return (type & 0x10) > 0;
  }
  
  public boolean isTypeEqual(int paramInt)
  {
    return (type & paramInt) > 0;
  }
  
  public void setKeyWordsType(int paramInt)
  {
    type |= paramInt;
  }
  
  public String toString()
  {
    return keyWords + ":" + type;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.nlp.ParserKeywords
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */