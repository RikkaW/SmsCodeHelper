/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.view.MotionEvent
 *  java.lang.Object
 */
package com.android.mms.audio;

import android.view.MotionEvent;
import com.android.mms.audio.AudioRecordingController;

public class AudioBtnTouchRunnable
implements Runnable {
    private AudioRecordingController mController;
    private MotionEvent mEvent;
    private boolean mIsRunning;

    public AudioBtnTouchRunnable(AudioRecordingController audioRecordingController) {
        this.mController = audioRecordingController;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    @Override
    public void run() {
        this.mIsRunning = true;
        this.mController.startRecord(this.mEvent);
    }

    public void setEvent(MotionEvent motionEvent) {
        this.mEvent = motionEvent;
        this.mIsRunning = false;
    }
}

