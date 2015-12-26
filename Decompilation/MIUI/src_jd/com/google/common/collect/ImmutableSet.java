package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public abstract class ImmutableSet<E>
  extends ImmutableCollection<E>
  implements Set<E>
{
  static int chooseTableSize(int paramInt)
  {
    if (paramInt < 536870912) {
      return Integer.highestOneBit(paramInt) << 2;
    }
    if (paramInt < 1073741824) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "collection too large");
      return 1073741824;
    }
  }
  
  private static <E> ImmutableSet<E> construct(Object... paramVarArgs)
  {
    int n = chooseTableSize(paramVarArgs.length);
    Object[] arrayOfObject = new Object[n];
    int i1 = n - 1;
    Object localObject1 = null;
    int j = 0;
    int i = 0;
    if (i < paramVarArgs.length)
    {
      Object localObject2 = paramVarArgs[i];
      int m = localObject2.hashCode();
      int k = Hashing.smear(m);
      for (;;)
      {
        int i2 = k & i1;
        Object localObject3 = arrayOfObject[i2];
        if (localObject3 == null)
        {
          if (localObject1 != null) {
            ((ArrayList)localObject1).add(localObject2);
          }
          arrayOfObject[i2] = localObject2;
          k = j + m;
          localObject2 = localObject1;
        }
        do
        {
          i += 1;
          j = k;
          localObject1 = localObject2;
          break;
          if (!localObject3.equals(localObject2)) {
            break label177;
          }
          k = j;
          localObject2 = localObject1;
        } while (localObject1 != null);
        localObject1 = new ArrayList(paramVarArgs.length);
        m = 0;
        for (;;)
        {
          k = j;
          localObject2 = localObject1;
          if (m >= i) {
            break;
          }
          ((ArrayList)localObject1).add(paramVarArgs[m]);
          m += 1;
        }
        label177:
        k += 1;
      }
    }
    if (localObject1 == null) {}
    while (paramVarArgs.length == 1)
    {
      return new SingletonImmutableSet(paramVarArgs[0], j);
      paramVarArgs = ((ArrayList)localObject1).toArray();
    }
    if (n > chooseTableSize(paramVarArgs.length) * 2) {
      return construct(paramVarArgs);
    }
    return new RegularImmutableSet(paramVarArgs, j, arrayOfObject, i1);
  }
  
  public static <E> ImmutableSet<E> copyOf(E[] paramArrayOfE)
  {
    switch (paramArrayOfE.length)
    {
    default: 
      return construct((Object[])paramArrayOfE.clone());
    case 0: 
      return of();
    }
    return of(paramArrayOfE[0]);
  }
  
  public static <E> ImmutableSet<E> of()
  {
    return EmptyImmutableSet.INSTANCE;
  }
  
  public static <E> ImmutableSet<E> of(E paramE)
  {
    return new SingletonImmutableSet(paramE);
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (((paramObject instanceof ImmutableSet)) && (isHashCodeFast()) && (((ImmutableSet)paramObject).isHashCodeFast()) && (hashCode() != paramObject.hashCode())) {
      return false;
    }
    return Sets.equalsImpl(this, paramObject);
  }
  
  public int hashCode()
  {
    return Sets.hashCodeImpl(this);
  }
  
  boolean isHashCodeFast()
  {
    return false;
  }
  
  public abstract UnmodifiableIterator<E> iterator();
  
  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }
  
  static abstract class ArrayImmutableSet<E>
    extends ImmutableSet<E>
  {
    final transient Object[] elements;
    
    ArrayImmutableSet(Object[] paramArrayOfObject)
    {
      elements = paramArrayOfObject;
    }
    
    public boolean containsAll(Collection<?> paramCollection)
    {
      if (paramCollection == this) {}
      for (;;)
      {
        return true;
        if (!(paramCollection instanceof ArrayImmutableSet)) {
          return super.containsAll(paramCollection);
        }
        if (paramCollection.size() > size()) {
          return false;
        }
        paramCollection = elements;
        int j = paramCollection.length;
        int i = 0;
        while (i < j)
        {
          if (!contains(paramCollection[i])) {
            return false;
          }
          i += 1;
        }
      }
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    public UnmodifiableIterator<E> iterator()
    {
      return Iterators.forArray(elements);
    }
    
    public int size()
    {
      return elements.length;
    }
    
    public Object[] toArray()
    {
      Object[] arrayOfObject = new Object[size()];
      System.arraycopy(elements, 0, arrayOfObject, 0, size());
      return arrayOfObject;
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      int i = size();
      Object localObject;
      if (paramArrayOfT.length < i) {
        localObject = ObjectArrays.newArray(paramArrayOfT, i);
      }
      for (;;)
      {
        System.arraycopy(elements, 0, localObject, 0, i);
        return (T[])localObject;
        localObject = paramArrayOfT;
        if (paramArrayOfT.length > i)
        {
          paramArrayOfT[i] = null;
          localObject = paramArrayOfT;
        }
      }
    }
  }
  
  private static class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID = 0L;
    final Object[] elements;
    
    SerializedForm(Object[] paramArrayOfObject)
    {
      elements = paramArrayOfObject;
    }
    
    Object readResolve()
    {
      return ImmutableSet.copyOf(elements);
    }
  }
  
  static abstract class TransformedImmutableSet<D, E>
    extends ImmutableSet<E>
  {
    final int hashCode;
    final D[] source;
    
    TransformedImmutableSet(D[] paramArrayOfD, int paramInt)
    {
      source = paramArrayOfD;
      hashCode = paramInt;
    }
    
    public final int hashCode()
    {
      return hashCode;
    }
    
    public boolean isEmpty()
    {
      return false;
    }
    
    boolean isHashCodeFast()
    {
      return true;
    }
    
    public UnmodifiableIterator<E> iterator()
    {
      new AbstractIndexedListIterator(source.length)
      {
        protected E get(int paramAnonymousInt)
        {
          return (E)transform(source[paramAnonymousInt]);
        }
      };
    }
    
    public int size()
    {
      return source.length;
    }
    
    public Object[] toArray()
    {
      return toArray(new Object[size()]);
    }
    
    public <T> T[] toArray(T[] paramArrayOfT)
    {
      int i = size();
      Object localObject;
      if (paramArrayOfT.length < i) {
        localObject = ObjectArrays.newArray(paramArrayOfT, i);
      }
      for (;;)
      {
        i = 0;
        while (i < source.length)
        {
          localObject[i] = transform(source[i]);
          i += 1;
        }
        localObject = paramArrayOfT;
        if (paramArrayOfT.length > i)
        {
          paramArrayOfT[i] = null;
          localObject = paramArrayOfT;
        }
      }
      return (T[])localObject;
    }
    
    abstract E transform(D paramD);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableSet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */