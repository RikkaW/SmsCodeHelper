package com.android.mms.model;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import lj;

public class Model
{
  public CopyOnWriteArrayList<lj> n = new CopyOnWriteArrayList();
  
  public void B()
  {
    h();
    n.clear();
  }
  
  public void a(lj paramlj) {}
  
  public void a(boolean paramBoolean)
  {
    Iterator localIterator = n.iterator();
    while (localIterator.hasNext()) {
      ((lj)localIterator.next()).onModelChanged(this, paramBoolean);
    }
  }
  
  public void b(lj paramlj) {}
  
  public void c(lj paramlj)
  {
    if (!n.contains(paramlj))
    {
      n.add(paramlj);
      a(paramlj);
    }
  }
  
  public void d(lj paramlj)
  {
    n.remove(paramlj);
    b(paramlj);
  }
  
  public void h() {}
}

/* Location:
 * Qualified Name:     com.android.mms.model.Model
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */