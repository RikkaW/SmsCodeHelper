package com.android.mms.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.util.Log;
import miui.provider.VoipContract;
import miui.provider.VoipContract.UsersColumn;

class SingleRecipientConversationActivity$1
  extends AsyncTask<Void, Void, Boolean>
{
  SingleRecipientConversationActivity$1(SingleRecipientConversationActivity paramSingleRecipientConversationActivity, String paramString) {}
  
  protected Boolean doInBackground(Void... paramVarArgs)
  {
    boolean bool = true;
    paramVarArgs = this$0.getContentResolver().query(VoipContract.USERS_URI, VoipContract.UsersColumn.PROJECTION, null, new String[] { val$number }, null);
    Log.v("SingleRecipientCA", " query result cursor is " + paramVarArgs);
    if (paramVarArgs != null) {}
    for (;;)
    {
      if (paramVarArgs != null) {
        paramVarArgs.close();
      }
      return Boolean.valueOf(bool);
      bool = false;
    }
  }
  
  protected void onPostExecute(Boolean paramBoolean)
  {
    SingleRecipientConversationActivity.access$102(this$0, null);
    if (this$0.isFinishing()) {}
    while (!paramBoolean.booleanValue()) {
      return;
    }
    SingleRecipientConversationActivity.access$200(this$0).updateState(true);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationActivity.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */