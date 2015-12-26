import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.transaction.flyme.FlymeTransactionService;

public class pf
  extends BroadcastReceiver
{
  public pf(FlymeTransactionService paramFlymeTransactionService) {}
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.NETWORK_CONNECT_FAIL".equals(paramIntent.getAction()))
    {
      Log.e("FlymeTransactionService", "Receive NetworConnectFail intent");
      FlymeTransactionService.b(a);
    }
  }
}

/* Location:
 * Qualified Name:     pf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */