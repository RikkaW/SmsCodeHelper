package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

class MediaControllerCompat$Callback$MessageHandler
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
  
  public MediaControllerCompat$Callback$MessageHandler(MediaControllerCompat.Callback paramCallback, Looper paramLooper)
  {
    super(paramLooper);
  }
  
  public void handleMessage(Message paramMessage)
  {
    if (!MediaControllerCompat.Callback.access$300(this$0)) {
      return;
    }
    switch (what)
    {
    default: 
      return;
    case 1: 
      this$0.onSessionEvent((String)obj, paramMessage.getData());
      return;
    case 2: 
      this$0.onPlaybackStateChanged((PlaybackStateCompat)obj);
      return;
    case 3: 
      this$0.onMetadataChanged((MediaMetadataCompat)obj);
      return;
    case 5: 
      this$0.onQueueChanged((List)obj);
      return;
    case 6: 
      this$0.onQueueTitleChanged((CharSequence)obj);
      return;
    case 7: 
      this$0.onExtrasChanged((Bundle)obj);
      return;
    case 4: 
      this$0.onAudioInfoChanged((MediaControllerCompat.PlaybackInfo)obj);
      return;
    }
    this$0.onSessionDestroyed();
  }
  
  public void post(int paramInt, Object paramObject, Bundle paramBundle)
  {
    obtainMessage(paramInt, paramObject).sendToTarget();
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.session.MediaControllerCompat.Callback.MessageHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */