package sdk.meizu.traffic;

import android.os.Handler;
import sdk.meizu.traffic.hybird.JsCmd;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface.PayHandler;

class TrafficSellerActivity$6
  implements BaseNativeInterface.PayHandler
{
  TrafficSellerActivity$6(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void doPayAction(TrafficOrder paramTrafficOrder, JsCmd paramJsCmd)
  {
    this$0.mUiHandler.post(new TrafficSellerActivity.6.1(this, paramTrafficOrder, paramJsCmd));
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */