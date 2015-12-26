package com.xiaomi.common;

import com.xiaomi.nlp.KeyWordAccessable;

public class StringString
  implements ACAutomationable, KeyWordAccessable, Comparable<StringString>
{
  private String first;
  private String second;
  
  public StringString(String paramString1, String paramString2)
  {
    first = paramString1;
    second = paramString2;
  }
  
  public int compareTo(StringString paramStringString)
  {
    return first.compareTo(first);
  }
  
  public String getFirst()
  {
    return first;
  }
  
  public String getKeyWord()
  {
    return first;
  }
  
  public String getSecond()
  {
    return second;
  }
  
  public String getWord()
  {
    return first;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.StringString
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */