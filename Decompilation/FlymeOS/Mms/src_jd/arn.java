import android.view.View;
import flyme.support.v7.widget.RecyclerView.LayoutParams;
import flyme.support.v7.widget.RecyclerView.h;

final class arn
  extends arl
{
  arn(RecyclerView.h paramh)
  {
    super(paramh, null);
  }
  
  public int a(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    return a.h(paramView) - topMargin;
  }
  
  public void a(int paramInt)
  {
    a.g(paramInt);
  }
  
  public int b(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    int i = a.j(paramView);
    return bottomMargin + i;
  }
  
  public int c()
  {
    return a.v();
  }
  
  public int c(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    int i = a.f(paramView);
    int j = topMargin;
    return bottomMargin + (i + j);
  }
  
  public int d()
  {
    return a.t() - a.x();
  }
  
  public int d(View paramView)
  {
    RecyclerView.LayoutParams localLayoutParams = (RecyclerView.LayoutParams)paramView.getLayoutParams();
    int i = a.e(paramView);
    int j = leftMargin;
    return rightMargin + (i + j);
  }
  
  public int e()
  {
    return a.t();
  }
  
  public int f()
  {
    return a.t() - a.v() - a.x();
  }
  
  public int g()
  {
    return a.x();
  }
}

/* Location:
 * Qualified Name:     arn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */