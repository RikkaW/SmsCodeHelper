package android.support.v4.media.session;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

public abstract class MediaControllerCompat$Callback
  implements IBinder.DeathRecipient
{
  private final Object mCallbackObj;
  private MessageHandler mHandler;
  private boolean mRegistered = false;
  
  public MediaControllerCompat$Callback()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      mCallbackObj = MediaControllerCompatApi21.createCallback(new StubApi21(null));
      return;
    }
    mCallbackObj = new StubCompat(null);
  }
  
  private void setHandler(Handler paramHandler)
  {
    mHandler = new MessageHandler(paramHandler.getLooper());
  }
  
  public void binderDied()
  {
    onSessionDestroyed();
  }
  
  public void onAudioInfoChanged(MediaControllerCompat.PlaybackInfo paramPlaybackInfo) {}
  
  public void onExtrasChanged(Bundle paramBundle) {}
  
  public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat) {}
  
  public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat) {}
  
  public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList) {}
  
  public void onQueueTitleChanged(CharSequence paramCharSequence) {}
  
  public void onSessionDestroyed() {}
  
  public void onSessionEvent(String paramString, Bundle paramBundle) {}
  
  class MessageHandler
    extends Handler
  {
    private static final int MSG_DESTROYED = 8;
    private static final int MSG_EVENT = 1;
    private static final int MSG_UPDATE_EXTRAS = 7;
    private static final int MSG_UPDATE_METADATA = 3;
    private static final int MSG_UPDATE_PLAYBACK_STATE = 2;
    private static final int MSG_UPDATE_QUEUE = 5;
    private static final int MSG_UPDATE_QUEUE_TITLE = 6;
    private static final int MSG_UPDATE_VOLUME = 4;
    
    public MessageHandler(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (!mRegistered) {
        return;
      }
      switch (what)
      {
      default: 
        return;
      case 1: 
        onSessionEvent((String)obj, paramMessage.getData());
        return;
      case 2: 
        onPlaybackStateChanged((PlaybackStateCompat)obj);
        return;
      case 3: 
        onMetadataChanged((MediaMetadataCompat)obj);
        return;
      case 5: 
        onQueueChanged((List)obj);
        return;
      case 6: 
        onQueueTitleChanged((CharSequence)obj);
        return;
      case 7: 
        onExtrasChanged((Bundle)obj);
        return;
      case 4: 
        onAudioInfoChanged((MediaControllerCompat.PlaybackInfo)obj);
        return;
      }
      onSessionDestroyed();
    }
    
    public void post(int paramInt, Object paramObject, Bundle paramBundle)
    {
      obtainMessage(paramInt, paramObject).sendToTarget();
    }
  }
  
  class StubApi21
    implements MediaControllerCompatApi21.Callback
  {
    private StubApi21() {}
    
    public void onMetadataChanged(Object paramObject)
    {
      onMetadataChanged(MediaMetadataCompat.fromMediaMetadata(paramObject));
    }
    
    public void onPlaybackStateChanged(Object paramObject)
    {
      onPlaybackStateChanged(PlaybackStateCompat.fromPlaybackState(paramObject));
    }
    
    public void onSessionDestroyed()
    {
      MediaControllerCompat.Callback.this.onSessionDestroyed();
    }
    
    public void onSessionEvent(String paramString, Bundle paramBundle)
    {
      MediaControllerCompat.Callback.this.onSessionEvent(paramString, paramBundle);
    }
  }
  
  class StubCompat
    extends IMediaControllerCallback.Stub
  {
    private StubCompat() {}
    
    public void onEvent(String paramString, Bundle paramBundle)
    {
      mHandler.post(1, paramString, paramBundle);
    }
    
    public void onExtrasChanged(Bundle paramBundle)
    {
      mHandler.post(7, paramBundle, null);
    }
    
    public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
    {
      mHandler.post(3, paramMediaMetadataCompat, null);
    }
    
    public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat)
    {
      mHandler.post(2, paramPlaybackStateCompat, null);
    }
    
    public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
    {
      mHandler.post(5, paramList, null);
    }
    
    public void onQueueTitleChanged(CharSequence paramCharSequence)
    {
      mHandler.post(6, paramCharSequence, null);
    }
    
    public void onSessionDestroyed()
    {
      mHandler.post(8, null, null);
    }
    
    public void onVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
    {
      if (paramParcelableVolumeInfo != null) {}
      for (paramParcelableVolumeInfo = new MediaControllerCompat.PlaybackInfo(volumeType, audioStream, controlType, maxVolume, currentVolume);; paramParcelableVolumeInfo = null)
      {
        mHandler.post(4, paramParcelableVolumeInfo, null);
        return;
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.Callback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */