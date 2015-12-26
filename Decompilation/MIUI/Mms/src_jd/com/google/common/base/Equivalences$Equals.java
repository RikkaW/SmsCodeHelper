package com.google.common.base;

import java.io.Serializable;

final class Equivalences$Equals
  extends Equivalence<Object>
  implements Serializable
{
  static final Equals INSTANCE = new Equals();
  private static final long serialVersionUID = 1L;
  
  private Object readResolve()
  {
    return INSTANCE;
  }
  
  protected boolean doEquivalent(Object paramObject1, Object paramObject2)
  {
    return paramObject1.equals(paramObject2);
  }
  
  public int doHash(Object paramObject)
  {
    return paramObject.hashCode();
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Equivalences.Equals
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */