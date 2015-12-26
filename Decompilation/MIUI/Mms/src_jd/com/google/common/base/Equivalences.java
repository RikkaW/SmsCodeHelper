package com.google.common.base;

import java.io.Serializable;

public final class Equivalences
{
  public static Equivalence<Object> equals()
  {
    return Equals.INSTANCE;
  }
  
  public static Equivalence<Object> identity()
  {
    return Identity.INSTANCE;
  }
  
  private static final class Equals
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
  
  private static final class Identity
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
}

/* Location:
 * Qualified Name:     com.google.common.base.Equivalences
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */