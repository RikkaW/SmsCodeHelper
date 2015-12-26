package flyme.support.v7.widget;

import android.view.View;
import arl;
import java.util.ArrayList;

class StaggeredGridLayoutManager$b
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

/* Location:
 * Qualified Name:     flyme.support.v7.widget.StaggeredGridLayoutManager.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */