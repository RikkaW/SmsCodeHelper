import android.view.View;
import android.view.ViewGroup.LayoutParams;
import flyme.support.v7.widget.RecyclerView.t;
import java.util.List;

public class ard
{
  final ard.b a;
  final ard.a b;
  final List<View> c;
  
  private int e(int paramInt)
  {
    if (paramInt < 0)
    {
      paramInt = -1;
      return paramInt;
    }
    int j = a.a();
    int i = paramInt;
    for (;;)
    {
      if (i >= j) {
        break label72;
      }
      int k = paramInt - (i - b.e(i));
      if (k == 0) {
        for (;;)
        {
          paramInt = i;
          if (!b.c(i)) {
            break;
          }
          i += 1;
        }
      }
      i += k;
    }
    label72:
    return -1;
  }
  
  public View a(int paramInt1, int paramInt2)
  {
    int j = c.size();
    int i = 0;
    while (i < j)
    {
      View localView = (View)c.get(i);
      RecyclerView.t localt = a.b(localView);
      if ((localt.d() == paramInt1) && (!localt.l()) && ((paramInt2 == -1) || (localt.f() == paramInt2))) {
        return localView;
      }
      i += 1;
    }
    return null;
  }
  
  public void a()
  {
    b.a();
    c.clear();
    a.b();
  }
  
  public void a(int paramInt)
  {
    paramInt = e(paramInt);
    View localView = a.b(paramInt);
    if (localView == null) {
      return;
    }
    if (b.d(paramInt)) {
      c.remove(localView);
    }
    a.a(paramInt);
  }
  
  public void a(View paramView)
  {
    int i = a.a(paramView);
    if (i < 0) {
      return;
    }
    if (b.d(i)) {
      c.remove(paramView);
    }
    a.a(i);
  }
  
  public void a(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams, boolean paramBoolean)
  {
    if (paramInt < 0) {}
    for (paramInt = a.a();; paramInt = e(paramInt))
    {
      b.a(paramInt, paramBoolean);
      if (paramBoolean) {
        c.add(paramView);
      }
      a.a(paramView, paramInt, paramLayoutParams);
      return;
    }
  }
  
  public void a(View paramView, int paramInt, boolean paramBoolean)
  {
    if (paramInt < 0) {}
    for (paramInt = a.a();; paramInt = e(paramInt))
    {
      b.a(paramInt, paramBoolean);
      if (paramBoolean) {
        c.add(paramView);
      }
      a.a(paramView, paramInt);
      return;
    }
  }
  
  public void a(View paramView, boolean paramBoolean)
  {
    a(paramView, -1, paramBoolean);
  }
  
  public int b()
  {
    return a.a() - c.size();
  }
  
  public int b(View paramView)
  {
    int i = a.a(paramView);
    if (i == -1) {}
    while (b.c(i)) {
      return -1;
    }
    return i - b.e(i);
  }
  
  public View b(int paramInt)
  {
    paramInt = e(paramInt);
    return a.b(paramInt);
  }
  
  public int c()
  {
    return a.a();
  }
  
  public View c(int paramInt)
  {
    return a.b(paramInt);
  }
  
  public boolean c(View paramView)
  {
    return c.contains(paramView);
  }
  
  public void d(int paramInt)
  {
    paramInt = e(paramInt);
    b.d(paramInt);
    a.c(paramInt);
  }
  
  public void d(View paramView)
  {
    int i = a.a(paramView);
    if (i < 0) {
      throw new IllegalArgumentException("view is not a child, cannot hide " + paramView);
    }
    b.a(i);
    c.add(paramView);
  }
  
  public String toString()
  {
    return b.toString() + ", hidden list:" + c.size();
  }
  
  static class a
  {
    long a = 0L;
    a b;
    
    private void b()
    {
      if (b == null) {
        b = new a();
      }
    }
    
    void a()
    {
      a = 0L;
      if (b != null) {
        b.a();
      }
    }
    
    void a(int paramInt)
    {
      if (paramInt >= 64)
      {
        b();
        b.a(paramInt - 64);
        return;
      }
      a |= 1L << paramInt;
    }
    
    void a(int paramInt, boolean paramBoolean)
    {
      if (paramInt >= 64)
      {
        b();
        b.a(paramInt - 64, paramBoolean);
      }
      label113:
      label119:
      for (;;)
      {
        return;
        boolean bool;
        if ((a & 0x8000000000000000) != 0L)
        {
          bool = true;
          long l1 = (1L << paramInt) - 1L;
          long l2 = a;
          a = (((l1 ^ 0xFFFFFFFFFFFFFFFF) & a) << 1 | l2 & l1);
          if (!paramBoolean) {
            break label113;
          }
          a(paramInt);
        }
        for (;;)
        {
          if ((!bool) && (b == null)) {
            break label119;
          }
          b();
          b.a(0, bool);
          return;
          bool = false;
          break;
          b(paramInt);
        }
      }
    }
    
    void b(int paramInt)
    {
      if (paramInt >= 64)
      {
        if (b != null) {
          b.b(paramInt - 64);
        }
        return;
      }
      a &= (1L << paramInt ^ 0xFFFFFFFFFFFFFFFF);
    }
    
    boolean c(int paramInt)
    {
      if (paramInt >= 64)
      {
        b();
        return b.c(paramInt - 64);
      }
      return (a & 1L << paramInt) != 0L;
    }
    
    boolean d(int paramInt)
    {
      boolean bool2;
      if (paramInt >= 64)
      {
        b();
        bool2 = b.d(paramInt - 64);
        return bool2;
      }
      long l1 = 1L << paramInt;
      if ((a & l1) != 0L) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        a &= (l1 ^ 0xFFFFFFFFFFFFFFFF);
        l1 -= 1L;
        long l2 = a;
        a = (Long.rotateRight((l1 ^ 0xFFFFFFFFFFFFFFFF) & a, 1) | l2 & l1);
        bool2 = bool1;
        if (b == null) {
          break;
        }
        if (b.c(0)) {
          a(63);
        }
        b.d(0);
        return bool1;
      }
    }
    
    int e(int paramInt)
    {
      if (b == null)
      {
        if (paramInt >= 64) {
          return Long.bitCount(a);
        }
        return Long.bitCount(a & (1L << paramInt) - 1L);
      }
      if (paramInt < 64) {
        return Long.bitCount(a & (1L << paramInt) - 1L);
      }
      return b.e(paramInt - 64) + Long.bitCount(a);
    }
    
    public String toString()
    {
      if (b == null) {
        return Long.toBinaryString(a);
      }
      return b.toString() + "xx" + Long.toBinaryString(a);
    }
  }
  
  public static abstract interface b
  {
    public abstract int a();
    
    public abstract int a(View paramView);
    
    public abstract void a(int paramInt);
    
    public abstract void a(View paramView, int paramInt);
    
    public abstract void a(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams);
    
    public abstract View b(int paramInt);
    
    public abstract RecyclerView.t b(View paramView);
    
    public abstract void b();
    
    public abstract void c(int paramInt);
  }
}

/* Location:
 * Qualified Name:     ard
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */