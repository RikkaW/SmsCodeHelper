package sdk.meizu.traffic.hybird.method;

import sdk.meizu.traffic.hybird.JsCmd;

public abstract interface BaseNativeInterface$SystemBarHandler
{
  public abstract void addMenuItem(String paramString1, String paramString2, boolean paramBoolean, JsCmd paramJsCmd);
  
  public abstract void handleMarginCallback(JsCmd paramJsCmd);
  
  public abstract void setTitle(String paramString);
  
  public abstract void updateMenuItem(String paramString1, String paramString2, boolean paramBoolean);
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.BaseNativeInterface.SystemBarHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */