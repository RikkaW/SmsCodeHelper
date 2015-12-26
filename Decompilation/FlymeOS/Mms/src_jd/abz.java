import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class abz
  implements DialogInterface.OnClickListener
{
  abz(abu paramabu, String paramString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == 0)
    {
      paramDialogInterface = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + a));
      abu.c(b).startActivity(paramDialogInterface);
      return;
    }
    if (paramInt == 1)
    {
      paramDialogInterface = new Intent("android.intent.action.SENDTO", Uri.fromParts("smsto", a, null));
      abu.c(b).startActivity(paramDialogInterface);
      return;
    }
    if (paramInt == 2)
    {
      ((ClipboardManager)abu.c(b).getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, a));
      return;
    }
    paramDialogInterface = new Intent("android.intent.action.INSERT_OR_EDIT");
    paramDialogInterface.setType("vnd.android.cursor.item/contact");
    paramDialogInterface.putExtra("phone", a);
    paramDialogInterface.putExtra("com.android.contacts.extra.SHOW_CREATE_NEW_CONTACT_BUTTON", false);
    abu.c(b).startActivity(paramDialogInterface);
  }
}

/* Location:
 * Qualified Name:     abz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */