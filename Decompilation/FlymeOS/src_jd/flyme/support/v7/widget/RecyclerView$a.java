package flyme.support.v7.widget;

import android.support.v4.os.TraceCompat;
import android.view.ViewGroup;

public abstract class RecyclerView$a<VH extends RecyclerView.t>
{
  private final RecyclerView.b a;
  private boolean b;
  
  public abstract int a();
  
  public int a(int paramInt)
  {
    return 0;
  }
  
  public abstract VH a(ViewGroup paramViewGroup, int paramInt);
  
  public void a(RecyclerView.c paramc)
  {
    a.registerObserver(paramc);
  }
  
  public void a(VH paramVH) {}
  
  public abstract void a(VH paramVH, int paramInt);
  
  public void a(RecyclerView paramRecyclerView) {}
  
  public long b(int paramInt)
  {
    return -1L;
  }
  
  public final VH b(ViewGroup paramViewGroup, int paramInt)
  {
    TraceCompat.beginSection("RV CreateView");
    paramViewGroup = a(paramViewGroup, paramInt);
    e = paramInt;
    TraceCompat.endSection();
    return paramViewGroup;
  }
  
  public void b(RecyclerView.c paramc)
  {
    a.unregisterObserver(paramc);
  }
  
  public final void b(VH paramVH, int paramInt)
  {
    b = paramInt;
    if (b()) {
      d = b(paramInt);
    }
    paramVH.a(1, 519);
    TraceCompat.beginSection("RV OnBindView");
    a(paramVH, paramInt);
    TraceCompat.endSection();
  }
  
  public void b(RecyclerView paramRecyclerView) {}
  
  public final boolean b()
  {
    return b;
  }
  
  public boolean b(VH paramVH)
  {
    return false;
  }
  
  public void c(VH paramVH) {}
}

/* Location:
 * Qualified Name:     flyme.support.v7.widget.RecyclerView.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */