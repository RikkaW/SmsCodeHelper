package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.List;

public class dv
{
  private dn a;
  private Context b;
  
  public dv(Context paramContext)
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
      ed.a(paramContext, "UpdateLogDB", "getDB");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public dx a()
  {
    try
    {
      if (a == null) {
        a = a(b);
      }
      Object localObject = a.c("1=1", new dw());
      if (((List)localObject).size() > 0)
      {
        localObject = (dx)((List)localObject).get(0);
        return (dx)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      ed.a(localThrowable, "UpdateLogDB", "getUpdateLog");
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void a(dx paramdx)
  {
    if (paramdx == null) {
      return;
    }
    dw localdw;
    try
    {
      if (a == null) {
        a = a(b);
      }
      localdw = new dw();
      localdw.a(paramdx);
      paramdx = a.c("1=1", new dw());
      if ((paramdx == null) || (paramdx.size() == 0))
      {
        a.a(localdw);
        return;
      }
    }
    catch (Throwable paramdx)
    {
      ed.a(paramdx, "UpdateLogDB", "updateLog");
      paramdx.printStackTrace();
      return;
    }
    a.b("1=1", localdw);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.dv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */