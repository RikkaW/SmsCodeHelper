package com.android.mms.ui;

import com.android.mms.data.Contact;

class NewMessagePopupActivity$5
  implements Runnable
{
  NewMessagePopupActivity$5(NewMessagePopupActivity paramNewMessagePopupActivity, Contact paramContact) {}
  
  public void run()
  {
    if ((Contact.get(this$0.mCurrentMessageThread.address) != val$updated) || (this$0.isFinishing())) {
      return;
    }
    NewMessagePopupActivity.access$500(this$0, val$updated);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessagePopupActivity.5
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */