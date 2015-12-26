package miui.external;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class a
  implements DialogInterface.OnClickListener
{
  a(SdkErrorActivity paramSdkErrorActivity) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    a.finish();
    System.exit(0);
  }
}

/* Location:
 * Qualified Name:     miui.external.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */