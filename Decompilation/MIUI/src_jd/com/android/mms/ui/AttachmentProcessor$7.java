package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;

class AttachmentProcessor$7
  implements DialogInterface.OnClickListener
{
  AttachmentProcessor$7(AttachmentProcessor paramAttachmentProcessor) {}
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.dismiss();
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      paramInt = 0;
      paramDialogInterface = AttachmentProcessor.access$100(this$0).getWorkingMessage().getSlideshow();
      if (paramDialogInterface != null) {
        paramInt = paramDialogInterface.get(0).getSlideSize();
      }
      long l = AttachmentProcessor.computeAttachmentSizeLimit(paramDialogInterface, paramInt);
      if (l > 0L)
      {
        MessageUtils.recordSound(AttachmentProcessor.access$100(this$0), 105, l);
        return;
      }
      Toast.makeText(AttachmentProcessor.access$100(this$0), AttachmentProcessor.access$100(this$0).getString(2131362147), 0).show();
      return;
    }
    MessageUtils.selectAudio(AttachmentProcessor.access$100(this$0), 104);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */