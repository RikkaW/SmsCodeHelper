package com.google.common.collect;

class RegularImmutableMap$Values$1
  extends AbstractIndexedListIterator<V>
{
  RegularImmutableMap$Values$1(RegularImmutableMap.Values paramValues, int paramInt)
  {
    super(paramInt);
  }
  
  protected V get(int paramInt)
  {
    return (V)RegularImmutableMap.access$000(this$0.map)[paramInt].getValue();
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.RegularImmutableMap.Values.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */