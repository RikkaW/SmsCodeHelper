package cn.com.xy.sms.util;

import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import java.util.Iterator;
import java.util.Set;

final class d
  implements Runnable
{
  d(Set paramSet) {}
  
  public final void run()
  {
    try
    {
      Iterator localIterator = a.iterator();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          MatchCacheManager.deleteDataByMsgIds(a);
          return;
        }
        ParseRichBubbleManager.deleteBubbleDataFromCache("", String.valueOf((Integer)localIterator.next()));
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.util.d
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */