import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.view.MeizuSearchListItem;

public class acz
  implements View.OnClickListener
{
  public acz(MeizuSearchListItem paramMeizuSearchListItem) {}
  
  public void onClick(View paramView)
  {
    paramView = MeizuSearchListItem.a(a).h();
    if ((paramView != null) && (paramView.size() > 0))
    {
      paramView = (gm)paramView.get(0);
      wd.a(MeizuSearchListItem.b(a), paramView.d(), paramView.k(), paramView.n(), false, paramView.p());
    }
  }
}

/* Location:
 * Qualified Name:     acz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */