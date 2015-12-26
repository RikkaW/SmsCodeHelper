package sdk.meizu.traffic;

import android.os.Handler;
import android.os.Message;

class TrafficSellerActivity$1
  extends Handler
{
  TrafficSellerActivity$1(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void handleMessage(Message paramMessage)
  {
    if ((what == 10000) && (this$0.mSearchContactThread != null)) {
      this$0.mSearchContactThread.start();
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */