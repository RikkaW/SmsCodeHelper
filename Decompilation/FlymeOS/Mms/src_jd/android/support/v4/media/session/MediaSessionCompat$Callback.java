package android.support.v4.media.session;

import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.media.RatingCompat;

public abstract class MediaSessionCompat$Callback
{
  final Object mCallbackObj;
  
  public MediaSessionCompat$Callback()
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21(null));
      return;
    }
    mCallbackObj = null;
  }
  
  public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver) {}
  
  public void onCustomAction(String paramString, Bundle paramBundle) {}
  
  public void onFastForward() {}
  
  public boolean onMediaButtonEvent(Intent paramIntent)
  {
    return false;
  }
  
  public void onPause() {}
  
  public void onPlay() {}
  
  public void onPlayFromMediaId(String paramString, Bundle paramBundle) {}
  
  public void onPlayFromSearch(String paramString, Bundle paramBundle) {}
  
  public void onRewind() {}
  
  public void onSeekTo(long paramLong) {}
  
  public void onSetRating(RatingCompat paramRatingCompat) {}
  
  public void onSkipToNext() {}
  
  public void onSkipToPrevious() {}
  
  public void onSkipToQueueItem(long paramLong) {}
  
  public void onStop() {}
  
  class StubApi21
    implements MediaSessionCompatApi21.Callback
  {
    private StubApi21() {}
    
    public void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
    {
      MediaSessionCompat.Callback.this.onCommand(paramString, paramBundle, paramResultReceiver);
    }
    
    public void onCustomAction(String paramString, Bundle paramBundle)
    {
      MediaSessionCompat.Callback.this.onCustomAction(paramString, paramBundle);
    }
    
    public void onFastForward()
    {
      MediaSessionCompat.Callback.this.onFastForward();
    }
    
    public boolean onMediaButtonEvent(Intent paramIntent)
    {
      return MediaSessionCompat.Callback.this.onMediaButtonEvent(paramIntent);
    }
    
    public void onPause()
    {
      MediaSessionCompat.Callback.this.onPause();
    }
    
    public void onPlay()
    {
      MediaSessionCompat.Callback.this.onPlay();
    }
    
    public void onPlayFromMediaId(String paramString, Bundle paramBundle)
    {
      MediaSessionCompat.Callback.this.onPlayFromMediaId(paramString, paramBundle);
    }
    
    public void onPlayFromSearch(String paramString, Bundle paramBundle)
    {
      MediaSessionCompat.Callback.this.onPlayFromSearch(paramString, paramBundle);
    }
    
    public void onRewind()
    {
      MediaSessionCompat.Callback.this.onRewind();
    }
    
    public void onSeekTo(long paramLong)
    {
      MediaSessionCompat.Callback.this.onSeekTo(paramLong);
    }
    
    public void onSetRating(Object paramObject)
    {
      onSetRating(RatingCompat.fromRating(paramObject));
    }
    
    public void onSkipToNext()
    {
      MediaSessionCompat.Callback.this.onSkipToNext();
    }
    
    public void onSkipToPrevious()
    {
      MediaSessionCompat.Callback.this.onSkipToPrevious();
    }
    
    public void onSkipToQueueItem(long paramLong)
    {
      MediaSessionCompat.Callback.this.onSkipToQueueItem(paramLong);
    }
    
    public void onStop()
    {
      MediaSessionCompat.Callback.this.onStop();
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompat.Callback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */