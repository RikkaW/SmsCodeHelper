package sdk.meizu.traffic;

import org.json.JSONArray;
import sdk.meizu.traffic.hybird.HyBirdView;
import sdk.meizu.traffic.hybird.JsCmd;

class TrafficSellerActivity$SearchContactThread$1
  implements Runnable
{
  TrafficSellerActivity$SearchContactThread$1(TrafficSellerActivity.SearchContactThread paramSearchContactThread, JSONArray paramJSONArray) {}
  
  public void run()
  {
    if ((TrafficSellerActivity.access$000(this$1.this$0) != null) && (TrafficSellerActivity.access$000(this$1.this$0).getWebView() != null)) {
      TrafficSellerActivity.SearchContactThread.access$300(this$1).setMethodArgs(new String[] { val$callbackDatas.toString() }).execute(TrafficSellerActivity.access$000(this$1.this$0).getWebView());
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.SearchContactThread.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */