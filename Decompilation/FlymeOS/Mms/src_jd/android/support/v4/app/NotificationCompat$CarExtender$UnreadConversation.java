package android.support.v4.app;

import android.app.PendingIntent;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$CarExtender$UnreadConversation
  extends NotificationCompatBase.UnreadConversation
{
  static final NotificationCompatBase.UnreadConversation.Factory FACTORY = new NotificationCompat.CarExtender.UnreadConversation.1();
  private final long mLatestTimestamp;
  private final String[] mMessages;
  private final String[] mParticipants;
  private final PendingIntent mReadPendingIntent;
  private final RemoteInput mRemoteInput;
  private final PendingIntent mReplyPendingIntent;
  
  NotificationCompat$CarExtender$UnreadConversation(String[] paramArrayOfString1, RemoteInput paramRemoteInput, PendingIntent paramPendingIntent1, PendingIntent paramPendingIntent2, String[] paramArrayOfString2, long paramLong)
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

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.CarExtender.UnreadConversation
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */