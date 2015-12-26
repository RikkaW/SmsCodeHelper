package cn.com.xy.sms.sdk.ui.popu.web;

import android.os.Handler;
import android.os.Message;

class NearbyPointList$a
  extends Thread
{
  private NearbyPointList$a(NearbyPointList paramNearbyPointList) {}
  
  public void run()
  {
    try
    {
      Thread.sleep(200L);
      NearbyPointList.l(a).obtainMessage(4101).sendToTarget();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      localInterruptedException.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.web.NearbyPointList.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */