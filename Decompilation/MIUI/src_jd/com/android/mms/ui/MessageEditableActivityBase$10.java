package com.android.mms.ui;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import com.android.mms.data.WorkingMessage;
import java.util.ArrayList;

class MessageEditableActivityBase$10
  implements Runnable
{
  MessageEditableActivityBase$10(MessageEditableActivityBase paramMessageEditableActivityBase, int paramInt, ArrayList paramArrayList, String paramString1, String paramString2, Runnable paramRunnable, AlertDialog paramAlertDialog) {}
  
  public void run()
  {
    int i = 0;
    while (i < val$numberToImport)
    {
      Parcelable localParcelable = (Parcelable)val$uris.get(i);
      this$0.mAttachmentProcessor.addAttachment(val$mimeType, (Uri)localParcelable, true);
      i += 1;
    }
    if (!TextUtils.isEmpty(val$finalText)) {
      this$0.mWorkingMessage.tryInsertExtraText(val$finalText);
    }
    this$0.mHandler.removeCallbacks(val$showProgress);
    val$dialog.dismiss();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */