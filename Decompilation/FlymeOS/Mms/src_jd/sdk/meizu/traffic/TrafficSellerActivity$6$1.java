package sdk.meizu.traffic;

import com.meizu.account.pay.MzPayPlatform;
import sdk.meizu.traffic.hybird.JsCmd;

class TrafficSellerActivity$6$1
  implements Runnable
{
  TrafficSellerActivity$6$1(TrafficSellerActivity.6 param6, TrafficOrder paramTrafficOrder, JsCmd paramJsCmd) {}
  
  public void run()
  {
    MzPayPlatform.pay(this$1.this$0, val$orderInfo, new TrafficSellerActivity.6.1.1(this));
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.6.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */