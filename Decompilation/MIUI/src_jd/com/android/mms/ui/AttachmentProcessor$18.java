package com.android.mms.ui;

import android.content.ContentUris;
import android.net.Uri;
import android.util.Log;
import com.android.mms.data.WorkingMessage;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;

class AttachmentProcessor$18
  implements MessageUtils.ResizeImageResultCallback
{
  AttachmentProcessor$18(AttachmentProcessor paramAttachmentProcessor) {}
  
  public void onResizeResult(PduPart paramPduPart, boolean paramBoolean)
  {
    if (paramPduPart == null)
    {
      this$0.handleAddAttachmentError(-1, 2131361995);
      return;
    }
    MiuiPduPersister localMiuiPduPersister = MiuiPduPersister.getPduPersister(AttachmentProcessor.access$100(this$0));
    WorkingMessage localWorkingMessage = AttachmentProcessor.access$100(this$0).getWorkingMessage();
    Uri localUri = localWorkingMessage.saveAsMms(true);
    int i;
    if (localUri == null) {
      i = -1;
    }
    for (;;)
    {
      this$0.handleAddAttachmentError(i, 2131361995);
      return;
      try
      {
        paramPduPart = localMiuiPduPersister.persistPart(paramPduPart, ContentUris.parseId(localUri));
        i = localWorkingMessage.setAttachment(1, paramPduPart, paramBoolean);
        Log.i("AttachmentProcessor", "ResizeImageResultCallback: dataUri=" + paramPduPart);
      }
      catch (MmsException paramPduPart)
      {
        i = -1;
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */