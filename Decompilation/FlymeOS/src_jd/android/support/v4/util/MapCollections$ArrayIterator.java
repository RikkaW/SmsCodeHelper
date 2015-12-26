package android.support.v4.util;

import java.util.Iterator;

final class MapCollections$ArrayIterator<T>
  implements Iterator<T>
{
  boolean mCanRemove = false;
  int mIndex;
  final int mOffset;
  int mSize;
  
  MapCollections$ArrayIterator(MapCollections paramMapCollections, int paramInt)
  {
    mOffset = paramInt;
    mSize = paramMapCollections.colGetSize();
  }
  
  public boolean hasNext()
  {
    return mIndex < mSize;
  }
  
  public T next()
  {
    Object localObject = this$0.colGetEntry(mIndex, mOffset);
    mIndex += 1;
    mCanRemove = true;
    return (T)localObject;
  }
  
  public void remove()
  {
    if (!mCanRemove) {
      throw new IllegalStateException();
    }
    mIndex -= 1;
    mSize -= 1;
    mCanRemove = false;
    this$0.colRemoveAt(mIndex);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.util.MapCollections.ArrayIterator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */