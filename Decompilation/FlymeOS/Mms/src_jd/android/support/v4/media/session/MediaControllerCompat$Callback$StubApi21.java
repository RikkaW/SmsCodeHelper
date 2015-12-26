package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;

class MediaControllerCompat$Callback$StubApi21
  implements MediaControllerCompatApi21.Callback
{
  private MediaControllerCompat$Callback$StubApi21(MediaControllerCompat.Callback paramCallback) {}
  
  public void onMetadataChanged(Object paramObject)
  {
    this$0.onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(paramObject));
  }
  
  public void onPlaybackStateChanged(Object paramObject)
  {
    this$0.onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(paramObject));
  }
  
  public void onSessionDestroyed()
  {
    this$0.onSessionDestroyed();
  }
  
  public void onSessionEvent(String paramString, Bundle paramBundle)
  {
    this$0.onSessionEvent(paramString, paramBundle);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.Callback.StubApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */