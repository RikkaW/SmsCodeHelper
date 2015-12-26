package com.google.common.collect;

class ImmutableSet$TransformedImmutableSet$1
  extends AbstractIndexedListIterator<E>
{
  ImmutableSet$TransformedImmutableSet$1(ImmutableSet.TransformedImmutableSet paramTransformedImmutableSet, int paramInt)
  {
    super(paramInt);
  }
  
  protected E get(int paramInt)
  {
    return (E)this$0.transform(this$0.source[paramInt]);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableSet.TransformedImmutableSet.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */