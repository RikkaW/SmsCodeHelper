package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

public class NotificationCompatBase
{
  public static abstract class Action
  {
    public abstract PendingIntent getActionIntent();
    
    public abstract Bundle getExtras();
    
    public abstract int getIcon();
    
    public abstract RemoteInputCompatBase.RemoteInput[] getRemoteInputs();
    
    public abstract CharSequence getTitle();
    
    public static abstract interface Factory
    {
      public abstract NotificationCompatBase.Action build(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput);
      
      public abstract NotificationCompatBase.Action[] newArray(int paramInt);
    }
  }
  
  public static abstract class UnreadConversation
  {
    abstract long getLatestTimestamp();
    
    abstract String[] getMessages();
    
    abstract String getParticipant();
    
    abstract String[] getParticipants();
    
    abstract PendingIntent getReadPendingIntent();
    
    abstract RemoteInputCompatBase.RemoteInput getRemoteInput();
    
    abstract PendingIntent getReplyPendingIntent();
    
    public static abstract interface Factory
    {
      public abstract NotificationCompatBase.UnreadConversation build(String[] paramArrayOfString1, RemoteInputCompatBase.RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */