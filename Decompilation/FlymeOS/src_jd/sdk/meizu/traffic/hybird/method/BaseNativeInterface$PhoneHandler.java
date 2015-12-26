package sdk.meizu.traffic.hybird.method;

import sdk.meizu.traffic.hybird.JsCmd;

public abstract interface BaseNativeInterface$PhoneHandler
{
  public abstract void getAreaMISP(String paramString, JsCmd paramJsCmd);
  
  public abstract void getDefaultNum(JsCmd paramJsCmd);
  
  public abstract void getPackageName(JsCmd paramJsCmd);
  
  public abstract void getPhoneImei(JsCmd paramJsCmd);
  
  public abstract void openContacts(JsCmd paramJsCmd);
  
  public abstract void suggest(String paramString, JsCmd paramJsCmd);
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.BaseNativeInterface.PhoneHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */