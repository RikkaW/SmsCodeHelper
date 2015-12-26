import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.android.mms.transaction.MessagingNotification.b;

public final class of
  implements DialogInterface.OnClickListener
{
  public of(Context paramContext, int paramInt, MessagingNotification.b[] paramArrayOfb) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    new Thread(new og(this), "MessagingNotification.delUnSeenMsg").start();
  }
}

/* Location:
 * Qualified Name:     of
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */