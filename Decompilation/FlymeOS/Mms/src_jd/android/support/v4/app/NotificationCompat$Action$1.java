package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

final class NotificationCompat$Action$1
  implements NotificationCompatBase.Action.Factory
{
  public NotificationCompat.Action build(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle, RemoteInputCompatBase.RemoteInput[] paramArrayOfRemoteInput)
  {
    return new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent, paramBundle, (RemoteInput[])paramArrayOfRemoteInput, null);
  }
  
  public NotificationCompat.Action[] newArray(int paramInt)
  {
    return new NotificationCompat.Action[paramInt];
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.Action.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */