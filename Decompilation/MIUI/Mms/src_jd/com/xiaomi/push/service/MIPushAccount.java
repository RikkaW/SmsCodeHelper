package com.xiaomi.push.service;

import android.content.Context;
import java.util.Locale;

public class MIPushAccount
{
  public final String account;
  protected final String appId;
  protected final String appToken;
  protected final int envType;
  protected final String packageName;
  protected final String security;
  protected final String token;
  
  public MIPushAccount(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt)
  {
    account = paramString1;
    token = paramString2;
    security = paramString3;
    appId = paramString4;
    appToken = paramString5;
    packageName = paramString6;
    envType = paramInt;
  }
  
  private static boolean isMIUIPush(Context paramContext)
  {
    return paramContext.getPackageName().equals("com.xiaomi.xmsf");
  }
  
  public PushClientsManager.ClientLoginInfo toClientLoginInfo(XMPushService paramXMPushService)
  {
    PushClientsManager.ClientLoginInfo localClientLoginInfo = new PushClientsManager.ClientLoginInfo(paramXMPushService);
    pkgName = paramXMPushService.getPackageName();
    userId = account;
    security = security;
    token = token;
    chid = "5";
    authMethod = "XMPUSH-PASS";
    kick = false;
    clientExtra = "sdk_ver:7";
    if (isMIUIPush(paramXMPushService)) {}
    for (String str = "1000271";; str = appId)
    {
      cloudExtra = String.format("%1$s:%2$s,%3$s:%4$s", new Object[] { "appid", str, "locale", Locale.getDefault().toString() });
      mClientEventDispatcher = paramXMPushService.getClientEventDispatcher();
      return localClientLoginInfo;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.MIPushAccount
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */