package com.android.mms.audio;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.util.VibratorManager;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;
import com.xiaomi.mms.mx.audio.player.XMAudioRecorder;

class AudioRecordingController$5
  extends Handler
{
  private boolean mIsWarning;
  
  AudioRecordingController$5(AudioRecordingController paramAudioRecordingController) {}
  
  public void handleMessage(Message paramMessage)
  {
    switch (what)
    {
    case 0: 
    default: 
    case 1: 
    case 2: 
      do
      {
        return;
        VibratorManager.vibrate(AudioRecordingController.access$900(this$0), 20);
        AudioHelper.gainFocus();
        AudioTalkMediaPlayer.getInstance(MmsApp.getApp()).stop();
        AudioRecordingController.access$1002(this$0, false);
        mIsWarning = false;
        return;
        AudioRecordingController.access$1100(this$0).addVolumes(((Integer)obj).intValue());
        if (AudioRecordingController.access$000(this$0).getDuration() >= 60000)
        {
          AudioRecordingController.access$1002(this$0, false);
          AudioRecordingController.access$000(this$0).stop();
          AudioRecordingController.access$1200(this$0);
          return;
        }
      } while (AudioRecordingController.access$000(this$0).getDuration() <= 50000);
      if (!mIsWarning)
      {
        VibratorManager.vibrate(AudioRecordingController.access$900(this$0), 20);
        mIsWarning = true;
      }
      int i = (60000 - AudioRecordingController.access$000(this$0).getDuration()) / 1000;
      paramMessage = String.format(MmsApp.getApp().getString(2131362355), new Object[] { Integer.valueOf(i) });
      AudioRecordingController.access$1300(this$0).setText(paramMessage);
      AudioRecordingController.access$1300(this$0).setVisibility(0);
      return;
    case 3: 
      AudioHelper.releaseFocus();
      if (this$0.isCancelled())
      {
        AudioRecordingController.access$000(this$0).clear();
        return;
      }
      if (AudioRecordingController.access$000(this$0).getDuration() < 1000)
      {
        AudioRecordingController.access$000(this$0).clear();
        return;
      }
      AudioHelper.sendRecordedAudio(AudioRecordingController.access$000(this$0).getAudioPath(), AudioRecordingController.access$000(this$0).getDuration(), AudioRecordingController.access$1400(this$0), AudioRecordingController.access$1500(this$0), AudioRecordingController.access$1600(this$0), AudioRecordingController.access$1700(this$0), AudioRecordingController.access$1800(this$0));
      AudioRecordingController.access$1902(this$0, true);
      return;
    }
    AudioRecordingController.access$000(this$0).stop();
    AudioRecordingController.access$000(this$0).clear();
    AudioRecordingController.access$1200(this$0);
    Toast.makeText(MmsApp.getApp(), 2131362359, 1).show();
    Log.e("AudioRecordingController.RICH", "controller audio failed " + what);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioRecordingController.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */