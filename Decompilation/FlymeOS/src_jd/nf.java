import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class nf
  implements DialogInterface.OnClickListener
{
  nf(ne paramne, String paramString, Context paramContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    new Thread(new ng(this), "MySdkDoAction.deleteMsgForDatabase").start();
  }
}

/* Location:
 * Qualified Name:     nf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */