package com.android.mms.ui;

import android.content.Context;
import android.content.res.Resources;
import android.text.ClipboardManager;
import android.widget.Toast;

final class MessageUtils$15
  implements Runnable
{
  MessageUtils$15(Context paramContext, MessageUtils.UriInfo paramUriInfo) {}
  
  public void run()
  {
    ((ClipboardManager)val$context.getSystemService("clipboard")).setText(val$info.content);
    Toast.makeText(val$context, val$context.getResources().getString(2131362081), 0).show();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.15
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */