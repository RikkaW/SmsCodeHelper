package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.Iterator;

class t
{
  private static t a = new t();
  private ArrayList<a> b = new ArrayList();
  
  public static t a()
  {
    return a;
  }
  
  public void a(a parama)
  {
    if (parama != null) {
      b.add(parama);
    }
  }
  
  public void b()
  {
    Iterator localIterator = b.iterator();
    while (localIterator.hasNext())
    {
      a locala = (a)localIterator.next();
      if (locala != null) {
        locala.O();
      }
    }
  }
  
  public void b(a parama)
  {
    if (parama != null) {
      b.remove(parama);
    }
  }
  
  public static abstract interface a
  {
    public abstract void O();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */