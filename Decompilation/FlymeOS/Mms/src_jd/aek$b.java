import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

class aek$b
  extends Drawable.ConstantState
{
  int a;
  aek.a[] b;
  int c;
  int d;
  private boolean e = false;
  private int f;
  private boolean g = false;
  private boolean h;
  private boolean i;
  private boolean j;
  
  aek$b(b paramb, aek paramaek, Resources paramResources)
  {
    if (paramb != null)
    {
      aek.a[] arrayOfa = b;
      int m = a;
      a = m;
      b = new aek.a[m];
      c = c;
      d = d;
      if (k < m)
      {
        Object localObject = b;
        aek.a locala = new aek.a();
        localObject[k] = locala;
        localObject = arrayOfa[k];
        if (paramResources != null) {}
        for (a = a.getConstantState().newDrawable(paramResources);; a = a.getConstantState().newDrawable())
        {
          a.setCallback(paramaek);
          b = b;
          c = c;
          d = d;
          e = e;
          f = f;
          k += 1;
          break;
        }
      }
      e = e;
      f = f;
      g = g;
      h = h;
      j = true;
      i = true;
      return;
    }
    a = 0;
    b = null;
  }
  
  public final int a()
  {
    if (e) {
      return f;
    }
    int n = a;
    if (n > 0) {}
    for (int k = b[0].a.getOpacity();; k = -2)
    {
      int m = 1;
      while (m < n)
      {
        k = Drawable.resolveOpacity(k, b[m].a.getOpacity());
        m += 1;
      }
    }
    f = k;
    e = true;
    return k;
  }
  
  public final boolean b()
  {
    boolean bool2 = false;
    if (g) {
      return h;
    }
    int m = a;
    int k = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (k < m)
      {
        if (b[k].a.isStateful()) {
          bool1 = true;
        }
      }
      else
      {
        h = bool1;
        g = true;
        return bool1;
      }
      k += 1;
    }
  }
  
  public boolean c()
  {
    int m;
    int k;
    if ((!i) && (b != null))
    {
      j = true;
      m = a;
      k = 0;
    }
    for (;;)
    {
      if (k < m)
      {
        if (b[k].a.getConstantState() == null) {
          j = false;
        }
      }
      else
      {
        i = true;
        return j;
      }
      k += 1;
    }
  }
  
  public int getChangingConfigurations()
  {
    return c;
  }
  
  public Drawable newDrawable()
  {
    return new aek(this, null);
  }
  
  public Drawable newDrawable(Resources paramResources)
  {
    return new aek(this, paramResources);
  }
}

/* Location:
 * Qualified Name:     aek.b
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */