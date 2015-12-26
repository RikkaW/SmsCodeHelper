package com.android.mms.audio;

import android.view.MotionEvent;

public class AudioBtnTouchRunnable
  implements Runnable
{
  private AudioRecordingController mController;
  private MotionEvent mEvent;
  private boolean mIsRunning;
  
  public AudioBtnTouchRunnable(AudioRecordingController paramAudioRecordingController)
  {
    mController = paramAudioRecordingController;
  }
  
  public boolean isRunning()
  {
    return mIsRunning;
  }
  
  public void run()
  {
    mIsRunning = true;
    mController.startRecord(mEvent);
  }
  
  public void setEvent(MotionEvent paramMotionEvent)
  {
    mEvent = paramMotionEvent;
    mIsRunning = false;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioBtnTouchRunnable
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */