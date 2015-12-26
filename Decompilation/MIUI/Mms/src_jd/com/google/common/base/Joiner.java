package com.google.common.base;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class Joiner
{
  private final String separator;
  
  private Joiner(String paramString)
  {
    separator = ((String)Preconditions.checkNotNull(paramString));
  }
  
  public static Joiner on(String paramString)
  {
    return new Joiner(paramString);
  }
  
  public <A extends Appendable> A appendTo(A paramA, Iterator<?> paramIterator)
    throws IOException
  {
    Preconditions.checkNotNull(paramA);
    if (paramIterator.hasNext())
    {
      paramA.append(toString(paramIterator.next()));
      while (paramIterator.hasNext())
      {
        paramA.append(separator);
        paramA.append(toString(paramIterator.next()));
      }
    }
    return paramA;
  }
  
  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Iterable<?> paramIterable)
  {
    return appendTo(paramStringBuilder, paramIterable.iterator());
  }
  
  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Iterator<?> paramIterator)
  {
    try
    {
      appendTo(paramStringBuilder, paramIterator);
      return paramStringBuilder;
    }
    catch (IOException paramStringBuilder)
    {
      throw new AssertionError(paramStringBuilder);
    }
  }
  
  public final StringBuilder appendTo(StringBuilder paramStringBuilder, Object[] paramArrayOfObject)
  {
    return appendTo(paramStringBuilder, Arrays.asList(paramArrayOfObject));
  }
  
  CharSequence toString(Object paramObject)
  {
    Preconditions.checkNotNull(paramObject);
    if ((paramObject instanceof CharSequence)) {
      return (CharSequence)paramObject;
    }
    return paramObject.toString();
  }
  
  public MapJoiner withKeyValueSeparator(String paramString)
  {
    return new MapJoiner(this, paramString, null);
  }
  
  public static final class MapJoiner
  {
    private final Joiner joiner;
    private final String keyValueSeparator;
    
    private MapJoiner(Joiner paramJoiner, String paramString)
    {
      joiner = paramJoiner;
      keyValueSeparator = ((String)Preconditions.checkNotNull(paramString));
    }
    
    public <A extends Appendable> A appendTo(A paramA, Iterator<? extends Map.Entry<?, ?>> paramIterator)
      throws IOException
    {
      Preconditions.checkNotNull(paramA);
      if (paramIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramIterator.next();
        paramA.append(joiner.toString(localEntry.getKey()));
        paramA.append(keyValueSeparator);
        paramA.append(joiner.toString(localEntry.getValue()));
        while (paramIterator.hasNext())
        {
          paramA.append(joiner.separator);
          localEntry = (Map.Entry)paramIterator.next();
          paramA.append(joiner.toString(localEntry.getKey()));
          paramA.append(keyValueSeparator);
          paramA.append(joiner.toString(localEntry.getValue()));
        }
      }
      return paramA;
    }
    
    public StringBuilder appendTo(StringBuilder paramStringBuilder, Iterable<? extends Map.Entry<?, ?>> paramIterable)
    {
      return appendTo(paramStringBuilder, paramIterable.iterator());
    }
    
    public StringBuilder appendTo(StringBuilder paramStringBuilder, Iterator<? extends Map.Entry<?, ?>> paramIterator)
    {
      try
      {
        appendTo(paramStringBuilder, paramIterator);
        return paramStringBuilder;
      }
      catch (IOException paramStringBuilder)
      {
        throw new AssertionError(paramStringBuilder);
      }
    }
    
    public StringBuilder appendTo(StringBuilder paramStringBuilder, Map<?, ?> paramMap)
    {
      return appendTo(paramStringBuilder, paramMap.entrySet());
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Joiner
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */