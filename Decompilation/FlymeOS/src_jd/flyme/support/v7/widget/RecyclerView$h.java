package flyme.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat;
import android.support.v4.view.accessibility.AccessibilityRecordCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import ard;
import java.util.ArrayList;

public abstract class RecyclerView$h
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

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */