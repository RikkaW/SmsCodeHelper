package android.support.v4.app;

import android.app.PendingIntent;

public abstract class NotificationCompatBase$UnreadConversation
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

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatBase.UnreadConversation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */