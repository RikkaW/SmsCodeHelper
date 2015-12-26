package android.support.v4.media.session;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import java.util.List;

abstract interface MediaControllerCompat$MediaControllerImpl
{
  public abstract void adjustVolume(int paramInt1, int paramInt2);
  
  public abstract boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent);
  
  public abstract Bundle getExtras();
  
  public abstract long getFlags();
  
  public abstract Object getMediaController();
  
  public abstract MediaMetadataCompat getMetadata();
  
  public abstract String getPackageName();
  
  public abstract MediaControllerCompat.PlaybackInfo getPlaybackInfo();
  
  public abstract PlaybackStateCompat getPlaybackState();
  
  public abstract List<MediaSessionCompat.QueueItem> getQueue();
  
  public abstract CharSequence getQueueTitle();
  
  public abstract int getRatingType();
  
  public abstract PendingIntent getSessionActivity();
  
  public abstract MediaControllerCompat.TransportControls getTransportControls();
  
  public abstract void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler);
  
  public abstract void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);
  
  public abstract void setVolumeTo(int paramInt1, int paramInt2);
  
  public abstract void unregisterCallback(MediaControllerCompat.Callback paramCallback);
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.MediaControllerImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */