package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.misc.BuildSettings;
import com.xiaomi.smack.ConnectionConfiguration;

class XMPushService$5
  extends XMPushService.Job
{
  XMPushService$5(XMPushService paramXMPushService, int paramInt1, int paramInt2, byte[] paramArrayOfByte, String paramString)
  {
    super(paramInt1);
  }
  
  public String getDesc()
  {
    return "clear account cache.";
  }
  
  public void process()
  {
    MIPushAccountUtils.clearAccount(this$0);
    PushClientsManager.getInstance().deactivateAllClientByChid("5");
    BuildSettings.setEnvType(val$envType);
    XMPushService.access$300(this$0).setHost(ConnectionConfiguration.getXmppServerHost());
    this$0.registerForMiPushApp(val$payload, val$packageName);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */