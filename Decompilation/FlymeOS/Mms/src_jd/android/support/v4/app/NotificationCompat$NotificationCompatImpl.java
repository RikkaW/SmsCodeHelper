package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;

abstract interface NotificationCompat$NotificationCompatImpl
{
  public abstract Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender);
  
  public abstract NotificationCompat.Action getAction(Notification paramNotification, int paramInt);
  
  public abstract int getActionCount(Notification paramNotification);
  
  public abstract NotificationCompat.Action[] getActionsFromParcelableArrayList(ArrayList<Parcelable> paramArrayList);
  
  public abstract Bundle getBundleForUnreadConversation(NotificationCompatBase.UnreadConversation paramUnreadConversation);
  
  public abstract String getCategory(Notification paramNotification);
  
  public abstract Bundle getExtras(Notification paramNotification);
  
  public abstract String getGroup(Notification paramNotification);
  
  public abstract boolean getLocalOnly(Notification paramNotification);
  
  public abstract ArrayList<Parcelable> getParcelableArrayListForActions(NotificationCompat.Action[] paramArrayOfAction);
  
  public abstract String getSortKey(Notification paramNotification);
  
  public abstract NotificationCompatBase.UnreadConversation getUnreadConversationFromBundle(Bundle paramBundle, NotificationCompatBase.UnreadConversation.Factory paramFactory, RemoteInputCompatBase.RemoteInput.Factory paramFactory1);
  
  public abstract boolean isGroupSummary(Notification paramNotification);
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */