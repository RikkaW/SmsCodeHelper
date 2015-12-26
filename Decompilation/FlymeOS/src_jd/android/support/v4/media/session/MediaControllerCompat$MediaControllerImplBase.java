package android.support.v4.media.session;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.v4.media.MediaMetadataCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

class MediaControllerCompat$MediaControllerImplBase
  implements MediaControllerCompat.MediaControllerImpl
{
  private IMediaSession mBinder;
  private MediaSessionCompat.Token mToken;
  private MediaControllerCompat.TransportControls mTransportControls;
  
  public MediaControllerCompat$MediaControllerImplBase(MediaSessionCompat.Token paramToken)
  {
    mToken = paramToken;
    mBinder = IMediaSession.Stub.asInterface((IBinder)paramToken.getToken());
  }
  
  public void adjustVolume(int paramInt1, int paramInt2)
  {
    try
    {
      mBinder.adjustVolume(paramInt1, paramInt2, null);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in adjustVolume. " + localRemoteException);
    }
  }
  
  public boolean dispatchMediaButtonEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent == null) {
      throw new IllegalArgumentException("event may not be null.");
    }
    try
    {
      mBinder.sendMediaButton(paramKeyEvent);
      return false;
    }
    catch (RemoteException paramKeyEvent)
    {
      for (;;)
      {
        Log.e("MediaControllerCompat", "Dead object in dispatchMediaButtonEvent. " + paramKeyEvent);
      }
    }
  }
  
  public Bundle getExtras()
  {
    try
    {
      Bundle localBundle = mBinder.getExtras();
      return localBundle;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getExtras. " + localRemoteException);
    }
    return null;
  }
  
  public long getFlags()
  {
    try
    {
      long l = mBinder.getFlags();
      return l;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getFlags. " + localRemoteException);
    }
    return 0L;
  }
  
  public Object getMediaController()
  {
    return null;
  }
  
  public MediaMetadataCompat getMetadata()
  {
    try
    {
      MediaMetadataCompat localMediaMetadataCompat = mBinder.getMetadata();
      return localMediaMetadataCompat;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getMetadata. " + localRemoteException);
    }
    return null;
  }
  
  public String getPackageName()
  {
    try
    {
      String str = mBinder.getPackageName();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getPackageName. " + localRemoteException);
    }
    return null;
  }
  
  public MediaControllerCompat.PlaybackInfo getPlaybackInfo()
  {
    try
    {
      Object localObject = mBinder.getVolumeAttributes();
      localObject = new MediaControllerCompat.PlaybackInfo(volumeType, audioStream, controlType, maxVolume, currentVolume);
      return (MediaControllerCompat.PlaybackInfo)localObject;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getPlaybackInfo. " + localRemoteException);
    }
    return null;
  }
  
  public PlaybackStateCompat getPlaybackState()
  {
    try
    {
      PlaybackStateCompat localPlaybackStateCompat = mBinder.getPlaybackState();
      return localPlaybackStateCompat;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getPlaybackState. " + localRemoteException);
    }
    return null;
  }
  
  public List<MediaSessionCompat.QueueItem> getQueue()
  {
    try
    {
      List localList = mBinder.getQueue();
      return localList;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getQueue. " + localRemoteException);
    }
    return null;
  }
  
  public CharSequence getQueueTitle()
  {
    try
    {
      CharSequence localCharSequence = mBinder.getQueueTitle();
      return localCharSequence;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getQueueTitle. " + localRemoteException);
    }
    return null;
  }
  
  public int getRatingType()
  {
    try
    {
      int i = mBinder.getRatingType();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getRatingType. " + localRemoteException);
    }
    return 0;
  }
  
  public PendingIntent getSessionActivity()
  {
    try
    {
      PendingIntent localPendingIntent = mBinder.getLaunchPendingIntent();
      return localPendingIntent;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in getSessionActivity. " + localRemoteException);
    }
    return null;
  }
  
  public MediaControllerCompat.TransportControls getTransportControls()
  {
    if (mTransportControls == null) {
      mTransportControls = new MediaControllerCompat.TransportControlsBase(mBinder);
    }
    return mTransportControls;
  }
  
  public void registerCallback(MediaControllerCompat.Callback paramCallback, Handler paramHandler)
  {
    if (paramCallback == null) {
      throw new IllegalArgumentException("callback may not be null.");
    }
    try
    {
      mBinder.asBinder().linkToDeath(paramCallback, 0);
      mBinder.registerCallbackListener((IMediaControllerCallback)MediaControllerCompat.Callback.access$400(paramCallback));
      MediaControllerCompat.Callback.access$500(paramCallback, paramHandler);
      MediaControllerCompat.Callback.access$302(paramCallback, true);
      return;
    }
    catch (RemoteException paramHandler)
    {
      Log.e("MediaControllerCompat", "Dead object in registerCallback. " + paramHandler);
      paramCallback.onSessionDestroyed();
    }
  }
  
  public void sendCommand(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver)
  {
    try
    {
      mBinder.sendCommand(paramString, paramBundle, new MediaSessionCompat.ResultReceiverWrapper(paramResultReceiver));
      return;
    }
    catch (RemoteException paramString)
    {
      Log.e("MediaControllerCompat", "Dead object in sendCommand. " + paramString);
    }
  }
  
  public void setVolumeTo(int paramInt1, int paramInt2)
  {
    try
    {
      mBinder.setVolumeTo(paramInt1, paramInt2, null);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("MediaControllerCompat", "Dead object in setVolumeTo. " + localRemoteException);
    }
  }
  
  public void unregisterCallback(MediaControllerCompat.Callback paramCallback)
  {
    if (paramCallback == null) {
      throw new IllegalArgumentException("callback may not be null.");
    }
    try
    {
      mBinder.unregisterCallbackListener((IMediaControllerCallback)MediaControllerCompat.Callback.access$400(paramCallback));
      mBinder.asBinder().unlinkToDeath(paramCallback, 0);
      MediaControllerCompat.Callback.access$302(paramCallback, false);
      return;
    }
    catch (RemoteException paramCallback)
    {
      Log.e("MediaControllerCompat", "Dead object in unregisterCallback. " + paramCallback);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.MediaControllerImplBase
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */