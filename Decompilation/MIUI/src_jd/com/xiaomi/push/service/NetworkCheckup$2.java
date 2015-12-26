package com.xiaomi.push.service;

import com.xiaomi.stats.StatsHelper;
import java.util.Iterator;
import java.util.List;

final class NetworkCheckup$2
  implements Runnable
{
  NetworkCheckup$2(List paramList, boolean paramBoolean) {}
  
  public void run()
  {
    int i = 1;
    boolean bool2 = NetworkCheckup.access$200("www.baidu.com:80");
    Iterator localIterator = val$addrs.iterator();
    boolean bool1;
    do
    {
      do
      {
        bool1 = bool2;
        if (!localIterator.hasNext()) {
          break;
        }
        String str = (String)localIterator.next();
        if ((!bool2) && (!NetworkCheckup.access$200(str))) {
          break label81;
        }
        bool1 = true;
        bool2 = bool1;
      } while (!bool1);
      bool2 = bool1;
    } while (val$all);
    if (bool1) {}
    for (;;)
    {
      StatsHelper.count(i);
      return;
      label81:
      bool1 = false;
      break;
      i = 2;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.NetworkCheckup.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */