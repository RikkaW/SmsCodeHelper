package com.xiaomi.mms.utils;

import java.util.HashMap;

public class EasyMap<K, V>
  extends HashMap<K, V>
{
  public EasyMap() {}
  
  public EasyMap(K paramK, V paramV)
  {
    put(paramK, paramV);
  }
  
  public EasyMap<K, V> easyPut(K paramK, V paramV)
  {
    put(paramK, paramV);
    return this;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.EasyMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */