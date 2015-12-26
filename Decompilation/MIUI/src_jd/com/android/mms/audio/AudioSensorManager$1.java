package com.android.mms.audio;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.os.Message;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;

class AudioSensorManager$1
  implements SensorEventListener
{
  AudioSensorManager$1(AudioSensorManager paramAudioSensorManager) {}
  
  public void onAccuracyChanged(Sensor paramSensor, int paramInt) {}
  
  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    if (8 == sensor.getType())
    {
      float f = values[0];
      paramSensorEvent = AudioSensorManager.access$000(this$0).obtainMessage();
      if (Float.compare(f, 0.0F) != 0) {
        break label60;
      }
      what = 100;
      AudioSensorManager.access$100(this$0).setEarphoneStatus(true);
    }
    for (;;)
    {
      paramSensorEvent.sendToTarget();
      return;
      label60:
      what = 101;
      AudioSensorManager.access$100(this$0).setEarphoneStatus(false);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioSensorManager.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */