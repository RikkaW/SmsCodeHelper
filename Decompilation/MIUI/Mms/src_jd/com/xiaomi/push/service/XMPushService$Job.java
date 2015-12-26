package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.MyLog;

public abstract class XMPushService$Job
{
  protected int type;
  
  public XMPushService$Job(int paramInt)
  {
    type = paramInt;
  }
  
  public abstract String getDesc();
  
  public abstract void process();
  
  public void run()
  {
    if ((type != 4) && (type != 8)) {
      MyLog.warn("JOB: " + getDesc());
    }
    process();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.XMPushService.Job
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */