package android.support.v4.app;

class NotificationManagerCompat$CancelTask
  implements NotificationManagerCompat.Task
{
  final boolean all;
  final int id;
  final String packageName;
  final String tag;
  
  public NotificationManagerCompat$CancelTask(String paramString)
  {
    packageName = paramString;
    id = 0;
    tag = null;
    all = true;
  }
  
  public NotificationManagerCompat$CancelTask(String paramString1, int paramInt, String paramString2)
  {
    packageName = paramString1;
    id = paramInt;
    tag = paramString2;
    all = false;
  }
  
  public void send(INotificationSideChannel paramINotificationSideChannel)
  {
    if (all)
    {
      paramINotificationSideChannel.cancelAll(packageName);
      return;
    }
    paramINotificationSideChannel.cancel(packageName, id, tag);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("CancelTask[");
    localStringBuilder.append("packageName:").append(packageName);
    localStringBuilder.append(", id:").append(id);
    localStringBuilder.append(", tag:").append(tag);
    localStringBuilder.append(", all:").append(all);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationManagerCompat.CancelTask
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */