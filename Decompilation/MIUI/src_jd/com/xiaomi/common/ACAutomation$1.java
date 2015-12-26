package com.xiaomi.common;

import java.util.Comparator;

class ACAutomation$1
  implements Comparator<int[]>
{
  ACAutomation$1(ACAutomation paramACAutomation) {}
  
  public int compare(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    if (paramArrayOfInt1[1] != paramArrayOfInt2[1]) {
      return paramArrayOfInt1[1] - paramArrayOfInt2[1];
    }
    return paramArrayOfInt2[2] - paramArrayOfInt1[2];
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.common.ACAutomation.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */