package android.support.v4.util;

public class Pools$SimplePool<T>
  implements Pools.Pool<T>
{
  private final Object[] mPool;
  private int mPoolSize;
  
  public Pools$SimplePool(int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException("The max pool size must be > 0");
    }
    mPool = new Object[paramInt];
  }
  
  private boolean isInPool(T paramT)
  {
    boolean bool2 = false;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < mPoolSize)
      {
        if (mPool[i] == paramT) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public T acquire()
  {
    if (mPoolSize > 0)
    {
      int i = mPoolSize - 1;
      Object localObject = mPool[i];
      mPool[i] = null;
      mPoolSize -= 1;
      return (T)localObject;
    }
    return null;
  }
  
  public boolean release(T paramT)
  {
    if (isInPool(paramT)) {
      throw new IllegalStateException("Already in the pool!");
    }
    if (mPoolSize < mPool.length)
    {
      mPool[mPoolSize] = paramT;
      mPoolSize += 1;
      return true;
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.util.Pools.SimplePool
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */