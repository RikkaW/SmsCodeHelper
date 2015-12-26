import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

final class wi
  implements DialogInterface.OnClickListener
{
  wi(Context paramContext) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (wd.c(a.getContentResolver()))
    {
      wd.m(a);
      return;
    }
    try
    {
      paramDialogInterface = new Intent("android.settings.SETTINGS");
      paramDialogInterface.addFlags(268435456);
      a.startActivity(paramDialogInterface);
      return;
    }
    catch (Exception paramDialogInterface)
    {
      paramDialogInterface.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     wi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */