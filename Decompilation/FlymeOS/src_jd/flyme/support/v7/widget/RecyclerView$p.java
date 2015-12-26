package flyme.support.v7.widget;

import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;

public abstract class RecyclerView$p
{
  private int a;
  private RecyclerView b;
  private RecyclerView.h c;
  private boolean d;
  private boolean e;
  private View f;
  private final a g;
  
  private void a(int paramInt1, int paramInt2)
  {
    RecyclerView localRecyclerView = b;
    if ((!e) || (a == -1) || (localRecyclerView == null)) {
      a();
    }
    d = false;
    if (f != null)
    {
      if (a(f) != a) {
        break label151;
      }
      a(f, t, g);
      a.a(g, localRecyclerView);
      a();
    }
    for (;;)
    {
      if (e)
      {
        a(paramInt1, paramInt2, t, g);
        boolean bool = g.a();
        a.a(g, localRecyclerView);
        if (bool)
        {
          if (!e) {
            break;
          }
          d = true;
          RecyclerView.m(localRecyclerView).a();
        }
      }
      return;
      label151:
      Log.e("RecyclerView", "Passed over target position while smooth scrolling.");
      f = null;
    }
    a();
  }
  
  public int a(View paramView)
  {
    return b.c(paramView);
  }
  
  protected final void a()
  {
    if (!e) {
      return;
    }
    e();
    RecyclerView.q.c(b.t, -1);
    f = null;
    a = -1;
    d = false;
    e = false;
    RecyclerView.h.a(c, this);
    c = null;
    b = null;
  }
  
  public void a(int paramInt)
  {
    a = paramInt;
  }
  
  protected abstract void a(int paramInt1, int paramInt2, RecyclerView.q paramq, a parama);
  
  protected abstract void a(View paramView, RecyclerView.q paramq, a parama);
  
  protected void b(View paramView)
  {
    if (a(paramView) == d()) {
      f = paramView;
    }
  }
  
  public boolean b()
  {
    return d;
  }
  
  public boolean c()
  {
    return e;
  }
  
  public int d()
  {
    return a;
  }
  
  protected abstract void e();
  
  public static class a
  {
    private int a;
    private int b;
    private int c;
    private int d;
    private Interpolator e;
    private boolean f;
    private int g;
    
    private void a(RecyclerView paramRecyclerView)
    {
      if (d >= 0)
      {
        int i = d;
        d = -1;
        RecyclerView.a(paramRecyclerView, i);
        f = false;
        return;
      }
      if (f)
      {
        b();
        if (e == null) {
          if (c == Integer.MIN_VALUE) {
            RecyclerView.m(paramRecyclerView).b(a, b);
          }
        }
        for (;;)
        {
          g += 1;
          if (g > 10) {
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
          }
          f = false;
          return;
          RecyclerView.m(paramRecyclerView).a(a, b, c);
          continue;
          RecyclerView.m(paramRecyclerView).a(a, b, c, e);
        }
      }
      g = 0;
    }
    
    private void b()
    {
      if ((e != null) && (c < 1)) {
        throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
      }
      if (c < 1) {
        throw new IllegalStateException("Scroll duration must be a positive number");
      }
    }
    
    boolean a()
    {
      return d >= 0;
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.p
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */