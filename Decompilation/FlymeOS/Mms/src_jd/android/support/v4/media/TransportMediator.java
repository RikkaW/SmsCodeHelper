package android.support.v4.media;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build.VERSION;
import android.support.v4.view.KeyEventCompat;
import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.View;
import android.view.Window;
import java.util.ArrayList;

public class TransportMediator
  extends TransportController
{
  public static final int FLAG_KEY_MEDIA_FAST_FORWARD = 64;
  public static final int FLAG_KEY_MEDIA_NEXT = 128;
  public static final int FLAG_KEY_MEDIA_PAUSE = 16;
  public static final int FLAG_KEY_MEDIA_PLAY = 4;
  public static final int FLAG_KEY_MEDIA_PLAY_PAUSE = 8;
  public static final int FLAG_KEY_MEDIA_PREVIOUS = 1;
  public static final int FLAG_KEY_MEDIA_REWIND = 2;
  public static final int FLAG_KEY_MEDIA_STOP = 32;
  public static final int KEYCODE_MEDIA_PAUSE = 127;
  public static final int KEYCODE_MEDIA_PLAY = 126;
  public static final int KEYCODE_MEDIA_RECORD = 130;
  final AudioManager mAudioManager;
  final TransportPerformer mCallbacks;
  final Context mContext;
  final TransportMediatorJellybeanMR2 mController;
  final Object mDispatcherState;
  final KeyEvent.Callback mKeyEventCallback = new TransportMediator.2(this);
  final ArrayList<TransportStateListener> mListeners = new ArrayList();
  final TransportMediatorCallback mTransportKeyCallback = new TransportMediator.1(this);
  final View mView;
  
  public TransportMediator(Activity paramActivity, TransportPerformer paramTransportPerformer)
  {
    this(paramActivity, null, paramTransportPerformer);
  }
  
  private TransportMediator(Activity paramActivity, View paramView, TransportPerformer paramTransportPerformer)
  {
    if (paramActivity != null) {}
    for (Object localObject = paramActivity;; localObject = paramView.getContext())
    {
      mContext = ((Context)localObject);
      mCallbacks = paramTransportPerformer;
      mAudioManager = ((AudioManager)mContext.getSystemService("audio"));
      if (paramActivity != null) {
        paramView = paramActivity.getWindow().getDecorView();
      }
      mView = paramView;
      mDispatcherState = KeyEventCompat.getKeyDispatcherState(mView);
      if (Build.VERSION.SDK_INT < 18) {
        break;
      }
      mController = new TransportMediatorJellybeanMR2(mContext, mAudioManager, mView, mTransportKeyCallback);
      return;
    }
    mController = null;
  }
  
  public TransportMediator(View paramView, TransportPerformer paramTransportPerformer)
  {
    this(null, paramView, paramTransportPerformer);
  }
  
  private TransportStateListener[] getListeners()
  {
    if (mListeners.size() <= 0) {
      return null;
    }
    TransportStateListener[] arrayOfTransportStateListener = new TransportStateListener[mListeners.size()];
    mListeners.toArray(arrayOfTransportStateListener);
    return arrayOfTransportStateListener;
  }
  
  static boolean isMediaKey(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return false;
    }
    return true;
  }
  
  private void pushControllerState()
  {
    if (mController != null) {
      mController.refreshState(mCallbacks.onIsPlaying(), mCallbacks.onGetCurrentPosition(), mCallbacks.onGetTransportControlFlags());
    }
  }
  
  private void reportPlayingChanged()
  {
    TransportStateListener[] arrayOfTransportStateListener = getListeners();
    if (arrayOfTransportStateListener != null)
    {
      int j = arrayOfTransportStateListener.length;
      int i = 0;
      while (i < j)
      {
        arrayOfTransportStateListener[i].onPlayingChanged(this);
        i += 1;
      }
    }
  }
  
  private void reportTransportControlsChanged()
  {
    TransportStateListener[] arrayOfTransportStateListener = getListeners();
    if (arrayOfTransportStateListener != null)
    {
      int j = arrayOfTransportStateListener.length;
      int i = 0;
      while (i < j)
      {
        arrayOfTransportStateListener[i].onTransportControlsChanged(this);
        i += 1;
      }
    }
  }
  
  public void destroy()
  {
    mController.destroy();
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return KeyEventCompat.dispatch(paramKeyEvent, mKeyEventCallback, mDispatcherState, this);
  }
  
  public int getBufferPercentage()
  {
    return mCallbacks.onGetBufferPercentage();
  }
  
  public long getCurrentPosition()
  {
    return mCallbacks.onGetCurrentPosition();
  }
  
  public long getDuration()
  {
    return mCallbacks.onGetDuration();
  }
  
  public Object getRemoteControlClient()
  {
    if (mController != null) {
      return mController.getRemoteControlClient();
    }
    return null;
  }
  
  public int getTransportControlFlags()
  {
    return mCallbacks.onGetTransportControlFlags();
  }
  
  public boolean isPlaying()
  {
    return mCallbacks.onIsPlaying();
  }
  
  public void pausePlaying()
  {
    if (mController != null) {
      mController.pausePlaying();
    }
    mCallbacks.onPause();
    pushControllerState();
    reportPlayingChanged();
  }
  
  public void refreshState()
  {
    pushControllerState();
    reportPlayingChanged();
    reportTransportControlsChanged();
  }
  
  public void registerStateListener(TransportStateListener paramTransportStateListener)
  {
    mListeners.add(paramTransportStateListener);
  }
  
  public void seekTo(long paramLong)
  {
    mCallbacks.onSeekTo(paramLong);
  }
  
  public void startPlaying()
  {
    if (mController != null) {
      mController.startPlaying();
    }
    mCallbacks.onStart();
    pushControllerState();
    reportPlayingChanged();
  }
  
  public void stopPlaying()
  {
    if (mController != null) {
      mController.stopPlaying();
    }
    mCallbacks.onStop();
    pushControllerState();
    reportPlayingChanged();
  }
  
  public void unregisterStateListener(TransportStateListener paramTransportStateListener)
  {
    mListeners.remove(paramTransportStateListener);
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.TransportMediator
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */