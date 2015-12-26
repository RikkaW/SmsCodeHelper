package android.support.v4.view;

import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.View;

class KeyEventCompat$EclairKeyEventVersionImpl
  extends KeyEventCompat.BaseKeyEventVersionImpl
{
  public boolean dispatch(KeyEvent paramKeyEvent, KeyEvent.Callback paramCallback, Object paramObject1, Object paramObject2)
  {
    return KeyEventCompatEclair.dispatch(paramKeyEvent, paramCallback, paramObject1, paramObject2);
  }
  
  public Object getKeyDispatcherState(View paramView)
  {
    return KeyEventCompatEclair.getKeyDispatcherState(paramView);
  }
  
  public boolean isTracking(KeyEvent paramKeyEvent)
  {
    return KeyEventCompatEclair.isTracking(paramKeyEvent);
  }
  
  public void startTracking(KeyEvent paramKeyEvent)
  {
    KeyEventCompatEclair.startTracking(paramKeyEvent);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.view.KeyEventCompat.EclairKeyEventVersionImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */