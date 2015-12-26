package android.support.v4.net;

import android.net.TrafficStats;
import java.net.Socket;

class TrafficStatsCompatIcs
{
  public static void clearThreadStatsTag() {}
  
  public static int getThreadStatsTag()
  {
    return TrafficStats.getThreadStatsTag();
  }
  
  public static void incrementOperationCount(int paramInt)
  {
    TrafficStats.incrementOperationCount(paramInt);
  }
  
  public static void incrementOperationCount(int paramInt1, int paramInt2)
  {
    TrafficStats.incrementOperationCount(paramInt1, paramInt2);
  }
  
  public static void setThreadStatsTag(int paramInt)
  {
    TrafficStats.setThreadStatsTag(paramInt);
  }
  
  public static void tagSocket(Socket paramSocket)
  {
    TrafficStats.tagSocket(paramSocket);
  }
  
  public static void untagSocket(Socket paramSocket)
  {
    TrafficStats.untagSocket(paramSocket);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.net.TrafficStatsCompatIcs
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */