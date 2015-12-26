package flyme.support.v7.widget;

import android.view.View;
import arl;

class LinearLayoutManager$a
{
  int a;
  int b;
  boolean c;
  
  private boolean a(View paramView, RecyclerView.q paramq)
  {
    paramView = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    return (!paramView.a()) && (paramView.c() >= 0) && (paramView.c() < paramq.f());
  }
  
  void a()
  {
    a = -1;
    b = Integer.MIN_VALUE;
    c = false;
  }
  
  public void a(View paramView)
  {
    int j = d.b.b();
    if (j >= 0) {
      b(paramView);
    }
    int i;
    do
    {
      int k;
      do
      {
        do
        {
          do
          {
            return;
            a = d.d(paramView);
            if (!c) {
              break;
            }
            i = d.b.d() - j - d.b.b(paramView);
            b = (d.b.d() - i);
          } while (i <= 0);
          j = d.b.c(paramView);
          k = b;
          m = d.b.c();
          j = k - j - (m + Math.min(d.b.a(paramView) - m, 0));
        } while (j >= 0);
        k = b;
        b = (Math.min(i, -j) + k);
        return;
        k = d.b.a(paramView);
        i = k - d.b.c();
        b = k;
      } while (i <= 0);
      int m = d.b.c(paramView);
      int n = d.b.d();
      int i1 = d.b.b(paramView);
      j = d.b.d() - Math.min(0, n - j - i1) - (k + m);
    } while (j >= 0);
    b -= Math.min(i, -j);
  }
  
  void b()
  {
    if (c) {}
    for (int i = d.b.d();; i = d.b.c())
    {
      b = i;
      return;
    }
  }
  
  public void b(View paramView)
  {
    if (c) {}
    for (b = (d.b.b(paramView) + d.b.b());; b = d.b.a(paramView))
    {
      a = d.d(paramView);
      return;
    }
  }
  
  public String toString()
  {
    return "AnchorInfo{mPosition=" + a + ", mCoordinate=" + b + ", mLayoutFromEnd=" + c + '}';
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.LinearLayoutManager.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */