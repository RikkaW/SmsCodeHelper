package com.xiaomi.smsunderstand;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class SMSBodyKeywordInfo
{
  private String[] areas;
  private List<String[]> keywords;
  private String patternName;
  private String[] phoneNumberParts;
  private BitSet phoneNumberPrefixs;
  private BitSet phoneNumberSuffixs;
  
  public SMSBodyKeywordInfo(List<String[]> paramList, String[] paramArrayOfString1, BitSet paramBitSet1, BitSet paramBitSet2, String[] paramArrayOfString2, String paramString)
  {
    keywords = paramList;
    phoneNumberParts = paramArrayOfString1;
    phoneNumberPrefixs = paramBitSet1;
    phoneNumberSuffixs = paramBitSet2;
    areas = paramArrayOfString2;
    patternName = paramString;
  }
  
  private int[] findFirstIndex(String paramString, int paramInt, String[] paramArrayOfString)
  {
    int j = Integer.MAX_VALUE;
    int k = Integer.MAX_VALUE;
    int i1 = paramArrayOfString.length;
    int i = 0;
    if (i >= i1)
    {
      if (j != Integer.MAX_VALUE) {
        return new int[] { j, k };
      }
    }
    else
    {
      String str = paramArrayOfString[i];
      int n = paramString.indexOf(str, paramInt);
      int m;
      if (n < 0) {
        m = j;
      }
      for (;;)
      {
        i += 1;
        j = m;
        break;
        m = j;
        if (j > n)
        {
          m = n;
          k = n + str.length();
        }
      }
    }
    return null;
  }
  
  private int matchArea(String paramString)
  {
    int j;
    if ((areas == null) || (areas.length == 0))
    {
      j = 0;
      return j;
    }
    int i;
    if ((areas != null) && (areas.length > 0))
    {
      if ((paramString == null) || (paramString.equals(""))) {
        return -1;
      }
      i = 0;
    }
    for (;;)
    {
      if (i >= areas.length) {
        return -1;
      }
      j = i;
      if (paramString.startsWith(areas[i])) {
        break;
      }
      i += 1;
    }
  }
  
  private int matchPhoneNumber(String paramString)
  {
    int j;
    if ((phoneNumberParts == null) || (phoneNumberParts.length == 0)) {
      j = 0;
    }
    int i;
    boolean bool1;
    do
    {
      return j;
      i = 0;
      if (i >= phoneNumberParts.length) {
        return -1;
      }
      bool1 = phoneNumberPrefixs.get(i);
      boolean bool2 = phoneNumberSuffixs.get(i);
      if ((!bool1) || (!bool2)) {
        break;
      }
      j = i;
    } while (paramString.equals(phoneNumberParts[i]));
    label104:
    do
    {
      do
      {
        i += 1;
        break;
        if (!bool1) {
          break label104;
        }
      } while (!paramString.startsWith(phoneNumberParts[i]));
      return i;
    } while (!paramString.endsWith(phoneNumberParts[i]));
    return i;
  }
  
  private boolean matchSMSBody(String paramString)
  {
    if ((keywords == null) || (keywords.size() == 0)) {}
    for (;;)
    {
      return true;
      int i = 0;
      Iterator localIterator = keywords.iterator();
      while (localIterator.hasNext())
      {
        int[] arrayOfInt = findFirstIndex(paramString, i, (String[])localIterator.next());
        if (arrayOfInt == null) {
          return false;
        }
        i = arrayOfInt[1];
      }
    }
  }
  
  public String getPatternName()
  {
    return patternName;
  }
  
  public boolean match(String paramString1, String paramString2, String paramString3)
  {
    if (matchArea(paramString2) < 0) {}
    while (matchPhoneNumber(paramString1) < 0) {
      return false;
    }
    return matchSMSBody(paramString3);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.smsunderstand.SMSBodyKeywordInfo
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */