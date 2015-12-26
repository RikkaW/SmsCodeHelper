import java.util.ArrayList;
import org.w3c.dom.DOMException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class hu
  extends hw
  implements aty
{
  hu(aud paramaud)
  {
    super(paramaud);
  }
  
  public String a()
  {
    String str2 = a.getAttribute("endsync");
    String str1;
    if ((str2 == null) || (str2.length() == 0))
    {
      a("last");
      str1 = "last";
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return str1;
            str1 = str2;
          } while ("first".equals(str2));
          str1 = str2;
        } while ("last".equals(str2));
        str1 = str2;
      } while ("all".equals(str2));
      str1 = str2;
    } while ("media".equals(str2));
    a("last");
    return "last";
  }
  
  public NodeList a(float paramFloat)
  {
    ArrayList localArrayList = new ArrayList();
    NodeList localNodeList = j_();
    int m = localNodeList.getLength();
    int j = 0;
    double d1;
    aua localaua;
    aun localaun;
    int n;
    int k;
    int i;
    label74:
    aum localaum;
    double d2;
    if (j < m)
    {
      d1 = 0.0D;
      localaua = (aua)localNodeList.item(j);
      localaun = localaua.g();
      n = localaun.a();
      k = 0;
      i = 0;
      if (k < n)
      {
        localaum = localaun.a(k);
        if (!localaum.b()) {
          break label275;
        }
        d2 = localaum.c() * 1000.0D;
        if ((d2 > paramFloat) || (d2 < d1)) {
          break label275;
        }
        i = 1;
        d1 = d2;
      }
    }
    label166:
    label272:
    label275:
    for (;;)
    {
      k += 1;
      break label74;
      localaun = localaua.h();
      n = localaun.a();
      k = 0;
      if (k < n)
      {
        localaum = localaun.a(k);
        if (!localaum.b()) {
          break label272;
        }
        d2 = localaum.c() * 1000.0D;
        if ((d2 > paramFloat) || (d2 < d1)) {
          break label272;
        }
        i = 0;
        d1 = d2;
      }
      for (;;)
      {
        k += 1;
        break label166;
        if (i != 0) {
          localArrayList.add((Node)localaua);
        }
        j += 1;
        break;
        return new hr(localArrayList);
      }
    }
  }
  
  public void a(String paramString)
  {
    if (("first".equals(paramString)) || ("last".equals(paramString)) || ("all".equals(paramString)) || ("media".equals(paramString)))
    {
      a.setAttribute("endsync", paramString);
      return;
    }
    throw new DOMException((short)9, "Unsupported endsync value" + paramString);
  }
  
  public float b()
  {
    float f2 = super.b();
    float f1 = f2;
    if (f2 == 0.0F) {
      f1 = f_();
    }
    return f1;
  }
  
  public float f_()
  {
    if ("last".equals(a()))
    {
      NodeList localNodeList = j_();
      int i = 0;
      float f1 = -1.0F;
      for (;;)
      {
        f2 = f1;
        if (i >= localNodeList.getLength()) {
          break;
        }
        aun localaun = ((aua)localNodeList.item(i)).h();
        int j = 0;
        while (j < localaun.a())
        {
          aum localaum = localaun.a(j);
          if (localaum.d() == 0) {
            return -1.0F;
          }
          f2 = f1;
          if (localaum.b())
          {
            float f3 = (float)localaum.c();
            f2 = f1;
            if (f3 > f1) {
              f2 = f3;
            }
          }
          j += 1;
          f1 = f2;
        }
        i += 1;
      }
    }
    float f2 = -1.0F;
    return f2;
  }
}

/* Location:
 * Qualified Name:     hu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */