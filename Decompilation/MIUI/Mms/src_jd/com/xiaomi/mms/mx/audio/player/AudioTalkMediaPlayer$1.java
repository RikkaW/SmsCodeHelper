package com.xiaomi.mms.mx.audio.player;

import android.os.Handler;
import java.util.Observable;

class AudioTalkMediaPlayer$1
  extends MediaPlayerObserver
{
  AudioTalkMediaPlayer$1(AudioTalkMediaPlayer paramAudioTalkMediaPlayer, Handler paramHandler, AudioTalkMediaPlayer.PlayPair paramPlayPair)
  {
    super(paramHandler);
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    if (val$playItem.mObserver != null) {
      val$playItem.mObserver.update(paramObservable, paramObject);
    }
    if ((paramObject instanceof PlayerStatus))
    {
      paramObservable = (PlayerStatus)paramObject;
      if ((mType == 0) || (mType != 3)) {}
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */