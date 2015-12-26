package android.support.v4.util;

import java.util.Map;

public class SimpleArrayMap<K, V>
{
  private static final int BASE_SIZE = 4;
  private static final int CACHE_SIZE = 10;
  private static final boolean DEBUG = false;
  private static final String TAG = "ArrayMap";
  static Object[] mBaseCache;
  static int mBaseCacheSize;
  static Object[] mTwiceBaseCache;
  static int mTwiceBaseCacheSize;
  Object[] mArray;
  int[] mHashes;
  int mSize;
  
  public SimpleArrayMap()
  {
    mHashes = ContainerHelpers.EMPTY_INTS;
    mArray = ContainerHelpers.EMPTY_OBJECTS;
    mSize = 0;
  }
  
  public SimpleArrayMap(int paramInt)
  {
    if (paramInt == 0)
    {
      mHashes = ContainerHelpers.EMPTY_INTS;
      mArray = ContainerHelpers.EMPTY_OBJECTS;
    }
    for (;;)
    {
      mSize = 0;
      return;
      allocArrays(paramInt);
    }
  }
  
  public SimpleArrayMap(SimpleArrayMap paramSimpleArrayMap)
  {
    this();
    if (paramSimpleArrayMap != null) {
      putAll(paramSimpleArrayMap);
    }
  }
  
  private void allocArrays(int paramInt)
  {
    if (paramInt == 8) {}
    for (;;)
    {
      try
      {
        if (mTwiceBaseCache != null)
        {
          Object[] arrayOfObject1 = mTwiceBaseCache;
          mArray = arrayOfObject1;
          mTwiceBaseCache = (Object[])arrayOfObject1[0];
          mHashes = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          mTwiceBaseCacheSize -= 1;
          return;
        }
        mHashes = new int[paramInt];
        mArray = new Object[paramInt << 1];
        return;
      }
      finally {}
      if (paramInt == 4) {
        try
        {
          if (mBaseCache != null)
          {
            Object[] arrayOfObject2 = mBaseCache;
            mArray = arrayOfObject2;
            mBaseCache = (Object[])arrayOfObject2[0];
            mHashes = ((int[])arrayOfObject2[1]);
            arrayOfObject2[1] = null;
            arrayOfObject2[0] = null;
            mBaseCacheSize -= 1;
            return;
          }
        }
        finally {}
      }
    }
  }
  
  private static void freeArrays(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8) {
      try
      {
        if (mTwiceBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mTwiceBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label117;
          mTwiceBaseCache = paramArrayOfObject;
          mTwiceBaseCacheSize += 1;
        }
        return;
      }
      finally {}
    }
    if (paramArrayOfInt.length == 4) {}
    for (;;)
    {
      try
      {
        if (mBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label134;
          mBaseCache = paramArrayOfObject;
          mBaseCacheSize += 1;
        }
        return;
      }
      finally {}
      label117:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
      break;
      return;
      label134:
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
    }
  }
  
  public void clear()
  {
    if (mSize != 0)
    {
      freeArrays(mHashes, mArray, mSize);
      mHashes = ContainerHelpers.EMPTY_INTS;
      mArray = ContainerHelpers.EMPTY_OBJECTS;
      mSize = 0;
    }
  }
  
  public boolean containsKey(Object paramObject)
  {
    return indexOfKey(paramObject) >= 0;
  }
  
  public boolean containsValue(Object paramObject)
  {
    return indexOfValue(paramObject) >= 0;
  }
  
  public void ensureCapacity(int paramInt)
  {
    if (mHashes.length < paramInt)
    {
      int[] arrayOfInt = mHashes;
      Object[] arrayOfObject = mArray;
      allocArrays(paramInt);
      if (mSize > 0)
      {
        System.arraycopy(arrayOfInt, 0, mHashes, 0, mSize);
        System.arraycopy(arrayOfObject, 0, mArray, 0, mSize << 1);
      }
      freeArrays(arrayOfInt, arrayOfObject, mSize);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    for (;;)
    {
      return true;
      if ((paramObject instanceof Map))
      {
        paramObject = (Map)paramObject;
        if (size() != ((Map)paramObject).size()) {
          return false;
        }
        int i = 0;
        try
        {
          while (i < mSize)
          {
            Object localObject1 = keyAt(i);
            Object localObject2 = valueAt(i);
            Object localObject3 = ((Map)paramObject).get(localObject1);
            if (localObject2 == null)
            {
              if (localObject3 != null) {
                break label121;
              }
              if (!((Map)paramObject).containsKey(localObject1)) {
                break label121;
              }
            }
            else
            {
              boolean bool = localObject2.equals(localObject3);
              if (!bool) {
                return false;
              }
            }
            i += 1;
          }
          return false;
        }
        catch (NullPointerException paramObject)
        {
          return false;
        }
        catch (ClassCastException paramObject)
        {
          return false;
        }
      }
    }
    label121:
    return false;
  }
  
  public V get(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0) {
      return (V)mArray[((i << 1) + 1)];
    }
    return null;
  }
  
  public int hashCode()
  {
    int[] arrayOfInt = mHashes;
    Object[] arrayOfObject = mArray;
    int n = mSize;
    int i = 1;
    int j = 0;
    int k = 0;
    if (j < n)
    {
      Object localObject = arrayOfObject[i];
      int i1 = arrayOfInt[j];
      if (localObject == null) {}
      for (int m = 0;; m = localObject.hashCode())
      {
        k += (m ^ i1);
        j += 1;
        i += 2;
        break;
      }
    }
    return k;
  }
  
  int indexOf(Object paramObject, int paramInt)
  {
    int m = mSize;
    int i;
    if (m == 0) {
      i = -1;
    }
    int j;
    do
    {
      do
      {
        return i;
        j = ContainerHelpers.binarySearch(mHashes, m, paramInt);
        i = j;
      } while (j < 0);
      i = j;
    } while (paramObject.equals(mArray[(j << 1)]));
    int k = j + 1;
    while ((k < m) && (mHashes[k] == paramInt))
    {
      if (paramObject.equals(mArray[(k << 1)])) {
        return k;
      }
      k += 1;
    }
    j -= 1;
    for (;;)
    {
      if ((j < 0) || (mHashes[j] != paramInt)) {
        break label156;
      }
      i = j;
      if (paramObject.equals(mArray[(j << 1)])) {
        break;
      }
      j -= 1;
    }
    label156:
    return k ^ 0xFFFFFFFF;
  }
  
  public int indexOfKey(Object paramObject)
  {
    if (paramObject == null) {
      return indexOfNull();
    }
    return indexOf(paramObject, paramObject.hashCode());
  }
  
  int indexOfNull()
  {
    int m = mSize;
    int i;
    if (m == 0) {
      i = -1;
    }
    int j;
    do
    {
      do
      {
        return i;
        j = ContainerHelpers.binarySearch(mHashes, m, 0);
        i = j;
      } while (j < 0);
      i = j;
    } while (mArray[(j << 1)] == null);
    int k = j + 1;
    while ((k < m) && (mHashes[k] == 0))
    {
      if (mArray[(k << 1)] == null) {
        return k;
      }
      k += 1;
    }
    j -= 1;
    for (;;)
    {
      if ((j < 0) || (mHashes[j] != 0)) {
        break label121;
      }
      i = j;
      if (mArray[(j << 1)] == null) {
        break;
      }
      j -= 1;
    }
    label121:
    return k ^ 0xFFFFFFFF;
  }
  
  int indexOfValue(Object paramObject)
  {
    int i = 1;
    int j = 1;
    int k = mSize * 2;
    Object[] arrayOfObject = mArray;
    if (paramObject == null)
    {
      i = j;
      while (i < k)
      {
        if (arrayOfObject[i] == null) {
          return i >> 1;
        }
        i += 2;
      }
    }
    do
    {
      i += 2;
      if (i >= k) {
        break;
      }
    } while (!paramObject.equals(arrayOfObject[i]));
    return i >> 1;
    return -1;
  }
  
  public boolean isEmpty()
  {
    return mSize <= 0;
  }
  
  public K keyAt(int paramInt)
  {
    return (K)mArray[(paramInt << 1)];
  }
  
  public V put(K paramK, V paramV)
  {
    int k = 8;
    int i;
    int j;
    if (paramK == null)
    {
      i = indexOfNull();
      j = 0;
    }
    while (i >= 0)
    {
      i = (i << 1) + 1;
      paramK = mArray[i];
      mArray[i] = paramV;
      return paramK;
      j = paramK.hashCode();
      i = indexOf(paramK, j);
    }
    int m = i ^ 0xFFFFFFFF;
    if (mSize >= mHashes.length)
    {
      if (mSize < 8) {
        break label267;
      }
      i = mSize + (mSize >> 1);
    }
    for (;;)
    {
      int[] arrayOfInt = mHashes;
      Object[] arrayOfObject = mArray;
      allocArrays(i);
      if (mHashes.length > 0)
      {
        System.arraycopy(arrayOfInt, 0, mHashes, 0, arrayOfInt.length);
        System.arraycopy(arrayOfObject, 0, mArray, 0, arrayOfObject.length);
      }
      freeArrays(arrayOfInt, arrayOfObject, mSize);
      if (m < mSize)
      {
        System.arraycopy(mHashes, m, mHashes, m + 1, mSize - m);
        System.arraycopy(mArray, m << 1, mArray, m + 1 << 1, mSize - m << 1);
      }
      mHashes[m] = j;
      mArray[(m << 1)] = paramK;
      mArray[((m << 1) + 1)] = paramV;
      mSize += 1;
      return null;
      label267:
      i = k;
      if (mSize < 4) {
        i = 4;
      }
    }
  }
  
  public void putAll(SimpleArrayMap<? extends K, ? extends V> paramSimpleArrayMap)
  {
    int i = 0;
    int j = mSize;
    ensureCapacity(mSize + j);
    if (mSize == 0) {
      if (j > 0)
      {
        System.arraycopy(mHashes, 0, mHashes, 0, j);
        System.arraycopy(mArray, 0, mArray, 0, j << 1);
        mSize = j;
      }
    }
    for (;;)
    {
      return;
      while (i < j)
      {
        put(paramSimpleArrayMap.keyAt(i), paramSimpleArrayMap.valueAt(i));
        i += 1;
      }
    }
  }
  
  public V remove(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0) {
      return (V)removeAt(i);
    }
    return null;
  }
  
  public V removeAt(int paramInt)
  {
    int i = 8;
    Object localObject = mArray[((paramInt << 1) + 1)];
    if (mSize <= 1)
    {
      freeArrays(mHashes, mArray, mSize);
      mHashes = ContainerHelpers.EMPTY_INTS;
      mArray = ContainerHelpers.EMPTY_OBJECTS;
      mSize = 0;
    }
    int[] arrayOfInt;
    Object[] arrayOfObject;
    do
    {
      return (V)localObject;
      if ((mHashes.length <= 8) || (mSize >= mHashes.length / 3)) {
        break;
      }
      if (mSize > 8) {
        i = mSize + (mSize >> 1);
      }
      arrayOfInt = mHashes;
      arrayOfObject = mArray;
      allocArrays(i);
      mSize -= 1;
      if (paramInt > 0)
      {
        System.arraycopy(arrayOfInt, 0, mHashes, 0, paramInt);
        System.arraycopy(arrayOfObject, 0, mArray, 0, paramInt << 1);
      }
    } while (paramInt >= mSize);
    System.arraycopy(arrayOfInt, paramInt + 1, mHashes, paramInt, mSize - paramInt);
    System.arraycopy(arrayOfObject, paramInt + 1 << 1, mArray, paramInt << 1, mSize - paramInt << 1);
    return (V)localObject;
    mSize -= 1;
    if (paramInt < mSize)
    {
      System.arraycopy(mHashes, paramInt + 1, mHashes, paramInt, mSize - paramInt);
      System.arraycopy(mArray, paramInt + 1 << 1, mArray, paramInt << 1, mSize - paramInt << 1);
    }
    mArray[(mSize << 1)] = null;
    mArray[((mSize << 1) + 1)] = null;
    return (V)localObject;
  }
  
  public V setValueAt(int paramInt, V paramV)
  {
    paramInt = (paramInt << 1) + 1;
    Object localObject = mArray[paramInt];
    mArray[paramInt] = paramV;
    return (V)localObject;
  }
  
  public int size()
  {
    return mSize;
  }
  
  public String toString()
  {
    if (isEmpty()) {
      return "{}";
    }
    StringBuilder localStringBuilder = new StringBuilder(mSize * 28);
    localStringBuilder.append('{');
    int i = 0;
    if (i < mSize)
    {
      if (i > 0) {
        localStringBuilder.append(", ");
      }
      Object localObject = keyAt(i);
      if (localObject != this)
      {
        localStringBuilder.append(localObject);
        label70:
        localStringBuilder.append('=');
        localObject = valueAt(i);
        if (localObject == this) {
          break label111;
        }
        localStringBuilder.append(localObject);
      }
      for (;;)
      {
        i += 1;
        break;
        localStringBuilder.append("(this Map)");
        break label70;
        label111:
        localStringBuilder.append("(this Map)");
      }
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
  
  public V valueAt(int paramInt)
  {
    return (V)mArray[((paramInt << 1) + 1)];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.util.SimpleArrayMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */