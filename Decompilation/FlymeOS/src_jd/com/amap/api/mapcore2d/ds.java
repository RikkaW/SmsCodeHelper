package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.List;

public class ds
{
  private dn a;
  private Context b;
  
  public ds(Context paramContext)
  {
    b = paramContext;
    a = a(b);
  }
  
  private dn a(Context paramContext)
  {
    try
    {
      paramContext = new dn(paramContext);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      ed.a(paramContext, "SDKDB", "getDB");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public List<dh> a()
  {
    try
    {
      Object localObject = new dt();
      String str = dt.c();
      localObject = a.c(str, (du)localObject);
      return (List<dh>)localObject;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void a(dh paramdh)
  {
    if (paramdh == null) {
      return;
    }
    dt localdt;
    try
    {
      if (a == null) {
        a = a(b);
      }
      localdt = new dt();
      localdt.a(paramdh);
      paramdh = dt.a(paramdh.a());
      List localList = a.c(paramdh, new dt());
      if ((localList == null) || (localList.size() == 0))
      {
        a.a(localdt);
        return;
      }
    }
    catch (Throwable paramdh)
    {
      ed.a(paramdh, "SDKDB", "insert");
      paramdh.printStackTrace();
      return;
    }
    a.b(paramdh, localdt);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ds
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */