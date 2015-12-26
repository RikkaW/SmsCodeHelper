package com.android.mms.audio;

import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.Button;

class AudioRecordingController$3
  implements View.OnLayoutChangeListener
{
  AudioRecordingController$3(AudioRecordingController paramAudioRecordingController) {}
  
  public void onLayoutChange(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    AudioRecordingController.access$300(this$0).getLocationOnScreen(AudioRecordingController.access$200(this$0));
    if (AudioRecordingController.access$400(this$0).getVisibility() == 0)
    {
      AudioRecordingController.access$400(this$0).setY(AudioRecordingController.access$200(this$0)[1]);
      return;
    }
    AudioRecordingController.access$500(this$0).setY(AudioRecordingController.access$200(this$0)[1]);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioRecordingController.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */