package android.support.v4.app;

import android.app.PendingIntent;
import java.util.ArrayList;
import java.util.List;

public class NotificationCompat$CarExtender$UnreadConversation$Builder
{
  private long mLatestTimestamp;
  private final List<String> mMessages = new ArrayList();
  private final String mParticipant;
  private PendingIntent mReadPendingIntent;
  private RemoteInput mRemoteInput;
  private PendingIntent mReplyPendingIntent;
  
  public NotificationCompat$CarExtender$UnreadConversation$Builder(String paramString)
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

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.CarExtender.UnreadConversation.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */