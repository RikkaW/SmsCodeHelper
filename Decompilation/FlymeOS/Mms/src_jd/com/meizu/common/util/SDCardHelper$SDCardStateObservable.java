package com.meizu.common.util;

import android.content.Intent;
import android.database.Observable;
import java.util.ArrayList;

class SDCardHelper$SDCardStateObservable
  extends Observable<SDCardHelper.SDCardStateObserver>
{
  public void notifyStateChanged(Intent paramIntent, boolean paramBoolean)
  {
    synchronized (mObservers)
    {
      int i = mObservers.size() - 1;
      while (i >= 0)
      {
        ((SDCardHelper.SDCardStateObserver)mObservers.get(i)).onChanged(paramIntent, paramBoolean);
        i -= 1;
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.util.SDCardHelper.SDCardStateObservable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */