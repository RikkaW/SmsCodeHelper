import android.util.Log;
import java.util.ArrayList;

public class ht
  implements atw
{
  private ArrayList<ht.a> a;
  private atw b;
  
  public ht(atw paramatw)
  {
    b = paramatw;
  }
  
  public void a(String paramString, atv paramatv, boolean paramBoolean)
  {
    if ((paramString == null) || (paramString.equals("")) || (paramatv == null)) {
      return;
    }
    b(paramString, paramatv, paramBoolean);
    if (a == null) {
      a = new ArrayList();
    }
    a.add(new ht.a(paramString, paramatv, paramBoolean));
  }
  
  public boolean a(att paramatt)
  {
    paramatt = (hs)paramatt;
    if (!paramatt.c()) {
      throw new atu((short)0, "Event not initialized");
    }
    if ((paramatt.b() == null) || (paramatt.b().equals(""))) {
      throw new atu((short)0, "Unspecified even type");
    }
    paramatt.a(b);
    paramatt.a((short)2);
    paramatt.b(b);
    if ((!paramatt.e()) && (a != null))
    {
      int i = 0;
      for (;;)
      {
        if (i < a.size())
        {
          ht.a locala = (ht.a)a.get(i);
          if ((!c) && (a.equals(paramatt.b()))) {}
          try
          {
            b.a(paramatt);
            i += 1;
          }
          catch (Exception localException)
          {
            for (;;)
            {
              Log.w("EventTargetImpl", "Catched EventListener exception", localException);
            }
          }
        }
      }
    }
    if (paramatt.a()) {}
    return paramatt.d();
  }
  
  public void b(String paramString, atv paramatv, boolean paramBoolean)
  {
    if (a == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < a.size())
      {
        ht.a locala = (ht.a)a.get(i);
        if ((c == paramBoolean) && (b == paramatv) && (a.equals(paramString)))
        {
          a.remove(i);
          return;
        }
        i += 1;
      }
    }
  }
  
  static class a
  {
    final String a;
    final atv b;
    final boolean c;
    
    a(String paramString, atv paramatv, boolean paramBoolean)
    {
      a = paramString;
      b = paramatv;
      c = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     ht
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */