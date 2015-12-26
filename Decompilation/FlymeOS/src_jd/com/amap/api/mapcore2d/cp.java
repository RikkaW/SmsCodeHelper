package com.amap.api.mapcore2d;

public final class cp
{
  private cp()
  {
    throw new AssertionError("Uninstantiable");
  }
  
  public static <T> T a(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException("null reference");
    }
    return paramT;
  }
  
  public static <T> T a(T paramT, Object paramObject)
  {
    if (paramT == null) {
      throw new NullPointerException(String.valueOf(paramObject));
    }
    return paramT;
  }
  
  public static void a(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalStateException(String.valueOf(paramObject));
    }
  }
  
  public static void a(boolean paramBoolean, String paramString, Object[] paramArrayOfObject)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException(String.format(paramString, paramArrayOfObject));
    }
  }
  
  public static void b(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException(String.valueOf(paramObject));
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */