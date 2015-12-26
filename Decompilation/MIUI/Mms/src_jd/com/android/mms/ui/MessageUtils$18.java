package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;

final class MessageUtils$18
  implements Runnable
{
  MessageUtils$18(String paramString, MessageUtils.UriInfo paramUriInfo, Context paramContext) {}
  
  public void run()
  {
    Intent localIntent = new Intent("android.intent.action.INSERT_OR_EDIT");
    localIntent.setType("vnd.android.cursor.item/contact");
    localIntent.putExtra(val$insert, val$info.content);
    MessageUtils.access$000(val$context, localIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.18
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */