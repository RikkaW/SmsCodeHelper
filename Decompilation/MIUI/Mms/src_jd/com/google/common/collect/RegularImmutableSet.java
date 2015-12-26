package com.google.common.collect;

final class RegularImmutableSet<E>
  extends ImmutableSet.ArrayImmutableSet<E>
{
  private final transient int hashCode;
  private final transient int mask;
  final transient Object[] table;
  
  RegularImmutableSet(Object[] paramArrayOfObject1, int paramInt1, Object[] paramArrayOfObject2, int paramInt2)
  {
    super(paramArrayOfObject1);
    table = paramArrayOfObject2;
    mask = paramInt2;
    hashCode = paramInt1;
  }
  
  public boolean contains(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    int i = Hashing.smear(paramObject.hashCode());
    for (;;)
    {
      Object localObject = table[(mask & i)];
      if (localObject == null) {
        break;
      }
      if (localObject.equals(paramObject)) {
        return true;
      }
      i += 1;
    }
  }
  
  public int hashCode()
  {
    return hashCode;
  }
  
  boolean isHashCodeFast()
  {
    return true;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */