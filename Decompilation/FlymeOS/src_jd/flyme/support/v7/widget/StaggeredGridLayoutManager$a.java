package flyme.support.v7.widget;

import arl;

class StaggeredGridLayoutManager$a
{
  int a;
  int b;
  boolean c;
  boolean d;
  
  void a()
  {
    a = -1;
    b = Integer.MIN_VALUE;
    c = false;
    d = false;
  }
  
  void a(int paramInt)
  {
    if (c)
    {
      b = (e.a.d() - paramInt);
      return;
    }
    b = (e.a.c() + paramInt);
  }
  
  void b()
  {
    if (c) {}
    for (int i = e.a.d();; i = e.a.c())
    {
      b = i;
      return;
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.StaggeredGridLayoutManager.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */