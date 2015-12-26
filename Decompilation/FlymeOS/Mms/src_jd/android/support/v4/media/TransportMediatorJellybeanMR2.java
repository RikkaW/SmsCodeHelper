package android.support.v4.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.RemoteControlClient;
import android.media.RemoteControlClient.OnGetPlaybackPositionListener;
import android.media.RemoteControlClient.OnPlaybackPositionUpdateListener;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnWindowAttachListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;

class TransportMediatorJellybeanMR2
  implements RemoteControlClient.OnGetPlaybackPositionListener, RemoteControlClient.OnPlaybackPositionUpdateListener
{
  AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new TransportMediatorJellybeanMR2.4(this);
  boolean mAudioFocused;
  final AudioManager mAudioManager;
  final Context mContext;
  boolean mFocused;
  final Intent mIntent;
  final BroadcastReceiver mMediaButtonReceiver = new TransportMediatorJellybeanMR2.3(this);
  PendingIntent mPendingIntent;
  int mPlayState = 0;
  final String mReceiverAction;
  final IntentFilter mReceiverFilter;
  RemoteControlClient mRemoteControl;
  final View mTargetView;
  final TransportMediatorCallback mTransportCallback;
  final ViewTreeObserver.OnWindowAttachListener mWindowAttachListener = new TransportMediatorJellybeanMR2.1(this);
  final ViewTreeObserver.OnWindowFocusChangeListener mWindowFocusListener = new TransportMediatorJellybeanMR2.2(this);
  
  public TransportMediatorJellybeanMR2(Context paramContext, AudioManager paramAudioManager, View paramView, TransportMediatorCallback paramTransportMediatorCallback)
  {
    mContext = paramContext;
    mAudioManager = paramAudioManager;
    mTargetView = paramView;
    mTransportCallback = paramTransportMediatorCallback;
    mReceiverAction = (paramContext.getPackageName() + ":transport:" + System.identityHashCode(this));
    mIntent = new Intent(mReceiverAction);
    mIntent.setPackage(paramContext.getPackageName());
    mReceiverFilter = new IntentFilter();
    mReceiverFilter.addAction(mReceiverAction);
    mTargetView.getViewTreeObserver().addOnWindowAttachListener(mWindowAttachListener);
    mTargetView.getViewTreeObserver().addOnWindowFocusChangeListener(mWindowFocusListener);
  }
  
  public void destroy()
  {
    windowDetached();
    mTargetView.getViewTreeObserver().removeOnWindowAttachListener(mWindowAttachListener);
    mTargetView.getViewTreeObserver().removeOnWindowFocusChangeListener(mWindowFocusListener);
  }
  
  void dropAudioFocus()
  {
    if (mAudioFocused)
    {
      mAudioFocused = false;
      mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
    }
  }
  
  void gainFocus()
  {
    if (!mFocused)
    {
      mFocused = true;
      mAudioManager.registerMediaButtonEventReceiver(mPendingIntent);
      mAudioManager.registerRemoteControlClient(mRemoteControl);
      if (mPlayState == 3) {
        takeAudioFocus();
      }
    }
  }
  
  public Object getRemoteControlClient()
  {
    return mRemoteControl;
  }
  
  void loseFocus()
  {
    dropAudioFocus();
    if (mFocused)
    {
      mFocused = false;
      mAudioManager.unregisterRemoteControlClient(mRemoteControl);
      mAudioManager.unregisterMediaButtonEventReceiver(mPendingIntent);
    }
  }
  
  public long onGetPlaybackPosition()
  {
    return mTransportCallback.getPlaybackPosition();
  }
  
  public void onPlaybackPositionUpdate(long paramLong)
  {
    mTransportCallback.playbackPositionUpdate(paramLong);
  }
  
  public void pausePlaying()
  {
    if (mPlayState == 3)
    {
      mPlayState = 2;
      mRemoteControl.setPlaybackState(2);
    }
    dropAudioFocus();
  }
  
  public void refreshState(boolean paramBoolean, long paramLong, int paramInt)
  {
    RemoteControlClient localRemoteControlClient;
    int i;
    if (mRemoteControl != null)
    {
      localRemoteControlClient = mRemoteControl;
      if (!paramBoolean) {
        break label47;
      }
      i = 3;
      if (!paramBoolean) {
        break label53;
      }
    }
    label47:
    label53:
    for (float f = 1.0F;; f = 0.0F)
    {
      localRemoteControlClient.setPlaybackState(i, paramLong, f);
      mRemoteControl.setTransportControlFlags(paramInt);
      return;
      i = 1;
      break;
    }
  }
  
  public void startPlaying()
  {
    if (mPlayState != 3)
    {
      mPlayState = 3;
      mRemoteControl.setPlaybackState(3);
    }
    if (mFocused) {
      takeAudioFocus();
    }
  }
  
  public void stopPlaying()
  {
    if (mPlayState != 1)
    {
      mPlayState = 1;
      mRemoteControl.setPlaybackState(1);
    }
    dropAudioFocus();
  }
  
  void takeAudioFocus()
  {
    if (!mAudioFocused)
    {
      mAudioFocused = true;
      mAudioManager.requestAudioFocus(mAudioFocusChangeListener, 3, 1);
    }
  }
  
  void windowAttached()
  {
    mContext.registerReceiver(mMediaButtonReceiver, mReceiverFilter);
    mPendingIntent = PendingIntent.getBroadcast(mContext, 0, mIntent, 268435456);
    mRemoteControl = new RemoteControlClient(mPendingIntent);
    mRemoteControl.setOnGetPlaybackPositionListener(this);
    mRemoteControl.setPlaybackPositionUpdateListener(this);
  }
  
  void windowDetached()
  {
    loseFocus();
    if (mPendingIntent != null)
    {
      mContext.unregisterReceiver(mMediaButtonReceiver);
      mPendingIntent.cancel();
      mPendingIntent = null;
      mRemoteControl = null;
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v4.media.TransportMediatorJellybeanMR2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */