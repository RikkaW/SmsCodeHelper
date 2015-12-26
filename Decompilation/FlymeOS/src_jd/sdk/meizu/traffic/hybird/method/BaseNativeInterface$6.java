package sdk.meizu.traffic.hybird.method;

import android.webkit.WebView;
import sdk.meizu.traffic.hybird.JsCmd;

class BaseNativeInterface$6
  implements Runnable
{
  BaseNativeInterface$6(BaseNativeInterface paramBaseNativeInterface, String paramString, WebView paramWebView) {}
  
  public void run()
  {
    if (BaseNativeInterface.access$200(this$0) != null) {
      BaseNativeInterface.access$200(this$0).setMethodArgs(new String[] { val$token }).execute(val$webView);
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.BaseNativeInterface.6
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */