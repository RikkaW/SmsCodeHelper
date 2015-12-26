package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Collection;

final class Collections2$1
  implements Function<Object, Object>
{
  Collections2$1(Collection paramCollection) {}
  
  public Object apply(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == val$collection) {
      localObject = "(this Collection)";
    }
    return localObject;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.Collections2.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */