import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;

public class aek
  extends Drawable
  implements Drawable.Callback
{
  aek.b a;
  private int b = 0;
  private int[] c;
  private int[] d;
  private int[] e;
  private int[] f;
  private final Rect g = new Rect();
  private boolean h;
  
  aek()
  {
    this((aek.b)null, null);
  }
  
  aek(aek.b paramb, Resources paramResources)
  {
    paramb = a(paramb, paramResources);
    a = paramb;
    if (a > 0) {
      a();
    }
  }
  
  public aek(Drawable[] paramArrayOfDrawable)
  {
    this(paramArrayOfDrawable, null);
  }
  
  aek(Drawable[] paramArrayOfDrawable, aek.b paramb)
  {
    this(paramb, null);
    int j = paramArrayOfDrawable.length;
    paramb = new aek.a[j];
    int i = 0;
    while (i < j)
    {
      paramb[i] = new aek.a();
      a = paramArrayOfDrawable[i];
      paramArrayOfDrawable[i].setCallback(this);
      aek.b localb = a;
      d |= paramArrayOfDrawable[i].getChangingConfigurations();
      i += 1;
    }
    a.a = j;
    a.b = paramb;
    a();
  }
  
  private void a()
  {
    int i = a.a;
    if ((c != null) && (c.length >= i)) {
      return;
    }
    c = new int[i];
    d = new int[i];
    e = new int[i];
    f = new int[i];
  }
  
  private boolean a(int paramInt, aek.a parama)
  {
    Rect localRect = g;
    a.getPadding(localRect);
    if ((left != c[paramInt]) || (top != d[paramInt]) || (right != e[paramInt]) || (bottom != f[paramInt]))
    {
      c[paramInt] = left;
      d[paramInt] = top;
      e[paramInt] = right;
      f[paramInt] = bottom;
      return true;
    }
    return false;
  }
  
  aek.b a(aek.b paramb, Resources paramResources)
  {
    return new aek.b(paramb, this, paramResources);
  }
  
  public void draw(Canvas paramCanvas)
  {
    aek.a[] arrayOfa = a.b;
    int j = a.a;
    int i = 0;
    while (i < j)
    {
      a.draw(paramCanvas);
      i += 1;
    }
  }
  
  public int getChangingConfigurations()
  {
    return super.getChangingConfigurations() | a.c | a.d;
  }
  
  public Drawable.ConstantState getConstantState()
  {
    if (a.c())
    {
      a.c = getChangingConfigurations();
      return a;
    }
    return null;
  }
  
  public int getIntrinsicHeight()
  {
    return a.b[0].a.getIntrinsicHeight();
  }
  
  public int getIntrinsicWidth()
  {
    return a.b[0].a.getIntrinsicWidth();
  }
  
  public int getOpacity()
  {
    if (b != 0) {
      return b;
    }
    return a.a();
  }
  
  public boolean getPadding(Rect paramRect)
  {
    left = 0;
    top = 0;
    right = 0;
    bottom = 0;
    aek.a[] arrayOfa = a.b;
    int j = a.a;
    int i = 0;
    while (i < j)
    {
      a(i, arrayOfa[i]);
      i += 1;
    }
    left = c[0];
    top = d[0];
    right = e[0];
    bottom = f[0];
    return true;
  }
  
  public void invalidateDrawable(Drawable paramDrawable)
  {
    paramDrawable = getCallback();
    if (paramDrawable != null) {
      paramDrawable.invalidateDrawable(this);
    }
  }
  
  public boolean isStateful()
  {
    return a.b();
  }
  
  public Drawable mutate()
  {
    if ((!h) && (super.mutate() == this))
    {
      if (!a.c()) {
        throw new IllegalStateException("One or more children of this LayerDrawable does not have constant state; this drawable cannot be mutated.");
      }
      a = new aek.b(a, this, null);
      aek.a[] arrayOfa = a.b;
      int j = a.a;
      int i = 0;
      while (i < j)
      {
        a.mutate();
        i += 1;
      }
      h = true;
    }
    return this;
  }
  
  protected void onBoundsChange(Rect paramRect)
  {
    aek.a[] arrayOfa = a.b;
    int j = a.a;
    int i = 0;
    while (i < j)
    {
      aek.a locala = arrayOfa[i];
      a.setBounds(left + b + 0, top + c + 0, right - d - 0, bottom - e - 0);
      i += 1;
    }
  }
  
  protected boolean onLevelChange(int paramInt)
  {
    boolean bool = false;
    aek.a[] arrayOfa = a.b;
    int k = a.a;
    int i = 0;
    int j = 0;
    while (i < k)
    {
      aek.a locala = arrayOfa[i];
      if (a.setLevel(paramInt)) {
        bool = true;
      }
      if (a(i, locala)) {
        j = 1;
      }
      i += 1;
    }
    if (j != 0) {
      onBoundsChange(getBounds());
    }
    return bool;
  }
  
  protected boolean onStateChange(int[] paramArrayOfInt)
  {
    boolean bool = false;
    aek.a[] arrayOfa = a.b;
    int k = a.a;
    int i = 0;
    int j = 0;
    while (i < k)
    {
      aek.a locala = arrayOfa[i];
      if (a.setState(paramArrayOfInt)) {
        bool = true;
      }
      if (a(i, locala)) {
        j = 1;
      }
      i += 1;
    }
    if (j != 0) {
      onBoundsChange(getBounds());
    }
    return bool;
  }
  
  public void scheduleDrawable(Drawable paramDrawable, Runnable paramRunnable, long paramLong)
  {
    paramDrawable = getCallback();
    if (paramDrawable != null) {
      paramDrawable.scheduleDrawable(this, paramRunnable, paramLong);
    }
  }
  
  public void setAlpha(int paramInt)
  {
    aek.a[] arrayOfa = a.b;
    int i = a.a;
    0a.setAlpha(paramInt);
    1a.setAlpha(255 - paramInt);
  }
  
  public void setColorFilter(ColorFilter paramColorFilter)
  {
    aek.a[] arrayOfa = a.b;
    int j = a.a;
    int i = 0;
    while (i < j)
    {
      a.setColorFilter(paramColorFilter);
      i += 1;
    }
  }
  
  public void setDither(boolean paramBoolean)
  {
    aek.a[] arrayOfa = a.b;
    int j = a.a;
    int i = 0;
    while (i < j)
    {
      a.setDither(paramBoolean);
      i += 1;
    }
  }
  
  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    aek.a[] arrayOfa = a.b;
    int j = a.a;
    int i = 0;
    while (i < j)
    {
      a.setVisible(paramBoolean1, paramBoolean2);
      i += 1;
    }
    return bool;
  }
  
  public void unscheduleDrawable(Drawable paramDrawable, Runnable paramRunnable)
  {
    paramDrawable = getCallback();
    if (paramDrawable != null) {
      paramDrawable.unscheduleDrawable(this, paramRunnable);
    }
  }
  
  static class a
  {
    public Drawable a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;
  }
  
  static class b
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
    
    b(b paramb, aek paramaek, Resources paramResources)
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
}

/* Location:
 * Qualified Name:     aek
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */