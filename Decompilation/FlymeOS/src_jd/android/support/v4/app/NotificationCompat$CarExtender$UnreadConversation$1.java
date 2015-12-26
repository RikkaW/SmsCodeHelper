package android.support.v4.app;

import android.app.PendingIntent;

final class NotificationCompat$CarExtender$UnreadConversation$1
  implements NotificationCompatBase.UnreadConversation.Factory
{
  public NotificationCompat.CarExtender.UnreadConversation build(String[] paramArrayOfString1, RemoteInputCompatBase.RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong)
  {
    return new NotificationCompat.CarExtender.UnreadConversation(paramArrayOfString1, (RemoteInput)paramRemoteInput, paramPendingIntent1, paramPendingIntent2, paramArrayOfString2, paramLong);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.CarExtender.UnreadConversation.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */