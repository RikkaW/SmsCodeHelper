package com.meizu.i18n.phonenumbers;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

class RegexCache$LRUCache$1
  extends LinkedHashMap<K, V>
{
  RegexCache$LRUCache$1(RegexCache.LRUCache paramLRUCache, int paramInt, float paramFloat, boolean paramBoolean)
  {
    super(paramInt, paramFloat, paramBoolean);
  }
  
  protected boolean removeEldestEntry(Map.Entry<K, V> paramEntry)
  {
    return size() > RegexCache.LRUCache.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.meizu.i18n.phonenumbers.RegexCache.LRUCache.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */