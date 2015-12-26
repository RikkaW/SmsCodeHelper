package android.support.v7.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat.BuilderExtender;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v7.internal.app.NotificationCompatImpl21;
import android.support.v7.internal.app.NotificationCompatImplBase;

public class NotificationCompat
  extends android.support.v4.app.NotificationCompat
{
  private static void addBigMediaStyleToBuilderJellybean(Notification paramNotification, android.support.v4.app.NotificationCompat.Builder paramBuilder)
  {
    if ((mStyle instanceof MediaStyle))
    {
      MediaStyle localMediaStyle = (MediaStyle)mStyle;
      NotificationCompatImplBase.overrideBigContentView(paramNotification, mContext, mContentTitle, mContentText, mContentInfo, mNumber, mLargeIcon, mSubText, mUseChronometer, mNotification.when, mActions, mShowCancelButton, mCancelButtonIntent);
    }
  }
  
  private static void addMediaStyleToBuilderIcs(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, android.support.v4.app.NotificationCompat.Builder paramBuilder)
  {
    if ((mStyle instanceof MediaStyle))
    {
      MediaStyle localMediaStyle = (MediaStyle)mStyle;
      NotificationCompatImplBase.overrideContentView(paramNotificationBuilderWithBuilderAccessor, mContext, mContentTitle, mContentText, mContentInfo, mNumber, mLargeIcon, mSubText, mUseChronometer, mNotification.when, mActions, mActionsToShowInCompact, mShowCancelButton, mCancelButtonIntent);
    }
  }
  
  private static void addMediaStyleToBuilderLollipop(NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor, NotificationCompat.Style paramStyle)
  {
    int[] arrayOfInt;
    if ((paramStyle instanceof MediaStyle))
    {
      paramStyle = (MediaStyle)paramStyle;
      arrayOfInt = mActionsToShowInCompact;
      if (mToken == null) {
        break label39;
      }
    }
    label39:
    for (paramStyle = mToken.getToken();; paramStyle = null)
    {
      NotificationCompatImpl21.addMediaStyle(paramNotificationBuilderWithBuilderAccessor, arrayOfInt, paramStyle);
      return;
    }
  }
  
  public static class Builder
    extends android.support.v4.app.NotificationCompat.Builder
  {
    public Builder(Context paramContext)
    {
      super();
    }
    
    protected NotificationCompat.BuilderExtender getExtender()
    {
      if (Build.VERSION.SDK_INT >= 21) {
        return new NotificationCompat.LollipopExtender(null);
      }
      if (Build.VERSION.SDK_INT >= 16) {
        return new NotificationCompat.JellybeanExtender(null);
      }
      if (Build.VERSION.SDK_INT >= 14) {
        return new NotificationCompat.IceCreamSandwichExtender(null);
      }
      return super.getExtender();
    }
  }
  
  static class IceCreamSandwichExtender
    extends NotificationCompat.BuilderExtender
  {
    public Notification build(android.support.v4.app.NotificationCompat.Builder paramBuilder, NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      NotificationCompat.addMediaStyleToBuilderIcs(paramNotificationBuilderWithBuilderAccessor, paramBuilder);
      return paramNotificationBuilderWithBuilderAccessor.build();
    }
  }
  
  static class JellybeanExtender
    extends NotificationCompat.BuilderExtender
  {
    public Notification build(android.support.v4.app.NotificationCompat.Builder paramBuilder, NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      NotificationCompat.addMediaStyleToBuilderIcs(paramNotificationBuilderWithBuilderAccessor, paramBuilder);
      paramNotificationBuilderWithBuilderAccessor = paramNotificationBuilderWithBuilderAccessor.build();
      NotificationCompat.addBigMediaStyleToBuilderJellybean(paramNotificationBuilderWithBuilderAccessor, paramBuilder);
      return paramNotificationBuilderWithBuilderAccessor;
    }
  }
  
  static class LollipopExtender
    extends NotificationCompat.BuilderExtender
  {
    public Notification build(android.support.v4.app.NotificationCompat.Builder paramBuilder, NotificationBuilderWithBuilderAccessor paramNotificationBuilderWithBuilderAccessor)
    {
      NotificationCompat.addMediaStyleToBuilderLollipop(paramNotificationBuilderWithBuilderAccessor, mStyle);
      return paramNotificationBuilderWithBuilderAccessor.build();
    }
  }
  
  public static class MediaStyle
    extends NotificationCompat.Style
  {
    int[] mActionsToShowInCompact = null;
    PendingIntent mCancelButtonIntent;
    boolean mShowCancelButton;
    MediaSessionCompat.Token mToken;
    
    public MediaStyle() {}
    
    public MediaStyle(android.support.v4.app.NotificationCompat.Builder paramBuilder)
    {
      setBuilder(paramBuilder);
    }
    
    public void setCancelButtonIntent(PendingIntent paramPendingIntent)
    {
      mCancelButtonIntent = paramPendingIntent;
    }
    
    public MediaStyle setMediaSession(MediaSessionCompat.Token paramToken)
    {
      mToken = paramToken;
      return this;
    }
    
    public MediaStyle setShowActionsInCompactView(int... paramVarArgs)
    {
      mActionsToShowInCompact = paramVarArgs;
      return this;
    }
    
    public void setShowCancelButton(boolean paramBoolean)
    {
      mShowCancelButton = paramBoolean;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.NotificationCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */