package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;

class NotificationManagerCompat$ImplEclair
  extends NotificationManagerCompat.ImplBase
{
  public void cancelNotification(NotificationManager paramNotificationManager, String paramString, int paramInt)
  {
    NotificationManagerCompatEclair.cancelNotification(paramNotificationManager, paramString, paramInt);
  }
  
  public void postNotification(NotificationManager paramNotificationManager, String paramString, int paramInt, Notification paramNotification)
  {
    NotificationManagerCompatEclair.postNotification(paramNotificationManager, paramString, paramInt, paramNotification);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationManagerCompat.ImplEclair
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */