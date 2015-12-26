package sdk.meizu.traffic;

import sdk.meizu.traffic.hybird.HyBirdView;
import sdk.meizu.traffic.hybird.method.BaseNativeInterface.ImHandler;
import sdk.meizu.traffic.util.InputMethodHelper;

class TrafficSellerActivity$7
  implements BaseNativeInterface.ImHandler
{
  TrafficSellerActivity$7(TrafficSellerActivity paramTrafficSellerActivity) {}
  
  public void closeInputMethod()
  {
    if ((TrafficSellerActivity.access$000(this$0) != null) && (TrafficSellerActivity.access$000(this$0).getWebView() != null)) {
      InputMethodHelper.closeInputMethod(this$0, TrafficSellerActivity.access$000(this$0).getWebView());
    }
  }
  
  public void showInputMethod()
  {
    if ((TrafficSellerActivity.access$000(this$0) != null) && (TrafficSellerActivity.access$000(this$0).getWebView() != null)) {
      InputMethodHelper.showInputMethod(this$0, TrafficSellerActivity.access$000(this$0).getWebView());
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */