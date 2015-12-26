import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class aif
  extends BaseAdapter
{
  private final Context a;
  private aif.a[] b;
  private int c = 0;
  private int d = 0;
  private boolean e = true;
  private boolean f = true;
  private boolean g;
  
  public aif(Context paramContext, int paramInt)
  {
    a = paramContext;
    b = new aif.a[2];
  }
  
  protected int a(int paramInt1, int paramInt2)
  {
    return 1;
  }
  
  protected View a(int paramInt1, Cursor paramCursor, int paramInt2, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null) {}
    for (;;)
    {
      a(paramView, paramInt1, paramCursor, paramInt2);
      return paramView;
      paramView = a(a, paramInt1, paramCursor, paramInt2, paramViewGroup);
    }
  }
  
  protected View a(int paramInt, Cursor paramCursor, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null) {}
    for (;;)
    {
      a(paramView, paramInt, paramCursor);
      return paramView;
      paramView = a(a, paramInt, paramCursor, paramViewGroup);
    }
  }
  
  protected abstract View a(Context paramContext, int paramInt1, Cursor paramCursor, int paramInt2, ViewGroup paramViewGroup);
  
  protected View a(Context paramContext, int paramInt, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    return null;
  }
  
  public void a(int paramInt, Cursor paramCursor)
  {
    Cursor localCursor = b[paramInt].c;
    if (localCursor != paramCursor)
    {
      if ((localCursor != null) && (!localCursor.isClosed())) {
        localCursor.close();
      }
      b[paramInt].c = paramCursor;
      if (paramCursor != null) {
        b[paramInt].d = paramCursor.getColumnIndex("_id");
      }
      e();
      notifyDataSetChanged();
    }
  }
  
  public void a(int paramInt, boolean paramBoolean)
  {
    b[paramInt].b = paramBoolean;
    e();
  }
  
  public void a(aif.a parama)
  {
    if (c >= b.length)
    {
      arrayOfa = new aif.a[c + 2];
      System.arraycopy(b, 0, arrayOfa, 0, c);
      b = arrayOfa;
    }
    aif.a[] arrayOfa = b;
    int i = c;
    c = (i + 1);
    arrayOfa[i] = parama;
    e();
    notifyDataSetChanged();
  }
  
  protected void a(View paramView, int paramInt, Cursor paramCursor) {}
  
  protected abstract void a(View paramView, int paramInt1, Cursor paramCursor, int paramInt2);
  
  public void a(boolean paramBoolean)
  {
    f = paramBoolean;
    if ((paramBoolean) && (g)) {
      notifyDataSetChanged();
    }
  }
  
  public void a(boolean paramBoolean1, boolean paramBoolean2)
  {
    a(new aif.a(paramBoolean1, paramBoolean2));
  }
  
  public boolean areAllItemsEnabled()
  {
    int i = 0;
    while (i < c)
    {
      if (b[i].b) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public void b(int paramInt)
  {
    Cursor localCursor = b[paramInt].c;
    if ((localCursor != null) && (!localCursor.isClosed())) {
      localCursor.close();
    }
    System.arraycopy(b, paramInt + 1, b, paramInt, c - paramInt - 1);
    c -= 1;
    e();
    notifyDataSetChanged();
  }
  
  protected boolean b(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public aif.a c(int paramInt)
  {
    if (paramInt >= c) {
      throw new ArrayIndexOutOfBoundsException(paramInt);
    }
    return b[paramInt];
  }
  
  public Context c()
  {
    return a;
  }
  
  public Cursor d(int paramInt)
  {
    return b[paramInt].c;
  }
  
  public void d()
  {
    int i = 0;
    while (i < c)
    {
      Cursor localCursor = b[i].c;
      if ((localCursor != null) && (!localCursor.isClosed()))
      {
        localCursor.close();
        b[i].c = null;
      }
      i += 1;
    }
    c = 0;
    e();
    notifyDataSetChanged();
  }
  
  public int e(int paramInt)
  {
    int i = 0;
    g();
    int k;
    for (int j = 0; i < c; j = k)
    {
      k = b[i].e + j;
      if ((paramInt >= j) && (paramInt < k)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  protected void e()
  {
    e = false;
  }
  
  public int f()
  {
    return c;
  }
  
  public int f(int paramInt)
  {
    int i = 0;
    int k;
    for (int j = 0; i < c; j = k)
    {
      k = b[i].e + j;
      if ((paramInt >= j) && (paramInt < k)) {
        return paramInt - j;
      }
      i += 1;
    }
    return -1;
  }
  
  protected void g()
  {
    if (e) {
      return;
    }
    d = 0;
    int j = 0;
    if (j < c)
    {
      Cursor localCursor = b[j].c;
      if (localCursor != null) {}
      for (int i = localCursor.getCount();; i = 0)
      {
        int k = i;
        if (b[j].b) {
          if (i == 0)
          {
            k = i;
            if (!b[j].a) {}
          }
          else
          {
            k = i + 1;
          }
        }
        b[j].e = k;
        d = (k + d);
        j += 1;
        break;
      }
    }
    e = true;
  }
  
  public int getCount()
  {
    g();
    return d;
  }
  
  public Object getItem(int paramInt)
  {
    int i = 0;
    g();
    int k;
    for (int j = 0; i < c; j = k)
    {
      k = b[i].e + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (b[i].b) {
          paramInt = j - 1;
        }
        if (paramInt == -1) {
          return null;
        }
        Cursor localCursor = b[i].c;
        localCursor.moveToPosition(paramInt);
        return localCursor;
      }
      i += 1;
    }
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    int i = 0;
    g();
    int k;
    for (int j = 0; i < c; j = k)
    {
      k = b[i].e + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (b[i].b) {
          paramInt = j - 1;
        }
        if (paramInt == -1) {
          return 0L;
        }
        if (b[i].d == -1) {
          return 0L;
        }
        Cursor localCursor = b[i].c;
        if ((localCursor == null) || (localCursor.isClosed()) || (!localCursor.moveToPosition(paramInt))) {
          return 0L;
        }
        return localCursor.getLong(b[i].d);
      }
      i += 1;
    }
    return 0L;
  }
  
  public int getItemViewType(int paramInt)
  {
    int i = 0;
    g();
    int k;
    for (int j = 0; i < c; j = k)
    {
      k = b[i].e + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        if ((b[i].b) && (paramInt - j == 0)) {
          return -1;
        }
        return a(i, paramInt);
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 0;
    g();
    int k;
    for (int j = 0; i < c; j = k)
    {
      k = b[i].e + j;
      if ((paramInt >= j) && (paramInt < k))
      {
        j = paramInt - j;
        paramInt = j;
        if (b[i].b) {
          paramInt = j - 1;
        }
        if (paramInt == -1) {}
        for (paramView = a(i, b[i].c, paramView, paramViewGroup); paramView == null; paramView = a(i, b[i].c, paramInt, paramView, paramViewGroup))
        {
          throw new NullPointerException("View should not be null, partition: " + i + " position: " + paramInt);
          if (!b[i].c.moveToPosition(paramInt)) {
            throw new IllegalStateException("Couldn't move cursor to position " + paramInt);
          }
        }
      }
      i += 1;
    }
    throw new ArrayIndexOutOfBoundsException(paramInt);
    return paramView;
  }
  
  public int getViewTypeCount()
  {
    return h() + 1;
  }
  
  public int h()
  {
    return 1;
  }
  
  public boolean isEnabled(int paramInt)
  {
    g();
    int i = 0;
    int k;
    for (int j = 0;; j = k)
    {
      if (i < c)
      {
        k = b[i].e + j;
        if ((paramInt < j) || (paramInt >= k)) {
          break label69;
        }
        paramInt -= j;
        if ((!b[i].b) || (paramInt != 0)) {}
      }
      else
      {
        return false;
      }
      return b(i, paramInt);
      label69:
      i += 1;
    }
  }
  
  public void notifyDataSetChanged()
  {
    if (f)
    {
      g = false;
      super.notifyDataSetChanged();
      return;
    }
    g = true;
  }
  
  public static class a
  {
    boolean a;
    boolean b;
    Cursor c;
    int d;
    int e;
    
    public a(boolean paramBoolean1, boolean paramBoolean2)
    {
      a = paramBoolean1;
      b = paramBoolean2;
    }
    
    public boolean a()
    {
      return b;
    }
  }
}

/* Location:
 * Qualified Name:     aif
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */