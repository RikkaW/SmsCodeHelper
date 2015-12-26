import android.accounts.Account;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aez
  implements afb
{
  private static String a = "vCard";
  private final List<aey> b = new ArrayList();
  private aey c;
  private final int d;
  private final Account e;
  private final List<afa> f = new ArrayList();
  
  public aez()
  {
    this(-1073741824, null, null);
  }
  
  public aez(int paramInt, Account paramAccount)
  {
    this(paramInt, paramAccount, null);
  }
  
  @Deprecated
  public aez(int paramInt, Account paramAccount, String paramString)
  {
    d = paramInt;
    e = paramAccount;
  }
  
  public void a()
  {
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext()) {
      ((afa)localIterator.next()).a();
    }
  }
  
  public void a(afa paramafa)
  {
    f.add(paramafa);
  }
  
  public void a(afj paramafj)
  {
    c.a(paramafj);
  }
  
  public void b()
  {
    Iterator localIterator = f.iterator();
    while (localIterator.hasNext()) {
      ((afa)localIterator.next()).b();
    }
  }
  
  public void c()
  {
    c = new aey(d, e);
    b.add(c);
  }
  
  public void d()
  {
    c.a();
    Object localObject = f.iterator();
    while (((Iterator)localObject).hasNext()) {
      ((afa)((Iterator)localObject).next()).a(c);
    }
    int i = b.size();
    if (i > 1)
    {
      localObject = (aey)b.get(i - 2);
      ((aey)localObject).a(c);
    }
    for (c = ((aey)localObject);; c = null)
    {
      b.remove(i - 1);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     aez
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */