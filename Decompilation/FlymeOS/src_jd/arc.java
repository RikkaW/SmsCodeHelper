import android.support.v4.util.Pools.Pool;
import flyme.support.v7.widget.RecyclerView.t;
import java.util.ArrayList;
import java.util.List;

public class arc
  implements ari.a
{
  final ArrayList<arc.b> a;
  final ArrayList<arc.b> b;
  final arc.a c;
  Runnable d;
  final boolean e;
  final ari f;
  private Pools.Pool<arc.b> g;
  
  private int b(int paramInt1, int paramInt2)
  {
    int j = b.size() - 1;
    arc.b localb;
    if (j >= 0)
    {
      localb = (arc.b)b.get(j);
      int k;
      int i;
      if (a == 3) {
        if (b < c)
        {
          k = b;
          i = c;
          label65:
          if ((paramInt1 < k) || (paramInt1 > i)) {
            break label198;
          }
          if (k != b) {
            break label155;
          }
          if (paramInt2 != 0) {
            break label135;
          }
          c += 1;
          label102:
          paramInt1 += 1;
          label106:
          i = paramInt1;
        }
      }
      for (;;)
      {
        j -= 1;
        paramInt1 = i;
        break;
        k = c;
        i = b;
        break label65;
        label135:
        if (paramInt2 != 1) {
          break label102;
        }
        c -= 1;
        break label102;
        label155:
        if (paramInt2 == 0) {
          b += 1;
        }
        for (;;)
        {
          paramInt1 -= 1;
          break;
          if (paramInt2 == 1) {
            b -= 1;
          }
        }
        label198:
        if (paramInt1 < b)
        {
          if (paramInt2 == 0)
          {
            b += 1;
            c += 1;
            break label106;
          }
          if (paramInt2 == 1)
          {
            b -= 1;
            c -= 1;
          }
        }
        break label106;
        if (b <= paramInt1)
        {
          if (a == 0)
          {
            i = paramInt1 - c;
          }
          else
          {
            i = paramInt1;
            if (a == 1) {
              i = paramInt1 + c;
            }
          }
        }
        else if (paramInt2 == 0)
        {
          b += 1;
          i = paramInt1;
        }
        else
        {
          i = paramInt1;
          if (paramInt2 == 1)
          {
            b -= 1;
            i = paramInt1;
          }
        }
      }
    }
    paramInt2 = b.size() - 1;
    if (paramInt2 >= 0)
    {
      localb = (arc.b)b.get(paramInt2);
      if (a == 3) {
        if ((c == b) || (c < 0))
        {
          b.remove(paramInt2);
          a(localb);
        }
      }
      for (;;)
      {
        paramInt2 -= 1;
        break;
        if (c <= 0)
        {
          b.remove(paramInt2);
          a(localb);
        }
      }
    }
    return paramInt1;
  }
  
  private void b(arc.b paramb)
  {
    g(paramb);
  }
  
  private boolean b(int paramInt)
  {
    int k = b.size();
    int i = 0;
    while (i < k)
    {
      arc.b localb = (arc.b)b.get(i);
      if (a == 3)
      {
        if (a(c, i + 1) == paramInt) {
          return true;
        }
      }
      else if (a == 0)
      {
        int m = b;
        int n = c;
        int j = b;
        while (j < m + n)
        {
          if (a(j, i + 1) == paramInt) {
            return true;
          }
          j += 1;
        }
      }
      i += 1;
    }
    return false;
  }
  
  private void c(arc.b paramb)
  {
    int i2 = b;
    int k = b + c;
    int j = -1;
    int i = b;
    int n = 0;
    if (i < k) {
      if ((c.a(i) != null) || (b(i)))
      {
        if (j != 0) {
          break label213;
        }
        e(a(1, i2, n));
      }
    }
    label96:
    label208:
    label213:
    for (int m = 1;; m = 0)
    {
      j = 1;
      if (m != 0)
      {
        m = i - n;
        i = k - n;
        k = 1;
        n = k;
        k = i;
        i = m + 1;
        break;
        if (j != 1) {
          break label208;
        }
        g(a(1, i2, n));
      }
      for (j = 1;; j = 0)
      {
        int i1 = 0;
        m = j;
        j = i1;
        break;
        n += 1;
        m = i;
        i = k;
        k = n;
        break label96;
        arc.b localb = paramb;
        if (n != c)
        {
          a(paramb);
          localb = a(1, i2, n);
        }
        if (j == 0)
        {
          e(localb);
          return;
        }
        g(localb);
        return;
      }
    }
  }
  
  private void d(arc.b paramb)
  {
    int k = b;
    int i2 = b;
    int i3 = c;
    int i = b;
    int i1 = -1;
    int j = 0;
    if (i < i2 + i3)
    {
      int m;
      int n;
      if ((c.a(i) != null) || (b(i)))
      {
        m = j;
        n = k;
        if (i1 == 0)
        {
          e(a(2, k, j));
          m = 0;
          n = i;
        }
        k = n;
      }
      for (j = 1;; j = 0)
      {
        i += 1;
        m += 1;
        i1 = j;
        j = m;
        break;
        m = j;
        n = k;
        if (i1 == 1)
        {
          g(a(2, k, j));
          m = 0;
          n = i;
        }
        k = n;
      }
    }
    arc.b localb = paramb;
    if (j != c)
    {
      a(paramb);
      localb = a(2, k, j);
    }
    if (i1 == 0)
    {
      e(localb);
      return;
    }
    g(localb);
  }
  
  private void e(arc.b paramb)
  {
    if ((a == 0) || (a == 3)) {
      throw new IllegalArgumentException("should not dispatch add or move for pre layout");
    }
    int i1 = b(b, a);
    int j = b;
    int k;
    int n;
    int m;
    label108:
    int i2;
    switch (a)
    {
    default: 
      throw new IllegalArgumentException("op should be remove or update." + paramb);
    case 2: 
      k = 1;
      n = 1;
      m = 1;
      if (m >= c) {
        break label286;
      }
      i2 = b(b + k * m, a);
      switch (a)
      {
      default: 
        i = 0;
        label166:
        if (i == 0) {}
        break;
      }
      break;
    }
    for (int i = n + 1;; i = n)
    {
      m += 1;
      n = i;
      break label108;
      k = 0;
      break;
      if (i2 == i1 + 1)
      {
        i = 1;
        break label166;
      }
      i = 0;
      break label166;
      if (i2 == i1)
      {
        i = 1;
        break label166;
      }
      i = 0;
      break label166;
      arc.b localb = a(a, i1, n);
      a(localb, j);
      a(localb);
      i = j;
      if (a == 2) {
        i = j + n;
      }
      n = 1;
      i1 = i2;
      j = i;
    }
    label286:
    a(paramb);
    if (n > 0)
    {
      paramb = a(a, i1, n);
      a(paramb, j);
      a(paramb);
    }
  }
  
  private void f(arc.b paramb)
  {
    g(paramb);
  }
  
  private void g(arc.b paramb)
  {
    b.add(paramb);
    switch (a)
    {
    default: 
      throw new IllegalArgumentException("Unknown update op type for " + paramb);
    case 0: 
      c.d(b, c);
      return;
    case 3: 
      c.e(b, c);
      return;
    case 1: 
      c.b(b, c);
      return;
    }
    c.c(b, c);
  }
  
  public int a(int paramInt)
  {
    return a(paramInt, 0);
  }
  
  int a(int paramInt1, int paramInt2)
  {
    int k = b.size();
    int j = paramInt2;
    paramInt2 = paramInt1;
    paramInt1 = paramInt2;
    arc.b localb;
    if (j < k)
    {
      localb = (arc.b)b.get(j);
      if (a == 3) {
        if (b == paramInt2) {
          paramInt1 = c;
        }
      }
    }
    for (;;)
    {
      j += 1;
      paramInt2 = paramInt1;
      break;
      int i = paramInt2;
      if (b < paramInt2) {
        i = paramInt2 - 1;
      }
      paramInt1 = i;
      if (c <= i)
      {
        paramInt1 = i + 1;
        continue;
        paramInt1 = paramInt2;
        if (b <= paramInt2) {
          if (a == 1)
          {
            if (paramInt2 < b + c)
            {
              paramInt1 = -1;
              return paramInt1;
            }
            paramInt1 = paramInt2 - c;
          }
          else
          {
            paramInt1 = paramInt2;
            if (a == 0) {
              paramInt1 = paramInt2 + c;
            }
          }
        }
      }
    }
  }
  
  public arc.b a(int paramInt1, int paramInt2, int paramInt3)
  {
    arc.b localb = (arc.b)g.acquire();
    if (localb == null) {
      return new arc.b(paramInt1, paramInt2, paramInt3);
    }
    a = paramInt1;
    b = paramInt2;
    c = paramInt3;
    return localb;
  }
  
  public void a()
  {
    a(a);
    a(b);
  }
  
  public void a(arc.b paramb)
  {
    if (!e) {
      g.release(paramb);
    }
  }
  
  void a(arc.b paramb, int paramInt)
  {
    c.a(paramb);
    switch (a)
    {
    default: 
      throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
    case 1: 
      c.a(paramInt, c);
      return;
    }
    c.c(paramInt, c);
  }
  
  void a(List<arc.b> paramList)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      a((arc.b)paramList.get(i));
      i += 1;
    }
    paramList.clear();
  }
  
  public void b()
  {
    f.a(a);
    int j = a.size();
    int i = 0;
    if (i < j)
    {
      arc.b localb = (arc.b)a.get(i);
      switch (a)
      {
      }
      for (;;)
      {
        if (d != null) {
          d.run();
        }
        i += 1;
        break;
        f(localb);
        continue;
        c(localb);
        continue;
        d(localb);
        continue;
        b(localb);
      }
    }
    a.clear();
  }
  
  public void c()
  {
    int j = b.size();
    int i = 0;
    while (i < j)
    {
      c.b((arc.b)b.get(i));
      i += 1;
    }
    a(b);
  }
  
  public boolean d()
  {
    return a.size() > 0;
  }
  
  public void e()
  {
    c();
    int j = a.size();
    int i = 0;
    if (i < j)
    {
      arc.b localb = (arc.b)a.get(i);
      switch (a)
      {
      }
      for (;;)
      {
        if (d != null) {
          d.run();
        }
        i += 1;
        break;
        c.b(localb);
        c.d(b, c);
        continue;
        c.b(localb);
        c.a(b, c);
        continue;
        c.b(localb);
        c.c(b, c);
        continue;
        c.b(localb);
        c.e(b, c);
      }
    }
    a(a);
  }
  
  public static abstract interface a
  {
    public abstract RecyclerView.t a(int paramInt);
    
    public abstract void a(int paramInt1, int paramInt2);
    
    public abstract void a(arc.b paramb);
    
    public abstract void b(int paramInt1, int paramInt2);
    
    public abstract void b(arc.b paramb);
    
    public abstract void c(int paramInt1, int paramInt2);
    
    public abstract void d(int paramInt1, int paramInt2);
    
    public abstract void e(int paramInt1, int paramInt2);
  }
  
  static class b
  {
    int a;
    int b;
    int c;
    
    b(int paramInt1, int paramInt2, int paramInt3)
    {
      a = paramInt1;
      b = paramInt2;
      c = paramInt3;
    }
    
    String a()
    {
      switch (a)
      {
      default: 
        return "??";
      case 0: 
        return "add";
      case 1: 
        return "rm";
      case 2: 
        return "up";
      }
      return "mv";
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        do
        {
          return true;
          if ((paramObject == null) || (getClass() != paramObject.getClass())) {
            return false;
          }
          paramObject = (b)paramObject;
          if (a != a) {
            return false;
          }
        } while ((a == 3) && (Math.abs(c - b) == 1) && (c == b) && (b == c));
        if (c != c) {
          return false;
        }
      } while (b == b);
      return false;
    }
    
    public int hashCode()
    {
      return (a * 31 + b) * 31 + c;
    }
    
    public String toString()
    {
      return "[" + a() + ",s:" + b + "c:" + c + "]";
    }
  }
}

/* Location:
 * Qualified Name:     arc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */