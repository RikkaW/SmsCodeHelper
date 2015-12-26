import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.MmsApp;

public class ft
  extends BroadcastReceiver
{
  public ft(MmsApp paramMmsApp) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("MmsApp", "mThemeChangedReceiver onReceive = " + paramIntent.getAction());
    if ("com.meizu.theme.change".equals(paramIntent.getAction())) {
      aaa.c();
    }
  }
}

/* Location:
 * Qualified Name:     ft
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */