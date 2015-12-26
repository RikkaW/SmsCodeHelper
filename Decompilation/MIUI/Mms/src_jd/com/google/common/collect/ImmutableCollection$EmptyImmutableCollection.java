package com.google.common.collect;

class ImmutableCollection$EmptyImmutableCollection
  extends ImmutableCollection<Object>
{
  private static final Object[] EMPTY_ARRAY = new Object[0];
  
  public boolean contains(Object paramObject)
  {
    return false;
  }
  
  public boolean isEmpty()
  {
    return true;
  }
  
  public UnmodifiableIterator<Object> iterator()
  {
    return Iterators.EMPTY_ITERATOR;
  }
  
  public int size()
  {
    return 0;
  }
  
  public Object[] toArray()
  {
    return EMPTY_ARRAY;
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    if (paramArrayOfT.length > 0) {
      paramArrayOfT[0] = null;
    }
    return paramArrayOfT;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableCollection.EmptyImmutableCollection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */