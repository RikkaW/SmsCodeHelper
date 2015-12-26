package com.xiaomi.push.service;

class PushClientsManager$ClientLoginInfo$1
  implements PushClientsManager.ClientLoginInfo.ClientStatusListener
{
  PushClientsManager$ClientLoginInfo$1(PushClientsManager.ClientLoginInfo paramClientLoginInfo) {}
  
  public void onChange(PushClientsManager.ClientStatus paramClientStatus1, PushClientsManager.ClientStatus paramClientStatus2, int paramInt)
  {
    if (paramClientStatus2 == PushClientsManager.ClientStatus.binding)
    {
      PushClientsManager.ClientLoginInfo.access$100(this$0).executeJobDelayed(PushClientsManager.ClientLoginInfo.access$000(this$0), 60000L);
      return;
    }
    PushClientsManager.ClientLoginInfo.access$100(this$0).removeJobs(PushClientsManager.ClientLoginInfo.access$000(this$0));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.PushClientsManager.ClientLoginInfo.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */