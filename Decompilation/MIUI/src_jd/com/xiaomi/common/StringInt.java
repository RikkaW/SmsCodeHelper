package com.xiaomi.common;

public class StringInt
  implements ACAutomationable, Comparable<StringInt>
{
  private String name;
  private int num;
  
  public StringInt() {}
  
  public StringInt(int paramInt, String paramString)
  {
    num = paramInt;
    name = paramString;
  }
  
  public StringInt(String paramString, int paramInt)
  {
    num = paramInt;
    name = paramString;
  }
  
  public int compareTo(StringInt paramStringInt)
  {
    if (num > num) {
      return 1;
    }
    if (num == num) {
      return 0;
    }
    return -1;
  }
  
  public String getName()
  {
    return name;
  }
  
  public int getNum()
  {
    return num;
  }
  
  public String getWord()
  {
    return name;
  }
  
  public String toString()
  {
    return name + ":" + num;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.StringInt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */