package android.support.v7.app;

import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationCompat.Style;
import android.support.v4.media.session.MediaSessionCompat.Token;

public class NotificationCompat$MediaStyle
  extends NotificationCompat.Style
{
  int[] mActionsToShowInCompact = null;
  PendingIntent mCancelButtonIntent;
  boolean mShowCancelButton;
  MediaSessionCompat.Token mToken;
  
  public NotificationCompat$MediaStyle() {}
  
  public NotificationCompat$MediaStyle(NotificationCompat.Builder paramBuilder)
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

/* Location:
 * Qualified Name:     android.support.v7.app.NotificationCompat.MediaStyle
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */