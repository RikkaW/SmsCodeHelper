import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.ServiceState;

class abk
  extends BroadcastReceiver
{
  abk(abh.a parama) {}
  
  public void onReceive(Context arg1, Intent paramIntent)
  {
    if ("android.intent.action.SERVICE_STATE".equals(paramIntent.getAction()))
    {
      ??? = aau.a("android.telephony.ServiceState", "newFromBundle", Bundle.class, paramIntent.getExtras());
      if ((??? != null) && ((??? instanceof ServiceState))) {}
      for (boolean bool = ((ServiceState)???).getRoaming();; bool = false) {
        synchronized (abh.a.b())
        {
          abh.a.a(a, abh.a.a(abh.a.a(a), bool));
          return;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     abk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */