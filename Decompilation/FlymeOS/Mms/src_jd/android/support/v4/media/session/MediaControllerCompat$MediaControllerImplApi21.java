package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.view.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MediaControllerCompat$MediaControllerImplApi21
  implements MediaControllerCompat.MediaControllerImpl
{
  private final Object mControllerObj;
  
  public MediaControllerCompat$MediaControllerImplApi21(Context paramContext, MediaSessionCompat.Token paramToken)
  {
    mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, paramToken.getToken());
    if (mControllerObj == null) {
      throw new RemoteException();
    }
  }
  
  public MediaControllerCompat$MediaControllerImplApi21(Context paramContext, MediaSessionCompat paramMediaSessionCompat)
  {
    mControllerObj = MediaControllerCompatApi21.fromToken(paramContext, paramMediaSessionCompat.getSessionToken().getToken());
  }
  
  public void adjustVolume(int paramInt1, int paramInt2)
  {
    MediaControllerCompatApi21.adjustVolume(mControllerObj, paramInt1, paramInt2);
  }
  
  public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
  {
    return MediaControllerCompatApi21.dispatchMediaButtonEvent(mControllerObj, paramKeyEvent);
  }
  
  public Bundle getExtras()
  {
    return MediaControllerCompatApi21.getExtras(mControllerObj);
  }
  
  public long getFlags()
  {
    return MediaControllerCompatApi21.getFlags(mControllerObj);
  }
  
  public Object getMediaController()
  {
    return mControllerObj;
  }
  
  public MediaMetadataCompat getMetadata()
  {
    Object localObject = MediaControllerCompatApi21.getMetadata(mControllerObj);
    if (localObject != null) {
      return MediaMetadataCompat.fromMediaMetadata(localObject);
    }
    return null;
  }
  
  public String getPackageName()
  {
    return MediaControllerCompatApi21.getPackageName(mControllerObj);
  }
  
  public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
  {
    Object localObject = MediaControllerCompatApi21.getPlaybackInfo(mControllerObj);
    if (localObject != null) {
      return new MediaControllerCompat.PlaybackInfo(MediaControllerCompatApi21.PlaybackInfo.getPlaybackType(localObject), MediaControllerCompatApi21.PlaybackInfo.getLegacyAudioStream(localObject), MediaControllerCompatApi21.PlaybackInfo.getVolumeControl(localObject), MediaControllerCompatApi21.PlaybackInfo.getMaxVolume(localObject), MediaControllerCompatApi21.PlaybackInfo.getCurrentVolume(localObject));
    }
    return null;
  }
  
  public PlaybackStateCompat getPlaybackState()
  {
    Object localObject = MediaControllerCompatApi21.getPlaybackState(mControllerObj);
    if (localObject != null) {
      return PlaybackStateCompat.fromPlaybackState(localObject);
    }
    return null;
  }
  
  public List<MediaSessionCompat.QueueItem> getQueue()
  {
    Object localObject = MediaControllerCompatApi21.getQueue(mControllerObj);
    if (localObject == null)
    {
      localObject = null;
      return (List<MediaSessionCompat.QueueItem>)localObject;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = ((List)localObject).iterator();
    for (;;)
    {
      localObject = localArrayList;
      if (!localIterator.hasNext()) {
        break;
      }
      localArrayList.add(MediaSessionCompat.QueueItem.obtain(localIterator.next()));
    }
  }
  
  public CharSequence getQueueTitle()
  {
    return MediaControllerCompatApi21.getQueueTitle(mControllerObj);
  }
  
  public int getRatingType()
  {
    return MediaControllerCompatApi21.getRatingType(mControllerObj);
  }
  
  public PendingIntent getSessionActivity()
  {
    return MediaControllerCompatApi21.getSessionActivity(mControllerObj);
  }
  
  public MediaControllerCompat.TransportControls getTransportControls()
  {
    Object localObject = MediaControllerCompatApi21.getTransportControls(mControllerObj);
    if (localObject != null) {
      return new MediaControllerCompat.TransportControlsApi21(localObject);
    }
    return null;
  }
  
  public void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
  {
    MediaControllerCompatApi21.registerCallback(mControllerObj, MediaControllerCompat.Callback.access$400(paramCallback), paramHandler);
  }
  
  public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    MediaControllerCompatApi21.sendCommand(mControllerObj, paramString, paramBundle, paramResultReceiver);
  }
  
  public void setVolumeTo(int paramInt1, int paramInt2)
  {
    MediaControllerCompatApi21.setVolumeTo(mControllerObj, paramInt1, paramInt2);
  }
  
  public void unregisterCallback(MediaControllerCompat.Callback paramCallback)
  {
    MediaControllerCompatApi21.unregisterCallback(mControllerObj, MediaControllerCompat.Callback.access$400(paramCallback));
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.MediaControllerImplApi21
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */