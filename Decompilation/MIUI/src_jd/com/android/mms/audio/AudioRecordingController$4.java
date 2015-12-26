package com.android.mms.audio;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;

class AudioRecordingController$4
  implements View.OnTouchListener
{
  AudioRecordingController$4(AudioRecordingController paramAudioRecordingController) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (!this$0.isRecording()) {
      switch (paramMotionEvent.getAction())
      {
      }
    }
    for (;;)
    {
      AudioRecordingController.access$500(this$0).setOnTouchListener(null);
      return false;
      AudioRecordingController.access$600(this$0).setEvent(paramMotionEvent);
      AudioRecordingController.access$700(this$0).postDelayed(AudioRecordingController.access$600(this$0), 200L);
      continue;
      if (!AudioRecordingController.access$600(this$0).isRunning())
      {
        AudioRecordingController.access$700(this$0).removeCallbacks(AudioRecordingController.access$600(this$0));
        AudioRecordingController.access$700(this$0).removeCallbacks(AudioRecordingController.access$800(this$0));
        AudioRecordingController.access$100(this$0);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioRecordingController.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */