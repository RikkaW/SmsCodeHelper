package com.google.common.collect;

final class Hashing
{
  static int smear(int paramInt)
  {
    paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
    return paramInt >>> 7 ^ paramInt ^ paramInt >>> 4;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Hashing
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */