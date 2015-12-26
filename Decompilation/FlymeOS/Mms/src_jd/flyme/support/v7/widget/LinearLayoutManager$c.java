package flyme.support.v7.widget;

import android.view.View;
import java.util.List;

class LinearLayoutManager$c
{
  boolean a = true;
  int b;
  int c;
  int d;
  int e;
  int f;
  int g;
  int h = 0;
  boolean i = false;
  int j;
  List<RecyclerView.t> k = null;
  
  private View b()
  {
    int n = k.size();
    int m = 0;
    if (m < n)
    {
      RecyclerView.t localt = (RecyclerView.t)k.get(m);
      if (localt.p()) {}
      while (d != localt.d())
      {
        m += 1;
        break;
      }
      a(localt);
      return a;
    }
    return null;
  }
  
  View a(RecyclerView.m paramm)
  {
    if (k != null) {
      return b();
    }
    paramm = paramm.b(d);
    d += e;
    return paramm;
  }
  
  public void a()
  {
    a(null);
  }
  
  public void a(RecyclerView.t paramt)
  {
    paramt = b(paramt);
    if (paramt == null) {}
    for (int m = -1;; m = paramt.d())
    {
      d = m;
      return;
    }
  }
  
  boolean a(RecyclerView.q paramq)
  {
    return (d >= 0) && (d < paramq.f());
  }
  
  public RecyclerView.t b(RecyclerView.t paramt)
  {
    int i2 = k.size();
    Object localObject = null;
    int m = Integer.MAX_VALUE;
    int n = 0;
    if (n < i2)
    {
      RecyclerView.t localt = (RecyclerView.t)k.get(n);
      if (localt != paramt) {
        if (!localt.p()) {}
      }
      for (;;)
      {
        n += 1;
        break;
        int i1 = (localt.d() - d) * e;
        if (i1 >= 0) {
          if (i1 < m)
          {
            if (i1 == 0) {
              return localt;
            }
            localObject = localt;
            m = i1;
          }
        }
      }
    }
    return (RecyclerView.t)localObject;
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.LinearLayoutManager.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */