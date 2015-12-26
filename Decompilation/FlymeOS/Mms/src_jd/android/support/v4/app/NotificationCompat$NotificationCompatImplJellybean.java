package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;

class NotificationCompat$NotificationCompatImplJellybean
  extends NotificationCompat.NotificationCompatImplBase
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender)
  {
    NotificationCompatJellybean.Builder localBuilder = new NotificationCompatJellybean.Builder(mContext, mNotification, mContentTitle, mContentText, mContentInfo, mTickerView, mNumber, mContentIntent, mFullScreenIntent, mLargeIcon, mProgressMax, mProgress, mProgressIndeterminate, mUseChronometer, mPriority, mSubText, mLocalOnly, mExtras, mGroupKey, mGroupSummary, mSortKey);
    NotificationCompat.access$000(localBuilder, mActions);
    NotificationCompat.access$100(localBuilder, mStyle);
    return paramBuilderExtender.build(paramBuilder, localBuilder);
  }
  
  public NotificationCompat.Action getAction(Notification paramNotification, int paramInt)
  {
    return (NotificationCompat.Action)NotificationCompatJellybean.getAction(paramNotification, paramInt, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
  }
  
  public int getActionCount(Notification paramNotification)
  {
    return NotificationCompatJellybean.getActionCount(paramNotification);
  }
  
  public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> paramArrayList)
  {
    return (NotificationCompat.Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(paramArrayList, NotificationCompat.Action.FACTORY, RemoteInput.FACTORY);
  }
  
  public Bundle getExtras(Notification paramNotification)
  {
    return NotificationCompatJellybean.getExtras(paramNotification);
  }
  
  public String getGroup(Notification paramNotification)
  {
    return NotificationCompatJellybean.getGroup(paramNotification);
  }
  
  public boolean getLocalOnly(Notification paramNotification)
  {
    return NotificationCompatJellybean.getLocalOnly(paramNotification);
  }
  
  public ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] paramArrayOfAction)
  {
    return NotificationCompatJellybean.getParcelableArrayListForActions(paramArrayOfAction);
  }
  
  public String getSortKey(Notification paramNotification)
  {
    return NotificationCompatJellybean.getSortKey(paramNotification);
  }
  
  public boolean isGroupSummary(Notification paramNotification)
  {
    return NotificationCompatJellybean.isGroupSummary(paramNotification);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImplJellybean
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */