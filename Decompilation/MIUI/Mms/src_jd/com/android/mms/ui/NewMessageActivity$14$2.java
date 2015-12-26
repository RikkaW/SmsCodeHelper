package com.android.mms.ui;

import android.content.Intent;
import android.provider.ContactsContract.Contacts;

class NewMessageActivity$14$2
  implements Runnable
{
  NewMessageActivity$14$2(NewMessageActivity.14 param14, String paramString) {}
  
  public void run()
  {
    Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
    localIntent.putExtra(val$insert, this$1.val$origNumber);
    localIntent.setFlags(524288);
    this$1.this$0.startActivity(localIntent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.14.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */