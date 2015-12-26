package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.widget.EditText;
import com.android.mms.data.WorkingMessage;

class AttachmentProcessor$14
  implements DialogInterface.OnClickListener
{
  AttachmentProcessor$14(AttachmentProcessor paramAttachmentProcessor, Uri paramUri, String paramString) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    WorkingMessage localWorkingMessage = AttachmentProcessor.access$100(this$0).getWorkingMessage();
    if (localWorkingMessage != null)
    {
      localWorkingMessage.removeAttachment(false);
      AttachmentProcessor.access$100(this$0).getTextEditor().setText("");
      localWorkingMessage.loadFromUri(val$uri, true, true);
      localWorkingMessage.setSubject(val$subject, true);
      AttachmentProcessor.access$100(this$0).getAttachmentView().update(localWorkingMessage);
      AttachmentProcessor.access$100(this$0).drawTopPanel();
      AttachmentProcessor.access$100(this$0).drawBottomPanel();
      this$0.updateAttachmentTypeStates();
    }
    paramDialogInterface.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */