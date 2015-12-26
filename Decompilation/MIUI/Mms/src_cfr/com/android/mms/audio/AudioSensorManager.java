/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.hardware.Sensor
 *  android.hardware.SensorEvent
 *  android.hardware.SensorEventListener
 *  android.hardware.SensorManager
 *  android.os.Handler
 *  android.os.Message
 *  java.lang.Float
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.audio;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import com.android.mms.MmsApp;
import com.xiaomi.mms.mx.audio.player.AudioTalkMediaPlayer;

public class AudioSensorManager {
    private Handler mHandler;
    private SensorEventListener mListener = null;
    private AudioTalkMediaPlayer mPlayer;
    private SensorManager mSensorManager;

    public AudioSensorManager(Handler handler) {
        this.mHandler = handler;
        this.mSensorManager = (SensorManager)MmsApp.getApp().getSystemService("sensor");
        this.mPlayer = AudioTalkMediaPlayer.getInstance((Context)MmsApp.getApp());
    }

    public void endListeningMode(boolean bl) {
        if (bl && this.mHandler != null) {
            this.mPlayer.setEarphoneStatus(false, true);
            Message message = this.mHandler.obtainMessage();
            message.what = 101;
            message.sendToTarget();
        }
        if (this.mSensorManager != null && this.mListener != null) {
            this.mSensorManager.unregisterListener(this.mListener);
            this.mListener = null;
        }
    }

    public void startListeningMode() {
        Sensor sensor = this.mSensorManager.getDefaultSensor(8);
        if (sensor != null && this.mListener == null) {
            this.mListener = new SensorEventListener(){

                public void onAccuracyChanged(Sensor sensor, int n) {
                }

                /*
                 * Enabled aggressive block sorting
                 */
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (8 == sensorEvent.sensor.getType()) {
                        float f2 = sensorEvent.values[0];
                        sensorEvent = AudioSensorManager.this.mHandler.obtainMessage();
                        if (Float.compare((float)f2, (float)0.0f) == 0) {
                            sensorEvent.what = 100;
                            AudioSensorManager.this.mPlayer.setEarphoneStatus(true);
                        } else {
                            sensorEvent.what = 101;
                            AudioSensorManager.this.mPlayer.setEarphoneStatus(false);
                        }
                        sensorEvent.sendToTarget();
                    }
                }
            };
            this.mSensorManager.registerListener(this.mListener, sensor, 2);
        }
    }

}

