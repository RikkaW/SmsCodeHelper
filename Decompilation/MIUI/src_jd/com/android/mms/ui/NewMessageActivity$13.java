package com.android.mms.ui;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import java.util.ArrayList;
import java.util.HashMap;

class NewMessageActivity$13
  implements View.OnClickListener
{
  NewMessageActivity$13(NewMessageActivity paramNewMessageActivity, Contact paramContact, String paramString) {}
  
  public void onClick(View arg1)
  {
    NewMessageActivity.access$500(this$0).removeView(???);
    NewMessageActivity.access$1700(this$0).remove(val$c);
    synchronized (NewMessageActivity.access$2500(this$0))
    {
      NewMessageActivity.access$2500(this$0).remove(val$c);
      NewMessageActivity.access$2600(this$0);
      NewMessageActivity.access$2700(this$0).remove(val$origNumber);
      NewMessageActivity.access$2800(this$0, val$c, true);
      this$0.onContactRemoved(val$c);
      NewMessageActivity.access$2900(this$0);
      if ((NewMessageActivity.access$1700(this$0).isEmpty()) && (NewMessageActivity.access$400(this$0).getText().length() == 0)) {
        NewMessageActivity.access$3000(this$0).setVisibility(0);
      }
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.13
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */