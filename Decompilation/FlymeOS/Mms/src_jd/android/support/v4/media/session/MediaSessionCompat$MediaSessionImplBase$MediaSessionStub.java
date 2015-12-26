package android.support.v4.media.session;

import android.app.PendingIntent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.support.v4.media.VolumeProviderCompat;
import android.view.KeyEvent;
import java.util.List;

class MediaSessionCompat$MediaSessionImplBase$MediaSessionStub
  extends IMediaSession.Stub
{
  MediaSessionCompat$MediaSessionImplBase$MediaSessionStub(MediaSessionCompat.MediaSessionImplBase paramMediaSessionImplBase) {}
  
  public void adjustVolume(int paramInt1, int paramInt2, String paramString)
  {
    MediaSessionCompat.MediaSessionImplBase.access$1600(this$0, paramInt1, paramInt2);
  }
  
  public void fastForward()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(9);
  }
  
  public Bundle getExtras()
  {
    synchronized (MediaSessionCompat.MediaSessionImplBase.access$1300(this$0))
    {
      Bundle localBundle = MediaSessionCompat.MediaSessionImplBase.access$2200(this$0);
      return localBundle;
    }
  }
  
  public long getFlags()
  {
    synchronized (MediaSessionCompat.MediaSessionImplBase.access$1300(this$0))
    {
      long l = MediaSessionCompat.MediaSessionImplBase.access$800(this$0);
      return l;
    }
  }
  
  public PendingIntent getLaunchPendingIntent()
  {
    synchronized (MediaSessionCompat.MediaSessionImplBase.access$1300(this$0))
    {
      PendingIntent localPendingIntent = MediaSessionCompat.MediaSessionImplBase.access$1400(this$0);
      return localPendingIntent;
    }
  }
  
  public MediaMetadataCompat getMetadata()
  {
    return MediaSessionCompat.MediaSessionImplBase.access$1800(this$0);
  }
  
  public String getPackageName()
  {
    return MediaSessionCompat.MediaSessionImplBase.access$1100(this$0);
  }
  
  public PlaybackStateCompat getPlaybackState()
  {
    return MediaSessionCompat.MediaSessionImplBase.access$1900(this$0);
  }
  
  public List<MediaSessionCompat.QueueItem> getQueue()
  {
    synchronized (MediaSessionCompat.MediaSessionImplBase.access$1300(this$0))
    {
      List localList = MediaSessionCompat.MediaSessionImplBase.access$2000(this$0);
      return localList;
    }
  }
  
  public CharSequence getQueueTitle()
  {
    return MediaSessionCompat.MediaSessionImplBase.access$2100(this$0);
  }
  
  public int getRatingType()
  {
    return MediaSessionCompat.MediaSessionImplBase.access$2300(this$0);
  }
  
  public String getTag()
  {
    return MediaSessionCompat.MediaSessionImplBase.access$1200(this$0);
  }
  
  public ParcelableVolumeInfo getVolumeAttributes()
  {
    int i = 2;
    synchronized (MediaSessionCompat.MediaSessionImplBase.access$1300(this$0))
    {
      int m = MediaSessionCompat.MediaSessionImplBase.access$300(this$0);
      int n = MediaSessionCompat.MediaSessionImplBase.access$400(this$0);
      VolumeProviderCompat localVolumeProviderCompat = MediaSessionCompat.MediaSessionImplBase.access$200(this$0);
      if (m == 2)
      {
        i = localVolumeProviderCompat.getVolumeControl();
        j = localVolumeProviderCompat.getMaxVolume();
        k = localVolumeProviderCompat.getCurrentVolume();
        return new ParcelableVolumeInfo(m, n, i, j, k);
      }
      int j = MediaSessionCompat.MediaSessionImplBase.access$1500(this$0).getStreamMaxVolume(n);
      int k = MediaSessionCompat.MediaSessionImplBase.access$1500(this$0).getStreamVolume(n);
    }
  }
  
  public boolean isTransportControlEnabled()
  {
    return (MediaSessionCompat.MediaSessionImplBase.access$800(this$0) & 0x2) != 0;
  }
  
  public void next()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(7);
  }
  
  public void pause()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(5);
  }
  
  public void play()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(1);
  }
  
  public void playFromMediaId(String paramString, Bundle paramBundle)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(2, paramString, paramBundle);
  }
  
  public void playFromSearch(String paramString, Bundle paramBundle)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(3, paramString, paramBundle);
  }
  
  public void previous()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(8);
  }
  
  public void rate(RatingCompat paramRatingCompat)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(12, paramRatingCompat);
  }
  
  public void registerCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
  {
    if (MediaSessionCompat.MediaSessionImplBase.access$900(this$0)) {}
    try
    {
      paramIMediaControllerCallback.onSessionDestroyed();
      return;
    }
    catch (Exception paramIMediaControllerCallback) {}
    MediaSessionCompat.MediaSessionImplBase.access$1000(this$0).register(paramIMediaControllerCallback);
    return;
  }
  
  public void rewind()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(10);
  }
  
  public void seekTo(long paramLong)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(11, Long.valueOf(paramLong));
  }
  
  public void sendCommand(String paramString, Bundle paramBundle, MediaSessionCompat.ResultReceiverWrapper paramResultReceiverWrapper)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(15, new MediaSessionCompat.MediaSessionImplBase.Command(paramString, paramBundle, MediaSessionCompat.ResultReceiverWrapper.access$600(paramResultReceiverWrapper)));
  }
  
  public void sendCustomAction(String paramString, Bundle paramBundle)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(13, paramString, paramBundle);
  }
  
  public boolean sendMediaButton(KeyEvent paramKeyEvent)
  {
    if ((MediaSessionCompat.MediaSessionImplBase.access$800(this$0) & 0x1) != 0) {}
    for (boolean bool = true;; bool = false)
    {
      if (bool) {
        MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(14, paramKeyEvent);
      }
      return bool;
    }
  }
  
  public void setVolumeTo(int paramInt1, int paramInt2, String paramString)
  {
    MediaSessionCompat.MediaSessionImplBase.access$1700(this$0, paramInt1, paramInt2);
  }
  
  public void skipToQueueItem(long paramLong)
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(4, Long.valueOf(paramLong));
  }
  
  public void stop()
  {
    MediaSessionCompat.MediaSessionImplBase.access$700(this$0).post(6);
  }
  
  public void unregisterCallbackListener(IMediaControllerCallback paramIMediaControllerCallback)
  {
    MediaSessionCompat.MediaSessionImplBase.access$1000(this$0).unregister(paramIMediaControllerCallback);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.MediaSessionStub
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */