package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

final class MapMaker$NullComputingConcurrentMap<K, V>
  extends MapMaker.NullConcurrentMap<K, V>
{
  private static final long serialVersionUID = 0L;
  final Function<? super K, ? extends V> computingFunction;
  
  MapMaker$NullComputingConcurrentMap(MapMaker paramMapMaker, Function<? super K, ? extends V> paramFunction)
  {
    super(paramMapMaker);
    computingFunction = ((Function)Preconditions.checkNotNull(paramFunction));
  }
  
  private V compute(K paramK)
  {
    Preconditions.checkNotNull(paramK);
    try
    {
      paramK = computingFunction.apply(paramK);
      return paramK;
    }
    catch (ComputationException paramK)
    {
      throw paramK;
    }
    catch (Throwable paramK)
    {
      throw new ComputationException(paramK);
    }
  }
  
  public V get(Object paramObject)
  {
    Object localObject = compute(paramObject);
    Preconditions.checkNotNull(localObject, computingFunction + " returned null for key " + paramObject + ".");
    notifyRemoval(paramObject, localObject);
    return (V)localObject;
  }
}

/* Location:
 * Qualified Name:     com.google.common.collect.MapMaker.NullComputingConcurrentMap
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */