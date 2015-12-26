package android.support.v4.app;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.IBinder;

public abstract class NotificationCompatSideChannelService
  extends Service
{
  private void checkPermission(int paramInt, String paramString)
  {
    String[] arrayOfString = getPackageManager().getPackagesForUid(paramInt);
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equals(paramString)) {
        return;
      }
      i += 1;
    }
    throw new SecurityException("NotificationSideChannelService: Uid " + paramInt + " is not authorized for package " + paramString);
  }
  
  public abstract void cancel(String paramString1, int paramInt, String paramString2);
  
  public abstract void cancelAll(String paramString);
  
  public abstract void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification);
  
  public IBinder onBind(Intent paramIntent)
  {
    if ((!paramIntent.getAction().equals("android.support.BIND_NOTIFICATION_SIDE_CHANNEL")) || (Build.VERSION.SDK_INT > 19)) {
      return null;
    }
    return new NotificationSideChannelStub(null);
  }
  
  class NotificationSideChannelStub
    extends INotificationSideChannel.Stub
  {
    private NotificationSideChannelStub() {}
    
    public void cancel(String paramString1, int paramInt, String paramString2)
    {
      NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), paramString1);
      long l = clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.cancel(paramString1, paramInt, paramString2);
        return;
      }
      finally
      {
        restoreCallingIdentity(l);
      }
    }
    
    public void cancelAll(String paramString)
    {
      NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), paramString);
      long l = clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.cancelAll(paramString);
        return;
      }
      finally
      {
        restoreCallingIdentity(l);
      }
    }
    
    public void notify(String paramString1, int paramInt, String paramString2, Notification paramNotification)
    {
      NotificationCompatSideChannelService.this.checkPermission(getCallingUid(), paramString1);
      long l = clearCallingIdentity();
      try
      {
        NotificationCompatSideChannelService.this.notify(paramString1, paramInt, paramString2, paramNotification);
        return;
      }
      finally
      {
        restoreCallingIdentity(l);
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompatSideChannelService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */