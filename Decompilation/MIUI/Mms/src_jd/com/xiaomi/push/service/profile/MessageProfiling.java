package com.xiaomi.push.service.profile;

import android.util.Pair;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class MessageProfiling
{
  private static ConcurrentHashMap<String, Long> sSendingMessages = new ConcurrentHashMap();
  private static Vector<Pair<String, Long>> sSentPerfDatas = new Vector();
  
  public static String getPrefString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Vector localVector = sSentPerfDatas;
    int i = 0;
    for (;;)
    {
      try
      {
        if (i < sSentPerfDatas.size())
        {
          Pair localPair = (Pair)sSentPerfDatas.elementAt(i);
          localStringBuilder.append((String)first).append(":").append(second);
          if (i < sSentPerfDatas.size() - 1) {
            localStringBuilder.append(";");
          }
        }
        else
        {
          sSentPerfDatas.clear();
          return localStringBuilder.toString();
        }
      }
      finally {}
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.service.profile.MessageProfiling
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */