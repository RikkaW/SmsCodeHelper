package android.support.v4.util;

import java.util.Map;

class ArrayMap$1
  extends MapCollections<K, V>
{
  ArrayMap$1(ArrayMap paramArrayMap) {}
  
  protected void colClear()
  {
    this$0.clear();
  }
  
  protected Object colGetEntry(int paramInt1, int paramInt2)
  {
    return this$0.mArray[((paramInt1 << 1) + paramInt2)];
  }
  
  protected Map<K, V> colGetMap()
  {
    return this$0;
  }
  
  protected int colGetSize()
  {
    return this$0.mSize;
  }
  
  protected int colIndexOfKey(Object paramObject)
  {
    return this$0.indexOfKey(paramObject);
  }
  
  protected int colIndexOfValue(Object paramObject)
  {
    return this$0.indexOfValue(paramObject);
  }
  
  protected void colPut(K paramK, V paramV)
  {
    this$0.put(paramK, paramV);
  }
  
  protected void colRemoveAt(int paramInt)
  {
    this$0.removeAt(paramInt);
  }
  
  protected V colSetValue(int paramInt, V paramV)
  {
    return (V)this$0.setValueAt(paramInt, paramV);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.util.ArrayMap.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */