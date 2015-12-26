package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;

class NotificationCompat$NotificationCompatImplKitKat
  extends NotificationCompat.NotificationCompatImplJellybean
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender)
  {
    NotificationCompatKitKat.Builder localBuilder = new NotificationCompatKitKat.Builder(mContext, mNotification, mContentTitle, mContentText, mContentInfo, mTickerView, mNumber, mContentIntent, mFullScreenIntent, mLargeIcon, mProgressMax, mProgress, mProgressIndeterminate, mShowWhen, mUseChronometer, mPriority, mSubText, mLocalOnly, mPeople, mExtras, mGroupKey, mGroupSummary, mSortKey);
    NotificationCompat.access$000(localBuilder, mActions);
    NotificationCompat.access$100(localBuilder, mStyle);
    return paramBuilderExtender.build(paramBuilder, localBuilder);
  }
  
  public NotificationCompat.Action getAction(Notification paramNotification, int paramInt)
  {
    return (NotificationCompat.Action)NotificationCompatKitKat.getAction(paramNotification, paramInt, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
  }
  
  public int getActionCount(Notification paramNotification)
  {
    return NotificationCompatKitKat.getActionCount(paramNotification);
  }
  
  public Bundle getExtras(Notification paramNotification)
  {
    return NotificationCompatKitKat.getExtras(paramNotification);
  }
  
  public String getGroup(Notification paramNotification)
  {
    return NotificationCompatKitKat.getGroup(paramNotification);
  }
  
  public boolean getLocalOnly(Notification paramNotification)
  {
    return NotificationCompatKitKat.getLocalOnly(paramNotification);
  }
  
  public String getSortKey(Notification paramNotification)
  {
    return NotificationCompatKitKat.getSortKey(paramNotification);
  }
  
  public boolean isGroupSummary(Notification paramNotification)
  {
    return NotificationCompatKitKat.isGroupSummary(paramNotification);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImplKitKat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */