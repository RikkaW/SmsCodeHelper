package com.amap.api.mapcore2d;

import java.util.ArrayList;
import java.util.LinkedList;

class ca
  extends bx<n.a>
{
  protected ArrayList<n.a> b(int paramInt, boolean paramBoolean)
  {
    int j = 0;
    for (;;)
    {
      int i;
      int k;
      int n;
      try
      {
        Object localObject1 = a;
        if (localObject1 == null)
        {
          localObject1 = null;
          return (ArrayList<n.a>)localObject1;
        }
        i = a.size();
        k = paramInt;
        if (paramInt > i) {
          k = i;
        }
        localObject1 = new ArrayList(k);
        m = 0;
        paramInt = i;
        i = m;
        n.a locala;
        if (j < paramInt)
        {
          if (a == null)
          {
            localObject1 = null;
            continue;
          }
          locala = (n.a)a.get(j);
          if (locala == null)
          {
            n = paramInt;
            m = j;
            paramInt = i;
            j = n;
            break label250;
          }
          m = a;
          if (paramBoolean)
          {
            if (m != 0) {
              break label237;
            }
            ((ArrayList)localObject1).add(locala);
            a.remove(j);
            j -= 1;
            i += 1;
            m = paramInt - 1;
            paramInt = j;
            j = m;
            break label268;
          }
        }
        else
        {
          b();
          continue;
        }
        if (m < 0)
        {
          ((ArrayList)localObject1).add(locala);
          a.remove(j);
          j -= 1;
          i += 1;
          m = paramInt - 1;
          paramInt = j;
          j = m;
        }
      }
      finally {}
      label237:
      int m = paramInt;
      paramInt = j;
      j = m;
      label250:
      label268:
      do
      {
        n = j;
        j = m + 1;
        i = paramInt;
        paramInt = n;
        break;
        m = paramInt;
        paramInt = i;
      } while (i < k);
    }
  }
}

/* Location:
 * Qualified Name:     com.amap.api.mapcore2d.ca
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */