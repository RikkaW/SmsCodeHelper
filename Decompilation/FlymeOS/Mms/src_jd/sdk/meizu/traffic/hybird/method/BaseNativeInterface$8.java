package sdk.meizu.traffic.hybird.method;

import sdk.meizu.traffic.hybird.JsCmd;

class BaseNativeInterface$8
  implements Runnable
{
  BaseNativeInterface$8(BaseNativeInterface paramBaseNativeInterface, String paramString1, String paramString2, String paramString3, boolean paramBoolean) {}
  
  public void run()
  {
    if (BaseNativeInterface.access$300(this$0) != null)
    {
      JsCmd localJsCmd = JsCmd.from("").setMethod(val$callbackStr);
      BaseNativeInterface.access$300(this$0).addMenuItem(val$key, val$text, val$enable, localJsCmd);
    }
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.BaseNativeInterface.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */