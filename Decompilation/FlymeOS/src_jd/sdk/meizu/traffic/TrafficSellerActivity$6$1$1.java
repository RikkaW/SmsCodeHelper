package sdk.meizu.traffic;

import android.widget.Toast;
import com.meizu.account.pay.OutTradeOrderInfo;
import com.meizu.account.pay.PayListener;
import sdk.meizu.traffic.hybird.HyBirdView;
import sdk.meizu.traffic.hybird.JsCmd;

class TrafficSellerActivity$6$1$1
  implements PayListener
{
  TrafficSellerActivity$6$1$1(TrafficSellerActivity.6.1 param1) {}
  
  public void onPayResult(int paramInt, OutTradeOrderInfo paramOutTradeOrderInfo, String paramString)
  {
    boolean bool;
    switch (paramInt)
    {
    case 1: 
    default: 
      Toast.makeText(this$2.this$1.this$0, paramString, 0).show();
      bool = false;
    }
    for (;;)
    {
      if ((TrafficSellerActivity.access$000(this$2.this$1.this$0) != null) && (TrafficSellerActivity.access$000(this$2.this$1.this$0).getWebView() != null)) {
        this$2.val$callback.setMethodArgs(new String[] { String.valueOf(bool) }).execute(TrafficSellerActivity.access$000(this$2.this$1.this$0).getWebView());
      }
      return;
      bool = true;
      continue;
      bool = false;
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.TrafficSellerActivity.6.1.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */