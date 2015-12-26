package flyme.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import are;
import arl;
import arr;
import ars;
import art;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

public class StaggeredGridLayoutManager
  extends RecyclerView.h
{
  private boolean A;
  private final Runnable B;
  arl a;
  arl b;
  boolean c;
  int d;
  int e;
  LazySpanLookup f;
  private int g;
  private b[] k;
  private int l;
  private int m;
  private are n;
  private boolean o;
  private BitSet p;
  private int q;
  private boolean r;
  private boolean s;
  private SavedState t;
  private int u;
  private int v;
  private int w;
  private final Rect x;
  private final a y;
  private boolean z;
  
  private boolean B()
  {
    if ((r() == 0) || (q == 0) || (!m())) {
      return false;
    }
    int j;
    if (c) {
      j = E();
    }
    for (int i = F(); (j == 0) && (e() != null); i = E())
    {
      f.a();
      A();
      l();
      return true;
      j = F();
    }
    if (!z) {
      return false;
    }
    if (c) {}
    StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem localFullSpanItem1;
    for (int i1 = -1;; i1 = 1)
    {
      localFullSpanItem1 = f.a(j, i + 1, i1, true);
      if (localFullSpanItem1 != null) {
        break;
      }
      z = false;
      f.a(i + 1);
      return false;
    }
    StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem localFullSpanItem2 = f.a(j, a, i1 * -1, true);
    if (localFullSpanItem2 == null) {
      f.a(a);
    }
    for (;;)
    {
      A();
      l();
      return true;
      f.a(a + 1);
    }
  }
  
  private void C()
  {
    if (a == null)
    {
      a = arl.a(this, l);
      b = arl.a(this, 1 - l);
      n = new are();
    }
  }
  
  private void D()
  {
    boolean bool = true;
    if ((l == 1) || (!f()))
    {
      c = o;
      return;
    }
    if (!o) {}
    for (;;)
    {
      c = bool;
      return;
      bool = false;
    }
  }
  
  private int E()
  {
    int i = r();
    if (i == 0) {
      return 0;
    }
    return d(e(i - 1));
  }
  
  private int F()
  {
    if (r() == 0) {
      return 0;
    }
    return d(e(0));
  }
  
  private int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt2 == 0) && (paramInt3 == 0)) {}
    int i;
    do
    {
      return paramInt1;
      i = View.MeasureSpec.getMode(paramInt1);
    } while ((i != Integer.MIN_VALUE) && (i != 1073741824));
    return View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt1) - paramInt2 - paramInt3, i);
  }
  
  private int a(RecyclerView.m paramm, are paramare, RecyclerView.q paramq)
  {
    p.set(0, g, true);
    int i1;
    int i2;
    label58:
    int i;
    label61:
    View localView;
    LayoutParams localLayoutParams;
    int i5;
    int i3;
    label123:
    b localb;
    label144:
    label155:
    label176:
    label208:
    int i4;
    StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem localFullSpanItem;
    int j;
    if (d == 1)
    {
      i1 = f + a;
      c(d, i1);
      if (!c) {
        break label407;
      }
      i2 = a.d();
      i = 0;
      if ((!paramare.a(paramq)) || (p.isEmpty())) {
        break label705;
      }
      localView = paramare.a(paramm);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      i5 = localLayoutParams.c();
      i = f.c(i5);
      if (i != -1) {
        break label419;
      }
      i3 = 1;
      if (i3 == 0) {
        break label435;
      }
      if (!f) {
        break label425;
      }
      localb = k[0];
      f.a(i5, localb);
      e = localb;
      if (d != 1) {
        break label447;
      }
      b(localView);
      a(localView, localLayoutParams);
      if (d != 1) {
        break label469;
      }
      if (!f) {
        break label457;
      }
      i = n(i2);
      i4 = i + a.c(localView);
      if ((i3 == 0) || (!f)) {
        break label796;
      }
      localFullSpanItem = j(i);
      b = -1;
      a = i5;
      f.a(localFullSpanItem);
      j = i;
    }
    for (;;)
    {
      if ((f) && (c == -1))
      {
        if (i3 != 0) {
          z = true;
        }
      }
      else
      {
        label295:
        a(localView, localLayoutParams, paramare);
        if (!f) {
          break label639;
        }
        i = b.c();
        label321:
        i3 = i + b.c(localView);
        if (l != 1) {
          break label670;
        }
        b(localView, i, j, i3, i4);
        label357:
        if (!f) {
          break label687;
        }
        c(n.d, i1);
      }
      for (;;)
      {
        a(paramm, n);
        i = 1;
        break label61;
        i1 = e - a;
        break;
        label407:
        i2 = a.c();
        break label58;
        label419:
        i3 = 0;
        break label123;
        label425:
        localb = a(paramare);
        break label144;
        label435:
        localb = k[i];
        break label155;
        label447:
        b(localView, 0);
        break label176;
        label457:
        i = localb.b(i2);
        break label208;
        label469:
        if (f) {}
        for (i = m(i2);; i = localb.a(i2))
        {
          j = i - a.c(localView);
          if ((i3 != 0) && (f))
          {
            localFullSpanItem = k(i);
            b = 1;
            a = i5;
            f.a(localFullSpanItem);
          }
          i4 = i;
          break;
        }
        if (d == 1) {
          if (!i()) {
            i = 1;
          }
        }
        for (;;)
        {
          if (i == 0) {
            break label637;
          }
          localFullSpanItem = f.f(i5);
          if (localFullSpanItem != null) {
            d = true;
          }
          z = true;
          break;
          i = 0;
          continue;
          if (!j()) {
            i = 1;
          } else {
            i = 0;
          }
        }
        label637:
        break label295;
        label639:
        i = d;
        i3 = m;
        i = b.c() + i * i3;
        break label321;
        label670:
        b(localView, j, i, i4, i3);
        break label357;
        label687:
        a(localb, n.d, i1);
      }
      label705:
      if (i == 0) {
        a(paramm, n);
      }
      if (n.d == -1) {
        i = m(a.c());
      }
      for (i = a.c() - i; i > 0; i = n(a.d()) - a.d()) {
        return Math.min(a, i);
      }
      return 0;
      label796:
      j = i;
    }
  }
  
  private int a(RecyclerView.q paramq)
  {
    boolean bool2 = false;
    if (r() == 0) {
      return 0;
    }
    C();
    arl localarl = a;
    if (!A) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = a(bool1, true);
      bool1 = bool2;
      if (!A) {
        bool1 = true;
      }
      return arr.a(paramq, localarl, localView, b(bool1, true), this, A, c);
    }
  }
  
  private b a(are paramare)
  {
    Object localObject2 = null;
    Object localObject1 = null;
    int j = -1;
    int i;
    int i1;
    int i4;
    int i3;
    int i2;
    if (p(d))
    {
      i = g - 1;
      i1 = -1;
      if (d != 1) {
        break label122;
      }
      i4 = a.c();
      i3 = Integer.MAX_VALUE;
      i2 = i;
      paramare = (are)localObject1;
      i = i3;
      label60:
      localObject1 = paramare;
      if (i2 == i1) {
        break label193;
      }
      localObject1 = k[i2];
      i3 = ((b)localObject1).b(i4);
      if (i3 >= i) {
        break label199;
      }
      paramare = (are)localObject1;
      i = i3;
    }
    label122:
    label193:
    label196:
    label199:
    for (;;)
    {
      i2 += j;
      break label60;
      i1 = g;
      i = 0;
      j = 1;
      break;
      i4 = a.d();
      i3 = Integer.MIN_VALUE;
      i2 = i;
      paramare = (are)localObject2;
      i = i3;
      localObject1 = paramare;
      if (i2 != i1)
      {
        localObject1 = k[i2];
        i3 = ((b)localObject1).a(i4);
        if (i3 <= i) {
          break label196;
        }
        paramare = (are)localObject1;
        i = i3;
      }
      for (;;)
      {
        i2 += j;
        break;
        return (b)localObject1;
      }
    }
  }
  
  private void a(int paramInt, RecyclerView.q paramq)
  {
    int i = 0;
    n.a = 0;
    n.b = paramInt;
    boolean bool1;
    if (o())
    {
      int j = paramq.c();
      if (j != -1)
      {
        boolean bool2 = c;
        if (j < paramInt)
        {
          bool1 = true;
          if (bool2 != bool1) {
            break label113;
          }
          paramInt = a.f();
        }
      }
    }
    for (;;)
    {
      if (n())
      {
        n.e = (a.c() - i);
        n.f = (paramInt + a.d());
        return;
        bool1 = false;
        break;
        label113:
        i = a.f();
        paramInt = 0;
        continue;
      }
      n.f = (paramInt + a.e());
      n.e = (-i);
      return;
      paramInt = 0;
    }
  }
  
  private void a(View paramView, LayoutParams paramLayoutParams)
  {
    if (f)
    {
      if (l == 1)
      {
        b(paramView, u, b(height, w));
        return;
      }
      b(paramView, b(width, v), u);
      return;
    }
    if (l == 1)
    {
      b(paramView, v, b(height, w));
      return;
    }
    b(paramView, b(width, v), w);
  }
  
  private void a(View paramView, LayoutParams paramLayoutParams, are paramare)
  {
    if (d == 1)
    {
      if (f)
      {
        o(paramView);
        return;
      }
      e.b(paramView);
      return;
    }
    if (f)
    {
      p(paramView);
      return;
    }
    e.a(paramView);
  }
  
  private void a(RecyclerView.m paramm, int paramInt)
  {
    for (;;)
    {
      View localView;
      LayoutParams localLayoutParams;
      if (r() > 0)
      {
        localView = e(0);
        if (a.b(localView) <= paramInt)
        {
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          if (!f) {
            break label105;
          }
          i = 0;
          if (i >= g) {
            break label79;
          }
          if (b.a(k[i]).size() != 1) {
            break label72;
          }
        }
      }
      label72:
      label79:
      label105:
      while (b.a(e).size() == 1)
      {
        for (;;)
        {
          return;
          i += 1;
        }
        int i = 0;
        while (i < g)
        {
          k[i].h();
          i += 1;
        }
      }
      e.h();
      a(localView, paramm);
    }
  }
  
  private void a(RecyclerView.m paramm, are paramare)
  {
    if (a == 0)
    {
      if (d == -1)
      {
        b(paramm, f);
        return;
      }
      a(paramm, e);
      return;
    }
    if (d == -1)
    {
      i = e - l(e);
      if (i < 0) {}
      for (i = f;; i = f - Math.min(i, a))
      {
        b(paramm, i);
        return;
      }
    }
    int i = o(f) - f;
    if (i < 0) {}
    int j;
    for (i = e;; i = Math.min(i, a) + j)
    {
      a(paramm, i);
      return;
      j = e;
    }
  }
  
  private void a(RecyclerView.m paramm, RecyclerView.q paramq, boolean paramBoolean)
  {
    int i = n(a.d());
    i = a.d() - i;
    if (i > 0)
    {
      i -= -c(-i, paramm, paramq);
      if ((paramBoolean) && (i > 0)) {
        a.a(i);
      }
    }
  }
  
  private void a(a parama)
  {
    if (t.c > 0) {
      if (t.c == g)
      {
        int j = 0;
        if (j < g)
        {
          k[j].e();
          int i1 = t.d[j];
          int i = i1;
          if (i1 != Integer.MIN_VALUE) {
            if (!t.i) {
              break label102;
            }
          }
          label102:
          for (i = i1 + a.d();; i = i1 + a.c())
          {
            k[j].c(i);
            j += 1;
            break;
          }
        }
      }
      else
      {
        t.a();
        t.a = t.b;
      }
    }
    s = t.j;
    a(t.h);
    D();
    if (t.a != -1) {
      d = t.a;
    }
    for (c = t.i;; c = c)
    {
      if (t.e > 1)
      {
        f.a = t.f;
        f.b = t.g;
      }
      return;
    }
  }
  
  private void a(b paramb, int paramInt1, int paramInt2)
  {
    int i = paramb.i();
    if (paramInt1 == -1) {
      if (i + paramb.b() <= paramInt2) {
        p.set(d, false);
      }
    }
    while (paramb.d() - i < paramInt2) {
      return;
    }
    p.set(d, false);
  }
  
  private boolean a(b paramb)
  {
    if (c)
    {
      if (paramb.d() >= a.d()) {}
    }
    else {
      while (paramb.b() > a.c()) {
        return true;
      }
    }
    return false;
  }
  
  private int b(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      return paramInt2;
    }
    return View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
  }
  
  private void b(View paramView, int paramInt1, int paramInt2)
  {
    a(paramView, x);
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    paramView.measure(a(paramInt1, leftMargin + x.left, rightMargin + x.right), a(paramInt2, topMargin + x.top, bottomMargin + x.bottom));
  }
  
  private void b(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    a(paramView, paramInt1 + leftMargin, paramInt2 + topMargin, paramInt3 - rightMargin, paramInt4 - bottomMargin);
  }
  
  private void b(RecyclerView.m paramm, int paramInt)
  {
    int i = r() - 1;
    for (;;)
    {
      View localView;
      LayoutParams localLayoutParams;
      if (i >= 0)
      {
        localView = e(i);
        if (a.a(localView) >= paramInt)
        {
          localLayoutParams = (LayoutParams)localView.getLayoutParams();
          if (!f) {
            break label119;
          }
          j = 0;
          if (j >= g) {
            break label88;
          }
          if (b.a(k[j]).size() != 1) {
            break label79;
          }
        }
      }
      label79:
      label88:
      label119:
      while (b.a(e).size() == 1)
      {
        for (;;)
        {
          return;
          j += 1;
        }
        int j = 0;
        while (j < g)
        {
          k[j].g();
          j += 1;
        }
      }
      e.g();
      a(localView, paramm);
      i -= 1;
    }
  }
  
  private void b(RecyclerView.m paramm, RecyclerView.q paramq, boolean paramBoolean)
  {
    int i = m(a.c()) - a.c();
    if (i > 0)
    {
      i -= c(i, paramm, paramq);
      if ((paramBoolean) && (i > 0)) {
        a.a(-i);
      }
    }
  }
  
  private void c(int paramInt1, int paramInt2)
  {
    int i = 0;
    if (i < g)
    {
      if (b.a(k[i]).isEmpty()) {}
      for (;;)
      {
        i += 1;
        break;
        a(k[i], paramInt1, paramInt2);
      }
    }
  }
  
  private boolean c(RecyclerView.q paramq, a parama)
  {
    if (r) {}
    for (int i = s(paramq.f());; i = r(paramq.f()))
    {
      a = i;
      b = Integer.MIN_VALUE;
      return true;
    }
  }
  
  private int h(RecyclerView.q paramq)
  {
    boolean bool2 = false;
    if (r() == 0) {
      return 0;
    }
    C();
    arl localarl = a;
    if (!A) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = a(bool1, true);
      bool1 = bool2;
      if (!A) {
        bool1 = true;
      }
      return arr.a(paramq, localarl, localView, b(bool1, true), this, A);
    }
  }
  
  private int i(RecyclerView.q paramq)
  {
    boolean bool2 = false;
    if (r() == 0) {
      return 0;
    }
    C();
    arl localarl = a;
    if (!A) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = a(bool1, true);
      bool1 = bool2;
      if (!A) {
        bool1 = true;
      }
      return arr.b(paramq, localarl, localView, b(bool1, true), this, A);
    }
  }
  
  private void i(int paramInt)
  {
    int i = 1;
    n.d = paramInt;
    are localare = n;
    boolean bool2 = c;
    boolean bool1;
    if (paramInt == -1)
    {
      bool1 = true;
      if (bool2 != bool1) {
        break label49;
      }
    }
    label49:
    for (paramInt = i;; paramInt = -1)
    {
      c = paramInt;
      return;
      bool1 = false;
      break;
    }
  }
  
  private StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem j(int paramInt)
  {
    StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem localFullSpanItem = new StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem();
    c = new int[g];
    int i = 0;
    while (i < g)
    {
      c[i] = (paramInt - k[i].b(paramInt));
      i += 1;
    }
    return localFullSpanItem;
  }
  
  private StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem k(int paramInt)
  {
    StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem localFullSpanItem = new StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem();
    c = new int[g];
    int i = 0;
    while (i < g)
    {
      c[i] = (k[i].a(paramInt) - paramInt);
      i += 1;
    }
    return localFullSpanItem;
  }
  
  private int l(int paramInt)
  {
    int j = k[0].a(paramInt);
    int i = 1;
    while (i < g)
    {
      int i2 = k[i].a(paramInt);
      int i1 = j;
      if (i2 > j) {
        i1 = i2;
      }
      i += 1;
      j = i1;
    }
    return j;
  }
  
  private int m(int paramInt)
  {
    int j = k[0].a(paramInt);
    int i = 1;
    while (i < g)
    {
      int i2 = k[i].a(paramInt);
      int i1 = j;
      if (i2 < j) {
        i1 = i2;
      }
      i += 1;
      j = i1;
    }
    return j;
  }
  
  private int n(int paramInt)
  {
    int j = k[0].b(paramInt);
    int i = 1;
    while (i < g)
    {
      int i2 = k[i].b(paramInt);
      int i1 = j;
      if (i2 > j) {
        i1 = i2;
      }
      i += 1;
      j = i1;
    }
    return j;
  }
  
  private int o(int paramInt)
  {
    int j = k[0].b(paramInt);
    int i = 1;
    while (i < g)
    {
      int i2 = k[i].b(paramInt);
      int i1 = j;
      if (i2 < j) {
        i1 = i2;
      }
      i += 1;
      j = i1;
    }
    return j;
  }
  
  private void o(View paramView)
  {
    int i = g - 1;
    while (i >= 0)
    {
      k[i].b(paramView);
      i -= 1;
    }
  }
  
  private void p(View paramView)
  {
    int i = g - 1;
    while (i >= 0)
    {
      k[i].a(paramView);
      i -= 1;
    }
  }
  
  private boolean p(int paramInt)
  {
    int i;
    if (l == 0) {
      if (paramInt == -1)
      {
        i = 1;
        if (i == c) {
          break label29;
        }
      }
    }
    label29:
    label63:
    label66:
    for (;;)
    {
      return true;
      i = 0;
      break;
      return false;
      if (paramInt == -1)
      {
        i = 1;
        if (i != c) {
          break label63;
        }
      }
      for (i = 1;; i = 0)
      {
        if (i == f()) {
          break label66;
        }
        return false;
        i = 0;
        break;
      }
    }
  }
  
  private int q(int paramInt)
  {
    int i = -1;
    if (r() == 0)
    {
      if (c) {
        return 1;
      }
      return -1;
    }
    int j;
    if (paramInt < F())
    {
      j = 1;
      if (j == c) {
        break label47;
      }
    }
    label47:
    for (paramInt = i;; paramInt = 1)
    {
      return paramInt;
      j = 0;
      break;
    }
  }
  
  private int r(int paramInt)
  {
    int j = r();
    int i = 0;
    while (i < j)
    {
      int i1 = d(e(i));
      if ((i1 >= 0) && (i1 < paramInt)) {
        return i1;
      }
      i += 1;
    }
    return 0;
  }
  
  private int s(int paramInt)
  {
    int i = r() - 1;
    while (i >= 0)
    {
      int j = d(e(i));
      if ((j >= 0) && (j < paramInt)) {
        return j;
      }
      i -= 1;
    }
    return 0;
  }
  
  public int a(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    return c(paramInt, paramm, paramq);
  }
  
  View a(boolean paramBoolean1, boolean paramBoolean2)
  {
    C();
    int j = a.c();
    int i1 = a.d();
    int i2 = r();
    int i = 0;
    Object localObject1 = null;
    if (i < i2)
    {
      View localView = e(i);
      int i3 = a.a(localView);
      Object localObject2 = localObject1;
      if (a.b(localView) > j)
      {
        if (i3 < i1) {
          break label97;
        }
        localObject2 = localObject1;
      }
      for (;;)
      {
        i += 1;
        localObject1 = localObject2;
        break;
        label97:
        if ((i3 >= j) || (!paramBoolean1)) {
          return localView;
        }
        localObject2 = localObject1;
        if (paramBoolean2)
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = localView;
          }
        }
      }
    }
    return (View)localObject1;
  }
  
  public RecyclerView.LayoutParams a()
  {
    return new LayoutParams(-2, -2);
  }
  
  public RecyclerView.LayoutParams a(Context paramContext, AttributeSet paramAttributeSet)
  {
    return new LayoutParams(paramContext, paramAttributeSet);
  }
  
  public RecyclerView.LayoutParams a(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
      return new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    }
    return new LayoutParams(paramLayoutParams);
  }
  
  public void a(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof SavedState))
    {
      t = ((SavedState)paramParcelable);
      l();
    }
  }
  
  public void a(AccessibilityEvent paramAccessibilityEvent)
  {
    super.a(paramAccessibilityEvent);
    View localView1;
    View localView2;
    if (r() > 0)
    {
      paramAccessibilityEvent = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
      localView1 = a(false, true);
      localView2 = b(false, true);
      if ((localView1 != null) && (localView2 != null)) {}
    }
    else
    {
      return;
    }
    int i = d(localView1);
    int j = d(localView2);
    if (i < j)
    {
      paramAccessibilityEvent.setFromIndex(i);
      paramAccessibilityEvent.setToIndex(j);
      return;
    }
    paramAccessibilityEvent.setFromIndex(j);
    paramAccessibilityEvent.setToIndex(i);
  }
  
  public void a(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    int j = 0;
    C();
    a locala = y;
    locala.a();
    if (t != null) {
      a(locala);
    }
    for (;;)
    {
      a(paramq, locala);
      if ((t == null) && ((c != r) || (f() != s)))
      {
        f.a();
        d = true;
      }
      if ((r() <= 0) || ((t != null) && (t.c >= 1))) {
        break label215;
      }
      if (!d) {
        break;
      }
      i = 0;
      while (i < g)
      {
        k[i].e();
        if (b != Integer.MIN_VALUE) {
          k[i].c(b);
        }
        i += 1;
      }
      D();
      c = c;
    }
    int i = 0;
    while (i < g)
    {
      k[i].a(c, b);
      i += 1;
    }
    label215:
    a(paramm);
    z = false;
    g();
    a(a, paramq);
    if (c)
    {
      i(-1);
      a(paramm, n, paramq);
      i(1);
      n.b = (a + n.c);
      a(paramm, n, paramq);
      if (r() > 0)
      {
        if (!c) {
          break label483;
        }
        a(paramm, paramq, true);
        b(paramm, paramq, false);
      }
    }
    for (;;)
    {
      if (!paramq.a())
      {
        i = j;
        if (q != 0)
        {
          i = j;
          if (r() > 0) {
            if (!z)
            {
              i = j;
              if (e() == null) {}
            }
            else
            {
              i = 1;
            }
          }
        }
        if (i != 0)
        {
          b(B);
          a(B);
        }
        d = -1;
        e = Integer.MIN_VALUE;
      }
      r = c;
      s = f();
      t = null;
      return;
      i(1);
      a(paramm, n, paramq);
      i(-1);
      n.b = (a + n.c);
      a(paramm, n, paramq);
      break;
      label483:
      b(paramm, paramq, true);
      a(paramm, paramq, false);
    }
  }
  
  void a(RecyclerView.q paramq, a parama)
  {
    if (b(paramq, parama)) {}
    while (c(paramq, parama)) {
      return;
    }
    parama.b();
    a = 0;
  }
  
  public void a(RecyclerView paramRecyclerView, RecyclerView.m paramm)
  {
    b(B);
    int i = 0;
    while (i < g)
    {
      k[i].e();
      i += 1;
    }
  }
  
  public void a(String paramString)
  {
    if (t == null) {
      super.a(paramString);
    }
  }
  
  public void a(boolean paramBoolean)
  {
    a(null);
    if ((t != null) && (t.h != paramBoolean)) {
      t.h = paramBoolean;
    }
    o = paramBoolean;
    l();
  }
  
  public boolean a(RecyclerView.LayoutParams paramLayoutParams)
  {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  public int b(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    return c(paramInt, paramm, paramq);
  }
  
  public int b(RecyclerView.q paramq)
  {
    return a(paramq);
  }
  
  public Parcelable b()
  {
    if (t != null) {
      return new SavedState(t);
    }
    SavedState localSavedState = new SavedState();
    h = o;
    i = r;
    j = s;
    int i;
    label130:
    int j;
    label167:
    int i1;
    if ((f != null) && (f.a != null))
    {
      f = f.a;
      e = f.length;
      g = f.b;
      if (r() <= 0) {
        break label277;
      }
      C();
      if (!r) {
        break label236;
      }
      i = E();
      a = i;
      b = h();
      c = g;
      d = new int[g];
      j = 0;
      if (j >= g) {
        break label295;
      }
      if (!r) {
        break label244;
      }
      i1 = k[j].b(Integer.MIN_VALUE);
      i = i1;
      if (i1 != Integer.MIN_VALUE) {
        i = i1 - a.d();
      }
    }
    for (;;)
    {
      d[j] = i;
      j += 1;
      break label167;
      e = 0;
      break;
      label236:
      i = F();
      break label130;
      label244:
      i1 = k[j].a(Integer.MIN_VALUE);
      i = i1;
      if (i1 != Integer.MIN_VALUE) {
        i = i1 - a.c();
      }
    }
    label277:
    a = -1;
    b = -1;
    c = 0;
    label295:
    return localSavedState;
  }
  
  View b(boolean paramBoolean1, boolean paramBoolean2)
  {
    C();
    int j = a.c();
    int i1 = a.d();
    int i = r() - 1;
    Object localObject1 = null;
    if (i >= 0)
    {
      View localView = e(i);
      int i2 = a.a(localView);
      int i3 = a.b(localView);
      Object localObject2 = localObject1;
      if (i3 > j)
      {
        if (i2 < i1) {
          break label98;
        }
        localObject2 = localObject1;
      }
      for (;;)
      {
        i -= 1;
        localObject1 = localObject2;
        break;
        label98:
        if ((i3 <= i1) || (!paramBoolean1)) {
          return localView;
        }
        localObject2 = localObject1;
        if (paramBoolean2)
        {
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = localView;
          }
        }
      }
    }
    return (View)localObject1;
  }
  
  public void b(int paramInt)
  {
    if ((t != null) && (t.a != paramInt)) {
      t.b();
    }
    d = paramInt;
    e = Integer.MIN_VALUE;
    l();
  }
  
  boolean b(RecyclerView.q paramq, a parama)
  {
    boolean bool = false;
    if ((paramq.a()) || (d == -1)) {
      return false;
    }
    if ((d < 0) || (d >= paramq.f()))
    {
      d = -1;
      e = Integer.MIN_VALUE;
      return false;
    }
    if ((t == null) || (t.a == -1) || (t.c < 1))
    {
      paramq = a(d);
      if (paramq != null)
      {
        if (c) {}
        for (int i = E();; i = F())
        {
          a = i;
          if (e == Integer.MIN_VALUE) {
            break label188;
          }
          if (!c) {
            break;
          }
          b = (a.d() - e - a.b(paramq));
          return true;
        }
        b = (a.c() + e - a.a(paramq));
        return true;
        label188:
        if (a.c(paramq) > a.f())
        {
          if (c) {}
          for (i = a.d();; i = a.c())
          {
            b = i;
            return true;
          }
        }
        i = a.a(paramq) - a.c();
        if (i < 0)
        {
          b = (-i);
          return true;
        }
        i = a.d() - a.b(paramq);
        if (i < 0)
        {
          b = i;
          return true;
        }
        b = Integer.MIN_VALUE;
        return true;
      }
      a = d;
      if (e == Integer.MIN_VALUE)
      {
        if (q(a) == 1) {
          bool = true;
        }
        c = bool;
        parama.b();
      }
      for (;;)
      {
        d = true;
        return true;
        parama.a(e);
      }
    }
    b = Integer.MIN_VALUE;
    a = d;
    return true;
  }
  
  int c(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    C();
    int i;
    int j;
    if (paramInt > 0)
    {
      i = 1;
      j = E();
      a(j, paramq);
      i(i);
      n.b = (j + n.c);
      j = Math.abs(paramInt);
      n.a = j;
      i = a(paramm, n, paramq);
      if (j >= i) {
        break label112;
      }
    }
    for (;;)
    {
      a.a(-paramInt);
      r = c;
      return paramInt;
      i = -1;
      j = F();
      break;
      label112:
      if (paramInt < 0) {
        paramInt = -i;
      } else {
        paramInt = i;
      }
    }
  }
  
  public int c(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if (l == 0) {
      return g;
    }
    return super.c(paramm, paramq);
  }
  
  public int c(RecyclerView.q paramq)
  {
    return a(paramq);
  }
  
  public boolean c()
  {
    return l == 0;
  }
  
  public int d(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if (l == 1) {
      return g;
    }
    return super.d(paramm, paramq);
  }
  
  public int d(RecyclerView.q paramq)
  {
    return h(paramq);
  }
  
  public boolean d()
  {
    return l == 1;
  }
  
  public int e(RecyclerView.q paramq)
  {
    return h(paramq);
  }
  
  View e()
  {
    int i1 = r() - 1;
    BitSet localBitSet = new BitSet(g);
    localBitSet.set(0, g, true);
    int j;
    int i;
    if ((l == 1) && (f()))
    {
      j = 1;
      if (!c) {
        break label132;
      }
      i = i1 - 1;
      i1 = -1;
      label61:
      if (i >= i1) {
        break label137;
      }
    }
    int i3;
    View localView;
    LayoutParams localLayoutParams;
    label132:
    label137:
    for (int i2 = 1;; i2 = -1)
    {
      i3 = i;
      if (i3 == i1) {
        break label343;
      }
      localView = e(i3);
      localLayoutParams = (LayoutParams)localView.getLayoutParams();
      if (!localBitSet.get(e.d)) {
        break label156;
      }
      if (!a(e)) {
        break label143;
      }
      return localView;
      j = -1;
      break;
      i = 0;
      break label61;
    }
    label143:
    localBitSet.clear(e.d);
    label156:
    if (f) {}
    label275:
    label337:
    label341:
    label343:
    label345:
    label348:
    for (;;)
    {
      i3 += i2;
      break;
      if (i3 + i2 != i1)
      {
        Object localObject = e(i3 + i2);
        int i4;
        if (c)
        {
          i = a.b(localView);
          i4 = a.b((View)localObject);
          if (i < i4) {
            return localView;
          }
          if (i != i4) {
            break label345;
          }
          i = 1;
        }
        for (;;)
        {
          if (i == 0) {
            break label348;
          }
          localObject = (LayoutParams)((View)localObject).getLayoutParams();
          if (e.d - e.d < 0)
          {
            i = 1;
            if (j >= 0) {
              break label337;
            }
          }
          for (i4 = 1;; i4 = 0)
          {
            if (i == i4) {
              break label341;
            }
            return localView;
            i = a.a(localView);
            i4 = a.a((View)localObject);
            if (i > i4) {
              return localView;
            }
            if (i != i4) {
              break label345;
            }
            i = 1;
            break;
            i = 0;
            break label275;
          }
          break;
          return null;
          i = 0;
        }
      }
    }
  }
  
  public void e(RecyclerView paramRecyclerView)
  {
    f.a();
    l();
  }
  
  public int f(RecyclerView.q paramq)
  {
    return i(paramq);
  }
  
  public void f(int paramInt)
  {
    super.f(paramInt);
    int i = 0;
    while (i < g)
    {
      k[i].d(paramInt);
      i += 1;
    }
  }
  
  boolean f()
  {
    return p() == 1;
  }
  
  public int g(RecyclerView.q paramq)
  {
    return i(paramq);
  }
  
  void g()
  {
    m = (b.f() / g);
    u = View.MeasureSpec.makeMeasureSpec(b.f(), 1073741824);
    if (l == 1)
    {
      v = View.MeasureSpec.makeMeasureSpec(m, 1073741824);
      w = View.MeasureSpec.makeMeasureSpec(0, 0);
      return;
    }
    w = View.MeasureSpec.makeMeasureSpec(m, 1073741824);
    v = View.MeasureSpec.makeMeasureSpec(0, 0);
  }
  
  public void g(int paramInt)
  {
    super.g(paramInt);
    int i = 0;
    while (i < g)
    {
      k[i].d(paramInt);
      i += 1;
    }
  }
  
  int h()
  {
    if (c) {}
    for (View localView = b(true, true); localView == null; localView = a(true, true)) {
      return -1;
    }
    return d(localView);
  }
  
  public void h(int paramInt)
  {
    if (paramInt == 0) {
      B();
    }
  }
  
  boolean i()
  {
    boolean bool2 = true;
    int j = k[0].b(Integer.MIN_VALUE);
    int i = 1;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < g)
      {
        if (k[i].b(Integer.MIN_VALUE) != j) {
          bool1 = false;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  boolean j()
  {
    boolean bool2 = true;
    int j = k[0].a(Integer.MIN_VALUE);
    int i = 1;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < g)
      {
        if (k[i].a(Integer.MIN_VALUE) != j) {
          bool1 = false;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  public boolean k()
  {
    return t == null;
  }
  
  public static class LayoutParams
    extends RecyclerView.LayoutParams
  {
    StaggeredGridLayoutManager.b e;
    boolean f;
    
    public LayoutParams(int paramInt1, int paramInt2)
    {
      super(paramInt2);
    }
    
    public LayoutParams(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public LayoutParams(ViewGroup.LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams paramMarginLayoutParams)
    {
      super();
    }
  }
  
  public static class LazySpanLookup
  {
    int[] a;
    List<FullSpanItem> b;
    
    private int g(int paramInt)
    {
      if (b == null) {
        return -1;
      }
      FullSpanItem localFullSpanItem = f(paramInt);
      if (localFullSpanItem != null) {
        b.remove(localFullSpanItem);
      }
      int j = b.size();
      int i = 0;
      if (i < j) {
        if (b.get(i)).a < paramInt) {}
      }
      for (;;)
      {
        if (i != -1)
        {
          localFullSpanItem = (FullSpanItem)b.get(i);
          b.remove(i);
          return a;
          i += 1;
          break;
        }
        return -1;
        i = -1;
      }
    }
    
    int a(int paramInt)
    {
      if (b != null)
      {
        int i = b.size() - 1;
        while (i >= 0)
        {
          if (b.get(i)).a >= paramInt) {
            b.remove(i);
          }
          i -= 1;
        }
      }
      return b(paramInt);
    }
    
    public FullSpanItem a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      Object localObject;
      if (b == null)
      {
        localObject = null;
        return (FullSpanItem)localObject;
      }
      int j = b.size();
      int i = 0;
      for (;;)
      {
        if (i >= j) {
          break label117;
        }
        FullSpanItem localFullSpanItem = (FullSpanItem)b.get(i);
        if (a >= paramInt2) {
          return null;
        }
        if (a >= paramInt1)
        {
          localObject = localFullSpanItem;
          if (paramInt3 == 0) {
            break;
          }
          localObject = localFullSpanItem;
          if (b == paramInt3) {
            break;
          }
          if (paramBoolean)
          {
            localObject = localFullSpanItem;
            if (d) {
              break;
            }
          }
        }
        i += 1;
      }
      label117:
      return null;
    }
    
    void a()
    {
      if (a != null) {
        Arrays.fill(a, -1);
      }
      b = null;
    }
    
    void a(int paramInt, StaggeredGridLayoutManager.b paramb)
    {
      e(paramInt);
      a[paramInt] = d;
    }
    
    public void a(FullSpanItem paramFullSpanItem)
    {
      if (b == null) {
        b = new ArrayList();
      }
      int j = b.size();
      int i = 0;
      while (i < j)
      {
        FullSpanItem localFullSpanItem = (FullSpanItem)b.get(i);
        if (a == a) {
          b.remove(i);
        }
        if (a >= a)
        {
          b.add(i, paramFullSpanItem);
          return;
        }
        i += 1;
      }
      b.add(paramFullSpanItem);
    }
    
    int b(int paramInt)
    {
      if (a == null) {}
      while (paramInt >= a.length) {
        return -1;
      }
      int i = g(paramInt);
      if (i == -1)
      {
        Arrays.fill(a, paramInt, a.length, -1);
        return a.length;
      }
      Arrays.fill(a, paramInt, i + 1, -1);
      return i + 1;
    }
    
    int c(int paramInt)
    {
      if ((a == null) || (paramInt >= a.length)) {
        return -1;
      }
      return a[paramInt];
    }
    
    int d(int paramInt)
    {
      int i = a.length;
      while (i <= paramInt) {
        i *= 2;
      }
      return i;
    }
    
    void e(int paramInt)
    {
      if (a == null)
      {
        a = new int[Math.max(paramInt, 10) + 1];
        Arrays.fill(a, -1);
      }
      while (paramInt < a.length) {
        return;
      }
      int[] arrayOfInt = a;
      a = new int[d(paramInt)];
      System.arraycopy(arrayOfInt, 0, a, 0, arrayOfInt.length);
      Arrays.fill(a, arrayOfInt.length, a.length, -1);
    }
    
    public FullSpanItem f(int paramInt)
    {
      Object localObject;
      if (b == null)
      {
        localObject = null;
        return (FullSpanItem)localObject;
      }
      int i = b.size() - 1;
      for (;;)
      {
        if (i < 0) {
          break label61;
        }
        FullSpanItem localFullSpanItem = (FullSpanItem)b.get(i);
        localObject = localFullSpanItem;
        if (a == paramInt) {
          break;
        }
        i -= 1;
      }
      label61:
      return null;
    }
    
    public static class FullSpanItem
      implements Parcelable
    {
      public static final Parcelable.Creator<FullSpanItem> CREATOR = new ars();
      int a;
      int b;
      int[] c;
      boolean d;
      
      public FullSpanItem() {}
      
      public FullSpanItem(Parcel paramParcel)
      {
        a = paramParcel.readInt();
        b = paramParcel.readInt();
        if (paramParcel.readInt() == 1) {}
        for (;;)
        {
          d = bool;
          int i = paramParcel.readInt();
          if (i > 0)
          {
            c = new int[i];
            paramParcel.readIntArray(c);
          }
          return;
          bool = false;
        }
      }
      
      int a(int paramInt)
      {
        if (c == null) {
          return 0;
        }
        return c[paramInt];
      }
      
      public int describeContents()
      {
        return 0;
      }
      
      public String toString()
      {
        return "FullSpanItem{mPosition=" + a + ", mGapDir=" + b + ", mHasUnwantedGapAfter=" + d + ", mGapPerSpan=" + Arrays.toString(c) + '}';
      }
      
      public void writeToParcel(Parcel paramParcel, int paramInt)
      {
        paramParcel.writeInt(a);
        paramParcel.writeInt(b);
        if (d) {}
        for (paramInt = 1;; paramInt = 0)
        {
          paramParcel.writeInt(paramInt);
          if ((c == null) || (c.length <= 0)) {
            break;
          }
          paramParcel.writeInt(c.length);
          paramParcel.writeIntArray(c);
          return;
        }
        paramParcel.writeInt(0);
      }
    }
  }
  
  public static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new art();
    int a;
    int b;
    int c;
    int[] d;
    int e;
    int[] f;
    List<StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem> g;
    boolean h;
    boolean i;
    boolean j;
    
    public SavedState() {}
    
    public SavedState(Parcel paramParcel)
    {
      a = paramParcel.readInt();
      b = paramParcel.readInt();
      c = paramParcel.readInt();
      if (c > 0)
      {
        d = new int[c];
        paramParcel.readIntArray(d);
      }
      e = paramParcel.readInt();
      if (e > 0)
      {
        f = new int[e];
        paramParcel.readIntArray(f);
      }
      if (paramParcel.readInt() == 1)
      {
        bool1 = true;
        h = bool1;
        if (paramParcel.readInt() != 1) {
          break label152;
        }
        bool1 = true;
        label113:
        i = bool1;
        if (paramParcel.readInt() != 1) {
          break label157;
        }
      }
      label152:
      label157:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        j = bool1;
        g = paramParcel.readArrayList(StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem.class.getClassLoader());
        return;
        bool1 = false;
        break;
        bool1 = false;
        break label113;
      }
    }
    
    public SavedState(SavedState paramSavedState)
    {
      c = c;
      a = a;
      b = b;
      d = d;
      e = e;
      f = f;
      h = h;
      i = i;
      j = j;
      g = g;
    }
    
    void a()
    {
      d = null;
      c = 0;
      e = 0;
      f = null;
      g = null;
    }
    
    void b()
    {
      d = null;
      c = 0;
      a = -1;
      b = -1;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      int k = 1;
      paramParcel.writeInt(a);
      paramParcel.writeInt(b);
      paramParcel.writeInt(c);
      if (c > 0) {
        paramParcel.writeIntArray(d);
      }
      paramParcel.writeInt(e);
      if (e > 0) {
        paramParcel.writeIntArray(f);
      }
      if (h)
      {
        paramInt = 1;
        paramParcel.writeInt(paramInt);
        if (!i) {
          break label120;
        }
        paramInt = 1;
        label87:
        paramParcel.writeInt(paramInt);
        if (!j) {
          break label125;
        }
      }
      label120:
      label125:
      for (paramInt = k;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        paramParcel.writeList(g);
        return;
        paramInt = 0;
        break;
        paramInt = 0;
        break label87;
      }
    }
  }
  
  class a
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
  
  class b
  {
    int a;
    int b;
    int c;
    final int d;
    private ArrayList<View> f;
    
    int a(int paramInt)
    {
      if (a != Integer.MIN_VALUE) {
        paramInt = a;
      }
      while (f.size() == 0) {
        return paramInt;
      }
      a();
      return a;
    }
    
    void a()
    {
      Object localObject = (View)f.get(0);
      StaggeredGridLayoutManager.LayoutParams localLayoutParams = c((View)localObject);
      a = e.a.a((View)localObject);
      if (f)
      {
        localObject = e.f.f(localLayoutParams.c());
        if ((localObject != null) && (b == -1)) {
          a -= ((StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem)localObject).a(d);
        }
      }
    }
    
    void a(View paramView)
    {
      StaggeredGridLayoutManager.LayoutParams localLayoutParams = c(paramView);
      e = this;
      f.add(0, paramView);
      a = Integer.MIN_VALUE;
      if (f.size() == 1) {
        b = Integer.MIN_VALUE;
      }
      if ((localLayoutParams.a()) || (localLayoutParams.b())) {
        c += e.a.c(paramView);
      }
    }
    
    void a(boolean paramBoolean, int paramInt)
    {
      int i;
      if (paramBoolean)
      {
        i = b(Integer.MIN_VALUE);
        e();
        if (i != Integer.MIN_VALUE) {
          break label32;
        }
      }
      label32:
      while (((paramBoolean) && (i < e.a.d())) || ((!paramBoolean) && (i > e.a.c())))
      {
        return;
        i = a(Integer.MIN_VALUE);
        break;
      }
      int j = i;
      if (paramInt != Integer.MIN_VALUE) {
        j = i + paramInt;
      }
      b = j;
      a = j;
    }
    
    int b()
    {
      if (a != Integer.MIN_VALUE) {
        return a;
      }
      a();
      return a;
    }
    
    int b(int paramInt)
    {
      if (b != Integer.MIN_VALUE) {
        paramInt = b;
      }
      while (f.size() == 0) {
        return paramInt;
      }
      c();
      return b;
    }
    
    void b(View paramView)
    {
      StaggeredGridLayoutManager.LayoutParams localLayoutParams = c(paramView);
      e = this;
      f.add(paramView);
      b = Integer.MIN_VALUE;
      if (f.size() == 1) {
        a = Integer.MIN_VALUE;
      }
      if ((localLayoutParams.a()) || (localLayoutParams.b())) {
        c += e.a.c(paramView);
      }
    }
    
    StaggeredGridLayoutManager.LayoutParams c(View paramView)
    {
      return (StaggeredGridLayoutManager.LayoutParams)paramView.getLayoutParams();
    }
    
    void c()
    {
      Object localObject = (View)f.get(f.size() - 1);
      StaggeredGridLayoutManager.LayoutParams localLayoutParams = c((View)localObject);
      b = e.a.b((View)localObject);
      if (f)
      {
        localObject = e.f.f(localLayoutParams.c());
        if ((localObject != null) && (b == 1))
        {
          int i = b;
          b = (((StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem)localObject).a(d) + i);
        }
      }
    }
    
    void c(int paramInt)
    {
      a = paramInt;
      b = paramInt;
    }
    
    int d()
    {
      if (b != Integer.MIN_VALUE) {
        return b;
      }
      c();
      return b;
    }
    
    void d(int paramInt)
    {
      if (a != Integer.MIN_VALUE) {
        a += paramInt;
      }
      if (b != Integer.MIN_VALUE) {
        b += paramInt;
      }
    }
    
    void e()
    {
      f.clear();
      f();
      c = 0;
    }
    
    void f()
    {
      a = Integer.MIN_VALUE;
      b = Integer.MIN_VALUE;
    }
    
    void g()
    {
      int i = f.size();
      View localView = (View)f.remove(i - 1);
      StaggeredGridLayoutManager.LayoutParams localLayoutParams = c(localView);
      e = null;
      if ((localLayoutParams.a()) || (localLayoutParams.b())) {
        c -= e.a.c(localView);
      }
      if (i == 1) {
        a = Integer.MIN_VALUE;
      }
      b = Integer.MIN_VALUE;
    }
    
    void h()
    {
      View localView = (View)f.remove(0);
      StaggeredGridLayoutManager.LayoutParams localLayoutParams = c(localView);
      e = null;
      if (f.size() == 0) {
        b = Integer.MIN_VALUE;
      }
      if ((localLayoutParams.a()) || (localLayoutParams.b())) {
        c -= e.a.c(localView);
      }
      a = Integer.MIN_VALUE;
    }
    
    public int i()
    {
      return c;
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.StaggeredGridLayoutManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */