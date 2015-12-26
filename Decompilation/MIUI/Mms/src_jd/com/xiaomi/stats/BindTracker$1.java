package com.xiaomi.stats;

import com.xiaomi.push.service.XMPushService.Job;

class BindTracker$1
  extends XMPushService.Job
{
  BindTracker$1(BindTracker paramBindTracker, int paramInt)
  {
    super(paramInt);
  }
  
  public String getDesc()
  {
    return "Handling bind stats";
  }
  
  public void process()
  {
    BindTracker.access$000(this$0);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.stats.BindTracker.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */