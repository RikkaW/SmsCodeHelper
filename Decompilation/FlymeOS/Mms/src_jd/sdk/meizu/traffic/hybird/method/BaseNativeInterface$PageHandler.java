package sdk.meizu.traffic.hybird.method;

import sdk.meizu.traffic.hybird.JsCmd;

public abstract interface BaseNativeInterface$PageHandler
{
  public abstract void finishModule(boolean paramBoolean, String paramString);
  
  public abstract void finishPage(String paramString1, String paramString2);
  
  public abstract void handleBackPressed(String paramString);
  
  public abstract void handleHomePressed(String paramString);
  
  public abstract void startPage(String paramString1, String paramString2, JsCmd paramJsCmd);
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.hybird.method.BaseNativeInterface.PageHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */