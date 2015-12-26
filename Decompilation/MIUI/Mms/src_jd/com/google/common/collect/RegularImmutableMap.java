package com.google.common.collect;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import java.util.Map.Entry;

final class RegularImmutableMap<K, V>
  extends ImmutableMap<K, V>
{
  private static final long serialVersionUID = 0L;
  private final transient LinkedEntry<K, V>[] entries;
  private transient ImmutableSet<Map.Entry<K, V>> entrySet;
  private transient ImmutableSet<K> keySet;
  private final transient int keySetHashCode;
  private final transient int mask;
  private final transient LinkedEntry<K, V>[] table;
  private transient ImmutableCollection<V> values;
  
  RegularImmutableMap(Map.Entry<?, ?>... paramVarArgs)
  {
    int k = paramVarArgs.length;
    entries = createEntryArray(k);
    int i = chooseTableSize(k);
    table = createEntryArray(i);
    mask = (i - 1);
    int j = 0;
    i = 0;
    while (i < k)
    {
      Object localObject2 = paramVarArgs[i];
      Object localObject1 = ((Map.Entry)localObject2).getKey();
      int m = localObject1.hashCode();
      j += m;
      m = Hashing.smear(m) & mask;
      LinkedEntry localLinkedEntry = table[m];
      localObject2 = newLinkedEntry(localObject1, ((Map.Entry)localObject2).getValue(), localLinkedEntry);
      table[m] = localObject2;
      entries[i] = localObject2;
      if (localLinkedEntry != null)
      {
        if (!localObject1.equals(localLinkedEntry.getKey())) {}
        for (boolean bool = true;; bool = false)
        {
          Preconditions.checkArgument(bool, "duplicate key: %s", new Object[] { localObject1 });
          localLinkedEntry = localLinkedEntry.next();
          break;
        }
      }
      i += 1;
    }
    keySetHashCode = j;
  }
  
  private static int chooseTableSize(int paramInt)
  {
    int i = Integer.highestOneBit(paramInt) << 1;
    if (i > 0) {}
    for (boolean bool = true;; bool = false)
    {
      Preconditions.checkArgument(bool, "table too large: %s", new Object[] { Integer.valueOf(paramInt) });
      return i;
    }
  }
  
  private LinkedEntry<K, V>[] createEntryArray(int paramInt)
  {
    return new LinkedEntry[paramInt];
  }
  
  private static <K, V> LinkedEntry<K, V> newLinkedEntry(K paramK, V paramV, LinkedEntry<K, V> paramLinkedEntry)
  {
    if (paramLinkedEntry == null) {}
    for (paramK = new TerminalEntry(paramK, paramV);; paramK = new NonTerminalEntry(paramK, paramV, paramLinkedEntry)) {
      return (LinkedEntry)paramK;
    }
  }
  
  public boolean containsValue(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return false;
      LinkedEntry[] arrayOfLinkedEntry = entries;
      int j = arrayOfLinkedEntry.length;
      int i = 0;
      while (i < j)
      {
        if (arrayOfLinkedEntry[i].getValue().equals(paramObject)) {
          return true;
        }
        i += 1;
      }
    }
  }
  
  public ImmutableSet<Map.Entry<K, V>> entrySet()
  {
    ImmutableSet localImmutableSet = entrySet;
    Object localObject = localImmutableSet;
    if (localImmutableSet == null)
    {
      localObject = new EntrySet(this);
      entrySet = ((ImmutableSet)localObject);
    }
    return (ImmutableSet<Map.Entry<K, V>>)localObject;
  }
  
  public V get(Object paramObject)
  {
    if (paramObject == null) {}
    for (;;)
    {
      return null;
      int i = Hashing.smear(paramObject.hashCode());
      int j = mask;
      for (LinkedEntry localLinkedEntry = table[(i & j)]; localLinkedEntry != null; localLinkedEntry = localLinkedEntry.next()) {
        if (paramObject.equals(localLinkedEntry.getKey())) {
          return (V)localLinkedEntry.getValue();
        }
      }
    }
  }
  
  public boolean isEmpty()
  {
    return false;
  }
  
  public ImmutableSet<K> keySet()
  {
    ImmutableSet localImmutableSet = keySet;
    Object localObject = localImmutableSet;
    if (localImmutableSet == null)
    {
      localObject = new KeySet(this);
      keySet = ((ImmutableSet)localObject);
    }
    return (ImmutableSet<K>)localObject;
  }
  
  public int size()
  {
    return entries.length;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = Collections2.newStringBuilderForCollection(size()).append('{');
    Collections2.STANDARD_JOINER.appendTo(localStringBuilder, entries);
    return '}';
  }
  
  public ImmutableCollection<V> values()
  {
    ImmutableCollection localImmutableCollection = values;
    Object localObject = localImmutableCollection;
    if (localImmutableCollection == null)
    {
      localObject = new Values(this);
      values = ((ImmutableCollection)localObject);
    }
    return (ImmutableCollection<V>)localObject;
  }
  
  private static class EntrySet<K, V>
    extends ImmutableSet.ArrayImmutableSet<Map.Entry<K, V>>
  {
    final transient RegularImmutableMap<K, V> map;
    
    EntrySet(RegularImmutableMap<K, V> paramRegularImmutableMap)
    {
      super();
      map = paramRegularImmutableMap;
    }
    
    public boolean contains(Object paramObject)
    {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if ((paramObject instanceof Map.Entry))
      {
        paramObject = (Map.Entry)paramObject;
        Object localObject = map.get(((Map.Entry)paramObject).getKey());
        bool1 = bool2;
        if (localObject != null)
        {
          bool1 = bool2;
          if (localObject.equals(((Map.Entry)paramObject).getValue())) {
            bool1 = true;
          }
        }
      }
      return bool1;
    }
  }
  
  private static class KeySet<K, V>
    extends ImmutableSet.TransformedImmutableSet<Map.Entry<K, V>, K>
  {
    final RegularImmutableMap<K, V> map;
    
    KeySet(RegularImmutableMap<K, V> paramRegularImmutableMap)
    {
      super(keySetHashCode);
      map = paramRegularImmutableMap;
    }
    
    public boolean contains(Object paramObject)
    {
      return map.containsKey(paramObject);
    }
    
    K transform(Map.Entry<K, V> paramEntry)
    {
      return (K)paramEntry.getKey();
    }
  }
  
  private static abstract interface LinkedEntry<K, V>
    extends Map.Entry<K, V>
  {
    public abstract LinkedEntry<K, V> next();
  }
  
  private static final class NonTerminalEntry<K, V>
    extends ImmutableEntry<K, V>
    implements RegularImmutableMap.LinkedEntry<K, V>
  {
    final RegularImmutableMap.LinkedEntry<K, V> next;
    
    NonTerminalEntry(K paramK, V paramV, RegularImmutableMap.LinkedEntry<K, V> paramLinkedEntry)
    {
      super(paramV);
      next = paramLinkedEntry;
    }
    
    public RegularImmutableMap.LinkedEntry<K, V> next()
    {
      return next;
    }
  }
  
  private static final class TerminalEntry<K, V>
    extends ImmutableEntry<K, V>
    implements RegularImmutableMap.LinkedEntry<K, V>
  {
    TerminalEntry(K paramK, V paramV)
    {
      super(paramV);
    }
    
    public RegularImmutableMap.LinkedEntry<K, V> next()
    {
      return null;
    }
  }
  
  private static class Values<V>
    extends ImmutableCollection<V>
  {
    final RegularImmutableMap<?, V> map;
    
    Values(RegularImmutableMap<?, V> paramRegularImmutableMap)
    {
      map = paramRegularImmutableMap;
    }
    
    public boolean contains(Object paramObject)
    {
      return map.containsValue(paramObject);
    }
    
    public UnmodifiableIterator<V> iterator()
    {
      new AbstractIndexedListIterator(map.entries.length)
      {
        protected V get(int paramAnonymousInt)
        {
          return (V)map.entries[paramAnonymousInt].getValue();
        }
      };
    }
    
    public int size()
    {
      return map.entries.length;
    }
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */