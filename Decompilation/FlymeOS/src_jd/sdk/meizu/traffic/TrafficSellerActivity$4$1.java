package sdk.meizu.traffic;

import android.util.Log;
import sdk.meizu.traffic.hybird.HyBirdView;
import sdk.meizu.traffic.hybird.JsCmd;

class TrafficSellerActivity$4$1
  implements Runnable
{
  TrafficSellerActivity$4$1(TrafficSellerActivity.4 param4, JsCmd paramJsCmd, String paramString) {}
  
  public void run()
  {
    if (TrafficSellerActivity.access$000(this$1.this$0).getWebView() != null)
    {
      val$authCallback.setMethodArgs(new String[] { "\"" + val$token + "\"" }).execute(TrafficSellerActivity.access$000(this$1.this$0).getWebView());
      Log.v(TrafficSellerActivity.access$100(), "getTokenCallback");
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.4.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */