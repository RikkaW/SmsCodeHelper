package com.xiaomi.smsunderstand;

import com.xiaomi.common.ACAutomationable;
import java.util.List;

public class StringAndIntArray
  implements ACAutomationable
{
  private List<Integer> indexs;
  private String key;
  
  public StringAndIntArray(String paramString, List<Integer> paramList)
  {
    key = paramString;
    indexs = paramList;
  }
  
  public List<Integer> getIndexs()
  {
    return indexs;
  }
  
  public String getWord()
  {
    return key;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.StringAndIntArray
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */