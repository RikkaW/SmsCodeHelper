import android.widget.ListAdapter;
import android.widget.ListView;
import com.android.mms.ui.MessagingPreferenceActivity;

public class ws
  implements Runnable
{
  public ws(MessagingPreferenceActivity paramMessagingPreferenceActivity) {}
  
  public void run()
  {
    if (MessagingPreferenceActivity.a(a) != null)
    {
      MessagingPreferenceActivity.a(a).setSelection(MessagingPreferenceActivity.a(a).getAdapter().getCount());
      MessagingPreferenceActivity.b(a);
    }
  }
}

/* Location:
 * Qualified Name:     ws
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */