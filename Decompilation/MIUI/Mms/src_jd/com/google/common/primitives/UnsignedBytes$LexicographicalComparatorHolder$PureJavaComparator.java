package com.google.common.primitives;

import java.util.Comparator;

 enum UnsignedBytes$LexicographicalComparatorHolder$PureJavaComparator
  implements Comparator<byte[]>
{
  INSTANCE;
  
  private UnsignedBytes$LexicographicalComparatorHolder$PureJavaComparator() {}
  
  public int compare(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int j = Math.min(paramArrayOfByte1.length, paramArrayOfByte2.length);
    int i = 0;
    while (i < j)
    {
      int k = UnsignedBytes.compare(paramArrayOfByte1[i], paramArrayOfByte2[i]);
      if (k != 0) {
        return k;
      }
      i += 1;
    }
    return paramArrayOfByte1.length - paramArrayOfByte2.length;
  }
}

/* Location:
 * Qualified Name:     com.google.common.primitives.UnsignedBytes.LexicographicalComparatorHolder.PureJavaComparator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */