package com.android.mms.audio;

import android.app.Application;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import com.android.mms.MmsApp;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;

public class AudioSensorManager
{
  private Handler mHandler;
  private SensorEventListener mListener = null;
  private AudioTalkMediaPlayer mPlayer;
  private SensorManager mSensorManager;
  
  public AudioSensorManager(Handler paramHandler)
  {
    mHandler = paramHandler;
    mSensorManager = ((SensorManager)MmsApp.getApp().getSystemService("sensor"));
    mPlayer = AudioTalkMediaPlayer.getInstance(MmsApp.getApp());
  }
  
  public void endListeningMode(boolean paramBoolean)
  {
    if ((paramBoolean) && (mHandler != null))
    {
      mPlayer.setEarphoneStatus(false, true);
      Message localMessage = mHandler.obtainMessage();
      what = 101;
      localMessage.sendToTarget();
    }
    if ((mSensorManager != null) && (mListener != null))
    {
      mSensorManager.unregisterListener(mListener);
      mListener = null;
    }
  }
  
  public void startListeningMode()
  {
    Sensor localSensor = mSensorManager.getDefaultSensor(8);
    if ((localSensor != null) && (mListener == null))
    {
      mListener = new SensorEventListener()
      {
        public void onAccuracyChanged(Sensor paramAnonymousSensor, int paramAnonymousInt) {}
        
        public void onSensorChanged(SensorEvent paramAnonymousSensorEvent)
        {
          if (8 == sensor.getType())
          {
            float f = values[0];
            paramAnonymousSensorEvent = mHandler.obtainMessage();
            if (Float.compare(f, 0.0F) != 0) {
              break label60;
            }
            what = 100;
            mPlayer.setEarphoneStatus(true);
          }
          for (;;)
          {
            paramAnonymousSensorEvent.sendToTarget();
            return;
            label60:
            what = 101;
            mPlayer.setEarphoneStatus(false);
          }
        }
      };
      mSensorManager.registerListener(mListener, localSensor, 2);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioSensorManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */