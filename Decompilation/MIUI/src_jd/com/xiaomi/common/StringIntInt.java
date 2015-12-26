package com.xiaomi.common;

public class StringIntInt
  implements Comparable<StringIntInt>
{
  private int endIndex;
  private String name;
  private int startIndex;
  
  public StringIntInt(String paramString, int paramInt1, int paramInt2)
  {
    startIndex = paramInt1;
    endIndex = paramInt2;
    name = paramString;
  }
  
  public int compareTo(StringIntInt paramStringIntInt)
  {
    if (startIndex < startIndex) {}
    do
    {
      return -1;
      if (startIndex > startIndex) {
        return 1;
      }
    } while (endIndex < endIndex);
    if (endIndex > endIndex) {
      return 1;
    }
    return 0;
  }
  
  public int getEndIndex()
  {
    return endIndex;
  }
  
  public String getName()
  {
    return name;
  }
  
  public int getStartIndex()
  {
    return startIndex;
  }
  
  public void setEndIndex(int paramInt)
  {
    endIndex = paramInt;
  }
  
  public void setStartIndex(int paramInt)
  {
    startIndex = paramInt;
  }
  
  public String toString()
  {
    return name + "\t" + startIndex + "\t" + endIndex;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.StringIntInt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */