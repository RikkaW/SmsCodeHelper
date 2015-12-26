package android.support.v7.app;

import android.app.Notification;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.BuilderExtender;

class NotificationCompat$LollipopExtender
  extends NotificationCompat.BuilderExtender
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
  {
    NotificationCompat.access$500(paramNotificationBuilderWithBuilderAccessor, mStyle);
    return paramNotificationBuilderWithBuilderAccessor.build();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.NotificationCompat.LollipopExtender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */