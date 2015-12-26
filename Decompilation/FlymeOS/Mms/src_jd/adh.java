import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.view.MessageListItem;

public class adh
  implements DialogInterface.OnClickListener
{
  public adh(MessageListItem paramMessageListItem) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    com.android.mms.view.MessageListItemBase.ab = true;
    com.android.mms.view.MessageListItemBase.ad = a.M.a();
    MessageListItem.a(a);
  }
}

/* Location:
 * Qualified Name:     adh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */