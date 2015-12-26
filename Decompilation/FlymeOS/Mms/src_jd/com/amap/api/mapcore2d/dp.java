package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.List;

public class dp
{
  private dn a;
  
  public dp(Context paramContext)
  {
    a = new dn(paramContext);
  }
  
  private dq a(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return null;
    case 0: 
      return new dl();
    case 1: 
      return new do();
    }
    return new dk();
  }
  
  private void a(dr paramdr, dq paramdq)
  {
    paramdq.a(paramdr);
    a.a(paramdq);
  }
  
  private void b(dr paramdr, dq paramdq)
  {
    String str = dq.a(paramdr.b());
    Object localObject = a.c(str, paramdq);
    if ((localObject == null) || (((List)localObject).size() == 0))
    {
      paramdq.a(paramdr);
      a.a(paramdq);
      return;
    }
    localObject = (dr)((List)localObject).get(0);
    if (paramdr.a() == 0) {
      ((dr)localObject).b(((dr)localObject).d() + 1);
    }
    for (;;)
    {
      paramdq.a((dr)localObject);
      a.b(str, paramdq);
      return;
      ((dr)localObject).b(0);
    }
  }
  
  private void c(String paramString, int paramInt)
  {
    paramString = dq.a(paramString);
    dq localdq = a(paramInt);
    a.a(paramString, localdq);
  }
  
  public List<dr> a(int paramInt1, int paramInt2)
  {
    try
    {
      Object localObject = a(paramInt2);
      String str = dq.a(paramInt1);
      localObject = a.c(str, (du)localObject);
      return (List<dr>)localObject;
    }
    catch (Throwable localThrowable)
    {
      ed.a(localThrowable, "LogDB", "ByState");
      localThrowable.printStackTrace();
    }
    return null;
  }
  
  public void a(dr paramdr, int paramInt)
  {
    try
    {
      dq localdq = a(paramInt);
      localdq.a(paramdr);
      paramdr = dq.a(paramdr.b());
      a.b(paramdr, localdq);
      return;
    }
    catch (Throwable paramdr)
    {
      ed.a(paramdr, "LogDB", "updateLogInfo");
      paramdr.printStackTrace();
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
      ed.a(paramString, "LogDB", "delLog");
      paramString.printStackTrace();
    }
  }
  
  public void b(dr paramdr, int paramInt)
  {
    dq localdq;
    try
    {
      localdq = a(paramInt);
      switch (paramInt)
      {
      case 0: 
        a(paramdr, localdq);
        return;
      }
    }
    catch (Throwable paramdr)
    {
      paramdr.printStackTrace();
      return;
    }
    b(paramdr, localdq);
    return;
    b(paramdr, localdq);
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
 * Qualified Name:     com.amap.api.mapcore2d.dp
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */