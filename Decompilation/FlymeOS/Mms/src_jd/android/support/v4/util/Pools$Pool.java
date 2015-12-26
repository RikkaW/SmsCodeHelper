package android.support.v4.util;

public abstract interface Pools$Pool<T>
{
  public abstract T acquire();
  
  public abstract boolean release(T paramT);
}

/* Location:
 * Qualified Name:     android.support.v4.util.Pools.Pool
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */