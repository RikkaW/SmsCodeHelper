package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

class MediaControllerCompat$Callback$StubCompat
  extends IMediaControllerCallback.Stub
{
  private MediaControllerCompat$Callback$StubCompat(MediaControllerCompat.Callback paramCallback) {}
  
  public void onEvent(String paramString, Bundle paramBundle)
  {
    MediaControllerCompat.Callback.access$200(this$0).post(1, paramString, paramBundle);
  }
  
  public void onExtrasChanged(Bundle paramBundle)
  {
    MediaControllerCompat.Callback.access$200(this$0).post(7, paramBundle, null);
  }
  
  public void onMetadataChanged(MediaMetadataCompat paramMediaMetadataCompat)
  {
    MediaControllerCompat.Callback.access$200(this$0).post(3, paramMediaMetadataCompat, null);
  }
  
  public void onPlaybackStateChanged(PlaybackStateCompat paramPlaybackStateCompat)
  {
    MediaControllerCompat.Callback.access$200(this$0).post(2, paramPlaybackStateCompat, null);
  }
  
  public void onQueueChanged(List<MediaSessionCompat.QueueItem> paramList)
  {
    MediaControllerCompat.Callback.access$200(this$0).post(5, paramList, null);
  }
  
  public void onQueueTitleChanged(CharSequence paramCharSequence)
  {
    MediaControllerCompat.Callback.access$200(this$0).post(6, paramCharSequence, null);
  }
  
  public void onSessionDestroyed()
  {
    MediaControllerCompat.Callback.access$200(this$0).post(8, null, null);
  }
  
  public void onVolumeInfoChanged(ParcelableVolumeInfo paramParcelableVolumeInfo)
  {
    if (paramParcelableVolumeInfo != null) {}
    for (paramParcelableVolumeInfo = new MediaControllerCompat.PlaybackInfo(volumeType, audioStream, controlType, maxVolume, currentVolume);; paramParcelableVolumeInfo = null)
    {
      MediaControllerCompat.Callback.access$200(this$0).post(4, paramParcelableVolumeInfo, null);
      return;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.Callback.StubCompat
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */