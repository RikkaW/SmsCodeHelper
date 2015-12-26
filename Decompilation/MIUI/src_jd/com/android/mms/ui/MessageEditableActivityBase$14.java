package com.android.mms.ui;

import com.android.mms.audio.AudioRecordingController;

class MessageEditableActivityBase$14
  implements Runnable
{
  MessageEditableActivityBase$14(MessageEditableActivityBase paramMessageEditableActivityBase, boolean paramBoolean1, boolean paramBoolean2) {}
  
  public void run()
  {
    if (!val$isSendingMx2Message) {
      MessageEditableActivityBase.access$600(this$0, val$mms);
    }
    MessageEditableActivityBase.access$700(this$0, val$mms);
    this$0.postSwitchMsgType();
    this$0.mAttachmentProcessor.updateAttachmentTypeStates();
    if (val$isSendingMx2Message) {
      MessageEditableActivityBase.access$200(this$0).resetIsSendingMx2();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */