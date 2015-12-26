import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class we
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1) {
      paramDialogInterface.dismiss();
    }
  }
}

/* Location:
 * Qualified Name:     we
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */