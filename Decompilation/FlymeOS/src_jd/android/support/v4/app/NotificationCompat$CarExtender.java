package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public final class NotificationCompat$CarExtender
  implements NotificationCompat.Extender
{
  private static final String EXTRA_CAR_EXTENDER = "android.car.EXTENSIONS";
  private static final String EXTRA_COLOR = "app_color";
  private static final String EXTRA_CONVERSATION = "car_conversation";
  private static final String EXTRA_LARGE_ICON = "large_icon";
  private static final String TAG = "CarExtender";
  private int mColor = 0;
  private Bitmap mLargeIcon;
  private UnreadConversation mUnreadConversation;
  
  public NotificationCompat$CarExtender() {}
  
  public NotificationCompat$CarExtender(Notification paramNotification)
  {
    if (Build.VERSION.SDK_INT < 21) {}
    for (;;)
    {
      return;
      if (NotificationCompat.getExtras(paramNotification) == null) {}
      for (paramNotification = null; paramNotification != null; paramNotification = NotificationCompat.getExtras(paramNotification).getBundle("android.car.EXTENSIONS"))
      {
        mLargeIcon = ((Bitmap)paramNotification.getParcelable("large_icon"));
        mColor = paramNotification.getInt("app_color", 0);
        paramNotification = paramNotification.getBundle("car_conversation");
        mUnreadConversation = ((UnreadConversation)NotificationCompat.access$200().getUnreadConversationFromBundle(paramNotification, UnreadConversation.FACTORY, RemoteInput.FACTORY));
        return;
      }
    }
  }
  
  public NotificationCompat.Builder extend(NotificationCompat.Builder paramBuilder)
  {
    if (Build.VERSION.SDK_INT < 21) {
      return paramBuilder;
    }
    Bundle localBundle = new Bundle();
    if (mLargeIcon != null) {
      localBundle.putParcelable("large_icon", mLargeIcon);
    }
    if (mColor != 0) {
      localBundle.putInt("app_color", mColor);
    }
    if (mUnreadConversation != null) {
      localBundle.putBundle("car_conversation", NotificationCompat.access$200().getBundleForUnreadConversation(mUnreadConversation));
    }
    paramBuilder.getExtras().putBundle("android.car.EXTENSIONS", localBundle);
    return paramBuilder;
  }
  
  public int getColor()
  {
    return mColor;
  }
  
  public Bitmap getLargeIcon()
  {
    return mLargeIcon;
  }
  
  public UnreadConversation getUnreadConversation()
  {
    return mUnreadConversation;
  }
  
  public CarExtender setColor(int paramInt)
  {
    mColor = paramInt;
    return this;
  }
  
  public CarExtender setLargeIcon(Bitmap paramBitmap)
  {
    mLargeIcon = paramBitmap;
    return this;
  }
  
  public CarExtender setUnreadConversation(UnreadConversation paramUnreadConversation)
  {
    mUnreadConversation = paramUnreadConversation;
    return this;
  }
  
  public static class UnreadConversation
    extends NotificationCompatBase.UnreadConversation
  {
    static final NotificationCompatBase.UnreadConversation.Factory FACTORY = new NotificationCompat.CarExtender.UnreadConversation.1();
    private final long mLatestTimestamp;
    private final String[] mMessages;
    private final String[] mParticipants;
    private final PendingIntent mReadPendingIntent;
    private final RemoteInput mRemoteInput;
    private final PendingIntent mReplyPendingIntent;
    
    UnreadConversation(String[] paramArrayOfString1, RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong)
    {
      mMessages = paramArrayOfString1;
      mRemoteInput = paramRemoteInput;
      mReadPendingIntent = paramPendingIntent2;
      mReplyPendingIntent = paramPendingIntent1;
      mParticipants = paramArrayOfString2;
      mLatestTimestamp = paramLong;
    }
    
    public long getLatestTimestamp()
    {
      return mLatestTimestamp;
    }
    
    public String[] getMessages()
    {
      return mMessages;
    }
    
    public String getParticipant()
    {
      if (mParticipants.length > 0) {
        return mParticipants[0];
      }
      return null;
    }
    
    public String[] getParticipants()
    {
      return mParticipants;
    }
    
    public PendingIntent getReadPendingIntent()
    {
      return mReadPendingIntent;
    }
    
    public RemoteInput getRemoteInput()
    {
      return mRemoteInput;
    }
    
    public PendingIntent getReplyPendingIntent()
    {
      return mReplyPendingIntent;
    }
    
    public static class Builder
    {
      private long mLatestTimestamp;
      private final List<String> mMessages = new ArrayList();
      private final String mParticipant;
      private PendingIntent mReadPendingIntent;
      private RemoteInput mRemoteInput;
      private PendingIntent mReplyPendingIntent;
      
      public Builder(String paramString)
      {
        mParticipant = paramString;
      }
      
      public Builder addMessage(String paramString)
      {
        mMessages.add(paramString);
        return this;
      }
      
      public NotificationCompat.CarExtender.UnreadConversation build()
      {
        String[] arrayOfString = (String[])mMessages.toArray(new String[mMessages.size()]);
        String str = mParticipant;
        RemoteInput localRemoteInput = mRemoteInput;
        PendingIntent localPendingIntent1 = mReplyPendingIntent;
        PendingIntent localPendingIntent2 = mReadPendingIntent;
        long l = mLatestTimestamp;
        return new NotificationCompat.CarExtender.UnreadConversation(arrayOfString, localRemoteInput, localPendingIntent1, localPendingIntent2, new String[] { str }, l);
      }
      
      public Builder setLatestTimestamp(long paramLong)
      {
        mLatestTimestamp = paramLong;
        return this;
      }
      
      public Builder setReadPendingIntent(PendingIntent paramPendingIntent)
      {
        mReadPendingIntent = paramPendingIntent;
        return this;
      }
      
      public Builder setReplyAction(PendingIntent paramPendingIntent, RemoteInput paramRemoteInput)
      {
        mRemoteInput = paramRemoteInput;
        mReplyPendingIntent = paramPendingIntent;
        return this;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.CarExtender
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */