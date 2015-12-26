package android.support.v4.media.session;

import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

public abstract interface MediaSessionCompatApi14$Callback
{
  public abstract void onCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver);
  
  public abstract void onFastForward();
  
  public abstract boolean onMediaButtonEvent(Intent paramIntent);
  
  public abstract void onPause();
  
  public abstract void onPlay();
  
  public abstract void onRewind();
  
  public abstract void onSeekTo(long paramLong);
  
  public abstract void onSetRating(Object paramObject);
  
  public abstract void onSkipToNext();
  
  public abstract void onSkipToPrevious();
  
  public abstract void onStop();
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi14.Callback
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */