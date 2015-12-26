package com.xiaomi.push.service;

class XMPushService$4
  implements PushClientsManager.ClientChangeListener
{
  XMPushService$4(XMPushService paramXMPushService) {}
  
  public void onChange()
  {
    XMPushService.access$200(this$0);
    if (PushClientsManager.getInstance().getActiveClientCount() <= 0) {
      this$0.executeJob(new XMPushService.DisconnectJob(this$0, 12, null));
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */