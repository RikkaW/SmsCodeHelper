package android.support.v4.net;

import android.os.Build.VERSION;
import java.net.Socket;

public class TrafficStatsCompat
{
  private static final TrafficStatsCompatImpl IMPL = new BaseTrafficStatsCompatImpl();
  
  static
  {
    if (Build.VERSION.SDK_INT >= 14)
    {
      IMPL = new IcsTrafficStatsCompatImpl();
      return;
    }
  }
  
  public static void clearThreadStatsTag()
  {
    IMPL.clearThreadStatsTag();
  }
  
  public static int getThreadStatsTag()
  {
    return IMPL.getThreadStatsTag();
  }
  
  public static void incrementOperationCount(int paramInt)
  {
    IMPL.incrementOperationCount(paramInt);
  }
  
  public static void incrementOperationCount(int paramInt1, int paramInt2)
  {
    IMPL.incrementOperationCount(paramInt1, paramInt2);
  }
  
  public static void setThreadStatsTag(int paramInt)
  {
    IMPL.setThreadStatsTag(paramInt);
  }
  
  public static void tagSocket(Socket paramSocket)
  {
    IMPL.tagSocket(paramSocket);
  }
  
  public static void untagSocket(Socket paramSocket)
  {
    IMPL.untagSocket(paramSocket);
  }
  
  static class BaseTrafficStatsCompatImpl
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
  
  static class IcsTrafficStatsCompatImpl
    implements TrafficStatsCompat.TrafficStatsCompatImpl
  {
    public void clearThreadStatsTag() {}
    
    public int getThreadStatsTag()
    {
      return TrafficStatsCompatIcs.getThreadStatsTag();
    }
    
    public void incrementOperationCount(int paramInt)
    {
      TrafficStatsCompatIcs.incrementOperationCount(paramInt);
    }
    
    public void incrementOperationCount(int paramInt1, int paramInt2)
    {
      TrafficStatsCompatIcs.incrementOperationCount(paramInt1, paramInt2);
    }
    
    public void setThreadStatsTag(int paramInt)
    {
      TrafficStatsCompatIcs.setThreadStatsTag(paramInt);
    }
    
    public void tagSocket(Socket paramSocket)
    {
      TrafficStatsCompatIcs.tagSocket(paramSocket);
    }
    
    public void untagSocket(Socket paramSocket)
    {
      TrafficStatsCompatIcs.untagSocket(paramSocket);
    }
  }
  
  static abstract interface TrafficStatsCompatImpl
  {
    public abstract void clearThreadStatsTag();
    
    public abstract int getThreadStatsTag();
    
    public abstract void incrementOperationCount(int paramInt);
    
    public abstract void incrementOperationCount(int paramInt1, int paramInt2);
    
    public abstract void setThreadStatsTag(int paramInt);
    
    public abstract void tagSocket(Socket paramSocket);
    
    public abstract void untagSocket(Socket paramSocket);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.net.TrafficStatsCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */