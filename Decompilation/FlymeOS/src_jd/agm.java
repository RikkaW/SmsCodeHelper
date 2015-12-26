import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class agm
  extends BroadcastReceiver
{
  agm(aie paramaie) {}
  
  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent != null) {}
    try
    {
      if (paramIntent.getAction().equals("android.location.GPS_FIX_CHANGE")) {
        aie.b = false;
      }
      return;
    }
    catch (Exception paramContext) {}
  }
}

/* Location:
 * Qualified Name:     agm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */