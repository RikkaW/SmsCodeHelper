package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;

public abstract class NotificationCompatBase$Action
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

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatBase.Action
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */