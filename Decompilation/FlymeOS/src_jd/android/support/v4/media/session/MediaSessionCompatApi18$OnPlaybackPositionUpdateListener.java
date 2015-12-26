package android.support.v4.media.session;

import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;

class MediaSessionCompatApi18$OnPlaybackPositionUpdateListener<T extends MediaSessionCompatApi14.Callback>
  implements RemoteControlClient.OnPlaybackPositionUpdateListener
{
  protected final T mCallback;
  
  public MediaSessionCompatApi18$OnPlaybackPositionUpdateListener(T paramT)
  {
    mCallback = paramT;
  }
  
  public void onPlaybackPositionUpdate(long paramLong)
  {
    mCallback.onSeekTo(paramLong);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi18.OnPlaybackPositionUpdateListener
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */