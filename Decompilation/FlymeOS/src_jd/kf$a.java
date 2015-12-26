import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.util.Log;
import com.android.mms.location.BaseGetLocationView;

class kf$a
  extends BroadcastReceiver
{
  private kf$a(kf paramkf) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool = paramIntent.getBooleanExtra("noConnectivity", false);
    paramContext = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    Log.d("location/BaseGetLocationActivity", "onReceive -> networkInfo : " + paramContext);
    if ((paramContext == null) || ((paramContext.getType() != 1) && (paramContext.getType() != 0))) {}
    for (;;)
    {
      return;
      if ((!bool) && (paramContext.isConnected())) {}
      for (bool = true; (!a.j()) && (bool != a.g); bool = false)
      {
        a.g = bool;
        if (!a.g) {
          a.a.a(2131493349, "onReceive");
        }
        kf.a(a);
        a.a.a(a.g);
        a.a.b(a.g);
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     kf.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */