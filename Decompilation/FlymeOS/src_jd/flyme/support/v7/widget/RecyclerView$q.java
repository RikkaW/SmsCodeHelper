package flyme.support.v7.widget;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;
import android.view.View;
import java.util.List;

public class RecyclerView$q
{
  ArrayMap<RecyclerView.t, RecyclerView.g> a;
  ArrayMap<RecyclerView.t, RecyclerView.g> b;
  ArrayMap<Long, RecyclerView.t> c;
  final List<View> d;
  int e;
  private int f;
  private SparseArray<Object> g;
  private int h;
  private int i;
  private boolean j;
  private boolean k;
  private boolean l;
  private boolean m;
  
  private void a(ArrayMap<Long, RecyclerView.t> paramArrayMap, RecyclerView.t paramt)
  {
    int n = paramArrayMap.size() - 1;
    for (;;)
    {
      if (n >= 0)
      {
        if (paramt == paramArrayMap.valueAt(n)) {
          paramArrayMap.removeAt(n);
        }
      }
      else {
        return;
      }
      n -= 1;
    }
  }
  
  void a(View paramView)
  {
    d.remove(paramView);
  }
  
  void a(RecyclerView.t paramt)
  {
    a.remove(paramt);
    b.remove(paramt);
    if (c != null) {
      a(c, paramt);
    }
    d.remove(a);
  }
  
  public boolean a()
  {
    return k;
  }
  
  void b(View paramView)
  {
    if (!d.contains(paramView)) {
      d.add(paramView);
    }
  }
  
  public boolean b()
  {
    return m;
  }
  
  public int c()
  {
    return f;
  }
  
  public boolean d()
  {
    return f != -1;
  }
  
  public boolean e()
  {
    return j;
  }
  
  public int f()
  {
    if (k) {
      return h - i;
    }
    return e;
  }
  
  public String toString()
  {
    return "State{mTargetPosition=" + f + ", mPreLayoutHolderMap=" + a + ", mPostLayoutHolderMap=" + b + ", mData=" + g + ", mItemCount=" + e + ", mPreviousLayoutItemCount=" + h + ", mDeletedInvisibleItemCountSincePreviousLayout=" + i + ", mStructureChanged=" + j + ", mInPreLayout=" + k + ", mRunSimpleAnimations=" + l + ", mRunPredictiveAnimations=" + m + '}';
  }
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.q
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */