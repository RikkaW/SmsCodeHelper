package com.xiaomi.push.service;

import android.text.TextUtils;

class XMPushService$BindTimeoutJob
  extends XMPushService.Job
{
  private final PushClientsManager.ClientLoginInfo mLoginInfo;
  
  public XMPushService$BindTimeoutJob(PushClientsManager.ClientLoginInfo paramClientLoginInfo)
  {
    super(12);
    mLoginInfo = paramClientLoginInfo;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof BindTimeoutJob)) {
      return false;
    }
    return TextUtils.equals(mLoginInfo.chid, mLoginInfo.chid);
  }
  
  public String getDesc()
  {
    return "bind time out. chid=" + mLoginInfo.chid;
  }
  
  public int hashCode()
  {
    return mLoginInfo.chid.hashCode();
  }
  
  public void process()
  {
    mLoginInfo.setStatus(PushClientsManager.ClientStatus.unbind, 1, 21, null, null);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.BindTimeoutJob
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */