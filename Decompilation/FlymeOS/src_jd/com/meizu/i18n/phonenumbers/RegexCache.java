package com.meizu.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class RegexCache
{
  private LRUCache<String, Pattern> cache;
  
  public RegexCache(int paramInt)
  {
    cache = new LRUCache(paramInt);
  }
  
  boolean containsRegex(String paramString)
  {
    return cache.containsKey(paramString);
  }
  
  public Pattern getPatternForRegex(String paramString)
  {
    Pattern localPattern2 = (Pattern)cache.get(paramString);
    Pattern localPattern1 = localPattern2;
    if (localPattern2 == null)
    {
      localPattern1 = Pattern.compile(paramString);
      cache.put(paramString, localPattern1);
    }
    return localPattern1;
  }
  
  static class LRUCache<K, V>
  {
    private LinkedHashMap<K, V> map;
    private int size;
    
    public LRUCache(int paramInt)
    {
      size = paramInt;
      map = new RegexCache.LRUCache.1(this, paramInt * 4 / 3 + 1, 0.75F, true);
    }
    
    public boolean containsKey(K paramK)
    {
      try
      {
        boolean bool = map.containsKey(paramK);
        return bool;
      }
      finally
      {
        paramK = finally;
        throw paramK;
      }
    }
    
    public V get(K paramK)
    {
      try
      {
        paramK = map.get(paramK);
        return paramK;
      }
      finally
      {
        paramK = finally;
        throw paramK;
      }
    }
    
    public void put(K paramK, V paramV)
    {
      try
      {
        map.put(paramK, paramV);
        return;
      }
      finally
      {
        paramK = finally;
        throw paramK;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.RegexCache
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */