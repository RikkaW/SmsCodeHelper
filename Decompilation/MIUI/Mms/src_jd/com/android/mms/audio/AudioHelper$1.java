package com.android.mms.audio;

import android.media.AudioManager.OnAudioFocusChangeListener;
import com.android.mms.MmsApp;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;

final class AudioHelper$1
  implements AudioManager.OnAudioFocusChangeListener
{
  public void onAudioFocusChange(int paramInt)
  {
    if (paramInt == -1)
    {
      AudioTalkMediaPlayer localAudioTalkMediaPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
      if (localAudioTalkMediaPlayer.isPlaying()) {
        localAudioTalkMediaPlayer.stop();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioHelper.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */