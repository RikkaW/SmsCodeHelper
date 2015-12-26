import android.view.View;
import android.widget.ListView;
import com.android.mms.ui.MessagingPreferenceActivity;

public class wu
  implements Runnable
{
  public wu(MessagingPreferenceActivity paramMessagingPreferenceActivity, int paramInt) {}
  
  public void run()
  {
    View localView = MessagingPreferenceActivity.a(b).getChildAt(a);
    if (localView != null) {
      localView.setBackgroundResource(2131820869);
    }
  }
}

/* Location:
 * Qualified Name:     wu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */