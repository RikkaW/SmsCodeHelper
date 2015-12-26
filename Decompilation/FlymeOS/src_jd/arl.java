import android.view.View;
import flyme.support.v7.widget.RecyclerView.h;

public abstract class arl
{
  protected final RecyclerView.h a;
  private int b = Integer.MIN_VALUE;
  
  private arl(RecyclerView.h paramh)
  {
    a = paramh;
  }
  
  public static arl a(RecyclerView.h paramh)
  {
    return new arm(paramh);
  }
  
  public static arl a(RecyclerView.h paramh, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      throw new IllegalArgumentException("invalid orientation");
    case 0: 
      return a(paramh);
    }
    return b(paramh);
  }
  
  public static arl b(RecyclerView.h paramh)
  {
    return new arn(paramh);
  }
  
  public abstract int a(View paramView);
  
  public void a()
  {
    b = f();
  }
  
  public abstract void a(int paramInt);
  
  public int b()
  {
    if (Integer.MIN_VALUE == b) {
      return 0;
    }
    return f() - b;
  }
  
  public abstract int b(View paramView);
  
  public abstract int c();
  
  public abstract int c(View paramView);
  
  public abstract int d();
  
  public abstract int d(View paramView);
  
  public abstract int e();
  
  public abstract int f();
  
  public abstract int g();
}

/* Location:
 * Qualified Name:     arl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */