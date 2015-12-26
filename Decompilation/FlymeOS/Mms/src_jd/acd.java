import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.ted.android.data.bubbleAction.ActionBase;

class acd
  implements DialogInterface.OnClickListener
{
  acd(abu paramabu, ActionBase paramActionBase, String paramString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == 0)
    {
      a.doAction(abu.c(c));
      return;
    }
    if (paramInt == 1)
    {
      ((ClipboardManager)abu.c(c).getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, b));
      return;
    }
    paramDialogInterface = new Intent("android.intent.action.INSERT_OR_EDIT");
    paramDialogInterface.setType("vnd.android.cursor.item/contact");
    paramDialogInterface.putExtra("email", b);
    paramDialogInterface.putExtra("com.android.contacts.extra.SHOW_CREATE_NEW_CONTACT_BUTTON", false);
    abu.c(c).startActivity(paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     acd
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */