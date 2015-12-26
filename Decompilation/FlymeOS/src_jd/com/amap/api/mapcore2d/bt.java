package com.amap.api.mapcore2d;

import com.amap.api.maps2d.AMapException;

abstract class bt<T, V>
  extends ey
{
  protected T a;
  private int e = 1;
  private int f = 0;
  
  public bt() {}
  
  public bt(T paramT)
  {
    this();
    a = paramT;
  }
  
  private V b(byte[] paramArrayOfByte)
  {
    return (V)a(paramArrayOfByte);
  }
  
  private V g()
  {
    int i = 0;
    Object localObject1 = null;
    Object localObject3;
    while (i < e)
    {
      Object localObject4 = localObject1;
      Object localObject5 = localObject1;
      try
      {
        localObject1 = b(ex.a(false).b(this));
        localObject4 = localObject1;
        localObject5 = localObject1;
        int j = e;
        i = j;
      }
      catch (AMapException localAMapException)
      {
        cy.a(localAMapException, "ProtocalHandler", "GetDataMaythrow");
        i += 1;
        if (i < e)
        {
          try
          {
            Thread.sleep(f * 1000);
            Object localObject2 = localObject4;
          }
          catch (InterruptedException localInterruptedException1)
          {
            throw new AMapException(localInterruptedException1.getMessage());
          }
        }
        else
        {
          localInterruptedException1.printStackTrace();
          f();
          throw new AMapException(localInterruptedException1.getErrorMessage());
        }
      }
      catch (cz localcz)
      {
        i += 1;
        if (i < e)
        {
          try
          {
            Thread.sleep(f * 1000);
            cy.a(localcz, "ProtocalHandler", "GetDataMaythrow");
            localObject3 = localObject5;
          }
          catch (InterruptedException localInterruptedException2)
          {
            throw new AMapException(((cz)localObject3).getMessage());
          }
        }
        else
        {
          ((cz)localObject3).printStackTrace();
          f();
          throw new AMapException(((cz)localObject3).a());
        }
      }
    }
    return (V)localObject3;
  }
  
  public V a()
  {
    Object localObject = null;
    if (a != null) {
      localObject = g();
    }
    return (V)localObject;
  }
  
  protected abstract V a(byte[] paramArrayOfByte);
  
  protected V f()
  {
    return null;
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */