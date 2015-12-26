package com.android.mms.ui;

import android.os.AsyncTask;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import java.util.Iterator;

class NewMessageActivity$20
  extends AsyncTask<Void, Void, Boolean>
{
  private ContactList mContactList;
  
  NewMessageActivity$20(NewMessageActivity paramNewMessageActivity) {}
  
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = bool3;
    Contact localContact;
    if (mContactList != null)
    {
      bool1 = bool3;
      if (!mContactList.isEmpty())
      {
        paramVarArgs = mContactList.iterator();
        bool1 = bool2;
        if (paramVarArgs.hasNext())
        {
          localContact = (Contact)paramVarArgs.next();
          if (!isCancelled()) {
            break label72;
          }
        }
      }
    }
    for (bool1 = false;; bool1 = false)
    {
      return Boolean.valueOf(bool1);
      label72:
      if (!localContact.existsInDatabase())
      {
        localContact.load(true, true);
        bool2 = true;
      }
      if (!isCancelled()) {
        break;
      }
    }
  }
  
  protected void onCancelled()
  {
    if (mContactList != null) {
      mContactList.clear();
    }
    mContactList = null;
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    NewMessageActivity.access$4002(this$0, null);
    if (mContactList != null) {
      mContactList.clear();
    }
    mContactList = null;
    if ((paramBoolean.booleanValue()) && (this$0.isResumed()))
    {
      NewMessageActivity.access$1900(this$0);
      NewMessageActivity.access$4100(this$0);
    }
  }
  
  protected void onPreExecute()
  {
    mContactList = ((ContactList)NewMessageActivity.access$1700(this$0).clone());
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.20
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */