package com.google.common.primitives;

import java.util.Comparator;

public final class UnsignedBytes
{
  public static int compare(byte paramByte1, byte paramByte2)
  {
    return toInt(paramByte1) - toInt(paramByte2);
  }
  
  static Comparator<byte[]> lexicographicalComparatorJavaImpl()
  {
    return UnsignedBytes.LexicographicalComparatorHolder.PureJavaComparator.INSTANCE;
  }
  
  public static int toInt(byte paramByte)
  {
    return paramByte & 0xFF;
  }
  
  static class LexicographicalComparatorHolder
  {
    static final Comparator<byte[]> BEST_COMPARATOR = UnsignedBytes.lexicographicalComparatorJavaImpl();
    static final String UNSAFE_COMPARATOR_NAME = LexicographicalComparatorHolder.class.getName() + "$UnsafeComparator";
    
    static enum PureJavaComparator
      implements Comparator<byte[]>
    {
      INSTANCE;
      
      private PureJavaComparator() {}
      
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
  }
}

/* Location:
 * Qualified Name:     com.google.common.primitives.UnsignedBytes
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */