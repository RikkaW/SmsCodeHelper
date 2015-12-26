package android.support.v4.app;

import android.app.Notification;
import android.app.Notification.Builder;

public abstract interface NotificationBuilderWithBuilderAccessor
{
  public abstract Notification build();
  
  public abstract Notification.Builder getBuilder();
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationBuilderWithBuilderAccessor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */