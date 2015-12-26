package android.support.v4.media;

import android.media.AudioManager.OnAudioFocusChangeListener;

class TransportMediatorJellybeanMR2$4
  implements AudioManager.OnAudioFocusChangeListener
{
  TransportMediatorJellybeanMR2$4(TransportMediatorJellybeanMR2 paramTransportMediatorJellybeanMR2) {}
  
  public void onAudioFocusChange(int paramInt)
  {
    this$0.mTransportCallback.handleAudioFocusChange(paramInt);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.TransportMediatorJellybeanMR2.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */