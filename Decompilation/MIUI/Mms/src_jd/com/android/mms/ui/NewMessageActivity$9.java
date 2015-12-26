package com.android.mms.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.CallLog.Calls;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import java.util.HashSet;
import java.util.Iterator;
import miui.provider.ExtraTelephony.MmsSms;

class NewMessageActivity$9
  extends AsyncTask<Void, Void, Void>
{
  private final HashSet<String> mExistingNames = new HashSet();
  
  NewMessageActivity$9(NewMessageActivity paramNewMessageActivity) {}
  
  private void addRecentRecipient(String paramString)
  {
    paramString = Contact.get(paramString);
    paramString.load(true, false);
    if ((NewMessageActivity.access$1500(this$0, paramString)) && (!mExistingNames.contains(paramString.getName())))
    {
      mExistingNames.add(paramString.getName());
      NewMessageActivity.access$1400(this$0).add(paramString);
    }
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    Cursor localCursor1 = this$0.getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[] { "number", "date" }, null, null, "date DESC LIMIT 50");
    if (localCursor1 == null) {
      return null;
    }
    Cursor localCursor2 = this$0.getContentResolver().query(ExtraTelephony.MmsSms.CONTENT_RECENT_RECIPIENTS_URI, new String[] { "address", "date" }, null, null, "date DESC LIMIT 50");
    if (localCursor2 == null)
    {
      localCursor1.close();
      return null;
    }
    try
    {
      localCursor1.moveToPosition(-1);
      localCursor2.moveToPosition(-1);
      Object localObject2 = null;
      paramVarArgs = null;
      long l1 = 0L;
      long l2 = 0L;
      if (localCursor1.moveToNext())
      {
        localObject2 = localCursor1.getString(0);
        l1 = localCursor1.getLong(1);
      }
      long l3 = l1;
      Object localObject1 = localObject2;
      if (localCursor2.moveToNext())
      {
        paramVarArgs = localCursor2.getString(0);
        l2 = localCursor2.getLong(1);
        localObject1 = localObject2;
        l3 = l1;
      }
      for (;;)
      {
        localObject2 = localObject1;
        if (localCursor1.isAfterLast()) {
          break;
        }
        localObject2 = localObject1;
        if (localCursor2.isAfterLast()) {
          break;
        }
        localObject2 = localObject1;
        if (NewMessageActivity.access$1400(this$0).size() >= 6) {
          break;
        }
        if (l3 > l2)
        {
          addRecentRecipient((String)localObject1);
          if (localCursor1.moveToNext())
          {
            localObject1 = localCursor1.getString(0);
            l3 = localCursor1.getLong(1);
          }
        }
        else
        {
          addRecentRecipient(paramVarArgs);
          if (localCursor2.moveToNext())
          {
            paramVarArgs = localCursor2.getString(0);
            l2 = localCursor2.getLong(1);
          }
        }
      }
      for (;;)
      {
        localObject1 = paramVarArgs;
        if (localCursor1.isAfterLast()) {
          break;
        }
        localObject1 = paramVarArgs;
        if (NewMessageActivity.access$1400(this$0).size() >= 6) {
          break;
        }
        addRecentRecipient((String)localObject2);
        if (localCursor1.moveToNext()) {
          localObject2 = localCursor1.getString(0);
        }
      }
      while ((!localCursor2.isAfterLast()) && (NewMessageActivity.access$1400(this$0).size() < 6))
      {
        addRecentRecipient((String)localObject1);
        if (localCursor2.moveToNext()) {
          localObject1 = localCursor2.getString(0);
        }
      }
      return null;
    }
    finally
    {
      localCursor1.close();
      localCursor2.close();
    }
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    boolean bool2 = false;
    paramVoid = NewMessageActivity.access$1400(this$0).iterator();
    while (paramVoid.hasNext())
    {
      final Contact localContact = (Contact)paramVoid.next();
      View localView = this$0.getLayoutInflater().inflate(2130968693, NewMessageActivity.access$1600(this$0), false);
      TextView localTextView = (TextView)localView.findViewById(2131820852);
      if (NewMessageActivity.access$1700(this$0).contains(localContact)) {
        localTextView.setEnabled(false);
      }
      localTextView.setText(localContact.getName());
      localTextView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          NewMessageActivity.access$1800(this$0, localContact);
          NewMessageActivity.access$1900(this$0);
        }
      });
      NewMessageActivity.access$1600(this$0).addView(localView);
      NewMessageActivity.access$2002(this$0, false);
    }
    paramVoid = this$0;
    boolean bool1 = bool2;
    if (NewMessageActivity.access$1400(this$0).size() > 0)
    {
      bool1 = bool2;
      if (NewMessageActivity.access$400(this$0).hasFocus()) {
        bool1 = true;
      }
    }
    NewMessageActivity.access$2100(paramVoid, bool1);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity.9
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */