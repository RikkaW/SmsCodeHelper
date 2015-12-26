package android.support.v4.media.session;

import android.media.Rating;
import android.media.session.MediaController.TransportControls;
import android.os.Bundle;

public class MediaControllerCompatApi21$TransportControls
{
  public static void fastForward(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).fastForward();
  }
  
  public static void pause(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).pause();
  }
  
  public static void play(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).play();
  }
  
  public static void playFromMediaId(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaController.TransportControls)paramObject).playFromMediaId(paramString, paramBundle);
  }
  
  public static void playFromSearch(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaController.TransportControls)paramObject).playFromSearch(paramString, paramBundle);
  }
  
  public static void rewind(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).rewind();
  }
  
  public static void seekTo(Object paramObject, long paramLong)
  {
    ((MediaController.TransportControls)paramObject).seekTo(paramLong);
  }
  
  public static void sendCustomAction(Object paramObject, String paramString, Bundle paramBundle)
  {
    ((MediaController.TransportControls)paramObject).sendCustomAction(paramString, paramBundle);
  }
  
  public static void setRating(Object paramObject1, Object paramObject2)
  {
    ((MediaController.TransportControls)paramObject1).setRating((Rating)paramObject2);
  }
  
  public static void skipToNext(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).skipToNext();
  }
  
  public static void skipToPrevious(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).skipToPrevious();
  }
  
  public static void skipToQueueItem(Object paramObject, long paramLong)
  {
    ((MediaController.TransportControls)paramObject).skipToQueueItem(paramLong);
  }
  
  public static void stop(Object paramObject)
  {
    ((MediaController.TransportControls)paramObject).stop();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompatApi21.TransportControls
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */