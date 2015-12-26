package com.google.common.base;

public final class Objects$ToStringHelper
{
  private final StringBuilder builder;
  private boolean needsSeparator = false;
  
  private Objects$ToStringHelper(String paramString)
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

/* Location:
 * Qualified Name:     com.google.common.base.Objects.ToStringHelper
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */