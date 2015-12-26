package com.android.mms.audio;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.PlayerStatus;

public class AudioPlayingHandler
  extends Handler
{
  private AudioItemCache mAudioItemCache;
  private AudioSensorManager mAudioSensorMgr;
  private GlobalAudioPlayController mGlobalAudioPlayController = GlobalAudioPlayController.get();
  private long mMsgId;
  
  public AudioPlayingHandler(long paramLong, AudioSensorManager paramAudioSensorManager, AudioItemCache paramAudioItemCache)
  {
    mMsgId = paramLong;
    mAudioItemCache = paramAudioItemCache;
    mAudioSensorMgr = paramAudioSensorManager;
  }
  
  public void handleMessage(Message paramMessage)
  {
    if ((obj instanceof PlayerStatus)) {
      switch (obj).mType)
      {
      case 1: 
      default: 
        Log.e("AudioPlayingHandler", "playerStatus type error");
      }
    }
    for (;;)
    {
      super.handleMessage(paramMessage);
      return;
      if (mAudioSensorMgr != null) {
        mAudioSensorMgr.endListeningMode(false);
      }
      Toast.makeText(MmsApp.getApp(), 2131362357, 0).show();
      continue;
      AudioTalkMediaPlayer localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
      AudioItemController localAudioItemController = mAudioItemCache.getItemController(mMsgId);
      if (localAudioTalkMediaPlayer.isPlaying())
      {
        if (mAudioSensorMgr != null) {
          mAudioSensorMgr.startListeningMode();
        }
        AudioHelper.gainFocus();
        if (localAudioItemController != null)
        {
          localAudioItemController.setPlayingViews();
          localAudioItemController.markAsRead();
          continue;
          localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
          localAudioItemController = mAudioItemCache.getItemController(mMsgId);
          if (mAudioSensorMgr != null) {
            mAudioSensorMgr.endListeningMode(true);
          }
          if (localAudioTalkMediaPlayer.hasNext()) {
            localAudioTalkMediaPlayer.playNext();
          }
          AudioHelper.releaseFocus();
          if (localAudioItemController != null) {
            localAudioItemController.resetPlayingViews();
          }
          if (mGlobalAudioPlayController != null) {
            mGlobalAudioPlayController.onStopPlay();
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioPlayingHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */