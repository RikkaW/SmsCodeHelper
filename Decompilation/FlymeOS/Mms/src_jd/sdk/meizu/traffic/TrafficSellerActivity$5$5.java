package sdk.meizu.traffic;

import sdk.meizu.traffic.hybird.HyBirdView;
import sdk.meizu.traffic.hybird.JsCmd;

class TrafficSellerActivity$5$5
  implements Runnable
{
  TrafficSellerActivity$5$5(TrafficSellerActivity.5 param5, JsCmd paramJsCmd, String paramString) {}
  
  public void run()
  {
    if ((TrafficSellerActivity.access$000(this$1.this$0) != null) && (TrafficSellerActivity.access$000(this$1.this$0).getWebView() != null)) {
      val$callback.setMethodArgs(new String[] { "'" + val$geoInfo + "'" }).execute(TrafficSellerActivity.access$000(this$1.this$0).getWebView());
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.5.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */