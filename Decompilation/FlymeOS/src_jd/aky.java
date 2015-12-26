import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class aky
  extends BroadcastReceiver
{
  aky(aks paramaks) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    anf.c("Receive dialog show broadcast.");
    if ((a.d != null) && (a.d.isShowing())) {}
    try
    {
      a.d.dismiss();
      return;
    }
    catch (Exception paramContext)
    {
      anf.d("dismiss dialog exception:" + paramContext.getMessage());
      a.d.hide();
      aks.a(a);
    }
  }
}

/* Location:
 * Qualified Name:     aky
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */