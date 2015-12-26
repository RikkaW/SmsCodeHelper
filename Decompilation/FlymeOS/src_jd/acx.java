import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.view.ConversationListItem;
import com.android.mms.view.ConversationListItem.a;

public class acx
  implements View.OnClickListener
{
  public acx(ConversationListItem.a parama) {}
  
  public void onClick(View paramView)
  {
    paramView = ConversationListItem.b(a.b).h();
    if ((paramView != null) && (paramView.size() > 0))
    {
      paramView = (gm)paramView.get(0);
      wd.a(ConversationListItem.a(a.b), paramView.d(), paramView.k(), paramView.n(), false, paramView.p());
    }
  }
}

/* Location:
 * Qualified Name:     acx
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */