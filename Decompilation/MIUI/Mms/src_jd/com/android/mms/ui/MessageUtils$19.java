package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import com.android.mms.data.Contact;

final class MessageUtils$19
  implements Runnable
{
  MessageUtils$19(Contact paramContact, String paramString, MessageUtils.UriInfo paramUriInfo, Context paramContext) {}
  
  public void run()
  {
    Intent localIntent = new Intent("android.intent.action.EDIT", val$c.getUri());
    localIntent.putExtra(val$insert, val$info.content);
    MessageUtils.access$000(val$context, localIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */