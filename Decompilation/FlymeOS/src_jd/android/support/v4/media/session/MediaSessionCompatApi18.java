package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.Context;
import android.media.AudioManager;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.os.SystemClock;

public class MediaSessionCompatApi18
{
  private static final long ACTION_SEEK_TO = 256L;
  
  public static Object createPlaybackPositionUpdateListener(MediaSessionCompatApi14.Callback paramCallback)
  {
    return new OnPlaybackPositionUpdateListener(paramCallback);
  }
  
  static int getRccTransportControlFlagsFromActions(long paramLong)
  {
    int j = MediaSessionCompatApi14.getRccTransportControlFlagsFromActions(paramLong);
    int i = j;
    if ((0x100 & paramLong) != 0L) {
      i = j | 0x100;
    }
    return i;
  }
  
  public static void registerMediaButtonEventReceiver(Context paramContext, PendingIntent paramPendingIntent)
  {
    ((AudioManager)paramContext.getSystemService("audio")).registerMediaButtonEventReceiver(paramPendingIntent);
  }
  
  public static void setOnPlaybackPositionUpdateListener(Object paramObject1, Object paramObject2)
  {
    ((RemoteControlClient)paramObject1).setPlaybackPositionUpdateListener((RemoteControlClient.OnPlaybackPositionUpdateListener)paramObject2);
  }
  
  public static void setState(Object paramObject, int paramInt, long paramLong1, float paramFloat, long paramLong2)
  {
    long l2 = 0L;
    long l3 = SystemClock.elapsedRealtime();
    long l1 = paramLong1;
    if (paramInt == 3)
    {
      l1 = paramLong1;
      if (paramLong1 > 0L)
      {
        l1 = l2;
        if (paramLong2 > 0L)
        {
          paramLong2 = l3 - paramLong2;
          l1 = paramLong2;
          if (paramFloat > 0.0F)
          {
            l1 = paramLong2;
            if (paramFloat != 1.0F) {
              l1 = ((float)paramLong2 * paramFloat);
            }
          }
        }
        l1 = paramLong1 + l1;
      }
    }
    paramInt = MediaSessionCompatApi14.getRccStateFromState(paramInt);
    ((RemoteControlClient)paramObject).setPlaybackState(paramInt, l1, paramFloat);
  }
  
  public static void setTransportControlFlags(Object paramObject, long paramLong)
  {
    ((RemoteControlClient)paramObject).setTransportControlFlags(getRccTransportControlFlagsFromActions(paramLong));
  }
  
  public static void unregisterMediaButtonEventReceiver(Context paramContext, PendingIntent paramPendingIntent)
  {
    ((AudioManager)paramContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(paramPendingIntent);
  }
  
  static class OnPlaybackPositionUpdateListener<T extends MediaSessionCompatApi14.Callback>
    implements RemoteControlClient.OnPlaybackPositionUpdateListener
  {
    protected final T mCallback;
    
    public OnPlaybackPositionUpdateListener(T paramT)
    {
      mCallback = paramT;
    }
    
    public void onPlaybackPositionUpdate(long paramLong)
    {
      mCallback.onSeekTo(paramLong);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaSessionCompatApi18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */