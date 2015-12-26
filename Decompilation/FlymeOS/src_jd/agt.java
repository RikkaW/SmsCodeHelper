import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.CellLocation;
import java.util.List;

public final class agt
{
  private static int c = 10;
  private static int d = 100;
  private static float f = 0.5F;
  protected aha a = new aha(this);
  protected agx b = new agx(this);
  private agf e;
  
  protected agt(agf paramagf)
  {
    e = paramagf;
  }
  
  protected static void a() {}
  
  protected static void a(int paramInt)
  {
    c = paramInt;
  }
  
  protected static void b(int paramInt)
  {
    d = paramInt;
  }
  
  protected final boolean a(Location paramLocation)
  {
    boolean bool3 = false;
    boolean bool1 = false;
    boolean bool2;
    if (e == null) {
      bool2 = bool1;
    }
    agy localagy;
    do
    {
      List localList;
      do
      {
        do
        {
          return bool2;
          localList = e.j();
          bool2 = bool1;
        } while (localList == null);
        bool2 = bool1;
      } while (paramLocation == null);
      new StringBuilder("cell.list.size: ").append(localList.size()).toString();
      localagy = null;
      bool1 = bool3;
      if (localList.size() >= 2)
      {
        localagy = new agy((CellLocation)localList.get(1));
        if (b.b != null) {
          break;
        }
        bool1 = true;
      }
      bool2 = bool1;
    } while (!bool1);
    b.a = localagy;
    return bool1;
    label154:
    int i;
    if (paramLocation.distanceTo(b.b) > d)
    {
      bool2 = true;
      bool1 = bool2;
      if (!bool2)
      {
        paramLocation = b.a;
        if ((e != e) || (d != d) || (c != c) || (b != b) || (a != a)) {
          break label264;
        }
        i = 1;
        label232:
        if (i != 0) {
          break label269;
        }
      }
    }
    label264:
    label269:
    for (bool1 = true;; bool1 = false)
    {
      new StringBuilder("collect cell?: ").append(bool1).toString();
      break;
      bool2 = false;
      break label154;
      i = 0;
      break label232;
    }
  }
  
  protected final boolean b(Location paramLocation)
  {
    int m = 0;
    if (e == null) {
      return false;
    }
    List localList = e.k();
    boolean bool;
    if (localList.size() >= 2)
    {
      localList = (List)localList.get(1);
      if (a.b == null)
      {
        bool = true;
        paramLocation = localList;
      }
    }
    for (;;)
    {
      int j;
      int i;
      label177:
      float f1;
      label206:
      label223:
      float f2;
      label300:
      int k;
      if (bool)
      {
        a.a.clear();
        j = paramLocation.size();
        i = m;
        while (i < j)
        {
          a.a.add(new agz(getBSSID));
          i += 1;
          continue;
          if ((localList == null) || (localList.size() <= 0)) {
            break label427;
          }
          if (paramLocation.distanceTo(a.b) > c)
          {
            bool = true;
            if (bool) {
              break label421;
            }
            paramLocation = a.a;
            f1 = f;
            if ((localList != null) && (paramLocation != null)) {
              break label223;
            }
            i = 0;
          }
          for (;;)
          {
            if (i == 0)
            {
              bool = true;
              break;
              bool = false;
              break label177;
              if ((localList == null) || (paramLocation == null))
              {
                i = 0;
              }
              else
              {
                int n = localList.size();
                int i1 = paramLocation.size();
                f2 = n + i1;
                if ((n == 0) && (i1 == 0))
                {
                  i = 1;
                }
                else if ((n == 0) || (i1 == 0))
                {
                  i = 0;
                }
                else
                {
                  j = 0;
                  i = 0;
                  if (j < n)
                  {
                    String str = getBSSID;
                    if (str == null) {
                      break label418;
                    }
                    k = 0;
                    label332:
                    if (k >= i1) {
                      break label418;
                    }
                    if (str.equals(geta)) {
                      i += 1;
                    }
                  }
                }
              }
            }
          }
        }
      }
      label418:
      for (;;)
      {
        j += 1;
        break label300;
        k += 1;
        break label332;
        if (i << 1 >= f2 * f1)
        {
          i = 1;
          break label206;
        }
        i = 0;
        break label206;
        bool = false;
        break;
        return bool;
      }
      label421:
      paramLocation = localList;
      continue;
      label427:
      paramLocation = localList;
      bool = false;
      continue;
      paramLocation = null;
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     agt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */