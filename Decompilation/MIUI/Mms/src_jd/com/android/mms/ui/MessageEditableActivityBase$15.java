package com.android.mms.ui;

class MessageEditableActivityBase$15
  implements Runnable
{
  MessageEditableActivityBase$15(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void run()
  {
    this$0.mAttachmentView.update(this$0.mWorkingMessage);
    this$0.mAttachmentProcessor.updateAttachmentTypeStates();
    this$0.drawBottomPanel();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */