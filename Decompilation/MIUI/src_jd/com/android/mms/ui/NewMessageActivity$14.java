package com.android.mms.ui;

import android.content.Intent;
import android.provider.ContactsContract.Contacts;
import android.provider.Telephony.Mms;
import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Toast;
import com.android.mms.data.Contact;
import miui.view.menu.ContextMenuDialog;

class NewMessageActivity$14
  implements View.OnLongClickListener
{
  NewMessageActivity$14(NewMessageActivity paramNewMessageActivity, String paramString, Contact paramContact) {}
  
  public boolean onLongClick(final View paramView)
  {
    ContextMenuDialog localContextMenuDialog = new ContextMenuDialog(this$0);
    localContextMenuDialog.setTitle(val$origNumber);
    if (NewMessageActivity.access$1500(this$0, val$c))
    {
      localContextMenuDialog.setTitle(String.format("%s (%s)", new Object[] { val$origNumber, val$c.getName() }));
      localContextMenuDialog.addMenuItem(2131361889, new Runnable()
      {
        public void run()
        {
          Intent localIntent;
          if (val$c.isYellowPageNumber())
          {
            localIntent = new Intent("com.miui.yellowpage.action.VIEW");
            localIntent.putExtra("android.intent.extra.PHONE_NUMBER", val$c.getNumber());
            localIntent.putExtra("source", "sms_recent_contacts");
          }
          for (;;)
          {
            localIntent.setFlags(524288);
            this$0.startActivity(localIntent);
            return;
            localIntent = new Intent("android.intent.action.VIEW", val$c.getUri());
          }
        }
      });
    }
    label152:
    for (;;)
    {
      localContextMenuDialog.addMenuItem(2131362080, new Runnable()
      {
        public void run()
        {
          ((ClipboardManager)this$0.getSystemService("clipboard")).setText(val$origNumber);
          Toast.makeText(this$0, 2131362081, 0).show();
        }
      });
      localContextMenuDialog.show();
      return true;
      if (Telephony.Mms.isPhoneNumber(val$origNumber)) {
        paramView = "phone";
      }
      for (;;)
      {
        if (paramView == null) {
          break label152;
        }
        localContextMenuDialog.addMenuItem(2131362083, new Runnable()
        {
          public void run()
          {
            Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
            localIntent.putExtra(paramView, val$origNumber);
            localIntent.setFlags(524288);
            this$0.startActivity(localIntent);
          }
        });
        break;
        if (Telephony.Mms.isEmailAddress(val$origNumber)) {
          paramView = "email";
        } else {
          paramView = null;
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.14
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */