package com.xiaomi.common;

public class Bisection
{
  public static int thr = 150;
  
  public static int bisectionSearch(int paramInt, int[] paramArrayOfInt)
  {
    int k = 0;
    int j = paramArrayOfInt.length - 1;
    int i = 0;
    if (paramInt == paramArrayOfInt[j]) {
      return j;
    }
    int m;
    if (paramInt == paramArrayOfInt[0])
    {
      return 0;
      if (paramArrayOfInt[m] >= paramInt) {
        break label76;
      }
      k = m;
    }
    for (;;)
    {
      i += 1;
      if (2 > j - k) {
        return -1;
      }
      m = k + (j - k) / 2;
      if (paramArrayOfInt[m] != paramInt) {
        break;
      }
      return m;
      label76:
      j = m;
    }
  }
  
  public static int bisectionSearch(int paramInt, IntInt[] paramArrayOfIntInt)
  {
    int m = 0;
    int k = paramArrayOfIntInt.length - 1;
    int i = 0;
    int j = 0;
    if (paramInt == paramArrayOfIntInt[k].getFirst()) {
      paramInt = k;
    }
    for (i = j;; i = j)
    {
      Log.println("loops = " + i);
      return paramInt;
      if (paramInt != paramArrayOfIntInt[0].getFirst()) {
        break;
      }
      paramInt = 0;
    }
    label67:
    if (paramArrayOfIntInt[j].getFirst() < paramInt) {
      m = j;
    }
    for (;;)
    {
      i += 1;
      if (2 > k - m)
      {
        paramInt = -1;
        break;
      }
      j = m + (k - m) / 2;
      if (paramArrayOfIntInt[j].getFirst() != paramInt) {
        break label67;
      }
      paramInt = j;
      break;
      k = j;
    }
  }
  
  public static int orderSearch(int paramInt, int[] paramArrayOfInt)
  {
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= paramArrayOfInt.length) {
        j = -1;
      }
      do
      {
        return j;
        j = i;
      } while (paramArrayOfInt[i] == paramInt);
      i += 1;
    }
  }
  
  public static int orderSearch(int paramInt, IntInt[] paramArrayOfIntInt)
  {
    int i = 0;
    for (;;)
    {
      int j;
      if (i >= paramArrayOfIntInt.length) {
        j = -1;
      }
      do
      {
        return j;
        j = i;
      } while (paramArrayOfIntInt[i].getFirst() == paramInt);
      i += 1;
    }
  }
  
  public static int search(int paramInt, int[] paramArrayOfInt)
  {
    if (paramArrayOfInt.length > thr) {
      return bisectionSearch(paramInt, paramArrayOfInt);
    }
    return orderSearch(paramInt, paramArrayOfInt);
  }
  
  public static int search(int paramInt, IntInt[] paramArrayOfIntInt)
  {
    if (paramArrayOfIntInt.length > thr) {
      return bisectionSearch(paramInt, paramArrayOfIntInt);
    }
    return orderSearch(paramInt, paramArrayOfIntInt);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.Bisection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */