package com.xiaomi.mms.mx.audio.player;

import java.util.Observable;

public class PlayerObservable
  extends Observable
{
  public void notifyObservers(Object paramObject)
  {
    super.setChanged();
    super.notifyObservers(paramObject);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.audio.player.PlayerObservable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */