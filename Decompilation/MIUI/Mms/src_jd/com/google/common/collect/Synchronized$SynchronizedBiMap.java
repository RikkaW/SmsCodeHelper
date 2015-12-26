package com.google.common.collect;

import java.io.Serializable;
import java.util.Set;

class Synchronized$SynchronizedBiMap<K, V>
  extends Synchronized.SynchronizedMap<K, V>
  implements BiMap<K, V>, Serializable
{
  private static final long serialVersionUID = 0L;
  private transient Set<V> valueSet;
  
  BiMap<K, V> delegate()
  {
    return (BiMap)super.delegate();
  }
  
  public Set<V> values()
  {
    synchronized (mutex)
    {
      if (valueSet == null) {
        valueSet = Synchronized.set(delegate().values(), mutex);
      }
      Set localSet = valueSet;
      return localSet;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Synchronized.SynchronizedBiMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */