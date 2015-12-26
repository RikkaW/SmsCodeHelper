package com.google.common.collect;

import java.io.Serializable;

class ImmutableCollection$SerializedForm
  implements Serializable
{
  private static final long serialVersionUID = 0L;
  final Object[] elements;
  
  ImmutableCollection$SerializedForm(Object[] paramArrayOfObject)
  {
    elements = paramArrayOfObject;
  }
  
  Object readResolve()
  {
    if (elements.length == 0) {
      return ImmutableCollection.EMPTY_IMMUTABLE_COLLECTION;
    }
    return new ImmutableCollection.ArrayImmutableCollection(Platform.clone(elements));
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.ImmutableCollection.SerializedForm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */