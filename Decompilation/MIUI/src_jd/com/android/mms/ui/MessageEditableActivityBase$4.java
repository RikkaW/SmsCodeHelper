package com.android.mms.ui;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.android.mms.audio.AudioBtnTouchRunnable;
import com.android.mms.audio.AudioRecordingController;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;

class MessageEditableActivityBase$4
  implements View.OnTouchListener
{
  MessageEditableActivityBase$4(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if ((this$0.mMxStyle) && (!this$0.mAirModeOn) && (!MessageEditableActivityBase.access$100(this$0)) && (MessageEditableActivityBase.access$200(this$0) != null) && (!MessageEditableActivityBase.access$200(this$0).isRecording())) {
      switch (paramMotionEvent.getAction())
      {
      }
    }
    for (;;)
    {
      return false;
      if (this$0.mSimButtonContainer != null) {
        this$0.mSimButtonContainer.setVisibility(8);
      }
      this$0.mWorkingMessage.syncWorkingRecipients();
      MessageEditableActivityBase.access$200(this$0).setThreadId(this$0.mConversation.ensureThreadId());
      MessageEditableActivityBase.access$200(this$0).setConversation(this$0.mConversation);
      MessageEditableActivityBase.access$300(this$0).setEvent(paramMotionEvent);
      this$0.mHandler.postDelayed(MessageEditableActivityBase.access$300(this$0), 200L);
      continue;
      if (!MessageEditableActivityBase.access$300(this$0).isRunning())
      {
        this$0.mHandler.removeCallbacks(MessageEditableActivityBase.access$300(this$0));
        MessageEditableActivityBase.access$200(this$0).showRemind();
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.4
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */