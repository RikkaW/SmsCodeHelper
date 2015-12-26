import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.PhoneNumberAction;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import java.util.List;

class aby
  implements DialogInterface.OnClickListener
{
  aby(abu paramabu, List paramList) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface = (ActionBase)a.get(paramInt);
    if ((paramDialogInterface instanceof QuickReplyAction))
    {
      paramDialogInterface = ((QuickReplyAction)paramDialogInterface).getItem();
      abu.a(b, message, number);
      return;
    }
    if ((paramDialogInterface instanceof PhoneNumberAction))
    {
      abu.a(b, (PhoneNumberAction)paramDialogInterface);
      return;
    }
    paramDialogInterface.doAction(abu.c(b));
  }
}

/* Location:
 * Qualified Name:     aby
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */