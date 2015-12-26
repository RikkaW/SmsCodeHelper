import android.util.Log;
import java.util.ArrayList;

public abstract class hx
  implements aua
{
  final aud a;
  
  hx(aud paramaud)
  {
    a = paramaud;
  }
  
  private boolean k()
  {
    Object localObject2 = g();
    Object localObject1 = h();
    if ((((aun)localObject2).a() == 1) && (((aun)localObject1).a() == 1))
    {
      localObject2 = ((aun)localObject2).a(0);
      localObject1 = ((aun)localObject1).a(0);
      return (((aum)localObject2).a() == 0.0D) && (((aum)localObject1).a() == 0.0D);
    }
    return false;
  }
  
  public float b()
  {
    float f = 0.0F;
    try
    {
      String str = a.getAttribute("dur");
      if (str != null)
      {
        f = im.a(str);
        f /= 1000.0F;
      }
      return f;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return 0.0F;
  }
  
  public void b(float paramFloat)
  {
    a.setAttribute("dur", Integer.toString((int)(1000.0F * paramFloat)) + "ms");
  }
  
  int e()
  {
    return 255;
  }
  
  abstract aua f();
  
  public aun g()
  {
    String[] arrayOfString = a.getAttribute("begin").split(";");
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    for (;;)
    {
      if (i < arrayOfString.length) {}
      try
      {
        localArrayList.add(new im(arrayOfString[i], g_()));
        i += 1;
        continue;
        if (localArrayList.size() == 0) {
          localArrayList.add(new im("0", 255));
        }
        return new in(localArrayList);
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
    }
  }
  
  int g_()
  {
    return 255;
  }
  
  public aun h()
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    Object localObject = a.getAttribute("end").split(";");
    int k = localObject.length;
    int i;
    if ((k != 1) || (localObject[0].length() != 0))
    {
      i = 0;
      for (;;)
      {
        if (i < k) {
          try
          {
            localArrayList.add(new im(localObject[i], e()));
            i += 1;
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            for (;;)
            {
              Log.e("ElementTimeImpl", "Malformed time value.", localIllegalArgumentException);
            }
          }
        }
      }
    }
    float f;
    if (localArrayList.size() == 0)
    {
      f = b();
      if (f >= 0.0F) {
        break label149;
      }
      localArrayList.add(new im("indefinite", e()));
    }
    for (;;)
    {
      return new in(localArrayList);
      label149:
      localObject = g();
      i = j;
      while (i < ((aun)localObject).a())
      {
        localArrayList.add(new im(((aun)localObject).a(i).c() + f + "s", e()));
        i += 1;
      }
    }
  }
  
  public short i()
  {
    String str = a.getAttribute("fill");
    if (str.equalsIgnoreCase("freeze")) {}
    do
    {
      do
      {
        return 1;
        if (str.equalsIgnoreCase("remove")) {
          return 0;
        }
      } while ((str.equalsIgnoreCase("hold")) || (str.equalsIgnoreCase("transition")));
      if (!str.equalsIgnoreCase("auto"))
      {
        short s = j();
        if (s != 2) {
          return s;
        }
      }
    } while (((a.getAttribute("dur").length() == 0) && (a.getAttribute("end").length() == 0) && (a.getAttribute("repeatCount").length() == 0) && (a.getAttribute("repeatDur").length() == 0)) || (k()));
    return 0;
  }
  
  public short j()
  {
    short s2 = 1;
    Object localObject = a.getAttribute("fillDefault");
    short s1;
    if (((String)localObject).equalsIgnoreCase("remove")) {
      s1 = 0;
    }
    do
    {
      do
      {
        do
        {
          return s1;
          s1 = s2;
        } while (((String)localObject).equalsIgnoreCase("freeze"));
        if (((String)localObject).equalsIgnoreCase("auto")) {
          return 2;
        }
        s1 = s2;
      } while (((String)localObject).equalsIgnoreCase("hold"));
      s1 = s2;
    } while (((String)localObject).equalsIgnoreCase("transition"));
    localObject = f();
    if (localObject == null) {
      return 2;
    }
    return ((hx)localObject).j();
  }
}

/* Location:
 * Qualified Name:     hx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */