import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.ui.ConversationList;

public class ue
  implements DialogInterface.OnClickListener
{
  public ue(ConversationList paramConversationList) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ConversationList.b(a, true);
    ConversationList.e(a);
  }
}

/* Location:
 * Qualified Name:     ue
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */