package android.support.v4.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

final class MapCollections$KeySet
  implements Set<K>
{
  MapCollections$KeySet(MapCollections paramMapCollections) {}
  
  public boolean add(K paramK)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean addAll(Collection<? extends K> paramCollection)
  {
    throw new UnsupportedOperationException();
  }
  
  public void clear()
  {
    this$0.colClear();
  }
  
  public boolean contains(Object paramObject)
  {
    return this$0.colIndexOfKey(paramObject) >= 0;
  }
  
  public boolean containsAll(Collection<?> paramCollection)
  {
    return MapCollections.containsAllHelper(this$0.colGetMap(), paramCollection);
  }
  
  public boolean equals(Object paramObject)
  {
    return MapCollections.equalsSetHelper(this, paramObject);
  }
  
  public int hashCode()
  {
    int i = this$0.colGetSize() - 1;
    int j = 0;
    if (i >= 0)
    {
      Object localObject = this$0.colGetEntry(i, 0);
      if (localObject == null) {}
      for (int k = 0;; k = localObject.hashCode())
      {
        j += k;
        i -= 1;
        break;
      }
    }
    return j;
  }
  
  public boolean isEmpty()
  {
    return this$0.colGetSize() == 0;
  }
  
  public Iterator<K> iterator()
  {
    return new MapCollections.ArrayIterator(this$0, 0);
  }
  
  public boolean remove(Object paramObject)
  {
    int i = this$0.colIndexOfKey(paramObject);
    if (i >= 0)
    {
      this$0.colRemoveAt(i);
      return true;
    }
    return false;
  }
  
  public boolean removeAll(Collection<?> paramCollection)
  {
    return MapCollections.removeAllHelper(this$0.colGetMap(), paramCollection);
  }
  
  public boolean retainAll(Collection<?> paramCollection)
  {
    return MapCollections.retainAllHelper(this$0.colGetMap(), paramCollection);
  }
  
  public int size()
  {
    return this$0.colGetSize();
  }
  
  public Object[] toArray()
  {
    return this$0.toArrayHelper(0);
  }
  
  public <T> T[] toArray(T[] paramArrayOfT)
  {
    return this$0.toArrayHelper(paramArrayOfT, 0);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.util.MapCollections.KeySet
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */