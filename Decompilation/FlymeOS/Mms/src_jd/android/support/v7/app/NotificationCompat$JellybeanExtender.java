package android.support.v7.app;

import android.app.Notification;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.BuilderExtender;

class NotificationCompat$JellybeanExtender
  extends NotificationCompat.BuilderExtender
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
  {
    NotificationCompat.access$300(paramNotificationBuilderWithBuilderAccessor, paramBuilder);
    paramNotificationBuilderWithBuilderAccessor = paramNotificationBuilderWithBuilderAccessor.build();
    NotificationCompat.access$400(paramNotificationBuilderWithBuilderAccessor, paramBuilder);
    return paramNotificationBuilderWithBuilderAccessor;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.NotificationCompat.JellybeanExtender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */