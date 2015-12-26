import java.util.List;

class ari
{
  final ari.a a;
  
  private void a(List<arc.b> paramList, int paramInt1, int paramInt2)
  {
    arc.b localb1 = (arc.b)paramList.get(paramInt1);
    arc.b localb2 = (arc.b)paramList.get(paramInt2);
    switch (a)
    {
    default: 
      return;
    case 1: 
      a(paramList, paramInt1, localb1, paramInt2, localb2);
      return;
    case 0: 
      c(paramList, paramInt1, localb1, paramInt2, localb2);
      return;
    }
    b(paramList, paramInt1, localb1, paramInt2, localb2);
  }
  
  private int b(List<arc.b> paramList)
  {
    int i = 0;
    int j = paramList.size() - 1;
    if (j >= 0)
    {
      if (geta == 3)
      {
        if (i == 0) {
          break label49;
        }
        return j;
      }
      i = 1;
    }
    label49:
    for (;;)
    {
      j -= 1;
      break;
      return -1;
    }
  }
  
  private void c(List<arc.b> paramList, int paramInt1, arc.b paramb1, int paramInt2, arc.b paramb2)
  {
    int i = 0;
    if (c < b) {
      i = -1;
    }
    int j = i;
    if (b < b) {
      j = i + 1;
    }
    if (b <= b) {
      b += c;
    }
    if (b <= c) {
      c += c;
    }
    b = (j + b);
    paramList.set(paramInt1, paramb2);
    paramList.set(paramInt2, paramb1);
  }
  
  void a(List<arc.b> paramList)
  {
    for (;;)
    {
      int i = b(paramList);
      if (i == -1) {
        break;
      }
      a(paramList, i, i + 1);
    }
  }
  
  void a(List<arc.b> paramList, int paramInt1, arc.b paramb1, int paramInt2, arc.b paramb2)
  {
    int j = 0;
    int i;
    if (b < c)
    {
      if ((b != b) || (c != c - b)) {
        break label622;
      }
      i = 1;
    }
    for (;;)
    {
      label70:
      arc.b localb;
      if (c < b)
      {
        b -= 1;
        if (b > b) {
          break label241;
        }
        b += 1;
        localb = null;
      }
      for (;;)
      {
        label97:
        if (i != 0)
        {
          paramList.set(paramInt1, paramb2);
          paramList.remove(paramInt2);
          a.a(paramb1);
        }
        label241:
        label595:
        label605:
        for (;;)
        {
          return;
          if ((b != c + 1) || (c != b - c)) {
            break label613;
          }
          j = 1;
          i = 1;
          break;
          if (c >= b + c) {
            break label70;
          }
          c -= 1;
          a = 1;
          c = 1;
          if (c == 0)
          {
            paramList.remove(paramInt2);
            a.a(paramb2);
            return;
            if (b >= b + c) {
              break label607;
            }
            int k = b;
            int m = c;
            int n = b;
            localb = a.a(1, b + 1, k + m - n);
            c = (b - b);
            break label97;
            if (j != 0)
            {
              if (localb != null)
              {
                if (b > b) {
                  b -= c;
                }
                if (c > b) {
                  c -= c;
                }
              }
              if (b > b) {
                b -= c;
              }
              if (c > b) {
                c -= c;
              }
              paramList.set(paramInt1, paramb2);
              if (b == c) {
                break label595;
              }
              paramList.set(paramInt2, paramb1);
            }
            for (;;)
            {
              if (localb == null) {
                break label605;
              }
              paramList.add(paramInt1, localb);
              return;
              if (localb != null)
              {
                if (b >= b) {
                  b -= c;
                }
                if (c >= b) {
                  c -= c;
                }
              }
              if (b >= b) {
                b -= c;
              }
              if (c < b) {
                break;
              }
              c -= c;
              break;
              paramList.remove(paramInt2);
            }
          }
        }
        label607:
        localb = null;
      }
      label613:
      i = 0;
      j = 1;
      continue;
      label622:
      i = 0;
    }
  }
  
  void b(List<arc.b> paramList, int paramInt1, arc.b paramb1, int paramInt2, arc.b paramb2)
  {
    Object localObject2 = null;
    Object localObject1;
    if (c < b)
    {
      b -= 1;
      localObject1 = null;
    }
    for (;;)
    {
      if (b <= b)
      {
        b += 1;
        label54:
        paramList.set(paramInt2, paramb1);
        if (c <= 0) {
          break label233;
        }
        paramList.set(paramInt1, paramb2);
      }
      for (;;)
      {
        if (localObject1 != null) {
          paramList.add(paramInt1, localObject1);
        }
        if (localObject2 != null) {
          paramList.add(paramInt1, localObject2);
        }
        return;
        if (c >= b + c) {
          break label255;
        }
        c -= 1;
        localObject1 = a.a(2, b, 1);
        break;
        if (b >= b + c) {
          break label54;
        }
        int i = b + c - b;
        localObject2 = a.a(2, b + 1, i);
        c -= i;
        break label54;
        label233:
        paramList.remove(paramInt1);
        a.a(paramb2);
      }
      label255:
      localObject1 = null;
    }
  }
  
  static abstract interface a
  {
    public abstract arc.b a(int paramInt1, int paramInt2, int paramInt3);
    
    public abstract void a(arc.b paramb);
  }
}

/* Location:
 * Qualified Name:     ari
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */