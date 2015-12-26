package com.google.common.base;

final class Platform
{
  private static final ThreadLocal<char[]> DEST_TL = new ThreadLocal()
  {
    protected char[] initialValue()
    {
      return new char['Ѐ'];
    }
  };
  
  static long systemNanoTime()
  {
    return System.nanoTime();
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Platform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */