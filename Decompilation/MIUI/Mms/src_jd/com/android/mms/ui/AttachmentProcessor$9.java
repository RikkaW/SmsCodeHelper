package com.android.mms.ui;

import android.content.Intent;
import com.android.mms.TempFileProvider;

class AttachmentProcessor$9
  implements Runnable
{
  AttachmentProcessor$9(AttachmentProcessor paramAttachmentProcessor) {}
  
  public void run()
  {
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", TempFileProvider.SCRAP_CONTENT_URI);
    AttachmentProcessor.access$100(this$0).startActivityForResult(localIntent, 101);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */