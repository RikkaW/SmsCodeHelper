package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;

class NotificationCompat$NotificationCompatImplBase
  implements NotificationCompat.NotificationCompatImpl
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender)
  {
    paramBuilderExtender = mNotification;
    paramBuilderExtender.setLatestEventInfo(mContext, mContentTitle, mContentText, mContentIntent);
    if (mPriority > 0) {
      flags |= 0x80;
    }
    return paramBuilderExtender;
  }
  
  public NotificationCompat.Action getAction(Notification paramNotification, int paramInt)
  {
    return null;
  }
  
  public int getActionCount(Notification paramNotification)
  {
    return 0;
  }
  
  public NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> paramArrayList)
  {
    return null;
  }
  
  public Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation paramUnreadConversation)
  {
    return null;
  }
  
  public String getCategory(Notification paramNotification)
  {
    return null;
  }
  
  public Bundle getExtras(Notification paramNotification)
  {
    return null;
  }
  
  public String getGroup(Notification paramNotification)
  {
    return null;
  }
  
  public boolean getLocalOnly(Notification paramNotification)
  {
    return false;
  }
  
  public ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] paramArrayOfAction)
  {
    return null;
  }
  
  public String getSortKey(Notification paramNotification)
  {
    return null;
  }
  
  public NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle, NotificationCompatBase.UnreadConversation.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1)
  {
    return null;
  }
  
  public boolean isGroupSummary(Notification paramNotification)
  {
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */