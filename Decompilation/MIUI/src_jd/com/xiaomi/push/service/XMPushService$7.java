package com.xiaomi.push.service;

class XMPushService$7
  implements PushClientsManager.ClientLoginInfo.ClientStatusListener
{
  XMPushService$7(XMPushService paramXMPushService) {}
  
  public void onChange(PushClientsManager.ClientStatus paramClientStatus1, PushClientsManager.ClientStatus paramClientStatus2, int paramInt)
  {
    if (paramClientStatus2 == PushClientsManager.ClientStatus.binded)
    {
      MIPushClientManager.processPendingRegistrationRequest(this$0);
      MIPushClientManager.processPendingMessages(this$0);
    }
    while (paramClientStatus2 != PushClientsManager.ClientStatus.unbind) {
      return;
    }
    MIPushClientManager.notifyRegisterError(this$0, 70000001, " the push is not connected.");
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */