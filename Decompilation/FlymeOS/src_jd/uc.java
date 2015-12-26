import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.android.mms.ui.ConversationList;
import com.android.mms.ui.MessagingPreferenceActivity;

public class uc
  implements View.OnClickListener
{
  public uc(ConversationList paramConversationList) {}
  
  public void onClick(View paramView)
  {
    paramView = new Intent(a, MessagingPreferenceActivity.class);
    a.startActivityIfNeeded(paramView, -1);
  }
}

/* Location:
 * Qualified Name:     uc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */