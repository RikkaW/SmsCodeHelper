package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplGingerbread
  extends NotificationCompat.NotificationCompatImplBase
{
  public Notification build(NotificationCompat.Builder paramBuilder, NotificationCompat.BuilderExtender paramBuilderExtender)
  {
    paramBuilderExtender = mNotification;
    paramBuilderExtender.setLatestEventInfo(mContext, mContentTitle, mContentText, mContentIntent);
    paramBuilderExtender = NotificationCompatGingerbread.add(paramBuilderExtender, mContext, mContentTitle, mContentText, mContentIntent, mFullScreenIntent);
    if (mPriority > 0) {
      flags |= 0x80;
    }
    return paramBuilderExtender;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.NotificationCompatImplGingerbread
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */