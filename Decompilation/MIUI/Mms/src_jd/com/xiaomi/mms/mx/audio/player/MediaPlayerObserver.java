package com.xiaomi.mms.mx.audio.player;

import android.os.Handler;
import android.os.Message;
import java.util.Observable;
import java.util.Observer;

public class MediaPlayerObserver
  implements Observer
{
  private final Handler mHandler;
  
  public MediaPlayerObserver(Handler paramHandler)
  {
    mHandler = paramHandler;
  }
  
  public void update(Observable paramObservable, Object paramObject)
  {
    paramObservable = new Message();
    obj = paramObject;
    mHandler.sendMessage(paramObservable);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.MediaPlayerObserver
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */