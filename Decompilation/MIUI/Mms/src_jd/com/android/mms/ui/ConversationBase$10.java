package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

class ConversationBase$10
  implements Runnable
{
  ConversationBase$10(ConversationBase paramConversationBase, Uri paramUri, ContentValues paramContentValues) {}
  
  public void run()
  {
    this$0.getContentResolver().update(val$lockUri, val$values, null, null);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationBase.10
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */