package com.amap.api.mapcore2d;

class cg
  extends h
{
  private ae c;
  private ae d;
  private int e;
  private int f;
  private int g;
  private int h;
  private int i;
  private int j;
  private int k;
  private ch l;
  
  public cg(int paramInt1, int paramInt2, ae paramae1, ae paramae2, int paramInt3, ch paramch)
  {
    super(paramInt1, paramInt2);
    c = paramae1;
    d = paramae2;
    e = ((int)c.e());
    f = ((int)c.f());
    l = paramch;
    i = ((int)Math.abs(paramae2.e() - c.e()));
    j = ((int)Math.abs(paramae2.f() - c.f()));
    a(paramInt3);
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 > paramInt1)
    {
      paramInt3 = paramInt1 + paramInt3;
      paramInt1 = paramInt3;
      if (paramInt3 >= paramInt2)
      {
        k = 0;
        return paramInt2;
      }
    }
    else
    {
      paramInt3 = paramInt1 - paramInt3;
      paramInt1 = paramInt3;
      if (paramInt3 <= paramInt2)
      {
        k = 0;
        return paramInt2;
      }
    }
    return paramInt1;
  }
  
  private void a(int paramInt)
  {
    int m = 2;
    paramInt = paramInt / 10 / 10;
    if (paramInt < 2) {
      paramInt = m;
    }
    for (;;)
    {
      g = (i / paramInt);
      h = (j / paramInt);
      return;
    }
  }
  
  protected void a()
  {
    int m = (int)d.e();
    int n = (int)d.f();
    if (!e())
    {
      e = m;
      f = n;
      l.a(new ae(f, e, false));
    }
    do
    {
      return;
      k += 1;
      e = a(e, m, g);
      f = a(f, n, h);
      l.a(new ae(f, e, false));
    } while ((e != m) || (f != n));
    a(false);
    f();
  }
  
  protected void b()
  {
    l.b();
    t.a().b();
  }
  
  protected void f()
  {
    bv.a().b();
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */