package com.google.common.collect;

import java.io.Serializable;

class ImmutableSet$SerializedForm
  implements Serializable
{
  private static final long serialVersionUID = 0L;
  final Object[] elements;
  
  ImmutableSet$SerializedForm(Object[] paramArrayOfObject)
  {
    elements = paramArrayOfObject;
  }
  
  Object readResolve()
  {
    return ImmutableSet.copyOf(elements);
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableSet.SerializedForm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */