package com.android.mms.ui;

import android.content.Context;
import android.net.Uri;
import android.provider.Browser;

final class MessageUtils$13
  implements Runnable
{
  MessageUtils$13(Context paramContext, MessageUtils.UriInfo paramUriInfo) {}
  
  public void run()
  {
    Browser.saveBookmark(val$context, null, val$info.uri.toString());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageUtils.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */