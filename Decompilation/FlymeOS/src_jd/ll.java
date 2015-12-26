import android.util.Log;
import com.android.mms.model.Model;
import java.util.ArrayList;
import java.util.Iterator;

public class ll
  extends Model
{
  private int a = 0;
  private lp b;
  private lp c;
  private lp d;
  private ArrayList<lp> e;
  private ke f = kd.a().b();
  
  public ll()
  {
    i();
    j();
    k();
  }
  
  public ll(lp paramlp, ArrayList<lp> paramArrayList)
  {
    b = paramlp;
    e = new ArrayList();
    paramlp = paramArrayList.iterator();
    while (paramlp.hasNext())
    {
      paramArrayList = (lp)paramlp.next();
      String str = paramArrayList.a();
      if (str.equals("Image")) {
        c = paramArrayList;
      } else if (str.equals("Text")) {
        d = paramArrayList;
      } else {
        e.add(paramArrayList);
      }
    }
    l();
  }
  
  private void i()
  {
    b = new lp(null, 0, 0, f.a(), f.b());
  }
  
  private void j()
  {
    if (b == null) {
      throw new IllegalStateException("Root-Layout uninitialized.");
    }
    c = new lp("Image", 0, 0, b.e(), f.c());
  }
  
  private void k()
  {
    if (b == null) {
      throw new IllegalStateException("Root-Layout uninitialized.");
    }
    d = new lp("Text", 0, f.c(), b.e(), f.d());
  }
  
  private void l()
  {
    if (b == null) {
      i();
    }
    if (c == null) {
      j();
    }
    if (d == null) {
      k();
    }
    if (c.d() == 0) {}
    for (int i = 0;; i = 1)
    {
      a = i;
      return;
    }
  }
  
  public lp a()
  {
    return c;
  }
  
  public lp a(String paramString)
  {
    if ("Image".equals(paramString)) {
      return c;
    }
    if ("Text".equals(paramString)) {
      return d;
    }
    Iterator localIterator = e.iterator();
    while (localIterator.hasNext())
    {
      lp locallp = (lp)localIterator.next();
      if (locallp.a().equals(paramString)) {
        return locallp;
      }
    }
    return null;
  }
  
  public void a(int paramInt)
  {
    if (b == null) {
      throw new IllegalStateException("Root-Layout uninitialized.");
    }
    if (f == null) {
      f = kd.a().b();
    }
    if (a != paramInt) {}
    switch (paramInt)
    {
    default: 
      Log.w("Mms/slideshow", "Unknown layout type: " + paramInt);
      return;
    case 0: 
      c.a(0);
      d.a(f.c());
      a = paramInt;
      a(true);
      return;
    }
    c.a(f.d());
    d.a(0);
    a = paramInt;
    a(true);
  }
  
  protected void a(lj paramlj)
  {
    if (b != null) {
      b.c(paramlj);
    }
    if (c != null) {
      c.c(paramlj);
    }
    if (d != null) {
      d.c(paramlj);
    }
  }
  
  public lp b()
  {
    return d;
  }
  
  protected void b(lj paramlj)
  {
    if (b != null) {
      b.d(paramlj);
    }
    if (c != null) {
      c.d(paramlj);
    }
    if (d != null) {
      d.d(paramlj);
    }
  }
  
  public ArrayList<lp> c()
  {
    ArrayList localArrayList = new ArrayList();
    if (c != null) {
      localArrayList.add(c);
    }
    if (d != null) {
      localArrayList.add(d);
    }
    return localArrayList;
  }
  
  public int d()
  {
    return b.e();
  }
  
  public int e()
  {
    return b.f();
  }
  
  public String f()
  {
    return b.g();
  }
  
  public int g()
  {
    return a;
  }
  
  protected void h()
  {
    if (b != null) {
      b.B();
    }
    if (c != null) {
      c.B();
    }
    if (d != null) {
      d.B();
    }
  }
}

/* Location:
 * Qualified Name:     ll
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */