package flyme.support.v7.widget;

import android.support.v4.os.TraceCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.view.View;
import android.view.animation.Interpolator;
import ard;
import java.util.ArrayList;

class RecyclerView$s
  implements Runnable
{
  private int b;
  private int c;
  private ScrollerCompat d;
  private Interpolator e;
  private boolean f;
  private boolean g;
  
  private float a(float paramFloat)
  {
    return (float)Math.sin((float)((paramFloat - 0.5F) * 0.4712389167638204D));
  }
  
  private int b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = Math.abs(paramInt1);
    int k = Math.abs(paramInt2);
    int i;
    if (j > k)
    {
      i = 1;
      paramInt3 = (int)Math.sqrt(paramInt3 * paramInt3 + paramInt4 * paramInt4);
      paramInt2 = (int)Math.sqrt(paramInt1 * paramInt1 + paramInt2 * paramInt2);
      if (i == 0) {
        break label140;
      }
    }
    label140:
    for (paramInt1 = a.getWidth();; paramInt1 = a.getHeight())
    {
      paramInt4 = paramInt1 / 2;
      float f3 = Math.min(1.0F, paramInt2 * 1.0F / paramInt1);
      float f1 = paramInt4;
      float f2 = paramInt4;
      f3 = a(f3);
      if (paramInt3 <= 0) {
        break label151;
      }
      paramInt1 = Math.round(1000.0F * Math.abs((f3 * f2 + f1) / paramInt3)) * 4;
      return Math.min(paramInt1, 2000);
      i = 0;
      break;
    }
    label151:
    if (i != 0) {}
    for (paramInt2 = j;; paramInt2 = k)
    {
      paramInt1 = (int)((paramInt2 / paramInt1 + 1.0F) * 300.0F);
      break;
    }
  }
  
  private void c()
  {
    g = false;
    f = true;
  }
  
  private void d()
  {
    f = false;
    if (g) {
      a();
    }
  }
  
  void a()
  {
    if (f)
    {
      g = true;
      return;
    }
    a.removeCallbacks(this);
    ViewCompat.postOnAnimation(a, this);
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    a.setScrollState(2);
    c = 0;
    b = 0;
    d.fling(0, 0, paramInt1, paramInt2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
    a();
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3)
  {
    a(paramInt1, paramInt2, paramInt3, RecyclerView.t());
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a(paramInt1, paramInt2, b(paramInt1, paramInt2, paramInt3, paramInt4));
  }
  
  public void a(int paramInt1, int paramInt2, int paramInt3, Interpolator paramInterpolator)
  {
    if (e != paramInterpolator)
    {
      e = paramInterpolator;
      d = ScrollerCompat.create(a.getContext(), paramInterpolator);
    }
    a.setScrollState(2);
    c = 0;
    b = 0;
    d.startScroll(0, 0, paramInt1, paramInt2, paramInt3);
    a();
  }
  
  public void b()
  {
    a.removeCallbacks(this);
    d.abortAnimation();
  }
  
  public void b(int paramInt1, int paramInt2)
  {
    a(paramInt1, paramInt2, 0, 0);
  }
  
  public void run()
  {
    c();
    RecyclerView.b(a);
    ScrollerCompat localScrollerCompat = d;
    RecyclerView.p localp = aa).j;
    int i6;
    int i7;
    int i4;
    int i5;
    int i1;
    int k;
    int j;
    int i3;
    int i;
    int n;
    int m;
    if (localScrollerCompat.computeScrollOffset())
    {
      i6 = localScrollerCompat.getCurrX();
      i7 = localScrollerCompat.getCurrY();
      i4 = i6 - b;
      i5 = i7 - c;
      i1 = 0;
      k = 0;
      i2 = 0;
      j = 0;
      b = i6;
      c = i7;
      i3 = 0;
      i = 0;
      n = 0;
      m = 0;
      if (RecyclerView.c(a) == null) {
        break label849;
      }
      a.g();
      RecyclerView.d(a);
      TraceCompat.beginSection("RV Scroll");
      if (i4 != 0)
      {
        k = RecyclerView.a(a).a(i4, a.p, a.t);
        i = i4 - k;
      }
      if (i5 != 0)
      {
        j = RecyclerView.a(a).b(i5, a.p, a.t);
        m = i5 - j;
      }
      TraceCompat.endSection();
      if (RecyclerView.e(a))
      {
        i1 = a.r.b();
        n = 0;
        while (n < i1)
        {
          View localView = a.r.b(n);
          Object localObject = a.a(localView);
          if ((localObject != null) && (h != null))
          {
            localObject = h.a;
            i2 = localView.getLeft();
            i3 = localView.getTop();
            if ((i2 != ((View)localObject).getLeft()) || (i3 != ((View)localObject).getTop())) {
              ((View)localObject).layout(i2, i3, ((View)localObject).getWidth() + i2, ((View)localObject).getHeight() + i3);
            }
          }
          n += 1;
        }
      }
      RecyclerView.f(a);
      a.b(false);
      n = m;
      i2 = j;
      i3 = i;
      i1 = k;
      if (localp == null) {
        break label849;
      }
      n = m;
      i2 = j;
      i3 = i;
      i1 = k;
      if (localp.b()) {
        break label849;
      }
      n = m;
      i2 = j;
      i3 = i;
      i1 = k;
      if (!localp.c()) {
        break label849;
      }
      n = a.t.f();
      if (n != 0) {
        break label781;
      }
      localp.a();
      n = j;
      j = i;
      if (!RecyclerView.g(a).isEmpty()) {
        a.invalidate();
      }
      if (ViewCompat.getOverScrollMode(a) != 2) {
        RecyclerView.a(a, i4, i5);
      }
      if ((j != 0) || (m != 0))
      {
        i1 = (int)localScrollerCompat.getCurrVelocity();
        if (j == i6) {
          break label921;
        }
        if (j >= 0) {
          break label866;
        }
        i = -i1;
      }
    }
    label532:
    label551:
    label678:
    label704:
    label724:
    label781:
    label849:
    label866:
    label899:
    label904:
    label909:
    label921:
    for (int i2 = i;; i2 = 0)
    {
      if (m != i7) {
        if (m < 0) {
          i = -i1;
        }
      }
      for (;;)
      {
        if (ViewCompat.getOverScrollMode(a) != 2) {
          a.c(i2, i);
        }
        if (((i2 != 0) || (j == i6) || (localScrollerCompat.getFinalX() == 0)) && ((i != 0) || (m == i7) || (localScrollerCompat.getFinalY() == 0))) {
          localScrollerCompat.abortAnimation();
        }
        if ((k != 0) || (n != 0)) {
          a.e(k, n);
        }
        if (!RecyclerView.h(a)) {
          a.invalidate();
        }
        if ((i5 != 0) && (RecyclerView.a(a).d()) && (n == i5))
        {
          i = 1;
          if ((i4 == 0) || (!RecyclerView.a(a).c()) || (k != i4)) {
            break label899;
          }
          j = 1;
          if (((i4 != 0) || (i5 != 0)) && (j == 0) && (i == 0)) {
            break label904;
          }
          i = 1;
          if ((!localScrollerCompat.isFinished()) && (i != 0)) {
            break label909;
          }
          a.setScrollState(0);
        }
        for (;;)
        {
          if (localp != null)
          {
            if (localp.b()) {
              RecyclerView.p.a(localp, 0, 0);
            }
            if (!g) {
              localp.a();
            }
          }
          d();
          return;
          if (localp.d() >= n)
          {
            localp.a(n - 1);
            RecyclerView.p.a(localp, i4 - i, i5 - m);
            n = j;
            j = i;
            break;
          }
          RecyclerView.p.a(localp, i4 - i, i5 - m);
          i1 = k;
          i3 = i;
          i2 = j;
          n = m;
          j = i3;
          m = n;
          n = i2;
          k = i1;
          break;
          if (j > 0)
          {
            i = i1;
            break label532;
          }
          i = 0;
          break label532;
          i = i1;
          if (m > 0) {
            break label551;
          }
          i = 0;
          break label551;
          i = 0;
          break label678;
          j = 0;
          break label704;
          i = 0;
          break label724;
          a();
        }
        i = 0;
      }
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.s
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */