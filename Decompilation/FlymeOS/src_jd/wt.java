import android.view.View;
import android.widget.ListView;
import com.android.mms.ui.MessagingPreferenceActivity;

public class wt
  implements Runnable
{
  public wt(MessagingPreferenceActivity paramMessagingPreferenceActivity, int paramInt) {}
  
  public void run()
  {
    View localView = MessagingPreferenceActivity.a(b).getChildAt(a);
    if (localView != null) {
      localView.setBackgroundResource(2130838696);
    }
  }
}

/* Location:
 * Qualified Name:     wt
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */