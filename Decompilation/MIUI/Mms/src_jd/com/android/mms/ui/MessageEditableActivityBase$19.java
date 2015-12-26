package com.android.mms.ui;

import android.net.Uri;
import android.view.Window;
import com.android.mms.data.WorkingMessage;

class MessageEditableActivityBase$19
  implements Runnable
{
  MessageEditableActivityBase$19(MessageEditableActivityBase paramMessageEditableActivityBase, int paramInt, boolean paramBoolean, Uri paramUri) {}
  
  public void run()
  {
    this$0.mAttachmentProcessor.handleAddAttachmentError(val$error, 2131361995);
    if (this$0.getWindow().isDestroyed()) {}
    while ((!val$reload) || (this$0.mWorkingMessage == null)) {
      return;
    }
    this$0.mWorkingMessage.loadFromUri(val$uri, false);
    this$0.mAttachmentView.update(this$0.mWorkingMessage);
    this$0.drawTopPanel();
    this$0.drawBottomPanel();
    this$0.mAttachmentProcessor.updateAttachmentTypeStates();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */