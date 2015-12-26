package com.xiaomi.mipush.sdk;

import java.util.List;

@Deprecated
public abstract class MiPushClient$MiPushClientCallback
{
  private String category;
  
  protected String getCategory()
  {
    return category;
  }
  
  public void onCommandResult(String paramString1, long paramLong, String paramString2, List<String> paramList) {}
  
  public void onInitializeResult(long paramLong, String paramString1, String paramString2) {}
  
  public void onReceiveMessage(MiPushMessage paramMiPushMessage) {}
  
  public void onReceiveMessage(String paramString1, String paramString2, String paramString3, boolean paramBoolean) {}
  
  public void onSubscribeResult(long paramLong, String paramString1, String paramString2) {}
  
  public void onUnsubscribeResult(long paramLong, String paramString1, String paramString2) {}
}

/* Location:
 * Qualified Name:     com.xiaomi.mipush.sdk.MiPushClient.MiPushClientCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */