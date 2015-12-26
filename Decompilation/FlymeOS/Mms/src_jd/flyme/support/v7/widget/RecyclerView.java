package flyme.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.database.Observable;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.os.TraceCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.VelocityTrackerCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.support.v4.widget.EdgeEffectCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.FocusFinder;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.View.MeasureSpec;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Interpolator;
import arc;
import ard;
import aro;
import arp;
import arq;
import java.util.ArrayList;
import java.util.List;

public class RecyclerView
  extends ViewGroup
  implements NestedScrollingChild, ScrollingView
{
  private static final boolean a;
  private static final Interpolator ak;
  private static final Class<?>[] b;
  private boolean A;
  private final AccessibilityManager B;
  private List<i> C;
  private boolean D;
  private int E;
  private EdgeEffectCompat F;
  private EdgeEffectCompat G;
  private EdgeEffectCompat H;
  private EdgeEffectCompat I;
  private int J;
  private int K;
  private VelocityTracker L;
  private int M;
  private int N;
  private int O;
  private int P;
  private int Q;
  private final int R;
  private final int S;
  private float T;
  private final s U;
  private k V;
  private List<k> W;
  private RecyclerView.e.a aa;
  private boolean ab;
  private arq ac;
  private d ad;
  private final int[] ae;
  private final NestedScrollingChildHelper af;
  private final int[] ag;
  private final int[] ah;
  private final int[] ai;
  private Runnable aj;
  private int al;
  private int am;
  private final o c;
  private SavedState d;
  private boolean e;
  private final Runnable f;
  private final Rect g;
  private a h;
  private h i;
  private n j;
  private final ArrayList<f> k;
  private final ArrayList<j> l;
  private j m;
  private boolean n;
  private boolean o;
  final m p;
  arc q;
  ard r;
  e s;
  final q t;
  boolean u;
  boolean v;
  private boolean w;
  private boolean x;
  private boolean y;
  private int z;
  
  static
  {
    if ((Build.VERSION.SDK_INT == 18) || (Build.VERSION.SDK_INT == 19) || (Build.VERSION.SDK_INT == 20)) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      b = new Class[] { Context.class, AttributeSet.class, Integer.TYPE, Integer.TYPE };
      ak = new aro();
      return;
    }
  }
  
  private void A()
  {
    boolean bool2 = true;
    if (D)
    {
      q.a();
      r();
      i.e(this);
    }
    int i1;
    label89:
    q localq;
    if ((s != null) && (i.k()))
    {
      q.b();
      if (((!u) || (v)) && (!u) && ((!v) || (!x()))) {
        break label210;
      }
      i1 = 1;
      localq = t;
      if ((!w) || (s == null) || ((!D) && (i1 == 0) && (!h.a(i))) || ((D) && (!h.b()))) {
        break label215;
      }
      bool1 = true;
      label149:
      q.c(localq, bool1);
      localq = t;
      if ((!q.b(t)) || (i1 == 0) || (D) || (!z())) {
        break label220;
      }
    }
    label210:
    label215:
    label220:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      q.d(localq, bool1);
      return;
      q.e();
      break;
      i1 = 0;
      break label89;
      bool1 = false;
      break label149;
    }
  }
  
  private boolean B()
  {
    int i2 = getChildCount();
    if (i2 == 0) {
      return true;
    }
    if (h != null) {}
    for (int i1 = h.a();; i1 = 0)
    {
      if (i2 != i1) {
        return false;
      }
      if (getChildAt(i2 - 1).getBottom() - getChildAt(0).getTop() <= getHeight() - getPaddingTop() - getPaddingBottom()) {
        break;
      }
      return false;
    }
  }
  
  private void a(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    int i2 = 1;
    int i3 = 0;
    int i1;
    if (paramFloat2 < 0.0F)
    {
      i();
      i1 = i3;
      if (F.onPull(-paramFloat2 / getWidth(), 1.0F - paramFloat3 / getHeight())) {
        i1 = 1;
      }
      if (paramFloat4 >= 0.0F) {
        break label158;
      }
      k();
      if (!G.onPull(-paramFloat4 / getHeight(), paramFloat1 / getWidth())) {
        break label196;
      }
    }
    for (;;)
    {
      if ((i2 != 0) || (paramFloat2 != 0.0F) || (paramFloat4 != 0.0F)) {
        ViewCompat.postInvalidateOnAnimation(this);
      }
      return;
      i1 = i3;
      if (paramFloat2 <= 0.0F) {
        break;
      }
      j();
      i1 = i3;
      if (!H.onPull(paramFloat2 / getWidth(), paramFloat3 / getHeight())) {
        break;
      }
      i1 = 1;
      break;
      label158:
      if (paramFloat4 > 0.0F)
      {
        l();
        if (I.onPull(paramFloat4 / getHeight(), 1.0F - paramFloat1 / getWidth())) {}
      }
      else
      {
        label196:
        i2 = i1;
      }
    }
  }
  
  private void a(int paramInt)
  {
    if (i == null) {
      return;
    }
    i.b(paramInt);
    awakenScrollBars();
  }
  
  private void a(ArrayMap<View, Rect> paramArrayMap)
  {
    List localList = t.d;
    int i1 = localList.size() - 1;
    if (i1 >= 0)
    {
      View localView = (View)localList.get(i1);
      t localt = b(localView);
      g localg = (g)t.a.remove(localt);
      if (!t.a()) {
        t.b.remove(localt);
      }
      if (paramArrayMap.remove(localView) != null) {
        i.a(localView, p);
      }
      for (;;)
      {
        i1 -= 1;
        break;
        if (localg != null) {
          a(localg);
        } else {
          a(new g(localt, localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom()));
        }
      }
    }
    localList.clear();
  }
  
  private void a(a parama, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (h != null)
    {
      h.b(c);
      h.b(this);
    }
    if ((!paramBoolean1) || (paramBoolean2))
    {
      if (s != null) {
        s.b();
      }
      if (i != null)
      {
        i.c(p);
        i.b(p);
      }
      p.a();
    }
    q.a();
    a locala = h;
    h = parama;
    if (parama != null)
    {
      parama.a(c);
      parama.a(this);
    }
    if (i != null) {
      i.a(locala, h);
    }
    p.a(locala, h, paramBoolean1);
    q.a(t, true);
    r();
  }
  
  private void a(g paramg)
  {
    View localView = a.a;
    b(a);
    int i1 = b;
    int i2 = c;
    int i3 = localView.getLeft();
    int i4 = localView.getTop();
    if ((!a.p()) && ((i1 != i3) || (i2 != i4)))
    {
      a.a(false);
      localView.layout(i3, i4, localView.getWidth() + i3, localView.getHeight() + i4);
      if (s.a(a, i1, i2, i3, i4)) {
        y();
      }
    }
    do
    {
      return;
      a.a(false);
    } while (!s.a(a));
    y();
  }
  
  private void a(t paramt, Rect paramRect, int paramInt1, int paramInt2)
  {
    View localView = a;
    if ((paramRect != null) && ((left != paramInt1) || (top != paramInt2)))
    {
      paramt.a(false);
      if (s.a(paramt, left, top, paramInt1, paramInt2)) {
        y();
      }
    }
    do
    {
      return;
      paramt.a(false);
    } while (!s.b(paramt));
    y();
  }
  
  private void a(t paramt1, t paramt2)
  {
    paramt1.a(false);
    b(paramt1);
    g = paramt2;
    p.d(paramt1);
    int i3 = a.getLeft();
    int i4 = a.getTop();
    int i2;
    int i1;
    if ((paramt2 == null) || (paramt2.c()))
    {
      i2 = i4;
      i1 = i3;
    }
    for (;;)
    {
      if (s.a(paramt1, paramt2, i3, i4, i1, i2)) {
        y();
      }
      return;
      i1 = a.getLeft();
      i2 = a.getTop();
      paramt2.a(false);
      h = paramt1;
    }
  }
  
  private void a(int[] paramArrayOfInt)
  {
    int i6 = r.b();
    if (i6 == 0)
    {
      paramArrayOfInt[0] = 0;
      paramArrayOfInt[1] = 0;
      return;
    }
    int i1 = Integer.MAX_VALUE;
    int i4 = Integer.MIN_VALUE;
    int i3 = 0;
    t localt;
    if (i3 < i6)
    {
      localt = b(r.b(i3));
      if (!localt.c()) {}
    }
    for (;;)
    {
      i3 += 1;
      break;
      int i5 = localt.d();
      int i2 = i1;
      if (i5 < i1) {
        i2 = i5;
      }
      if (i5 > i4)
      {
        i4 = i5;
        i1 = i2;
        continue;
        paramArrayOfInt[0] = i1;
        paramArrayOfInt[1] = i4;
      }
      else
      {
        i1 = i2;
      }
    }
  }
  
  private boolean a(MotionEvent paramMotionEvent)
  {
    int i2 = paramMotionEvent.getAction();
    if ((i2 == 3) || (i2 == 0)) {
      m = null;
    }
    int i3 = l.size();
    int i1 = 0;
    while (i1 < i3)
    {
      j localj = (j)l.get(i1);
      if ((localj.a(this, paramMotionEvent)) && (i2 != 3))
      {
        m = localj;
        return true;
      }
      i1 += 1;
    }
    return false;
  }
  
  static t b(View paramView)
  {
    if (paramView == null) {
      return null;
    }
    return getLayoutParamsa;
  }
  
  private void b()
  {
    f.run();
  }
  
  private void b(t paramt)
  {
    View localView = a;
    if (localView.getParent() == this) {}
    for (int i1 = 1;; i1 = 0)
    {
      p.d(a(localView));
      if (!paramt.q()) {
        break;
      }
      r.a(localView, -1, localView.getLayoutParams(), true);
      return;
    }
    if (i1 == 0)
    {
      r.a(localView, true);
      return;
    }
    r.d(localView);
  }
  
  private boolean b(MotionEvent paramMotionEvent)
  {
    int i1 = paramMotionEvent.getAction();
    int i2;
    if (m != null)
    {
      if (i1 == 0) {
        m = null;
      }
    }
    else
    {
      if (i1 == 0) {
        break label108;
      }
      i2 = l.size();
      i1 = 0;
    }
    while (i1 < i2)
    {
      j localj = (j)l.get(i1);
      if (localj.a(this, paramMotionEvent))
      {
        m = localj;
        return true;
        m.b(this, paramMotionEvent);
        if ((i1 == 3) || (i1 == 1)) {
          m = null;
        }
        return true;
      }
      i1 += 1;
    }
    label108:
    return false;
  }
  
  private void c()
  {
    U.b();
    if (i != null) {
      i.z();
    }
  }
  
  private void c(MotionEvent paramMotionEvent)
  {
    int i1 = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (MotionEventCompat.getPointerId(paramMotionEvent, i1) == K) {
      if (i1 != 0) {
        break label75;
      }
    }
    label75:
    for (i1 = 1;; i1 = 0)
    {
      K = MotionEventCompat.getPointerId(paramMotionEvent, i1);
      int i2 = (int)(MotionEventCompat.getX(paramMotionEvent, i1) + 0.5F);
      O = i2;
      M = i2;
      i1 = (int)(MotionEventCompat.getY(paramMotionEvent, i1) + 0.5F);
      P = i1;
      N = i1;
      return;
    }
  }
  
  private void d()
  {
    boolean bool2 = false;
    if (F != null) {
      bool2 = F.onRelease();
    }
    boolean bool1 = bool2;
    if (G != null) {
      bool1 = bool2 | G.onRelease();
    }
    bool2 = bool1;
    if (H != null) {
      bool2 = bool1 | H.onRelease();
    }
    bool1 = bool2;
    if (I != null) {
      bool1 = bool2 | I.onRelease();
    }
    if (bool1) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void e()
  {
    if (L != null) {
      L.clear();
    }
    stopNestedScroll();
    d();
    setScrollState(0);
  }
  
  private void f()
  {
    E += 1;
  }
  
  private void f(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (F != null)
    {
      bool1 = bool2;
      if (!F.isFinished())
      {
        bool1 = bool2;
        if (paramInt1 > 0) {
          bool1 = F.onRelease();
        }
      }
    }
    bool2 = bool1;
    if (H != null)
    {
      bool2 = bool1;
      if (!H.isFinished())
      {
        bool2 = bool1;
        if (paramInt1 < 0) {
          bool2 = bool1 | H.onRelease();
        }
      }
    }
    bool1 = bool2;
    if (G != null)
    {
      bool1 = bool2;
      if (!G.isFinished())
      {
        bool1 = bool2;
        if (paramInt2 > 0) {
          bool1 = bool2 | G.onRelease();
        }
      }
    }
    bool2 = bool1;
    if (I != null)
    {
      bool2 = bool1;
      if (!I.isFinished())
      {
        bool2 = bool1;
        if (paramInt2 < 0) {
          bool2 = bool1 | I.onRelease();
        }
      }
    }
    if (bool2) {
      ViewCompat.postInvalidateOnAnimation(this);
    }
  }
  
  private void f(View paramView)
  {
    t localt = b(paramView);
    d(paramView);
    if ((h != null) && (localt != null)) {
      h.c(localt);
    }
    if (C != null)
    {
      int i1 = C.size() - 1;
      while (i1 >= 0)
      {
        ((i)C.get(i1)).a(paramView);
        i1 -= 1;
      }
    }
  }
  
  private void g(int paramInt1, int paramInt2)
  {
    int i2 = View.MeasureSpec.getMode(paramInt1);
    int i1 = View.MeasureSpec.getMode(paramInt2);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    switch (i2)
    {
    default: 
      paramInt1 = ViewCompat.getMinimumWidth(this);
    }
    switch (i1)
    {
    default: 
      paramInt2 = ViewCompat.getMinimumHeight(this);
    }
    setMeasuredDimension(paramInt1, paramInt2);
  }
  
  private float getScrollFactor()
  {
    if (T == Float.MIN_VALUE)
    {
      TypedValue localTypedValue = new TypedValue();
      if (getContext().getTheme().resolveAttribute(16842829, localTypedValue, true)) {
        T = localTypedValue.getDimension(getContext().getResources().getDisplayMetrics());
      }
    }
    else
    {
      return T;
    }
    return 0.0F;
  }
  
  private boolean h(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    int i2 = r.b();
    boolean bool1;
    if (i2 == 0) {
      if (paramInt1 == 0)
      {
        bool1 = bool2;
        if (paramInt2 == 0) {}
      }
      else
      {
        bool1 = true;
      }
    }
    int i1;
    do
    {
      return bool1;
      i1 = 0;
      bool1 = bool2;
    } while (i1 >= i2);
    t localt = b(r.b(i1));
    if (localt.c()) {}
    int i3;
    do
    {
      i1 += 1;
      break;
      i3 = localt.d();
    } while ((i3 >= paramInt1) && (i3 <= paramInt2));
    return true;
  }
  
  private void v()
  {
    E -= 1;
    if (E < 1)
    {
      E = 0;
      w();
    }
  }
  
  private void w()
  {
    int i1 = z;
    z = 0;
    if ((i1 != 0) && (B != null) && (B.isEnabled()))
    {
      AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain();
      localAccessibilityEvent.setEventType(2048);
      AccessibilityEventCompat.setContentChangeTypes(localAccessibilityEvent, i1);
      sendAccessibilityEventUnchecked(localAccessibilityEvent);
    }
  }
  
  private boolean x()
  {
    return (s != null) && (s.a());
  }
  
  private void y()
  {
    if ((!ab) && (n))
    {
      ViewCompat.postOnAnimation(this, aj);
      ab = true;
    }
  }
  
  private boolean z()
  {
    return (s != null) && (i.k());
  }
  
  long a(t paramt)
  {
    if (h.b()) {
      return paramt.e();
    }
    return b;
  }
  
  public t a(View paramView)
  {
    ViewParent localViewParent = paramView.getParent();
    if ((localViewParent != null) && (localViewParent != this)) {
      throw new IllegalArgumentException("View " + paramView + " is not a direct child of " + this);
    }
    return b(paramView);
  }
  
  void a()
  {
    if (h == null)
    {
      Log.e("RecyclerView", "No adapter attached; skipping layout");
      return;
    }
    if (i == null)
    {
      Log.e("RecyclerView", "No layout manager attached; skipping layout");
      return;
    }
    t.d.clear();
    g();
    f();
    A();
    Object localObject2 = t;
    Object localObject1;
    int i2;
    int i1;
    if ((q.b(t)) && (v) && (x()))
    {
      localObject1 = new ArrayMap();
      c = ((ArrayMap)localObject1);
      v = false;
      u = false;
      q.b(t, q.a(t));
      t.e = h.a();
      a(ae);
      if (!q.b(t)) {
        break label302;
      }
      t.a.clear();
      t.b.clear();
      i2 = r.b();
      i1 = 0;
      label193:
      if (i1 >= i2) {
        break label302;
      }
      localObject1 = b(r.b(i1));
      if ((!((t)localObject1).c()) && ((!((t)localObject1).l()) || (h.b()))) {
        break label250;
      }
    }
    for (;;)
    {
      i1 += 1;
      break label193;
      localObject1 = null;
      break;
      label250:
      localObject2 = a;
      t.a.put(localObject1, new g((t)localObject1, ((View)localObject2).getLeft(), ((View)localObject2).getTop(), ((View)localObject2).getRight(), ((View)localObject2).getBottom()));
    }
    label302:
    long l1;
    boolean bool;
    if (q.a(t))
    {
      p();
      if (t.c != null)
      {
        i2 = r.b();
        i1 = 0;
        while (i1 < i2)
        {
          localObject1 = b(r.b(i1));
          if ((((t)localObject1).n()) && (!((t)localObject1).p()) && (!((t)localObject1).c()))
          {
            l1 = a((t)localObject1);
            t.c.put(Long.valueOf(l1), localObject1);
            t.a.remove(localObject1);
          }
          i1 += 1;
        }
      }
      bool = q.c(t);
      q.a(t, false);
      i.a(p, t);
      q.a(t, bool);
      localObject2 = new ArrayMap();
      i1 = 0;
      label476:
      if (i1 < r.b())
      {
        localObject1 = r.b(i1);
        if (!b((View)localObject1).c()) {}
      }
    }
    label508:
    label517:
    label739:
    label749:
    label905:
    label911:
    label1007:
    label1264:
    label1447:
    label1515:
    label1561:
    label1682:
    label1691:
    for (;;)
    {
      i1 += 1;
      break label476;
      i2 = 0;
      if (i2 < t.a.size()) {
        if (t.a.keyAt(i2)).a != localObject1) {}
      }
      for (i2 = 1;; i2 = 0)
      {
        if (i2 != 0) {
          break label1691;
        }
        ((ArrayMap)localObject2).put(localObject1, new Rect(((View)localObject1).getLeft(), ((View)localObject1).getTop(), ((View)localObject1).getRight(), ((View)localObject1).getBottom()));
        break label508;
        i2 += 1;
        break label517;
        q();
        q.c();
        for (;;)
        {
          t.e = h.a();
          q.a(t, 0);
          q.b(t, false);
          i.a(p, t);
          q.a(t, false);
          d = null;
          localObject1 = t;
          Object localObject3;
          if ((q.b(t)) && (s != null))
          {
            bool = true;
            q.c((q)localObject1, bool);
            if (!q.b(t)) {
              break label1561;
            }
            if (t.c == null) {
              break label905;
            }
            localObject1 = new ArrayMap();
            i2 = r.b();
            i1 = 0;
            if (i1 >= i2) {
              break label1007;
            }
            localObject3 = b(r.b(i1));
            if (!((t)localObject3).c()) {
              break label911;
            }
          }
          Object localObject4;
          for (;;)
          {
            i1 += 1;
            break label749;
            q();
            q.e();
            if (t.c == null) {
              break label1682;
            }
            i2 = r.b();
            i1 = 0;
            while (i1 < i2)
            {
              localObject1 = b(r.b(i1));
              if ((((t)localObject1).n()) && (!((t)localObject1).p()) && (!((t)localObject1).c()))
              {
                l1 = a((t)localObject1);
                t.c.put(Long.valueOf(l1), localObject1);
                t.a.remove(localObject1);
              }
              i1 += 1;
            }
            bool = false;
            break;
            localObject1 = null;
            break label739;
            localObject4 = a;
            l1 = a((t)localObject3);
            if ((localObject1 != null) && (t.c.get(Long.valueOf(l1)) != null)) {
              ((ArrayMap)localObject1).put(Long.valueOf(l1), localObject3);
            } else {
              t.b.put(localObject3, new g((t)localObject3, ((View)localObject4).getLeft(), ((View)localObject4).getTop(), ((View)localObject4).getRight(), ((View)localObject4).getBottom()));
            }
          }
          a((ArrayMap)localObject2);
          i1 = t.a.size() - 1;
          while (i1 >= 0)
          {
            localObject3 = (t)t.a.keyAt(i1);
            if (!t.b.containsKey(localObject3))
            {
              localObject3 = (g)t.a.valueAt(i1);
              t.a.removeAt(i1);
              localObject4 = a.a;
              p.d(a);
              a((g)localObject3);
            }
            i1 -= 1;
          }
          i1 = t.b.size();
          if (i1 > 0)
          {
            i1 -= 1;
            if (i1 >= 0)
            {
              localObject4 = (t)t.b.keyAt(i1);
              g localg = (g)t.b.valueAt(i1);
              if ((t.a.isEmpty()) || (!t.a.containsKey(localObject4)))
              {
                t.b.removeAt(i1);
                if (localObject2 == null) {
                  break label1264;
                }
              }
              for (localObject3 = (Rect)((ArrayMap)localObject2).get(a);; localObject3 = null)
              {
                a((t)localObject4, (Rect)localObject3, b, c);
                i1 -= 1;
                break;
              }
            }
          }
          i2 = t.b.size();
          i1 = 0;
          while (i1 < i2)
          {
            localObject2 = (t)t.b.keyAt(i1);
            localObject3 = (g)t.b.valueAt(i1);
            localObject4 = (g)t.a.get(localObject2);
            if ((localObject4 != null) && (localObject3 != null) && ((b != b) || (c != c)))
            {
              ((t)localObject2).a(false);
              if (s.a((t)localObject2, b, c, b, c)) {
                y();
              }
            }
            i1 += 1;
          }
          if (t.c != null)
          {
            i1 = t.c.size();
            i1 -= 1;
            if (i1 < 0) {
              break label1561;
            }
            l1 = ((Long)t.c.keyAt(i1)).longValue();
            localObject2 = (t)t.c.get(Long.valueOf(l1));
            localObject3 = a;
            if (!((t)localObject2).c()) {
              break label1515;
            }
          }
          for (;;)
          {
            i1 -= 1;
            break label1447;
            i1 = 0;
            break;
            if ((m.a(p) != null) && (m.a(p).contains(localObject2))) {
              a((t)localObject2, (t)((ArrayMap)localObject1).get(Long.valueOf(l1)));
            }
          }
          b(false);
          i.b(p);
          q.b(t, t.e);
          D = false;
          q.c(t, false);
          q.d(t, false);
          v();
          h.a(i, false);
          if (m.a(p) != null) {
            m.a(p).clear();
          }
          t.c = null;
          if (!h(ae[0], ae[1])) {
            break;
          }
          e(0, 0);
          return;
          localObject2 = null;
        }
      }
    }
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    int i1 = 0;
    if (i == null) {
      Log.e("RecyclerView", "Cannot smooth scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
    }
    for (;;)
    {
      return;
      if (!i.c()) {
        paramInt1 = 0;
      }
      if (!i.d()) {
        paramInt2 = i1;
      }
      while ((paramInt1 != 0) || (paramInt2 != 0))
      {
        U.b(paramInt1, paramInt2);
        return;
      }
    }
  }
  
  void a(View paramView, int paramInt) {}
  
  void a(String paramString)
  {
    if (n())
    {
      if (paramString == null) {
        throw new IllegalStateException("Cannot call this method while RecyclerView is computing a layout or scrolling");
      }
      throw new IllegalStateException(paramString);
    }
  }
  
  boolean a(int paramInt1, int paramInt2, MotionEvent paramMotionEvent)
  {
    int i2 = 0;
    int i7 = 0;
    int i4 = 0;
    int i5 = 0;
    int i1 = 0;
    int i8 = 0;
    int i3 = 0;
    int i6 = 0;
    b();
    if (h != null)
    {
      g();
      f();
      TraceCompat.beginSection("RV Scroll");
      i1 = i8;
      i2 = i7;
      if (paramInt1 != 0)
      {
        i1 = i.a(paramInt1, p, t);
        i2 = paramInt1 - i1;
      }
      i3 = i6;
      i4 = i5;
      if (paramInt2 != 0)
      {
        i3 = i.b(paramInt2, p, t);
        i4 = paramInt2 - i3;
      }
      TraceCompat.endSection();
      if (x())
      {
        i6 = r.b();
        i5 = 0;
        if (i5 < i6)
        {
          View localView = r.b(i5);
          Object localObject = a(localView);
          if ((localObject != null) && (h != null))
          {
            localObject = h;
            if (localObject == null) {
              break label274;
            }
          }
          label274:
          for (localObject = a;; localObject = null)
          {
            if (localObject != null)
            {
              i7 = localView.getLeft();
              i8 = localView.getTop();
              if ((i7 != ((View)localObject).getLeft()) || (i8 != ((View)localObject).getTop())) {
                ((View)localObject).layout(i7, i8, ((View)localObject).getWidth() + i7, ((View)localObject).getHeight() + i8);
              }
            }
            i5 += 1;
            break;
          }
        }
      }
      v();
      b(false);
    }
    if (!k.isEmpty()) {
      invalidate();
    }
    if (dispatchNestedScroll(i1, i3, i2, i4, ag))
    {
      O -= ag[0];
      P -= ag[1];
      if (paramMotionEvent != null) {
        paramMotionEvent.offsetLocation(ag[0], ag[1]);
      }
      paramMotionEvent = ai;
      paramMotionEvent[0] += ag[0];
      paramMotionEvent = ai;
      paramMotionEvent[1] += ag[1];
    }
    for (;;)
    {
      if ((i1 != 0) || (i3 != 0)) {
        e(i1, i3);
      }
      if (!awakenScrollBars()) {
        invalidate();
      }
      if ((i1 == 0) && (i3 == 0)) {
        break;
      }
      return true;
      if ((ViewCompat.getOverScrollMode(this) != 2) && ((ViewCompat.getOverScrollMode(this) != 1) || (!B())))
      {
        if (paramMotionEvent != null) {
          a(paramMotionEvent.getX(), i2, paramMotionEvent.getY(), i4);
        }
        f(paramInt1, paramInt2);
      }
    }
    return false;
  }
  
  boolean a(AccessibilityEvent paramAccessibilityEvent)
  {
    boolean bool = false;
    int i2 = 0;
    if (n()) {
      if (paramAccessibilityEvent == null) {
        break label46;
      }
    }
    label46:
    for (int i1 = AccessibilityEventCompat.getContentChangeTypes(paramAccessibilityEvent);; i1 = 0)
    {
      if (i1 == 0) {
        i1 = i2;
      }
      for (;;)
      {
        z = (i1 | z);
        bool = true;
        return bool;
      }
    }
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
  {
    if ((i == null) || (!i.a(this, paramArrayList, paramInt1, paramInt2))) {
      super.addFocusables(paramArrayList, paramInt1, paramInt2);
    }
  }
  
  public void b(int paramInt)
  {
    int i2 = r.b();
    int i1 = 0;
    while (i1 < i2)
    {
      r.b(i1).offsetTopAndBottom(paramInt);
      i1 += 1;
    }
  }
  
  void b(boolean paramBoolean)
  {
    if (x)
    {
      if ((paramBoolean) && (y) && (i != null) && (h != null)) {
        a();
      }
      x = false;
      y = false;
    }
  }
  
  public boolean b(int paramInt1, int paramInt2)
  {
    if (i == null) {
      Log.e("RecyclerView", "Cannot fling without a LayoutManager set. Call setLayoutManager with a non-null argument.");
    }
    boolean bool2;
    int i1;
    do
    {
      return false;
      bool1 = i.c();
      bool2 = i.d();
      if (bool1)
      {
        i1 = paramInt1;
        if (Math.abs(paramInt1) >= R) {}
      }
      else
      {
        i1 = 0;
      }
      if (bool2)
      {
        paramInt1 = paramInt2;
        if (Math.abs(paramInt2) >= R) {}
      }
      else
      {
        paramInt1 = 0;
      }
    } while (((i1 == 0) && (paramInt1 == 0)) || (dispatchNestedPreFling(i1, paramInt1)));
    if ((bool1) || (bool2)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      dispatchNestedFling(i1, paramInt1, bool1);
      if (!bool1) {
        break;
      }
      paramInt2 = Math.max(-S, Math.min(i1, S));
      paramInt1 = Math.max(-S, Math.min(paramInt1, S));
      U.a(paramInt2, paramInt1);
      return true;
    }
  }
  
  public int c(View paramView)
  {
    paramView = b(paramView);
    if (paramView != null) {
      return paramView.d();
    }
    return -1;
  }
  
  public void c(int paramInt)
  {
    int i2 = r.b();
    int i1 = 0;
    while (i1 < i2)
    {
      r.b(i1).offsetLeftAndRight(paramInt);
      i1 += 1;
    }
  }
  
  void c(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
    {
      i();
      F.onAbsorb(-paramInt1);
      if (paramInt2 >= 0) {
        break label69;
      }
      k();
      G.onAbsorb(-paramInt2);
    }
    for (;;)
    {
      if ((paramInt1 != 0) || (paramInt2 != 0)) {
        ViewCompat.postInvalidateOnAnimation(this);
      }
      return;
      if (paramInt1 <= 0) {
        break;
      }
      j();
      H.onAbsorb(paramInt1);
      break;
      label69:
      if (paramInt2 > 0)
      {
        l();
        I.onAbsorb(paramInt2);
      }
    }
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    return ((paramLayoutParams instanceof LayoutParams)) && (i.a((LayoutParams)paramLayoutParams));
  }
  
  public int computeHorizontalScrollExtent()
  {
    if (i.c()) {
      return i.d(t);
    }
    return 0;
  }
  
  public int computeHorizontalScrollOffset()
  {
    if (i.c()) {
      return i.b(t);
    }
    return 0;
  }
  
  public int computeHorizontalScrollRange()
  {
    if (i.c()) {
      return i.f(t);
    }
    return 0;
  }
  
  public int computeVerticalScrollExtent()
  {
    if (i.d()) {
      return i.e(t);
    }
    return 0;
  }
  
  public int computeVerticalScrollOffset()
  {
    if (i.d()) {
      return i.c(t);
    }
    return 0;
  }
  
  public int computeVerticalScrollRange()
  {
    if (i.d()) {
      return i.g(t);
    }
    return 0;
  }
  
  public void d(int paramInt) {}
  
  public void d(int paramInt1, int paramInt2) {}
  
  public void d(View paramView) {}
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean)
  {
    return af.dispatchNestedFling(paramFloat1, paramFloat2, paramBoolean);
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2)
  {
    return af.dispatchNestedPreFling(paramFloat1, paramFloat2);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return af.dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfInt1, paramArrayOfInt2);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt)
  {
    return af.dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt);
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchThawSelfOnly(paramSparseArray);
  }
  
  protected void dispatchSaveInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    dispatchFreezeSelfOnly(paramSparseArray);
  }
  
  public void draw(Canvas paramCanvas)
  {
    int i3 = 1;
    int i4 = 0;
    super.draw(paramCanvas);
    int i2 = k.size();
    int i1 = 0;
    while (i1 < i2)
    {
      ((f)k.get(i1)).b(paramCanvas, this, t);
      i1 += 1;
    }
    int i5;
    if ((F != null) && (!F.isFinished()))
    {
      i5 = paramCanvas.save();
      if (e)
      {
        i1 = getPaddingBottom();
        paramCanvas.rotate(270.0F);
        paramCanvas.translate(i1 + -getHeight(), 0.0F);
        if ((F == null) || (!F.draw(paramCanvas))) {
          break label456;
        }
        i2 = 1;
        label128:
        paramCanvas.restoreToCount(i5);
      }
    }
    for (;;)
    {
      i1 = i2;
      if (G != null)
      {
        i1 = i2;
        if (!G.isFinished())
        {
          i5 = paramCanvas.save();
          if (al < 0) {
            break label461;
          }
          paramCanvas.translate(getPaddingLeft(), al);
          label182:
          if ((G == null) || (!G.draw(paramCanvas))) {
            break label478;
          }
          i1 = 1;
          label202:
          i1 = i2 | i1;
          paramCanvas.restoreToCount(i5);
        }
      }
      i2 = i1;
      if (H != null)
      {
        i2 = i1;
        if (!H.isFinished())
        {
          i5 = paramCanvas.save();
          int i6 = getWidth();
          if (!e) {
            break label483;
          }
          i2 = getPaddingTop();
          label257:
          paramCanvas.rotate(90.0F);
          paramCanvas.translate(-i2, -i6);
          if ((H == null) || (!H.draw(paramCanvas))) {
            break label488;
          }
          i2 = 1;
          label295:
          i2 = i1 | i2;
          paramCanvas.restoreToCount(i5);
        }
      }
      i1 = i2;
      if (I != null)
      {
        i1 = i2;
        if (!I.isFinished())
        {
          i5 = paramCanvas.save();
          paramCanvas.rotate(180.0F);
          if (am < 0) {
            break label493;
          }
          paramCanvas.translate(-getWidth() + getPaddingRight(), -getHeight() + am);
          label372:
          i1 = i4;
          if (I != null)
          {
            i1 = i4;
            if (I.draw(paramCanvas)) {
              i1 = 1;
            }
          }
          i1 = i2 | i1;
          paramCanvas.restoreToCount(i5);
        }
      }
      if ((i1 == 0) && (s != null) && (k.size() > 0) && (s.c())) {
        i1 = i3;
      }
      for (;;)
      {
        if (i1 != 0) {
          ViewCompat.postInvalidateOnAnimation(this);
        }
        return;
        i1 = 0;
        break;
        label456:
        i2 = 0;
        break label128;
        label461:
        paramCanvas.translate(getPaddingLeft(), getPaddingTop());
        break label182;
        label478:
        i1 = 0;
        break label202;
        label483:
        i2 = 0;
        break label257;
        label488:
        i2 = 0;
        break label295;
        label493:
        paramCanvas.translate(-getWidth() + getPaddingRight(), -getHeight() + getPaddingBottom());
        break label372;
      }
      i2 = 0;
    }
  }
  
  public boolean drawChild(Canvas paramCanvas, View paramView, long paramLong)
  {
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  Rect e(View paramView)
  {
    LayoutParams localLayoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!c) {
      return b;
    }
    Rect localRect = b;
    localRect.set(0, 0, 0, 0);
    int i2 = k.size();
    int i1 = 0;
    while (i1 < i2)
    {
      g.set(0, 0, 0, 0);
      ((f)k.get(i1)).a(g, paramView, this, t);
      left += g.left;
      top += g.top;
      right += g.right;
      bottom += g.bottom;
      i1 += 1;
    }
    c = false;
    return localRect;
  }
  
  void e(int paramInt)
  {
    if (i != null) {
      i.h(paramInt);
    }
    d(paramInt);
    if (V != null) {
      V.a(this, paramInt);
    }
    if (W != null)
    {
      int i1 = W.size() - 1;
      while (i1 >= 0)
      {
        ((k)W.get(i1)).a(this, paramInt);
        i1 -= 1;
      }
    }
  }
  
  void e(int paramInt1, int paramInt2)
  {
    int i1 = getScrollX();
    int i2 = getScrollY();
    onScrollChanged(i1, i2, i1, i2);
    d(paramInt1, paramInt2);
    if (V != null) {
      V.a(this, paramInt1, paramInt2);
    }
    if (W != null)
    {
      i1 = W.size() - 1;
      while (i1 >= 0)
      {
        ((k)W.get(i1)).a(this, paramInt1, paramInt2);
        i1 -= 1;
      }
    }
  }
  
  public View focusSearch(View paramView, int paramInt)
  {
    Object localObject2 = i.d(paramView, paramInt);
    if (localObject2 != null) {}
    Object localObject1;
    do
    {
      return (View)localObject2;
      localObject2 = FocusFinder.getInstance().findNextFocus(this, paramView, paramInt);
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = localObject2;
        if (h != null)
        {
          localObject1 = localObject2;
          if (i != null)
          {
            localObject1 = localObject2;
            if (!n())
            {
              g();
              localObject1 = i.a(paramView, paramInt, p, t);
              b(false);
            }
          }
        }
      }
      localObject2 = localObject1;
    } while (localObject1 != null);
    return super.focusSearch(paramView, paramInt);
  }
  
  void g()
  {
    if (!x)
    {
      x = true;
      y = false;
    }
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams()
  {
    if (i == null) {
      throw new IllegalStateException("RecyclerView has no LayoutManager");
    }
    return i.a();
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet)
  {
    if (i == null) {
      throw new IllegalStateException("RecyclerView has no LayoutManager");
    }
    return i.a(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (i == null) {
      throw new IllegalStateException("RecyclerView has no LayoutManager");
    }
    return i.a(paramLayoutParams);
  }
  
  public a getAdapter()
  {
    return h;
  }
  
  public int getBaseline()
  {
    if (i != null) {
      return i.q();
    }
    return super.getBaseline();
  }
  
  protected int getChildDrawingOrder(int paramInt1, int paramInt2)
  {
    if (ad == null) {
      return super.getChildDrawingOrder(paramInt1, paramInt2);
    }
    return ad.a(paramInt1, paramInt2);
  }
  
  public arq getCompatAccessibilityDelegate()
  {
    return ac;
  }
  
  public e getItemAnimator()
  {
    return s;
  }
  
  public h getLayoutManager()
  {
    return i;
  }
  
  public int getMaxFlingVelocity()
  {
    return S;
  }
  
  public int getMinFlingVelocity()
  {
    return R;
  }
  
  public l getRecycledViewPool()
  {
    return p.f();
  }
  
  public int getScrollState()
  {
    return J;
  }
  
  public void h()
  {
    setScrollState(0);
    c();
  }
  
  public boolean hasNestedScrollingParent()
  {
    return af.hasNestedScrollingParent();
  }
  
  void i()
  {
    if (F != null) {
      return;
    }
    F = new EdgeEffectCompat(getContext());
    if (e)
    {
      F.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
      return;
    }
    F.setSize(getMeasuredHeight(), getMeasuredWidth());
  }
  
  public boolean isAttachedToWindow()
  {
    return n;
  }
  
  public boolean isNestedScrollingEnabled()
  {
    return af.isNestedScrollingEnabled();
  }
  
  void j()
  {
    if (H != null) {
      return;
    }
    H = new EdgeEffectCompat(getContext());
    if (e)
    {
      H.setSize(getMeasuredHeight() - getPaddingTop() - getPaddingBottom(), getMeasuredWidth() - getPaddingLeft() - getPaddingRight());
      return;
    }
    H.setSize(getMeasuredHeight(), getMeasuredWidth());
  }
  
  void k()
  {
    if (G != null) {
      return;
    }
    G = new EdgeEffectCompat(getContext());
    if (e)
    {
      G.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
      return;
    }
    G.setSize(getMeasuredWidth(), getMeasuredHeight());
  }
  
  void l()
  {
    if (I != null) {
      return;
    }
    I = new EdgeEffectCompat(getContext());
    if (e)
    {
      I.setSize(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(), getMeasuredHeight() - getPaddingTop() - getPaddingBottom());
      return;
    }
    I.setSize(getMeasuredWidth(), getMeasuredHeight());
  }
  
  void m()
  {
    I = null;
    G = null;
    H = null;
    F = null;
  }
  
  public boolean n()
  {
    return E > 0;
  }
  
  void o()
  {
    int i2 = r.c();
    int i1 = 0;
    while (i1 < i2)
    {
      r.c(i1).getLayoutParams()).c = true;
      i1 += 1;
    }
    p.i();
  }
  
  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    E = 0;
    n = true;
    w = false;
    if (i != null) {
      i.b(this);
    }
    ab = false;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (s != null) {
      s.b();
    }
    w = false;
    h();
    n = false;
    if (i != null) {
      i.b(this, p);
    }
    removeCallbacks(aj);
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i2 = k.size();
    int i1 = 0;
    while (i1 < i2)
    {
      ((f)k.get(i1)).a(paramCanvas, this, t);
      i1 += 1;
    }
  }
  
  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    if (i == null) {}
    label103:
    label106:
    for (;;)
    {
      return false;
      if (((MotionEventCompat.getSource(paramMotionEvent) & 0x2) != 0) && (paramMotionEvent.getAction() == 8))
      {
        float f1;
        if (i.d())
        {
          f1 = -MotionEventCompat.getAxisValue(paramMotionEvent, 9);
          if (!i.c()) {
            break label103;
          }
        }
        for (float f2 = MotionEventCompat.getAxisValue(paramMotionEvent, 10);; f2 = 0.0F)
        {
          if ((f1 == 0.0F) && (f2 == 0.0F)) {
            break label106;
          }
          float f3 = getScrollFactor();
          a((int)(f2 * f3), (int)(f1 * f3), paramMotionEvent);
          return false;
          f1 = 0.0F;
          break;
        }
      }
    }
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    int i3 = -1;
    if (a(paramMotionEvent))
    {
      e();
      return true;
    }
    if (i == null) {
      return false;
    }
    boolean bool1 = i.c();
    boolean bool2 = i.d();
    if (L == null) {
      L = VelocityTracker.obtain();
    }
    L.addMovement(paramMotionEvent);
    int i2 = MotionEventCompat.getActionMasked(paramMotionEvent);
    int i1 = MotionEventCompat.getActionIndex(paramMotionEvent);
    switch (i2)
    {
    case 4: 
    default: 
      if (J != 1) {
        return false;
      }
      break;
    case 0: 
      label120:
      K = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      i1 = (int)(paramMotionEvent.getX() + 0.5F);
      O = i1;
      M = i1;
      i1 = (int)(paramMotionEvent.getY() + 0.5F);
      P = i1;
      N = i1;
      if (J == 2)
      {
        paramMotionEvent = getParent();
        if (paramMotionEvent != null) {
          paramMotionEvent.requestDisallowInterceptTouchEvent(true);
        }
        setScrollState(1);
      }
      if (!bool1) {}
      break;
    }
    for (i1 = 1;; i1 = 0)
    {
      i2 = i1;
      if (bool2) {
        i2 = i1 | 0x2;
      }
      startNestedScroll(i2);
      break label120;
      K = MotionEventCompat.getPointerId(paramMotionEvent, i1);
      i2 = (int)(MotionEventCompat.getX(paramMotionEvent, i1) + 0.5F);
      O = i2;
      M = i2;
      i1 = (int)(MotionEventCompat.getY(paramMotionEvent, i1) + 0.5F);
      P = i1;
      N = i1;
      break label120;
      i2 = MotionEventCompat.findPointerIndex(paramMotionEvent, K);
      if (i2 < 0)
      {
        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + K + " not found. Did any MotionEvents get skipped?");
        return false;
      }
      i1 = (int)(MotionEventCompat.getX(paramMotionEvent, i2) + 0.5F);
      i2 = (int)(MotionEventCompat.getY(paramMotionEvent, i2) + 0.5F);
      if (J == 1) {
        break label120;
      }
      i1 -= M;
      int i4 = i2 - N;
      int i5;
      if ((bool1) && (Math.abs(i1) > Q))
      {
        i2 = M;
        i5 = Q;
        if (i1 < 0)
        {
          i1 = -1;
          label418:
          O = (i1 * i5 + i2);
        }
      }
      for (i1 = 1;; i1 = 0)
      {
        i2 = i1;
        if (bool2)
        {
          i2 = i1;
          if (Math.abs(i4) > Q)
          {
            i2 = N;
            i5 = Q;
            if (i4 >= 0) {
              break label499;
            }
          }
        }
        label499:
        for (i1 = i3;; i1 = 1)
        {
          P = (i2 + i1 * i5);
          i2 = 1;
          if (i2 == 0) {
            break;
          }
          setScrollState(1);
          break;
          i1 = 1;
          break label418;
        }
        c(paramMotionEvent);
        break label120;
        L.clear();
        stopNestedScroll();
        break label120;
        e();
        break label120;
        break;
      }
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    g();
    TraceCompat.beginSection("RV OnLayout");
    a();
    TraceCompat.endSection();
    b(false);
    w = true;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (A)
    {
      g();
      A();
      if (q.a(t))
      {
        q.b(t, true);
        A = false;
        b(false);
      }
    }
    else
    {
      if (h == null) {
        break label107;
      }
      t.e = h.a();
      label65:
      if (i != null) {
        break label118;
      }
      g(paramInt1, paramInt2);
    }
    for (;;)
    {
      q.b(t, false);
      return;
      q.e();
      q.b(t, false);
      break;
      label107:
      t.e = 0;
      break label65;
      label118:
      i.a(p, t, paramInt1, paramInt2);
    }
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    d = ((SavedState)paramParcelable);
    super.onRestoreInstanceState(d.getSuperState());
    if ((i != null) && (d.a != null)) {
      i.a(d.a);
    }
  }
  
  protected Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if (d != null)
    {
      SavedState.a(localSavedState, d);
      return localSavedState;
    }
    if (i != null)
    {
      a = i.b();
      return localSavedState;
    }
    a = null;
    return localSavedState;
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if ((paramInt1 != paramInt3) || (paramInt2 != paramInt4)) {
      m();
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool1 = false;
    if (b(paramMotionEvent))
    {
      e();
      bool1 = true;
    }
    while (i == null) {
      return bool1;
    }
    bool1 = i.c();
    boolean bool2 = i.d();
    if (L == null) {
      L = VelocityTracker.obtain();
    }
    L.addMovement(paramMotionEvent);
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    int i2 = MotionEventCompat.getActionMasked(paramMotionEvent);
    int i1 = MotionEventCompat.getActionIndex(paramMotionEvent);
    if (i2 == 0)
    {
      int[] arrayOfInt = ai;
      ai[1] = 0;
      arrayOfInt[0] = 0;
    }
    localMotionEvent.offsetLocation(ai[0], ai[1]);
    switch (i2)
    {
    case 4: 
    default: 
      localMotionEvent.recycle();
      return true;
    case 0: 
      K = MotionEventCompat.getPointerId(paramMotionEvent, 0);
      i1 = (int)(paramMotionEvent.getX() + 0.5F);
      O = i1;
      M = i1;
      i1 = (int)(paramMotionEvent.getY() + 0.5F);
      P = i1;
      N = i1;
      if (!bool1) {
        break;
      }
    }
    for (i1 = 1;; i1 = 0)
    {
      i2 = i1;
      if (bool2) {
        i2 = i1 | 0x2;
      }
      startNestedScroll(i2);
      break;
      K = MotionEventCompat.getPointerId(paramMotionEvent, i1);
      i2 = (int)(MotionEventCompat.getX(paramMotionEvent, i1) + 0.5F);
      O = i2;
      M = i2;
      i1 = (int)(MotionEventCompat.getY(paramMotionEvent, i1) + 0.5F);
      P = i1;
      N = i1;
      break;
      i1 = MotionEventCompat.findPointerIndex(paramMotionEvent, K);
      if (i1 < 0)
      {
        Log.e("RecyclerView", "Error processing scroll; pointer index for id " + K + " not found. Did any MotionEvents get skipped?");
        return false;
      }
      int i7 = (int)(MotionEventCompat.getX(paramMotionEvent, i1) + 0.5F);
      int i8 = (int)(MotionEventCompat.getY(paramMotionEvent, i1) + 0.5F);
      int i4 = O - i7;
      int i3 = P - i8;
      i1 = i3;
      i2 = i4;
      if (dispatchNestedPreScroll(i4, i3, ah, ag))
      {
        i2 = i4 - ah[0];
        i1 = i3 - ah[1];
        localMotionEvent.offsetLocation(ag[0], ag[1]);
        paramMotionEvent = ai;
        paramMotionEvent[0] += ag[0];
        paramMotionEvent = ai;
        paramMotionEvent[1] += ag[1];
      }
      i3 = i1;
      int i5 = i2;
      if (J != 1)
      {
        if ((!bool1) || (Math.abs(i2) <= Q)) {
          break label888;
        }
        if (i2 <= 0) {
          break label734;
        }
        i2 -= Q;
      }
      label578:
      label628:
      label703:
      label734:
      label746:
      label764:
      label811:
      label876:
      label888:
      for (i3 = 1;; i3 = 0)
      {
        i4 = i1;
        int i6 = i3;
        if (bool2)
        {
          i4 = i1;
          i6 = i3;
          if (Math.abs(i1) > Q)
          {
            if (i1 <= 0) {
              break label746;
            }
            i4 = i1 - Q;
            i6 = 1;
          }
        }
        i3 = i4;
        i5 = i2;
        if (i6 != 0)
        {
          setScrollState(1);
          i5 = i2;
          i3 = i4;
        }
        if (J != 1) {
          break;
        }
        O = (i7 - ag[0]);
        P = (i8 - ag[1]);
        if (getParent() == null) {
          break;
        }
        if (bool1) {
          if (!bool2) {
            break label764;
          }
        }
        while (a(i5, i3, localMotionEvent))
        {
          getParent().requestDisallowInterceptTouchEvent(true);
          break;
          i2 += Q;
          break label578;
          i4 = i1 + Q;
          break label628;
          i5 = 0;
          break label703;
          i3 = 0;
        }
        c(paramMotionEvent);
        break;
        L.computeCurrentVelocity(1000, S);
        float f1;
        if (bool1)
        {
          f1 = -VelocityTrackerCompat.getXVelocity(L, K);
          if (!bool2) {
            break label876;
          }
        }
        for (float f2 = -VelocityTrackerCompat.getYVelocity(L, K);; f2 = 0.0F)
        {
          if (((f1 == 0.0F) && (f2 == 0.0F)) || (!b((int)f1, (int)f2))) {
            setScrollState(0);
          }
          L.clear();
          d();
          break;
          f1 = 0.0F;
          break label811;
        }
        e();
        break;
      }
    }
  }
  
  void p()
  {
    int i2 = r.c();
    int i1 = 0;
    while (i1 < i2)
    {
      t localt = b(r.c(i1));
      if (!localt.c()) {
        localt.b();
      }
      i1 += 1;
    }
  }
  
  void q()
  {
    int i2 = r.c();
    int i1 = 0;
    while (i1 < i2)
    {
      t localt = b(r.c(i1));
      if (!localt.c()) {
        localt.a();
      }
      i1 += 1;
    }
    p.h();
  }
  
  void r()
  {
    int i2 = r.c();
    int i1 = 0;
    while (i1 < i2)
    {
      t localt = b(r.c(i1));
      if ((localt != null) && (!localt.c())) {
        localt.b(6);
      }
      i1 += 1;
    }
    o();
    p.g();
  }
  
  protected void removeDetachedView(View paramView, boolean paramBoolean)
  {
    t localt = b(paramView);
    if (localt != null)
    {
      if (!localt.q()) {
        break label32;
      }
      localt.k();
    }
    label32:
    while (localt.c())
    {
      f(paramView);
      super.removeDetachedView(paramView, paramBoolean);
      return;
    }
    throw new IllegalArgumentException("Called removeDetachedView with a view which is not flagged as tmp detached." + localt);
  }
  
  public void requestChildFocus(View paramView1, View paramView2)
  {
    Object localObject;
    if ((!i.a(this, t, paramView1, paramView2)) && (paramView2 != null))
    {
      g.set(0, 0, paramView2.getWidth(), paramView2.getHeight());
      localObject = paramView2.getLayoutParams();
      if ((localObject instanceof LayoutParams))
      {
        localObject = (LayoutParams)localObject;
        if (!c)
        {
          localObject = b;
          Rect localRect = g;
          left -= left;
          localRect = g;
          right += right;
          localRect = g;
          top -= top;
          localRect = g;
          int i1 = bottom;
          bottom = (bottom + i1);
        }
      }
      offsetDescendantRectToMyCoords(paramView2, g);
      offsetRectIntoDescendantCoords(paramView1, g);
      localObject = g;
      if (w) {
        break label215;
      }
    }
    label215:
    for (boolean bool = true;; bool = false)
    {
      requestChildRectangleOnScreen(paramView1, (Rect)localObject, bool);
      super.requestChildFocus(paramView1, paramView2);
      return;
    }
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean)
  {
    return i.a(this, paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean)
  {
    int i2 = l.size();
    int i1 = 0;
    while (i1 < i2)
    {
      ((j)l.get(i1)).a(paramBoolean);
      i1 += 1;
    }
    super.requestDisallowInterceptTouchEvent(paramBoolean);
  }
  
  public void requestLayout()
  {
    if (!x)
    {
      super.requestLayout();
      return;
    }
    y = true;
  }
  
  public boolean s()
  {
    return (!w) || (D) || (q.d());
  }
  
  public void scrollBy(int paramInt1, int paramInt2)
  {
    if (i == null) {
      Log.e("RecyclerView", "Cannot scroll without a LayoutManager set. Call setLayoutManager with a non-null argument.");
    }
    boolean bool1;
    boolean bool2;
    do
    {
      return;
      bool1 = i.c();
      bool2 = i.d();
    } while ((!bool1) && (!bool2));
    if (bool1) {
      if (!bool2) {
        break label67;
      }
    }
    for (;;)
    {
      a(paramInt1, paramInt2, null);
      return;
      paramInt1 = 0;
      break;
      label67:
      paramInt2 = 0;
    }
  }
  
  public void scrollTo(int paramInt1, int paramInt2)
  {
    throw new UnsupportedOperationException("RecyclerView does not support scrolling to an absolute position.");
  }
  
  public void sendAccessibilityEventUnchecked(AccessibilityEvent paramAccessibilityEvent)
  {
    if (a(paramAccessibilityEvent)) {
      return;
    }
    super.sendAccessibilityEventUnchecked(paramAccessibilityEvent);
  }
  
  public void setAccessibilityDelegateCompat(arq paramarq)
  {
    ac = paramarq;
    ViewCompat.setAccessibilityDelegate(this, ac);
  }
  
  public void setAdapter(a parama)
  {
    a(parama, false, true);
    requestLayout();
  }
  
  public void setChildDrawingOrderCallback(d paramd)
  {
    if (paramd == ad) {
      return;
    }
    ad = paramd;
    if (ad != null) {}
    for (boolean bool = true;; bool = false)
    {
      setChildrenDrawingOrderEnabled(bool);
      return;
    }
  }
  
  public void setClipToPadding(boolean paramBoolean)
  {
    if (paramBoolean != e) {
      m();
    }
    e = paramBoolean;
    super.setClipToPadding(paramBoolean);
    if (w) {
      requestLayout();
    }
  }
  
  public void setHasFixedSize(boolean paramBoolean)
  {
    o = paramBoolean;
  }
  
  public void setItemAnimator(e parame)
  {
    if (s != null)
    {
      s.b();
      s.a(null);
    }
    s = parame;
    if (s != null) {
      s.a(aa);
    }
  }
  
  public void setItemViewCacheSize(int paramInt)
  {
    p.a(paramInt);
  }
  
  public void setLayoutManager(h paramh)
  {
    if (paramh == i) {
      return;
    }
    if (i != null)
    {
      if (n) {
        i.b(this, p);
      }
      i.a(null);
    }
    p.a();
    r.a();
    i = paramh;
    if (paramh != null)
    {
      if (i != null) {
        throw new IllegalArgumentException("LayoutManager " + paramh + " is already attached to a RecyclerView: " + i);
      }
      i.a(this);
      if (n) {
        i.b(this);
      }
    }
    requestLayout();
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean)
  {
    af.setNestedScrollingEnabled(paramBoolean);
  }
  
  @Deprecated
  public void setOnScrollListener(k paramk)
  {
    V = paramk;
  }
  
  public void setRecycledViewPool(l paraml)
  {
    p.a(paraml);
  }
  
  public void setRecyclerListener(n paramn)
  {
    j = paramn;
  }
  
  protected void setScrollState(int paramInt)
  {
    if (paramInt == J) {
      return;
    }
    J = paramInt;
    if (paramInt != 2) {
      c();
    }
    e(paramInt);
  }
  
  public void setScrollingTouchSlop(int paramInt)
  {
    ViewConfiguration localViewConfiguration = ViewConfiguration.get(getContext());
    switch (paramInt)
    {
    default: 
      Log.w("RecyclerView", "setScrollingTouchSlop(): bad argument constant " + paramInt + "; using default value");
    case 0: 
      Q = localViewConfiguration.getScaledTouchSlop();
      return;
    }
    Q = ViewConfigurationCompat.getScaledPagingTouchSlop(localViewConfiguration);
  }
  
  public void setViewCacheExtension(r paramr)
  {
    p.a(paramr);
  }
  
  public boolean startNestedScroll(int paramInt)
  {
    return af.startNestedScroll(paramInt);
  }
  
  public void stopNestedScroll()
  {
    af.stopNestedScroll();
  }
  
  public static class LayoutParams
    extends ViewGroup.MarginLayoutParams
  {
    RecyclerView.t a;
    final Rect b = new Rect();
    boolean c = true;
    boolean d = false;
    
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
    
    public LayoutParams(LayoutParams paramLayoutParams)
    {
      super();
    }
    
    public boolean a()
    {
      return a.p();
    }
    
    public boolean b()
    {
      return a.n();
    }
    
    public int c()
    {
      return a.d();
    }
  }
  
  public static class SavedState
    extends View.BaseSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new arp();
    Parcelable a;
    
    public SavedState(Parcel paramParcel)
    {
      super();
      a = paramParcel.readParcelable(RecyclerView.h.class.getClassLoader());
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    private void a(SavedState paramSavedState)
    {
      a = a;
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      super.writeToParcel(paramParcel, paramInt);
      paramParcel.writeParcelable(a, 0);
    }
  }
  
  public static abstract class a<VH extends RecyclerView.t>
  {
    private final RecyclerView.b a;
    private boolean b;
    
    public abstract int a();
    
    public int a(int paramInt)
    {
      return 0;
    }
    
    public abstract VH a(ViewGroup paramViewGroup, int paramInt);
    
    public void a(RecyclerView.c paramc)
    {
      a.registerObserver(paramc);
    }
    
    public void a(VH paramVH) {}
    
    public abstract void a(VH paramVH, int paramInt);
    
    public void a(RecyclerView paramRecyclerView) {}
    
    public long b(int paramInt)
    {
      return -1L;
    }
    
    public final VH b(ViewGroup paramViewGroup, int paramInt)
    {
      TraceCompat.beginSection("RV CreateView");
      paramViewGroup = a(paramViewGroup, paramInt);
      e = paramInt;
      TraceCompat.endSection();
      return paramViewGroup;
    }
    
    public void b(RecyclerView.c paramc)
    {
      a.unregisterObserver(paramc);
    }
    
    public final void b(VH paramVH, int paramInt)
    {
      b = paramInt;
      if (b()) {
        d = b(paramInt);
      }
      paramVH.a(1, 519);
      TraceCompat.beginSection("RV OnBindView");
      a(paramVH, paramInt);
      TraceCompat.endSection();
    }
    
    public void b(RecyclerView paramRecyclerView) {}
    
    public final boolean b()
    {
      return b;
    }
    
    public boolean b(VH paramVH)
    {
      return false;
    }
    
    public void c(VH paramVH) {}
  }
  
  static class b
    extends Observable<RecyclerView.c>
  {}
  
  public static abstract class c {}
  
  public static abstract interface d
  {
    public abstract int a(int paramInt1, int paramInt2);
  }
  
  public static abstract class e
  {
    private a a;
    private boolean b;
    
    void a(a parama)
    {
      a = parama;
    }
    
    public boolean a()
    {
      return b;
    }
    
    public abstract boolean a(RecyclerView.t paramt);
    
    public abstract boolean a(RecyclerView.t paramt, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract boolean a(RecyclerView.t paramt1, RecyclerView.t paramt2, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
    
    public abstract void b();
    
    public abstract boolean b(RecyclerView.t paramt);
    
    public abstract void c(RecyclerView.t paramt);
    
    public abstract boolean c();
    
    static abstract interface a {}
  }
  
  public static abstract class f
  {
    @Deprecated
    public void a(Canvas paramCanvas, RecyclerView paramRecyclerView) {}
    
    public void a(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.q paramq)
    {
      a(paramCanvas, paramRecyclerView);
    }
    
    @Deprecated
    public void a(Rect paramRect, int paramInt, RecyclerView paramRecyclerView)
    {
      paramRect.set(0, 0, 0, 0);
    }
    
    public void a(Rect paramRect, View paramView, RecyclerView paramRecyclerView, RecyclerView.q paramq)
    {
      a(paramRect, ((RecyclerView.LayoutParams)paramView.getLayoutParams()).c(), paramRecyclerView);
    }
    
    @Deprecated
    public void b(Canvas paramCanvas, RecyclerView paramRecyclerView) {}
    
    public void b(Canvas paramCanvas, RecyclerView paramRecyclerView, RecyclerView.q paramq)
    {
      b(paramCanvas, paramRecyclerView);
    }
  }
  
  static class g
  {
    RecyclerView.t a;
    int b;
    int c;
    int d;
    int e;
    
    g(RecyclerView.t paramt, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      a = paramt;
      b = paramInt1;
      c = paramInt2;
      d = paramInt3;
      e = paramInt4;
    }
  }
  
  public static abstract class h
  {
    private boolean a = false;
    private boolean b = false;
    ard h;
    RecyclerView i;
    @Nullable
    RecyclerView.p j;
    
    public static int a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      int m = 1073741824;
      int k = Math.max(0, paramInt1 - paramInt2);
      if (paramBoolean) {
        if (paramInt3 >= 0)
        {
          paramInt1 = paramInt3;
          paramInt2 = m;
        }
      }
      for (;;)
      {
        return View.MeasureSpec.makeMeasureSpec(paramInt1, paramInt2);
        paramInt2 = 0;
        paramInt1 = 0;
        continue;
        paramInt2 = m;
        paramInt1 = paramInt3;
        if (paramInt3 < 0) {
          if (paramInt3 == -1)
          {
            paramInt1 = k;
            paramInt2 = m;
          }
          else if (paramInt3 == -2)
          {
            paramInt2 = Integer.MIN_VALUE;
            paramInt1 = k;
          }
          else
          {
            paramInt2 = 0;
            paramInt1 = 0;
          }
        }
      }
    }
    
    private void a(int paramInt, View paramView)
    {
      h.d(paramInt);
    }
    
    private void a(View paramView, int paramInt, boolean paramBoolean)
    {
      RecyclerView.t localt = RecyclerView.b(paramView);
      RecyclerView.LayoutParams localLayoutParams;
      if ((paramBoolean) || (localt.p()))
      {
        i.t.b(paramView);
        localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
        if ((!localt.i()) && (!localt.g())) {
          break label126;
        }
        if (!localt.g()) {
          break label118;
        }
        localt.h();
        label67:
        h.a(paramView, paramInt, paramView.getLayoutParams(), false);
      }
      for (;;)
      {
        if (d)
        {
          a.invalidate();
          d = false;
        }
        return;
        i.t.a(paramView);
        break;
        label118:
        localt.j();
        break label67;
        label126:
        if (paramView.getParent() == i)
        {
          int m = h.b(paramView);
          int k = paramInt;
          if (paramInt == -1) {
            k = h.b();
          }
          if (m == -1) {
            throw new IllegalStateException("Added View has RecyclerView as parent but view is not a real child. Unfiltered index:" + i.indexOfChild(paramView));
          }
          if (m != k) {
            RecyclerView.a(i).a(m, k);
          }
        }
        else
        {
          h.a(paramView, paramInt, false);
          c = true;
          if ((j != null) && (j.c())) {
            j.b(paramView);
          }
        }
      }
    }
    
    private void a(RecyclerView.m paramm, int paramInt, View paramView)
    {
      RecyclerView.t localt = RecyclerView.b(paramView);
      if (localt.c()) {
        return;
      }
      if ((localt.l()) && (!localt.p()) && (!localt.n()) && (!RecyclerView.c(i).b()))
      {
        c(paramInt);
        paramm.b(localt);
        return;
      }
      d(paramInt);
      paramm.c(paramView);
    }
    
    private void a(RecyclerView.p paramp)
    {
      if (j == paramp) {
        j = null;
      }
    }
    
    public void A()
    {
      a = true;
    }
    
    public int a(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
    {
      return 0;
    }
    
    public View a(int paramInt)
    {
      int m = r();
      int k = 0;
      if (k < m)
      {
        View localView = e(k);
        RecyclerView.t localt = RecyclerView.b(localView);
        if (localt == null) {}
        while ((localt.d() != paramInt) || (localt.c()) || ((!i.t.a()) && (localt.p())))
        {
          k += 1;
          break;
        }
        return localView;
      }
      return null;
    }
    
    @Nullable
    public View a(View paramView, int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
    {
      return null;
    }
    
    public abstract RecyclerView.LayoutParams a();
    
    public RecyclerView.LayoutParams a(Context paramContext, AttributeSet paramAttributeSet)
    {
      return new RecyclerView.LayoutParams(paramContext, paramAttributeSet);
    }
    
    public RecyclerView.LayoutParams a(ViewGroup.LayoutParams paramLayoutParams)
    {
      if ((paramLayoutParams instanceof RecyclerView.LayoutParams)) {
        return new RecyclerView.LayoutParams((RecyclerView.LayoutParams)paramLayoutParams);
      }
      if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams)) {
        return new RecyclerView.LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
      }
      return new RecyclerView.LayoutParams(paramLayoutParams);
    }
    
    public void a(int paramInt1, int paramInt2)
    {
      View localView = e(paramInt1);
      if (localView == null) {
        throw new IllegalArgumentException("Cannot move a child from non-existing index:" + paramInt1);
      }
      d(paramInt1);
      c(localView, paramInt2);
    }
    
    public void a(int paramInt, RecyclerView.m paramm)
    {
      View localView = e(paramInt);
      c(paramInt);
      paramm.a(localView);
    }
    
    public void a(Parcelable paramParcelable) {}
    
    public void a(AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      a(i.p, i.t, paramAccessibilityNodeInfoCompat);
    }
    
    public void a(View paramView)
    {
      a(paramView, -1);
    }
    
    public void a(View paramView, int paramInt)
    {
      a(paramView, paramInt, true);
    }
    
    public void a(View paramView, int paramInt1, int paramInt2)
    {
      RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
      Rect localRect = i.e(paramView);
      int k = left;
      int m = right;
      int n = top;
      int i1 = bottom;
      paramView.measure(a(s(), k + m + paramInt1 + (u() + w() + leftMargin + rightMargin), width, c()), a(t(), i1 + n + paramInt2 + (v() + x() + topMargin + bottomMargin), height, d()));
    }
    
    public void a(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      Rect localRect = getLayoutParamsb;
      paramView.layout(left + paramInt1, top + paramInt2, paramInt3 - right, paramInt4 - bottom);
    }
    
    public void a(View paramView, int paramInt, RecyclerView.LayoutParams paramLayoutParams)
    {
      RecyclerView.t localt = RecyclerView.b(paramView);
      if (localt.p()) {
        i.t.b(paramView);
      }
      for (;;)
      {
        h.a(paramView, paramInt, paramLayoutParams, localt.p());
        return;
        i.t.a(paramView);
      }
    }
    
    public void a(View paramView, Rect paramRect)
    {
      if (i == null)
      {
        paramRect.set(0, 0, 0, 0);
        return;
      }
      paramRect.set(i.e(paramView));
    }
    
    public void a(View paramView, RecyclerView.m paramm)
    {
      c(paramView);
      paramm.a(paramView);
    }
    
    public void a(AccessibilityEvent paramAccessibilityEvent)
    {
      a(i.p, i.t, paramAccessibilityEvent);
    }
    
    public void a(RecyclerView.a parama1, RecyclerView.a parama2) {}
    
    public void a(RecyclerView.m paramm)
    {
      int k = r() - 1;
      while (k >= 0)
      {
        a(paramm, k, e(k));
        k -= 1;
      }
    }
    
    public void a(RecyclerView.m paramm, RecyclerView.q paramq)
    {
      Log.e("RecyclerView", "You must override onLayoutChildren(Recycler recycler, State state) ");
    }
    
    public void a(RecyclerView.m paramm, RecyclerView.q paramq, int paramInt1, int paramInt2)
    {
      RecyclerView.b(i, paramInt1, paramInt2);
    }
    
    public void a(RecyclerView.m paramm, RecyclerView.q paramq, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      if ((ViewCompat.canScrollVertically(i, -1)) || (ViewCompat.canScrollHorizontally(i, -1)))
      {
        paramAccessibilityNodeInfoCompat.addAction(8192);
        paramAccessibilityNodeInfoCompat.setScrollable(true);
      }
      if ((ViewCompat.canScrollVertically(i, 1)) || (ViewCompat.canScrollHorizontally(i, 1)))
      {
        paramAccessibilityNodeInfoCompat.addAction(4096);
        paramAccessibilityNodeInfoCompat.setScrollable(true);
      }
      paramAccessibilityNodeInfoCompat.setCollectionInfo(AccessibilityNodeInfoCompat.CollectionInfoCompat.obtain(c(paramm, paramq), d(paramm, paramq), e(paramm, paramq), b(paramm, paramq)));
    }
    
    public void a(RecyclerView.m paramm, RecyclerView.q paramq, AccessibilityEvent paramAccessibilityEvent)
    {
      boolean bool2 = true;
      paramm = AccessibilityEventCompat.asRecord(paramAccessibilityEvent);
      if ((i == null) || (paramm == null)) {
        return;
      }
      boolean bool1 = bool2;
      if (!ViewCompat.canScrollVertically(i, 1))
      {
        bool1 = bool2;
        if (!ViewCompat.canScrollVertically(i, -1))
        {
          bool1 = bool2;
          if (!ViewCompat.canScrollHorizontally(i, -1)) {
            if (!ViewCompat.canScrollHorizontally(i, 1)) {
              break label111;
            }
          }
        }
      }
      label111:
      for (bool1 = bool2;; bool1 = false)
      {
        paramm.setScrollable(bool1);
        if (RecyclerView.c(i) == null) {
          break;
        }
        paramm.setItemCount(RecyclerView.c(i).a());
        return;
      }
    }
    
    void a(RecyclerView paramRecyclerView)
    {
      if (paramRecyclerView == null)
      {
        i = null;
        h = null;
        return;
      }
      i = paramRecyclerView;
      h = r;
    }
    
    public void a(RecyclerView paramRecyclerView, RecyclerView.m paramm)
    {
      d(paramRecyclerView);
    }
    
    public void a(Runnable paramRunnable)
    {
      if (i != null) {
        ViewCompat.postOnAnimation(i, paramRunnable);
      }
    }
    
    public void a(String paramString)
    {
      if (i != null) {
        i.a(paramString);
      }
    }
    
    public boolean a(int paramInt, Bundle paramBundle)
    {
      return a(i.p, i.t, paramInt, paramBundle);
    }
    
    public boolean a(RecyclerView.LayoutParams paramLayoutParams)
    {
      return paramLayoutParams != null;
    }
    
    public boolean a(RecyclerView.m paramm, RecyclerView.q paramq, int paramInt, Bundle paramBundle)
    {
      if (i == null) {}
      int k;
      do
      {
        return false;
        switch (paramInt)
        {
        default: 
          paramInt = 0;
          k = 0;
        }
      } while ((k == 0) && (paramInt == 0));
      i.scrollBy(paramInt, k);
      return true;
      if (ViewCompat.canScrollVertically(i, -1)) {}
      for (paramInt = -(t() - v() - x());; paramInt = 0)
      {
        k = paramInt;
        int m;
        if (ViewCompat.canScrollHorizontally(i, -1))
        {
          m = -(s() - u() - w());
          k = paramInt;
          paramInt = m;
          break;
          if (!ViewCompat.canScrollVertically(i, 1)) {
            break label207;
          }
        }
        label207:
        for (paramInt = t() - v() - x();; paramInt = 0)
        {
          k = paramInt;
          if (ViewCompat.canScrollHorizontally(i, 1))
          {
            m = s();
            int n = u();
            int i1 = w();
            k = paramInt;
            paramInt = m - n - i1;
            break;
          }
          paramInt = 0;
          break;
        }
      }
    }
    
    public boolean a(RecyclerView paramRecyclerView, View paramView, Rect paramRect, boolean paramBoolean)
    {
      int i4 = u();
      int i1 = v();
      int i5 = s() - w();
      int i3 = t();
      int i8 = x();
      int i6 = paramView.getLeft() + left;
      int i2 = paramView.getTop() + top;
      int i7 = i6 + paramRect.width();
      int i9 = paramRect.height();
      int k = Math.min(0, i6 - i4);
      int m = Math.min(0, i2 - i1);
      int n = Math.max(0, i7 - i5);
      i3 = Math.max(0, i2 + i9 - (i3 - i8));
      if (p() == 1) {
        if (n != 0)
        {
          k = n;
          if (m == 0) {
            break label207;
          }
          label144:
          if ((k == 0) && (m == 0)) {
            break label233;
          }
          if (!paramBoolean) {
            break label222;
          }
          paramRecyclerView.scrollBy(k, m);
        }
      }
      for (;;)
      {
        return true;
        k = Math.max(k, i7 - i5);
        break;
        if (k != 0) {
          break;
        }
        for (;;)
        {
          k = Math.min(i6 - i4, n);
        }
        label207:
        m = Math.min(i2 - i1, i3);
        break label144;
        label222:
        paramRecyclerView.a(k, m);
      }
      label233:
      return false;
    }
    
    @Deprecated
    public boolean a(RecyclerView paramRecyclerView, View paramView1, View paramView2)
    {
      return (o()) || (paramRecyclerView.n());
    }
    
    public boolean a(RecyclerView paramRecyclerView, RecyclerView.q paramq, View paramView1, View paramView2)
    {
      return a(paramRecyclerView, paramView1, paramView2);
    }
    
    public boolean a(RecyclerView paramRecyclerView, ArrayList<View> paramArrayList, int paramInt1, int paramInt2)
    {
      return false;
    }
    
    public int b(int paramInt, RecyclerView.m paramm, RecyclerView.q paramq)
    {
      return 0;
    }
    
    public int b(RecyclerView.m paramm, RecyclerView.q paramq)
    {
      return 0;
    }
    
    public int b(RecyclerView.q paramq)
    {
      return 0;
    }
    
    public Parcelable b()
    {
      return null;
    }
    
    public void b(int paramInt) {}
    
    public void b(View paramView)
    {
      b(paramView, -1);
    }
    
    public void b(View paramView, int paramInt)
    {
      a(paramView, paramInt, false);
    }
    
    void b(RecyclerView.m paramm)
    {
      int m = paramm.d();
      int k = m - 1;
      if (k >= 0)
      {
        View localView = paramm.d(k);
        RecyclerView.t localt = RecyclerView.b(localView);
        if (localt.c()) {}
        for (;;)
        {
          k -= 1;
          break;
          localt.a(false);
          if (localt.q()) {
            i.removeDetachedView(localView, false);
          }
          if (i.s != null) {
            i.s.c(localt);
          }
          localt.a(true);
          paramm.b(localView);
        }
      }
      paramm.e();
      if (m > 0) {
        i.invalidate();
      }
    }
    
    void b(RecyclerView paramRecyclerView)
    {
      b = true;
      c(paramRecyclerView);
    }
    
    void b(RecyclerView paramRecyclerView, RecyclerView.m paramm)
    {
      b = false;
      a(paramRecyclerView, paramm);
    }
    
    public boolean b(Runnable paramRunnable)
    {
      if (i != null) {
        return i.removeCallbacks(paramRunnable);
      }
      return false;
    }
    
    public int c(RecyclerView.m paramm, RecyclerView.q paramq)
    {
      if ((i == null) || (RecyclerView.c(i) == null)) {}
      while (!d()) {
        return 1;
      }
      return RecyclerView.c(i).a();
    }
    
    public int c(RecyclerView.q paramq)
    {
      return 0;
    }
    
    public void c(int paramInt)
    {
      if (e(paramInt) != null) {
        h.a(paramInt);
      }
    }
    
    public void c(View paramView)
    {
      h.a(paramView);
    }
    
    public void c(View paramView, int paramInt)
    {
      a(paramView, paramInt, (RecyclerView.LayoutParams)paramView.getLayoutParams());
    }
    
    public void c(RecyclerView.m paramm)
    {
      int k = r() - 1;
      while (k >= 0)
      {
        if (!RecyclerView.b(e(k)).c()) {
          a(k, paramm);
        }
        k -= 1;
      }
    }
    
    public void c(RecyclerView paramRecyclerView) {}
    
    public boolean c()
    {
      return false;
    }
    
    public int d(View paramView)
    {
      return ((RecyclerView.LayoutParams)paramView.getLayoutParams()).c();
    }
    
    public int d(RecyclerView.m paramm, RecyclerView.q paramq)
    {
      if ((i == null) || (RecyclerView.c(i) == null)) {}
      while (!c()) {
        return 1;
      }
      return RecyclerView.c(i).a();
    }
    
    public int d(RecyclerView.q paramq)
    {
      return 0;
    }
    
    public View d(View paramView, int paramInt)
    {
      return null;
    }
    
    public void d(int paramInt)
    {
      a(paramInt, e(paramInt));
    }
    
    @Deprecated
    public void d(RecyclerView paramRecyclerView) {}
    
    public boolean d()
    {
      return false;
    }
    
    public int e(View paramView)
    {
      Rect localRect = getLayoutParamsb;
      int k = paramView.getMeasuredWidth();
      int m = left;
      return right + (k + m);
    }
    
    public int e(RecyclerView.q paramq)
    {
      return 0;
    }
    
    public View e(int paramInt)
    {
      if (h != null) {
        return h.b(paramInt);
      }
      return null;
    }
    
    public void e(RecyclerView paramRecyclerView) {}
    
    public boolean e(RecyclerView.m paramm, RecyclerView.q paramq)
    {
      return false;
    }
    
    public int f(View paramView)
    {
      Rect localRect = getLayoutParamsb;
      int k = paramView.getMeasuredHeight();
      int m = top;
      return bottom + (k + m);
    }
    
    public int f(RecyclerView.q paramq)
    {
      return 0;
    }
    
    public void f(int paramInt)
    {
      if (i != null) {
        i.c(paramInt);
      }
    }
    
    public int g(View paramView)
    {
      return paramView.getLeft() - m(paramView);
    }
    
    public int g(RecyclerView.q paramq)
    {
      return 0;
    }
    
    public void g(int paramInt)
    {
      if (i != null) {
        i.b(paramInt);
      }
    }
    
    public int h(View paramView)
    {
      return paramView.getTop() - k(paramView);
    }
    
    public void h(int paramInt) {}
    
    public int i(View paramView)
    {
      return paramView.getRight() + n(paramView);
    }
    
    public int j(View paramView)
    {
      return paramView.getBottom() + l(paramView);
    }
    
    public int k(View paramView)
    {
      return getLayoutParamsb.top;
    }
    
    public boolean k()
    {
      return false;
    }
    
    public int l(View paramView)
    {
      return getLayoutParamsb.bottom;
    }
    
    public void l()
    {
      if (i != null) {
        i.requestLayout();
      }
    }
    
    public int m(View paramView)
    {
      return getLayoutParamsb.left;
    }
    
    public boolean m()
    {
      return b;
    }
    
    public int n(View paramView)
    {
      return getLayoutParamsb.right;
    }
    
    public boolean n()
    {
      return (i != null) && (RecyclerView.l(i));
    }
    
    public boolean o()
    {
      return (j != null) && (j.c());
    }
    
    public int p()
    {
      return ViewCompat.getLayoutDirection(i);
    }
    
    public int q()
    {
      return -1;
    }
    
    public int r()
    {
      if (h != null) {
        return h.b();
      }
      return 0;
    }
    
    public int s()
    {
      if (i != null) {
        return i.getWidth();
      }
      return 0;
    }
    
    public int t()
    {
      if (i != null) {
        return i.getHeight();
      }
      return 0;
    }
    
    public int u()
    {
      if (i != null) {
        return i.getPaddingLeft();
      }
      return 0;
    }
    
    public int v()
    {
      if (i != null) {
        return i.getPaddingTop();
      }
      return 0;
    }
    
    public int w()
    {
      if (i != null) {
        return i.getPaddingRight();
      }
      return 0;
    }
    
    public int x()
    {
      if (i != null) {
        return i.getPaddingBottom();
      }
      return 0;
    }
    
    public View y()
    {
      if (i == null) {}
      View localView;
      do
      {
        return null;
        localView = i.getFocusedChild();
      } while ((localView == null) || (h.c(localView)));
      return localView;
    }
    
    void z()
    {
      if (j != null) {
        j.a();
      }
    }
  }
  
  public static abstract interface i
  {
    public abstract void a(View paramView);
  }
  
  public static abstract interface j
  {
    public abstract void a(boolean paramBoolean);
    
    public abstract boolean a(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent);
    
    public abstract void b(RecyclerView paramRecyclerView, MotionEvent paramMotionEvent);
  }
  
  public static abstract class k
  {
    public void a(RecyclerView paramRecyclerView, int paramInt) {}
    
    public void a(RecyclerView paramRecyclerView, int paramInt1, int paramInt2) {}
  }
  
  public static class l
  {
    private SparseArray<ArrayList<RecyclerView.t>> a = new SparseArray();
    private SparseIntArray b = new SparseIntArray();
    private int c = 0;
    
    private ArrayList<RecyclerView.t> b(int paramInt)
    {
      ArrayList localArrayList2 = (ArrayList)a.get(paramInt);
      ArrayList localArrayList1 = localArrayList2;
      if (localArrayList2 == null)
      {
        localArrayList2 = new ArrayList();
        a.put(paramInt, localArrayList2);
        localArrayList1 = localArrayList2;
        if (b.indexOfKey(paramInt) < 0)
        {
          b.put(paramInt, 5);
          localArrayList1 = localArrayList2;
        }
      }
      return localArrayList1;
    }
    
    public RecyclerView.t a(int paramInt)
    {
      ArrayList localArrayList = (ArrayList)a.get(paramInt);
      if ((localArrayList != null) && (!localArrayList.isEmpty()))
      {
        paramInt = localArrayList.size() - 1;
        RecyclerView.t localt = (RecyclerView.t)localArrayList.get(paramInt);
        localArrayList.remove(paramInt);
        return localt;
      }
      return null;
    }
    
    public void a()
    {
      a.clear();
    }
    
    void a(RecyclerView.a parama)
    {
      c += 1;
    }
    
    void a(RecyclerView.a parama1, RecyclerView.a parama2, boolean paramBoolean)
    {
      if (parama1 != null) {
        b();
      }
      if ((!paramBoolean) && (c == 0)) {
        a();
      }
      if (parama2 != null) {
        a(parama2);
      }
    }
    
    public void a(RecyclerView.t paramt)
    {
      int i = paramt.f();
      ArrayList localArrayList = b(i);
      if (b.get(i) <= localArrayList.size()) {
        return;
      }
      paramt.s();
      localArrayList.add(paramt);
    }
    
    void b()
    {
      c -= 1;
    }
  }
  
  public final class m
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
  
  public static abstract interface n
  {
    public abstract void a(RecyclerView.t paramt);
  }
  
  class o
    extends RecyclerView.c
  {}
  
  public static abstract class p
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
  
  public static class q
  {
    ArrayMap<RecyclerView.t, RecyclerView.g> a;
    ArrayMap<RecyclerView.t, RecyclerView.g> b;
    ArrayMap<Long, RecyclerView.t> c;
    final List<View> d;
    int e;
    private int f;
    private SparseArray<Object> g;
    private int h;
    private int i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    
    private void a(ArrayMap<Long, RecyclerView.t> paramArrayMap, RecyclerView.t paramt)
    {
      int n = paramArrayMap.size() - 1;
      for (;;)
      {
        if (n >= 0)
        {
          if (paramt == paramArrayMap.valueAt(n)) {
            paramArrayMap.removeAt(n);
          }
        }
        else {
          return;
        }
        n -= 1;
      }
    }
    
    void a(View paramView)
    {
      d.remove(paramView);
    }
    
    void a(RecyclerView.t paramt)
    {
      a.remove(paramt);
      b.remove(paramt);
      if (c != null) {
        a(c, paramt);
      }
      d.remove(a);
    }
    
    public boolean a()
    {
      return k;
    }
    
    void b(View paramView)
    {
      if (!d.contains(paramView)) {
        d.add(paramView);
      }
    }
    
    public boolean b()
    {
      return m;
    }
    
    public int c()
    {
      return f;
    }
    
    public boolean d()
    {
      return f != -1;
    }
    
    public boolean e()
    {
      return j;
    }
    
    public int f()
    {
      if (k) {
        return h - i;
      }
      return e;
    }
    
    public String toString()
    {
      return "State{mTargetPosition=" + f + ", mPreLayoutHolderMap=" + a + ", mPostLayoutHolderMap=" + b + ", mData=" + g + ", mItemCount=" + e + ", mPreviousLayoutItemCount=" + h + ", mDeletedInvisibleItemCountSincePreviousLayout=" + i + ", mStructureChanged=" + j + ", mInPreLayout=" + k + ", mRunSimpleAnimations=" + l + ", mRunPredictiveAnimations=" + m + '}';
    }
  }
  
  public static abstract class r
  {
    public abstract View a(RecyclerView.m paramm, int paramInt1, int paramInt2);
  }
  
  class s
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
  
  public static abstract class t
  {
    public final View a;
    int b;
    int c;
    long d;
    int e;
    int f;
    t g;
    t h;
    RecyclerView i;
    private int j;
    private int k;
    private RecyclerView.m l;
    
    private boolean u()
    {
      return ((j & 0x10) == 0) && (ViewCompat.hasTransientState(a));
    }
    
    void a()
    {
      c = -1;
      f = -1;
    }
    
    void a(int paramInt1, int paramInt2)
    {
      j = (j & (paramInt2 ^ 0xFFFFFFFF) | paramInt1 & paramInt2);
    }
    
    void a(RecyclerView.m paramm)
    {
      l = paramm;
    }
    
    public final void a(boolean paramBoolean)
    {
      int m;
      if (paramBoolean)
      {
        m = k - 1;
        k = m;
        if (k >= 0) {
          break label64;
        }
        k = 0;
        Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
      }
      label64:
      do
      {
        return;
        m = k + 1;
        break;
        if ((!paramBoolean) && (k == 1))
        {
          j |= 0x10;
          return;
        }
      } while ((!paramBoolean) || (k != 0));
      j &= 0xFFFFFFEF;
    }
    
    boolean a(int paramInt)
    {
      return (j & paramInt) != 0;
    }
    
    void b()
    {
      if (c == -1) {
        c = b;
      }
    }
    
    void b(int paramInt)
    {
      j |= paramInt;
    }
    
    boolean c()
    {
      return (j & 0x80) != 0;
    }
    
    public final int d()
    {
      if (f == -1) {
        return b;
      }
      return f;
    }
    
    public final long e()
    {
      return d;
    }
    
    public final int f()
    {
      return e;
    }
    
    boolean g()
    {
      return l != null;
    }
    
    void h()
    {
      l.d(this);
    }
    
    boolean i()
    {
      return (j & 0x20) != 0;
    }
    
    void j()
    {
      j &= 0xFFFFFFDF;
    }
    
    void k()
    {
      j &= 0xFEFF;
    }
    
    public boolean l()
    {
      return (j & 0x4) != 0;
    }
    
    boolean m()
    {
      return (j & 0x2) != 0;
    }
    
    boolean n()
    {
      return (j & 0x40) != 0;
    }
    
    boolean o()
    {
      return (j & 0x1) != 0;
    }
    
    boolean p()
    {
      return (j & 0x8) != 0;
    }
    
    boolean q()
    {
      return (j & 0x100) != 0;
    }
    
    boolean r()
    {
      return ((j & 0x200) != 0) || (l());
    }
    
    void s()
    {
      j = 0;
      b = -1;
      c = -1;
      d = -1L;
      f = -1;
      k = 0;
      g = null;
      h = null;
    }
    
    public final boolean t()
    {
      return ((j & 0x10) == 0) && (!ViewCompat.hasTransientState(a));
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + b + " id=" + d + ", oldPos=" + c + ", pLpos:" + f);
      if (g()) {
        localStringBuilder.append(" scrap");
      }
      if (l()) {
        localStringBuilder.append(" invalid");
      }
      if (!o()) {
        localStringBuilder.append(" unbound");
      }
      if (m()) {
        localStringBuilder.append(" update");
      }
      if (p()) {
        localStringBuilder.append(" removed");
      }
      if (c()) {
        localStringBuilder.append(" ignored");
      }
      if (n()) {
        localStringBuilder.append(" changed");
      }
      if (q()) {
        localStringBuilder.append(" tmpDetached");
      }
      if (!t()) {
        localStringBuilder.append(" not recyclable(" + k + ")");
      }
      if (r()) {
        localStringBuilder.append("undefined adapter position");
      }
      if (a.getParent() == null) {
        localStringBuilder.append(" no parent");
      }
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */