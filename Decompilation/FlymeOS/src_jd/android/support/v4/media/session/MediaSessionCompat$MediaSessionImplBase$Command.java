package android.support.v4.media.session;

import android.os.Bundle;
import android.os.ResultReceiver;

final class MediaSessionCompat$MediaSessionImplBase$Command
{
  public final String command;
  public final Bundle extras;
  public final ResultReceiver stub;
  
  public MediaSessionCompat$MediaSessionImplBase$Command(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    command = paramString;
    extras = paramBundle;
    stub = paramResultReceiver;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.Command
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */