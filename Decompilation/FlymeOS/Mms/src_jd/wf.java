import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class wf
  implements DialogInterface.OnClickListener
{
  wf(DialogInterface.OnClickListener paramOnClickListener) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    switch (paramInt)
    {
    default: 
      paramDialogInterface.dismiss();
      return;
    }
    a.onClick(paramDialogInterface, paramInt);
  }
}

/* Location:
 * Qualified Name:     wf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */