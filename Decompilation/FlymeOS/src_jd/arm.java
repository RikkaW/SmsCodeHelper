import android.view.View;
import flyme.support.v7.widget.RecyclerView.LayoutParams;
import flyme.support.v7.widget.RecyclerView.h;

final class arm
  extends arl
{
  arm(RecyclerView.h paramh)
  {
    super(paramh, null);
  }
  
  public int a(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    return a.g(paramView) - leftMargin;
  }
  
  public void a(int paramInt)
  {
    a.f(paramInt);
  }
  
  public int b(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    int i = a.i(paramView);
    return rightMargin + i;
  }
  
  public int c()
  {
    return a.u();
  }
  
  public int c(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    int i = a.e(paramView);
    int j = leftMargin;
    return rightMargin + (i + j);
  }
  
  public int d()
  {
    return a.s() - a.w();
  }
  
  public int d(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    int i = a.f(paramView);
    int j = topMargin;
    return bottomMargin + (i + j);
  }
  
  public int e()
  {
    return a.s();
  }
  
  public int f()
  {
    return a.s() - a.u() - a.w();
  }
  
  public int g()
  {
    return a.w();
  }
}

/* Location:
 * Qualified Name:     arm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */