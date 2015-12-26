package android.support.v4.media;

import android.view.KeyEvent;
import android.view.KeyEvent.Callback;

class TransportMediator$2
  implements KeyEvent.Callback
{
  TransportMediator$2(TransportMediator paramTransportMediator) {}
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (TransportMediator.isMediaKey(paramInt)) {
      return this$0.mCallbacks.onMediaButtonDown(paramInt, paramKeyEvent);
    }
    return false;
  }
  
  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }
  
  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent)
  {
    return false;
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (TransportMediator.isMediaKey(paramInt)) {
      return this$0.mCallbacks.onMediaButtonUp(paramInt, paramKeyEvent);
    }
    return false;
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.TransportMediator.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */