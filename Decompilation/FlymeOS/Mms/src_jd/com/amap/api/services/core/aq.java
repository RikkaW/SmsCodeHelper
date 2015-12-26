package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

public class aq
{
  private ai a;
  private Context b;
  
  public aq(Context paramContext)
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
      ay.a(paramContext, "UpdateLogDB", "getDB");
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public as a()
  {
    try
    {
      if (a == null) {
        a = a(b);
      }
      Object localObject = a.c("1=1", new ar());
      if (((List)localObject).size() > 0)
      {
        localObject = (as)((List)localObject).get(0);
        return (as)localObject;
      }
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "UpdateLogDB", "getUpdateLog");
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void a(as paramas)
  {
    if (paramas == null) {
      return;
    }
    ar localar;
    try
    {
      if (a == null) {
        a = a(b);
      }
      localar = new ar();
      localar.a(paramas);
      paramas = a.c("1=1", new ar());
      if ((paramas == null) || (paramas.size() == 0))
      {
        a.a(localar);
        return;
      }
    }
    catch (Throwable paramas)
    {
      ay.a(paramas, "UpdateLogDB", "updateLog");
      paramas.printStackTrace();
      return;
    }
    a.b("1=1", localar);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.aq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */