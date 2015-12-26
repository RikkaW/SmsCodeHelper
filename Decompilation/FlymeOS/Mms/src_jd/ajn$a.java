import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.meizu.statsapp.util.Utils;

public class ajn$a
  extends BroadcastReceiver
{
  public ajn$a(ajn paramajn) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction())) {
      ajn.a(a, Utils.getNetworkType(ajn.k(a)));
    }
    while (!ajn.l(a).equals(paramIntent.getAction())) {
      return;
    }
    ajn.a(a, Utils.getNetworkType(ajn.k(a)));
  }
}

/* Location:
 * Qualified Name:     ajn.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */