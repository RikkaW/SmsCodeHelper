import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.ComposeMessageActivity;
import com.android.mms.view.MmsRichContainer;

public class aep
  implements View.OnClickListener
{
  public aep(MmsRichContainer paramMmsRichContainer) {}
  
  public void onClick(View paramView)
  {
    ((ComposeMessageActivity)MmsRichContainer.a(a)).f(true);
  }
}

/* Location:
 * Qualified Name:     aep
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */