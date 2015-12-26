package flyme.support.v7.widget;

import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.View;

public abstract class RecyclerView$t
{
  public final View a;
  int b;
  int c;
  long d;
  int e;
  int f;
  t g;
  t h;
  RecyclerView i;
  private int j;
  private int k;
  private RecyclerView.m l;
  
  private boolean u()
  {
    return ((j & 0x10) == 0) && (ViewCompat.hasTransientState(a));
  }
  
  void a()
  {
    c = -1;
    f = -1;
  }
  
  void a(int paramInt1, int paramInt2)
  {
    j = (j & (paramInt2 ^ 0xFFFFFFFF) | paramInt1 & paramInt2);
  }
  
  void a(RecyclerView.m paramm)
  {
    l = paramm;
  }
  
  public final void a(boolean paramBoolean)
  {
    int m;
    if (paramBoolean)
    {
      m = k - 1;
      k = m;
      if (k >= 0) {
        break label64;
      }
      k = 0;
      Log.e("View", "isRecyclable decremented below 0: unmatched pair of setIsRecyable() calls for " + this);
    }
    label64:
    do
    {
      return;
      m = k + 1;
      break;
      if ((!paramBoolean) && (k == 1))
      {
        j |= 0x10;
        return;
      }
    } while ((!paramBoolean) || (k != 0));
    j &= 0xFFFFFFEF;
  }
  
  boolean a(int paramInt)
  {
    return (j & paramInt) != 0;
  }
  
  void b()
  {
    if (c == -1) {
      c = b;
    }
  }
  
  void b(int paramInt)
  {
    j |= paramInt;
  }
  
  boolean c()
  {
    return (j & 0x80) != 0;
  }
  
  public final int d()
  {
    if (f == -1) {
      return b;
    }
    return f;
  }
  
  public final long e()
  {
    return d;
  }
  
  public final int f()
  {
    return e;
  }
  
  boolean g()
  {
    return l != null;
  }
  
  void h()
  {
    l.d(this);
  }
  
  boolean i()
  {
    return (j & 0x20) != 0;
  }
  
  void j()
  {
    j &= 0xFFFFFFDF;
  }
  
  void k()
  {
    j &= 0xFEFF;
  }
  
  public boolean l()
  {
    return (j & 0x4) != 0;
  }
  
  boolean m()
  {
    return (j & 0x2) != 0;
  }
  
  boolean n()
  {
    return (j & 0x40) != 0;
  }
  
  boolean o()
  {
    return (j & 0x1) != 0;
  }
  
  boolean p()
  {
    return (j & 0x8) != 0;
  }
  
  boolean q()
  {
    return (j & 0x100) != 0;
  }
  
  boolean r()
  {
    return ((j & 0x200) != 0) || (l());
  }
  
  void s()
  {
    j = 0;
    b = -1;
    c = -1;
    d = -1L;
    f = -1;
    k = 0;
    g = null;
    h = null;
  }
  
  public final boolean t()
  {
    return ((j & 0x10) == 0) && (!ViewCompat.hasTransientState(a));
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("ViewHolder{" + Integer.toHexString(hashCode()) + " position=" + b + " id=" + d + ", oldPos=" + c + ", pLpos:" + f);
    if (g()) {
      localStringBuilder.append(" scrap");
    }
    if (l()) {
      localStringBuilder.append(" invalid");
    }
    if (!o()) {
      localStringBuilder.append(" unbound");
    }
    if (m()) {
      localStringBuilder.append(" update");
    }
    if (p()) {
      localStringBuilder.append(" removed");
    }
    if (c()) {
      localStringBuilder.append(" ignored");
    }
    if (n()) {
      localStringBuilder.append(" changed");
    }
    if (q()) {
      localStringBuilder.append(" tmpDetached");
    }
    if (!t()) {
      localStringBuilder.append(" not recyclable(" + k + ")");
    }
    if (r()) {
      localStringBuilder.append("undefined adapter position");
    }
    if (a.getParent() == null) {
      localStringBuilder.append(" no parent");
    }
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.t
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */