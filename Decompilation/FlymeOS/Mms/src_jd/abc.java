import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.android.mms.transaction.flyme.FlymeTransactionService;

class abc
  implements air
{
  abc(aba paramaba) {}
  
  public void a()
  {
    Log.d("SipSmsManager", "onServiceConnected...");
    a.a(aba.d(a));
  }
  
  public void b()
  {
    Log.d("SipSmsManager", "onServiceDisconnected...");
    a.b(aba.d(a));
    aba.a(a, null);
    Intent localIntent = new Intent(aba.c(a), FlymeTransactionService.class);
    localIntent.setAction("android.intent.action.ACTION_RESET_MSG_STATUS_WHEN_SERVICE_DISCONNECTED");
    localIntent.putExtra("From", aba.class.getName());
    aba.c(a).startService(localIntent);
  }
}

/* Location:
 * Qualified Name:     abc
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */