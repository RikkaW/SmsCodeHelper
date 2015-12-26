package com.google.common.base;

public final class Objects
{
  public static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }
  
  public static <T> T firstNonNull(T paramT1, T paramT2)
  {
    if (paramT1 != null) {
      return paramT1;
    }
    return (T)Preconditions.checkNotNull(paramT2);
  }
  
  private static String simpleName(Class<?> paramClass)
  {
    paramClass = paramClass.getName().replaceAll("\\$[0-9]+", "\\$");
    int j = paramClass.lastIndexOf('$');
    int i = j;
    if (j == -1) {
      i = paramClass.lastIndexOf('.');
    }
    return paramClass.substring(i + 1);
  }
  
  public static ToStringHelper toStringHelper(Object paramObject)
  {
    return new ToStringHelper(simpleName(paramObject.getClass()), null);
  }
  
  public static final class ToStringHelper
  {
    private final StringBuilder builder;
    private boolean needsSeparator = false;
    
    private ToStringHelper(String paramString)
    {
      Preconditions.checkNotNull(paramString);
      builder = new StringBuilder(32).append(paramString).append('{');
    }
    
    private StringBuilder checkNameAndAppend(String paramString)
    {
      Preconditions.checkNotNull(paramString);
      return maybeAppendSeparator().append(paramString).append('=');
    }
    
    private StringBuilder maybeAppendSeparator()
    {
      if (needsSeparator) {
        return builder.append(", ");
      }
      needsSeparator = true;
      return builder;
    }
    
    public ToStringHelper add(String paramString, int paramInt)
    {
      checkNameAndAppend(paramString).append(paramInt);
      return this;
    }
    
    public ToStringHelper add(String paramString, Object paramObject)
    {
      checkNameAndAppend(paramString).append(paramObject);
      return this;
    }
    
    public ToStringHelper addValue(Object paramObject)
    {
      maybeAppendSeparator().append(paramObject);
      return this;
    }
    
    public String toString()
    {
      try
      {
        String str = '}';
        return str;
      }
      finally
      {
        builder.setLength(builder.length() - 1);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Objects
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */