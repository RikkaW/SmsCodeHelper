package com.google.common.base;

public final class Preconditions
{
  private static String badPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    if (paramInt1 < 0) {
      return format("%s (%s) must not be negative", new Object[] { paramString, Integer.valueOf(paramInt1) });
    }
    if (paramInt2 < 0) {
      throw new IllegalArgumentException("negative size: " + paramInt2);
    }
    return format("%s (%s) must not be greater than size (%s)", new Object[] { paramString, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }
  
  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException();
    }
  }
  
  public static void checkArgument(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException(String.valueOf(paramObject));
    }
  }
  
  public static void checkArgument(boolean paramBoolean, String paramString, Object... paramVarArgs)
  {
    if (!paramBoolean) {
      throw new IllegalArgumentException(format(paramString, paramVarArgs));
    }
  }
  
  public static <T> T checkNotNull(T paramT)
  {
    if (paramT == null) {
      throw new NullPointerException();
    }
    return paramT;
  }
  
  public static <T> T checkNotNull(T paramT, Object paramObject)
  {
    if (paramT == null) {
      throw new NullPointerException(String.valueOf(paramObject));
    }
    return paramT;
  }
  
  public static int checkPositionIndex(int paramInt1, int paramInt2)
  {
    return checkPositionIndex(paramInt1, paramInt2, "index");
  }
  
  public static int checkPositionIndex(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 < 0) || (paramInt1 > paramInt2)) {
      throw new IndexOutOfBoundsException(badPositionIndex(paramInt1, paramInt2, paramString));
    }
    return paramInt1;
  }
  
  public static void checkState(boolean paramBoolean)
  {
    if (!paramBoolean) {
      throw new IllegalStateException();
    }
  }
  
  public static void checkState(boolean paramBoolean, Object paramObject)
  {
    if (!paramBoolean) {
      throw new IllegalStateException(String.valueOf(paramObject));
    }
  }
  
  public static void checkState(boolean paramBoolean, String paramString, Object... paramVarArgs)
  {
    if (!paramBoolean) {
      throw new IllegalStateException(format(paramString, paramVarArgs));
    }
  }
  
  static String format(String paramString, Object... paramVarArgs)
  {
    paramString = String.valueOf(paramString);
    StringBuilder localStringBuilder = new StringBuilder(paramString.length() + paramVarArgs.length * 16);
    int j = 0;
    int i = 0;
    for (;;)
    {
      int k;
      if (i < paramVarArgs.length)
      {
        k = paramString.indexOf("%s", j);
        if (k != -1) {}
      }
      else
      {
        localStringBuilder.append(paramString.substring(j));
        if (i >= paramVarArgs.length) {
          break label159;
        }
        localStringBuilder.append(" [");
        localStringBuilder.append(paramVarArgs[i]);
        i += 1;
        while (i < paramVarArgs.length)
        {
          localStringBuilder.append(", ");
          localStringBuilder.append(paramVarArgs[i]);
          i += 1;
        }
      }
      localStringBuilder.append(paramString.substring(j, k));
      localStringBuilder.append(paramVarArgs[i]);
      j = k + 2;
      i += 1;
    }
    localStringBuilder.append(']');
    label159:
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Preconditions
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */