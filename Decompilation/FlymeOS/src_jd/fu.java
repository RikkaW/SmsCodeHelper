import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.android.mms.MmsApp;

public class fu
  extends BroadcastReceiver
{
  public fu(MmsApp paramMmsApp) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("MmsApp", "mContactDataClearReceiver onReceive action = " + paramIntent.getAction() + ", package = " + paramIntent.getData().getSchemeSpecificPart());
    paramContext = paramIntent.getData().getSchemeSpecificPart();
    if ((paramContext.equals("com.android.providers.contacts")) || (paramContext.equals("com.meizu.netcontactservice"))) {
      MmsApp.a(a, false);
    }
  }
}

/* Location:
 * Qualified Name:     fu
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */