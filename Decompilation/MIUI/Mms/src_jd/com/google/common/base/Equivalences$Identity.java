package com.google.common.base;

import java.io.Serializable;

final class Equivalences$Identity
  extends Equivalence<Object>
  implements Serializable
{
  static final Identity INSTANCE = new Identity();
  private static final long serialVersionUID = 1L;
  
  private Object readResolve()
  {
    return INSTANCE;
  }
  
  protected boolean doEquivalent(Object paramObject1, Object paramObject2)
  {
    return false;
  }
  
  protected int doHash(Object paramObject)
  {
    return System.identityHashCode(paramObject);
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Equivalences.Identity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */