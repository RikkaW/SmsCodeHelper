package com.google.common.collect;

import java.lang.reflect.Array;

class Platform
{
  static <T> T[] clone(T[] paramArrayOfT)
  {
    return (Object[])paramArrayOfT.clone();
  }
  
  static <T> T[] newArray(T[] paramArrayOfT, int paramInt)
  {
    return (Object[])Array.newInstance(paramArrayOfT.getClass().getComponentType(), paramInt);
  }
  
  static MapMaker tryWeakKeys(MapMaker paramMapMaker)
  {
    return paramMapMaker.weakKeys();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Platform
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */