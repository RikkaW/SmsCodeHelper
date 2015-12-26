package com.amap.api.mapcore2d;

import android.util.SparseArray;

public class bl$b
{
  public boolean a = false;
  int b = 0;
  
  public bl$b(bl parambl)
  {
    e();
  }
  
  public void a()
  {
    if (bl.a.a(c.d)) {
      c.d.b();
    }
    b += 1;
    if ((b < 20) || (b % 20 != 0)) {
      return;
    }
    int i = 0;
    label55:
    bi localbi;
    if (i < bl.c.a(c.e).size())
    {
      localbi = (bi)bl.c.a(c.e).valueAt(i);
      if (localbi != null) {
        break label101;
      }
    }
    for (;;)
    {
      i += 1;
      break label55;
      break;
      label101:
      localbi.h();
    }
  }
  
  public void b()
  {
    c.b.a = false;
    int i = 0;
    if (i < bl.c.a(c.e).size())
    {
      bi localbi = (bi)bl.c.a(c.e).valueAt(i);
      if (localbi == null) {}
      for (;;)
      {
        i += 1;
        break;
        localbi.a();
      }
    }
  }
  
  public void c()
  {
    int i = 0;
    if (i < bl.c.a(c.e).size())
    {
      bi localbi = (bi)bl.c.a(c.e).valueAt(i);
      if (localbi == null) {}
      for (;;)
      {
        i += 1;
        break;
        localbi.c();
      }
    }
  }
  
  public void d()
  {
    int i = 0;
    if (i < bl.c.a(c.e).size())
    {
      bi localbi = (bi)bl.c.a(c.e).valueAt(i);
      if (localbi == null) {}
      for (;;)
      {
        i += 1;
        break;
        localbi.b();
      }
    }
  }
  
  public void e()
  {
    int i = 0;
    if (i < bl.c.a(c.e).size())
    {
      bi localbi = (bi)bl.c.a(c.e).valueAt(i);
      if (localbi == null) {}
      for (;;)
      {
        i += 1;
        break;
        localbi.g();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.bl.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */