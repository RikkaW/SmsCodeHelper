package flyme.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityManager;
import arc;
import ard;
import arq;
import java.util.ArrayList;
import java.util.List;

public final class RecyclerView$m
{
  final ArrayList<RecyclerView.t> a;
  final ArrayList<RecyclerView.t> b;
  private ArrayList<RecyclerView.t> d;
  private final List<RecyclerView.t> e;
  private int f;
  private RecyclerView.l g;
  private RecyclerView.r h;
  
  private void a(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    int i = paramViewGroup.getChildCount() - 1;
    while (i >= 0)
    {
      View localView = paramViewGroup.getChildAt(i);
      if ((localView instanceof ViewGroup)) {
        a((ViewGroup)localView, true);
      }
      i -= 1;
    }
    if (!paramBoolean) {
      return;
    }
    if (paramViewGroup.getVisibility() == 4)
    {
      paramViewGroup.setVisibility(0);
      paramViewGroup.setVisibility(4);
      return;
    }
    i = paramViewGroup.getVisibility();
    paramViewGroup.setVisibility(4);
    paramViewGroup.setVisibility(i);
  }
  
  private void d(View paramView)
  {
    if ((RecyclerView.i(c) != null) && (RecyclerView.i(c).isEnabled()))
    {
      if (ViewCompat.getImportantForAccessibility(paramView) == 0) {
        ViewCompat.setImportantForAccessibility(paramView, 1);
      }
      if (!ViewCompat.hasAccessibilityDelegate(paramView)) {
        ViewCompat.setAccessibilityDelegate(paramView, RecyclerView.j(c).a());
      }
    }
  }
  
  private void f(RecyclerView.t paramt)
  {
    if ((a instanceof ViewGroup)) {
      a((ViewGroup)a, false);
    }
  }
  
  View a(int paramInt, boolean paramBoolean)
  {
    boolean bool = true;
    if ((paramInt < 0) || (paramInt >= c.t.f())) {
      throw new IndexOutOfBoundsException("Invalid item position " + paramInt + "(" + paramInt + "). Item count:" + c.t.f());
    }
    Object localObject2;
    int i;
    if (c.t.a())
    {
      localObject2 = e(paramInt);
      if (localObject2 != null) {
        i = 1;
      }
    }
    for (;;)
    {
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject2 = a(paramInt, -1, paramBoolean);
        localObject1 = localObject2;
        if (localObject2 != null) {
          if (!a((RecyclerView.t)localObject2)) {
            if (!paramBoolean)
            {
              ((RecyclerView.t)localObject2).b(4);
              if (((RecyclerView.t)localObject2).g())
              {
                c.removeDetachedView(a, false);
                ((RecyclerView.t)localObject2).h();
                label174:
                b((RecyclerView.t)localObject2);
              }
            }
            else
            {
              localObject1 = null;
            }
          }
        }
      }
      for (;;)
      {
        Object localObject3 = localObject1;
        int k = i;
        int j;
        if (localObject1 == null)
        {
          k = c.q.a(paramInt);
          if ((k < 0) || (k >= RecyclerView.c(c).a()))
          {
            throw new IndexOutOfBoundsException("Inconsistency detected. Invalid item position " + paramInt + "(offset:" + k + ")." + "state:" + c.t.f());
            i = 0;
            break;
            if (!((RecyclerView.t)localObject2).i()) {
              break label174;
            }
            ((RecyclerView.t)localObject2).j();
            break label174;
            i = 1;
            localObject1 = localObject2;
            continue;
          }
          int m = RecyclerView.c(c).a(k);
          localObject2 = localObject1;
          j = i;
          if (RecyclerView.c(c).b())
          {
            localObject1 = a(RecyclerView.c(c).b(k), m, paramBoolean);
            localObject2 = localObject1;
            j = i;
            if (localObject1 != null)
            {
              b = k;
              j = 1;
              localObject2 = localObject1;
            }
          }
          localObject1 = localObject2;
          if (localObject2 == null)
          {
            localObject1 = localObject2;
            if (h != null)
            {
              localObject3 = h.a(this, paramInt, m);
              localObject1 = localObject2;
              if (localObject3 != null)
              {
                localObject2 = c.a((View)localObject3);
                if (localObject2 == null) {
                  throw new IllegalArgumentException("getViewForPositionAndType returned a view which does not have a ViewHolder");
                }
                localObject1 = localObject2;
                if (((RecyclerView.t)localObject2).c()) {
                  throw new IllegalArgumentException("getViewForPositionAndType returned a view that is ignored. You must call stopIgnoring before returning this view.");
                }
              }
            }
          }
          localObject2 = localObject1;
          if (localObject1 == null)
          {
            localObject1 = f().a(m);
            localObject2 = localObject1;
            if (localObject1 != null)
            {
              ((RecyclerView.t)localObject1).s();
              localObject2 = localObject1;
              if (RecyclerView.u())
              {
                f((RecyclerView.t)localObject1);
                localObject2 = localObject1;
              }
            }
          }
          localObject3 = localObject2;
          k = j;
          if (localObject2 == null)
          {
            localObject2 = RecyclerView.c(c).b(c, m);
            i = j;
          }
        }
        for (;;)
        {
          if ((c.t.a()) && (((RecyclerView.t)localObject2).o()))
          {
            f = paramInt;
            paramInt = 0;
          }
          for (;;)
          {
            localObject1 = a.getLayoutParams();
            if (localObject1 == null)
            {
              localObject1 = (RecyclerView.LayoutParams)c.generateDefaultLayoutParams();
              a.setLayoutParams((ViewGroup.LayoutParams)localObject1);
              label643:
              a = ((RecyclerView.t)localObject2);
              if ((i == 0) || (paramInt == 0)) {
                break label815;
              }
            }
            label815:
            for (paramBoolean = bool;; paramBoolean = false)
            {
              d = paramBoolean;
              return a;
              if ((((RecyclerView.t)localObject2).o()) && (!((RecyclerView.t)localObject2).m()) && (!((RecyclerView.t)localObject2).l())) {
                break label820;
              }
              j = c.q.a(paramInt);
              i = c;
              RecyclerView.c(c).b((RecyclerView.t)localObject2, j);
              d(a);
              if (c.t.a()) {
                f = paramInt;
              }
              paramInt = 1;
              break;
              if (!c.checkLayoutParams((ViewGroup.LayoutParams)localObject1))
              {
                localObject1 = (RecyclerView.LayoutParams)c.generateLayoutParams((ViewGroup.LayoutParams)localObject1);
                a.setLayoutParams((ViewGroup.LayoutParams)localObject1);
                break label643;
              }
              localObject1 = (RecyclerView.LayoutParams)localObject1;
              break label643;
            }
            label820:
            paramInt = 0;
          }
          i = k;
          localObject2 = localObject3;
        }
      }
      localObject2 = null;
      i = 0;
    }
  }
  
  RecyclerView.t a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int j = 0;
    int k = a.size();
    int i = 0;
    Object localObject;
    if (i < k)
    {
      localObject = (RecyclerView.t)a.get(i);
      if ((((RecyclerView.t)localObject).i()) || (((RecyclerView.t)localObject).d() != paramInt1) || (((RecyclerView.t)localObject).l()) || ((!RecyclerView.q.d(c.t)) && (((RecyclerView.t)localObject).p()))) {
        break label275;
      }
      if ((paramInt2 != -1) && (((RecyclerView.t)localObject).f() != paramInt2)) {
        Log.e("RecyclerView", "Scrap view for position " + paramInt1 + " isn't dirty but has" + " wrong view type! (found " + ((RecyclerView.t)localObject).f() + " but expected " + paramInt2 + ")");
      }
    }
    else
    {
      if (!paramBoolean)
      {
        localObject = c.r.a(paramInt1, paramInt2);
        if (localObject != null) {
          c.s.c(c.a((View)localObject));
        }
      }
      i = b.size();
      paramInt2 = j;
    }
    for (;;)
    {
      if (paramInt2 >= i) {
        break label291;
      }
      localObject = (RecyclerView.t)b.get(paramInt2);
      if ((!((RecyclerView.t)localObject).l()) && (((RecyclerView.t)localObject).d() == paramInt1))
      {
        if (!paramBoolean) {
          b.remove(paramInt2);
        }
        return (RecyclerView.t)localObject;
        ((RecyclerView.t)localObject).b(32);
        return (RecyclerView.t)localObject;
        label275:
        i += 1;
        break;
      }
      paramInt2 += 1;
    }
    label291:
    return null;
  }
  
  RecyclerView.t a(long paramLong, int paramInt, boolean paramBoolean)
  {
    int i = a.size() - 1;
    RecyclerView.t localt2;
    RecyclerView.t localt1;
    while (i >= 0)
    {
      localt2 = (RecyclerView.t)a.get(i);
      if ((localt2.e() == paramLong) && (!localt2.i()))
      {
        if (paramInt == localt2.f())
        {
          localt2.b(32);
          localt1 = localt2;
          if (localt2.p())
          {
            localt1 = localt2;
            if (!c.t.a())
            {
              localt2.a(2, 14);
              localt1 = localt2;
            }
          }
          return localt1;
        }
        if (!paramBoolean)
        {
          a.remove(i);
          c.removeDetachedView(a, false);
          b(a);
        }
      }
      i -= 1;
    }
    i = b.size() - 1;
    for (;;)
    {
      if (i < 0) {
        break label245;
      }
      localt2 = (RecyclerView.t)b.get(i);
      if (localt2.e() == paramLong)
      {
        if (paramInt == localt2.f())
        {
          localt1 = localt2;
          if (paramBoolean) {
            break;
          }
          b.remove(i);
          return localt2;
        }
        if (!paramBoolean) {
          c(i);
        }
      }
      i -= 1;
    }
    label245:
    return null;
  }
  
  public void a()
  {
    a.clear();
    c();
  }
  
  public void a(int paramInt)
  {
    f = paramInt;
    int i = b.size() - 1;
    while ((i >= 0) && (b.size() > paramInt))
    {
      c(i);
      i -= 1;
    }
  }
  
  public void a(View paramView)
  {
    RecyclerView.t localt = RecyclerView.b(paramView);
    if (localt.q()) {
      c.removeDetachedView(paramView, false);
    }
    if (localt.g()) {
      localt.h();
    }
    for (;;)
    {
      b(localt);
      return;
      if (localt.i()) {
        localt.j();
      }
    }
  }
  
  void a(RecyclerView.a parama1, RecyclerView.a parama2, boolean paramBoolean)
  {
    a();
    f().a(parama1, parama2, paramBoolean);
  }
  
  void a(RecyclerView.l paraml)
  {
    if (g != null) {
      g.b();
    }
    g = paraml;
    if (paraml != null) {
      g.a(c.getAdapter());
    }
  }
  
  void a(RecyclerView.r paramr)
  {
    h = paramr;
  }
  
  boolean a(RecyclerView.t paramt)
  {
    if (paramt.p()) {}
    do
    {
      return true;
      if ((b < 0) || (b >= RecyclerView.c(c).a())) {
        throw new IndexOutOfBoundsException("Inconsistency detected. Invalid view holder adapter position" + paramt);
      }
      if ((!c.t.a()) && (RecyclerView.c(c).a(b) != paramt.f())) {
        return false;
      }
    } while ((!RecyclerView.c(c).b()) || (paramt.e() == RecyclerView.c(c).b(b)));
    return false;
  }
  
  public View b(int paramInt)
  {
    View localView = a(paramInt, false);
    c.a(localView, paramInt);
    return localView;
  }
  
  public List<RecyclerView.t> b()
  {
    return e;
  }
  
  void b(View paramView)
  {
    paramView = RecyclerView.b(paramView);
    RecyclerView.t.a(paramView, null);
    paramView.j();
    b(paramView);
  }
  
  void b(RecyclerView.t paramt)
  {
    boolean bool = true;
    int j = 0;
    if ((paramt.g()) || (a.getParent() != null))
    {
      StringBuilder localStringBuilder = new StringBuilder().append("Scrapped or attached views may not be recycled. isScrap:").append(paramt.g()).append(" isAttached:");
      if (a.getParent() != null) {}
      for (;;)
      {
        throw new IllegalArgumentException(bool);
        bool = false;
      }
    }
    if (paramt.q()) {
      throw new IllegalArgumentException("Tmp detached view should be removed from RecyclerView before it can be recycled: " + paramt);
    }
    if (paramt.c()) {
      throw new IllegalArgumentException("Trying to recycle an ignored view holder. You should first call stopIgnoringView(view) before calling recycle.");
    }
    bool = RecyclerView.t.a(paramt);
    int i;
    if ((RecyclerView.c(c) != null) && (bool) && (RecyclerView.c(c).b(paramt)))
    {
      i = 1;
      if ((i == 0) && (!paramt.t())) {
        break label292;
      }
      if (paramt.a(78)) {
        break label287;
      }
      i = b.size();
      if ((i == f) && (i > 0)) {
        c(0);
      }
      if (i >= f) {
        break label287;
      }
      b.add(paramt);
      i = 1;
      label238:
      if (i != 0) {
        break label284;
      }
      c(paramt);
      j = 1;
    }
    for (;;)
    {
      c.t.a(paramt);
      if ((i == 0) && (j == 0) && (bool)) {
        i = null;
      }
      return;
      i = 0;
      break;
      label284:
      continue;
      label287:
      i = 0;
      break label238;
      label292:
      i = 0;
    }
  }
  
  void c()
  {
    int i = b.size() - 1;
    while (i >= 0)
    {
      c(i);
      i -= 1;
    }
    b.clear();
  }
  
  void c(int paramInt)
  {
    c((RecyclerView.t)b.get(paramInt));
    b.remove(paramInt);
  }
  
  void c(View paramView)
  {
    paramView = RecyclerView.b(paramView);
    paramView.a(this);
    if ((!paramView.n()) || (!RecyclerView.e(c)))
    {
      if ((paramView.l()) && (!paramView.p()) && (!RecyclerView.c(c).b())) {
        throw new IllegalArgumentException("Called scrap view with an invalid view. Invalid views cannot be reused from scrap, they should rebound from recycler pool.");
      }
      a.add(paramView);
      return;
    }
    if (d == null) {
      d = new ArrayList();
    }
    d.add(paramView);
  }
  
  void c(RecyclerView.t paramt)
  {
    ViewCompat.setAccessibilityDelegate(a, null);
    e(paramt);
    i = null;
    f().a(paramt);
  }
  
  int d()
  {
    return a.size();
  }
  
  View d(int paramInt)
  {
    return a.get(paramInt)).a;
  }
  
  void d(RecyclerView.t paramt)
  {
    if ((!paramt.n()) || (!RecyclerView.e(c)) || (d == null)) {
      a.remove(paramt);
    }
    for (;;)
    {
      RecyclerView.t.a(paramt, null);
      paramt.j();
      return;
      d.remove(paramt);
    }
  }
  
  RecyclerView.t e(int paramInt)
  {
    int j = 0;
    int k;
    if (d != null)
    {
      k = d.size();
      if (k != 0) {}
    }
    else
    {
      return null;
    }
    int i = 0;
    RecyclerView.t localt;
    while (i < k)
    {
      localt = (RecyclerView.t)d.get(i);
      if ((!localt.i()) && (localt.d() == paramInt))
      {
        localt.b(32);
        return localt;
      }
      i += 1;
    }
    if (RecyclerView.c(c).b())
    {
      paramInt = c.q.a(paramInt);
      if ((paramInt > 0) && (paramInt < RecyclerView.c(c).a()))
      {
        long l = RecyclerView.c(c).b(paramInt);
        paramInt = j;
        while (paramInt < k)
        {
          localt = (RecyclerView.t)d.get(paramInt);
          if ((!localt.i()) && (localt.e() == l))
          {
            localt.b(32);
            return localt;
          }
          paramInt += 1;
        }
      }
    }
    return null;
  }
  
  void e()
  {
    a.clear();
  }
  
  void e(RecyclerView.t paramt)
  {
    if (RecyclerView.k(c) != null) {
      RecyclerView.k(c).a(paramt);
    }
    if (RecyclerView.c(c) != null) {
      RecyclerView.c(c).a(paramt);
    }
    if (c.t != null) {
      c.t.a(paramt);
    }
  }
  
  RecyclerView.l f()
  {
    if (g == null) {
      g = new RecyclerView.l();
    }
    return g;
  }
  
  void g()
  {
    int j;
    int i;
    if ((RecyclerView.c(c) != null) && (RecyclerView.c(c).b()))
    {
      j = b.size();
      i = 0;
    }
    while (i < j)
    {
      RecyclerView.t localt = (RecyclerView.t)b.get(i);
      if (localt != null) {
        localt.b(6);
      }
      i += 1;
      continue;
      c();
    }
  }
  
  void h()
  {
    int j = 0;
    int k = b.size();
    int i = 0;
    while (i < k)
    {
      ((RecyclerView.t)b.get(i)).a();
      i += 1;
    }
    k = a.size();
    i = 0;
    while (i < k)
    {
      ((RecyclerView.t)a.get(i)).a();
      i += 1;
    }
    if (d != null)
    {
      k = d.size();
      i = j;
      while (i < k)
      {
        ((RecyclerView.t)d.get(i)).a();
        i += 1;
      }
    }
  }
  
  void i()
  {
    int j = b.size();
    int i = 0;
    while (i < j)
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)b.get(i)).a.getLayoutParams();
      if (localLayoutParams != null) {
        c = true;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.m
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */