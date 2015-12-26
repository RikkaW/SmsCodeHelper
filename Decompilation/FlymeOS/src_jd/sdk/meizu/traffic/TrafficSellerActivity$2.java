package sdk.meizu.traffic;

import android.os.Handler;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface.LoadingHandler;

class TrafficSellerActivity$2
  implements BaseNativeInterface.LoadingHandler
{
  TrafficSellerActivity$2(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void startLoading(String paramString)
  {
    this$0.mUiHandler.post(new TrafficSellerActivity.2.1(this));
  }
  
  public void stopLoading()
  {
    this$0.mUiHandler.post(new TrafficSellerActivity.2.2(this));
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */