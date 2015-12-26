package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract.Contacts;

final class MessageUtils$17
  implements Runnable
{
  MessageUtils$17(String paramString, MessageUtils.UriInfo paramUriInfo, Context paramContext) {}
  
  public void run()
  {
    Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
    localIntent.putExtra(val$insert, val$info.content);
    MessageUtils.access$000(val$context, localIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */