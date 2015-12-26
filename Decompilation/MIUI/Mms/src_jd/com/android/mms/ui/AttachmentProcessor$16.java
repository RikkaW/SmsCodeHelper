package com.android.mms.ui;

import android.content.res.Resources;
import android.widget.Toast;

class AttachmentProcessor$16
  implements Runnable
{
  AttachmentProcessor$16(AttachmentProcessor paramAttachmentProcessor, int paramInt1, int paramInt2) {}
  
  public void run()
  {
    Object localObject = AttachmentProcessor.access$100(this$0).getResources();
    String str2 = ((Resources)localObject).getString(val$mediaTypeStringId);
    String str1;
    switch (val$error)
    {
    default: 
      throw new IllegalArgumentException("unknown error " + val$error);
    case -1: 
      localObject = ((Resources)localObject).getString(2131361853, new Object[] { str2 });
      Toast.makeText(AttachmentProcessor.access$100(this$0), (CharSequence)localObject, 0).show();
      return;
    case -3: 
      str1 = ((Resources)localObject).getString(2131361847, new Object[] { str2 });
      localObject = ((Resources)localObject).getString(2131361848, new Object[] { str2 });
    }
    for (;;)
    {
      MessageUtils.showErrorDialog(AttachmentProcessor.access$100(this$0), str1, (String)localObject);
      return;
      str1 = ((Resources)localObject).getString(2131361849, new Object[] { str2 });
      localObject = ((Resources)localObject).getString(2131361853, new Object[] { str2 });
      continue;
      str1 = ((Resources)localObject).getString(2131361854);
      localObject = ((Resources)localObject).getString(2131361855);
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */