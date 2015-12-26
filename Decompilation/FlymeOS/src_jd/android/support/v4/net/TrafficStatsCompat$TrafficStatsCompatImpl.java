package android.support.v4.net;

import java.net.Socket;

abstract interface TrafficStatsCompat$TrafficStatsCompatImpl
{
  public abstract void clearThreadStatsTag();
  
  public abstract int getThreadStatsTag();
  
  public abstract void incrementOperationCount(int paramInt);
  
  public abstract void incrementOperationCount(int paramInt1, int paramInt2);
  
  public abstract void setThreadStatsTag(int paramInt);
  
  public abstract void tagSocket(Socket paramSocket);
  
  public abstract void untagSocket(Socket paramSocket);
}

/* Location:
 * Qualified Name:     android.support.v4.net.TrafficStatsCompat.TrafficStatsCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */