package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

public class an
{
  private ai a;
  private Context b;
  
  public an(Context paramContext)
  {
    b = paramContext;
    a = a(b);
  }
  
  private ai a(Context paramContext)
  {
    try
    {
      paramContext = new ai(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ay.a(paramContext, "SDKDB", "getDB");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public List<ad> a()
  {
    try
    {
      Object localObject = new ao();
      String str = ao.c();
      localObject = a.c(str, (ap)localObject);
      return (List<ad>)localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void a(ad paramad)
  {
    if (paramad == null) {
      return;
    }
    ao localao;
    try
    {
      if (a == null) {
        a = a(b);
      }
      localao = new ao();
      localao.a(paramad);
      paramad = ao.a(paramad.a());
      List localList = a.c(paramad, new ao());
      if ((localList == null) || (localList.size() == 0))
      {
        a.a(localao);
        return;
      }
    }
    catch (Throwable paramad)
    {
      ay.a(paramad, "SDKDB", "insert");
      paramad.printStackTrace();
      return;
    }
    a.b(paramad, localao);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.an
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */