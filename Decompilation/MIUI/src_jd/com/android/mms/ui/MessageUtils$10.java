package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

final class MessageUtils$10
  implements Runnable
{
  MessageUtils$10(Context paramContext, MessageUtils.UriInfo paramUriInfo) {}
  
  public void run()
  {
    MessageUtils.access$000(val$context, new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + val$info.content)));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */