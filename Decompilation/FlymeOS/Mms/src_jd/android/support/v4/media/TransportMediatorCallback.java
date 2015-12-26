package android.support.v4.media;

import android.view.KeyEvent;

abstract interface TransportMediatorCallback
{
  public abstract long getPlaybackPosition();
  
  public abstract void handleAudioFocusChange(int paramInt);
  
  public abstract void handleKey(KeyEvent paramKeyEvent);
  
  public abstract void playbackPositionUpdate(long paramLong);
}

/* Location:
 * Qualified Name:     android.support.v4.media.TransportMediatorCallback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */