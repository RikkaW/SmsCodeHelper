package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import com.android.mms.data.Contact;

final class MessageUtils$16
  implements Runnable
{
  MessageUtils$16(Context paramContext, MessageUtils.UriInfo paramUriInfo) {}
  
  public void run()
  {
    MessageUtils.access$000(val$context, new Intent("android.intent.action.VIEW", val$info.contact.getUri()));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.16
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */