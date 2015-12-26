package cn.com.xy.sms.sdk.action;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

final class c
  extends Thread
{
  private c(NearbyPoint paramNearbyPoint) {}
  
  public final void run()
  {
    if ((NearbyPoint.a(a) < 0.0D) || (NearbyPoint.b(a) < 0.0D) || (NearbyPoint.c(a) == null) || (NearbyPoint.c(a).equalsIgnoreCase("")))
    {
      NearbyPoint.d(a).obtainMessage(4098).sendToTarget();
      return;
    }
    Object localObject = NearbyPoint.a(a, NearbyPoint.c(a), NearbyPoint.a(a), NearbyPoint.b(a), 20000, NearbyPoint.e(a));
    if (localObject == null)
    {
      NearbyPoint.d(a).obtainMessage(4099).sendToTarget();
      return;
    }
    try
    {
      localObject = new FutureTask(new b((String)localObject));
      new Thread((Runnable)localObject).start();
      localObject = (String)((FutureTask)localObject).get(5000L, TimeUnit.MILLISECONDS);
      Message localMessage = NearbyPoint.d(a).obtainMessage(4097);
      Bundle localBundle = new Bundle();
      localBundle.putString("queryResult", (String)localObject);
      localMessage.setData(localBundle);
      localMessage.sendToTarget();
      return;
    }
    catch (Throwable localThrowable)
    {
      NearbyPoint.d(a).obtainMessage(4100).sendToTarget();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.action.c
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */