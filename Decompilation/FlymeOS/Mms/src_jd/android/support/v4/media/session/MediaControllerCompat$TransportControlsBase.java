package android.support.v4.media.session;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.RatingCompat;
import android.util.Log;

class MediaControllerCompat$TransportControlsBase
  extends MediaControllerCompat.TransportControls
{
  private IMediaSession mBinder;
  
  public MediaControllerCompat$TransportControlsBase(IMediaSession paramIMediaSession)
  {
    mBinder = paramIMediaSession;
  }
  
  public void fastForward()
  {
    try
    {
      mBinder.fastForward();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in fastForward. " + localRemoteException);
    }
  }
  
  public void pause()
  {
    try
    {
      mBinder.pause();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in pause. " + localRemoteException);
    }
  }
  
  public void play()
  {
    try
    {
      mBinder.play();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in play. " + localRemoteException);
    }
  }
  
  public void playFromMediaId(String paramString, Bundle paramBundle)
  {
    try
    {
      mBinder.playFromMediaId(paramString, paramBundle);
      return;
    }
    catch (RemoteException paramString)
    {
      Log.e("MediaControllerCompat", "Dead object in playFromMediaId. " + paramString);
    }
  }
  
  public void playFromSearch(String paramString, Bundle paramBundle)
  {
    try
    {
      mBinder.playFromSearch(paramString, paramBundle);
      return;
    }
    catch (RemoteException paramString)
    {
      Log.e("MediaControllerCompat", "Dead object in playFromSearch. " + paramString);
    }
  }
  
  public void rewind()
  {
    try
    {
      mBinder.rewind();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in rewind. " + localRemoteException);
    }
  }
  
  public void seekTo(long paramLong)
  {
    try
    {
      mBinder.seekTo(paramLong);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in seekTo. " + localRemoteException);
    }
  }
  
  public void sendCustomAction(PlaybackStateCompat.CustomAction paramCustomAction, Bundle paramBundle)
  {
    sendCustomAction(paramCustomAction.getAction(), paramBundle);
  }
  
  public void sendCustomAction(String paramString, Bundle paramBundle)
  {
    try
    {
      mBinder.sendCustomAction(paramString, paramBundle);
      return;
    }
    catch (RemoteException paramString)
    {
      Log.e("MediaControllerCompat", "Dead object in sendCustomAction. " + paramString);
    }
  }
  
  public void setRating(RatingCompat paramRatingCompat)
  {
    try
    {
      mBinder.rate(paramRatingCompat);
      return;
    }
    catch (RemoteException paramRatingCompat)
    {
      Log.e("MediaControllerCompat", "Dead object in setRating. " + paramRatingCompat);
    }
  }
  
  public void skipToNext()
  {
    try
    {
      mBinder.next();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in skipToNext. " + localRemoteException);
    }
  }
  
  public void skipToPrevious()
  {
    try
    {
      mBinder.previous();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in skipToPrevious. " + localRemoteException);
    }
  }
  
  public void skipToQueueItem(long paramLong)
  {
    try
    {
      mBinder.skipToQueueItem(paramLong);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in skipToQueueItem. " + localRemoteException);
    }
  }
  
  public void stop()
  {
    try
    {
      mBinder.stop();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in stop. " + localRemoteException);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.TransportControlsBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */