package android.support.v4.app;

import android.app.PendingIntent;
import android.os.Bundle;
import java.util.ArrayList;

public final class NotificationCompat$Action$Builder
{
  private final Bundle mExtras;
  private final int mIcon;
  private final PendingIntent mIntent;
  private ArrayList<RemoteInput> mRemoteInputs;
  private final CharSequence mTitle;
  
  public NotificationCompat$Action$Builder(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent)
  {
    this(paramInt, paramCharSequence, paramPendingIntent, new Bundle());
  }
  
  private NotificationCompat$Action$Builder(int paramInt, CharSequence paramCharSequence, PendingIntent paramPendingIntent, Bundle paramBundle)
  {
    mIcon = paramInt;
    mTitle = NotificationCompat.Builder.limitCharSequenceLength(paramCharSequence);
    mIntent = paramPendingIntent;
    mExtras = paramBundle;
  }
  
  public NotificationCompat$Action$Builder(NotificationCompat.Action paramAction)
  {
    this(icon, title, actionIntent, new Bundle(NotificationCompat.Action.access$300(paramAction)));
  }
  
  public Builder addExtras(Bundle paramBundle)
  {
    if (paramBundle != null) {
      mExtras.putAll(paramBundle);
    }
    return this;
  }
  
  public Builder addRemoteInput(RemoteInput paramRemoteInput)
  {
    if (mRemoteInputs == null) {
      mRemoteInputs = new ArrayList();
    }
    mRemoteInputs.add(paramRemoteInput);
    return this;
  }
  
  public NotificationCompat.Action build()
  {
    if (mRemoteInputs != null) {}
    for (RemoteInput[] arrayOfRemoteInput = (RemoteInput[])mRemoteInputs.toArray(new RemoteInput[mRemoteInputs.size()]);; arrayOfRemoteInput = null) {
      return new NotificationCompat.Action(mIcon, mTitle, mIntent, mExtras, arrayOfRemoteInput, null);
    }
  }
  
  public Builder extend(NotificationCompat.Action.Extender paramExtender)
  {
    paramExtender.extend(this);
    return this;
  }
  
  public Bundle getExtras()
  {
    return mExtras;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.app.NotificationCompat.Action.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */