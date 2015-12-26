package com.android.mms.ui;

import android.content.Intent;
import com.android.mms.data.Contact;

class NewMessageActivity$14$1
  implements Runnable
{
  NewMessageActivity$14$1(NewMessageActivity.14 param14) {}
  
  public void run()
  {
    Intent localIntent;
    if (this$1.val$c.isYellowPageNumber())
    {
      localIntent = new Intent("com.miui.yellowpage.action.VIEW");
      localIntent.putExtra("android.intent.extra.PHONE_NUMBER", this$1.val$c.getNumber());
      localIntent.putExtra("source", "sms_recent_contacts");
    }
    for (;;)
    {
      localIntent.setFlags(524288);
      this$1.this$0.startActivity(localIntent);
      return;
      localIntent = new Intent("android.intent.action.VIEW", this$1.val$c.getUri());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.14.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */