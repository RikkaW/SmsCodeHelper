package com.amap.api.mapcore2d;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

class cd
  extends j<n.a, n.a>
  implements cl
{
  private Context d;
  private av e = new av();
  
  public cd(bl parambl, Context paramContext)
  {
    super(parambl, paramContext);
    d = paramContext;
    a = new ca();
    b.a(this);
  }
  
  private ArrayList<n.a> a(ArrayList<n.a> paramArrayList, aw paramaw, int paramInt, boolean paramBoolean)
  {
    if ((paramArrayList == null) || (paramaw == null)) {}
    int i;
    do
    {
      do
      {
        do
        {
          return null;
        } while ((!f) || (o == null));
        o.clear();
      } while ((paramInt > b) || (paramInt < c));
      i = paramArrayList.size();
    } while (i <= 0);
    ArrayList localArrayList = new ArrayList();
    paramInt = 0;
    if (paramInt < i)
    {
      n.a locala = (n.a)paramArrayList.get(paramInt);
      if (locala == null) {}
      for (;;)
      {
        paramInt += 1;
        break;
        Object localObject = new StringBuilder();
        ((StringBuilder)localObject).append(b);
        ((StringBuilder)localObject).append("-");
        ((StringBuilder)localObject).append(c);
        ((StringBuilder)localObject).append("-");
        ((StringBuilder)localObject).append(d);
        int j = m.a(((StringBuilder)localObject).toString());
        localObject = new n.a(b, c, d, k);
        g = j;
        f = f;
        o.add(localObject);
        if ((a((n.a)localObject)) && (!paramBoolean) && (!e.contains(localObject)))
        {
          if (!g) {
            a = -1;
          }
          localArrayList.add(localObject);
        }
      }
    }
    return localArrayList;
  }
  
  private void a(ArrayList<n.a> paramArrayList, boolean paramBoolean)
  {
    if ((a == null) || (paramArrayList == null)) {}
    while (paramArrayList.size() == 0) {
      return;
    }
    a.a(paramArrayList, paramBoolean);
  }
  
  private boolean a(n.a parama)
  {
    return (parama == null) || (g < 0);
  }
  
  private void c(ArrayList<n.a> paramArrayList)
  {
    if ((paramArrayList == null) || (e == null)) {}
    for (;;)
    {
      return;
      int j = paramArrayList.size();
      if (j != 0)
      {
        int i = 0;
        while (i < j)
        {
          e.a((n.a)paramArrayList.get(i));
          i += 1;
        }
      }
    }
  }
  
  private boolean i()
  {
    if ((b == null) || (b.d == null)) {
      return false;
    }
    if (b.d.a == null) {
      return false;
    }
    int j = b.d.a.size();
    if (j <= 0) {
      return false;
    }
    int i = 0;
    if (i < j)
    {
      aw localaw = (aw)b.d.a.get(i);
      if (localaw == null) {}
      while (f != true)
      {
        i += 1;
        break;
      }
      return true;
    }
    return false;
  }
  
  protected ArrayList<n.a> a(ArrayList<n.a> paramArrayList)
  {
    if ((paramArrayList == null) || (paramArrayList.size() == 0)) {}
    do
    {
      do
      {
        do
        {
          return null;
        } while ((b == null) || (b.d == null) || (b.d.a == null));
        i = b.d.a.size();
      } while (get0e >= i);
      a(paramArrayList);
    } while ((paramArrayList.size() == 0) || (b.d.a.size() == 0));
    int i = get0e;
    ArrayList localArrayList;
    if (b.d.a.get(i)).j != null)
    {
      ce localce = new ce(d, paramArrayList);
      localce.a((aw)b.d.a.get(get0e));
      localArrayList = (ArrayList)localce.a();
      localce.a(null);
    }
    for (;;)
    {
      c(paramArrayList);
      if ((b == null) || (b.d == null)) {
        return localArrayList;
      }
      b.d.b();
      return localArrayList;
      localArrayList = null;
    }
  }
  
  public void a()
  {
    super.a();
    e.clear();
  }
  
  public void a(List<n.a> paramList)
  {
    if (paramList == null) {}
    int i;
    do
    {
      return;
      i = paramList.size();
    } while (i == 0);
    int j = 0;
    label18:
    if (j < i)
    {
      if (e.b((n.a)paramList.get(j))) {
        break label66;
      }
      paramList.remove(j);
      j -= 1;
      i -= 1;
    }
    label66:
    for (;;)
    {
      j += 1;
      break label18;
      break;
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!i()) {}
    ArrayList localArrayList1;
    do
    {
      return;
      localArrayList1 = b.h.a(b.h.i, b.h.g, b.b.c(), b.b.d());
    } while ((localArrayList1 == null) || (localArrayList1.size() <= 0));
    int j = b.d.a.size();
    int i = 0;
    paramBoolean1 = true;
    ArrayList localArrayList2;
    if (i < j)
    {
      localArrayList2 = a(localArrayList1, (aw)b.d.a.get(i), b.b.e(), paramBoolean2);
      if (localArrayList2 == null) {
        break label184;
      }
      a(localArrayList2, paramBoolean1);
      if (paramBoolean1 != true) {
        break label187;
      }
      paramBoolean1 = false;
    }
    label184:
    label187:
    for (;;)
    {
      localArrayList2.clear();
      for (;;)
      {
        i += 1;
        break;
        localArrayList1.clear();
        b.b.g().invalidate();
        return;
      }
    }
  }
  
  protected ArrayList<n.a> b(ArrayList<n.a> paramArrayList)
  {
    if (paramArrayList == null) {
      localObject = null;
    }
    int i;
    int j;
    ArrayList localArrayList;
    do
    {
      return (ArrayList<n.a>)localObject;
      i = paramArrayList.size();
      if (i == 0) {
        return null;
      }
      j = 0;
      localArrayList = null;
      localObject = localArrayList;
    } while (j >= i);
    Object localObject = (n.a)paramArrayList.get(j);
    if (localObject == null) {}
    for (;;)
    {
      j += 1;
      break;
      if ((b == null) || (b.d == null) || (b.d.a == null)) {
        return null;
      }
      int k = b.d.a.size();
      if ((e < k) && (b.d.a.get(e)).g))
      {
        int m = b.d.a.get(e)).n.a((n.a)localObject);
        if (m < 0) {
          break label304;
        }
        paramArrayList.remove(j);
        i -= 1;
        j -= 1;
        bw localbw = b.d.a.get(e)).o;
        if (localbw != null)
        {
          int n = localbw.size();
          k = 0;
          if (k < n)
          {
            n.a locala = (n.a)localbw.get(k);
            if (locala == null) {}
            while (!locala.equals(localObject))
            {
              k += 1;
              break;
            }
            g = m;
            b.d.b();
          }
        }
      }
    }
    label304:
    if (localArrayList == null) {
      localArrayList = new ArrayList();
    }
    for (;;)
    {
      localObject = new n.a((n.a)localObject);
      a = -1;
      localArrayList.add(localObject);
      break;
    }
  }
  
  protected int e()
  {
    return 4;
  }
  
  protected int f()
  {
    return 1;
  }
  
  public void g() {}
  
  public void h()
  {
    a(false, false);
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.cd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */