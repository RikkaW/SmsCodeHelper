package com.android.mms.ui;

import android.os.AsyncTask;
import android.view.View;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import miui.yellowpage.MiPubUtils;

class SingleRecipientConversationActivity$8
  extends AsyncTask<Void, Void, Void>
{
  SingleRecipientConversationActivity$8(SingleRecipientConversationActivity paramSingleRecipientConversationActivity) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    this$0.mMenuList = MiPubUtils.getYellowPageMenu(this$0, this$0.mYellowPageId.longValue(), false, 4);
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    SingleRecipientConversationActivity.access$502(this$0, null);
    if (this$0.isFinishing()) {
      return;
    }
    this$0.findViewById(2131820694).setVisibility(0);
    this$0.showMenuMode();
    SingleRecipientConversationActivity.access$600(this$0);
  }
  
  protected void onPreExecute()
  {
    this$0.mContact = ((Contact)this$0.mConversation.getRecipients().get(0));
    this$0.mYellowPageId = Long.valueOf(this$0.mContact.getYellowPageId());
    this$0.mYellowPageName = this$0.mContact.getName();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.8
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */