package android.support.v4.media.session;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.RatingCompat;

class MediaSessionCompat$MediaSessionImplBase$2
  implements MediaSessionCompatApi14.Callback
{
  MediaSessionCompat$MediaSessionImplBase$2(MediaSessionCompat.MediaSessionImplBase paramMediaSessionImplBase, MediaSessionCompat.Callback paramCallback) {}
  
  public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    val$callback.onCommand(paramString, paramBundle, paramResultReceiver);
  }
  
  public void onFastForward()
  {
    val$callback.onFastForward();
  }
  
  public boolean onMediaButtonEvent(Intent paramIntent)
  {
    return val$callback.onMediaButtonEvent(paramIntent);
  }
  
  public void onPause()
  {
    val$callback.onPause();
  }
  
  public void onPlay()
  {
    val$callback.onPlay();
  }
  
  public void onRewind()
  {
    val$callback.onRewind();
  }
  
  public void onSeekTo(long paramLong)
  {
    val$callback.onSeekTo(paramLong);
  }
  
  public void onSetRating(Object paramObject)
  {
    val$callback.onSetRating(RatingCompat.fromRating(paramObject));
  }
  
  public void onSkipToNext()
  {
    val$callback.onSkipToNext();
  }
  
  public void onSkipToPrevious()
  {
    val$callback.onSkipToPrevious();
  }
  
  public void onStop()
  {
    val$callback.onStop();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.MediaSessionImplBase.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */