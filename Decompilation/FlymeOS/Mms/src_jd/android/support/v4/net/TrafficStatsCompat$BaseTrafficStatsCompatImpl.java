package android.support.v4.net;

import java.net.Socket;

class TrafficStatsCompat$BaseTrafficStatsCompatImpl
  implements TrafficStatsCompat.TrafficStatsCompatImpl
{
  private ThreadLocal<SocketTags> mThreadSocketTags = new TrafficStatsCompat.BaseTrafficStatsCompatImpl.1(this);
  
  public void clearThreadStatsTag()
  {
    mThreadSocketTags.get()).statsTag = -1;
  }
  
  public int getThreadStatsTag()
  {
    return mThreadSocketTags.get()).statsTag;
  }
  
  public void incrementOperationCount(int paramInt) {}
  
  public void incrementOperationCount(int paramInt1, int paramInt2) {}
  
  public void setThreadStatsTag(int paramInt)
  {
    mThreadSocketTags.get()).statsTag = paramInt;
  }
  
  public void tagSocket(Socket paramSocket) {}
  
  public void untagSocket(Socket paramSocket) {}
  
  static class SocketTags
  {
    public int statsTag = -1;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.net.TrafficStatsCompat.BaseTrafficStatsCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */