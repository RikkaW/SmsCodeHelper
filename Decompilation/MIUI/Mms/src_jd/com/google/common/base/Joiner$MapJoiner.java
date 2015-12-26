package com.google.common.base;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class Joiner$MapJoiner
{
  private final Joiner joiner;
  private final String keyValueSeparator;
  
  private Joiner$MapJoiner(Joiner paramJoiner, String paramString)
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
        paramA.append(Joiner.access$100(joiner));
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

/* Location:
 * Qualified Name:     com.google.common.base.Joiner.MapJoiner
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */