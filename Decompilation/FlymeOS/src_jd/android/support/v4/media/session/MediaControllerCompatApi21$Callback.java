package android.support.v4.media.session;

import android.os.Bundle;

public abstract interface MediaControllerCompatApi21$Callback
{
  public abstract void onMetadataChanged(Object paramObject);
  
  public abstract void onPlaybackStateChanged(Object paramObject);
  
  public abstract void onSessionDestroyed();
  
  public abstract void onSessionEvent(String paramString, Bundle paramBundle);
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompatApi21.Callback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */