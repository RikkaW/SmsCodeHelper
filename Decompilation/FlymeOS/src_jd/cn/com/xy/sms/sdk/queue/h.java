package cn.com.xy.sms.sdk.queue;

import android.os.Process;
import bs;
import cn.com.xy.sms.sdk.db.entity.j;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.db.entity.q;
import cn.com.xy.sms.sdk.iccid.IccidLocationUtil;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.e;
import java.util.concurrent.BlockingQueue;

final class h
  extends Thread
{
  public final void run()
  {
    setName("xiaoyuan_taskqueue");
    Process.setThreadPriority(g.b);
    for (;;)
    {
      try
      {
        i locali = (i)g.a.take();
        if (locali == null) {
          continue;
        }
        new StringBuilder("task =").append(locali).append(" type: ").append(a);
        switch (a)
        {
        case 1: 
          IccidLocationUtil.startQueryIccidLocation(b, true);
          continue;
          continue;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        LogManager.e("SdkTaskQueue", localThrowable.getLocalizedMessage(), localThrowable);
      }
      e.a();
      continue;
      j.a(b);
      continue;
      j.b(b);
      continue;
      k.a(b);
      continue;
      i.a(b);
      continue;
      c.a();
      continue;
      q.a(b);
      continue;
      cn.com.xy.sms.sdk.db.entity.a.c.a(b);
      b.a();
      bs.a();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.h
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */