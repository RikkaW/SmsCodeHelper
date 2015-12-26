package com.amap.api.mapcore2d;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

class cx<K, V>
{
  private final LinkedHashMap<K, V> a;
  private int b;
  private int c;
  private int d;
  private int e;
  private int f;
  private int g;
  private int h;
  
  public cx(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    c = paramInt;
    a = new LinkedHashMap(0, 0.75F, true);
  }
  
  private void a(int paramInt)
  {
    Object localObject3;
    Object localObject2;
    try
    {
      if ((b < 0) || ((a.isEmpty()) && (b != 0))) {
        throw new IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
      }
    }
    finally
    {
      throw ((Throwable)localObject1);
      if (b <= paramInt) {
        return;
      }
      localObject3 = a.entrySet().iterator();
      for (localObject2 = null; ((Iterator)localObject3).hasNext(); localObject2 = (Map.Entry)((Iterator)localObject3).next()) {}
      if (localObject2 == null) {
        return;
      }
      localObject3 = ((Map.Entry)localObject2).getKey();
      localObject2 = ((Map.Entry)localObject2).getValue();
      a.remove(localObject3);
      b -= c(localObject3, localObject2);
      f += 1;
    }
  }
  
  private int c(K paramK, V paramV)
  {
    int i = a(paramK, paramV);
    if (i < 0) {
      throw new IllegalStateException("Negative size: " + paramK + "=" + paramV);
    }
    return i;
  }
  
  protected int a(K paramK, V paramV)
  {
    return 1;
  }
  
  public final V a(K paramK)
  {
    if (paramK == null) {
      throw new NullPointerException("key == null");
    }
    Object localObject1;
    try
    {
      localObject1 = a.get(paramK);
      if (localObject1 != null)
      {
        g += 1;
        return (V)localObject1;
      }
      h += 1;
      localObject1 = b(paramK);
      if (localObject1 == null) {
        return null;
      }
    }
    finally {}
    try
    {
      e += 1;
      Object localObject2 = a.put(paramK, localObject1);
      if (localObject2 != null) {
        a.put(paramK, localObject2);
      }
      for (;;)
      {
        if (localObject2 == null) {
          break;
        }
        a(false, paramK, localObject1, localObject2);
        return (V)localObject2;
        b += c(paramK, localObject1);
      }
      a(c);
    }
    finally {}
    return (V)localObject1;
  }
  
  public final void a()
  {
    a(-1);
  }
  
  protected void a(boolean paramBoolean, K paramK, V paramV1, V paramV2) {}
  
  protected V b(K paramK)
  {
    return null;
  }
  
  public final V b(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      throw new NullPointerException("key == null || value == null");
    }
    try
    {
      d += 1;
      b += c(paramK, paramV);
      Object localObject = a.put(paramK, paramV);
      if (localObject != null) {
        b -= c(paramK, localObject);
      }
      if (localObject != null) {
        a(false, paramK, localObject, paramV);
      }
      a(c);
      return (V)localObject;
    }
    finally {}
  }
  
  public final String toString()
  {
    int i = 0;
    try
    {
      int j = g + h;
      if (j != 0) {
        i = g * 100 / j;
      }
      String str = String.format("LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", new Object[] { Integer.valueOf(c), Integer.valueOf(g), Integer.valueOf(h), Integer.valueOf(i) });
      return str;
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */