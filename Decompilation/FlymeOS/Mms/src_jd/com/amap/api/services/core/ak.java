package com.amap.api.services.core;

import android.content.Context;
import java.util.List;

public class ak
{
  private ai a;
  
  public ak(Context paramContext)
  {
    a = new ai(paramContext);
  }
  
  private al a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return new ag();
    case 1: 
      return new aj();
    }
    return new af();
  }
  
  private void a(am paramam, al paramal)
  {
    paramal.a(paramam);
    a.a(paramal);
  }
  
  private void b(am paramam, al paramal)
  {
    String str = al.a(paramam.b());
    Object localObject = a.c(str, paramal);
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      paramal.a(paramam);
      a.a(paramal);
      return;
    }
    localObject = (am)((List)localObject).get(0);
    if (paramam.a() == 0) {
      ((am)localObject).b(((am)localObject).d() + 1);
    }
    for (;;)
    {
      paramal.a((am)localObject);
      a.b(str, paramal);
      return;
      ((am)localObject).b(0);
    }
  }
  
  private void c(String paramString, int paramInt)
  {
    paramString = al.a(paramString);
    al localal = a(paramInt);
    a.a(paramString, localal);
  }
  
  public List<am> a(int paramInt1, int paramInt2)
  {
    try
    {
      Object localObject = a(paramInt2);
      String str = al.a(paramInt1);
      localObject = a.c(str, (ap)localObject);
      return (List<am>)localObject;
    }
    catch (Throwable localThrowable)
    {
      ay.a(localThrowable, "LogDB", "ByState");
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void a(am paramam, int paramInt)
  {
    try
    {
      al localal = a(paramInt);
      localal.a(paramam);
      paramam = al.a(paramam.b());
      a.b(paramam, localal);
      return;
    }
    catch (Throwable paramam)
    {
      ay.a(paramam, "LogDB", "updateLogInfo");
      paramam.printStackTrace();
    }
  }
  
  public void a(String paramString, int paramInt)
  {
    try
    {
      c(paramString, paramInt);
      return;
    }
    catch (Throwable paramString)
    {
      ay.a(paramString, "LogDB", "delLog");
      paramString.printStackTrace();
    }
  }
  
  public void b(am paramam, int paramInt)
  {
    al localal;
    try
    {
      localal = a(paramInt);
      switch (paramInt)
      {
      case 0: 
        a(paramam, localal);
        return;
      }
    }
    catch (Throwable paramam)
    {
      paramam.printStackTrace();
      return;
    }
    b(paramam, localal);
    return;
    b(paramam, localal);
    return;
  }
  
  public void b(String paramString, int paramInt)
  {
    try
    {
      c(paramString, paramInt);
      return;
    }
    catch (Throwable paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.services.core.ak
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */