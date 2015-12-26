package flyme.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import arf;
import arl;
import arr;
import java.util.List;

public class LinearLayoutManager
  extends RecyclerView.h
{
  int a;
  arl b;
  boolean c;
  int d;
  int e;
  SavedState f;
  final a g;
  private c k;
  private boolean l;
  private boolean m;
  private boolean n;
  private boolean o;
  private boolean p;
  
  private void B()
  {
    boolean bool = true;
    if ((a == 1) || (!f()))
    {
      c = m;
      return;
    }
    if (!m) {}
    for (;;)
    {
      c = bool;
      return;
      bool = false;
    }
  }
  
  private View C()
  {
    if (c) {}
    for (int i = r() - 1;; i = 0) {
      return e(i);
    }
  }
  
  private View D()
  {
    if (c) {}
    for (int i = 0;; i = r() - 1) {
      return e(i);
    }
  }
  
  private int a(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq, boolean paramBoolean)
  {
    int i = b.d() - paramInt;
    if (i > 0)
    {
      int j = -c(-i, paramm, paramq);
      i = j;
      if (paramBoolean)
      {
        paramInt = b.d() - (paramInt + j);
        i = j;
        if (paramInt > 0)
        {
          b.a(paramInt);
          i = j + paramInt;
        }
      }
      return i;
    }
    return 0;
  }
  
  private View a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (c) {
      return a(r() - 1, -1, paramBoolean1, paramBoolean2);
    }
    return a(0, r(), paramBoolean1, paramBoolean2);
  }
  
  private void a(int paramInt1, int paramInt2, boolean paramBoolean, RecyclerView.q paramq)
  {
    int i = -1;
    int j = 1;
    k.h = a(paramq);
    k.f = paramInt1;
    if (paramInt1 == 1)
    {
      paramq = k;
      h += b.g();
      paramq = D();
      localc = k;
      if (c) {}
      for (paramInt1 = i;; paramInt1 = 1)
      {
        e = paramInt1;
        k.d = (d(paramq) + k.e);
        k.b = b.b(paramq);
        paramInt1 = b.b(paramq) - b.d();
        k.c = paramInt2;
        if (paramBoolean)
        {
          paramq = k;
          c -= paramInt1;
        }
        k.g = paramInt1;
        return;
      }
    }
    paramq = C();
    c localc = k;
    h += b.c();
    localc = k;
    if (c) {}
    for (paramInt1 = j;; paramInt1 = -1)
    {
      e = paramInt1;
      k.d = (d(paramq) + k.e);
      k.b = b.a(paramq);
      paramInt1 = -b.a(paramq) + b.c();
      break;
    }
  }
  
  private void a(a parama)
  {
    b(a, b);
  }
  
  private void a(RecyclerView.m paramm, int paramInt)
  {
    if (paramInt < 0) {}
    for (;;)
    {
      return;
      int j = r();
      int i;
      View localView;
      if (c)
      {
        i = j - 1;
        while (i >= 0)
        {
          localView = e(i);
          if (b.b(localView) > paramInt)
          {
            a(paramm, j - 1, i);
            return;
          }
          i -= 1;
        }
      }
      else
      {
        i = 0;
        while (i < j)
        {
          localView = e(i);
          if (b.b(localView) > paramInt)
          {
            a(paramm, 0, i);
            return;
          }
          i += 1;
        }
      }
    }
  }
  
  private void a(RecyclerView.m paramm, int paramInt1, int paramInt2)
  {
    if (paramInt1 == paramInt2) {}
    for (;;)
    {
      return;
      int i = paramInt1;
      if (paramInt2 > paramInt1)
      {
        paramInt2 -= 1;
        while (paramInt2 >= paramInt1)
        {
          a(paramInt2, paramm);
          paramInt2 -= 1;
        }
      }
      else
      {
        while (i > paramInt2)
        {
          a(i, paramm);
          i -= 1;
        }
      }
    }
  }
  
  private void a(RecyclerView.m paramm, c paramc)
  {
    if (!a) {
      return;
    }
    if (f == -1)
    {
      b(paramm, g);
      return;
    }
    a(paramm, g);
  }
  
  private boolean a(RecyclerView.q paramq, a parama)
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
    a = d;
    if ((f != null) && (f.a()))
    {
      c = f.c;
      if (c)
      {
        b = (b.d() - f.b);
        return true;
      }
      b = (b.c() + f.b);
      return true;
    }
    if (e == Integer.MIN_VALUE)
    {
      paramq = a(d);
      int i;
      if (paramq != null)
      {
        if (b.c(paramq) > b.f())
        {
          parama.b();
          return true;
        }
        if (b.a(paramq) - b.c() < 0)
        {
          b = b.c();
          c = false;
          return true;
        }
        if (b.d() - b.b(paramq) < 0)
        {
          b = b.d();
          c = true;
          return true;
        }
        if (c) {}
        for (i = b.b(paramq) + b.b();; i = b.a(paramq))
        {
          b = i;
          return true;
        }
      }
      if (r() > 0)
      {
        i = d(e(0));
        if (d >= i) {
          break label351;
        }
      }
      label351:
      for (int j = 1;; j = 0)
      {
        if (j == c) {
          bool = true;
        }
        c = bool;
        parama.b();
        return true;
      }
    }
    c = c;
    if (c)
    {
      b = (b.d() - e);
      return true;
    }
    b = (b.c() + e);
    return true;
  }
  
  private int b(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq, boolean paramBoolean)
  {
    int i = paramInt - b.c();
    if (i > 0)
    {
      int j = -c(i, paramm, paramq);
      i = j;
      if (paramBoolean)
      {
        paramInt = paramInt + j - b.c();
        i = j;
        if (paramInt > 0)
        {
          b.a(-paramInt);
          i = j - paramInt;
        }
      }
      return i;
    }
    return 0;
  }
  
  private View b(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (c) {
      return a(0, r(), paramBoolean1, paramBoolean2);
    }
    return a(r() - 1, -1, paramBoolean1, paramBoolean2);
  }
  
  private void b(int paramInt1, int paramInt2)
  {
    k.c = (b.d() - paramInt2);
    c localc = k;
    if (c) {}
    for (int i = -1;; i = 1)
    {
      e = i;
      k.d = paramInt1;
      k.f = 1;
      k.b = paramInt2;
      k.g = Integer.MIN_VALUE;
      return;
    }
  }
  
  private void b(a parama)
  {
    c(a, b);
  }
  
  private void b(RecyclerView.m paramm, int paramInt)
  {
    int i = r();
    if (paramInt < 0) {}
    for (;;)
    {
      return;
      int j = b.e() - paramInt;
      View localView;
      if (c)
      {
        paramInt = 0;
        while (paramInt < i)
        {
          localView = e(paramInt);
          if (b.a(localView) < j)
          {
            a(paramm, 0, paramInt);
            return;
          }
          paramInt += 1;
        }
      }
      else
      {
        paramInt = i - 1;
        while (paramInt >= 0)
        {
          localView = e(paramInt);
          if (b.a(localView) < j)
          {
            a(paramm, i - 1, paramInt);
            return;
          }
          paramInt -= 1;
        }
      }
    }
  }
  
  private void b(RecyclerView.m paramm, RecyclerView.q paramq, int paramInt1, int paramInt2)
  {
    if ((!paramq.b()) || (r() == 0) || (paramq.a()) || (!k())) {
      return;
    }
    int i = 0;
    int j = 0;
    List localList = paramm.b();
    int i3 = localList.size();
    int i4 = d(e(0));
    int i1 = 0;
    if (i1 < i3)
    {
      RecyclerView.t localt = (RecyclerView.t)localList.get(i1);
      int i2;
      if (localt.p())
      {
        i2 = j;
        j = i;
        i = i2;
      }
      for (;;)
      {
        i2 = i1 + 1;
        i1 = j;
        j = i;
        i = i1;
        i1 = i2;
        break;
        int i5;
        if (localt.d() < i4)
        {
          i5 = 1;
          label143:
          if (i5 == c) {
            break label195;
          }
        }
        label195:
        for (i2 = -1;; i2 = 1)
        {
          if (i2 != -1) {
            break label201;
          }
          i2 = b.c(a) + i;
          i = j;
          j = i2;
          break;
          i5 = 0;
          break label143;
        }
        label201:
        i2 = b.c(a) + j;
        j = i;
        i = i2;
      }
    }
    k.k = localList;
    if (i > 0)
    {
      c(d(C()), paramInt1);
      k.h = i;
      k.c = 0;
      k.a();
      a(paramm, k, paramq, false);
    }
    if (j > 0)
    {
      b(d(D()), paramInt2);
      k.h = j;
      k.c = 0;
      k.a();
      a(paramm, k, paramq, false);
    }
    k.k = null;
  }
  
  private void b(RecyclerView.m paramm, RecyclerView.q paramq, a parama)
  {
    if (a(paramq, parama)) {}
    while (c(paramm, paramq, parama)) {
      return;
    }
    parama.b();
    if (n) {}
    for (int i = paramq.f() - 1;; i = 0)
    {
      a = i;
      return;
    }
  }
  
  private void c(int paramInt1, int paramInt2)
  {
    k.c = (paramInt2 - b.c());
    k.d = paramInt1;
    c localc = k;
    if (c) {}
    for (paramInt1 = 1;; paramInt1 = -1)
    {
      e = paramInt1;
      k.f = -1;
      k.b = paramInt2;
      k.g = Integer.MIN_VALUE;
      return;
    }
  }
  
  private boolean c(RecyclerView.m paramm, RecyclerView.q paramq, a parama)
  {
    int i = 0;
    if (r() == 0) {}
    do
    {
      return false;
      View localView = y();
      if ((localView != null) && (a.a(parama, localView, paramq)))
      {
        parama.a(localView);
        return true;
      }
    } while (l != n);
    if (c)
    {
      paramm = f(paramm, paramq);
      label66:
      if (paramm == null) {
        break label165;
      }
      parama.b(paramm);
      if ((!paramq.a()) && (k()))
      {
        if ((b.a(paramm) >= b.d()) || (b.b(paramm) < b.c())) {
          i = 1;
        }
        if (i != 0) {
          if (!c) {
            break label167;
          }
        }
      }
    }
    label165:
    label167:
    for (i = b.d();; i = b.c())
    {
      b = i;
      return true;
      paramm = g(paramm, paramq);
      break label66;
      break;
    }
  }
  
  private View f(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if (c) {
      return h(paramm, paramq);
    }
    return i(paramm, paramq);
  }
  
  private View g(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if (c) {
      return i(paramm, paramq);
    }
    return h(paramm, paramq);
  }
  
  private int h(RecyclerView.q paramq)
  {
    boolean bool2 = false;
    if (r() == 0) {
      return 0;
    }
    g();
    arl localarl = b;
    if (!o) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = a(bool1, true);
      bool1 = bool2;
      if (!o) {
        bool1 = true;
      }
      return arr.a(paramq, localarl, localView, b(bool1, true), this, o, c);
    }
  }
  
  private View h(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    return a(paramm, paramq, 0, r(), paramq.f());
  }
  
  private int i(int paramInt)
  {
    int j = -1;
    int i1 = 1;
    int i2 = Integer.MIN_VALUE;
    int i = j;
    switch (paramInt)
    {
    default: 
      i = Integer.MIN_VALUE;
    case 1: 
    case 2: 
    case 33: 
    case 130: 
    case 17: 
      do
      {
        do
        {
          return i;
          return 1;
          i = j;
        } while (a == 1);
        return Integer.MIN_VALUE;
        paramInt = i2;
        if (a == 1) {
          paramInt = 1;
        }
        return paramInt;
        i = j;
      } while (a == 0);
      return Integer.MIN_VALUE;
    }
    if (a == 0) {}
    for (paramInt = i1;; paramInt = Integer.MIN_VALUE) {
      return paramInt;
    }
  }
  
  private int i(RecyclerView.q paramq)
  {
    boolean bool2 = false;
    if (r() == 0) {
      return 0;
    }
    g();
    arl localarl = b;
    if (!o) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = a(bool1, true);
      bool1 = bool2;
      if (!o) {
        bool1 = true;
      }
      return arr.a(paramq, localarl, localView, b(bool1, true), this, o);
    }
  }
  
  private View i(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    return a(paramm, paramq, r() - 1, -1, paramq.f());
  }
  
  private int j(RecyclerView.q paramq)
  {
    boolean bool2 = false;
    if (r() == 0) {
      return 0;
    }
    g();
    arl localarl = b;
    if (!o) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      View localView = a(bool1, true);
      bool1 = bool2;
      if (!o) {
        bool1 = true;
      }
      return arr.b(paramq, localarl, localView, b(bool1, true), this, o);
    }
  }
  
  public int a(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if (a == 1) {
      return 0;
    }
    return c(paramInt, paramm, paramq);
  }
  
  int a(RecyclerView.m paramm, c paramc, RecyclerView.q paramq, boolean paramBoolean)
  {
    int i1 = c;
    if (g != Integer.MIN_VALUE)
    {
      if (c < 0) {
        g += c;
      }
      a(paramm, paramc);
    }
    int i = c + h;
    b localb = new b();
    if ((i > 0) && (paramc.a(paramq)))
    {
      localb.a();
      a(paramm, paramq, paramc, localb);
      if (!b) {
        break label104;
      }
    }
    for (;;)
    {
      return i1 - c;
      label104:
      b += a * f;
      int j;
      if ((c) && (k.k == null))
      {
        j = i;
        if (paramq.a()) {}
      }
      else
      {
        c -= a;
        j = i - a;
      }
      if (g != Integer.MIN_VALUE)
      {
        g += a;
        if (c < 0) {
          g += c;
        }
        a(paramm, paramc);
      }
      i = j;
      if (!paramBoolean) {
        break;
      }
      i = j;
      if (!d) {
        break;
      }
    }
  }
  
  protected int a(RecyclerView.q paramq)
  {
    if (paramq.d()) {
      return b.f();
    }
    return 0;
  }
  
  public View a(int paramInt)
  {
    int i = r();
    Object localObject;
    if (i == 0) {
      localObject = null;
    }
    View localView;
    do
    {
      return (View)localObject;
      int j = paramInt - d(e(0));
      if ((j < 0) || (j >= i)) {
        break;
      }
      localView = e(j);
      localObject = localView;
    } while (d(localView) == paramInt);
    return super.a(paramInt);
  }
  
  View a(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    g();
    int j = b.c();
    int i1 = b.d();
    if (paramInt2 > paramInt1) {}
    Object localObject;
    View localView;
    for (int i = 1;; i = -1)
    {
      localObject = null;
      if (paramInt1 == paramInt2) {
        break label130;
      }
      localView = e(paramInt1);
      int i2 = b.a(localView);
      int i3 = b.b(localView);
      if ((i2 >= i1) || (i3 <= j)) {
        break label133;
      }
      if ((paramBoolean1) && ((i2 < j) || (i3 > i1))) {
        break;
      }
      return localView;
    }
    if ((paramBoolean2) && (localObject == null)) {
      localObject = localView;
    }
    label130:
    label133:
    for (;;)
    {
      paramInt1 += i;
      break;
      return (View)localObject;
    }
  }
  
  public View a(View paramView, int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    B();
    if (r() == 0) {}
    label42:
    label134:
    label136:
    label142:
    for (;;)
    {
      return null;
      paramInt = i(paramInt);
      if (paramInt != Integer.MIN_VALUE)
      {
        g();
        if (paramInt == -1)
        {
          paramView = g(paramm, paramq);
          if (paramView == null) {
            break label134;
          }
          g();
          a(paramInt, (int)(0.33F * b.f()), false, paramq);
          k.g = Integer.MIN_VALUE;
          k.a = false;
          a(paramm, k, paramq, true);
          if (paramInt != -1) {
            break label136;
          }
        }
        for (paramm = C();; paramm = D())
        {
          if ((paramm == paramView) || (!paramm.isFocusable())) {
            break label142;
          }
          return paramm;
          paramView = f(paramm, paramq);
          break label42;
          break;
        }
      }
    }
  }
  
  View a(RecyclerView.m paramm, RecyclerView.q paramq, int paramInt1, int paramInt2, int paramInt3)
  {
    paramq = null;
    g();
    int j = b.c();
    int i1 = b.d();
    int i;
    label35:
    Object localObject1;
    if (paramInt2 > paramInt1)
    {
      i = 1;
      paramm = null;
      if (paramInt1 == paramInt2) {
        break label157;
      }
      localObject1 = e(paramInt1);
      int i2 = d((View)localObject1);
      if ((i2 < 0) || (i2 >= paramInt3)) {
        break label172;
      }
      if (!((RecyclerView.LayoutParams)((View)localObject1).getLayoutParams()).a()) {
        break label113;
      }
      if (paramm != null) {
        break label172;
      }
      paramm = paramq;
      paramq = (RecyclerView.q)localObject1;
    }
    for (;;)
    {
      paramInt1 += i;
      localObject1 = paramq;
      paramq = paramm;
      paramm = (RecyclerView.m)localObject1;
      break label35;
      i = -1;
      break;
      label113:
      Object localObject2;
      if (b.a((View)localObject1) < i1)
      {
        localObject2 = localObject1;
        if (b.b((View)localObject1) >= j) {}
      }
      else
      {
        if (paramq != null) {
          break label172;
        }
        paramq = paramm;
        paramm = (RecyclerView.m)localObject1;
        continue;
        label157:
        if (paramq == null) {
          break label167;
        }
      }
      for (;;)
      {
        localObject2 = paramq;
        return (View)localObject2;
        label167:
        paramq = paramm;
      }
      label172:
      localObject1 = paramm;
      paramm = paramq;
      paramq = (RecyclerView.q)localObject1;
    }
  }
  
  public RecyclerView.LayoutParams a()
  {
    return new RecyclerView.LayoutParams(-2, -2);
  }
  
  public void a(Parcelable paramParcelable)
  {
    if ((paramParcelable instanceof SavedState))
    {
      f = ((SavedState)paramParcelable);
      l();
    }
  }
  
  public void a(AccessibilityEvent paramAccessibilityEvent)
  {
    super.a(paramAccessibilityEvent);
    if (r() > 0)
    {
      paramAccessibilityEvent = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
      paramAccessibilityEvent.setFromIndex(i());
      paramAccessibilityEvent.setToIndex(j());
    }
  }
  
  public void a(RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if ((f != null) && (f.a())) {
      d = f.a;
    }
    g();
    k.a = false;
    B();
    g.a();
    g.c = (c ^ n);
    b(paramm, paramq, g);
    int i = a(paramq);
    int j;
    int i1;
    int i2;
    Object localObject;
    label216:
    label229:
    int i3;
    if (k.j >= 0)
    {
      j = 0;
      i1 = j + b.c();
      i2 = i + b.g();
      i = i2;
      j = i1;
      if (paramq.a())
      {
        i = i2;
        j = i1;
        if (d != -1)
        {
          i = i2;
          j = i1;
          if (e != Integer.MIN_VALUE)
          {
            localObject = a(d);
            i = i2;
            j = i1;
            if (localObject != null)
            {
              if (!c) {
                break label585;
              }
              i = b.d() - b.b((View)localObject) - e;
              if (i <= 0) {
                break label617;
              }
              j = i1 + i;
              i = i2;
            }
          }
        }
      }
      a(paramm, paramq, g);
      a(paramm);
      k.i = paramq.a();
      if (!g.c) {
        break label629;
      }
      b(g);
      k.h = j;
      a(paramm, k, paramq, false);
      i2 = k.b;
      i3 = k.d;
      j = i;
      if (k.c > 0) {
        j = i + k.c;
      }
      a(g);
      k.h = j;
      localObject = k;
      d += k.e;
      a(paramm, k, paramq, false);
      i1 = k.b;
      if (k.c <= 0) {
        break label873;
      }
      i = k.c;
      c(i3, i2);
      k.h = i;
      a(paramm, k, paramq, false);
    }
    label458:
    label585:
    label617:
    label629:
    label828:
    label873:
    for (i = k.b;; i = i2)
    {
      j = i;
      i = i1;
      i1 = i;
      i2 = j;
      if (r() > 0)
      {
        if (!(c ^ n)) {
          break label828;
        }
        i1 = a(i, paramm, paramq, true);
        i2 = j + i1;
        j = b(i2, paramm, paramq, false);
        i2 += j;
      }
      for (i1 = i + i1 + j;; i1 = i + i3)
      {
        b(paramm, paramq, i2, i1);
        if (!paramq.a())
        {
          d = -1;
          e = Integer.MIN_VALUE;
          b.a();
        }
        l = n;
        f = null;
        return;
        j = i;
        i = 0;
        break;
        i = b.a((View)localObject);
        j = b.c();
        i = e - (i - j);
        break label216;
        i = i2 - i;
        j = i1;
        break label229;
        a(g);
        k.h = i;
        a(paramm, k, paramq, false);
        i1 = k.b;
        i3 = k.d;
        i = j;
        if (k.c > 0) {
          i = j + k.c;
        }
        b(g);
        k.h = i;
        localObject = k;
        d += k.e;
        a(paramm, k, paramq, false);
        i2 = k.b;
        i = i1;
        j = i2;
        if (k.c <= 0) {
          break label458;
        }
        i = k.c;
        b(i3, i1);
        k.h = i;
        a(paramm, k, paramq, false);
        i = k.b;
        j = i2;
        break label458;
        i1 = b(j, paramm, paramq, true);
        i += i1;
        i3 = a(i, paramm, paramq, false);
        i2 = j + i1 + i3;
      }
    }
  }
  
  void a(RecyclerView.m paramm, RecyclerView.q paramq, a parama) {}
  
  void a(RecyclerView.m paramm, RecyclerView.q paramq, c paramc, b paramb)
  {
    paramm = paramc.a(paramm);
    if (paramm == null)
    {
      b = true;
      return;
    }
    paramq = (RecyclerView.LayoutParams)paramm.getLayoutParams();
    boolean bool2;
    boolean bool1;
    label61:
    int i;
    int j;
    label120:
    int i1;
    int i2;
    if (k == null)
    {
      bool2 = c;
      if (f == -1)
      {
        bool1 = true;
        if (bool2 != bool1) {
          break label215;
        }
        b(paramm);
        a(paramm, 0, 0);
        a = b.c(paramm);
        if (a != 1) {
          break label322;
        }
        if (!f()) {
          break label271;
        }
        i = s() - w();
        j = i - b.d(paramm);
        if (f != -1) {
          break label293;
        }
        i1 = b;
        i2 = b - a;
      }
    }
    for (;;)
    {
      a(paramm, j + leftMargin, i2 + topMargin, i - rightMargin, i1 - bottomMargin);
      if ((paramq.a()) || (paramq.b())) {
        c = true;
      }
      d = paramm.isFocusable();
      return;
      bool1 = false;
      break;
      label215:
      b(paramm, 0);
      break label61;
      bool2 = c;
      if (f == -1) {}
      for (bool1 = true;; bool1 = false)
      {
        if (bool2 != bool1) {
          break label262;
        }
        a(paramm);
        break;
      }
      label262:
      a(paramm, 0);
      break label61;
      label271:
      j = u();
      i = b.d(paramm) + j;
      break label120;
      label293:
      i2 = b;
      i1 = b;
      int i3 = a;
      i1 += i3;
      continue;
      label322:
      i2 = v();
      i1 = b.d(paramm) + i2;
      if (f == -1)
      {
        i = b;
        j = b - a;
      }
      else
      {
        j = b;
        i = b;
        i3 = a;
        i += i3;
      }
    }
  }
  
  public void a(RecyclerView paramRecyclerView, RecyclerView.m paramm)
  {
    super.a(paramRecyclerView, paramm);
    if (p)
    {
      c(paramm);
      paramm.a();
    }
  }
  
  public void a(String paramString)
  {
    if (f == null) {
      super.a(paramString);
    }
  }
  
  public int b(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if (a == 0) {
      return 0;
    }
    return c(paramInt, paramm, paramq);
  }
  
  public int b(RecyclerView.q paramq)
  {
    return h(paramq);
  }
  
  public Parcelable b()
  {
    if (f != null) {
      return new SavedState(f);
    }
    SavedState localSavedState = new SavedState();
    if (r() > 0)
    {
      g();
      boolean bool = l ^ c;
      c = bool;
      if (bool)
      {
        localView = D();
        b = (b.d() - b.b(localView));
        a = d(localView);
        return localSavedState;
      }
      View localView = C();
      a = d(localView);
      b = (b.a(localView) - b.c());
      return localSavedState;
    }
    localSavedState.b();
    return localSavedState;
  }
  
  public void b(int paramInt)
  {
    d = paramInt;
    e = Integer.MIN_VALUE;
    if (f != null) {
      f.b();
    }
    l();
  }
  
  int c(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
  {
    if ((r() == 0) || (paramInt == 0)) {
      return 0;
    }
    k.a = true;
    g();
    if (paramInt > 0) {}
    int j;
    int i1;
    for (int i = 1;; i = -1)
    {
      j = Math.abs(paramInt);
      a(i, j, true, paramq);
      i1 = k.g + a(paramm, k, paramq, false);
      if (i1 >= 0) {
        break;
      }
      return 0;
    }
    if (j > i1) {
      paramInt = i * i1;
    }
    b.a(-paramInt);
    k.j = paramInt;
    return paramInt;
  }
  
  public int c(RecyclerView.q paramq)
  {
    return h(paramq);
  }
  
  public boolean c()
  {
    return a == 0;
  }
  
  public int d(RecyclerView.q paramq)
  {
    return i(paramq);
  }
  
  public boolean d()
  {
    return a == 1;
  }
  
  public int e()
  {
    return a;
  }
  
  public int e(RecyclerView.q paramq)
  {
    return i(paramq);
  }
  
  public int f(RecyclerView.q paramq)
  {
    return j(paramq);
  }
  
  protected boolean f()
  {
    return p() == 1;
  }
  
  public int g(RecyclerView.q paramq)
  {
    return j(paramq);
  }
  
  void g()
  {
    if (k == null) {
      k = h();
    }
    if (b == null) {
      b = arl.a(this, a);
    }
  }
  
  c h()
  {
    return new c();
  }
  
  public int i()
  {
    View localView = a(0, r(), false, true);
    if (localView == null) {
      return -1;
    }
    return d(localView);
  }
  
  public int j()
  {
    View localView = a(r() - 1, -1, false, true);
    if (localView == null) {
      return -1;
    }
    return d(localView);
  }
  
  public boolean k()
  {
    return (f == null) && (l == n);
  }
  
  public static class SavedState
    implements Parcelable
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new arf();
    int a;
    int b;
    boolean c;
    
    public SavedState() {}
    
    public SavedState(Parcel paramParcel)
    {
      a = paramParcel.readInt();
      b = paramParcel.readInt();
      if (paramParcel.readInt() == 1) {}
      for (;;)
      {
        c = bool;
        return;
        bool = false;
      }
    }
    
    public SavedState(SavedState paramSavedState)
    {
      a = a;
      b = b;
      c = c;
    }
    
    boolean a()
    {
      return a >= 0;
    }
    
    void b()
    {
      a = -1;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(a);
      paramParcel.writeInt(b);
      if (c) {}
      for (paramInt = 1;; paramInt = 0)
      {
        paramParcel.writeInt(paramInt);
        return;
      }
    }
  }
  
  class a
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
  
  public static class b
  {
    public int a;
    public boolean b;
    public boolean c;
    public boolean d;
    
    void a()
    {
      a = 0;
      b = false;
      c = false;
      d = false;
    }
  }
  
  static class c
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
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.LinearLayoutManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */