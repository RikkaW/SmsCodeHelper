import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;

class acg
  implements DialogInterface.OnClickListener
{
  acg(abu paramabu, ActionBase paramActionBase, DateReminderAction paramDateReminderAction, String paramString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == 0) {
      a.doAction(abu.c(d));
    }
    do
    {
      return;
      if (paramInt == 1)
      {
        paramDialogInterface = new Intent("android.intent.action.VIEW");
        paramDialogInterface.putExtra("viewTime", b.startTime);
        paramDialogInterface.setData(Uri.parse("content://com.android.calendar/time/" + b.startTime));
        abu.c(d).startActivity(paramDialogInterface);
        return;
      }
    } while (paramInt != 2);
    ((ClipboardManager)abu.c(d).getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, c));
  }
}

/* Location:
 * Qualified Name:     acg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */