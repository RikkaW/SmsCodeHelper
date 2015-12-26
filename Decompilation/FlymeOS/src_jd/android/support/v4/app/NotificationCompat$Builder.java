package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.ArrayList;

public class NotificationCompat$Builder
{
  private static final int MAX_CHARSEQUENCE_LENGTH = 5120;
  public ArrayList<NotificationCompat.Action> mActions = new ArrayList();
  String mCategory;
  int mColor = 0;
  public CharSequence mContentInfo;
  PendingIntent mContentIntent;
  public CharSequence mContentText;
  public CharSequence mContentTitle;
  public Context mContext;
  Bundle mExtras;
  PendingIntent mFullScreenIntent;
  String mGroupKey;
  boolean mGroupSummary;
  public Bitmap mLargeIcon;
  boolean mLocalOnly = false;
  public Notification mNotification = new Notification();
  public int mNumber;
  public ArrayList<String> mPeople;
  int mPriority;
  int mProgress;
  boolean mProgressIndeterminate;
  int mProgressMax;
  Notification mPublicVersion;
  boolean mShowWhen = true;
  String mSortKey;
  public NotificationCompat.Style mStyle;
  public CharSequence mSubText;
  RemoteViews mTickerView;
  public boolean mUseChronometer;
  int mVisibility = 0;
  
  public NotificationCompat$Builder(Context paramContext)
  {
    mContext = paramContext;
    mNotification.when = System.currentTimeMillis();
    mNotification.audioStreamType = -1;
    mPriority = 0;
    mPeople = new ArrayList();
  }
  
  protected static CharSequence limitCharSequenceLength(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {}
    while (paramCharSequence.length() <= 5120) {
      return paramCharSequence;
    }
    return paramCharSequence.subSequence(0, 5120);
  }
  
  private void setFlag(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      localNotification = mNotification;
      flags |= paramInt;
      return;
    }
    Notification localNotification = mNotification;
    flags &= (paramInt ^ 0xFFFFFFFF);
  }
  
  public Builder addAction(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
  {
    mActions.add(new NotificationCompat.Action(paramInt, paramCharSequence, paramPendingIntent));
    return this;
  }
  
  public Builder addAction(NotificationCompat.Action paramAction)
  {
    mActions.add(paramAction);
    return this;
  }
  
  public Builder addExtras(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      if (mExtras == null) {
        mExtras = new Bundle(paramBundle);
      }
    }
    else {
      return this;
    }
    mExtras.putAll(paramBundle);
    return this;
  }
  
  public Builder addPerson(String paramString)
  {
    mPeople.add(paramString);
    return this;
  }
  
  public Notification build()
  {
    return NotificationCompat.access$200().build(this, getExtender());
  }
  
  public Builder extend(NotificationCompat.Extender paramExtender)
  {
    paramExtender.extend(this);
    return this;
  }
  
  public NotificationCompat.BuilderExtender getExtender()
  {
    return new NotificationCompat.BuilderExtender();
  }
  
  public Bundle getExtras()
  {
    if (mExtras == null) {
      mExtras = new Bundle();
    }
    return mExtras;
  }
  
  @Deprecated
  public Notification getNotification()
  {
    return build();
  }
  
  public Builder setAutoCancel(boolean paramBoolean)
  {
    setFlag(16, paramBoolean);
    return this;
  }
  
  public Builder setCategory(String paramString)
  {
    mCategory = paramString;
    return this;
  }
  
  public Builder setColor(int paramInt)
  {
    mColor = paramInt;
    return this;
  }
  
  public Builder setContent(RemoteViews paramRemoteViews)
  {
    mNotification.contentView = paramRemoteViews;
    return this;
  }
  
  public Builder setContentInfo(CharSequence paramCharSequence)
  {
    mContentInfo = limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public Builder setContentIntent(PendingIntent paramPendingIntent)
  {
    mContentIntent = paramPendingIntent;
    return this;
  }
  
  public Builder setContentText(CharSequence paramCharSequence)
  {
    mContentText = limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public Builder setContentTitle(CharSequence paramCharSequence)
  {
    mContentTitle = limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public Builder setDefaults(int paramInt)
  {
    mNotification.defaults = paramInt;
    if ((paramInt & 0x4) != 0)
    {
      Notification localNotification = mNotification;
      flags |= 0x1;
    }
    return this;
  }
  
  public Builder setDeleteIntent(PendingIntent paramPendingIntent)
  {
    mNotification.deleteIntent = paramPendingIntent;
    return this;
  }
  
  public Builder setExtras(Bundle paramBundle)
  {
    mExtras = paramBundle;
    return this;
  }
  
  public Builder setFullScreenIntent(PendingIntent paramPendingIntent, boolean paramBoolean)
  {
    mFullScreenIntent = paramPendingIntent;
    setFlag(128, paramBoolean);
    return this;
  }
  
  public Builder setGroup(String paramString)
  {
    mGroupKey = paramString;
    return this;
  }
  
  public Builder setGroupSummary(boolean paramBoolean)
  {
    mGroupSummary = paramBoolean;
    return this;
  }
  
  public Builder setLargeIcon(Bitmap paramBitmap)
  {
    mLargeIcon = paramBitmap;
    return this;
  }
  
  public Builder setLights(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = 1;
    mNotification.ledARGB = paramInt1;
    mNotification.ledOnMS = paramInt2;
    mNotification.ledOffMS = paramInt3;
    Notification localNotification;
    if ((mNotification.ledOnMS != 0) && (mNotification.ledOffMS != 0))
    {
      paramInt1 = 1;
      localNotification = mNotification;
      paramInt2 = mNotification.flags;
      if (paramInt1 == 0) {
        break label88;
      }
    }
    label88:
    for (paramInt1 = i;; paramInt1 = 0)
    {
      flags = (paramInt2 & 0xFFFFFFFE | paramInt1);
      return this;
      paramInt1 = 0;
      break;
    }
  }
  
  public Builder setLocalOnly(boolean paramBoolean)
  {
    mLocalOnly = paramBoolean;
    return this;
  }
  
  public Builder setNumber(int paramInt)
  {
    mNumber = paramInt;
    return this;
  }
  
  public Builder setOngoing(boolean paramBoolean)
  {
    setFlag(2, paramBoolean);
    return this;
  }
  
  public Builder setOnlyAlertOnce(boolean paramBoolean)
  {
    setFlag(8, paramBoolean);
    return this;
  }
  
  public Builder setPriority(int paramInt)
  {
    mPriority = paramInt;
    return this;
  }
  
  public Builder setProgress(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    mProgressMax = paramInt1;
    mProgress = paramInt2;
    mProgressIndeterminate = paramBoolean;
    return this;
  }
  
  public Builder setPublicVersion(Notification paramNotification)
  {
    mPublicVersion = paramNotification;
    return this;
  }
  
  public Builder setShowWhen(boolean paramBoolean)
  {
    mShowWhen = paramBoolean;
    return this;
  }
  
  public Builder setSmallIcon(int paramInt)
  {
    mNotification.icon = paramInt;
    return this;
  }
  
  public Builder setSmallIcon(int paramInt1, int paramInt2)
  {
    mNotification.icon = paramInt1;
    mNotification.iconLevel = paramInt2;
    return this;
  }
  
  public Builder setSortKey(String paramString)
  {
    mSortKey = paramString;
    return this;
  }
  
  public Builder setSound(Uri paramUri)
  {
    mNotification.sound = paramUri;
    mNotification.audioStreamType = -1;
    return this;
  }
  
  public Builder setSound(Uri paramUri, int paramInt)
  {
    mNotification.sound = paramUri;
    mNotification.audioStreamType = paramInt;
    return this;
  }
  
  public Builder setStyle(NotificationCompat.Style paramStyle)
  {
    if (mStyle != paramStyle)
    {
      mStyle = paramStyle;
      if (mStyle != null) {
        mStyle.setBuilder(this);
      }
    }
    return this;
  }
  
  public Builder setSubText(CharSequence paramCharSequence)
  {
    mSubText = limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public Builder setTicker(CharSequence paramCharSequence)
  {
    mNotification.tickerText = limitCharSequenceLength(paramCharSequence);
    return this;
  }
  
  public Builder setTicker(CharSequence paramCharSequence, RemoteViews paramRemoteViews)
  {
    mNotification.tickerText = limitCharSequenceLength(paramCharSequence);
    mTickerView = paramRemoteViews;
    return this;
  }
  
  public Builder setUsesChronometer(boolean paramBoolean)
  {
    mUseChronometer = paramBoolean;
    return this;
  }
  
  public Builder setVibrate(long[] paramArrayOfLong)
  {
    mNotification.vibrate = paramArrayOfLong;
    return this;
  }
  
  public Builder setVisibility(int paramInt)
  {
    mVisibility = paramInt;
    return this;
  }
  
  public Builder setWhen(long paramLong)
  {
    mNotification.when = paramLong;
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */