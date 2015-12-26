package com.google.common.base;

public abstract class Equivalence<T>
{
  protected abstract boolean doEquivalent(T paramT1, T paramT2);
  
  protected abstract int doHash(T paramT);
  
  public final boolean equivalent(T paramT1, T paramT2)
  {
    if (paramT1 == paramT2) {
      return true;
    }
    if ((paramT1 == null) || (paramT2 == null)) {
      return false;
    }
    return doEquivalent(paramT1, paramT2);
  }
  
  public final int hash(T paramT)
  {
    if (paramT == null) {
      return 0;
    }
    return doHash(paramT);
  }
}

/* Location:
 * Qualified Name:     com.google.common.base.Equivalence
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */