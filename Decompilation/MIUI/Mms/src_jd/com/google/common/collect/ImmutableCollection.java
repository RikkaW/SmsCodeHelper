package com.google.common.collect;

import java.io.Serializable;
import java.util.Collection;

public abstract class ImmutableCollection<E>
  implements Collection<E>, Serializable
{
  static final ImmutableCollection<Object> EMPTY_IMMUTABLE_COLLECTION = new EmptyImmutableCollection(null);
  
  public final boolean add(E paramE)
  {
    throw new UnsupportedOperationException();
  }
  
  public final boolean addAll(Collection<? extends E> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public final void clear()
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean contains(Object paramObject)
  {
    return (paramObject != null) && (Iterators.contains(iterator(), paramObject));
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return Collections2.containsAllImpl(this, paramCollection);
  }
  
  public boolean isEmpty()
  {
    return size() == 0;
  }
  
  public abstract UnmodifiableIterator<E> iterator();
  
  public final boolean remove(Object paramObject)
  {
    throw new UnsupportedOperationException();
  }
  
  public final boolean removeAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public final boolean retainAll(Collection<?> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public Object[] toArray()
  {
    return ObjectArrays.toArrayImpl(this);
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return ObjectArrays.toArrayImpl(this, paramArrayOfT);
  }
  
  public String toString()
  {
    return Collections2.toStringImpl(this);
  }
  
  Object writeReplace()
  {
    return new SerializedForm(toArray());
  }
  
  private static class ArrayImmutableCollection<E>
    extends ImmutableCollection<E>
  {
    private final E[] elements;
    
    ArrayImmutableCollection(E[] paramArrayOfE)
    {
      elements = paramArrayOfE;
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
  }
  
  private static class EmptyImmutableCollection
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
      if (elements.length == 0) {
        return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
      }
      return new ImmutableCollection.ArrayImmutableCollection(Platform.clone(elements));
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableCollection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */