import android.view.View;
import android.view.View.OnClickListener;

class vt$a
  implements View.OnClickListener
{
  private int b;
  
  private vt$a(vt paramvt) {}
  
  public void onClick(View paramView)
  {
    if (paramView.getId() == 2131886475) {
      b = ((Integer)paramView.getTag()).intValue();
    }
    paramView = (vt.c)vt.e(a).getItem(b);
    if ((paramView == null) || (!d)) {
      return;
    }
    if (vt.f(a))
    {
      vt.a(a, paramView);
      return;
    }
    a.a(paramView);
  }
}

/* Location:
 * Qualified Name:     vt.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */