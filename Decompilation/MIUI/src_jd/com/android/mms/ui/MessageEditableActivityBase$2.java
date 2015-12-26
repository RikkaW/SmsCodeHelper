package com.android.mms.ui;

import android.os.AsyncTask;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import java.util.Iterator;

class MessageEditableActivityBase$2
  extends AsyncTask<Void, Void, Boolean>
{
  MessageEditableActivityBase$2(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    paramVarArgs = this$0.mConversation.getRecipients();
    boolean bool = false;
    paramVarArgs = paramVarArgs.iterator();
    for (;;)
    {
      Contact localContact;
      if (paramVarArgs.hasNext())
      {
        localContact = (Contact)paramVarArgs.next();
        if (!isCancelled()) {}
      }
      else
      {
        return Boolean.valueOf(bool);
      }
      String str = localContact.getName();
      localContact.load(true, true);
      if (!str.equals(localContact.getName())) {
        bool = true;
      }
    }
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue()) {
      this$0.onContactsUpdated(this$0.mConversation.getRecipients());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */